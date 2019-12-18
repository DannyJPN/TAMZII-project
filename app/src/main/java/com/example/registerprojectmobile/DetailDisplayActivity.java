package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;

public class DetailDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_display);
        final TextView tb_childname = (TextView) findViewById(R.id.tb_childnamedisplay);
        final TextView tb_childsurname = (TextView) findViewById(R.id.tb_childsurnamedisplay);
        final TextView tb_regnum = (TextView) findViewById(R.id.tb_childregnumdisplay);

        final TextView tb_insurcompany = (TextView) findViewById(R.id.tb_childinsuranccompanydisplay);

        final TextView tb_groupname = (TextView) findViewById(R.id.tb_groupnamedisplay);

        final TextView tb_birthday = (TextView) findViewById(R.id.tb_childbirthdatedisplay);

        final ImageView img_photo = (ImageView) findViewById(R.id.img_photodisplay);

        Intent setter = getIntent();
        if (setter.getExtras().getString("OriginActivity").equals("ChildList")) {
            tb_childname.setText(setter.getExtras().getString("Name"));
            tb_childsurname.setText(setter.getExtras().getString("Surname"));
            tb_birthday.setText(setter.getExtras().getString("BirthDate"));
            tb_regnum.setText(setter.getExtras().getInt("RegNum"));
            tb_insurcompany.setText(setter.getExtras().getInt("InsuranceNumber"));

            byte[] byteArray = setter.getExtras().getByteArray("Photo");
            Bitmap photo = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img_photo.setImageBitmap(photo);
            int groupid=setter.getExtras().getInt("GroupID");
            String groupname = GetGroupName(groupid);
            tb_groupname.setText(groupname);

        }

    }

    private String GetGroupName(int groupid) {
        SQLmanager regman = new SQLmanager(getApplicationContext());
        SQLiteDatabase db=regman.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME

        };

        String selection = SQLmanagerContract.GroupEntry._ID + " = ?";

        String[] selectionArgs = { String.valueOf(groupid)};


        Cursor cursor = db.query(
                SQLmanagerContract.GroupEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );
        String ret = "";
        if(cursor==null||cursor.getCount()<=0)
        {
            cursor.close();

        }
        else
        {
            cursor.moveToNext();
            ret=cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME));
           cursor.close();

        }
        return ret;
    }
}
