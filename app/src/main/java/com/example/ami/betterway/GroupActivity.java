package com.example.ami.betterway;

import android.accessibilityservice.GestureDescription;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GroupActivity extends AppCompatActivity {

    Group group;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Group");
    TextView gr_name, gr_info, gr_key, gr_people;
    String info;
    Button safe;
    Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        gr_name = (TextView) findViewById(R.id.insert_name);
        gr_info = (TextView) findViewById(R.id.insert_info);
        gr_key = (TextView) findViewById(R.id.insert_key);
        gr_people = (TextView) findViewById(R.id.insert_people);

        safe = (Button) findViewById(R.id.btn_safe);
        help = (Button) findViewById(R.id.btn_help);


        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Group").child("exitReached").setValue(true);
                /*TODO:
                    show new Activity
                 */
            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Group").child("needHelp").setValue(true);
                /*TODO:
                    add user's position to db to reach him when help requested
                 */
                Toast.makeText(getBaseContext(),"Request registered. We are sending someone to help you", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*Retriving group info from db*/
                String name = dataSnapshot.child("groupName").getValue(String.class);
                Boolean child = dataSnapshot.child("children").getValue(Boolean.class);
                Boolean handicap = dataSnapshot.child("handicap").getValue(Boolean.class);
                int people = dataSnapshot.child("people").getValue(int.class);

                group = new Group(name, people, child, handicap);  //every logged user has an instance of the same Group object

                gr_name.setText(group.getGroupName());
                gr_key.setText(group.getPin());
                gr_people.setText(Integer.toString(group.getPeople()));

                if (group.getChildren()) info = " Children in group, ";
                else info = " No children in group, ";

                if (group.getHandicap()) info += "people with handicap in group";
                else info += " no people with handicap";

                gr_info.setText(info);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
            /* Nothing to do
                override of onBackPressed method to disable back button
             */
    }
}
