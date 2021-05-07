package com.techlabsolution.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity2 extends AppCompatActivity {
    RecyclerView rvBtn;
    List<String> numberList = new ArrayList<>();
    int[] colorInt = new int[]{R.color.test1, R.color.test2, R.color.test3, R.color.test4, R.color.test5, R.color.test6, R.color.test7, R.color.test8, R.color.test9, R.color.test10, R.color.test11, R.color.test12, R.color.test13, R.color.test14, R.color.test15};
    BtnAdpter adpter;
    ClsBtnPosition[] clsBtnPositionsArray = new ClsBtnPosition[15];


    int i = R.color.black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        rvBtn = findViewById(R.id.rvBtn);
        rvBtn.setLayoutManager(new LinearLayoutManager(HomeActivity2.this));
        for (int i = 0; i < 15; i++) {
            clsBtnPositionsArray[i] = new ClsBtnPosition(String.valueOf(i + 1), i);
        }
        adpter = new BtnAdpter(clsBtnPositionsArray, HomeActivity2.this);
        rvBtn.setAdapter(adpter);


    }
}