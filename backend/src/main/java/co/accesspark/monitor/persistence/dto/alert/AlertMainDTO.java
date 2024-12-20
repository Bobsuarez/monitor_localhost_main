/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.alert;

import java.util.List;

/**
 *
 * @author Bobsuarez
 */
public class AlertMainDTO {

    private List<DescriptionAlert> pending_error;
    private List<DescriptionAlert> detail_error;
    private List<DescriptionAlert> pay_error;
    private List<DescriptionAlert> hopper_error;
    private List<DescriptionAlert> money_error;

    public AlertMainDTO(List<DescriptionAlert> pending_error, List<DescriptionAlert> detail_error, List<DescriptionAlert> pay_error, List<DescriptionAlert> hopper_error, List<DescriptionAlert> money_error) {
        this.pending_error = pending_error;
        this.detail_error = detail_error;
        this.pay_error = pay_error;
        this.hopper_error = hopper_error;
        this.money_error = money_error;
    }

    public List<DescriptionAlert> getPending_error() {
        return pending_error;
    }

    public AlertMainDTO setPending_error(List<DescriptionAlert> pending_error) {
        this.pending_error = pending_error;
        return this;
    }

    public List<DescriptionAlert> getDetail_error() {
        return detail_error;
    }

    public AlertMainDTO setDetail_error(List<DescriptionAlert> detail_error) {
        this.detail_error = detail_error;
        return this;
    }

    public List<DescriptionAlert> getPay_error() {
        return pay_error;
    }

    public AlertMainDTO setPay_error(List<DescriptionAlert> pay_error) {
        this.pay_error = pay_error;
        return this;
    }

    public List<DescriptionAlert> getHopper_error() {
        return hopper_error;
    }

    public AlertMainDTO setHopper_error(List<DescriptionAlert> hopper_error) {
        this.hopper_error = hopper_error;
        return this;
    }

    public List<DescriptionAlert> getMoney_error() {
        return money_error;
    }

    public AlertMainDTO setMoney_error(List<DescriptionAlert> money_error) {
        this.money_error = money_error;
        return this;
    }

}
