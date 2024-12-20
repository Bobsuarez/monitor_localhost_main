/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.service;

import co.accesspark.monitor.business.constant.EGenericQuery;
import co.accesspark.monitor.business.util.UtilMonitor;
import co.accesspark.monitor.persistence.dao.AlertDAO;
import co.accesspark.monitor.persistence.dto.alert.DescriptionAlert;
import co.accesspark.monitor.persistence.entity.Alert;
import com.google.gson.Gson;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.template.IConnectionName;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AccessSoftware2
 */
@Service
public class AlertService {

  @Autowired
  private UtilMonitor utilMonitor;

  private static final Logger LOG = Logger.getLogger(AlertService.class.
          getName());
  private int counter = 10;
  private int acumulador = 0;

  public String searchError(LocalDateTime currentDay)
          throws AppException
  {
    String result = null;
    List<DescriptionAlert> alertListReturn = new ArrayList<>();
    DescriptionAlert alertReturn = new DescriptionAlert();
    if (RequestUtil.CONNECTIONS_LIST.isEmpty()) {
      return result;
    }
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      List<String> listReturn = new ArrayList<>();
      listReturn.addAll(new AlertDAO().errorDetail(key, currentDay));
      listReturn.addAll(new AlertDAO().errorHopper(key));
      listReturn.addAll(new AlertDAO().errorLevelCoin(key));
      listReturn.addAll(new AlertDAO().errorLevelBill(key));
      listReturn.addAll(new AlertDAO().errorPay(key, currentDay));
      alertReturn.setError(listReturn);
      if (alertReturn.getError().isEmpty()) {
        return result;
      }
      alertReturn.setLocation(utilMonitor.location(key.getName()));
      alertReturn = comparePending(key, alertReturn);
      if (alertReturn == null) {
        continue;
      }
      alertListReturn.add(alertReturn);
      LOG.log(Level.INFO, "Resultado de la busqueda alertas : {0}", alertReturn);
    }
    if (alertListReturn.isEmpty()) {
      return null;
    }
    result = new Gson().toJson(alertListReturn);
    alertListReturn.clear();
    return result;
  }

  public void insertAlert(DescriptionAlert alertReturn, IConnectionName connection)
          throws JdbcException
  {
    Alert alert = new Alert()
            .setTitle(new Gson().toJson(alertReturn))
            .setStatus(EGenericQuery.Status.PENDING);
    alert.insert(connection);
    RequestUtil.commit();

  }

  public DescriptionAlert comparePending(IConnectionName connection, DescriptionAlert alertReturn)
          throws JdbcException, AppException
  {

    Alert alertPending;
    alertPending = new AlertDAO().errorListSearch(connection, EGenericQuery.Status.PENDING);
    if (alertPending != null) {
      List<String> listString = utilMonitor.jsonArrayToList(alertPending.getTitle(), String.class);
      alertReturn.setError(listString);
      return alertReturn;
    }
    alertPending = new AlertDAO().errorListSearch(connection, EGenericQuery.Status.ACTIVE);
    if (alertPending != null) {
      List<String> listString = utilMonitor.jsonArrayToList(alertPending.getTitle(), String.class);
      boolean result = utilMonitor.equalLists(listString, alertReturn.getError());
      if (result) {
        if (counter > acumulador) {
          acumulador = acumulador + 1;
          return null;
        }
        acumulador = 0;
        insertAlert(alertReturn, connection);
        return alertReturn.setError(listString);
      }
    }
    insertAlert(alertReturn, connection);
    return alertReturn;
  }

  public void aceptAlert(String location)
          throws JdbcException, AppException
  {
    LOG.log(Level.INFO, "Resultado de entrada de list: {0}", location);
    String name = utilMonitor.connectionToLocation(location);
    IConnectionName key = utilMonitor.findConnection(name);
    new AlertDAO().saveAlertAccept(key);
  }
}
