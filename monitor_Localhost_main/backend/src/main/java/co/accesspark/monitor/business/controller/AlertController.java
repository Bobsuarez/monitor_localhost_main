/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.controller;

import co.accesspark.monitor.business.constant.ERoute;
import co.accesspark.monitor.business.controller.template.GenericController;
import co.accesspark.monitor.business.service.AlertService;
import com.spiwer.standard.dto.Answer;
import com.spiwer.standard.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bobsuarez
 */
@RestController
@CrossOrigin
public class AlertController extends GenericController {

    @Autowired
    private AlertService alertService;

    @PostMapping(ERoute.Alert.SAVE_ALERT)
    public Answer saveConnection(
            @RequestParam("name") String location)
            throws AppException {
        alertService.aceptAlert(location);
        return new Answer();
    }

}
