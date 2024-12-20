/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.controller;

import co.accesspark.monitor.business.constant.ERoute;
import co.accesspark.monitor.business.controller.template.GenericController;
import co.accesspark.monitor.business.service.MonitorService;
import com.spiwer.standard.dto.Answer;
import com.spiwer.standard.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author AccessSoftware2
 */
@RestController
@CrossOrigin
public class MonitorController extends GenericController
{

  @Autowired
  private MonitorService monitorService;

  @PostMapping(ERoute.Monitor.LEVEL)
  public Answer searchLevel(@RequestParam("name") String nameLocation)
          throws AppException
  {
    return new Answer().setInfo(monitorService.levelMonitor(nameLocation));

  }

}
