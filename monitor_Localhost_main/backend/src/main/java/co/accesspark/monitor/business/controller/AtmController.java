/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.controller;

import co.accesspark.monitor.business.constant.ERoute;
import co.accesspark.monitor.business.controller.template.GenericController;
import co.accesspark.monitor.business.service.AtmService;
import co.accesspark.monitor.persistence.dto.AtmConnectionDTO;
import com.spiwer.standard.dto.Answer;
import com.spiwer.standard.exception.AppException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AccessSoftware2
 */
@RestController
@CrossOrigin
public class AtmController
        extends GenericController {

    @Autowired
    private AtmService atmService;

    @PostMapping(ERoute.Atm.SEARCH_ATM_ACTIVE)
    public Answer saveConnection()
            throws AppException
    {
        return new Answer().setInfo(atmService.searchAtmActive());
    }

    @PostMapping(ERoute.Atm.TEST_CONNECTION)
    public Answer testConnection(
            @RequestBody AtmConnectionDTO atmDTO)
            throws AppException
    {

        return new Answer().setInfo(atmService.testConnection(atmDTO));
    }

    @PostMapping(ERoute.Atm.SAVE_PROPERTY)
    public Answer saveConnection(
            @RequestBody AtmConnectionDTO atmDTO)
            throws AppException
    {
        return new Answer().setInfo(atmService.saveConnection(atmDTO));
    }

    @PostMapping(ERoute.Atm.SEARCH_STATION)
    public Answer searchStation(
            @RequestParam("name") String name)
            throws AppException
    {
        return new Answer().setInfo(atmService.getNameStation(name));
    }

    @PostMapping(ERoute.Atm.DISABLE_ATM)
    public Answer disableAtm(
           @RequestBody List<AtmConnectionDTO> atmDTO)
            throws AppException
    {
        atmService.disableATM(atmDTO);
        return new Answer();
    }
}
