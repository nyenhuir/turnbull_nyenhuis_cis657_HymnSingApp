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


public class MainActivity extends AppCompatActivity {

    EditText RespText=null;
    ArrayList<String> url = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtUrl = (EditText) findViewById(R.id.edtURL);
        Button btnGo = (Button) findViewById(R.id.btnGo);
        RespText = (EditText) findViewById(R.id.edtResp);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String siteUrl = edtUrl.getText().toString();
                (new ParseURL()).execute(new String[]{siteUrl});


            }
        });

    }



    private class ParseURL extends AsyncTask<String,Void,String>

    {

        @Override
        protected String doInBackground (String[]strings){
        StringBuffer buffer = new StringBuffer();

        try {
            Document doc = Jsoup.connect(strings[0]).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                if (link.text().equals("PDF")) {
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
        RespText.setText(s);
        Intent intent = new Intent(MainActivity.this, PdfScreen.class);
        intent.putExtra("n1", url.get(0));
        startActivity(intent);
    }

    }

}


