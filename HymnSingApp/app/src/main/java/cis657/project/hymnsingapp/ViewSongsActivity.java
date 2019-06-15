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


    ArrayList<String> url = new ArrayList<String>();
    String siteUrl = "https://hymnary.org/text/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);



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
