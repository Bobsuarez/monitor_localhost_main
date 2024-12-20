/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.FillEmpty;

import java.util.List;

/**
 *
 * @author Bobsuarez
 */
public class FillEmpty {

    private String type;
    private Double totalfinal;
    private List<ListDetail> list_detail;

    public FillEmpty() {
    }

    public FillEmpty(String type, Double totalfinal, List<ListDetail> list_detail) {
        this.type = type;
        this.totalfinal = totalfinal;
        this.list_detail = list_detail;
    }

    public String getType() {
        return type;
    }

    public FillEmpty setType(String type) {
        this.type = type;
        return this;
    }

    public Double getTotalfinal() {
        return totalfinal;
    }

    public FillEmpty setTotalfinal(Double totalfinal) {
        this.totalfinal = totalfinal;
        return this;
    }

    public List<ListDetail> getList_detail() {
        return list_detail;
    }

    public FillEmpty setList_detail(List<ListDetail> list_detail) {
        this.list_detail = list_detail;
        return this;
    }

}
