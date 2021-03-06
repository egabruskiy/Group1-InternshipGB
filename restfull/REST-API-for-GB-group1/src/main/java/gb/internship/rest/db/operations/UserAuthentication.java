package gb.internship.rest.db.operations;


import gb.internship.rest.db.DbWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Валидация пары логин/пароль в базе. Использует кастомную функцию в базе.
 *
 * @author Aleksandr Vvedensky
 */
public class UserAuthentication {

    private Log LOG = LogFactory.getLog(UserAuthentication.class);

    private Connection connection;

    public UserAuthentication() {
        this.connection = DbWrapper.getInstance().getConnection();
    }

    /**
     * Проверяет правильность введённого логина и пароля используя кастомную функцию в базе.
     *
     * @param username логин.
     * @param password пароль.
     * @return 2 - admin, 1 - user, 0 - incorrect user.
     * @throws SQLException
     */
    public int getUserAuthorityId(String username, String password) throws SQLException {
        int result = 0;

        LOG.info("Validate user: " + username);
        String sqlQuery = "SELECT user_logon((?), (?));";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            result = resultSet.getInt("user_logon");
        }
        preparedStatement.close();

        return result;
    }
}
