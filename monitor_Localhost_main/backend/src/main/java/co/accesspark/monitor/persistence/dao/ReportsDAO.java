/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dao;

import com.spiwer.rosilla.database.DatabaseManager;
import static com.spiwer.rosilla.database.DatabaseManager.params;
import com.spiwer.rosilla.dto.Param;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.standard.template.IConnectionName;
import java.time.LocalDateTime;

/**
 *
 * @author Bobsuarez
 */
public class ReportsDAO {

    public String searchTransaction(IConnectionName nameConnection, LocalDateTime initialDate, LocalDateTime finalDate)
            throws JdbcException {

        String sql = """
                     SELECT to_json((SELECT array_agg(info.*)
                                     FROM (SELECT mov.instant,
                                                  mov.id,
                                                  CASE
                                                      WHEN mov.status = 'C' THEN 'Cancelado'
                                                      WHEN mov.status = 'E' THEN 'Error'
                                                      WHEN mov.status = 'F' THEN 'Finalizado'
                                                      WHEN mov.status = 'I' THEN 'Incompleto'
                                                      WHEN mov.status = 'S' THEN 'Iniciado' END      AS status,
                                                  inv.prefix,
                                                  inv.bill_number,
                                                  inv.total,
                                                  mov.refunds,
                                                  mov.payment,
                                                  mov.change,
                                                  mov.total_entered,
                                                  to_json((SELECT array_agg(info.*)
                                                           FROM (SELECT CASE WHEN d.kind = 'I' THEN 'Ingreso' ELSE 'Devuelto' END  AS type_movement,
                                                                        d.quantity,
                                                                        CASE
                                                                            WHEN c.kind = 'B' AND d.quantity > 1 THEN 'Billetes'
                                                                            WHEN c.kind = 'B' AND d.quantity = 1 THEN 'Billete'
                                                                            WHEN c.kind = 'C' AND d.quantity > 1 THEN 'Monedas'
                                                                            WHEN c.kind = 'C' AND d.quantity = 1
                                                                                THEN 'Moneda' END                                  AS type_money,
                                                                        c.amount,
                                                                        d.message,
                                                                        d.description
                                                                 FROM pay.detail d
                                                                          INNER JOIN pay.cash c ON d.cash = c.code
                                                                 WHERE d.movement = mov.code
                                                                 ORDER BY type_movement DESC) info)) AS money
                                           FROM pay.movement mov
                                                    LEFT JOIN billing.invoice inv ON mov.invoice = inv.code
                                           WHERE mov.kind = 'P'
                                             AND mov.instant BETWEEN :initialDate::TIMESTAMP AND :finalDate::TIMESTAMP
                                           ORDER BY mov.instant) info)) AS data;
                     """;
        Param<String, Object> params = params()
                .add("initialDate", initialDate)
                .add("finalDate", finalDate);
        return DatabaseManager.executeSingle(nameConnection, sql, params, (retrieve) -> {
            return retrieve.getObject("data", String.class);
        });

    }

    public String searchFillEmpty(IConnectionName nameConnection, LocalDateTime initialDate, LocalDateTime finalDate)
            throws JdbcException {

        String sql = """
                     SELECT to_json((select array_agg(query.*)
                                     from (select info.kind as type,
                                                  sum(info.total_amount) totalFinal,
                                                  array_agg(info.*) as   list_detail
                                           from (select mv.kind,
                                                        mv.instant::date,
                                                        c.amount::int,
                                                        sum(dt.quantity)            as quantity,
                                                        sum(dt.quantity) * c.amount as total_amount
                                                 from pay.movement mv
                                                          inner join pay.detail dt on mv.code = dt.movement
                                                          inner join pay.cash c on c.code = dt.cash
                                                 where mv.kind in ('E', 'F')
                                                   and mv.instant BETWEEN :initialDate::TIMESTAMP AND :finalDate::TIMESTAMP
                                                   and dt.quantity > 0
                                                 group by mv.kind, mv.instant::date, c.amount
                                                 order by mv.instant::date,c.amount) info
                                           group by info.kind) query)) as data;
                     """;
        Param<String, Object> params = params()
                .add("initialDate", initialDate)
                .add("finalDate", finalDate);
        return DatabaseManager.executeSingle(nameConnection, sql, params, (retrieve) -> {
            return retrieve.getObject("data", String.class);
        });

    }

    public String searchRefundsColletions(IConnectionName nameConnection, LocalDateTime initialDate, LocalDateTime finalDate)
            throws JdbcException {

        String sql = """
                     SELECT TO_JSON((SELECT ARRAY_AGG(data.*)
                                     FROM (
                                              SELECT SUM(info.refunds)    AS total_refunds,
                                                     SUM(info.collection) AS total_colletion,
                                                     ARRAY_AGG(info.*)       list_detail
                                              FROM (
                                                       SELECT mv.instant::date, SUM(mv.refunds) AS refunds, SUM(inv.total) AS collection
                                                       FROM pay.movement mv
                                                                INNER JOIN billing.invoice inv ON mv.invoice = inv.code
                                                       WHERE mv.instant BETWEEN :initialDate::TIMESTAMP AND :finalDate::TIMESTAMP
                                                       GROUP BY mv.instant::date
                                                       ORDER BY mv.instant::date) info) data)) AS data;
                     """;
        Param<String, Object> params = params()
                .add("initialDate", initialDate)
                .add("finalDate", finalDate);
        return DatabaseManager.executeSingle(nameConnection, sql, params, (retrieve) -> {
            return retrieve.getObject("data", String.class);
        });

    }

    public String searchRefundsDay(IConnectionName nameConnection, LocalDateTime initialDate, LocalDateTime finalDate)
            throws JdbcException {

        String sql = """
                    select to_json((
                        Select array_agg(info.*)
                        from (
                                 select to_char(mv.instant, 'YYYY-MM-DD HH24:MI') as instant,
                                        inv.prefix,
                                        inv.bill_number,
                                        inv.receipt,
                                        inv.total,
                                        inv.exchange,
                                        inv.exchange - mv.refunds                 as change,
                                        mv.refunds
                                 from pay.movement mv
                                          inner join billing.invoice inv on mv.invoice = inv.code
                                 where mv.instant BETWEEN :initialDate::TIMESTAMP AND :finalDate::TIMESTAMP
                                   and mv.refunds > 0) info)) as data;
                     """;
        Param<String, Object> params = params()
                .add("initialDate", initialDate)
                .add("finalDate", finalDate);
        return DatabaseManager.executeSingle(nameConnection, sql, params, (retrieve) -> {
            return retrieve.getObject("data", String.class);
        });

    }

}
