package com.yair.helloandroid.control;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yair.helloandroid.R;
import com.yair.helloandroid.model.entities.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int orientation = this.getResources().getConfiguration().orientation;

        Button send_button = (Button) findViewById(R.id.send_button);
        //Button yooB = (Button)findViewById(R.id.yossiButton);
        send_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //  Toast.makeText(MainActivity.this, "Sending...", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.Sending), Toast.LENGTH_SHORT).show();
            }
        } );
        Button add_button  = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Hello Android",Toast.LENGTH_LONG).show();
            }
        });

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Button call_button = (Button)findViewById(R.id.call_Button);
            call_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + "02 1234567"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
            Button find_person = (Button)findViewById(R.id.find_button);
            find_person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Person p = new Person();
                    p.setId(1234);
                    EditText et = (EditText) findViewById(R.id.editText);
                    p.setName(et.getText().toString());

                    Intent intent = new Intent(MainActivity.this,personActivity.class);
                    intent.putExtra("Person",p);
                    startActivity(intent);
                }
            });
        }

    }

    public void loginButtonOnclick(View view) {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
