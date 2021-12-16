package ru.sfedu.mypack;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mypack.api.DataProviderJdbc;
import ru.sfedu.mypack.model.beans.Carpet;
import ru.sfedu.mypack.model.enums.EnumCategory;
import ru.sfedu.mypack.model.enums.EnumColourWool;


public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
//        CSV
//        DataProviderCsv dataProviderCsv = new DataProviderCsv();
//        Carpet carpet = new Carpet();
//        carpet.setId();
//        carpet.setName("Carpet");
//        carpet.setCategory(EnumCategory.CARPET);
//        carpet.setPrice(150.0);
//        carpet.setWool("ovechka");
//        carpet.setWeight(50.0);
//        carpet.setLength(150.0);
//        carpet.setColour(EnumColourWool.RED);
//        dataProviderCsv.addCarpet(carpet);
//        dataProviderCsv.deleteCarpet(carpet.getId());


//        XML
//        DataProviderXml dataProviderXml = new DataProviderXml();
//        Carpet carpet =new Carpet();
//        carpet.setId();
//        carpet.setName("Carpet");
//        carpet.setCategory(EnumCategory.CARPET);
//        carpet.setPrice(120.0);
//        carpet.setWool("koza");
//        carpet.setWeight(30.0);
//        carpet.setLength(70.0);
//        carpet.setColour(EnumColourWool.GREEN);
//        dataProviderXml.addCarpet(carpet);
//        dataProviderXml.deleteCarpet(carpet.getId());


//        Delte CSV and XML by id
//        DataProviderCsv dataProviderCsv = new DataProviderCsv();
//        DataProviderXml dataProviderXml = new DataProviderXml();
//        dataProviderCsv.deleteCarpet(L);
//        dataProviderXml.deleteCarpet(L);

//        Db
//        DataProviderJdbc dataProviderJdbc = new DataProviderJdbc();
//        dataProviderJdbc.deleteAllTables();
//        dataProviderJdbc.createTables();
//        Carpet carpet = new Carpet();
//        carpet.setId();
//        carpet.setName("Carpet");
//        carpet.setCategory(EnumCategory.CARPET);
//        carpet.setPrice(150.0);
//        carpet.setWool("ovechka");
//        carpet.setWeight(50.0);
//        carpet.setLength(150.0);
//        carpet.setColour(EnumColourWool.RED);
//        dataProviderJdbc.addCarpet(carpet);


    }
}
