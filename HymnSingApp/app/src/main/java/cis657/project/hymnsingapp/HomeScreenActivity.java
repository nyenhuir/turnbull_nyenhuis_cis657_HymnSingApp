package cis657.project.hymnsingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import cis657.project.hymnsingapp.dummy.EventContent;

public class HomeScreenActivity extends AppCompatActivity
                implements EventFragment.OnListFragmentInteractionListener{

    public static int SEARCH_RESULT = 1;

    Button homeButton;
    Button organizationButton;
    Button songButton;
    Button bulletinButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        homeButton = (Button) findViewById(R.id.homeButton);
        organizationButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);


        homeButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, MainActivity.class);
            startActivityForResult(newLocation, SEARCH_RESULT);
        });

        organizationButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, MainActivity.class);
            startActivityForResult(newLocation, SEARCH_RESULT);
        });

        songButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, MainActivity.class);
            startActivityForResult(newLocation, SEARCH_RESULT);
        });

        bulletinButton.setOnClickListener(y -> {
            Intent newLocation = new Intent(HomeScreenActivity.this, MainActivity.class);
            startActivityForResult(newLocation, SEARCH_RESULT);
        });
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
