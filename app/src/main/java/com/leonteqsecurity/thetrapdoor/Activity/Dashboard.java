package com.leonteqsecurity.thetrapdoor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.leonteqsecurity.thetrapdoor.Adaptor.CardAdapter;
import com.leonteqsecurity.thetrapdoor.Model.CardItem;
import com.leonteqsecurity.thetrapdoor.R;

public class Dashboard extends AppCompatActivity {
    private GridView gridView;
    private ImageView gobackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        DisplayCards();

        gobackbtn=(ImageView) findViewById(R.id.goback);
//        click icon to go back
        gobackbtn.setOnClickListener(v -> onBackPressed());

    }



    public  void DisplayCards()
    {
        gridView = findViewById(R.id.gridView);
        CardItem[] cardItems = {

                new CardItem(R.drawable.ic_launcher_foreground, "About", "Description 1"),
                new CardItem(R.drawable.ic_launcher_foreground, "initialize Backdoor", "Description 2"),
                new CardItem(R.drawable.ic_launcher_foreground, "Stop Backdoor", "Description 2"),
                new CardItem(R.drawable.ic_launcher_foreground, "Uninstall", "Description 2"),

        };

        CardAdapter adapter = new CardAdapter(this, cardItems);
        gridView.setAdapter(adapter);


    }
}