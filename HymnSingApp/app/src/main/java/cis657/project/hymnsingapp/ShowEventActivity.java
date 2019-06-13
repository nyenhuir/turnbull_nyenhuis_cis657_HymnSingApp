package cis657.project.hymnsingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
//
//import com.borax12.materialdaterangepicker.date.DatePickerDialog;
//import com.google.android.gms.common.api.Status;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.widget.Autocomplete;
//import com.google.android.libraries.places.widget.AutocompleteActivity;
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShowEventActivity extends AppCompatActivity
        implements ShowPlaylistFragment.OnListFragmentInteractionListener{

    @BindView(R.id.Location)
    TextView eventLocation;
    @BindView(R.id.Title)
    TextView eventTitle;
    @BindView(R.id.Time)
    TextView eventTime;
    @BindView(R.id.Date)
    TextView eventDate;

    Event shownEvent = new Event();

    String location,title,date,time;
    public static List<String> songlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        ButterKnife.bind(this);

        songlist = new ArrayList<String>();

        Intent receiveintent = getIntent();
        if(receiveintent.hasExtra("event")){
            Parcelable parcel = receiveintent.getParcelableExtra("event");
            Event e = Parcels.unwrap(parcel);
            this.shownEvent=e;
        }
        eventTitle.setText(shownEvent.title);
        eventLocation.setText(shownEvent.location);
        eventTime.setText(shownEvent.time);
        eventDate.setText(shownEvent.date);
        songlist=shownEvent.songs;

//        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
//        if (allFragments != null) {
//            for (Fragment fragment : allFragments) {
//                Fragment1 f1 = (Fragment1)fragment;
//                if (f1.fragmentType == 1)
//                    f1.updateFragmentData();
//            }
//        }
//    }
    }

    @Override
    public void onListFragmentInteraction(String item) {

    }
}
