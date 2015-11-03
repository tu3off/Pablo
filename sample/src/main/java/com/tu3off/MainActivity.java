package com.tu3off;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> items = new ArrayList<>();
        generateItems(items);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(simpleAdapter);

    }

    private void generateItems(List<String> items) {
        for (int i = 0; i < 10; i++) {
            items.add("#" + i);
        }
    }
}
