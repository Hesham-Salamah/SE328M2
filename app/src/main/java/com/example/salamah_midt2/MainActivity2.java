package com.example.salamah_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper myDB; // we need it to be avaialable for all methods
    EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        result = (EditText) findViewById(R.id.editTextTextPersonName2);
        TextView txt = (TextView) findViewById(R.id.result);
        Button search = (Button) findViewById(R.id.button4);
        Button view = (Button) findViewById(R.id.button5);

        myDB=new DatabaseHelper(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = myDB.getSpecificResult(result.getText().toString());
                txt.setText(c.toString());

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cur = myDB.ViewEmployess();
                StringBuffer buffer = new StringBuffer();
                Toast.makeText(MainActivity2.this, "Item Viewed", Toast.LENGTH_LONG).show();
                while (cur.moveToNext()){

                    buffer.append("id: "+ cur.getString(1)+ "\n");
                    buffer.append("Name: "+ cur.getString(2)+ "\n");
                    buffer.append("Mail: "+ cur.getString(3)+ "\n");
                    buffer.append("Phone: "+ cur.getString(4)+ "\n\n");



                }

                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity2.this);
                builder.setCancelable(true);
                builder.setTitle(" All Employee");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });



    }
}