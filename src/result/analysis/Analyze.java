/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;

/**
 * 
 * @author Mohammed Siddiq
 * This class should have all the functions as methods .
 */
public class Analyze {
    
    
    // For example!
    
    DB db;
    Dbops dbop;
    
    public Analyze(DB db)
    {
        this.db = db;
        dbop=new Dbops();
        
    }    

    float GetPassPercent(DBCollection collection) {
        
        float passpercent=0;
        /**
         * create a where query[basicDbobject] and invoke 'Query Method' of Dbops 
         * process the result
         * send the function result back to the caller
         
         */
        
        BasicDBObject where=new BasicDBObject();
        where.put("class","FAIL");
        
        int no_failures= dbop.Query(collection, where).count();
        
        where.remove("class","FAIL");
        
        int total_students=dbop.Query(collection, where).count();
        
        passpercent=(total_students-no_failures)/total_students;
        
        return passpercent;
    }
    void GetDistribution(DBCollection collection){
        
        BasicDBObject where=new BasicDBObject();
        
        where.put("class","FAIL");
        int no_failures= dbop.Query(collection, where).count();
        
        where.replace("class","SECOND CLASS");
        int no_sc= dbop.Query(collection, where).count();
        
        where.replace("class","FIRST CLASS");
        int no_fc= dbop.Query(collection, where).count();
        
        where.replace("class","FIRST CLASS WITH DISTINCTION");
        int no_fcd= dbop.Query(collection, where).count();
        
        where.remove("class");
        int total= dbop.Query(collection, where).count();
        System.out.println(no_failures+"   "+no_sc+"  "+no_fc+"   "+no_fcd);
        
    }
    
    int GetSubjectIndex(DBCollection collection, String sub_code)
    {
        DBObject obj = collection.findOne();

        List<BasicDBObject> docs= (List<BasicDBObject>) obj.get("result");
        int count=-1;
        for (BasicDBObject doc:docs)
        {
            count ++;
            String sc=(String)doc.get("code");
            if(sc.equals(sub_code))
            {
                return count;
            }
        }
        return -1;
    }
    
    float GetSubjectPassPercent(DBCollection collection, String sub_code)
    {
        float passpercent;
        int si = GetSubjectIndex(collection, sub_code);
        //code to check if the sub_code does not match
        
        String key="result."+si+".res";
        System.out.println(key);
        
        BasicDBObject where=new BasicDBObject();
        
        where.put(key,"F");
        int no_failures= dbop.Query(collection, where).count();
        
        where.remove(key);
        int total= dbop.Query(collection, where).count();
        
        passpercent=(total-no_failures)/total;
        System.out.println(" number of failurees in "+sub_code+" "+no_failures);
        return passpercent;
        
    }
    
}
