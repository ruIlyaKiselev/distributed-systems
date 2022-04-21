package database;

import generated.Node;
import generated.Tag;
import org.apache.log4j.Logger;

import java.sql.*;

public class DatabaseProvider {

    private static final Logger logger = Logger.getLogger(DatabaseProvider.class);

    private Connection connection;
    private Statement batchStatement = null;
    NodeDao nodeDao;
    TagDao tagDao;

    private Connection getConnection() {
        return connection;
    }

    public DatabaseProvider() {
        createConnection();
        try {
            initBatchConnection();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    private void createConnection() {
        try {
            logger.info("createConnection: start");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"
                            + DatabaseContract.host + ":"
                            + DatabaseContract.port + "/"
                            + DatabaseContract.databaseName,
                    DatabaseContract.user,
                    DatabaseContract.password
            );
            connection.setAutoCommit(false);
            logger.info("createConnection: finish success");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private void initBatchConnection() throws SQLException {
        batchStatement = connection.createStatement();
        nodeDao = new NodeDao(connection);
        tagDao = new TagDao(connection);
    }

    public void createTables() throws SQLException {
        logger.info("createTables: start");

        createNodesTable();
        createTagsTable();

        logger.info("createTables: finish");
    }

    public void createNodesTable() throws SQLException {
        String sql =
                "CREATE TABLE " + DatabaseContract.Node.tableName + " (" +
                        DatabaseContract.Node.columnNameId + " BIGINT NOT NULL PRIMARY KEY, " +
                        DatabaseContract.Node.columnNameVersion + " BIGINT, " +
                        DatabaseContract.Node.columnNameTimestamp + " TIMESTAMP, " +
                        DatabaseContract.Node.columnNameUid + " BIGINT, " +
                        DatabaseContract.Node.columnNameUser + " VARCHAR(255), " +
                        DatabaseContract.Node.columnNameChangeSet + " BIGINT, " +
                        DatabaseContract.Node.columnNameLat  + " NUMERIC(15), " +
                        DatabaseContract.Node.columnNameLon + " NUMERIC(15)" +
                ")";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
        connection.commit();
    }

    public void createTagsTable() throws SQLException {
        String sql =
                "CREATE TABLE " + DatabaseContract.Tag.tableName + " (" +
                        DatabaseContract.Tag.columnNodeId + " BIGINT NOT NULL REFERENCES " +
                            DatabaseContract.Node.tableName + " (" + DatabaseContract.Node.columnNameId + "), " +
                        DatabaseContract.Tag.columnNameK + " VARCHAR(255), " +
                        DatabaseContract.Tag.columnNameV + " VARCHAR(255), " +
                        "PRIMARY KEY(" +
                            DatabaseContract.Tag.columnNodeId + ", " +
                            DatabaseContract.Tag.columnNameK +
                        ")" +
                ")";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
        connection.commit();
    }

    public void dropTables() throws SQLException {
        logger.info("deleteTables: start");

        String sql =
                "DROP TABLE IF EXISTS " + DatabaseContract.Node.tableName + " CASCADE;" +
                "DROP TABLE IF EXISTS " + DatabaseContract.Tag.tableName + " CASCADE";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
        connection.commit();

        logger.info("deleteTables: finish");
    }

    public long insertByStatement(Node node) {
        try {
            long startTime = System.currentTimeMillis();

            NodeDao nodeDao = new NodeDao(connection, node);
            connection.createStatement().execute(nodeDao.getInsertStatement());

            for (Tag tag : node.getTag()) {
                TagDao tagDao = new TagDao(connection, node.getId().longValue(), tag);
                connection.createStatement().execute(tagDao.getInsertStatement());
            }

            connection.commit();
            return System.currentTimeMillis() - startTime;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return -1;
        }
    }

    public long insertByPreparedStatement(Node node) {
        try {
            long startTime = System.currentTimeMillis();
            nodeDao.executePreparedStatement(node);

            for (Tag tag : node.getTag()) {
                tagDao.executePreparedStatement(node.getId().longValue(), tag);
            }

            connection.commit();
            return System.currentTimeMillis() - startTime;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return -1;
        }
    }

    public void addToBatch(Node node) {
        try {
            NodeDao nodeDao = new NodeDao(connection, node);
            batchStatement.addBatch(nodeDao.getInsertStatement());

            for (Tag tag : node.getTag()) {
                TagDao tagDao = new TagDao(connection, node.getId().longValue(), tag);
                batchStatement.addBatch(tagDao.getInsertStatement());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public long executeBatch() {
        try {
            long startTime = System.currentTimeMillis();

            batchStatement.executeBatch();
            connection.commit();

            return System.currentTimeMillis() - startTime;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return -1;
        }
    }
}
