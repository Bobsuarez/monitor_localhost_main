/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto;

/**
 *
 * @author AccessSoftware2
 */
public class AlertError
{

  private String detailError;
  private String payError;
  private String hopperError;
  private String LowLevelMoneda;
  private String nameConnection;

  public String getDetailError()
  {
    return detailError;
  }

  public AlertError setDetailError(String detailError)
  {
    this.detailError = detailError;
    return this;
  }

  public String getPayError()
  {
    return payError;
  }

  public AlertError setPayError(String payError)
  {
    this.payError = payError;
    return this;
  }

  public String getHopperError()
  {
    return hopperError;
  }

  public AlertError setHopperError(String hopperError)
  {
    this.hopperError = hopperError;
    return this;
  }

  public String getNameConnection()
  {
    return nameConnection;
  }

  public AlertError setNameConnection(String nameConnection)
  {
    this.nameConnection = nameConnection;
    return this;
  }

  public String getLowLevelMoneda()
  {
    return LowLevelMoneda;
  }

  public AlertError setLowLevelMoneda(String LowLevelMoneda)
  {
    this.LowLevelMoneda = LowLevelMoneda;
    return this;
  }

}
