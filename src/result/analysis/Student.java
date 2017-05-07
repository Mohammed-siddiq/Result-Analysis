/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.sun.jmx.snmp.BerDecoder;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Mohammed Siddiq
 */
public class Student {

    String name;
    String usn;
    int totalMarks;
    String cclass;
    float percentage;
    List<Subject> result;
    List<Document> resultdoc; 
    Dbops dbop;
    Student() {
        result = new ArrayList<>();
        resultdoc = new ArrayList<>();
        dbop = new Dbops();
    }

    void fillStudent(int detail, String data) {
        int index;
        int startIndex;
        int endIndex;

        switch (detail) {
            case 1:
                startIndex = data.indexOf('(');
                endIndex = data.indexOf(')');
                name = data.substring(0, startIndex - 1).trim();
                usn = data.substring(startIndex + 1, endIndex).trim();
                break;
            case 2:
                index = data.indexOf(':');
                String marks = data.substring(index + 1).trim();
                totalMarks = Integer.parseInt(marks);
                break;
            case 3:
                index = data.indexOf(':');
                cclass = data.substring(index + 1).trim();
                break;
            case 4:
                startIndex = data.indexOf(':');
                endIndex = data.indexOf('%');
                String percent = data.substring(startIndex + 1, endIndex);
                percentage = Float.parseFloat(percent);
                break;

        }
    }

    void ShowStudent() {

        System.out.println("Student:\n------");
        System.out.println("Name: " + name);
        System.out.println("Usn: " + usn);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("class: " + cclass);
        System.out.println("percentage: " + percentage);

    }
    
    Document Setdocument()
    {
        return(dbop.CreateStudentDocument(this));
    }
    

}
