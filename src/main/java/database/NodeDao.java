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
        return "insert into" + DatabaseContract.Node.tableName + "(" +
                    DatabaseContract.Node.columnNameId + ", " +
                    DatabaseContract.Node.columnNameVersion + ", " +
                    DatabaseContract.Node.columnNameTimestamp + ", " +
                    DatabaseContract.Node.columnNameUid + ", " +
                    DatabaseContract.Node.columnNameUser + ", " +
                    DatabaseContract.Node.columnNameChangeSet + ", " +
                    DatabaseContract.Node.columnNameLat + ", " +
                    DatabaseContract.Node.columnNameLon +
                ") " +
                "values (" +
                    node.getId() + ", " +
                    node.getLat() + ", " +
                    node.getLon() + ", '" +
                    node.getUser().replace('\'', '\\') + "', " +
                    node.getUid() + ", " +
                    node.isVisible() + ", " +
                    node.getVersion() + ", " +
                    node.getChangeset() + ", " +
                    convertTimestamp(node.getTimestamp()) +
                "')";
    }

    @Override
    public PreparedStatement getPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertStatementPattern());

        preparedStatement.setInt(DatabaseContract.Node.columnIndexId, node.getId().intValue());
        preparedStatement.setInt(DatabaseContract.Node.columnIndexVersion, node.getVersion().intValue());
        preparedStatement.setTimestamp(DatabaseContract.Node.columnIndexTimestamp, convertTimestamp(node.getTimestamp()));
        preparedStatement.setInt(DatabaseContract.Node.columnIndexUid, node.getUid().intValue());
        preparedStatement.setString(DatabaseContract.Node.columnIndexUser, node.getUser());
        preparedStatement.setInt(DatabaseContract.Node.columnIndexChangeSet, node.getChangeset().intValue());
        preparedStatement.setDouble(DatabaseContract.Node.columnIndexLat, node.getLat());
        preparedStatement.setDouble(DatabaseContract.Node.columnIndexLon, node.getLon());

        return preparedStatement;
    }

    @Override
    public String getInsertStatementPattern() {
        return "insert into" + DatabaseContract.Node.tableName + "(" +
                    DatabaseContract.Node.columnNameId + ", " +
                    DatabaseContract.Node.columnNameVersion + ", " +
                    DatabaseContract.Node.columnNameTimestamp + ", " +
                    DatabaseContract.Node.columnNameUid + ", " +
                    DatabaseContract.Node.columnNameUser + ", " +
                    DatabaseContract.Node.columnNameChangeSet + ", " +
                    DatabaseContract.Node.columnNameLat + ", " +
                    DatabaseContract.Node.columnNameLon +
                ") " +
                "values (" +
                    "?, " +
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
