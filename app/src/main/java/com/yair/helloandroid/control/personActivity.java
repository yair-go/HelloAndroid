package com.yair.helloandroid.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yair.helloandroid.R;
import com.yair.helloandroid.model.entities.Person;

public class personActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Intent myIntent = getIntent();

        //  a -
//        Long personId = myIntent.getLongExtra("Id", -1);
//        String personName = myIntent.getStringExtra("Name");
//
//        Person person = new Person();
//        person.setId(personId);
//        person.setName(personName);

        // b- if we pass the Person to the function:
        Person person = (Person) myIntent.getSerializableExtra("Person");

        TextView nameTV = (TextView) findViewById(R.id.nameTV);
        nameTV.setText(person.getName());


    }
}
