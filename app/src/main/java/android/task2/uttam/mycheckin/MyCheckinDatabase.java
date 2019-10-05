// database to store for Mycheckins app
package android.task2.uttam.mycheckin;

public class MyCheckinDatabase {
    public static final class MyCheckinTable {
        public static final String NAME = "MyCheckins";
        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String DETAIL = "detail";
            public static final String RESTAURANT = "place";
            public static final String LOCATION = "location";

        }
    }
}
