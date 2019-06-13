package cis657.project.hymnsingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BulletinActivity extends AppCompatActivity
        implements EventFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);
    }

    @Override
    public void onListFragmentInteraction(Event item) {

    }
}
