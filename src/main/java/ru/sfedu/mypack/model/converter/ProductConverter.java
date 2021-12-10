package ru.sfedu.mypack.model.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mypack.model.beans.Product;

public class ProductConverter extends AbstractBeanField<Product, Integer> {

    private static final Logger log = LogManager.getLogger(ProductConverter.class);

    @Override
    protected Object convert(String str) throws CsvDataTypeMismatchException, CsvConstraintViolationException{
        String indexString = str.substring(1, str.length() -1);
        Product product = new Product();
        if (!indexString.isEmpty()){
            product.setId(Long.parseLong(indexString));
        }
        return product;
    }

    public String convertToWrite(Object obj){
        Product product = (Product) obj;
        StringBuilder builder = new StringBuilder("{ ");
        builder.append(product.getId());
        builder.append(" ,");
        builder.delete(builder.length() -1, builder.length());
        builder.append(" }");
        return builder.toString();
    }

}
