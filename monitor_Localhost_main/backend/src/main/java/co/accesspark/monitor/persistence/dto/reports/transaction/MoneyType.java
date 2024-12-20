/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.transaction;

/**
 *
 * @author Bobsuarez
 */
public class MoneyType {

    private String type_movement;
    private Integer quantity;
    private String type_money;
    private Double amount;
    private String message;
    private String description;

    public MoneyType() {
    }
    
    public MoneyType(String type_movement, Integer quantity, String type_money, Double amount, String message, String description) {
        this.type_movement = type_movement;
        this.quantity = quantity;
        this.type_money = type_money;
        this.amount = amount;
        this.message = message;
        this.description = description;
    }

    public String getType_movement() {
        return type_movement;
    }

    public MoneyType setType_movement(String type_movement) {
        this.type_movement = type_movement;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MoneyType setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType_money() {
        return type_money;
    }

    public MoneyType setType_money(String type_money) {
        this.type_money = type_money;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public MoneyType setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MoneyType setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MoneyType setDescription(String description) {
        this.description = description;
        return this;
    }

}
