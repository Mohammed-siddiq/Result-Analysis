/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.MongoClient;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author Mohammed Siddiq
 */
public class Dbops {

    String myUserName = "sid";
    String myPassword = "password";
    MongoDatabase db;

    public Dbops() {
        
        //
    }

    
    Document CreateSubjectDocument(Subject sub)
    {
            Document doc = new Document("name", sub.name)
                    .append("code", sub.code)
                    .append("internal", sub.internalMarks)
                    .append("external", sub.externalMarks)
                    .append("total", sub.total)
                    .append("res", sub.res);
        return doc;
        
        
    }
    MongoCollection<Document> CreateCollection(String name,MongoDatabase db)
    {
        return db.getCollection(name);
        
        
    }
     Document CreateStudentDocument(Student stud)
    {
            Document doc = new Document("name", stud.name)
                    .append("usn", stud.usn)
                    .append("total", stud.totalMarks)
                    .append("class", stud.cclass)
                    .append("percentage", stud.percentage)
                    .append("result", stud.resultdoc);
        return doc;
        
        
    }
     
    void CreateDb(String name) {

        try {

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // Now connect to your databases
            MongoDatabase db = mongoClient.getDatabase(name);//must be there where csvparser is invoked
            System.out.println("Connect to database successfully");
            MongoCollection<Document> collection = db.getCollection("test");
            Document doc = new Document("name", "MongoDB")
                    .append("type", "database")
                    .append("count", 1)
                    .append("info", new Document("x", 203).append("y", 102));
            collection.insertOne(doc);
            
            System.out.println(collection.count());
            Document myDoc = collection.find().first();
            System.out.println(myDoc.toJson());
            
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

}
