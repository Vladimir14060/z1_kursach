package ru.sfedu.mypack.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mypack.Constants;
import ru.sfedu.mypack.model.history.HistoryContent;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.bson.Document.parse;

public class HistoryUtil {

    private static final Logger log = LogManager.getLogger(HistoryUtil.class);

    public static void saveToLog(HistoryContent content){
        try(MongoClient mongoClient = MongoClients.create(Constants.MONGO_CONNECT)){
            mongoClient.getDatabase(Constants.MONGO_DATABASE_NAME).getCollection(Constants.MONGO_COLLECTION_NAME).insertOne(parse(objectToString(content)));
        } catch (Exception e){
            String date = new SimpleDateFormat(Constants.MONGO_DATE).format(new Date());
            log.error(String.valueOf(e), date);
        }
    }

    private static String objectToString(Object object) throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(object);
    }
}
