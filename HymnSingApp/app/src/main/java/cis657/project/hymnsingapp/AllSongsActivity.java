package cis657.project.hymnsingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cis657.project.hymnsingapp.dummy.DummyContent;

public class AllSongsActivity extends AppCompatActivity implements SongsFragment.OnListFragmentInteractionListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);




    }

            @Override
    public void onListFragmentInteraction(DummyContent.DummySong item) {
        System.out.println("Interact!");
        Intent toDetails = new Intent(this, PdfScreen.class);
        String t = "/media/fetch/146838";
        toDetails.putExtra("n1",t);
        startActivity(toDetails);
    }
}

