package ua.epam.internetprovider.db.builders;

import ua.epam.internetprovider.db.entity.Entity;
import ua.epam.internetprovider.db.repository.DBManager;

import java.sql.*;
import java.util.List;

/**
 * Abstract class for working with the database
 */
public abstract class QueryBuilder<T extends Entity> {
    /**
     * Lets you know the next automatic id mysql
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return next automatic id mysql
     */
    public final int getNextAutoIncrement(final DBManager instance, final String query) {
        int nextID = -1;
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    nextID = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        instance.closeConnection(conn);
        return nextID;
    }

    /**
     * Execute query (CREATE, UPDATE, DELETE ) the database
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @throws SQLException the SQL exception
     */
    public final void execute(final DBManager instance, final String query) throws SQLException {
        executeQuery(instance, query);
    }

    /**
     * Execute query (CREATE, UPDATE, DELETE ) the database
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     * @throws SQLException the SQL exception
     */
    public final void execute(final DBManager instance, final String query, Object... args) throws SQLException {
        executeQuery(instance, query, args);
    }

    /**
     * Reading from database
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the list of {@link Entity}
     */
    public final List<T> executeAndReturnList(final DBManager instance, final String query, Object... args) {
        return executeAndReturnValues(instance, query, args);
    }

    /**
     * Reading from database by parameter
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     * @return the list of {@link Entity}
     */
    public final T executeAndReturn(final DBManager instance, final String query, Object... args) {
        return executeAndReturnValue(instance, query, args);
    }

    /**
     * QueryBuilder for methods {@link #execute(DBManager, String), {@link #execute(DBManager, String, Object...)}}
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     */
    private void executeQuery(final DBManager instance, String query, Object... args) throws SQLException {
        Connection conn = instance.getConnection();
        // Сброс автофиксации
        conn.setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(st, args);
            st.executeUpdate();
            // Завершение транзакции
            conn.commit();
            // Восстановление по умолчанию
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
        instance.closeConnection(conn);
    }

    /**
     * QueryBuilder for methods {@link #executeAndReturnList(DBManager, String, Object...)} ,
     * {@link #executeAndReturn(DBManager, String, Object...)}}
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the list of {@link Entity}
     */
    private List<T> executeAndReturnValues(final DBManager instance, final String query, Object... args) {
        List<T> models = null;
        Connection conn = instance.getConnection();
        // Сброс автофиксации
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(statement, args);
            try (ResultSet rs = statement.executeQuery()) {
                models = getListOfResult(rs);
                // Завершение транзакции
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
        // Восстановление по умолчанию
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        instance.closeConnection(conn);
        return models;
    }

    /**
     * QueryBuilder for methods {@link #executeAndReturn(DBManager, String, Object...)} ,
     * {@link #executeAndReturn(DBManager, String, Object...)}}
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     * @return the list of {@link Entity}
     */
    private T executeAndReturnValue(final DBManager instance, final String query, Object... args) {
        T model = null;
        Connection conn = instance.getConnection();
        // Сброс автофиксации
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(statement, args);
            try (ResultSet rs = statement.executeQuery()) {
                model = getResult(rs);
                // Завершение транзакции
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
        // Восстановление по умолчанию
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        instance.closeConnection(conn);
        return model;
    }

    /**
     * Sets args of prepared statement.
     * Sets the value of the designated parameter using the given object.
     * The JDBC specification specifies a standard mapping from Java Object types to SQL types.
     * The given argument will be converted to the corresponding SQL type before being sent to the database.
     *
     * @param statement the PreparedStatement
     * @param args      the args
     * @throws SQLException the SQL exception
     */
    private void setArgsOfPreparedStatement(PreparedStatement statement, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }
    }

    /**
     * Creates objects
     *
     * @param rs the ResultSet
     * @return the list of {@link Entity}
     * @throws SQLException the SQL exception
     */
    public abstract List<T> getListOfResult(ResultSet rs) throws SQLException;


    /**
     * Creates object
     *
     * @param rs the ResultSet
     * @return the list of {@link Entity}
     * @throws SQLException the SQL exception
     */
    public abstract T getResult(ResultSet rs) throws SQLException;
}