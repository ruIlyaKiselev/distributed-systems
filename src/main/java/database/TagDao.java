package database;

import generated.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagDao implements IDao {
        private final Connection connection;
        private final long nodeId;
        private final Tag tag;

        private PreparedStatement preparedStatement = null;

        public TagDao(Connection connection, long nodeId, Tag tag) {
                this.connection = connection;
                this.nodeId = nodeId;
                this.tag = tag;
        }

        public TagDao(Connection connection) {
                this.connection = connection;
                nodeId = 0;
                tag = new Tag();
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
        public PreparedStatement getPreparedStatement() {
                if (preparedStatement == null) {
                        try {
                                preparedStatement = connection.prepareStatement(getInsertStatementPattern());
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }
                }

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

        public void executePreparedStatement(
                long nodeId,
                Tag tag
        ) {
                try {
                        if (preparedStatement == null) {
                                getPreparedStatement();
                        }
                        preparedStatement.setLong(DatabaseContract.Tag.columnIndexNodeId, nodeId);
                        preparedStatement.setString(DatabaseContract.Tag.columnIndexK, tag.getK());
                        preparedStatement.setString(DatabaseContract.Tag.columnIndexV, tag.getV());
                        preparedStatement.execute();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
}
