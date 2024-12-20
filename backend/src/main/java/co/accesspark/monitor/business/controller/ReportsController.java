/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.controller;

import co.accesspark.monitor.business.constant.ERoute;
import co.accesspark.monitor.business.controller.template.GenericController;
import co.accesspark.monitor.business.service.ReportsService;
import com.spiwer.standard.dto.Answer;
import com.spiwer.standard.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AccessSoftware2
 */
@RestController
@CrossOrigin
public class ReportsController extends GenericController {

    @Autowired
    private ReportsService reportsService;

    @PostMapping(ERoute.Reports.TRANSACTIONS)
    public Answer searchLevel(
            @RequestParam("name") String nameLocation,
            @RequestParam("initialDate") String initialDate,
            @RequestParam("finalDate") String finalDate)
            throws AppException {
        return new Answer().setInfo(reportsService
                .searchTransaction(nameLocation, initialDate, finalDate));

    }

    @PostMapping(ERoute.Reports.FILL_EMPTY)
    public Answer searchEmptyFill(
            @RequestParam("name") String nameLocation,
            @RequestParam("initialDate") String initialDate,
            @RequestParam("finalDate") String finalDate)
            throws AppException {
        return new Answer().setInfo(reportsService
                .searchEmptyFill(nameLocation, initialDate, finalDate));

    }

    @PostMapping(ERoute.Reports.REFUNDS_COLLETIONS)
    public Answer searchRefundsColletions(
            @RequestParam("name") String nameLocation,
            @RequestParam("initialDate") String initialDate,
            @RequestParam("finalDate") String finalDate)
            throws AppException {
        return new Answer().setInfo(reportsService
                .searchRefundsColletion(nameLocation, initialDate, finalDate));

    }

    @PostMapping(ERoute.Reports.REFUNDS_DAYS)
    public Answer searchRefundsDay(
            @RequestParam("name") String nameLocation,
            @RequestParam("initialDate") String initialDate,
            @RequestParam("finalDate") String finalDate)
            throws AppException {
        return new Answer().setInfo(reportsService
                .searchrRefundsDay(nameLocation, initialDate, finalDate));

    }

}
