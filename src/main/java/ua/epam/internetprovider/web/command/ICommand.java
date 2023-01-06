package ua.epam.internetprovider.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Main interface for the Command pattern implementation.
 */
public interface ICommand {
    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     * @throws SQLException the SQL exception
     * @throws RuntimeException the runtime exception
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws RuntimeException, SQLException;
}
