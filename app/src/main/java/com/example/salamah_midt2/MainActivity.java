package com.example.salamah_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB; // we need it to be avaialable for all methods
    EditText num, name, mail, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);


        Button delete = (Button) findViewById(R.id.button6);
        Button bttnAdd = (Button) findViewById(R.id.button);
        Button act2 = (Button) findViewById(R.id.button2);
        Button act3 = (Button) findViewById(R.id.button3);
        num = (EditText) findViewById(R.id.editTextNumber);
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        mail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        phone = (EditText) findViewById(R.id.editTextPhone);

        bttnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDB.addData(num.getText().toString(), name.getText().toString(), mail.getText().toString()
                ,phone.getText().toString());

                Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_LONG).show();
                num.getText().clear();
                name.getText().clear();
                mail.getText().clear();
                phone.getText().clear();



            }
        });

        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });

        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.DeleteEmployees(num.getText().toString());
                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();
            }
        });

    }
}