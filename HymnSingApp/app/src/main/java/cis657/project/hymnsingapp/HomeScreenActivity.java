package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.parceler.Parcels;

import cis657.project.hymnsingapp.dummy.EventContent;

public class HomeScreenActivity extends AppCompatActivity
                implements EventFragment.OnListFragmentInteractionListener{

    public static int EVENT_RESULT = 2;
    public static int SONG_RESULT = 3;
    public static int BULLETIN_RESULT = 4;

    Button homeButton;
    Button eventButton;
    Button songButton;
    Button bulletinButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        homeButton = (Button) findViewById(R.id.homeButton);
        eventButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);



        homeButton.setOnClickListener(y -> {

        });

        eventButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, CreateEventActivity.class);
            startActivityForResult(newLocation, EVENT_RESULT);
        });

        songButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, ViewSongsActivity.class);
            startActivityForResult(newLocation, SONG_RESULT);
        });

        bulletinButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, BulletinActivity.class);
            startActivityForResult(newLocation, BULLETIN_RESULT);
        });
    }

    //If a result is returned, this function is activated and stores the returned data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String nextScreen ="";

        if(resultCode == EVENT_RESULT) {
            if(data!=null && data.hasExtra("next")&&data.hasExtra("event")) {
                nextScreen = data.getStringExtra("next");
                Parcelable parcel = data.getParcelableExtra("event");
                Event e = Parcels.unwrap(parcel);
                EventContent.EventItem item = new EventContent.EventItem(e.title,
                        e.location, e.time, e.date);
                EventContent.addItem(item);

//                getSupportFragmentManager().beginTransaction().detach(findViewById(R.id.fragment).attach().commit();

                if (nextScreen.contentEquals("song")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, ViewSongsActivity.class);
                    startActivityForResult(newLocation, SONG_RESULT);
                }

                if (nextScreen.contentEquals("bulletin")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, BulletinActivity.class);
                    startActivityForResult(newLocation, BULLETIN_RESULT);
                }
            }
        }
        else if (resultCode == SONG_RESULT) {
        }
        else if (resultCode == BULLETIN_RESULT) {

            }
            else
                super.onActivityResult(requestCode, resultCode, data);
        }

    @Override
    public void onListFragmentInteraction(EventContent.EventItem item) {
        System.out.println("Interact!");
        Intent intent = new Intent();
//        Double[] vals = {item.origLat, item.origLong, item.endLat, item.endLong};
//        intent.putExtra("item", vals);
//        setResult(MainActivity.HISTORY_RESULT,intent);
        finish();

//        Intent result = new Intent();
//        // add more code to initialize the rest of the fields
//        Parcelable parcel = Parcels.wrap(item);
//        result.putExtra("item", parcel);
//        setResult(MainActivity.HISTORY_RESULT, result);
//        finish();

    }
}
