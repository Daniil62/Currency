package ru.job4j.currency.data_base;

public class DbSchema {
    public static final class EurTable {
        public static final String TAB_NAME = "EUR_TABLE";
        public static final class Cols {
            public static final String CURRENCY = "EUR_CURRENCY";
            public static final String NAME = "EUR_NAME";
            public static final String DATE = "EUR_DATE";
            public static final String RATE = "EUR_RATE";
            public static final String MOVE = "EUR_MOVE";
        }
    }
    public static final class RubTable {
        public static final String TAB_NAME = "RUB_TABLE";
        public static final class Cols {
            public static final String CURRENCY = "RUB_CURRENCY";
            public static final String NAME = "RUB_NAME";
            public static final String DATE = "RUB_DATE";
            public static final String RATE = "RUB_RATE";
            public static final String MOVE = "RUB_MOVE";
        }
    }
    public static final class UsdTable {
        public static final String TAB_NAME = "USD_TABLE";
        public static final class Cols {
            public static final String CURRENCY = "USD_CURRENCY";
            public static final String NAME = "USD_NAME";
            public static final String DATE = "USD_DATE";
            public static final String RATE = "USD_RATE";
            public static final String MOVE = "USD_MOVE";
        }
    }
}
