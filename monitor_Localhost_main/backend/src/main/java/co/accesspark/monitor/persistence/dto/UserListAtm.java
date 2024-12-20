/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto;

import co.accesspark.monitor.persistence.entity.Worker;
import java.util.List;

/**
 *
 * @author AccessSoftware2
 */
public class UserListAtm
{

  private String nameAtm;
  private List<Worker> listUserAtm;

  public String getNameAtm()
  {
    return nameAtm;
  }

  public UserListAtm setNameAtm(String nameAtm)
  {
    this.nameAtm = nameAtm;
    return this;
  }

  public List<Worker> getListUserAtm()
  {
    return listUserAtm;
  }

  public UserListAtm setListUserAtm(List<Worker> listUserAtm)
  {
    this.listUserAtm = listUserAtm;
    return this;
  }

}
