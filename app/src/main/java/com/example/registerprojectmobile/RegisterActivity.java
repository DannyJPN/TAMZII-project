package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        final EditText tb_childname = (EditText) findViewById(R.id.tb_childname);
        final EditText tb_childsurname = (EditText) findViewById(R.id.tb_childsurname);
        final EditText tb_regnum = (EditText) findViewById(R.id.tb_regnum);
        final DatePicker dp_birthdate = (DatePicker) findViewById(R.id.dp_birthdate);
        final EditText tb_insurcompany = (EditText) findViewById(R.id.tb_insurcompany);
        Button b_save = (Button) findViewById(R.id.btn_save);
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = (Date) new Date
                        (dp_birthdate.getYear(), dp_birthdate.getMonth(), dp_birthdate.getDayOfMonth());
                Date BirthDate =date;
                Integer InsuranceNumber=Integer.getInteger(tb_insurcompany.getText().toString());

                String Name=tb_childname.getText().toString();
                String Surname=tb_childsurname.getText().toString();
                Integer RegNum=Integer.getInteger(tb_regnum.getText().toString());

                Intent photoact = new Intent(getApplicationContext(), PhotoActivity.class);
                photoact.putExtra("Name",Name);
                photoact.putExtra("Surname",Surname);
                photoact.putExtra("BirthDate",BirthDate.toString());
                photoact.putExtra("RegNum",RegNum);
                photoact.putExtra("InsuranceNumber",InsuranceNumber);
                photoact.putExtra("OriginActivity","Register");

                startActivity(photoact);
            }
        });
        tb_regnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        tb_insurcompany.setInputType(InputType.TYPE_CLASS_NUMBER);

    }


}