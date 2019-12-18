package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Context;
=======
>>>>>>> 0e8c936a63e80ca7fdcaa023308076a13587daa7
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
=======
>>>>>>> 0e8c936a63e80ca7fdcaa023308076a13587daa7
import java.time.LocalDateTime;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

<<<<<<< HEAD
Boolean created = false;
=======

>>>>>>> 0e8c936a63e80ca7fdcaa023308076a13587daa7
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
<<<<<<< HEAD
created=true;
        try{

            FileOutputStream fos = openFileOutput("savedregistration.txt", Context.MODE_PRIVATE);


            String  InsuranceNumber="InsuranceNumber:"+"\n";

            String Name="Name:"+"\n";
            String Surname="Surname:"+"\n";
            String RegNum="RegNum:"+"\n";
            fos.write(Name.getBytes());
            fos.write(Surname.getBytes());
            fos.write(InsuranceNumber.getBytes());
            fos.write(RegNum.getBytes());




            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

=======



>>>>>>> 0e8c936a63e80ca7fdcaa023308076a13587daa7
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
                Integer InsuranceNumber=Integer.parseInt(tb_insurcompany.getText().toString());

                String Name=tb_childname.getText().toString();
                String Surname=tb_childsurname.getText().toString();
                Integer RegNum=Integer.parseInt(tb_regnum.getText().toString());

                Intent photoact = new Intent(getApplicationContext(), PhotoActivity.class);
                photoact.putExtra("Name",Name);
                photoact.putExtra("Surname",Surname);
<<<<<<< HEAD
                photoact.putExtra("BirthDate", Child.GetISODate(BirthDate));
=======
                photoact.putExtra("BirthDate", GetISODate(BirthDate));
>>>>>>> 0e8c936a63e80ca7fdcaa023308076a13587daa7
                photoact.putExtra("RegNum",RegNum);
                photoact.putExtra("InsuranceNumber",InsuranceNumber);
                photoact.putExtra("OriginActivity","Register");

                startActivity(photoact);
            }
        });
<<<<<<< HEAD

    }

  @Override
    protected void onResume() {

        super.onResume();
        if(created)
        {
            return;}
			
			 try
        {
            InputStream is = openFileInput("savedregistration.txt");

           BufferedReader d
          = new BufferedReader(new InputStreamReader(is));
           String line = "";
            final EditText tb_childname = (EditText) findViewById(R.id.tb_childname);
            final EditText tb_childsurname = (EditText) findViewById(R.id.tb_childsurname);
            final EditText tb_regnum = (EditText) findViewById(R.id.tb_regnum);
            final EditText tb_insurcompany = (EditText) findViewById(R.id.tb_insurcompany);
		   if(((line=d.readLine()) !=null)){if(line.split(":")[0]=="Name"){ tb_childname.setText(line.split(":")[1]);} }
if(((line=d.readLine()) !=null)){if(line.split(":")[0]=="Surname"){ tb_childsurname.setText(line.split(":")[1]);} }
if(((line=d.readLine()) !=null)){if(line.split(":")[0]=="InsuranceNumber"){ tb_insurcompany.setText(line.split(":")[1]);} }
if(((line=d.readLine()) !=null)){if(line.split(":")[0]=="RegNum"){ tb_regnum.setText(line.split(":")[1]);} }


        } catch (IOException e) {
            e.printStackTrace();
        }
			
			
			
	}



 @Override
    protected void onPause() {
        super.onPause();
        created=false;
		
		 try{

        FileOutputStream fos = openFileOutput("savedregistration.txt", Context.MODE_PRIVATE);
             final EditText tb_childname = (EditText) findViewById(R.id.tb_childname);
             final EditText tb_childsurname = (EditText) findViewById(R.id.tb_childsurname);
             final EditText tb_regnum = (EditText) findViewById(R.id.tb_regnum);
             final EditText tb_insurcompany = (EditText) findViewById(R.id.tb_insurcompany);


        String  InsuranceNumber="InsuranceNumber:"+tb_insurcompany.getText().toString()+"\n";

                String Name="Name:"+tb_childname.getText().toString()+"\n";
                String Surname="Surname:"+tb_childsurname.getText().toString()+"\n";
               String RegNum="RegNum:"+tb_regnum.getText().toString()+"\n";
				fos.write(Name.getBytes());
				fos.write(Surname.getBytes());
				fos.write(InsuranceNumber.getBytes());
				fos.write(RegNum.getBytes());
				

       

        fos.close();
         } 
	catch (IOException e) {
            e.printStackTrace();
        }

		
		
		
	}
=======
        tb_regnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        tb_insurcompany.setInputType(InputType.TYPE_CLASS_NUMBER);

    }

    private String GetISODate(Date birthDate) {
        StringBuilder sb = new StringBuilder();
        sb.append(birthDate.getYear());
        sb.append("-");
        String month =String.valueOf(birthDate.getMonth());
        if(birthDate.getMonth()<10)
        {
            month="0"+month;
        }
        sb.append(month);
        sb.append("-");
        String day =String.valueOf(birthDate.getDay());
        if(birthDate.getDay()<10)
        {
            day="0"+day;
        }
        sb.append(day);

       // sb.append(" 00:00:00.000");


        
        return sb.toString();
        
        
        

    }
>>>>>>> 0e8c936a63e80ca7fdcaa023308076a13587daa7


}