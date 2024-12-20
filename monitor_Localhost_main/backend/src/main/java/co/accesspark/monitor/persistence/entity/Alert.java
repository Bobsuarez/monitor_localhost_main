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
import java.time.LocalDateTime;

/**
 *
 * @author Bobsuarez
 */
public class Alert extends EntityManager<Alert> implements Serializable {

    private static final long serialVersionUID = 2789422210900023525L;
    public static final String TABLE_NAME = "core.alert";

    public static final String COL_CODE = "code";
    public static final String COL_TITLE = "title";
    public static final String COL_STATUS = "status";
    public static final String COL_INSTANT = "instant";

    private Integer code;
    private String title;
    private String status;
    private LocalDateTime instant;

    public Alert() {
        super(TABLE_NAME);
    }

    public Integer getCode() {
        return code;
    }

    public Alert setCode(Integer code) {
        super.set(COL_CODE);
        this.code = code;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Alert setTitle(String title) {
        super.set(COL_TITLE);
        this.title = title;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Alert setStatus(String status) {
        super.set(COL_STATUS);
        this.status = status;
        return this;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public Alert setInstant(LocalDateTime instant) {
        super.set(COL_INSTANT);
        this.instant = instant;
        return this;
    }

    @Override
    public String columnInsertUpdate(String column) {
        return switch (column) {
            case COL_TITLE ->
                column + "::JSONB";
            default ->
                super.columnInsertUpdate(column);
        };
    }

    @Override
    public PrimaryKey primaryKey() {
        return new PrimaryKey(
                COL_CODE,
                Integer.class, ETypePrimaryKey.AUTOINCREMENT);
    }

    @Override
    public void primaryKey(Object key) {
        this.code = (Integer) key;
    }

    @Override
    public Alert validate() throws AppException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Alert getRegister(Retrieve retrieve) throws JdbcException {
        Integer codeCol = retrieve.getObjectOptional(COL_CODE, Integer.class);
        if (codeCol != null) {
            setCode(codeCol);
        }

        String titleCol = retrieve.getObjectOptional(COL_TITLE, String.class);
        if (titleCol != null) {
            setTitle(titleCol);
        }
        String statusCol = retrieve.getObjectOptional(COL_STATUS, String.class);
        if (statusCol != null) {
            setStatus(statusCol);
        }
        LocalDateTime instantCol = retrieve.getObjectOptional(COL_INSTANT, LocalDateTime.class);
        if (instantCol != null) {
            setInstant(instantCol);
        }
        return this;
    }

    @Override
    public Object getValue(String columnName) throws AppException {
        switch (columnName) {
            case COL_CODE:
                return code;
            case COL_TITLE:
                return title;
            case COL_STATUS:
                return status;
            case COL_INSTANT:
                return instant;
            default:
                throw new AppException(EMessageRosilla.ERROR_DATABASE_COLUMN_NO_FOUND_NAME, columnName);
        }
    }

    public static Alert fill(Retrieve retrieve)
            throws JdbcException {
        return new Alert().getRegister(retrieve);
    }

}
