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
    static {
        DateTime now = DateTime.now();
        addItem(new EventItem("Hymnsing1", "CHURCH 1", now.minusDays(1)));
        addItem(new EventItem("Hymnsing2", "CHURCH 2", now.minusDays(1)));
        addItem(new EventItem("Hymnsing3", "CHURCH 3", now.plusDays(1)));
        addItem(new EventItem("Hymnsing4", "CHURCH 4", now.plusDays(1)));
    }


    public static void addItem(EventItem item) {
        ITEMS.add(item);
    }

    public static class EventItem {
        public final String name;
        public final String location;
        public final DateTime timestamp;

        public EventItem(String name, String location, DateTime timestamp) {
            this.name = name;
            this.location = location;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "(" + this.name + "," + this.location + ")";
        }
    }
}
