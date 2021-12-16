package ru.sfedu.mypack;

public class Constants {

    public static final String CSV_CARPET = "CARPET_CSV";
    public static final String CSV_CHAIR = "CHAIR_CSV";
    public static final String CSV_CUSTOMER = "CUSTOMER_CSV";
    public static final String CSV_ORDER = "ORDER_CSV";
    public static final String CSV_PRODUCT = "PRODUCT_CSV";
    public static final String CSV_TABLE = "TABLE_CSV";

    public static final String XML_CARPET = "CARPET_XML";
    public static final String XML_CHAIR = "CHAIR_XML";
    public static final String XML_CUSTOMER = "CUSTOMER_XML";
    public static final String XML_ORDER = "ORDER_XML";
    public static final String XML_PRODUCT = "PRODUCT_XML";
    public static final String XML_TABLE = "TABLE_XML";

    public static final String NOT_ID = "Object[%d] is not exists";

    public static final String PRODUCT_CONST = "product";
    public static final String CARPET_CONST = "carpet";
    public static final String CHAIR_CONST = "chair";
    public static final String TABLE_CONST = "table";

    public static final String GET_CUSTOMER_ORDERS = "getCustomerOrders";
    public static final String GET_ALL_ORDERS = "getAllOrders";
    public static final String GET_SUCCESS_ORDERS = "getSuccessOrders";
    public static final String GET_UNSUCCESS_ORDERS = "getUnsuccessOrders";
    public static final String GET_PRODUCT_LIST = "getProductList";
    public static final String FILTER_PRODUCT_BY_CATEGORY = "filterProductByCategory";

    public static final String MONGO_DATABASE_NAME = "LoftShop";
    public static final String ACTOR_NAME = "System";
    public static final String MONGO_COLLECTION_NAME = "HistoryCollection";
    public static final String MONGO_DATE = " yyyy.MM.dd HH:mm:ss";
    public static final String MONGO_CONNECT = "mongodb://localhost:27017";

    public static final String ADD = "Add %s";
    public static final String DELETE = "Delete %s";
    public static final String UPDATE ="Update %s";
    public static final String GET_ID = "Get %s";

    public static final String DB_USER = "db_user";
    public static final String DB_PASS = "db_pass";
    public static final String DB_CONNECT = "db_url";

    public static final String PRODUCT_CREATE = "PRODUCT_CREATE";
    public static final String CARPET_CREATE = "CARPET_CREATE";
    public static final String CHAIR_CREATE = "CHAIR_CREATE";
    public static final String TABLE_CREATE = "TABLE_CREATE";
    public static final String CUSTOMER_CREATE = "CUSTOMER_CREATE";
    public static final String ORDER_CREATE = "ORDER_CREATE";

    public static final String SELECT_ALL = "SELECT * FROM %s";

    public static final String PRODUCT_ID = "Id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_PRICE = "price";

    public static final String CUSTOMER_ID = "Id";
    public static final String CUSTOMER_NAME = "name";
    public static final String CUSTOMER_ADDRESS = "address";

    public static final String ORDER_ID = "Id";
    public static final String ORDER_CUSTOMER_ID = "customer";
    public static final String ORDER_PRODUCT_ID = "product";
    public static final String ORDER_PRODUCT_PRICE = "productPrice";
    public static final String ORDER_DELIVERY_PRICE = "deliveryPrice";
    public static final String ORDER_IS_FINISHED = "isFinished";

    public static final String CARPET_WOOL = "wool";
    public static final String CARPET_WEIGHT = "weight";
    public static final String CARPET_LENGTH = "length";
    public static final String CARPET_COLOUR = "colour";

    public static final String CHAIR_WOOD = "wood";
    public static final String CHAIR_HEIGHT = "height";
    public static final String CHAIR_WEIGHT = "weight";
    public static final String CHAIR_CATEGORY = "leg";

    public static final String TABLE_WOOD = "wood";
    public static final String TABLE_HEIGHT = "height";
    public static final String TABLE_WEIGHT = "weight";
    public static final String TABLE_LENGTH = "length";
    public static final String TABLE_CATEGORY = "leg";

    public static final String SELECT_ALL_ID = "SELECT * FROM %s WHERE Id = %d";

    public static final String ADD_PRODUCT = "Add product";
    public static final String INSERT_PRODUCT = "INSERT INTO PRODUCT(name, category, price) VALUES ('%s', '%s', %.0f);";

    public static final String PRODUCT_DELETE = "Delete product";
    public static final String DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE Id = %d;";

    public static final String PRODUCT_UPDATE = "Update product";
    public static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET name = '%s', category = '%s', price = %.0f WHERE id = %d;";


    public static final String ADD_CUSTOMER = "Add customer";
    public static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER(name, address) VALUES ('%s', '%s');";

    public static final String CUSTOMER_DELETE = "Delete customer";
    public static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE Id = %d;";

    public static final String CUSTOMER_UPDATE = "Update customer";
    public static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET name = '%s', address = '%s' WHERE id = %d;";


    public static final String ADD_ORDER = "Add order";
    public static final String INSERT_ORDER = "INSERT INTO \"ORDER\" (customer, product, productPrice, deliveryPrice, isFinished) VALUES (%d, %d, %.0f, %.0f, BOOLEAN);";

    public static final String ORDER_DELETE = "Delete order";
    public static final String DELETE_ORDER = "DELETE FROM \"ORDER\" WHERE Id = %d;";

    public static final String ORDER_UPDATE = "Update order";
    public static final String UPDATE_ORDER = "UPDATE \"ORDER\" SET customer = %d, product = %d, productPrice = %.0f, deliveryPrice = %.0f, isFinished = BOOLEAN WHERE id = %d;";

    public static final String ADD_CARPET = "Add carpet";
    public static final String INSERT_CARPET = "INSERT INTO CARPET(name, category, price, wool, weight, length, colour) VALUES ('%s', '%s', %.0f, '%s', %.0f, %.0f, '%s');";

    public static final String CARPET_DELETE = "Delete carpet";
    public static final String DELETE_CARPET = "DELETE FROM CARPET WHERE Id = %d;";

    public static final String CARPET_UPDATE = "Update carpet";
    public static final String UPDATE_CARPET = "UPDATE CARPET SET name = '%s', category = '%s', price = %.0f, wool = '%s', weight = %.0f, length = %.0f, colour = '%s' WHERE id = %d;";


    public static final String ADD_CHAIR = "Add chair";
    public static final String INSERT_CHAIR = "INSERT INTO CHAIR(name, category, price, wood, height, weight, leg) VALUES ('%s', '%s', %.0f, '%s', %.0f, %.0f, '%s');";

    public static final String CHAIR_DELETE = "Delete chair";
    public static final String DELETE_CHAIR = "DELETE FROM CHAIR WHERE Id = %d;";

    public static final String CHAIR_UPDATE = "Update chair";
    public static final String UPDATE_CHAIR = "UPDATE CHAIR SET name = '%s', category = '%s', price = %.0f, wood = '%s', height = %.0f, weight = %.0f, leg = '%s' WHERE id = %d;";


    public static final String ADD_TABLE = "Add table";
    public static final String INSERT_TABLE = "INSERT INTO \"TABLE\"(name, category, price, wood, height, weight, length, leg) VALUES ('%s', '%s', %.0f, '%s', %.0f, %.0f, %.0f, '%s');";

    public static final String TABLE_DELETE = "Delete table";
    public static final String DELETE_TABLE = "DELETE FROM \"TABLE\" WHERE Id = %d;";

    public static final String TABLE_UPDATE = "Update table";
    public static final String UPDATE_TABLE = "UPDATE \"TABLE\" SET name = '%s', category = '%s', price = %.0f, wood = '%s', height = %.0f, weight = %.0f, length = %.0f, leg = '%s' WHERE id = %d;";

    public static final String DROP_NAME = "Drop TABLE";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS \"%s\" CASCADE";

    public static final String CREATE_PRODUCT = "create table if not exists PRODUCT\n" +
            "(\n" +
            "   ID BIGINT auto_increment primary key,\n" +
            "   NAME           VARCHAR(255),\n" +
            "   CATEGORY       VARCHAR(255),\n" +
            "   PRICE          DOUBLE PRECISION\n" +
            ");";

    public static final String CREATE_CARPET = "create table if not exists CARPET\n" +
            "(\n" +
            "    ID BIGINT auto_increment primary key,\n" +
            "    NAME           VARCHAR(40),\n" +
            "    CATEGORY       VARCHAR(40),\n" +
            "    PRICE          DOUBLE PRECISION,\n" +
            "    WOOL           VARCHAR(40),\n" +
            "    WEIGHT         DOUBLE PRECISION,\n" +
            "    LENGTH         DOUBLE PRECISION,\n" +
            "    COLOUR         VARCHAR(40)\n" +
            ");";

    public static final String CREATE_CHAIR = "create table if not exists CHAIR\n" +
            "(\n" +
            "    ID BIGINT auto_increment primary key,\n" +
            "    NAME           VARCHAR(40),\n" +
            "    CATEGORY       VARCHAR(40),\n" +
            "    PRICE          DOUBLE PRECISION,\n" +
            "    WOOD           VARCHAR(40),\n" +
            "    HEIGHT         DOUBLE PRECISION,\n" +
            "    WEIGHT         DOUBLE PRECISION,\n" +
            "    LEG            VARCHAR(40)\n" +
            ");";

    public static final String CREATE_TABLE = "create table if not exists \"TABLE\"\n" +
            "(\n" +
            "    ID BIGINT auto_increment primary key,\n" +
            "    NAME           VARCHAR(40),\n" +
            "    CATEGORY       VARCHAR(40),\n" +
            "    PRICE          DOUBLE PRECISION,\n" +
            "    WOOD           VARCHAR(40),\n" +
            "    HEIGHT         DOUBLE PRECISION,\n" +
            "    WEIGHT         DOUBLE PRECISION,\n" +
            "    LENGTH         DOUBLE PRECISION,\n" +
            "    LEG            VARCHAR(40)\n" +
            ");";

    public static final String CREATE_CUSTOMER = "create table if not exists CUSTOMER\n" +
            "(\n" +
            "    ID BIGINT auto_increment primary key,\n" +
            "    NAME           VARCHAR(255),\n" +
            "    ADDRESS        VARCHAR(255)\n" +
            ");";

    public static final String CREATE_ORDER = "create table if not exists \"ORDER\"\n" +
            "(\n" +
            "    ID BIGINT auto_increment primary key,\n" +
            "    CUSTOMER       BIGINT,\n" +
            "    PRODUCT        BIGINT,\n" +
            "    PRODUCTPRICE   DOUBLE PRECISION,\n" +
            "    DELIVERYPRICE  DOUBLE PRECISION,\n" +
            "    ISFINISHED     BOOLEAN\n" +
            ");";

}
