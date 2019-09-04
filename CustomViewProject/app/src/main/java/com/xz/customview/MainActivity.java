package com.xz.customview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PieChartView pieChartView = findViewById(R.id.am_piechartview);

        final ArrayList<PieChartView.PieChartData> dataList = new ArrayList<>();
        dataList.add(new PieChartView.PieChartData("A", 7, 0xFFCCFF00));
        dataList.add(new PieChartView.PieChartData("A", 1, 0xFF6495ED));
        dataList.add(new PieChartView.PieChartData("A", 3, 0xFFE32636));
        dataList.add(new PieChartView.PieChartData("A", 6, 0xFF800000));
        dataList.add(new PieChartView.PieChartData("A", 11, 0xFF808000));
        dataList.add(new PieChartView.PieChartData("A", 22, 0xFFFF8C69));
        dataList.add(new PieChartView.PieChartData("A", 8, 0xFF80808));

        pieChartView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pieChartView.setDatas(dataList);
            }
        },5000);


        TestView testView=findViewById(R.id.am_testview);



    }
}
