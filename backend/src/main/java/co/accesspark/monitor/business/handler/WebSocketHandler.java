/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.handler;

import co.accesspark.monitor.business.service.AlertService;
import co.accesspark.monitor.business.util.UtilMonitor;
import com.google.gson.Gson;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.exception.AppException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author AccessSoftware2
 */
public class WebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

  private static final Logger LOG = Logger.getLogger(UtilMonitor.class.getName());

  private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

  private LocalDateTime currentDay = LocalDateTime.now();

  @Autowired
  private AlertService alertService;
  private UtilMonitor utilMonitor;

  @Override
  public void afterConnectionEstablished(WebSocketSession session)
          throws Exception
  {
    LOG.info("Server connection opened");
    sessions.add(session);
  }

  /**
   *
   * @param session
   * @param status
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session,
          CloseStatus status)
  {
    LOG.log(Level.SEVERE, "Server sends: {0}", status);
    sessions.remove(session);
  }

  /**
   *
   * @param session
   * @param message
   * @throws Exception
   */
  @Override
  public void handleTextMessage(WebSocketSession session,
          TextMessage message)
          throws Exception
  {
    String request = message.getPayload();
    LOG.log(Level.SEVERE, "Server sends: {0}", request);
    String response = String.format("response from server to '%s'",
            HtmlUtils.htmlEscape(request));
    LOG.log(Level.SEVERE, "Server sends: {0}", response);
    session.sendMessage(new TextMessage(response));
  }

  /**
   *
   * @throws IOException
   */
  @Scheduled(fixedRate = 10000)
  void sendPeriodicMessages()
          throws IOException, AppException
  {
    try {
      LocalDateTime now = LocalDateTime.now();
      if (sessions.isEmpty()) {
        return;
      }
      for (WebSocketSession session : sessions) {
        if (!session.isOpen()) {
          sessions.remove(session);
          continue;
        }
        String alertResult = alertService.searchError(currentDay);
        if (alertResult == null) {
          return;
        }
        sendMessage(session, alertResult);
      }
      currentDay = now;
    }finally  {
      RequestUtil.remove();
    }
  }

  @Override
  public List<String> getSubProtocols()
  {
    return Collections.singletonList("subprotocol.demo.websocket");
  }

  public void sendMessage(
          WebSocketSession session, String alertResult)
          throws IOException
  {
    LOG.log(Level.SEVERE, "Server sends: {0}", alertResult);
    session.sendMessage(new TextMessage(alertResult));
  }
}
