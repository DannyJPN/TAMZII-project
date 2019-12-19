package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.sql.Date;

public class RegisterActivity extends AppCompatActivity {

    Boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        created = true;
        try {

            FileOutputStream fos = openFileOutput("savedregistration.txt", Context.MODE_PRIVATE);


            String InsuranceNumber = "InsuranceNumber:" + "\n";

            String Name = "Name:" + "\n";
            String Surname = "Surname:" + "\n";
            String RegNum = "RegNum:" + "\n";
            fos.write(Name.getBytes());
            fos.write(Surname.getBytes());
            fos.write(InsuranceNumber.getBytes());
            fos.write(RegNum.getBytes());


            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                Date BirthDate = date;

                String Name = tb_childname.getText().toString();
                String Surname = tb_childsurname.getText().toString();
                String regnumstr = tb_regnum.getText().toString();
                String insur = tb_insurcompany.getText().toString();
                if (Name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vyplňte jméno", Toast.LENGTH_LONG).show();
                    return;
                }
                if (Surname.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vyplňte příjmení", Toast.LENGTH_LONG).show();
                    return;
                }
                if (regnumstr.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vyplňte registrační číslo", Toast.LENGTH_LONG).show();
                    return;
                }
                if (insur.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vyplňte číslo pojišťovny", Toast.LENGTH_LONG).show();
                    return;
                }


                Integer RegNum = regnumstr.equals("") ? 0 : Integer.parseInt(regnumstr);
                Integer InsuranceNumber = insur.equals("") ? 0 : Integer.parseInt(insur);

                Intent photoact = new Intent(getApplicationContext(), PhotoActivity.class);
                photoact.putExtra("Name", Name);
                photoact.putExtra("Surname", Surname);
                photoact.putExtra("BirthDate", Child.GetISODate(BirthDate));
                photoact.putExtra("RegNum", RegNum);
                photoact.putExtra("InsuranceNumber", InsuranceNumber);
                photoact.putExtra("OriginActivity", "Register");

                startActivity(photoact);
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        if (created) {
            return;
        }

        try {
            InputStream is = openFileInput("savedregistration.txt");

            BufferedReader d
                    = new BufferedReader(new InputStreamReader(is));
            String line = "";
            final EditText tb_childname = (EditText) findViewById(R.id.tb_childname);
            final EditText tb_childsurname = (EditText) findViewById(R.id.tb_childsurname);
            final EditText tb_regnum = (EditText) findViewById(R.id.tb_regnum);
            final EditText tb_insurcompany = (EditText) findViewById(R.id.tb_insurcompany);
            if (((line = d.readLine()) != null)) {
                if (line.split(":")[0] == "Name") {
                    tb_childname.setText(line.split(":")[1]);
                }
            }
            if (((line = d.readLine()) != null)) {
                if (line.split(":")[0] == "Surname") {
                    tb_childsurname.setText(line.split(":")[1]);
                }
            }
            if (((line = d.readLine()) != null)) {
                if (line.split(":")[0] == "InsuranceNumber") {
                    tb_insurcompany.setText(line.split(":")[1]);
                }
            }
            if (((line = d.readLine()) != null)) {
                if (line.split(":")[0] == "RegNum") {
                    tb_regnum.setText(line.split(":")[1]);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onPause() {
        super.onPause();
        created = false;

        try {

            FileOutputStream fos = openFileOutput("savedregistration.txt", Context.MODE_PRIVATE);
            final EditText tb_childname = (EditText) findViewById(R.id.tb_childname);
            final EditText tb_childsurname = (EditText) findViewById(R.id.tb_childsurname);
            final EditText tb_regnum = (EditText) findViewById(R.id.tb_regnum);
            final EditText tb_insurcompany = (EditText) findViewById(R.id.tb_insurcompany);


            String InsuranceNumber = "InsuranceNumber:" + tb_insurcompany.getText().toString() + "\n";

            String Name = "Name:" + tb_childname.getText().toString() + "\n";
            String Surname = "Surname:" + tb_childsurname.getText().toString() + "\n";
            String RegNum = "RegNum:" + tb_regnum.getText().toString() + "\n";
            fos.write(Name.getBytes());
            fos.write(Surname.getBytes());
            fos.write(InsuranceNumber.getBytes());
            fos.write(RegNum.getBytes());


            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}