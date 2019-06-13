package cis657.project.hymnsingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.eventLoc)
    EditText eventLocation;
    @BindView(R.id.eventTitle)
    EditText eventTitle;
    @BindView(R.id.eventTime)
    EditText eventTime;
    @BindView(R.id.date)
    TextView eventDateView;

    String location,title,date,time;
    Button homeButton;
    Button eventButton;
    Button songButton;
    Button bulletinButton;
    FloatingActionButton fab;


    private DateTime eventDate;
    private DatePickerDialog dpDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ButterKnife.bind(this);

        DateTime today = DateTime.now();

        dpDialog = new DatePickerDialog(this, this, today.getYear(), today.getMonthOfYear() - 1, today.getDayOfMonth());
        eventDateView.setText(formatted(today));
        eventDate = today;

        homeButton = (Button) findViewById(R.id.homeButton);
        eventButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        homeButton.setOnClickListener(y -> {

            Intent intent = new Intent();
            intent.putExtra("next","home");
            //Returns to main
            setResult(HomeScreenActivity.EVENT_RESULT,intent);
            //Exits window
            finish();
        });

        eventButton.setOnClickListener(y -> {

        });

        songButton.setOnClickListener(y -> {
            Intent intent = new Intent();
            intent.putExtra("next","song");
            //Returns to main
            setResult(HomeScreenActivity.EVENT_RESULT,intent);
            //Exits window
            finish();
        });

        bulletinButton.setOnClickListener(y -> {
            Intent intent = new Intent();
            intent.putExtra("next","bulletin");
            //Returns to main
            setResult(HomeScreenActivity.EVENT_RESULT,intent);
            //Exits window
            finish();
        });
    }

    @OnClick(R.id.fab)
    public void FABPressed() {
        if(validinput()){
            Intent result = new Intent();
            Event newEvent = new Event();
            newEvent.title = eventTitle.getText().toString();
            newEvent.location = eventLocation.getText().toString();
            newEvent.time = eventTime.getText().toString();
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy");
            newEvent.date = fmt.print(eventDate);
            Parcelable parcel = Parcels.wrap(newEvent);
            result.putExtra("event", parcel);
            result.putExtra("next","home");
            setResult(HomeScreenActivity.EVENT_RESULT,result);
            finish();
        }
        else {
            Snackbar.make(fab, "One or more of the text fields is incomplete or incorrect",
                    Snackbar.LENGTH_LONG).show();
        }

    }

    @OnClick({R.id.date})
    public void datePressed() {
        dpDialog.show();
    }

    private boolean validinput() {
        if(eventLocation.getText().toString().length()!=0 &&eventTime.getText().toString().length()!=0
        &&eventTitle.getText().toString().length()!=0 && eventTime.getText().toString().contains(":"))
            return true;
        else return false;
    }


    private String formatted(DateTime d) {
        return d.monthOfYear().getAsShortText(Locale.getDefault()) + " " +
                d.getDayOfMonth() + ", " + d.getYear();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        eventDate = new DateTime(year, month + 1, dayOfMonth, 0, 0);
        eventDateView.setText(formatted(eventDate));
    }
}
