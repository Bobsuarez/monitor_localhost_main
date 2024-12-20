/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.FillEmpty;

import java.util.Date;

/**
 *
 * @author Bobsuarez
 */
public class ListDetail {

    private String kind;
    private String instant;
    private Integer amount;
    private Integer quantity;
    private Double total_amount;

    public ListDetail() {
    }

    public ListDetail(String kind, String instant, Integer amount, Integer quantity, Double total_amount) {
        this.kind = kind;
        this.instant = instant;
        this.amount = amount;
        this.quantity = quantity;
        this.total_amount = total_amount;
    }

   

    public String getKind() {
        return kind;
    }

    public ListDetail setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getInstant() {
        return instant;
    }

    public ListDetail setInstant(String instant) {
        this.instant = instant;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public ListDetail setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ListDetail setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public ListDetail setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
        return this;
    }

}
