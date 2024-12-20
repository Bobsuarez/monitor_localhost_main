/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports;

/**
 *
 * @author Bobsuarez
 */
public class RefundsDay {

    private String instant;
    private String prefix;
    private Integer bill_number;
    private Double receipt;
    private Double total;
    private Double exchange;
    private Double change;
    private Double refunds;

    public RefundsDay() {
    }

    public RefundsDay(String instant, String prefix, Integer bill_number, Double receipt, Double total, Double exchange, Double change, Double refunds) {
        this.instant = instant;
        this.prefix = prefix;
        this.bill_number = bill_number;
        this.receipt = receipt;
        this.total = total;
        this.exchange = exchange;
        this.change = change;
        this.refunds = refunds;
    }

    public String getInstant() {
        return instant;
    }

    public RefundsDay setInstant(String instant) {
        this.instant = instant;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public RefundsDay setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Integer getBill_number() {
        return bill_number;
    }

    public RefundsDay setBill_number(Integer bill_number) {
        this.bill_number = bill_number;
        return this;
    }

    public Double getReceipt() {
        return receipt;
    }

    public RefundsDay setReceipt(Double receipt) {
        this.receipt = receipt;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public RefundsDay setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Double getExchange() {
        return exchange;
    }

    public RefundsDay setExchange(Double exchange) {
        this.exchange = exchange;
        return this;
    }

    public Double getChange() {
        return change;
    }

    public RefundsDay setChange(Double change) {
        this.change = change;
        return this;
    }

    public Double getRefunds() {
        return refunds;
    }

    public RefundsDay setRefunds(Double refunds) {
        this.refunds = refunds;
        return this;
    }

}
