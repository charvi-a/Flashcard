package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView Question = findViewById(R.id.question);
        final TextView Answer = findViewById(R.id.answer);
        Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer.setVisibility(View.VISIBLE);
                Question.setVisibility(View.INVISIBLE);
            }
        });
    }
}