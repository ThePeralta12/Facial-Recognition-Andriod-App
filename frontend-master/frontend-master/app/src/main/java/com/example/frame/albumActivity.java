package com.example.frame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class albumActivity extends AppCompatActivity {

    CardView cardPerson1;
    CardView cardPerson2;
    CardView cardPerson3;
    CardView cardPerson4;
    CardView cardPerson5;
    CardView cardPerson6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        //previously was activity main

      cardPerson1 = findViewById(R.id.cardPerson1);
      cardPerson2 = findViewById(R.id.cardPerson2);
      cardPerson3 = findViewById(R.id.cardPerson3);
      cardPerson4 = findViewById(R.id.cardPerson4);
      cardPerson5 = findViewById(R.id.cardPerson5);
      cardPerson6 = findViewById(R.id.cardPerson6);


      cardPerson1.setOnClickListener(new View.OnClickListener(){
           @Override
                   public void onClick(View v){
               showToast("Person 1 Clicked");
           }
        });
        cardPerson2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showToast("Person 2 Clicked");
            }
        });

        cardPerson3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showToast("Person 3 Clicked");
            }
        });
        cardPerson4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showToast("Person 4 Clicked");
            }
        });
        cardPerson5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showToast("Person 5 Clicked");
            }
        });
        cardPerson6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showToast("Person 6 Clicked");
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
