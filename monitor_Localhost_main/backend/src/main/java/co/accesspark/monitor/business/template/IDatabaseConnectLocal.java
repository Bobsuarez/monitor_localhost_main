/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.template;

import com.spiwer.standard.exception.AppException;
import java.sql.Connection;

/**
 *
 * @author AccessSoftware2
 */
public interface IDatabaseConnectLocal
{

  public Connection connect(String ip, String port)
          throws AppException;
}
