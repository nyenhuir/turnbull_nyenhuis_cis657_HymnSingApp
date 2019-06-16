package cis657.project.hymnsingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ViewSongsActivity extends AppCompatActivity
        implements SongFragment.OnListFragmentInteractionListener{

    Button homeButton;
    Button eventButton;
    Button songButton;
    Button bulletinButton;

    String siteUrl = "https://hymnary.org/text/";
    ArrayList<String> url = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);
        homeButton = (Button) findViewById(R.id.homeButton);
        eventButton = (Button) findViewById(R.id.organizationButton);
        songButton = (Button) findViewById(R.id.songButton);
        bulletinButton = (Button) findViewById(R.id.bulletinButton);


        homeButton.setOnClickListener(y -> {

            Intent intent = new Intent();
            intent.putExtra("next","home");
            //Returns to main
            setResult(HomeScreenActivity.SONG_RESULT,intent);
            //Exits window
            finish();
        });

        eventButton.setOnClickListener(y -> {
            Intent intent = new Intent();
            intent.putExtra("next","event");
            //Returns to main
            setResult(HomeScreenActivity.SONG_RESULT,intent);
            //Exits window
            finish();
        });

        songButton.setOnClickListener(y -> {

        });

        bulletinButton.setOnClickListener(y -> {
            Intent intent = new Intent();
            intent.putExtra("next","bulletin");
            //Returns to main
            setResult(HomeScreenActivity.SONG_RESULT,intent);
            //Exits window
            finish();
        });




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
            Intent intent = new Intent(ViewSongsActivity.this, PdfScreen.class);
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
