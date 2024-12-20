/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dao;

import co.accesspark.monitor.persistence.entity.Account;
import com.spiwer.rosilla.database.DatabaseManager;
import com.spiwer.rosilla.dto.Param;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.standard.template.IConnectionName;
import co.accesspark.monitor.business.constant.EGenericQuery;

/**
 *
 * @author AccessSoftware2
 */
public class UserDAO
{

  public UserDAO()
  {
  }

  public Account searchAccount(IConnectionName nameConnection, Integer codeTypeUser)
          throws JdbcException
  {
    String sql = """
                 SELECT *
                 FROM core.account a
                 WHERE a.code = :code
                   AND a.status = :status 
                   limit 1;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("status", EGenericQuery.Status.ACTIVE)
            .add("code", codeTypeUser);
    return DatabaseManager.executeSingle(nameConnection, sql, params, (rtrv) -> {
      return Account.fill(rtrv);
    });

  }
}
