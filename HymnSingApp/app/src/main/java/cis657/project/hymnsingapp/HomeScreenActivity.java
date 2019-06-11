package cis657.project.hymnsingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import cis657.project.hymnsingapp.dummy.DummyContent;

public class HomeScreenActivity extends AppCompatActivity
                implements EventFragment.OnListFragmentInteractionListener{

    public static int SEARCH_RESULT = 1;

    Button tempbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        tempbutton = (Button) findViewById(R.id.homeButton);


        tempbutton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, HymnSearchActivity.class);
            startActivityForResult(newLocation, SEARCH_RESULT);
        });
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyEvent item) {

    }
}
