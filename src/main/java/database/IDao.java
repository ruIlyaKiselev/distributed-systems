package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

interface IDao {
    String getInsertStatement();
    String getInsertStatementPattern();
    PreparedStatement getPreparedStatement() throws SQLException;
}
