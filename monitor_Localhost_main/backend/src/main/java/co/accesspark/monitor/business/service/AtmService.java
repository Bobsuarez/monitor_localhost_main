/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.service;

import co.accesspark.monitor.business.constant.EMessageApp;
import co.accesspark.monitor.business.database.DatabaseConnection;
import co.accesspark.monitor.business.util.UtilMonitor;
import static co.accesspark.monitor.business.util.UtilMonitor.readFileAsString;
import co.accesspark.monitor.persistence.dao.AtmDAO;
import co.accesspark.monitor.persistence.dto.AtmConnectionDTO;
import com.google.gson.Gson;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.template.IConnectionName;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AccessSoftware2
 */
@Service
public class AtmService {

  @Autowired
  private UtilMonitor utilMonitor;

  private static final Logger LOG = Logger.getLogger(DatabaseConnection.class.
          getName());

  public List<String> searchAtmActive()
          throws AppException
  {
    return utilMonitor.searchListAtmActive();
  }

  public String testConnection(AtmConnectionDTO atmDTO)

  {
    String resultName = "No Connection";
    var name = (IConnectionName) () -> atmDTO.getName();
    LOG.log(Level.SEVERE,
            new Gson().toJson(atmDTO));
    try {
      RequestUtil.CONNECTIONS_LIST.put(name,
              new DatabaseConnection(atmDTO.getIp(),
                      atmDTO.getPort(),
                      atmDTO.getBdname(),
                      atmDTO.getUser(),
                      atmDTO.getPassword()));
      boolean status = utilMonitor.verifyConnection(name);
      if (status) {
        resultName = atmDTO.getName();
        return resultName;
      }
    } catch (Exception e) {
      RequestUtil.CONNECTIONS_LIST.remove(name);
      return resultName;
    }
    return resultName;
  }

  public String saveConnection(AtmConnectionDTO atmDTO)
          throws AppException
  {
    LOG.log(Level.SEVERE,
            "ingreso a guardar property");
    List<AtmConnectionDTO> connectionsList = new ArrayList<>();
    String propertyConnection = null;
    try {
      propertyConnection = readFileAsString("configuration.json");
    } catch (Exception ex) {
      Logger.getLogger(AtmService.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      LOG.log(Level.SEVERE,
              propertyConnection);
      if (propertyConnection == null || propertyConnection.isEmpty()) {
        connectionsList.add(atmDTO);
        fileWrite(connectionsList);
        throw new AppException(EMessageApp.OK);
      }
      connectionsList
              = utilMonitor.jsonArrayToList(propertyConnection,
                      AtmConnectionDTO.class);
      List<AtmConnectionDTO> listFilter = connectionsList
              .stream()
              .filter(listanew -> !listanew.getName().equals(atmDTO.getName())).
              collect(Collectors.toList());
      listFilter.forEach(list -> LOG.log(Level.SEVERE,
              "lista despues de filtrar : " + list.getName()));
      listFilter.add(atmDTO);
      listFilter.forEach(list -> LOG.log(Level.SEVERE,
              "lista final : " + list.getName()));
      fileWrite(listFilter);
      throw new AppException(EMessageApp.ADD_PROPERTY);
    }
  }

  public void fileWrite(List<AtmConnectionDTO> listaNew)
  {

    String json = null;
    try {
      json = utilMonitor.ListArrayToJson(listaNew, null);
    } catch (AppException ex) {
      Logger.getLogger(AtmService.class.getName()).log(Level.SEVERE, null, ex);
    }

    LOG.log(Level.SEVERE,
            "Ingresa a escribir el archivo property");
    LOG.log(Level.SEVERE,
            " el json : " + json);
    utilMonitor.writeFile(json, new File("configuration.json"));
    LOG.log(Level.SEVERE,
            System.getProperty("configuration"));
  }

  public String getNameStation(String name)
          throws AppException
  {
    IConnectionName keyConnection = utilMonitor.testfindConnection(name);
    String nameStation = AtmDAO.getNameStation(keyConnection);
    if (nameStation == null) {
      throw new AppException(EMessageApp.ERROR_FOUND);
    }
    return nameStation;
  }

  public void disableATM(List<AtmConnectionDTO> atmDTO)
          throws AppException
  {

    String propertyConnection = null;
    try {
      propertyConnection = readFileAsString("configuration.json");
    } catch (Exception ex) {
      Logger.getLogger(AtmService.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (propertyConnection == null) {
        throw new AppException(EMessageApp.ERROR_FOUND);
      }
      List<AtmConnectionDTO> connectionsList = utilMonitor.jsonArrayToList(
              propertyConnection,
              AtmConnectionDTO.class);

      List<AtmConnectionDTO> listFinal = Stream
              .concat(atmDTO.stream(),
                      connectionsList.stream())
              .distinct()
              .collect(Collectors.toList());
      LOG.log(Level.SEVERE,
              "disable atm");
      fileWrite(listFinal);

    }
  }

}
