package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
	Boolean photoloaded = false;
	Boolean created= false;
    private Child regchild=new Child();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        created=true;
        final ImageView img_photo = (ImageView)findViewById(R.id.img_childphoto);
       final Drawable def = img_photo.getDrawable();

       Button photoloader = (Button)findViewById(R.id.btn_loadphoto);
       photoloader.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AddPhoto();
           }
       });

        Button finalsaver = (Button)findViewById(R.id.btn_savefinal);
        finalsaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent input = getIntent();
                String origin =input.getExtras().getString("OriginActivity");
                if( origin.equals( "Register"))
                {
                    regchild =new Child();
                    String date=input.getExtras().getString("BirthDate");
                    regchild.setBirthDate(Date.valueOf(date));
                    regchild.setRegNum(input.getExtras().getInt("RegNum"));
                    regchild.setName(input.getExtras().getString("Name"));
                    regchild.setSurname(input.getExtras().getString("Surname"));
                    regchild.setInsuranceNumber(input.getExtras().getInt("InsuranceNumber"));
                    Drawable d = img_photo.getDrawable();
                    if(photoloaded)
                    {
                        regchild.setPhoto(((BitmapDrawable)d).getBitmap());

                    }
                    else
                        {

                            //AddPhoto();
                            Toast.makeText(getApplicationContext(),"Nahrajte fotografii",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    GetGroupID();
                    SaveChild(regchild);
                    Intent searcher = new Intent(getApplicationContext(),ChildSearchActivity.class);
                    searcher.putExtra("OriginActivity","Photo");
                    startActivity(searcher);
                }
            }
        });
       
    }

    private void GetGroupID() {
        SQLmanager regman = new SQLmanager(getApplicationContext());
        SQLiteDatabase db=regman.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME

        };

        String selection = SQLmanagerContract.GroupEntry.COLUMN_NAME_NAME + " = ?";
        EditText groupname = (EditText)findViewById(R.id.tb_groupname);
        String[] selectionArgs = { groupname.getText().toString()};


        Cursor cursor = db.query(
                SQLmanagerContract.GroupEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
               null              // The sort order
        );
        if(cursor==null||cursor.getCount()<=0)
        {
            regchild.setGroupID(0);
        }
        else
        {
            cursor.moveToNext();
            int groupid = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.GroupEntry._ID));
            regchild.setGroupID(groupid);

        }
        cursor.close();

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
			photoloaded=true;
        }
    }


}
