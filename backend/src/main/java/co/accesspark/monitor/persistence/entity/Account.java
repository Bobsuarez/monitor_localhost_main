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
public class Account extends EntityManager<Account> implements Serializable
{

  private static final long serialVersionUID = 2789422210988823525L;
  public static final String TABLE_NAME = "core.account";

  public static final String COL_CODE = "code";
  public static final String COL_COMPANY = "company";
  public static final String COL_TITLE = "title";
  public static final String COL_STATUS = "status";

  private Integer code;
  private Company company;
  private String title;
  private String status;

  public Account()
  {
    super(TABLE_NAME);
  }

  @Override
  public PrimaryKey primaryKey()
  {
    return new PrimaryKey(
            COL_CODE,
            Integer.class, ETypePrimaryKey.AUTOINCREMENT);
  }

  @Override
  public void primaryKey(Object key)
  {
    this.code = (Integer) key;
  }

  public Integer getCode()
  {
    return code;
  }

  public Account setCode(Integer code)
  {
    super.set(COL_CODE);
    this.code = code;
    return this;
  }

  public Company getCompany()
  {
    return company;
  }

  public Account setCompany(Company company)
  {
    super.set(COL_COMPANY);
    this.company = company;
    return this;
  }

  public String getTitle()
  {
    return title;
  }

  public Account setTitle(String title)
  {
    super.set(COL_TITLE);
    this.title = title;
    return this;
  }

  public String getStatus()
  {
    return status;
  }

  public Account setStatus(String status)
  {
    super.set(COL_STATUS);
    this.status = status;
    return this;
  }

  @Override
  public Account validate()
          throws AppException
  {
    return this;
  }

  @Override
  public Account getRegister(Retrieve retrieve)
          throws JdbcException
  {
    Integer codeCol = retrieve.getObjectOptional(COL_CODE, Integer.class);
    if (codeCol != null) {
      setCode(codeCol);
    }
    Integer companyCol = retrieve.getObjectOptional(COL_COMPANY, Integer.class);
    if (companyCol != null) {
      Company objCompany = new Company();
      objCompany.setCode(companyCol);
      setCompany(objCompany);
    }
    String titleCol = retrieve.getObjectOptional(COL_TITLE, String.class);
    if (titleCol != null) {
      setTitle(titleCol);
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
      case COL_COMPANY:
        return company == null ? null : company.getCode();
      case COL_TITLE:
        return title;
      case COL_STATUS:
        return status;
      default:
        throw new AppException(EMessageRosilla.ERROR_DATABASE_COLUMN_NO_FOUND_NAME, columnName);
    }
  }

   public static Account fill(Retrieve retrieve)
          throws JdbcException
  {
    return new Account().getRegister(retrieve);
  }

}
