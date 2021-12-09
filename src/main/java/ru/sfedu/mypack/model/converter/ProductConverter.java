package ru.sfedu.mypack.model.converter;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.mypack.api.DataProviderCsv;
import ru.sfedu.mypack.model.beans.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductConverter extends AbstractBeanField<Product, Integer> {

    private static final DataProviderCsv DATA_PROVIDER_CSV = new DataProviderCsv();

    @Override
    protected Object convert(String str){
        String indexString = str.substring(1, str.length() -1);
        String[] unparsesList = indexString.split(" ,");
        List<String> indexTaskList = new ArrayList<>(Arrays.asList(unparsesList));
        List<Product> productList = new ArrayList<>();
        indexTaskList.forEach(indexStr -> productList.add(DATA_PROVIDER_CSV.getProductById(Long.parseLong(indexStr)).getData()));
        return productList;
    }

    public String convertToWrite(Object obj){
        List<Product> productList = (List<Product>) obj;
        StringBuilder builder = new StringBuilder("{ ");
        if (productList.size() >0){
            productList.forEach(product -> builder.append(product.getId()).append(", "));
            builder.delete(builder.length() -1, builder.length());
        }
        builder.append(" }");
        return builder.toString();
    }

}
