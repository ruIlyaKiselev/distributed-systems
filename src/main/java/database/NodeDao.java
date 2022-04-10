package database;

import generated.Node;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NodeDao implements IDao {

    private final Connection connection;
    private final Node node;

    public NodeDao(Connection connection, Node node) {
        this.connection = connection;
        this.node = node;
    }

    @Override
    public String getInsertStatement() {
        return "INSERT INTO " + DatabaseContract.Node.tableName + " (" +
                    DatabaseContract.Node.columnNameId + ", " +
                    DatabaseContract.Node.columnNameVersion + ", " +
                    DatabaseContract.Node.columnNameTimestamp + ", " +
                    DatabaseContract.Node.columnNameUid + ", " +
                    DatabaseContract.Node.columnNameUser + ", " +
                    DatabaseContract.Node.columnNameChangeSet + ", " +
                    DatabaseContract.Node.columnNameLat + ", " +
                    DatabaseContract.Node.columnNameLon +
                ") " +
                "VALUES (" +
                    node.getId() + ", " +
                    node.getVersion() + ", " +
                    "'" + convertTimestamp(node.getTimestamp()) + "'" + ", " +
                    node.getUid() + ", " +
                    "'" + node.getUser().replace('\'', '\\') + "'" + ", " +
                    node.getChangeset() + ", " +
                    node.getLat() + ", " +
                    node.getLon() +
                ")";
    }

    @Override
    public PreparedStatement getPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertStatementPattern());

        preparedStatement.setLong(DatabaseContract.Node.columnIndexId, node.getId().longValue());
        preparedStatement.setLong(DatabaseContract.Node.columnIndexVersion, node.getVersion().longValue());
        preparedStatement.setTimestamp(DatabaseContract.Node.columnIndexTimestamp, convertTimestamp(node.getTimestamp()));
        preparedStatement.setLong(DatabaseContract.Node.columnIndexUid, node.getUid().longValue());
        preparedStatement.setString(DatabaseContract.Node.columnIndexUser, node.getUser());
        preparedStatement.setLong(DatabaseContract.Node.columnIndexChangeSet, node.getChangeset().longValue());
        preparedStatement.setDouble(DatabaseContract.Node.columnIndexLat, node.getLat());
        preparedStatement.setDouble(DatabaseContract.Node.columnIndexLon, node.getLon());

        return preparedStatement;
    }

    @Override
    public String getInsertStatementPattern() {
        return "INSERT INTO " + DatabaseContract.Node.tableName + " (" +
                    DatabaseContract.Node.columnNameId + ", " +
                    DatabaseContract.Node.columnNameVersion + ", " +
                    DatabaseContract.Node.columnNameTimestamp + ", " +
                    DatabaseContract.Node.columnNameUid + ", " +
                    DatabaseContract.Node.columnNameUser + ", " +
                    DatabaseContract.Node.columnNameChangeSet + ", " +
                    DatabaseContract.Node.columnNameLat + ", " +
                    DatabaseContract.Node.columnNameLon +
                ") " +
                "VALUES (" +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?" +
                ")";
    }

    private static Timestamp convertTimestamp(XMLGregorianCalendar data) {
        return Timestamp.from(data.toGregorianCalendar().toInstant());
    }
}