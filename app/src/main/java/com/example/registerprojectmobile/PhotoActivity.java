package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.sql.Date;

import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_BIRTHDATE;
import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_GROUPID;
import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_INSURANCENUMBER;
import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME;
import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_PHOTO;
import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_REGNUM;
import static com.example.registerprojectmobile.SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME;

public class PhotoActivity extends AppCompatActivity {
    private final int REQUEST_IMAGE_CAPTURE=1;
    private Child regchild=new Child();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

       Button finalsaver = (Button)findViewById(R.id.btn_savefinal);
        finalsaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent input = getIntent();
                if(input.getExtras().getString("OriginActivity") == "Register")
                {
                    regchild =new Child();
                    regchild.setBirthDate(Date.valueOf(input.getExtras().getString("BirthDate")));
                    regchild.setRegNum(input.getExtras().getInt("RegNum"));
                    regchild.setName(input.getExtras().getString("Name"));
                    regchild.setSurname(input.getExtras().getString("Surname"));
                    regchild.setInsuranceNumber(input.getExtras().getInt("InsuranceCompany"));
                    AddPhoto();
                    GetGroupID();
                    SaveChild(regchild);
                }
            }
        });
       
    }

    private void GetGroupID() {
        SQLmanager regman = new SQLmanager(getApplicationContext());
        SQLiteDatabase db=regman.getReadableDatabase();
// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME

        };

// Filter results WHERE "title" = 'My Title'
        String selection = SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME + " = ?";
        EditText groupname = (EditText)find
        String[] selectionArgs = { "My Title" };


        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
               null              // The sort order
        );


    }

    private void AddPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }
    private void SaveChild(Child regchild)
    {
SQLmanager regman = new SQLmanager(getApplicationContext());
        SQLiteDatabase db=regman.getWritableDatabase();
        ContentValues vals  =new ContentValues();
        vals.put(COLUMN_NAME_NAME,regchild.getName());
        vals.put(COLUMN_NAME_SURNAME,regchild.getSurname());
        vals.put(COLUMN_NAME_BIRTHDATE,regchild.getBirthDate().toString());
        vals.put(COLUMN_NAME_REGNUM,regchild.getRegNum());
        ByteArrayOutputStream imagestream = new ByteArrayOutputStream();
        regchild.getPhoto().compress(Bitmap.CompressFormat.PNG, 100, imagestream);
        vals.put(COLUMN_NAME_PHOTO,imagestream.toByteArray());
        vals.put(COLUMN_NAME_GROUPID,regchild.getGroupID());
        vals.put(COLUMN_NAME_INSURANCENUMBER,regchild.getInsuranceNumber());

        db.insert(SQLmanagerContract.ChildEntry.TABLE_NAME,null,vals);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView img_photo = (ImageView)findViewById(R.id.img_childphoto);
            img_photo.setImageBitmap(photo);
            regchild.setPhoto(photo);
        }
    }


}
