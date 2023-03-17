package com.tarastarasiuk.androidcourse.constant;

public final class AppConstant {
    public static final String RESULT_FRAGMENT_ARG = "result";
    public static final String DEFAULT_DATE_FORMAT = "hh:mm a, EEE, MMM dd, yyyy";
    public static final int DEFAULT_RESULT_MAX_SIZE = 13;

    public static class DatabaseDefinition {
        // Database version
        public static final int DATABASE_VERSION = 1;
        // Database name
        public static final String DATABASE_NAME = "AndroidCourse";

        // TrackedResult table
        public static final String TRACKED_RESULT_TABLE = "trackedResult";

        // TrackedResult columns
        public static final String ID_KEY = "id";
        public static final String DATE_KEY = "date";
        public static final String RESULT_KEY = "result";

        // TrackedResult creation query
        public static final String CREATE_TABLE_TRACKED_RESULT_QUERY = "CREATE TABLE " + TRACKED_RESULT_TABLE + "("
                + ID_KEY + " INTEGER PRIMARY KEY,"
                + DATE_KEY + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + RESULT_KEY + " TEXT"
                + ")";

        // DROP TrackedResult table query
        public static final String DROP_TRACKED_RESULT_TABLE_QUERY = "DROP TABLE IF EXISTS " + TRACKED_RESULT_TABLE;

        // SELECT TrackedResult entries query
        public static final String SELECT_TRACKED_RESULT_QUERY = "SELECT * FROM " + TRACKED_RESULT_TABLE;

        // Project for selection TrackedResult
        public static final String[] SELECT_TRACKED_RESULT_PROJECTION = {ID_KEY, DATE_KEY, RESULT_KEY};
    }
}
