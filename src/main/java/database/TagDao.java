package database;

import generated.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagDao implements IDao {
        private final Connection connection;
        private final int nodeId;
        private final Tag tag;

        public TagDao(Connection connection, int nodeId, Tag tag) {
                this.connection = connection;
                this.nodeId = nodeId;
                this.tag = tag;
        }

        @Override
        public String getInsertStatement() {
                return "insert into" + DatabaseContract.Tag.tableName + "(" +
                                DatabaseContract.Tag.columnNodeId + ", " +
                                DatabaseContract.Tag.columnNameK + ", " +
                                DatabaseContract.Tag.columnNameV +
                        ") " +
                        "values (" +
                                nodeId + ", " +
                                tag.getK() + ", " +
                                tag.getV() +
                        "')";
        }

        @Override
        public PreparedStatement getPreparedStatement() throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(getInsertStatementPattern());

                preparedStatement.setInt(DatabaseContract.Tag.columnIndexNodeId, nodeId);
                preparedStatement.setString(DatabaseContract.Tag.columnIndexK, tag.getK());
                preparedStatement.setString(DatabaseContract.Tag.columnIndexV, tag.getV());

                return preparedStatement;
        }

        @Override
        public String getInsertStatementPattern() {
                return "insert into" + DatabaseContract.Tag.tableName + "(" +
                                DatabaseContract.Tag.columnNodeId + ", " +
                                DatabaseContract.Tag.columnNameK + ", " +
                                DatabaseContract.Tag.columnNameV +
                        ") " +
                        "values (" +
                                "?, " +
                                "?, " +
                                "?" +
                        ")";
        }
}
