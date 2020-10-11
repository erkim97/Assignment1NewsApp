package ca.bcit.assignment1newsapp;

package com.example.kimer.goodfoodsmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main);

        ListView list_continents = findViewById( R.id.list_categories );
        list_continents.setOnItemClickListener( ( adapterView, view, position, id ) -> {
            Intent i = new Intent( MainActivity.this, ProductTypeActivity.class );
            i.putExtra( "index", (int) id );
            startActivity( i );
        } );
    }
}
