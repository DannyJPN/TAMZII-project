package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button regbutton =    (Button) findViewById(R.id.registerbutton);

        Button searchbutton = (Button)findViewById(R.id.searchchildbutton);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(),RegisterActivity.class);
                reg.putExtra("OriginActivity","Main");
                startActivity(reg);
            }
        });

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(),ChildSearchActivity.class);
                reg.putExtra("OriginActivity","Main");
                startActivity(reg);
            }
        });

SQLmanager regman = new SQLmanager(getApplicationContext());
SQLiteDatabase db = regman.getWritableDatabase();
        Group[] groups = new Group[]
                {
                        new Group(1,"vaření"),
                        new Group(2,"keramika"),
                        new Group(3,"zpěv"),
                        new Group(4,"hra na flétnu"),
                        new Group(5,"hra na kytaru"),
                        new Group(6,"elektrotechnický kroužek"),
                        new Group(7,"florbal")


                };

        for(int i =0;i<groups.length;i++)
        {
            ContentValues vals  =new ContentValues();
            vals.put("Name",groups[i].getName());
            db.insert(SQLmanagerContract.GroupEntry.TABLE_NAME,null,vals);
        }

    }
}
