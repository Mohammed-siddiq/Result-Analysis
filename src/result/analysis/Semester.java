/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Mohammed Siddiq
 */
public class Semester {
    
    List<Student> students ;
    Dbops dbop;
    MongoCollection<Document> SemCollection;
    public Semester() {
        students = new ArrayList<>();
        dbop = new Dbops();
       
    }
    MongoCollection<Document> GetSemCollection(String name,MongoDatabase db)
    {
        
        
        SemCollection = dbop.GetCollection(name, db);
        return SemCollection;
    }
    void InsertDocument(Document doc)
    {
        SemCollection.insertOne(doc);
    }
    
    
}
