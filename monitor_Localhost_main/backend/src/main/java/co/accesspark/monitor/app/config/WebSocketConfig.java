package co.accesspark.monitor.app.config;

import co.accesspark.monitor.business.handler.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer  {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
  {
     registry.addHandler(webSocketHandler(), "/websocket")
             .setAllowedOrigins("http://localhost:8081",
                        "chrome-extension://ggnhohnkfcpcanfekomdkjffnfcjnjam");
  }
   @Bean
   public WebSocketHandler webSocketHandler() {
       return new WebSocketHandler();
   }

}