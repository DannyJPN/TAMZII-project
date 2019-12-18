package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChildSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_search);

        final EditText namer = (EditText)findViewById(R.id.tb_childnamesearch);
        final EditText surnamer = (EditText)findViewById(R.id.tb_childsurnamesearch);

        Button searcher = (Button)findViewById(R.id.btn_childsearch);
        searcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = namer.getText().toString();
                String surname = surnamer.getText().toString();

                Search(name,surname);
            }
        });
    }

    private void Search(String name,String surname)
    {
        Intent searchintent = new Intent(getApplicationContext(),ChildListActivity.class);
        searchintent.putExtra("NameToSearch",name);
        searchintent.putExtra("SurnameToSearch",surname);
        searchintent.putExtra("OriginActivity","ChildSearch");
        startActivity(searchintent);
    }
}
