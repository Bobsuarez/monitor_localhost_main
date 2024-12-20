/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.reports.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author AccessSoftware2
 */
public class LevelDTO {

  private String driver;
  private String type;
  private String amount;
  private Integer percentage;
  @JsonProperty("cash_list")
  private List<CashLevel> cashList;

  public LevelDTO()
  {
  }
   

  public LevelDTO(String driver, String type, String amount, Integer percentage, List<CashLevel> cashList)
  {
    this.driver = driver;
    this.type = type;
    this.amount = amount;
    this.percentage = percentage;
    this.cashList = cashList;
  }

  public List<CashLevel> getCashList()
  {
    return cashList;
  }

  public LevelDTO setCashList(List<CashLevel> cashList)
  {
    this.cashList = cashList;
    return this;
  }

  public String getDriver()
  {
    return driver;
  }

  public LevelDTO setDriver(String driver)
  {
    this.driver = driver;
    return this;
  }

  public String getType()
  {
    return type;
  }

  public LevelDTO setType(String type)
  {
    this.type = type;
    return this;
  }

  public String getAmount()
  {
    return amount;
  }

  public LevelDTO setAmount(String amount)
  {
    this.amount = amount;
    return this;
  }

  public Integer getPercentage()
  {
    return percentage;
  }

  public LevelDTO setPercentage(Integer percentage)
  {
    this.percentage = percentage;
    return this;
  }

}
