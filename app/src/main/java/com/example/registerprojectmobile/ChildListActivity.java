package com.example.registerprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChildListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);

        Button childsearcher = (Button)findViewById(R.id.btn_childsearch);
        childsearcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Search();
            }
        });







    }

    private void Search() {

  /*      final List<String> lisviewstrings = new ArrayList<>();
        for (int i = 0; i < sokolevels.size(); i++) {
            lisviewstrings.add("level " + String.valueOf(i));

        }
        ListView listview = (ListView) findViewById(R.id.listview);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, lisviewstrings);
        listview.setAdapter(adapter);
*/

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
