/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.level;

/**
 *
 * @author bobsuarez
 */
public class CashLevel {

  private Double value;
  private Integer quantity;
  private Integer maximum;

  public CashLevel()
  {
  }

  public CashLevel(Double value, Integer quantity, Integer maximum)
  {
    this.value = value;
    this.quantity = quantity;
    this.maximum = maximum;
  }

  public Double getValue()
  {
    return value;
  }

  public CashLevel setValue(Double value)
  {
    this.value = value;
    return this;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public CashLevel setQuantity(Integer quantity)
  {
    this.quantity = quantity;
    return this;
  }

  public Integer getMaximum()
  {
    return maximum;
  }

  public CashLevel setMaximum(Integer maximum)
  {
    this.maximum = maximum;
    return this;
  }

}
