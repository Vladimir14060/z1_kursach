package ru.sfedu.mypack.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mypack.Constants;
import ru.sfedu.mypack.Main;
import ru.sfedu.mypack.model.Result;
import ru.sfedu.mypack.model.beans.*;
import ru.sfedu.mypack.model.enums.EnumCategory;
import ru.sfedu.mypack.model.enums.EnumColourWool;
import ru.sfedu.mypack.model.enums.EnumLeg;
import ru.sfedu.mypack.model.enums.EnumResult;
import ru.sfedu.mypack.model.history.HistoryContent;
import ru.sfedu.mypack.utils.ConfigurationUtil;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

import static ru.sfedu.mypack.utils.HistoryUtil.saveToLog;

public class DataProviderJdbc implements IDataProvider {

    public Connection connection;
    private static final Logger log = LogManager.getLogger(Main.class);

    @Override
    public Boolean createOrderProduct(long orderId, long id) {
        try {
            log.info("createOrderProduct");
            Order order = getOrderById(orderId).getData();
            log.debug(order);
            Customer customer = getCustomerById(id).getData();
            log.debug(customer);
            if (order.getId() == 0){
                log.error("Order == 0");
                return false;
            }
            if (customer.getId() == 0){
                log.error("Customer == 0");
                return false;
            }
            order.setCustomer(customer);
            updateOrder(order);
            log.info("createOrderProduct - success");
            return true;
        } catch (Exception e) {
            log.info("createOrderProduct - unsuccess");
            log.error(e);
            return false;
        }

    }

    @Override
    public Result<Customer> findCustomer(long id) {
        return new Result<>(EnumResult.Success, getCustomerById(id).getData());
    }

    @Override
    public Result<List<Order>> getCustomerOrders(long id) {
        try {
            log.info("getCustomerOrders");
            List<Order> objects = getOrder();
            log.debug(objects);
            List<Order> customersList = objects.stream().filter(order -> order.getCustomer().getId() == id).collect(Collectors.toList());
            log.info("getCustomerOrders - success");
            return new Result(EnumResult.Success, customersList);
        } catch (Exception e) {
            log.info("getCustomerOrders - unsuccess");
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Order>> getAllOrders(String typeOfOrder) {
        try {
            log.info("getAllOrders");
            List<Order> orderList = getOrder();
            log.debug(orderList);
            switch (typeOfOrder.toLowerCase()) {
                case "success":
                    log.debug("Order success" + getSuccessedOrder());
                    return new Result<>(EnumResult.Success, getSuccessedOrder().getData());
                case "unsuccess":
                    log.debug("Order unsuccess" + getUnsuccessedOrder());
                    return new Result<>(EnumResult.Success, getUnsuccessedOrder().getData());
                case "all":
                    log.debug("Orderr all" + orderList);
                    return new Result<>(EnumResult.Success, orderList);
                default:
                    log.error("getAllOrders - unsuccess");
                    return new Result<>(EnumResult.Error);
            }
        } catch (Exception e) {
            log.info("getAllOrders - unsuccess");
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Order>> getSuccessedOrder() {
        try {
            List<Order> orderList = getOrder();
            List<Order> newOrderList = orderList.stream().filter(Order::isFinished).collect(Collectors.toList());
            return new Result<>(EnumResult.Success, newOrderList);
        } catch (Exception e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Order>> getUnsuccessedOrder() {
        try {
            List<Order> orderList = getOrder();
            List<Order> newOrderList = orderList.stream().filter(order -> !order.isFinished()).collect(Collectors.toList());
            return new Result<>(EnumResult.Success, newOrderList);
        } catch (Exception e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Product>> getProductsList(EnumCategory category) {
        try {
            log.info("getProductsList");
            if (category == null) {
                log.error("Category == null");
                return new Result<>(EnumResult.Error);
            }
            List<Product> productList = getProduct();
            log.debug(productList);
            switch (category) {
                case NONE -> {
                    log.debug("ProductsList" + productList);
                    return new Result<>(EnumResult.Success, productList);
                }
                default -> {
                    log.debug("ProductsList" + filterProductByCategory(category));
                    return new Result(EnumResult.Success, filterProductByCategory(category));
                }
            }
        } catch (Exception e) {
            log.info("getProductsList - unsuccess");
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Product>> filterProductByCategory(EnumCategory category) {
        try {
            List<Product> productList = getProduct();
            List<Product> sortedList = productList.stream().filter(product -> product.getCategory() == category).collect(Collectors.toList());
            return new Result<>(EnumResult.Success, sortedList);
        } catch (Exception e) {
            log.error(e);
            return new Result(EnumResult.Error);
        }
    }

    @Override
    public Boolean addOrder(Order order) {
        try {
            boolean exist = execute(order,String.format(
                    Constants.INSERT_ORDER,
                    order.getCustomer(),
                    order.getProduct(),
                    order.getProductPrice(),
                    order.getDeliveryPrice(),
                    order.isFinished()),Constants.ADD_ORDER).getResultEnum() == EnumResult.Success;
            return exist;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Boolean deleteOrder(long id) {
        try {
            boolean a = execute(null,String.format(Constants.DELETE_ORDER, id),Constants.ORDER_DELETE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Boolean updateOrder(Order order) {
        try {
            boolean a = execute(order,String.format(Constants.UPDATE_ORDER,
                    order.getCustomer().getId(),
                    order.getProduct().getId(),
                    order.getProductPrice(),
                    order.getDeliveryPrice(),
                    order.isFinished(),
                    order.getId()),Constants.ORDER_UPDATE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Result<Order> getOrderById(long id) {
        try {
            ResultSet set = setResById(Order.class, id);
            log.debug(set);
            if (set != null && set.next()) {
                Order order = new Order();
                order.setId(set.getLong(Constants.ORDER_ID));
                order.setCustomer(getCustomerById(set.getLong(Constants.ORDER_CUSTOMER_ID)).getData());
                order.setProduct(getProductById(set.getLong(Constants.ORDER_PRODUCT_ID)).getData());
                order.setProductPrice(set.getDouble(Constants.ORDER_PRODUCT_PRICE));
                order.setDeliveryPrice(set.getDouble(Constants.ORDER_DELIVERY_PRICE));
                order.setFinished(set.getBoolean(Constants.ORDER_IS_FINISHED));
                return new Result<>(EnumResult.Success, order);
            } else {
                return new Result<>(EnumResult.Error);
            }
        } catch (SQLException e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }


    @Override
    public Boolean addProduct(Product product) {
        try {
            boolean exist = execute(product,String.format(
                    Constants.INSERT_PRODUCT,
                    product.getName(),
                    product.getCategory(),
                    product.getPrice()),Constants.ADD_PRODUCT).getResultEnum() == EnumResult.Success;
            return exist;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Boolean deleteProduct(long id) {
        try {
            boolean a = execute(null,String.format(Constants.DELETE_PRODUCT, id),Constants.PRODUCT_DELETE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Boolean updateProduct(Product product) {
        try {
            boolean a = execute(product,String.format(Constants.UPDATE_PRODUCT,
                    product.getName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getId()),Constants.PRODUCT_UPDATE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Result<Product> getProductById(long id) {
        try {
            ResultSet set = setResById(Product.class, id);
            log.debug(set);
            if (set != null && set.next()) {
                Product product = new Product();
                setBasicProduct(set, product);
                return new Result<>(EnumResult.Success, product);
            } else {
                return new Result<>(EnumResult.Error);
            }
        } catch (SQLException e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }


    @Override
    public Boolean addCustomer(Customer customer) {
        try {
            boolean exist = execute(customer,String.format(
                    Constants.INSERT_CUSTOMER,
                    customer.getName(),
                    customer.getAddress()),Constants.ADD_CUSTOMER).getResultEnum() == EnumResult.Success;
            return exist;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Boolean deleteCustomer(long id) {
        try {
            boolean a = execute(null,String.format(Constants.DELETE_CUSTOMER, id),Constants.CUSTOMER_DELETE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        try {
            boolean a = execute(customer,String.format(Constants.UPDATE_CUSTOMER,
                    customer.getName(),
                    customer.getAddress(),
                    customer.getId()),Constants.CUSTOMER_UPDATE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Result<Customer> getCustomerById(long id) {
        try {
            ResultSet set = setResById(Customer.class, id);
            log.debug(set);
            if (set != null && set.next()) {
                Customer customer = new Customer();
                customer.setId(set.getLong(Constants.CUSTOMER_ID));
                customer.setName(set.getString(Constants.CUSTOMER_NAME));
                customer.setAddress(set.getString(Constants.CUSTOMER_ADDRESS));
                return new Result<>(EnumResult.Success, customer);
            } else {
                return new Result<>(EnumResult.Error);
            }
        } catch (SQLException e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }


    @Override
    public Boolean addCarpet(Carpet carpet) {
        try {
            boolean exist = execute(carpet,String.format(
                    Constants.INSERT_CARPET,
                    carpet.getName(),
                    carpet.getCategory(),
                    carpet.getPrice(),
                    carpet.getWool(),
                    carpet.getWeight(),
                    carpet.getLength(),
                    carpet.getColour()),Constants.ADD_CARPET).getResultEnum() == EnumResult.Success;
            return exist;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Boolean deleteCarpet(long id) {
        try {
            boolean a = execute(null,String.format(Constants.DELETE_CARPET, id),Constants.CARPET_DELETE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Boolean updateCarpet(Carpet carpet) {
        try {
            boolean a = execute(carpet,String.format(Constants.UPDATE_CARPET,
                    carpet.getName(),
                    carpet.getCategory(),
                    carpet.getPrice(),
                    carpet.getWool(),
                    carpet.getWeight(),
                    carpet.getLength(),
                    carpet.getColour(),
                    carpet.getId()),Constants.CARPET_UPDATE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Result<Carpet> getCarpetById(long id) {
        try {
            ResultSet set = setResById(Order.class, id);
            log.debug(set);
            if (set != null && set.next()) {
                Carpet carpet = new Carpet();
                setBasicProduct(set, carpet);
                carpet.setWool(set.getString(Constants.CARPET_WOOL));
                carpet.setWeight(set.getDouble(Constants.CARPET_WEIGHT));
                carpet.setLength(set.getDouble(Constants.CARPET_LENGTH));
                carpet.setColour(EnumColourWool.valueOf(set.getString(Constants.CARPET_COLOUR)));
                return new Result<>(EnumResult.Success, carpet);
            } else {
                return new Result<>(EnumResult.Error);
            }
        } catch (SQLException e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }


    @Override
    public Boolean addChair(Chair chair) {
        try {
            boolean exist = execute(chair,String.format(
                    Constants.INSERT_CHAIR,
                    chair.getName(),
                    chair.getCategory(),
                    chair.getPrice(),
                    chair.getWood(),
                    chair.getHeight(),
                    chair.getWeight(),
                    chair.getLeg()),Constants.ADD_CHAIR).getResultEnum() == EnumResult.Success;
            return exist;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Boolean deleteChair(long id) {
        try {
            boolean a = execute(null,String.format(Constants.DELETE_CHAIR, id),Constants.CHAIR_DELETE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Boolean updateChair(Chair chair) {
        try {
            boolean a = execute(chair,String.format(Constants.UPDATE_CHAIR,
                    chair.getName(),
                    chair.getCategory(),
                    chair.getPrice(),
                    chair.getWood(),
                    chair.getHeight(),
                    chair.getWeight(),
                    chair.getLeg(),
                    chair.getId()),Constants.CHAIR_UPDATE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Result<Chair> getChairById(long id) {
        try {
            ResultSet set = setResById(Chair.class, id);
            log.debug(set);
            if (set != null && set.next()) {
                Chair chair = new Chair();
                setBasicProduct(set, chair);
                chair.setWood(set.getString(Constants.CHAIR_WOOD));
                chair.setHeight(set.getDouble(Constants.CHAIR_HEIGHT));
                chair.setWeight(set.getDouble(Constants.CHAIR_WEIGHT));
                chair.setLeg(EnumLeg.valueOf(set.getString(Constants.CHAIR_CATEGORY)));
                return new Result<>(EnumResult.Success, chair);
            } else {
                return new Result<>(EnumResult.Error);
            }
        } catch (SQLException e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }


    @Override
    public Boolean addTable(Table table) {
        try {
            boolean exist = execute(table,String.format(
                    Constants.INSERT_TABLE,
                    table.getName(),
                    table.getCategory(),
                    table.getPrice(),
                    table.getWood(),
                    table.getHeight(),
                    table.getWeight(),
                    table.getLength(),
                    table.getLeg()),Constants.ADD_TABLE).getResultEnum() == EnumResult.Success;
            return exist;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Boolean deleteTable(long id) {
        try {
            boolean a = execute(null,String.format(Constants.DELETE_TABLE, id),Constants.TABLE_DELETE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Boolean updateTable(Table table) {
        try {
            boolean a = execute(table,String.format(Constants.UPDATE_TABLE,
                    table.getName(),
                    table.getCategory(),
                    table.getPrice(),
                    table.getWood(),
                    table.getHeight(),
                    table.getLength(),
                    table.getWeight(),
                    table.getLeg(),
                    table.getId()),Constants.TABLE_UPDATE).getResultEnum() == EnumResult.Success;
            if (a) return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public Result<Table> getTableById(long id) {
        try {
            ResultSet set = setResById(Table.class, id);
            log.debug(set);
            if (set != null && set.next()) {
                Table table = new Table();
                setBasicProduct(set, table);
                table.setWood(set.getString(Constants.TABLE_WOOD));
                table.setHeight(set.getDouble(Constants.TABLE_HEIGHT));
                table.setLength(set.getDouble(Constants.TABLE_LENGTH));
                table.setWeight(set.getDouble(Constants.TABLE_WEIGHT));
                table.setLeg(EnumLeg.valueOf(set.getString(Constants.TABLE_CATEGORY)));
                return new Result<>(EnumResult.Success, table);
            } else {
                return new Result<>(EnumResult.Error);
            }
        } catch (SQLException e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    private static HistoryContent createHistoryContent(String method, Object object, EnumResult enumResult) {
        return new HistoryContent(DataProviderJdbc.class.getSimpleName(), new Date(), Constants.ACTOR_NAME, method, object, enumResult);
    }

    private Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    ConfigurationUtil.getConfigurationEntry(Constants.DB_CONNECT),
                    ConfigurationUtil.getConfigurationEntry(Constants.DB_USER),
                    ConfigurationUtil.getConfigurationEntry(Constants.DB_PASS)
            );
            return connection;
        } catch (SQLException | IOException e) {
            log.error(e);
            return null;
        }
    }

    private <T> Result execute(T lst, String key, String method) {
        EnumResult result;
        try {
            if (lst == null) {
                lst = (T) " Nothing ";
            }
            PreparedStatement statement = getConnection().prepareStatement(key);
            statement.executeUpdate();
            getConnection().close();
            result = EnumResult.Success;
        } catch (SQLException e) {
            log.error(e);
            result = EnumResult.Error;
        }
        saveToLog(createHistoryContent(method, lst, result));
        return new Result(result);
    }

    private ResultSet setResById(Class cls, long id) {
        ResultSet resultSet = getTaskRecords(String.format(Constants.SELECT_ALL_ID, cls.getSimpleName().toLowerCase(), id));
        return resultSet;
    }

    private ResultSet getTaskRecords(String key) {
        log.info(key);
        try {
            PreparedStatement statement = getConnection().prepareStatement(key);
            getConnection().close();
            return statement.executeQuery();
        } catch (SQLException e) {
            log.info(e);
        }
        return null;
    }

    private List<Product> getProduct() {
        try {
            List<Product> productList = new ArrayList<>();
            ResultSet setProduct = getTaskRecords(String.format(Constants.SELECT_ALL, "PRODUCT"));
            while (setProduct.next()) {
                Product product = new Product();
                setBasicProduct(setProduct, product);
                log.debug(product);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            log.error(e);
            return new ArrayList<>();
        }
    }

    private void setBasicProduct(ResultSet set, Product product) {
        try {
            product.setId(set.getLong(Constants.PRODUCT_ID));
            product.setName(set.getString(Constants.PRODUCT_NAME));
            product.setCategory(EnumCategory.valueOf(set.getString(Constants.PRODUCT_CATEGORY)));
            product.setPrice(set.getDouble(Constants.PRODUCT_PRICE));
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private List<Order> getOrder() {
        try {
            List<Order> orderList = new ArrayList<>();
            ResultSet setOrder = getTaskRecords(String.format(Constants.SELECT_ALL, "ORDER"));
            while (setOrder.next()) {
                Order order = new Order();
                order.setId(setOrder.getLong(Constants.ORDER_ID));
                order.setCustomer(getCustomerById(setOrder.getLong(Constants.ORDER_CUSTOMER_ID)).getData());
                order.setProduct(getProductById(setOrder.getLong(Constants.ORDER_PRODUCT_ID)).getData());
                order.setProductPrice(setOrder.getDouble(Constants.ORDER_PRODUCT_PRICE));
                order.setDeliveryPrice(setOrder.getDouble(Constants.ORDER_DELIVERY_PRICE));
                order.setFinished(setOrder.getBoolean(Constants.ORDER_IS_FINISHED));
                log.debug(order);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            log.error(e);
            return new ArrayList<>();
        }
    }

    public void createTables() {
        execute(null, String.format(Constants.CREATE_PRODUCT), Constants.PRODUCT_CREATE);
        execute(null, String.format(Constants.CREATE_CARPET), Constants.CARPET_CREATE);
        execute(null, String.format(Constants.CREATE_CHAIR), Constants.CHAIR_CREATE);
        execute(null, String.format(Constants.CREATE_TABLE), Constants.TABLE_CREATE);
        execute(null, String.format(Constants.CREATE_CUSTOMER), Constants.CUSTOMER_CREATE);
        execute(null, String.format(Constants.CREATE_ORDER), Constants.ORDER_CREATE);
    }

    public void deleteAllTables() {
        deleteRecords(Carpet.class);
        deleteRecords(Chair.class);
        deleteRecords(Table.class);
        deleteRecords(Product.class);
        deleteRecords(Customer.class);
        deleteRecords(Order.class);
    }

    public <T> void deleteRecords(Class<T> cls) {
        execute(null, String.format(Constants.DROP_TABLE, cls.getSimpleName().toUpperCase()), Constants.DROP_NAME);
        new Result<>(EnumResult.Success);
    }


}
