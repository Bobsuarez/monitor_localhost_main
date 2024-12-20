/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.controller;

import co.accesspark.monitor.business.constant.EMessageApp;
import co.accesspark.monitor.business.constant.ERoute;
import co.accesspark.monitor.business.controller.template.GenericController;
import co.accesspark.monitor.business.service.UserServices;
import co.accesspark.monitor.persistence.entity.Worker;
import com.spiwer.standard.dto.Answer;
import com.spiwer.standard.exception.AppException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AccessSoftware2
 */
@RestController
@CrossOrigin
public class UserController extends GenericController {

  @Autowired
  private UserServices userServices;

  @PostMapping(ERoute.User.CREATE_USER)
  public Answer createUser(
          @RequestBody Worker worker)
          throws AppException
  {
    return new Answer().setInfo(userServices.createUser(worker));
  }

  @PostMapping(ERoute.User.SEARCH_USER)
  public Answer searchUser()
          throws AppException
  {
    return new Answer().setInfo(userServices.searchUser());
  }

  @PostMapping(ERoute.User.DELETE_USER)
  public Answer deleteUser(
          @RequestBody List<Worker> workerList)
          throws AppException
  {
    userServices.deleteUser(workerList);
    return new Answer().setMessage(EMessageApp.OK.getMessage());
  }

  @PostMapping(ERoute.User.PROFILE_USER)
  public Answer searchUserProfile()
          throws AppException
  {
    return new Answer().setInfo(userServices.searchUserProfile());
  }
}
