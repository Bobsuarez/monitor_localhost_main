/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.util;

import co.accesspark.monitor.business.constant.EGenericQuery;
import co.accesspark.monitor.business.constant.EMessageApp;
import co.accesspark.monitor.business.database.lasting.EConnectionName;
import co.accesspark.monitor.persistence.dto.AtmConnectionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spiwer.rosilla.database.DatabaseManager;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.template.IConnectionName;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author AccessSoftware2
 */
@Component
public class UtilMonitor {

  @Autowired
  private ObjectMapper objectMapper;
  private static final Logger LOG = Logger.getLogger(UtilMonitor.class.
          getName());

  public static String readFileAsString(String file) throws Exception
  {
    return new String(Files.readAllBytes(Paths.get(file)));
  }

  public List<AtmConnectionDTO> AtmList()
          throws AppException
  {

    String propertyConnection = null;
    try {
      propertyConnection = readFileAsString("configuration.json");
    } catch (Exception ex) {
      Logger.getLogger(UtilMonitor.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      List<AtmConnectionDTO> listATM;
      LOG.log(Level.SEVERE,
              "el valor de la propiedad es " + propertyConnection + "");
      if (propertyConnection == null || propertyConnection.isEmpty()) {
        throw new AppException(EMessageApp.ERROR_PROPERTY);
      }
      listATM = jsonArrayToList(propertyConnection,
              AtmConnectionDTO.class);
      return listATM;
    }
  }

  public IConnectionName findConnection(String name)
  {
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      String keyName = key.getName();
      if (keyName.equalsIgnoreCase(name)) {
        return key;
      }
    }
    return EConnectionName.DEFAULT;
  }

  public IConnectionName testfindConnection(String name)
          throws AppException
  {
    IConnectionName keyConnection = new UtilMonitor().findConnection(name);
    if (keyConnection == EConnectionName.DEFAULT) {
      throw new AppException(EMessageApp.ERROR_CONECCTION_ROSILLA,
              name);
    }
    return keyConnection;
  }

  /**
   * Converte de Json a List Generic
   *
   * @param <T>
   * @param json
   * @param elementClass
   * @return
   */
  public <T> List<T> jsonArrayToList(String json,
          Class<T> elementClass)
          throws AppException
  {

    try {
      CollectionType listType = objectMapper.getTypeFactory().
              constructCollectionType(List.class,
                      elementClass);
      return objectMapper.readValue(json,
              listType);
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
      throw new AppException(EMessageApp.ERROR_PROPERTY_JSON,
              json);
    }
  }

  public <T> T jsonArrayToList(String json,
          TypeReference<T> elementClass)
          throws AppException
  {
    try {
      return objectMapper.readValue(json,
              elementClass);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new AppException(EMessageApp.ERROR_PROPERTY_JSON,
              json);
    }
  }
  
    public <T> String ListArrayToJson(List<T> listGeneric,
          TypeReference<T> elementClass)
          throws AppException
  {
    try {
      if (listGeneric != null){
      return objectMapper.writeValueAsString(listGeneric);
      }
      return objectMapper.writeValueAsString(elementClass);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new AppException(EMessageApp.ERROR_PROPERTY_JSON);
    }
  }

  /**
   * converte date to String /
   * LocalDatetime
   *
   * @param dateString
   * @return Localdatetime
   */
  public LocalDateTime dateStringToLocalDate(String dateString)
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse(dateString,
            formatter);
    return dateTime;
  }

  public boolean verifyConnection(IConnectionName name)
  {

    try {
      DatabaseManager.executeSingle(name,
              EGenericQuery.testQuery.TEST_QUERY,
              (rtrv)
              -> {
        return rtrv.getObject("current",
                String.class);
      });
      return true;
    } catch (JdbcException ex) {
      return false;
    }
  }

  public String location(String key)
          throws AppException
  {
    List<AtmConnectionDTO> AtmList = AtmList();
    for (AtmConnectionDTO atmConnectionDTO : AtmList) {
      if (atmConnectionDTO.getName().equals(key)) {
        return atmConnectionDTO.getLocation();
      }
    }
    return null;
  }

  public String connectionToLocation(String location)
          throws AppException
  {
    List<AtmConnectionDTO> AtmList = AtmList();
    for (AtmConnectionDTO atmConnectionDTO : AtmList) {
      if (atmConnectionDTO.getLocation().equals(location)) {
        return atmConnectionDTO.getName();
      }
    }
    return null;
  }

  public List<String> searchListAtmActive()
          throws AppException
  {

    List<String> nameList = new ArrayList<>();
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      nameList.add(location(key.getName()));
    }
    return nameList
            .stream()
            .sorted()
            .distinct()
            .collect(Collectors.toList());
  }
  
  public boolean equalLists(List<String> one, List<String> two)
  {
    if (one == null && two == null) {
      return true;
    }

    if ((one == null && two != null)
            || one != null && two == null
            || one.size() != two.size()) {
      return false;
    }

    //to avoid messing the order of the lists we will use a copy
    //as noted in comments by A. R. S.
    one = new ArrayList<String>(one);
    two = new ArrayList<String>(two);

    Collections.sort(one);
    Collections.sort(two);
    return one.equals(two);
  }

  public static String getMD5(String password)
  {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(password.getBytes());
      BigInteger number = new BigInteger(1,
              messageDigest);
      String hashtext = number.toString(16);

      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public void writeFile(String contents, File file)
  {

    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(contents);
      bw.close();
    } catch (IOException ex) {
      Logger.getLogger(UtilMonitor.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        fw.close();
      } catch (IOException ex) {
        Logger.getLogger(UtilMonitor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

}
