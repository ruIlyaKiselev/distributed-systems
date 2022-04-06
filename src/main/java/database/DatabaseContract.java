package database;

public class DatabaseContract {
    public static final String host = "localhost";
    public static final String user = "postgres";
    public static final String password = "admin";
    public static final String databaseName = "openStreetMap";
    public static final String port = "5432";

    public static class Node {
        public static final String tableName = "nodes";

        public static final String columnNameId = "_id";
        public static final String columnNameVersion = "version";
        public static final String columnNameTimestamp = "timestamp";
        public static final String columnNameUid = "uid";
        public static final String columnNameUser = "user";
        public static final String columnNameChangeSet = "changeSet";
        public static final String columnNameLat = "lat";
        public static final String columnNameLon = "lon";

        public static final int columnIndexId = 1;
        public static final int columnIndexVersion = 2;
        public static final int columnIndexTimestamp = 3;
        public static final int columnIndexUid = 4;
        public static final int columnIndexUser = 5;
        public static final int columnIndexChangeSet = 6;
        public static final int columnIndexLat = 7;
        public static final int columnIndexLon = 8;
    }

    public static class Tag {
        public static final String tableName = "tags";

        public static final String columnNodeId = "nodeId";
        public static final String columnNameK = "k";
        public static final String columnNameV = "v";

        public static final int columnIndexNodeId = 1;
        public static final int columnIndexK = 2;
        public static final int columnIndexV = 3;
    }
}
