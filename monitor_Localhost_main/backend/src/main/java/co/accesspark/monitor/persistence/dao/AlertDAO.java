/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dao;

import co.accesspark.monitor.business.constant.EGenericQuery;
import co.accesspark.monitor.persistence.entity.Alert;
import com.spiwer.rosilla.database.DatabaseManager;
import com.spiwer.rosilla.dto.Param;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.template.IConnectionName;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author AccessSoftware2
 */
public class AlertDAO {

  public AlertDAO()
  {
  }

  public List<String> errorDetail(IConnectionName connectionName, LocalDateTime dateSearch)
          throws JdbcException
  {
    String sql = """
                 SELECT dt.description AS error
                 FROM pay.detail dt
                 WHERE dt.instant >= :datesearch::TIMESTAMP
                   AND dt.status = :statuserror
                   AND dt.description IS NOT NULL
                 ORDER BY dt.code DESC;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("datesearch", dateSearch)
            .add("statuserror", EGenericQuery.level.ERROR);
    return DatabaseManager.executeList(connectionName, sql, params, (retrieve) -> {
      return retrieve.getObject("error", String.class);
    });
  }

  public List<String> errorPay(IConnectionName connectionName, LocalDateTime dateSearch)
          throws JdbcException
  {
    String sql = """
                 SELECT mv.description AS error
                 FROM pay.movement mv
                 WHERE mv.status = :statusincomplete
                   AND mv.instant >= :datesearch::TIMESTAMP
                 ORDER BY mv.code DESC
                 LIMIT 1;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("datesearch", dateSearch)
            .add("statusincomplete", EGenericQuery.Status.INCOMPLETE);
    return DatabaseManager.executeList(connectionName, sql, params, (retrieve) -> {
      return retrieve.getObject("error", String.class);
    });
  }

  public List<String> errorHopper(IConnectionName connectionName)
          throws JdbcException
  {
    String sql = """
                 SELECT CONCAT(cs.description, ': Hopper ', amount::Integer) AS error
                 FROM pay.cash cs
                 WHERE cs.status = :statuserror;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("statuserror", EGenericQuery.level.ERROR);
    return DatabaseManager.executeList(connectionName, sql, params, (retrieve) -> {
      return retrieve.getObject("error", String.class);
    });
  }

  public List<String> errorLevelCoin(IConnectionName connectionName)
          throws JdbcException
  {
    String sql = """
                 SELECT CONCAT('Error Poca Moneda de : ', cs.amount::int) AS error
                 FROM pay.cash cs
                          INNER JOIN core.device_config dc ON cs.device = dc.device
                 WHERE cs.condition ILIKE 'hopper'
                   AND dc.property = 'minimum'
                   AND cs.quantity <= dc.value::int
                   And cs.status = 'A';
                 """;
    return DatabaseManager.executeList(connectionName, sql, (retrieve) -> {
      return retrieve.getObject("error", String.class);
    });
  }

  public List<String> errorLevelBill(IConnectionName connectionName)
          throws JdbcException
  {
    String sql = """
                 SELECT CONCAT('Error Poco Billete Cassette de : ', cs.amount::int) AS error
                   FROM pay.cash cs
                   WHERE cs.condition ILIKE 'RECYCLE'
                     AND cs.status = 'A'
                     AND cs.quantity <= cs.minimum;
                 """;
    return DatabaseManager.executeList(connectionName, sql, (retrieve) -> {
      return retrieve.getObject("error", String.class);
    });
  }

  public Alert errorListSearch(IConnectionName connectionName, String Status)
          throws JdbcException
  {
    String sql = """
                 SELECT ale.code,
                        ale.status,
                        ale.instant,
                        ale.title ->> 'error' AS title
                 FROM core.alert ale
                 WHERE status = :status
                 ORDER BY ale.code DESC
                 LIMIT 1;
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("status", Status);
    return DatabaseManager.executeSingle(connectionName, sql, params, (retrieve) -> {
      return Alert.fill(retrieve);
    });
  }

  public void saveAlertAccept(IConnectionName connectionName)
          throws JdbcException
  {
    String sql = """
                 update core.alert set status = :status
                 """;
    Param<String, Object> params = DatabaseManager.params()
            .add("status", EGenericQuery.Status.ACTIVE);
    DatabaseManager.executeUpdate(connectionName, sql, params);
    RequestUtil.commit();
  }

}
