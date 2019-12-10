package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

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
        Button addbutton =    (Button)findViewById(R.id.addtogroupbutton);
        Button searchbutton = (Button)findViewById(R.id.searchchildbutton);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(),RegisterActivity.class);
                reg.putExtra("OriginActivity","Main");
                startActivity(reg);
            }
        });
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(),RegisterToGroupActivity.class);
                reg.putExtra("OriginActivity","Main");
                reg.putExtra("DefaultChild" ,"None");

                startActivity(reg);
            }
        });
        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(),ChildListActivity.class);
                reg.putExtra("OriginActivity","Main");
                startActivity(reg);
            }
        });

    }
}
