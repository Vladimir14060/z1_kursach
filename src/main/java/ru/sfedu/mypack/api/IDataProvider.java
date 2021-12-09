package ru.sfedu.mypack.api;

import ru.sfedu.mypack.model.Result;
import ru.sfedu.mypack.model.beans.*;
import ru.sfedu.mypack.model.enums.EnumCategory;

import java.util.List;
public interface IDataProvider {

    Boolean createOrderProduct(long orderId, long id);

    Result<Customer> findCustomer(long id);

    Result<List<Order>> getCustomerOrders(long id, boolean sum);

    Result<Double> calculateOrderCost(List<Order> orderList);

    Result<List<Order>> getAllOrders(String typeOrder);

    Result<List<Order>> getSuccessedOrder();

    Result<List<Order>> getUnsuccessedOrder();

    Result<List<Product>> getProductsList(EnumCategory category);

    Result<List<Product>> filterProductByCategory(EnumCategory category);


    Boolean addProduct(Product product);
    Boolean deleteProduct(long id);
    Boolean updateProduct(Product product);
    Result<Product> getProductById(long id);

    Boolean addCustomer(Customer customer);
    Boolean deleteCustomer(long id);
    Boolean updateCustomer(Customer customer);
    Result<Customer> getCustomerById(long id);

    Boolean addOrder(Order order);
    Boolean deleteOrder(long id);
    Boolean updateOrder(Order order);
    Result<Order> getOrderById(long id);

    Boolean addCarpet(Carpet carpet);
    Boolean deleteCarpet(long id);
    Boolean updateCarpet(Carpet carpet);
    Result<Carpet> getCarpetById(long id);

    Boolean addChair(Chair chair);
    Boolean deleteChair(long id);
    Boolean updateChair(Chair chair);
    Result<Chair> getChairById(long id);

    Boolean addTable(Table table);
    Boolean deleteTable(long id);
    Boolean updateTable(Table table);
    Result<Table> getTableById(long id);

}
