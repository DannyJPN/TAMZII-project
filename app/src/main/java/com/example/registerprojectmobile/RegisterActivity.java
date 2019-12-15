package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText tb_childname = (EditText) findViewById(R.id.tb_childname);
        EditText tb_childsurname = (EditText) findViewById(R.id.tb_childsurname);
        EditText tb_regnum = (EditText) findViewById(R.id.tb_regnum);
        EditText tb_address = (EditText) findViewById(R.id.tb_address);
        EditText tb_healthstate = (EditText) findViewById(R.id.tb_healthstate);
        EditText tb_comments = (EditText) findViewById(R.id.tb_comments);
        DatePicker dp_birthdate = (DatePicker) findViewById(R.id.dp_birthdate);
        EditText tb_childemail = (EditText) findViewById(R.id.tb_childemail);
        EditText tb_childphone = (EditText) findViewById(R.id.tb_childphone);
        EditText tb_insurcompany = (EditText) findViewById(R.id.tb_insurcompany);
        CheckBox chb_leavingalone = (CheckBox) findViewById(R.id.chb_leavingalone);

        tb_regnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        tb_insurcompany.setInputType(InputType.TYPE_CLASS_NUMBER);
        tb_childphone.setInputType(InputType.TYPE_CLASS_NUMBER);


    }
}
