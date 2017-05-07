/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.DBCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author Mohammed Siddiq
 * This class should have all the functions as methods .
 */
public class Analyze {
    
    
    // For example!
    
    MongoDatabase db;
    
    public Analyze(MongoDatabase db)
    {
        this.db = db;
        
    }    

    Float GetPassPercent(DBCollection collection) {
        
        float passpercent=0;
        /**
         * create a where query[basicDbobject] and invoke 'Query Method' of Dbops 
         * process the result
         * send the function result back to the caller
         
         */
        
        return passpercent;
    }
    
}
