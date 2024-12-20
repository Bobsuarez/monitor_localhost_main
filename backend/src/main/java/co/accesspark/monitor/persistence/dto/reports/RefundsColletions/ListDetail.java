/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.RefundsColletions;

/**
 *
 * @author Bobsuarez
 */
public class ListDetail {

    private String instant;
    private Double refunds;
    private Double collection;

    public ListDetail() {
    }

    public ListDetail(String instant, Double refunds, Double collection) {
        this.instant = instant;
        this.refunds = refunds;
        this.collection = collection;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }

    public Double getRefunds() {
        return refunds;
    }

    public ListDetail setRefunds(Double refunds) {
        this.refunds = refunds;
        return this;
    }

    public Double getCollection() {
        return collection;
    }

    public ListDetail setCollection(Double collection) {
        this.collection = collection;
        return this;
    }

}
