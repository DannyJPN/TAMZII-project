package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class ChildListActivity extends AppCompatActivity {
    List<Child> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);
        children = new ArrayList<>();
        Intent searchdata = getIntent();
        if (searchdata.getExtras().getString("OriginActivity").equals("ChildSearch")) {
            String name = searchdata.getExtras().getString("NameToSearch");
            String surname = searchdata.getExtras().getString("SurnameToSearch");

            Search(name, surname);


        }
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                // final Child item = (Child) parent.getItemAtPosition(position);

                String childstr = String.valueOf(parent.getItemAtPosition(position));
                int index = Integer.parseInt(childstr.split(" ")[0]);
                Child item = SearchOne(index);
                if (item == null) {
                    Toast.makeText(getApplicationContext(), "Dítě nenalezeno", Toast.LENGTH_LONG).show();
                return;
                }
                Intent detailer = new Intent(getApplicationContext(), DetailDisplayActivity.class);
                detailer.putExtra("Name",item.getName());
                detailer.putExtra("Surname",item.getSurname());
                String datestr = item.getBirthDate().toString();
                detailer.putExtra("BirthDate", datestr);
                detailer.putExtra("RegNum",item.getRegNum());
                detailer.putExtra("InsuranceNumber",item.getInsuranceNumber());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                item.getPhoto().compress(Bitmap.CompressFormat.PNG,100, stream);
                byte[] byteArray = stream.toByteArray();

				detailer.putExtra("Photo",byteArray);
				detailer.putExtra("GroupID",item.getGroupID());

                detailer.putExtra("OriginActivity", "ChildList");

                startActivity(detailer);

            }

        });


    }


    private Child SearchOne(int ID) {

        SQLmanager regman = new SQLmanager(getApplicationContext());
        SQLiteDatabase db = regman.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_BIRTHDATE,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_REGNUM,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_PHOTO,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_GROUPID,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_INSURANCENUMBER


        };
        String selection = SQLmanagerContract.ChildEntry._ID +
                " = ? ";
        String[] selectionArgs = {String.valueOf(ID)};


        Cursor cursor = db.query(
                SQLmanagerContract.ChildEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null            // The sort order
        );

        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToNext();
        Child searchild = new Child();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry._ID));
        String Name = cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME));
        String Surname = cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME));
        String bdate=cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_BIRTHDATE));
        Date BirthDate = Date.valueOf(bdate);
        int RegNum = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_REGNUM));
        Bitmap Photo = convertByteArrayToBitmap(cursor.getBlob(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_PHOTO)));
        int GroupID = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_GROUPID));
        int InsuranceNumber = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_INSURANCENUMBER));

        searchild.setID(ID);
        searchild.setName(Name);
        searchild.setSurname(Surname);
        searchild.setBirthDate(BirthDate);
        searchild.setRegNum(RegNum);
        searchild.setPhoto(Photo);
        searchild.setGroupID(GroupID);
        searchild.setInsuranceNumber(InsuranceNumber);


        cursor.close();

return  searchild;
    }


    private void Search(String name, String surname) {

        SQLmanager regman = new SQLmanager(getApplicationContext());
        SQLiteDatabase db = regman.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_BIRTHDATE,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_REGNUM,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_PHOTO,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_GROUPID,
                SQLmanagerContract.ChildEntry.COLUMN_NAME_INSURANCENUMBER


        };
        String selection = SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME +
                " = ? and " + SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME + " = ?";
        String[] selectionArgs = {name, surname};

        if (surname.equals("") && !name.equals("")) {
            selection = SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME +
                    " = ?";
            selectionArgs = new String[]{name};
        } else if (name.equals("") && !surname.equals("")) {
            selection = SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME +
                    " = ?";
            selectionArgs = new String[]{surname};
        } else if (surname.equals("") && name.equals("")) {

            return;
        }

        String sortOrder =
                SQLmanagerContract.ChildEntry._ID;
        Cursor cursor = db.query(
                SQLmanagerContract.ChildEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder            // The sort order
        );


        while (cursor.moveToNext()) {
            Child searchild = new Child();
            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry._ID));
            String Name = cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_NAME));
            String Surname = cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_SURNAME));
            Date BirthDate = Date.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_BIRTHDATE)));
            int RegNum = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_REGNUM));
            Bitmap Photo = convertByteArrayToBitmap(cursor.getBlob(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_PHOTO)));
            int GroupID = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_GROUPID));
            int InsuranceNumber = cursor.getInt(cursor.getColumnIndexOrThrow(SQLmanagerContract.ChildEntry.COLUMN_NAME_INSURANCENUMBER));

            searchild.setID(ID);
            searchild.setName(Name);
            searchild.setSurname(Surname);
            searchild.setBirthDate(BirthDate);
            searchild.setRegNum(RegNum);
            searchild.setPhoto(Photo);
            searchild.setGroupID(GroupID);
            searchild.setInsuranceNumber(InsuranceNumber);

            children.add(searchild);
        }
        cursor.close();

        FillList(children);


    }

    private Bitmap convertByteArrayToBitmap(byte[] blob) {
        return BitmapFactory.decodeByteArray(blob, 0, blob.length);
    }

    private void FillList(List<Child> children) {
        final List<String> lisviewstrings = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            lisviewstrings.add(children.get(i).getID() + " " + children.get(i).getName() + " " + children.get(i).getSurname());

        }
        ListView listview = (ListView) findViewById(R.id.listview);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, lisviewstrings);
        listview.setAdapter(adapter);


    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
