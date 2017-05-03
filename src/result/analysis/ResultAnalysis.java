/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

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
//        
//        Dbops mongo = new Dbops();
//        mongo.CreateDb("mydb");
        String file ="C:\\Users\\AFZAAL\\PycharmProjects\\FastVtuScraper\\College_results\\rnsit\\13\\cs\\rnsit_13_cs_1sem.csv";
        CsvParser parser = new CsvParser();
        try {
            parser.Readresults(file);
        } catch (IOException ex) {
            Logger.getLogger(ResultAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
