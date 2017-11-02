package com.example.fivestar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FiveStarView fsv;
    private TextView textView;
    private int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fsv = (FiveStarView) findViewById(R.id.fsv);
        textView = (TextView) findViewById(R.id.tv);
        initData();
    }
    private void initData(){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                fsv.setCurProgress(num);
                Toast.makeText(MainActivity.this,num+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
