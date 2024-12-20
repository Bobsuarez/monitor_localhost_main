/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.database.lasting;

import com.spiwer.standard.template.IConnectionName;

/**
 *
 * @author AccessparkSoft
 */
public enum EConnectionName implements IConnectionName
{
  DEFAULT("default"),
  FIREBIRD("fireBird");

  private final String name;

  private EConnectionName(String name)
  {
    this.name = name;
  }

  @Override
  public String getName()
  {
    return name;
  }
;
}
