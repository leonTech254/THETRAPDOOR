package com.leonteqsecurity.thetrapdoor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.leonteqsecurity.thetrapdoor.Adaptor.CardAdapter;
import com.leonteqsecurity.thetrapdoor.Model.CardItem;
import com.leonteqsecurity.thetrapdoor.R;

public class Dashboard extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        DisplayCards();

    }



    public  void DisplayCards()
    {
        gridView = findViewById(R.id.gridView);
        CardItem[] cardItems = {
                new CardItem(R.drawable.ic_launcher_foreground, "Card 1", "Description 1"),
                new CardItem(R.drawable.ic_launcher_foreground, "Card 2", "Description 2"),
                // Add more card items as needed
        };

// Create a custom adapter to bind data to the card item layout
        CardAdapter adapter = new CardAdapter(this, cardItems);
        gridView.setAdapter(adapter);


    }
}