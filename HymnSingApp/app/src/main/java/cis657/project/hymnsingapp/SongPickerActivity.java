package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

public class SongPickerActivity extends AppCompatActivity
        implements SongFragment.OnListFragmentInteractionListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_picker);
    }

    @Override
    public void onListFragmentInteraction(Song item) {
        Intent result = new Intent();

        Parcelable parcel = Parcels.wrap(item);
        result.putExtra("songpick", parcel);
        //Returns to main
        setResult(CreateEventActivity.PICKER_RESULT,result);
        //Exits window
        finish();
    }
}

//Not possible without God's help.
