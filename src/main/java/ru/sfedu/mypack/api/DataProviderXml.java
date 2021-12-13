package ru.sfedu.mypack.api;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.mypack.Constants;
import ru.sfedu.mypack.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mypack.model.Result;
import ru.sfedu.mypack.model.beans.*;
import ru.sfedu.mypack.model.enums.EnumCategory;
import ru.sfedu.mypack.model.enums.EnumResult;
import ru.sfedu.mypack.model.history.HistoryContent;
import ru.sfedu.mypack.utils.ConfigurationUtil;
import ru.sfedu.mypack.utils.XmlWrapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.sfedu.mypack.utils.HistoryUtil.saveToLog;

public class DataProviderXml implements IDataProvider{

    private static final Logger log = LogManager.getLogger(Main.class);

    @Override
    public Boolean createOrderProduct(long orderId, long id) {
        try {
            Order order = getOrderById(orderId).getData();
            Customer customer = getCustomerById(id).getData();
            if (order.getId() == 0){
                return false;
            }
            if (customer.getId() == 0){
                return false;
            }
            order.setCustomer(customer);
            updateOrder(order);
            return true;
        } catch (Exception e) {
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
            String method = Constants.GET_CUSTOMER_ORDERS;
            List<Order> objects = xmlToBean(Constants.XML_PRODUCT, method);
            List<Order> customersList = objects.stream().filter(order -> order.getCustomer().getId() == id).collect(Collectors.toList());
            return new Result(EnumResult.Success, customersList);

        } catch (Exception e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Order>> getAllOrders(String typeOfOrder) {
        try {
            String method = Constants.GET_ALL_ORDERS;
            List<Order> orderList = xmlToBean(Constants.XML_PRODUCT, method);
            switch (typeOfOrder.toLowerCase()) {
                case "success":
                    return new Result<>(EnumResult.Success, getSuccessedOrder().getData());
                case "unsuccess":
                    return new Result<>(EnumResult.Success, getUnsuccessedOrder().getData());
                case "all":
                    return new Result<>(EnumResult.Success, orderList);
                default:
                    return new Result<>(EnumResult.Error);
            }
        } catch (Exception e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Order>> getSuccessedOrder() {
        try {
            String method = Constants.GET_SUCCESS_ORDERS;
            List<Order> orderList = xmlToBean(Constants.XML_PRODUCT, method);
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
            String method = Constants.GET_UNSUCCESS_ORDERS;
            List<Order> orderList = xmlToBean(Constants.XML_PRODUCT, method);
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
            if (category == null) {
                return new Result<>(EnumResult.Error);
            }
            String method = Constants.GET_PRODUCT_LIST;
            List<Product> productList = xmlToBean(Constants.XML_PRODUCT, method);
            switch (category) {
                case NONE -> {
                    return new Result<>(EnumResult.Success, productList);
                }
                default -> {
                    return new Result(EnumResult.Success, filterProductByCategory(category));
                }
            }
        } catch (Exception e) {
            log.error(e);
            return new Result<>(EnumResult.Error);
        }
    }

    @Override
    public Result<List<Product>> filterProductByCategory(EnumCategory category) {
        try {
            String method = Constants.FILTER_PRODUCT_BY_CATEGORY;
            List<Product> productList = xmlToBean(Constants.XML_PRODUCT, method);
            List<Product> sortedList = productList.stream().filter(product -> product.getCategory() == category).collect(Collectors.toList());
            return new Result<>(EnumResult.Success, sortedList);
        } catch (Exception e) {
            log.error(e);
            return new Result(EnumResult.Error);
        }
    }

    @Override
    public Boolean addOrder(Order order) {
        final String method = String.format(Constants.ADD, Order.class.getSimpleName());
        List<Order> orderList = xmlToBean(Constants.XML_ORDER, method);
        if (orderList.stream().anyMatch(o -> o.getId() == order.getId())) {
            return false;
        }
        orderList.add(order);
        return beanToXml(orderList, Constants.XML_ORDER, method) != EnumResult.Error;
    }

    @Override
    public Boolean deleteOrder(long id) {
        final String method = String.format(Constants.DELETE, Order.class.getSimpleName());
        List<Order> objects = xmlToBean(Constants.XML_ORDER, method);
        objects.removeIf(o -> o.getId() == id);
        beanToXml(objects, Constants.XML_ORDER, method);
        return true;
    }

    @Override
    public Boolean updateOrder(Order order) {
        final String method = String.format(Constants.UPDATE, Order.class.getSimpleName());
        List<Order> objects = xmlToBean(Constants.XML_ORDER, method);
        if (objects.stream().noneMatch(o -> o.getId() == order.getId())) {
            return false;
        }
        objects.removeIf(o -> o.getId() == order.getId());
        objects.add(order);
        return beanToXml(objects, Constants.XML_ORDER, method) != EnumResult.Error;
    }

    @Override
    public Result<Order> getOrderById(long id) {
        final String method = String.format(Constants.GET_ID, Order.class.getSimpleName());
        List<Order> objects = xmlToBean(Constants.XML_ORDER, method);
        return new Result<>(EnumResult.Success, objects.stream().filter(o -> o.getId() == id).findFirst().orElse(new Order()));
    }


    @Override
    public Boolean addProduct(Product product) {
        return addSharedProduct(Product.class, product);
    }

    @Override
    public Boolean deleteProduct(long id){
        return deleteSharedProduct(Product.class, id);
    }

    @Override
    public Boolean updateProduct(Product product){
        return updateSharedProduct(Product.class, product);
    }

    @Override
    public Result<Product> getProductById(long id){
        return new Result<>(EnumResult.Success, getSharedProductById(Product.class, id).orElse(new Product()));
    }


    @Override
    public Boolean addCustomer(Customer customer){
        final String method = String.format(Constants.ADD, Customer.class.getSimpleName());
        List<Customer> customers = xmlToBean(Constants.XML_CUSTOMER, method);
        if (customers.stream().anyMatch(o -> o.getId() == customer.getId())){
            return false;
        }
        customers.add(customer);
        return beanToXml(customers, Constants.XML_CUSTOMER, method) != EnumResult.Error;
    }

    @Override
    public Boolean deleteCustomer(long id){
        final String method = String.format(Constants.DELETE, Customer.class.getSimpleName());
        List<Customer> objects = xmlToBean(Constants.XML_CUSTOMER, method);
        objects.removeIf(o -> o.getId() == id);
        beanToXml(objects, Constants.XML_CUSTOMER, method);
        return true;
    }

    @Override
    public Boolean updateCustomer(Customer customer){
        final String method = String.format(Constants.UPDATE, Customer.class.getSimpleName());
        List<Customer> objects = xmlToBean(Constants.XML_CUSTOMER, method);
        if (objects.stream().noneMatch(o -> o.getId() == customer.getId())){
            return false;
        }
        objects.removeIf(o -> o.getId() == customer.getId());
        objects.add(customer);
        return beanToXml(objects, Constants.XML_CUSTOMER, method) != EnumResult.Error;
    }

    @Override
    public Result<Customer> getCustomerById(long id){
        final String method = String.format(Constants.GET_ID, Customer.class.getSimpleName());
        List<Customer> objects = xmlToBean(Constants.XML_CUSTOMER, method);
        return new Result(EnumResult.Success, objects.stream().filter(o -> o.getId() == id).findFirst().orElse(new Customer()));
    }


    @Override
    public Boolean addCarpet(Carpet carpet){
        return addSharedProduct(Carpet.class, carpet);
    }

    @Override
    public Boolean deleteCarpet(long id){
        return deleteSharedProduct(Carpet.class, id);
    }

    @Override
    public Boolean updateCarpet(Carpet carpet){
        return updateSharedProduct(Carpet.class, carpet);
    }

    @Override
    public Result<Carpet> getCarpetById(long id){
        return new Result<>(EnumResult.Success, getSharedProductById(Carpet.class, id).orElse(new Carpet()));
    }


    @Override
    public Boolean addChair(Chair chair){
        return addSharedProduct(Chair.class, chair);
    }

    @Override
    public Boolean deleteChair(long id){
        return deleteSharedProduct(Chair.class, id);
    }

    @Override
    public Boolean updateChair(Chair chair){
        return updateSharedProduct(Chair.class, chair);
    }

    @Override
    public Result<Chair> getChairById(long id){
        return new Result<>(EnumResult.Success, getSharedProductById(Chair.class, id).orElse(new Chair()));
    }


    @Override
    public Boolean addTable(Table table){
        return addSharedProduct(Table.class, table);
    }

    @Override
    public Boolean deleteTable(long id){
        return deleteSharedProduct(Table.class, id);
    }

    @Override
    public Boolean updateTable(Table table){
        return updateSharedProduct(Table.class, table);
    }

    @Override
    public Result<Table> getTableById(long id){
        return new Result<>(EnumResult.Success, getSharedProductById(Table.class, id).orElse(new Table()));
    }


    public <T extends Product> Boolean addSharedProduct(Class<T> cl, T obj){
        final String method = String.format(Constants.ADD, cl.getSimpleName());
        String classname = getClassName(cl);
        List<T> list = xmlToBean(classname, method);
        if (list.stream().anyMatch(o -> o.getId() == obj.getId())){
            return false;
        }
        list.add(obj);
        return beanToXml(list, classname, method) != EnumResult.Error;
    }

    public <T extends Product> Boolean deleteSharedProduct(Class<T> cl, long id){
        final String method = String.format(Constants.DELETE, cl.getSimpleName());
        String className = getClassName(cl);
        List<T> objects = xmlToBean(className, method);
        objects.removeIf(o -> o.getId() == id);
        beanToXml(objects, className, method);
        return true;
    }

    public <T extends Product> Boolean updateSharedProduct(Class<T> cl, T obj){
        final String method = String.format(Constants.UPDATE, cl.getSimpleName());
        String classname = getClassName(cl);
        List<T> objects = xmlToBean(classname, method);
        if (objects.stream().noneMatch(o -> o.getId() == obj.getId())){
            return false;
        }
        objects.removeIf(o -> o.getId() == obj.getId());
        objects.add(obj);
        return beanToXml(objects, Constants.NOT_ID, method) != EnumResult.Error;
    }

    public <T extends Product> Optional<T> getSharedProductById(Class<T> cl, long id){
        final String method = String.format(Constants.GET_ID, cl.getSimpleName());
        String className = getClassName(cl);
        List<T> objects = xmlToBean(className, method);
        return objects.stream().filter(o -> o.getId() == id).findFirst();
    }


    private static HistoryContent createHistoryContent(String method, Object object, EnumResult enumResult){
        return new HistoryContent(DataProviderCsv.class.getSimpleName(), new Date(), Constants.ACTOR_NAME, method, object, enumResult);
    }

   //добавить xmltoBean and beanToXml

    public <T> String getClassName(Class<T> cl){
        return switch (cl.getSimpleName().toLowerCase()){
            case Constants.PRODUCT_CONST -> Constants.XML_PRODUCT;
            case Constants.CHAIR_CONST -> Constants.XML_CHAIR;
            case Constants.TABLE_CONST -> Constants.XML_TABLE;
            case Constants.CARPET_CONST -> Constants.XML_CARPET;
            default -> "---WRITING---";
        };
    }

}
