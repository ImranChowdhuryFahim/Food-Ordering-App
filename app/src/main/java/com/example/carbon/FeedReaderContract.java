package com.example.carbon;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "cart";
        public static final String COLUMN_NAME_TITLE = "Catagory";
        public static final String COLUMN_NAME_TITLE1 = "Name";
        public static final String COLUMN_NAME_TITLE2 = "Price";
        public static final String COLUMN_NAME_TITLE3 = "Quantity";
    }
}
