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
public class Worker extends EntityManager<Worker> implements Serializable {

  private static final long serialVersionUID = 2789466610988823525L;
  public static final String TABLE_NAME = "core.worker";

  public static final String COL_CODE = "code";
  public static final String COL_IDENTIFICATION = "identification";
  public static final String COL_TITLE = "title";
  public static final String COL_STATUS = "status";
  public static final String COL_ACCOUNT = "account";
  public static final String COL_EMAIL = "email";
  public static final String COL_SECRET = "secret";
  public static final String COL_CARD_ID = "card_id";

  private Integer code;
  private String identification;
  private String title;
  private String status;
  private Account account;
  private String email;
  private String secret;
  private String card_id;

  public Worker()
  {
    super(TABLE_NAME);
  }

  public Worker(Integer code)
  {
    this();
    super.set(COL_CODE);
    this.code = code;
  }

  @Override
  public PrimaryKey primaryKey()
  {
    return new PrimaryKey(
            COL_CODE,
            Integer.class,
            ETypePrimaryKey.AUTOINCREMENT);
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

  public Worker setCode(Integer code)
  {
    super.set(COL_CODE);
    this.code = code;
    return this;
  }

  public String getIdentification()
  {
    return identification;
  }

  public Worker setIdentification(String identification)
  {
    super.set(COL_IDENTIFICATION);
    this.identification = identification;
    return this;

  }

  public String getTitle()
  {
    return title;
  }

  public Worker setTitle(String title)
  {
    super.set(COL_TITLE);
    this.title = title;
    return this;
  }

  public String getStatus()
  {
    return status;
  }

  public Worker setStatus(String status)
  {
    super.set(COL_STATUS);
    this.status = status;
    return this;
  }

  public Account getAccount()
  {
    return account;
  }

  public Worker setAccount(Account account)
  {
    super.set(COL_ACCOUNT);
    this.account = account;
    return this;
  }

  public String getEmail()
  {
    return email;
  }

  public Worker setEmail(String email)
  {
    super.set(COL_EMAIL);
    this.email = email;
    return this;
  }

  public String getSecret()
  {
    return secret;
  }

  public Worker setSecret(String secret)
  {
    super.set(COL_SECRET);
    this.secret = secret;
    return this;
  }

  public String getCard_id()
  {
    return card_id;
  }

  public Worker setCard_id(String card_id)
  {
    super.set(COL_CARD_ID);
    this.card_id = card_id;
    return this;
  }

  @Override
  public Worker validate()
          throws AppException
  {
    return this;
  }

  @Override
  public Worker getRegister(Retrieve retrieve)
          throws JdbcException
  {
    Integer codeCol = retrieve.getObjectOptional(COL_CODE,
            Integer.class);
    if (codeCol != null) {
      setCode(codeCol);
    }
    String identificationCol = retrieve.getObjectOptional(COL_IDENTIFICATION,
            String.class);
    if (identificationCol != null) {
      setIdentification(identificationCol);
    }
    String titleCol = retrieve.getObjectOptional(COL_TITLE,
            String.class);
    if (titleCol != null) {
      setTitle(titleCol);
    }
    Integer accountCol = retrieve.getObjectOptional(COL_ACCOUNT,
            Integer.class);
    if (accountCol != null) {
      Account objAccount = new Account();
      objAccount.setCode(accountCol);
      setAccount(objAccount);
    }
    String statusCol = retrieve.getObjectOptional(COL_STATUS,
            String.class);
    if (statusCol != null) {
      setStatus(statusCol);
    }
    String emailCol = retrieve.getObjectOptional(COL_EMAIL,
            String.class);
    if (emailCol != null) {
      setEmail(emailCol);
    }
    String secretCol = retrieve.getObjectOptional(COL_SECRET,
            String.class);
    if (secretCol != null) {
      setSecret(secretCol);
    }
    String cardIdCol = retrieve.getObjectOptional(COL_CARD_ID,
            String.class);
    if (cardIdCol != null) {
      setCard_id(cardIdCol);
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
      case COL_ACCOUNT:
        return account == null ? null : account.getCode();
      case COL_STATUS:
        return status;
      case COL_EMAIL:
        return email;
      case COL_SECRET:
        return secret;
      case COL_CARD_ID:
        return card_id;
      default:
        throw new AppException(
                EMessageRosilla.ERROR_DATABASE_COLUMN_NO_FOUND_NAME,
                columnName);
    }
  }

  public static Worker fill(Retrieve retrieve)
          throws JdbcException
  {
    return new Worker().getRegister(retrieve);
  }
}
