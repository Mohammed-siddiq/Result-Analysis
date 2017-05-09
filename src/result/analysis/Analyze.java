/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.BasicDBList;
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

    double GetPassPercent(DBCollection collection) {
        
        double passpercent=0.0;
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
    
    double GetNumber(DBCollection collection,String cclass)
    {
      
        BasicDBObject where=new BasicDBObject();
        where.put("class",cclass);
        
        double number= dbop.Query(collection, where).count();
        
        return number;
        
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
    
    double GetSubjectPassPercent(DBCollection collection, String sub_code)
    {
        double passpercent;
        int si = GetSubjectIndex(collection, sub_code);
        //code to check if the sub_code does not match
        
        String key="result."+si+".res";
        System.out.println(key);
        
        BasicDBObject where=new BasicDBObject();
        
        where.put(key,"F");
        int no_failures= dbop.Query(collection, where).count();
        
        where.remove(key);
        double total= dbop.Query(collection, where).count();
        
        passpercent=(total-no_failures)/total;
        System.out.println(" number of failurees in "+sub_code+" "+no_failures);
        return passpercent;
        
    }
    double GetAverageSubject(DBCollection collection,String sub_code)
    {
        double average;
        int si = GetSubjectIndex(collection, sub_code);
        int sum=0;
        int count=0;
        BasicDBObject where=new BasicDBObject();
        
        DBCursor cur= dbop.Query(collection, where);
        // to check
        while(cur.hasNext())
        {
            
            int temp=0;
            
            
                List<BasicDBObject> docs= (List<BasicDBObject>) cur.next().get("result");
                
                if(docs.size()!=0)
                {
                    try{
                    temp=(int) docs.get(si).get("total");
                    //System.out.println(temp);
                    count++;
                    System.out.println(count);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }  
            sum+= temp;
        }
        
        return sum/count;
    }
    String[] GetSubCodes(DBCollection collection)
    {
        String[] codes;
        DBObject t=collection.findOne();
        DBObject obj = collection.findOne();
        List<BasicDBObject> docs= (List<BasicDBObject>) obj.get("result");
        codes=new String[docs.size()];
        for(int i =0; i<docs.size();i++)
        {
           codes[i]=docs.get(i).getString("code");
        }
        return codes;
        
    }
    
    
    int GetMaxOfSubject(DBCollection collection,String sub_code)
    {
        int max=0;
        int si = GetSubjectIndex(collection, sub_code);
        BasicDBObject where=new BasicDBObject();
        
        DBCursor cur= dbop.Query(collection, where);
        
        while(cur.hasNext())
        {
            
            int temp=0;
            
            
                List<BasicDBObject> docs= (List<BasicDBObject>) cur.next().get("result");
                
                if(docs.size()!=0)
                {
                    temp=(int) docs.get(si).get("external");
                    if(temp>max)
                    {
                        max=temp;
                    }
                    System.out.println(temp);
                    
                }  
            
        }
        
        return max;
    }
}
