package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

public class BulletinActivity extends AppCompatActivity
        implements EventFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);
    }

    @Override
    public void onListFragmentInteraction(Event item) {
        Intent newLocation = new Intent(BulletinActivity.this, ShowEventActivity.class);
        Parcelable parcel = Parcels.wrap(item);
        newLocation.putExtra("event", parcel);
        startActivity(newLocation);
    }
}
