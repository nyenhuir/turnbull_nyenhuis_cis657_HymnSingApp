package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.parceler.Parcels;

public class BulletinActivity extends AppCompatActivity
        implements EventFragment.OnListFragmentInteractionListener{

    Button homeButton;
    Button eventButton;
    Button songButton;
    Button bulletinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);
        homeButton = (Button) findViewById(R.id.homeButton);
        eventButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);

        homeButton.setOnClickListener(y -> {

            Intent intent = new Intent();
            intent.putExtra("next","home");
            //Returns to main
            setResult(HomeScreenActivity.BULLETIN_RESULT,intent);
            //Exits window
            finish();
        });

        eventButton.setOnClickListener(y -> {
            Intent intent = new Intent();
            intent.putExtra("next","event");
            //Returns to main
            setResult(HomeScreenActivity.BULLETIN_RESULT,intent);
            //Exits window
            finish();
        });

        bulletinButton.setOnClickListener(y -> {

        });

        songButton.setOnClickListener(y -> {
            Intent intent = new Intent();
            intent.putExtra("next","song");
            //Returns to main
            setResult(HomeScreenActivity.BULLETIN_RESULT,intent);
            //Exits window
            finish();
        });
    }

    @Override
    public void onListFragmentInteraction(Event item) {
        Intent newLocation = new Intent(BulletinActivity.this, ShowEventActivity.class);
        Parcelable parcel = Parcels.wrap(item);
        newLocation.putExtra("event", parcel);
        startActivity(newLocation);
    }
}

//Not possible without God's help.