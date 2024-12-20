/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.RefundsColletions;

import java.util.List;

/**
 *
 * @author Bobsuarez
 */
public class RefundsColletions {

    private Double total_refunds;
    private Double total_colletion;
    private List<ListDetail> list_detail;

    public RefundsColletions() {
    }

    public RefundsColletions(Double total_refunds, Double total_colletion, List<ListDetail> list_detail) {
        this.total_refunds = total_refunds;
        this.total_colletion = total_colletion;
        this.list_detail = list_detail;
    }

    public Double getTotal_refunds() {
        return total_refunds;
    }

    public RefundsColletions setTotal_refunds(Double total_refunds) {
        this.total_refunds = total_refunds;
        return this;
    }

    public Double getTotal_colletion() {
        return total_colletion;
    }

    public RefundsColletions setTotal_colletion(Double total_colletion) {
        this.total_colletion = total_colletion;
        return this;
    }

    public List<ListDetail> getList_detail() {
        return list_detail;
    }

    public RefundsColletions setList_detail(List<ListDetail> list_detail) {
        this.list_detail = list_detail;
        return this;
    }

}
