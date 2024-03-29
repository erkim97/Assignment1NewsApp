package ca.bcit.assignment1newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String KEYWORD = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Button search_button = findViewById(R.id.search_button);
        EditText search_bar = findViewById(R.id.search_bar);
        String keyword = search_bar.getText().toString();

        if ((view == search_button && !keyword.isEmpty())){
            Intent i = new Intent(this, ResultsActivity.class);
            i.putExtra(KEYWORD, keyword);
            startActivity(i);
        } else {
            Toast.makeText(this, "Please enter keyword", Toast.LENGTH_SHORT).show();
        }
    }


}
