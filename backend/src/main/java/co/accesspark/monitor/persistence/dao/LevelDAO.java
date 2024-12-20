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
public class LevelDAO {

  public LevelDAO()
  {
  }

  public String queryLevelMonitor(IConnectionName keyName)
          throws JdbcException
  {

    String sql = """
              SELECT TO_JSON((SELECT ARRAY_AGG(info.*)
                              FROM ((
                                  SELECT CASE WHEN c.condition = 'DEPOSIT' THEN 'Cofre Billete' END    AS driver,
                                         CASE WHEN c.condition = 'DEPOSIT' THEN 'Capacidad Dispo.' END AS type,
                                         CASE WHEN c.condition = 'DEPOSIT' THEN 'Billete' END          AS amount,
                                         (100 - ((SUM(c.quantity) * 100) / 600))::INT                  AS percentage,
                                         (SELECT TO_JSON((SELECT ARRAY_AGG(info.*)
                                                          FROM (SELECT ch.amount        AS value,
                                                                       SUM(ch.quantity) AS quantity,
                                                                       600              AS maximum
                                                                FROM pay.cash ch
                                                                WHERE ch.condition = c.condition
                                                                  AND ch.amount >= 50
                                                                GROUP BY ch.amount
                                                                ORDER BY ch.amount) info)))            AS cash_list
                                  FROM pay.cash c
                                  WHERE c.status = 'A'
                                    AND c.amount >= 50
                                    AND c."condition" IN ('DEPOSIT')
                                  GROUP BY c."condition"
                                  UNION ALL
                                  SELECT CASE WHEN c.condition = 'COIN_VALIDATOR' THEN 'Cofre Moneda' END     AS driver,
                                         CASE WHEN c.condition = 'COIN_VALIDATOR' THEN 'Capacidad Dispo.' END AS type,
                                         CASE WHEN c.condition = 'COIN_VALIDATOR' THEN 'Moneda' END           AS amount,
                                         (100 - ((SUM(c.quantity) * 100) / 1500))::INT                        AS percentage,
                                         (SELECT TO_JSON((SELECT ARRAY_AGG(info.*)
                                                          FROM (SELECT sh.amount AS     value,
                                                                       SUM(sh.quantity) quantity,
                                                                       1500      AS     maximum
                                                                FROM pay.cash sh
                                                                WHERE c.condition = sh.condition
                                                                  AND sh.amount >= 50
                                                                GROUP BY sh.amount
                                                                ORDER BY sh.amount) info)))                   AS cash_list
                                  FROM pay.cash c
                                  WHERE c.status = 'A'
                                    AND c.amount >= 50
                                    AND c.condition IN ('COIN_VALIDATOR')
                                  GROUP BY c.condition
                                  UNION ALL
                                  SELECT CASE WHEN c.condition = 'RECYCLE' THEN 'Cassette' ELSE c.condition END AS driver,
                                         CASE WHEN c.kind = 'B' THEN 'Billete' ELSE 'Moneda' END                AS type,
                                         CONCAT('$', c.amount::integer)                                         AS amount,
                                         CASE
                                             WHEN ((SUM(c.quantity) * 100) / dc.maximun) IS NULL
                                                 THEN ((SUM(c.quantity) * 100) / c.maximum)::INT
                                             ELSE ((SUM(c.quantity) * 100) / dc.maximun)::INT END               AS percentage,
                                         (SELECT TO_JSON((SELECT ARRAY_AGG(info.*)
                                                          FROM (SELECT ch.amount                           AS value,
                                                                       SUM(ch.quantity)                    AS quantity,
                                                                       COALESCE(ch.maximum, dc.maximun, 0) AS maximum
                                                                FROM pay.cash ch
                                                                WHERE ch.amount = c.amount
                                                                  AND ch.condition = c.condition
                                                                  AND ch.status = 'A'
                                                                GROUP BY ch.amount, ch.maximum) info)))         AS cash_list
                                  FROM pay.cash c
                                           LEFT JOIN (SELECT dc.value::integer AS maximun, dc.device
                                                      FROM core.device_config dc
                                                      WHERE property = 'maximum') AS dc
                                                     ON c.device = dc.device
                                  WHERE c.status = 'A'
                                    AND c."condition" IN ('HOPPER', 'RECYCLE')
                                  GROUP BY c."condition", c.amount, c.kind, dc.maximun, c.maximum)
                                  ORDER BY percentage) info)) AS dato;
                 """;
    return DatabaseManager.executeSingle(keyName, sql, null, (retrieve) -> {
      return retrieve.getObject("dato", String.class);
    });
  }

}
