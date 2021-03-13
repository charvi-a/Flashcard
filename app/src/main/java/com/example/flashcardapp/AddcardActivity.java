package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddcardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcard);


        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string1 = ((EditText) findViewById(R.id.editTextQuestion)).getText().toString();
                String string2 = ((EditText) findViewById(R.id.editTextAnswer)).getText().toString();
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("string1", string1); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", string2); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();
            }
        });

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}