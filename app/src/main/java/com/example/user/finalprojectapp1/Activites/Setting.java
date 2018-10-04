package com.example.user.finalprojectapp1.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.finalprojectapp1.API.AppPreferences;
import com.example.user.finalprojectapp1.R;

public class Setting extends AppCompatActivity {

    private TextView room;
    private EditText new_room;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        room = (TextView) findViewById(R.id.room_name_default);
        new_room = (EditText) findViewById(R.id.input_room);
        button = (Button) findViewById(R.id.button_setting_confirm);

        room.setText(AppPreferences.getInstance(Setting.this).getRoom());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_room_name = new_room.getText().toString();
                if (new_room_name.matches("")){
                    Toast.makeText(Setting.this, "please enter room name", Toast.LENGTH_SHORT).show();
                } else{
                    room.setText(new_room_name);
                    AppPreferences.getInstance(Setting.this).setRoom(new_room_name);
                    AppPreferences.getInstance(Setting.this).setChecked();
                    Intent intent = new Intent(Setting.this,Test.class);
                    Intent intent1 = new Intent(Setting.this,Test.class);
                    startService(intent1);
                    startActivity(intent);
                }
            }
        });


    }
}
