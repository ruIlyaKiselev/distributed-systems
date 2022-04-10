package database;

import generated.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagDao implements IDao {
        private final Connection connection;
        private final long nodeId;
        private final Tag tag;

        public TagDao(Connection connection, long nodeId, Tag tag) {
                this.connection = connection;
                this.nodeId = nodeId;
                this.tag = tag;
        }

        @Override
        public String getInsertStatement() {
                return "INSERT INTO " + DatabaseContract.Tag.tableName + " (" +
                                DatabaseContract.Tag.columnNodeId + ", " +
                                DatabaseContract.Tag.columnNameK + ", " +
                                DatabaseContract.Tag.columnNameV +
                        ") " +
                        "VALUES (" +
                                nodeId + ", " +
                                "'" + tag.getK().replace('\'', '\\') + "'" + ", " +
                                "'" + tag.getV().replace('\'', '\\') + "'" +
                        ")";
        }

        @Override
        public PreparedStatement getPreparedStatement() throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(getInsertStatementPattern());

                preparedStatement.setLong(DatabaseContract.Tag.columnIndexNodeId, nodeId);
                preparedStatement.setString(DatabaseContract.Tag.columnIndexK, tag.getK());
                preparedStatement.setString(DatabaseContract.Tag.columnIndexV, tag.getV());

                return preparedStatement;
        }

        @Override
        public String getInsertStatementPattern() {
                return "INSERT INTO " + DatabaseContract.Tag.tableName + " (" +
                                DatabaseContract.Tag.columnNodeId + ", " +
                                DatabaseContract.Tag.columnNameK + ", " +
                                DatabaseContract.Tag.columnNameV +
                        ") " +
                        "VALUES (" +
                                "?, " +
                                "?, " +
                                "?" +
                        ")";
        }
}
