package com.example.hackupc;

import android.provider.BaseColumns;

public class ProductoContract {
    public static abstract class ProductoEntry implements BaseColumns {
        public static final String TABLE_NAME ="products";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String TYPE = "tipo";
    }
}
