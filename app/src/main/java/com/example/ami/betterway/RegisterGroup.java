package com.example.ami.betterway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterGroup extends AppCompatActivity {

    Button register;
    EditText group_name, people_count;
    CheckBox isChildren, isHandicap;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference groupRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_group);

        register = (Button) findViewById(R.id.register);
        group_name = (EditText) findViewById(R.id.Name);
        people_count = (EditText) findViewById(R.id.Count);
        isChildren = (CheckBox) findViewById(R.id.Children);
        isHandicap = (CheckBox) findViewById(R.id.Handicap);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = group_name.getText().toString();  //getting group name by EditText
                int count = Integer.parseInt(people_count.getText().toString()); //getting #group_people by EditText

                Group group = new Group(name, count ,isChildren.isChecked(),isHandicap.isChecked());
                groupRef = myRef.child("Group");
                groupRef.setValue(group); //adding group to firebase db

                Intent intent = new Intent(RegisterGroup.this, GroupActivity.class);
                startActivity(intent);

            }
        });
    }
}
