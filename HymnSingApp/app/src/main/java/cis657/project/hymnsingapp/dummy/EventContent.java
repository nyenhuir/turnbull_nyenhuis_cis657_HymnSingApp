package cis657.project.hymnsingapp.dummy;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class EventContent {
    public static final List<EventItem> ITEMS = new ArrayList<EventItem>();
//    static {
//        DateTime now = DateTime.now();
//        addItem(new EventItem("Hymnsing1", "CHURCH 1", "CHURCH 1","CHURCH 1f"));
//        addItem(new EventItem("Hymnsing1", "CHURCH 1", "CHURCH 1","CHURCH 1f"));
//        addItem(new EventItem("Hymnsing1", "CHURCH 1", "CHURCH 1","CHURCH 1f"));
//        addItem(new EventItem("Hymnsing1", "CHURCH 1", "CHURCH 1","CHURCH 1f"));
//    }

    public static void addItem(EventItem item) {
        ITEMS.add(item);
    }


    public static class EventItem {
        public final String name,location,time,date;

        public EventItem(String name, String location, String time, String date) {
            this.name = name;
            this.location = location;
            this.time = time;
            this.date=date;
        }



        @Override
        public String toString() {
            return "(" + this.name + "," + this.location + ")";
        }
    }
}
