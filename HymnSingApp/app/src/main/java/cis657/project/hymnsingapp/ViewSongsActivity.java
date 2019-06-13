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

    String siteUrl = "https://hymnary.org/text/";
    ArrayList<String> url = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);



    }


    @Override
    public void onListFragmentInteraction(Song item) {
        siteUrl+=item.reference;
        System.out.println("URL:"+siteUrl);
        (new ParseURL()).execute(new String[]{siteUrl});
    }

    private class ParseURL extends AsyncTask<String,Void,String>

    {

        @Override
        protected String doInBackground (String[]strings){
            StringBuffer buffer = new StringBuffer();

            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements links = doc.select("a[ref]");
                for (Element link : links) {

                    System.out.println(link.toString());
                    if (link.text().contains("asfdljk;")) {
                        String linkHref = link.attr("href");
                        String linkref = link.ownText();
                        buffer.append("Data [" + linkHref + "]");
                        url.add(linkHref);
                        break;
                    }
                }

            } catch (Throwable t) {
                t.printStackTrace();
            }
            return buffer.toString();
        }


        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            Intent intent = new Intent(ViewSongsActivity.this, PdfScreen.class);
            if(url.get(0)!=null)
            intent.putExtra("n1", url.get(0));
            startActivity(intent);
        }

    }
}
