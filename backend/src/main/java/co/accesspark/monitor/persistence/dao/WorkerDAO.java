/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dao;

import co.accesspark.monitor.business.constant.EGenericQuery;
import co.accesspark.monitor.persistence.entity.Worker;
import co.accesspark.monitor.persistence.entity.Account;
import com.spiwer.rosilla.database.DatabaseManager;
import com.spiwer.rosilla.dto.Param;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.standard.template.IConnectionName;
import java.util.List;

/**
 *
 * @author AccessSoftware2
 */
public class WorkerDAO {

  public static List<Worker> queryUser(IConnectionName connection)
          throws JdbcException
  {
    String sql = """
                 SELECT *
                 FROM core.worker w
                          INNER JOIN core.account a ON w.account = a.code
                 WHERE w.status IN (:statusactive, :statussuspend)
                   AND NOT w.title ILIKE '%SYSTEM%'
                 ORDER BY w.title;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("statusactive", EGenericQuery.Status.ACTIVE)
            .add("statussuspend", EGenericQuery.Status.SUSPEND);
    return DatabaseManager.executeList(connection, sql, params, (retrieve) -> {
      Account account = Account.fill(retrieve.setAlias("account1"));
      Worker worker = Worker.fill(retrieve.setAlias("worker1"));
      return worker.setAccount(account);
    });
  }

  public static List<Account> queryUserProfile(IConnectionName connection)
          throws JdbcException
  {
    String sql = """
                  SELECT *
                  FROM core.account a
                  WHERE a.status = :statusactive
                  ORDER BY a.title 
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("statusactive", EGenericQuery.Status.ACTIVE);
    return DatabaseManager.executeList(connection, sql, params, (retrieve) -> {
      return Account.fill(retrieve);
    });
  }

  public static Worker queryUserExist(IConnectionName connection, Worker workerIn)
          throws JdbcException
  {
    String sql = """
                 SELECT *
                 FROM core.worker w
                 WHERE w.card_id = :card_id
                   AND w.status = :statusactive;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("card_id", workerIn.getCard_id())
            .add("statusactive", EGenericQuery.Status.ACTIVE);
    return DatabaseManager.executeSingle(connection, sql, params, (retrieve) -> {
      return Worker.fill(retrieve);
    });
  }

  public static void updateUser(IConnectionName connectionName, Worker worker)
          throws JdbcException
  {
    Param<String, Object> params = DatabaseManager.params()
            .add(Worker.COL_CODE, worker.getCode())
            .add(Worker.COL_IDENTIFICATION, worker.getIdentification())
            .add(Worker.COL_TITLE, worker.getTitle())
            .add(Worker.COL_ACCOUNT, worker.getAccount().getCode())
            .add(Worker.COL_STATUS, worker.getStatus())
            .add(Worker.COL_SECRET, worker.getSecret())
            .add(worker.COL_CARD_ID, worker.getCard_id())
            .add(worker.COL_EMAIL, worker.getEmail());
    String sql = """
                 UPDATE core.worker
                 SET identification = :identification,
                     title          =:title,
                     status         =:status,
                     account        = :account,
                     email          = :email,
                     secret         =:secret,
                     card_id        = :card_id
                 WHERE code = :code;
                 """;
            DatabaseManager.executeUpdate(connectionName, sql, params);
  }

  public static void deleteUser(IConnectionName connectionName, String card_id)
          throws JdbcException
  {

    Param<String, Object> params = DatabaseManager.params()
            .add(Worker.COL_CARD_ID, card_id)
            .add(Worker.COL_STATUS, EGenericQuery.Status.DELETE);
    String sql = """
                 UPDATE core.worker
                 SET status = :status
                 WHERE card_id = :card_id;
                 """;
    DatabaseManager.executeUpdate(connectionName, sql, params);

  }

}
