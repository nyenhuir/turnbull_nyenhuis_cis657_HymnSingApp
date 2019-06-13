package cis657.project.hymnsingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SongPickerActivity extends AppCompatActivity
        implements SongFragment.OnListFragmentInteractionListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_picker);
    }

    @Override
    public void onListFragmentInteraction(Song item) {
        Intent intent = new Intent();
        intent.putExtra("songpick",item.title);
        //Returns to main
        setResult(CreateEventActivity.PICKER_RESULT,intent);
        //Exits window
        finish();
    }
}
