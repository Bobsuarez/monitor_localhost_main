/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.service;

import co.accesspark.monitor.business.constant.EMessageApp;
import co.accesspark.monitor.business.util.UtilMonitor;
import co.accesspark.monitor.persistence.dao.UserDAO;
import co.accesspark.monitor.persistence.dao.WorkerDAO;
import co.accesspark.monitor.persistence.dto.UserListAtm;
import com.spiwer.standard.exception.AppException;
import co.accesspark.monitor.persistence.entity.Account;
import co.accesspark.monitor.persistence.entity.Worker;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.template.IConnectionName;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AccessSoftware2
 */
@Service
public class UserServices {

  @Autowired
  private UtilMonitor utilMonitor;

  private static final Logger LOG = Logger.getLogger(UserServices.class.
          getName());

  public List<String> createUser(Worker workerIn)
          throws AppException

  {
    List<String> errorList = new ArrayList<>();
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      try {
        Account account = new UserDAO().searchAccount(key,
                workerIn.getAccount().getCode());
        if (account == null) {
          errorList.add(
                  "Se debe registrar los niveles de usuarios en cajero " + key.getName());
          continue;
        }
        workerIn.setSecret(UtilMonitor.getMD5(workerIn.getSecret()));
        if (workerIn.getCode() != null) {
          WorkerDAO.updateUser(key,
                  workerIn);
          RequestUtil.commit();
          LOG.info("Se ha modificado un usuario al cajero");
          return errorList;
        }
        Worker workerExist = WorkerDAO.queryUserExist(key, workerIn);
        if (workerExist != null) {
          workerIn.setCode(workerExist.getCode());
          WorkerDAO.updateUser(key,
                  workerIn);
          RequestUtil.commit();
          LOG.info("Se ha modificado un usuario existe al cajero");
          return errorList;
        }
        workerIn.insert(key);
        RequestUtil.commit();
        LOG.info("Se ha insertado un usuario al cajero");
      } catch (AppException ex) {
        throw new AppException(EMessageApp.ERROR_INSERT_USER,
                workerIn.getTitle());
      }
    }
    return errorList;
  }

  public List<UserListAtm> searchUser()
          throws AppException
  {
    List<UserListAtm> userListAtms = new ArrayList<>();
    UserListAtm userAtm;
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      userAtm = new UserListAtm()
              .setNameAtm(utilMonitor.location(key.getName()))
              .setListUserAtm(new WorkerDAO().queryUser(key));
      userListAtms.add(userAtm);
    }
    return userListAtms;
  }

  public void deleteUser(List<Worker> workerList)
          throws AppException
  {
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      for (Worker worker : workerList) {
        new WorkerDAO().deleteUser(key,
                worker.getCard_id());
      }
      RequestUtil.commit();
    }
  }

  public List<Account> searchUserProfile()
          throws AppException
  {
    List<Account> accountListAtms = new ArrayList<>();
    for (IConnectionName key : RequestUtil.CONNECTIONS_LIST.keySet()) {
      accountListAtms = new WorkerDAO().queryUserProfile(key);
      if (accountListAtms != null) {
        return accountListAtms;
      }
    }
    return accountListAtms;
  }

}
