package com.shervinf.mylifeinweeks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbarSetup();
        
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 360;i++){
            numList.add(i+1);
        }
        recyclerView = findViewById(R.id.rvNumbers);
        int numberOfColumns = 13;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, numList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "" +adapter.getItem(position) + "    " + position, Toast.LENGTH_SHORT).show();
        view.setBackgroundColor(Color.BLACK);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_dob_menu, menu);
        return true;
    }
}