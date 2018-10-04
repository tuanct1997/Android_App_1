package com.example.user.finalprojectapp1.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.user.finalprojectapp1.MainActivity;
import com.example.user.finalprojectapp1.R;

public class Test extends AppCompatActivity {
    CardView button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button = (CardView) findViewById(R.id.test_dasboard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test.this,MainActivity.class);
                startActivity(intent);
            }
        });
    button2 = (CardView)findViewById(R.id.setting_dasboard);
    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Test.this,Setting.class);
        }
    });
    }
}
