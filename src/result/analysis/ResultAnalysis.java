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
        // TODO code application logic here
//        String[] colleges = {"rnsit","jssate","sjbit","bit","bnmit"};
//        String[] years = {"13"};
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
//                for (int k = 7; k <= 7; k++) {
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
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        //MongoDatabase db=mongoClient.getDatabase("rnsit");
//
//        Chart ch = new Chart();
//        String [] c ={"rnsit"};
//        //ch.batchAcrossSemesters("13", 1, c);
//        //ch.perSemPerformace("13","6",c);
//        //ch.BatchsubjectPerformance("13","6",c,"10CS65");
//        //ch.avgMarks("13", "6", c);
//        //ch.subjectWisePerformance("13", "6", c, "10CS65");
//        ch.currentPerformance(1,c);
        
        

      
    }

}
