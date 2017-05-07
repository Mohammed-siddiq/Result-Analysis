/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammed Siddiq
 */
public class ResultAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        // TODO code application logic here
//        String[] colleges = {"jssate"};
//        String[] years = {"11","12","13"};
////        Dbops mongo = new Dbops();
////        mongo.CreateDb("mydb");
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        String filename = null;
//        MongoDatabase db;
//        CsvParser parser;
//       
//        String BaseUrl = "C:\\Users\\AFZAAL\\PycharmProjects\\FastVtuScraper\\College_results";
//        for (int i = 0; i < colleges.length; i++) {
//            db = mongoClient.getDatabase(colleges[i]);
//            parser = new CsvParser(db);
//            for (int j = 0; j < years.length; j++) {
//                for (int k = 1; k <= 8; k++) {
//                    String basefile = colleges[i] + "_" + years[j] + "_cs_" + k + "sem.csv";
//                    filename = BaseUrl + "\\" + colleges[i] + "\\" + years[j] + "\\cs\\" + basefile;
//                    String collectionName = "cs_" + years[j] + "_" + k + "_sem";
//                    try {
//                        parser.Readresults(filename, collectionName);
//                    }catch (FileNotFoundException ex)
//                    {
//                       System.out.println(ex.getMessage());
//                        continue;
//                    }
//                    catch (IOException ex) {
//                        Logger.getLogger(ResultAnalysis.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    
//
//                }
//            }
//        }
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase db=mongoClient.getDatabase("rnsit");
        DB db=mongoClient.getDB("rnsit");
        Analyze a = new Analyze(db);
        
        a.GetDistribution((DBCollection) db.getCollection("cs_11_1_sem"));
        a.GetSubjectPassPercent((DBCollection) db.getCollection("cs_11_1_sem"), "10MAT11");
        
        
        

      
    }

}
