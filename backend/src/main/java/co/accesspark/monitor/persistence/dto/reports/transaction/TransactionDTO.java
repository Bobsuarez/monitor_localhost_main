/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.transaction;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Bobsuarez
 */
public class TransactionDTO {

    private LocalDateTime instant;
    private String id;
    private String status;
    private String prefix;
    private Double bill_number;
    private Double total;
    private Double refunds;
    private Double payment;
    private Double change;
    private Double total_entered;
    private List<MoneyType> money;

    public TransactionDTO() {
    }

    public TransactionDTO(LocalDateTime instant, String id, String status, String prefix, Double bill_number, Double total, Double refunds, Double payment, Double change, Double total_entered, List<MoneyType> money) {
        this.instant = instant;
        this.id = id;
        this.status = status;
        this.prefix = prefix;
        this.bill_number = bill_number;
        this.total = total;
        this.refunds = refunds;
        this.payment = payment;
        this.change = change;
        this.total_entered = total_entered;
        this.money = money;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public TransactionDTO setInstant(LocalDateTime instant) {
        this.instant = instant;
        return this;
    }

    public String getId() {
        return id;
    }

    public TransactionDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TransactionDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public TransactionDTO setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Double getBill_number() {
        return bill_number;
    }

    public TransactionDTO setBill_number(Double bill_number) {
        this.bill_number = bill_number;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public TransactionDTO setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Double getRefunds() {
        return refunds;
    }

    public TransactionDTO setRefunds(Double refunds) {
        this.refunds = refunds;
        return this;
    }

    public Double getPayment() {
        return payment;
    }

    public TransactionDTO setPayment(Double payment) {
        this.payment = payment;
        return this;
    }

    public Double getChange() {
        return change;
    }

    public TransactionDTO setChange(Double change) {
        this.change = change;
        return this;
    }

    public Double getTotal_entered() {
        return total_entered;
    }

    public TransactionDTO setTotal_entered(Double total_entered) {
        this.total_entered = total_entered;
        return this;
    }

    public List<MoneyType> getMoney() {
        return money;
    }

    public TransactionDTO setMoney(List<MoneyType> money) {
        this.money = money;
        return this;
    }

}
