package com.example.boris.bakingapp.constants;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {
    public static final String TAG_WORK_CHECKING = "checkingWorkProcess";
    public static final String TAG_VAR_VALUE = "checkingVariableValue";

    /**
     * WIDGET
     */
    public static final String AUTHORITY = "com.example.android.mygarden";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_PLANTS = "plants";

    public static final class PlantEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build();

        public static final String TABLE_NAME = "recipes";
        public static final String COLUMN_PLANT_TYPE = "plantType";
        public static final String COLUMN_CREATION_TIME = "createdAt";
        public static final String COLUMN_LAST_WATERED_TIME = "lastWateredAt";
    }
}
