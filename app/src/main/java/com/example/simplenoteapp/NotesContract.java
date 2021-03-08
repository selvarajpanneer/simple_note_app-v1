package com.example.simplenoteapp;

import android.provider.BaseColumns;

public final class NotesContract {

        private NotesContract() {}

        public static class NotesEntry implements BaseColumns {
            public static final String SERIAL_NO="Serial_No";
            public static final String TABLE_NAME = "notes";
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
            public static final String CREATION_TIME = "created_at";
            public static final String DELETE_FLAG = "Delete_Flag";
            public static final String ALARM_TIME = "Alarm_Time";
            public static final String ALARM_ACTIVE = "Alaram_Active";
            public static final String ALARM_REPETITION= "Alarm_Repetition";
            public static final String LAST_MODIFIED_TIME = "Last_Modified_time";
            public static final String REMINDER_PRIORITY= "Reminder_priority";
            public static final String CATEGORIES= "categories";


        }

}
