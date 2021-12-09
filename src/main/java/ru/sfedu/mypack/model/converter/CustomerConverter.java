package ru.sfedu.mypack.model.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mypack.model.beans.Customer;

public class CustomerConverter extends AbstractBeanField<Customer, Integer> {

    private static final Logger log = LogManager.getLogger(CustomerConverter.class);

    @Override
    protected Object convert(String str) throws CsvDataTypeMismatchException, CsvConstraintViolationException{
        String indexString = str.substring(1, str.length() -1);
        Customer customer = new Customer();
        if (!indexString.isEmpty()){
            customer.setId(Long.parseLong(indexString));
        }
        return customer;
    }

    public String convertToWrite(Object obj){
        Customer customer = (Customer) obj;
        StringBuilder builder = new StringBuilder("{ ");
        builder.append(customer.getId());
        builder.append(" ,");
        builder.delete(builder.length() -1, builder.length());
        builder.append(" }");
        return builder.toString();
    }

}
