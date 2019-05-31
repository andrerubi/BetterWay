package com.example.ami.betterway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.support.constraint.Constraints.TAG;


public class MainActivity extends AppCompatActivity {

    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*onclick "register a group" button redirect to RegisterGroup.java*/
        register = (Button) findViewById(R.id.register_activity);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterGroup.class);
                startActivity(intent);
            }
        });

        /*onclick "login to a group" button redirect to LoginActivity.java*/
        login = (Button) findViewById(R.id.login_activity);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}