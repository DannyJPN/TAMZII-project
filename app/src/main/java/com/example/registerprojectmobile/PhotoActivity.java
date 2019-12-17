package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Child regchild =new Child();
        Intent input = getIntent();
        if(input.getExtras().getString("OriginActivity") == "")
        {}
    }
}
