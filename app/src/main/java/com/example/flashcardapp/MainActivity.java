package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDB;
    List<Flashcard> savedCards;
    int ind = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDB = new FlashcardDatabase(this);
        savedCards = flashcardDB.getAllCards();

        if (savedCards != null && savedCards.size() > 0) {
            ((TextView) findViewById(R.id.question)).setText(savedCards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer)).setText(savedCards.get(0).getAnswer());
        }
        final TextView Question = findViewById(R.id.question);
        final TextView Answer = findViewById(R.id.answer);
        Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer.setVisibility(View.VISIBLE);
                Question.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddcardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });


        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(savedCards.size() == 0){
                    return;
                }
                ind++;
                if(ind >= savedCards.size() ){
                    ind = 0;
                }
                savedCards = flashcardDB.getAllCards();
                Flashcard new_card =  savedCards.get(ind);

                ((TextView) findViewById(R.id.question)).setText(new_card.getQuestion());
                ((TextView) findViewById(R.id.answer)).setText(new_card.getAnswer());
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            ((TextView) findViewById(R.id.question)).setText(string1);
            ((TextView) findViewById(R.id.answer)).setText(string2);

            Flashcard new_card = new Flashcard(string1,string2);
            flashcardDB.insertCard(new_card);
            savedCards = flashcardDB.getAllCards();
        }
    }
}