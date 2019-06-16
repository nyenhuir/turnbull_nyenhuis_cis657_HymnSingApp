package cis657.project.hymnsingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShowEventActivity extends AppCompatActivity
        implements ShowPlaylistFragment.OnListFragmentInteractionListener{

    String siteUrl = "https://hymnary.org/text/";
    ArrayList<String> url = new ArrayList<String>();

    @BindView(R.id.Location)
    TextView eventLocation;
    @BindView(R.id.Title)
    TextView eventTitle;
    @BindView(R.id.Time)
    TextView eventTime;
    @BindView(R.id.Date)
    TextView eventDate;

    public static RecyclerView recycleview;

    Event shownEvent = new Event();

    String location,title,date,time;
    public static List<Song> songlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        songlist = new ArrayList<Song>();
        Intent receiveintent = getIntent();
        if(receiveintent.hasExtra("event")){
            System.out.println("IN EVENT ACTIVITY:");
            Parcelable parcel = receiveintent.getParcelableExtra("event");
            Event e = Parcels.unwrap(parcel);
            System.out.println(e.title);
            this.shownEvent=e;

            songlist=shownEvent.songs;

            setContentView(R.layout.activity_show_event);
            ButterKnife.bind(this);
            eventTitle.setText(shownEvent.title);
            eventLocation.setText(shownEvent.location);
            eventTime.setText(shownEvent.time);
            eventDate.setText(shownEvent.date);
        }
        else {
            setContentView(R.layout.activity_show_event);
            ButterKnife.bind(this);
        }

        recycleview = (RecyclerView) findViewById(R.id.fragment4);
        recycleview.getAdapter().notifyDataSetChanged();


        receiveintent = getIntent();
        if(receiveintent.hasExtra("event")){
            Parcelable parcel = receiveintent.getParcelableExtra("event");
            Event e = Parcels.unwrap(parcel);
            this.shownEvent=e;
            eventTitle.setText(shownEvent.title);
            eventLocation.setText(shownEvent.location);
            eventTime.setText(shownEvent.time);
            eventDate.setText(shownEvent.date);
            songlist=shownEvent.songs;
        }
        if(receiveintent.hasExtra("previous_event")){
            Parcelable parcel = receiveintent.getParcelableExtra("previous_event");
            Event e = Parcels.unwrap(parcel);
            this.shownEvent=e;
            eventTitle.setText(shownEvent.title);
            eventLocation.setText(shownEvent.location);
            eventTime.setText(shownEvent.time);
            eventDate.setText(shownEvent.date);
            songlist=shownEvent.songs;
        }





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
    public void onResume(){
        super.onResume();
        siteUrl = "https://hymnary.org/text/";
        url = new ArrayList<String>();

    }

    @Override
    public void onListFragmentInteraction(Song item) {
        url = new ArrayList<>();
        siteUrl = "https://hymnary.org/text/" + item.reference;
        System.out.println("URL:"+siteUrl);
        (new ParseURL()).execute(new String[]{siteUrl});

    }

    private class ParseURL extends AsyncTask<String,Void,String>

    {

        @Override
        protected String doInBackground (String[]strings){
            //StringBuffer buffer = new StringBuffer();

            try {
                System.out.println(strings[0]);
                Document doc = Jsoup.connect(strings[0]).userAgent("Chrome").timeout(50000000).get();
                Elements links = doc.select("a[href]");
                for (Element link : links) {

                    System.out.println("in the try statement");
                    if (link.text().equals("PDF")) {
                        String linkHref = link.attr("href");
                        String linkref = link.ownText();
                        //buffer.append("Data [" + linkHref + "]");
                        url.add(linkHref);
                        break;
                    }

                }

            } catch (final java.net.SocketTimeoutException e){
                System.out.println("You caught it!");

            } catch (Throwable t) {
                t.printStackTrace();
            }
            return "";
        }


        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            Intent intent = new Intent(ShowEventActivity.this, PdfScreen.class);
            if(!url.isEmpty()) {
                intent.putExtra("n1", url.get(0));
                startActivity(intent);
            }
            else {
                System.out.println("Array is empty");
            }
        }

    }
}
