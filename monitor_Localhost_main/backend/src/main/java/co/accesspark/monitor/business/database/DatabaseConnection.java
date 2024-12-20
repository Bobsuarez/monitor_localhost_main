package co.accesspark.monitor.business.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.spiwer.rosilla.database.ConnectionManager;
import com.spiwer.rosilla.lasting.EMessageRosilla;
import com.spiwer.standard.exception.AppException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.spiwer.rosilla.template.IDatabaseConnect;

/**
 *
 * @author spiwer.com - Herman Leonardo Rey Baquero - leoreyb@gmail.com
 */
public class DatabaseConnection extends ConnectionManager implements IDatabaseConnect {

    private String ip;
    private String port;
    private String bd;
    private String user;
    private String password;

    public DatabaseConnection(String ip, String port, String bd, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.bd = bd;
        this.user = user;
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static final Logger LOG = Logger.getLogger(DatabaseConnection.class.getName());

    @Override
    public Connection connect()
            throws AppException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection cnn = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/"+bd+"", user, password);
            cnn.setAutoCommit(false);
            return cnn;
        } catch (SQLException | ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw new AppException(EMessageRosilla.ERROR_DATABASE_CONNECTION);
        }
    }

}
