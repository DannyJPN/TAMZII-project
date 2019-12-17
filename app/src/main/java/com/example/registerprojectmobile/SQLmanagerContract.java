package com.example.registerprojectmobile;


import android.provider.BaseColumns;

public final class SQLmanagerContract {
    private SQLmanagerContract() {
    }



    /* Inner class that defines the table contents */
    public static class ChildEntry implements BaseColumns {
        public static final String TABLE_NAME = "Children";
        public static final String COLUMN_NAME_ID = "ID";

        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_SURNAME = "Surname";
        public static final String COLUMN_NAME_BIRTHDATE = "Birthdate";
        public static final String COLUMN_NAME_REGNUM = "Regnum";

        public static final String COLUMN_NAME_INSURANCENUMBER = "Insurancenumber";
        public static final String COLUMN_NAME_GROUPID = "Groupid";
        public static final String COLUMN_NAME_PHOTO = "Photo";

    }

    public static class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "Groups";
        public static final String COLUMN_NAME_ID = "ID";

        public static final String COLUMN_NAME_NAME = "Name";
    }


}
