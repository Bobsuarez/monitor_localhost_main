/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.entity;

import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.rosilla.lasting.EMessageRosilla;
import com.spiwer.rosilla.template.EntityManager;
import com.spiwer.rosilla.util.Retrieve;
import com.spiwer.standard.column.PrimaryKey;
import com.spiwer.standard.exception.AppException;
import com.spiwer.standard.lasting.ETypePrimaryKey;
import java.io.Serializable;

/**
 *
 * @author AccessSoftware2
 */
public class Company extends EntityManager<Company> implements Serializable
{

  private static final long serialVersionUID = 2789466610966623525L;
  public static final String TABLE_NAME = "core.company";

  public static final String COL_CODE = "code";
  public static final String COL_IDENTIFICATION = "identification";
  public static final String COL_TITLE = "title";
  public static final String COL_PHONE = "phone";
  public static final String COL_ADRESS = "adress";
  public static final String COL_LOGO = "logo";
  public static final String COL_RESOLUTION = "resolution";
  public static final String COL_OTHER = "other";
  public static final String COL_STATUS = "status";

  private Integer code;
  private String identification;
  private String title;
  private String phone;
  private String adress;
  private String logo;
  private String resoluction;
  private String other;
  private String status;

  public Company()
  {
    super(TABLE_NAME);
  }

  @Override
  public PrimaryKey primaryKey()
  {
    return new PrimaryKey(
            COL_CODE, Integer.class,
            ETypePrimaryKey.AUTOINCREMENT);
  }

  public String getPhone()
  {
    return phone;
  }

  public Company setPhone(String phone)
  {
    super.set(COL_PHONE);
    this.phone = phone;
    return this;
  }

  public String getAdress()
  {
    return adress;
  }

  public Company setAdress(String adress)
  {
    super.set(COL_ADRESS);
    this.adress = adress;
    return this;
  }

  public String getLogo()
  {
    return logo;
  }

  public Company setLogo(String logo)
  {
    super.set(COL_LOGO);
    this.logo = logo;
    return this;
  }

  public String getResoluction()
  {
    return resoluction;
  }

  public Company setResoluction(String resoluction)
  {
    super.set(COL_RESOLUTION);
    this.resoluction = resoluction;
    return this;
  }

  public String getOther()
  {
    return other;
  }

  public Company setOther(String other)
  {
    super.set(COL_OTHER);
    this.other = other;
    return this;
  }

  public Integer getCode()
  {
    return code;
  }

  public Company setCode(Integer code)
  {
    super.set(COL_CODE);
    this.code = code;
    return this;
  }

  public String getIdentification()
  {
    return identification;
  }

  public Company setIdentification(String identification)
  {
    super.set(COL_IDENTIFICATION);
    this.identification = identification;
    return this;
  }

  public String getTitle()
  {
    return title;
  }

  public Company setTitle(String title)
  {
    super.set(COL_TITLE);
    this.title = title;
    return this;
  }

  public String getStatus()
  {
    return status;
  }

  public Company setStatus(String status)
  {
    super.set(COL_STATUS);
    this.status = status;
    return this;
  }

  @Override
  public Company validate()
          throws AppException
  {
    return this;
  }

  @Override
  public Company getRegister(Retrieve retrieve)
          throws JdbcException
  {
    Integer codeCol = retrieve.getObjectOptional(COL_CODE, Integer.class);
    if (codeCol != null) {
      setCode(codeCol);
    }
    String identificationCol = retrieve.getObjectOptional(COL_IDENTIFICATION, String.class);
    if (identificationCol != null) {
      setIdentification(identificationCol);
    }
    String titleCol = retrieve.getObjectOptional(COL_TITLE, String.class);
    if (titleCol != null) {
      setTitle(titleCol);
    }
    String phoneCol = retrieve.getObjectOptional(COL_PHONE, String.class);
    if (phoneCol != null) {
      setPhone(phoneCol);
    }
    String adressCol = retrieve.getObjectOptional(COL_ADRESS, String.class);
    if (adressCol != null) {
      setAdress(adressCol);
    }
    String logoCol = retrieve.getObjectOptional(COL_LOGO, String.class);
    if (logoCol != null) {
      setLogo(logoCol);
    }
    String resolutionCol = retrieve.getObjectOptional(COL_RESOLUTION, String.class);
    if (resolutionCol != null) {
      setResoluction(resolutionCol);
    }
    String otherCol = retrieve.getObjectOptional(COL_OTHER, String.class);
    if (otherCol != null) {
      setOther(otherCol);
    }
    String statusCol = retrieve.getObjectOptional(COL_STATUS, String.class);
    if (statusCol != null) {
      setStatus(statusCol);
    }
    return this;
  }

  @Override
  public Object getValue(String columnName)
          throws AppException
  {
    switch (columnName) {
      case COL_CODE:
        return code;
      case COL_IDENTIFICATION:
        return identification;
      case COL_TITLE:
        return title;
      case COL_PHONE:
        return phone;
      case COL_ADRESS:
        return adress;
      case COL_LOGO:
        return logo;
      case COL_RESOLUTION:
        return resoluction;
      case COL_OTHER:
        return other;
      case COL_STATUS:
        return status;
      default:
        throw new AppException(EMessageRosilla.ERROR_DATABASE_COLUMN_NO_FOUND_NAME, columnName);
    }
  }

  public static Company fill(Retrieve retrieve)
          throws JdbcException
  {
    return new Company().getRegister(retrieve);
  }

}
