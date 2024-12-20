/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.service;

import co.accesspark.monitor.business.database.DatabaseConnection;
import co.accesspark.monitor.business.database.lasting.EConnectionName;
import co.accesspark.monitor.business.util.UtilMonitor;
import co.accesspark.monitor.persistence.dto.AtmConnectionDTO;
import co.accesspark.monitor.persistence.dto.AtmReponseDTO;
import com.spiwer.rosilla.util.RequestUtil;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.template.IConnectionName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AccessSoftware2
 */
@Service
public class MainService
{

  @Autowired
  private UtilMonitor utilMonitor;

  public List<AtmReponseDTO> ATMService()
          throws AppException, Exception
  {
    List<AtmConnectionDTO> listATM = utilMonitor.AtmList();
    initConnection(listATM);
    return responseList(listATM);
  }

  public void initConnection(List<AtmConnectionDTO> atmList)
  {
    RequestUtil.CONNECTIONS_LIST.clear();
    for (AtmConnectionDTO atmDTO : atmList) {
      String atmName = atmDTO.getName();
      var name = (IConnectionName) () -> atmName;
      RequestUtil.DEFAULT = EConnectionName.DEFAULT;
      try {
        RequestUtil.CONNECTIONS_LIST.put(name,
                new DatabaseConnection(atmDTO.getIp(),
                        atmDTO.getPort(),
                        atmDTO.getBdname(),
                        atmDTO.getUser(),
                        atmDTO.getPassword()));
        boolean status = utilMonitor.verifyConnection(name);
        if (status) {
          atmDTO.setStatus("Enable");
        }
      } catch (Exception e) {
        RequestUtil.CONNECTIONS_LIST.remove(name);
        atmDTO.setStatus("Disable");
      }
    }
  }

  public List<AtmReponseDTO> responseList(List<AtmConnectionDTO> atmListIn)
  {
    List<AtmReponseDTO> atmListResponse = new ArrayList<>();
    for (AtmConnectionDTO atmDTOIn : atmListIn) {
      AtmReponseDTO atmReponseDTO = new AtmReponseDTO()
              .setIp(atmDTOIn.getIp()).
              setName(atmDTOIn.getName()).
              setLocation(atmDTOIn.getLocation()).
              setStatus(atmDTOIn.getStatus());
      atmListResponse.add(atmReponseDTO);
    }
    return atmListResponse
            .stream().
            sorted().
            collect(Collectors.toList());
  }

}
