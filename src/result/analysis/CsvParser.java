/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammed Siddiq
 */
public class CsvParser {

    
    private Dbops dbop;
    private Semester sem;
    private  MongoDatabase Db;
    
    public CsvParser(MongoDatabase Dbname) {
        dbop = new Dbops();
        sem = new Semester();
        this.Db = Dbname;
        
    }
    
    void Readresults(String filename,String CollectionName) throws IOException {
        try {
            CSVReader reader = new CSVReader(new FileReader(filename), ',', '"', 0);
            sem.GetSemCollection(CollectionName, Db);
            int details = 1;
            Student stud = null;
            Subject subj = null;
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (!nextLine[0].equals("") && !nextLine[0].contains("---------")) {
                    details = 1;
                    stud = new Student();//initialize a mongo document here
                    while (details <= 4)//student details first 4 Lines
                    {
                        stud.fillStudent(details, nextLine[0]);
                        while (details < 4 && (nextLine = reader.readNext()) != null && nextLine[0].equals(""));
                        details++;
                    }
                    stud.ShowStudent();
                    //student result :subject wise;
                    //while((nextLine = reader.readNext())!=null && nextLine[0].equals("") );
                    while ((nextLine = reader.readNext()) != null && !nextLine[0].contains("---------")) {
                        if (!nextLine[0].equals("")) {
                            subj = new Subject();//initialize another document for result
                            subj.name = nextLine[0];
                            subj.code = nextLine[1];
                            subj.internalMarks = Integer.parseInt(nextLine[2].trim());
                            subj.externalMarks = Integer.parseInt(nextLine[3].trim());
                            subj.total = Integer.parseInt(nextLine[4].trim());
                            subj.res = nextLine[5].trim().charAt(0);
                            subj.ShowSubject();
                            stud.resultdoc.add(subj.Setdocument());
                            stud.result.add(subj);
                            //dbops : add to the result document as a document!
                        }

                    }

                    sem.InsertDocument(stud.Setdocument());
                    
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
