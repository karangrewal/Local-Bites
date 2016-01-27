package com.example.karangre.localbites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
    }

    public void launchSearchActivity(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);

        // get query
        EditText searchbar = (EditText) findViewById(R.id.search_bar);
        String query = searchbar.getText().toString();

        // launch ResultsActivity
        intent.putExtra("QUERY", query);
        startActivity(intent);
    }
}
