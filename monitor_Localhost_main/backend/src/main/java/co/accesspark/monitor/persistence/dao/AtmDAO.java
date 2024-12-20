/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dao;

import com.spiwer.rosilla.database.DatabaseManager;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.standard.template.IConnectionName;

/**
 *
 * @author AccessSoftware2
 */
public class AtmDAO
{

  public AtmDAO()
  {

  }

  public static String getNameStation(IConnectionName connectionName)
          throws JdbcException
  {
    String sql = """
                 SELECT st.equipment as nameStation FROM core.station st ;
                 """;
    return DatabaseManager.executeSingle(connectionName, sql, null, (retrieve) -> {
      return retrieve.getObject("nameStation", String.class);
    });
  }
}
