package com.example.ami.betterway;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button login;
    String group_pin; //from db
    String pin;  //from user
    EditText et_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login_button);

        /*Getting pin from registered group*/
        DatabaseReference pinRef = FirebaseDatabase.getInstance().getReference().child("Group").child("pin");
        pinRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                group_pin = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_pin = (EditText) findViewById(R.id.user_value);
                pin = et_pin.getText().toString();

                if( pin.equals(group_pin) ){
                    Intent intent = new Intent(LoginActivity.this, GroupActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Pin [" + pin + "] incorrect",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
