/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import org.bson.Document;

/**
 *
 * @author Mohammed Siddiq
 */
public class Subject {
    
    String name;
    String code;
    int internalMarks;
    int externalMarks;
    int total;
    char res;
    Dbops dbop;

    public Subject() {
        dbop= new Dbops();
    }
    
    void ShowSubject()
    {
        System.out.println("Subject:\n------");
        System.out.println("Name: "+ name);
        System.out.println("code: "+ code);
        System.out.println("Internal Marks: "+ internalMarks);
        System.out.println("External Marks: "+ externalMarks);
        System.out.println("total: "+ total);
        System.out.println("Result: "+ res);
       
        
        
    }
    Document Setdocument()
    {
        
           return dbop.CreateSubjectDocument(this);
    }
}
