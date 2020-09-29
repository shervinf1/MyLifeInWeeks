package com.shervinf.mylifeinweeks;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        ArrayList<Integer> numList = new ArrayList<>();
//        for (int i = 0; i < 360;i++){
//            numList.add(i+1);
//        }
        recyclerView = findViewById(R.id.rvNumbers);
        int numberOfColumns = 13;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "" +adapter.getItem(position) + "    " + position, Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_dob_menu, menu);
        return true;
    }

    private void setDate(){
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                LocalDate startDate = LocalDate.of(year,monthOfYear,dayOfMonth);
                                LocalDate endDate = LocalDate.of(cldr.get(Calendar.YEAR),cldr.get(Calendar.MONTH),cldr.get(Calendar.DAY_OF_MONTH));
                                int weeksInYear = Math.toIntExact(ChronoUnit.WEEKS.between(startDate, endDate));
                                int numOfItems = 5110;
                                adapter = new MyRecyclerViewAdapter(MainActivity.this, numOfItems,weeksInYear);
                                adapter.setClickListener(MainActivity.this);
                                recyclerView.setAdapter(adapter);
                                Toast.makeText(MainActivity.this, "" + weeksInYear, Toast.LENGTH_SHORT).show();
                            }
                        }, year, month, day);
                picker.show();
            }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.dob_calender:
                setDate();
        }
        return true;
    }



}