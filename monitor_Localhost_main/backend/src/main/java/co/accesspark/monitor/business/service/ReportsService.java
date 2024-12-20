/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.service;

import co.accesspark.monitor.business.constant.EMessageApp;
import co.accesspark.monitor.business.util.UtilMonitor;
import co.accesspark.monitor.persistence.dao.ReportsDAO;
import co.accesspark.monitor.persistence.dto.reports.FillEmpty.FillEmpty;
import co.accesspark.monitor.persistence.dto.reports.RefundsColletions.RefundsColletions;
import co.accesspark.monitor.persistence.dto.reports.RefundsDay;
import co.accesspark.monitor.persistence.dto.reports.transaction.TransactionDTO;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.template.IConnectionName;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bobsuarez
 */
@Service
public class ReportsService {

    @Autowired
    private UtilMonitor utilMonitor;

    /**
     *
     * @param location
     * @param initialDate
     * @param finalDate
     * @return
     * @throws AppException
     */
    public List<TransactionDTO> searchTransaction(String location,
            String initialDate, String finalDate)
            throws AppException {
        String nameConnection = utilMonitor.connectionToLocation(location);
        IConnectionName keyConnection = utilMonitor.testfindConnection(nameConnection);
        LocalDateTime dateInitial = utilMonitor.dateStringToLocalDate(initialDate);
        LocalDateTime datefinal = utilMonitor.dateStringToLocalDate(finalDate);
        String resultData = new ReportsDAO()
                .searchTransaction(keyConnection, dateInitial, datefinal);
        if (resultData == null) {
            throw new AppException(EMessageApp.ERROR_FOUND);
        }
        List<TransactionDTO> transactionResult = utilMonitor.jsonArrayToList(resultData, TransactionDTO.class);
        return transactionResult;
    }

    public List<FillEmpty> searchEmptyFill(String location,
            String initialDate, String finalDate)
            throws AppException {
        String nameConnection = utilMonitor.connectionToLocation(location);
        IConnectionName keyConnection = utilMonitor.testfindConnection(nameConnection);
        LocalDateTime dateInitial = utilMonitor.dateStringToLocalDate(initialDate);
        LocalDateTime datefinal = utilMonitor.dateStringToLocalDate(finalDate);
        String resultData = new ReportsDAO()
                .searchFillEmpty(keyConnection, dateInitial, datefinal);
        if (resultData == null) {
            throw new AppException(EMessageApp.ERROR_FOUND);
        }
        List<FillEmpty> fillEmptyResult = utilMonitor.jsonArrayToList(resultData, FillEmpty.class);
        return fillEmptyResult;
    }

    public List<RefundsColletions> searchRefundsColletion(String location,
            String initialDate, String finalDate)
            throws AppException {
        String nameConnection = utilMonitor.connectionToLocation(location);
        IConnectionName keyConnection = utilMonitor.testfindConnection(nameConnection);
        LocalDateTime dateInitial = utilMonitor.dateStringToLocalDate(initialDate);
        LocalDateTime datefinal = utilMonitor.dateStringToLocalDate(finalDate);
        String resultData = new ReportsDAO()
                .searchRefundsColletions(keyConnection, dateInitial, datefinal);
        if (resultData == null) {
            throw new AppException(EMessageApp.ERROR_FOUND);
        }
        List<RefundsColletions> RefundsColletionsResult = utilMonitor.jsonArrayToList(resultData, RefundsColletions.class);
        return RefundsColletionsResult;
    }

    public List<RefundsDay> searchrRefundsDay(String location,
            String initialDate, String finalDate)
            throws AppException {
        String nameConnection = utilMonitor.connectionToLocation(location);
        IConnectionName keyConnection = utilMonitor.testfindConnection(nameConnection);
        LocalDateTime dateInitial = utilMonitor.dateStringToLocalDate(initialDate);
        LocalDateTime datefinal = utilMonitor.dateStringToLocalDate(finalDate);
        String resultData = new ReportsDAO()
                .searchRefundsDay(keyConnection, dateInitial, datefinal);
        if (resultData == null) {
            throw new AppException(EMessageApp.ERROR_FOUND);
        }
        List<RefundsDay> RefundsDayList = utilMonitor.jsonArrayToList(resultData, RefundsDay.class);
        return RefundsDayList;
    }

}
