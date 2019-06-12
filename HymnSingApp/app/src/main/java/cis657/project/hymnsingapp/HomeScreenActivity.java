package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

//import cis657.project.hymnsingapp.dummy.EventFragmentContent;

public class HomeScreenActivity extends AppCompatActivity
                implements EventFragment.OnListFragmentInteractionListener{

    public static int EVENT_RESULT = 2;
    public static int SONG_RESULT = 3;
    public static int BULLETIN_RESULT = 4;

    Button homeButton;
    Button eventButton;
    Button songButton;
    Button bulletinButton;
    RecyclerView recycleview;

    DatabaseReference topRef;
    public static List<Event> allEvents;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        homeButton = (Button) findViewById(R.id.homeButton);
        eventButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);
        recycleview = (RecyclerView) findViewById(R.id.fragment);

        topRef = FirebaseDatabase.getInstance().getReference();
        allEvents = new ArrayList<Event>();




        homeButton.setOnClickListener(y -> {
//            System.out.println(FirebaseDatabase.getInstance);

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

    @Override
    public void onResume(){
        super.onResume();
        allEvents.clear();
        topRef = FirebaseDatabase.getInstance().getReference();
        topRef.addChildEventListener (chEvListener);
        recycleview.getAdapter().notifyDataSetChanged();
        //topRef.addValueEventListener(valEvListener);
    }

    @Override
    public void onPause(){
        super.onPause();
        topRef.removeEventListener(chEvListener);
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
//                EventContent.EventItem item = new EventContent.EventItem(e.title,
//                        e.location, e.time, e.date);
//                EventContent.addItem(item);
                Event eventEntry = new Event();
                eventEntry.setTitle(e.title);
                eventEntry.setLocation(e.location);
                eventEntry.setTime(e.time);
                eventEntry.setDate(e.date);
//                topRef.child("Event").push().setValue(eventEntry);
                topRef.push().setValue(eventEntry);
                recycleview.getAdapter().notifyDataSetChanged();

                }
                if (nextScreen.contentEquals("song")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, ViewSongsActivity.class);
                    startActivityForResult(newLocation, SONG_RESULT);
                }

                if (nextScreen.contentEquals("bulletin")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, BulletinActivity.class);
                    startActivityForResult(newLocation, BULLETIN_RESULT);
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
    public void onListFragmentInteraction(Event item) {
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

    private ChildEventListener chEvListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            System.out.println("\n\n\nLISTINING"+allEvents.size());
            Event entry = (Event) dataSnapshot.getValue(Event.class);
            entry._eventkey = dataSnapshot.getKey();
            allEvents.add(entry);
            recycleview.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            recycleview.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Event entry = (Event) dataSnapshot.getValue(Event.class);
            List<Event> newHistory = new ArrayList<Event>();
            for (Event t : allEvents) {
                if (!t._eventkey.equals(dataSnapshot.getKey())) {
                    newHistory.add(t);
                }
            }

            allEvents = newHistory;
            recycleview.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            recycleview.getAdapter().notifyDataSetChanged();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
