/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.service;

import co.accesspark.monitor.business.constant.EMessageApp;
import co.accesspark.monitor.business.util.UtilMonitor;
import co.accesspark.monitor.persistence.dao.LevelDAO;
import co.accesspark.monitor.persistence.dto.reports.level.LevelDTO;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.template.IConnectionName;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AccessSoftware2
 */
@Service
public class MonitorService {

  @Autowired
  private UtilMonitor utilMonitor;

  public List<LevelDTO> levelMonitor(String nameLocation)
          throws JdbcException, AppException
  {
    String nameConnection = utilMonitor.connectionToLocation(nameLocation);
    IConnectionName keyConnection = utilMonitor.testfindConnection(nameConnection);
    String result = new LevelDAO().queryLevelMonitor(keyConnection);
    if (result == null) {
      throw new AppException(EMessageApp.ERROR_FOUND_LEVEL, nameLocation);
    }
    List<LevelDTO> resulLevelDTO = utilMonitor.jsonArrayToList(result, LevelDTO.class);
    return resulLevelDTO;
  }

}
