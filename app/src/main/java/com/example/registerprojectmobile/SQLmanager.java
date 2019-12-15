package com.example.registerprojectmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SQLmanager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Register.db";
    public static final String SQL_CREATE_CHILDTABLE =
            "CREATE TABLE " + SQLmanagerContract.ChildEntry.TABLE_NAME + "(" +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_ID + "INTEGER PRIMARY KEY," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_BIRTHDATE + "REAL," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_REGNUM + "INTEGER," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_ADDRESS + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_HEALTHSTATE + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_COMMENTS + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_EMAIL + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_PHONE + "INTEGER," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_LEAVINGWITHOUTPARENTS + "TEXT," +
                    SQLmanagerContract.ChildEntry.COLUMN_NAME_INSURANCENUMBER + "INTEGER" + ")";
    public static final String SQL_CREATE_GROUPTABLE =
            "CREATE TABLE " + SQLmanagerContract.GroupEntry.TABLE_NAME + "(" +
                    SQLmanagerContract.GroupEntry.COLUMN_NAME_ID + "INTEGER PRIMARY KEY," +
                    SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME + "TEXT)";
    private static final String SQL_DELETE_CHILDTABLE = "DROP TABLE IF EXISTS " + SQLmanagerContract.ChildEntry.TABLE_NAME;
    private static final String SQL_DELETE_GROUPTABLE = "DROP TABLE IF EXISTS " + SQLmanagerContract.GroupEntry.TABLE_NAME;

    public SQLmanager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHILDTABLE);
        db.execSQL(SQL_CREATE_GROUPTABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CHILDTABLE);
        db.execSQL(SQL_DELETE_GROUPTABLE);

        onCreate(db);

    }

}

