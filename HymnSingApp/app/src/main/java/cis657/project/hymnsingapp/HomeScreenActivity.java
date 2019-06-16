package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static RecyclerView recycleview;

    DatabaseReference topRef;
    DatabaseReference songRef;
    DatabaseReference topRef2;
    public static List<Event> allEvents;
    public static List<Song> allSongs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allEvents = new ArrayList<Event>();

        setContentView(R.layout.activity_home_screen);

        homeButton = (Button) findViewById(R.id.homeButton);
        eventButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);
        recycleview = (RecyclerView) findViewById(R.id.fragment);


        topRef = FirebaseDatabase.getInstance().getReference().child("Event");

        songRef = FirebaseDatabase.getInstance().getReference().child("Sheet1");
        allSongs = new ArrayList<Song>();
        songRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount()+"songs");
                for(DataSnapshot eventSnapshot: dataSnapshot.getChildren()){
                     HashMap<String,String> instring = (HashMap<String,String>)eventSnapshot.getValue();
//                    inEvent._eventkey = dataSnapshot.getKey();
//                    allEvents.add(inEvent);
                    Song newsong = new Song();
                    newsong.reference = eventSnapshot.child("reference").getValue().toString();
                    newsong.title=eventSnapshot.getKey().toString();
                    allSongs.add(newsong);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recycleview.getAdapter().notifyDataSetChanged();

//        ArrayList<String> songList;
//        songList = new ArrayList<String>();
//
//        songRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                songList
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



//        songRef=topRef.child("String").getDatabase();
//        System.out.println("SONGREF: "+songRef.);




        homeButton.setOnClickListener(y -> {

            Intent newLocation = new Intent(HomeScreenActivity.this, HomeScreenActivity.class);
            startActivity(newLocation);
            finish();

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
        topRef = FirebaseDatabase.getInstance().getReference().child("Event");
        topRef.addChildEventListener (chEvListener);
        recycleview.getAdapter().notifyDataSetChanged();
//        topRef.addValueEventListener(valEvListener);
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
            if (data != null && data.hasExtra("next")) {
                nextScreen = data.getStringExtra("next");
                if (data.hasExtra("event")) {
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
                    eventEntry.set_eventkey(e.date);
                    eventEntry.setSongs(e.songs);
                    topRef.push().setValue(eventEntry);

//                    topRef.push().setValue(eventEntry);
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
        }
        else if (resultCode == SONG_RESULT) {
            if (data != null && data.hasExtra("next")) {
                nextScreen = data.getStringExtra("next");
                if (nextScreen.contentEquals("event")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, CreateEventActivity.class);
                    startActivityForResult(newLocation, SONG_RESULT);
                }


                if (nextScreen.contentEquals("bulletin")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, BulletinActivity.class);
                    startActivityForResult(newLocation, BULLETIN_RESULT);
                }
            }
        }
        else if (resultCode == BULLETIN_RESULT) {
            if (data != null && data.hasExtra("next")) {
                nextScreen = data.getStringExtra("next");
                if (nextScreen.contentEquals("song")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, ViewSongsActivity.class);
                    startActivityForResult(newLocation, SONG_RESULT);
                }

                if (nextScreen.contentEquals("event")) {
                    Intent newLocation = new Intent(HomeScreenActivity.this, CreateEventActivity.class);
                    startActivityForResult(newLocation, BULLETIN_RESULT);
                }
            }
            }
            else
                super.onActivityResult(requestCode, resultCode, data);
        }

    @Override
    public void onListFragmentInteraction(Event item) {
        Intent newLocation = new Intent(HomeScreenActivity.this, ShowEventActivity.class);
        Parcelable parcel = Parcels.wrap(item);
        newLocation.putExtra("event", parcel);
        startActivity(newLocation);

    }

//    int count=0;
    private ChildEventListener chEvListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            Event entry = (Event) dataSnapshot.getValue(Event.class);
            entry._eventkey = dataSnapshot.getKey();
            allEvents.add(entry);
            System.out.println("\n\n\nLISTINING: alleventsSize: "+allEvents.size()+" at "+entry._eventkey);
            System.out.println("From Homescreen: "+recycleview.getAdapter());
            recycleview.getAdapter().notifyDataSetChanged();
//            count++;
//            if(count==3) {
//                System.out.println("TRY TO FIND KEY: \nKEY: " + entry._eventkey);
//                topRef.child(entry._eventkey).removeValue();
//            }

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
