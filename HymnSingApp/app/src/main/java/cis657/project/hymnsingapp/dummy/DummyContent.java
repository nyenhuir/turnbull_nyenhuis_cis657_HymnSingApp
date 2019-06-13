package cis657.project.hymnsingapp.dummy;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cis657.project.hymnsingapp.AllSongsActivity;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public static ArrayList<String> titles = new ArrayList<String>();

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummySong> ITEMS = new ArrayList<DummySong>();

    public static void sendList(ArrayList<String> t){
       titles.add(t.get(1));


    }


    static {
            DummySong ds;
            ds = new DummySong(titles.get(0), "fgsdfgsdfgdsfgsdfgsdfg");
            ITEMS.add(ds);



    }
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummySong> ITEM_MAP = new HashMap<String, DummySong>();

    private static final int COUNT = 25;




    /**
     * A dummy item representing a piece of content.
     */
    public static class DummySong {
        public String title;
        public String url;
        public int content;

        public DummySong(String title, String url) {
            this.title = title;
            this.url = url;

        }

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        @Override
        public String toString() {
            return url;
        }
    }
}
