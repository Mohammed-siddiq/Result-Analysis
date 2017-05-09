/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result.analysis;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.Toolkit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.jfree.util.TableOrder;

/**
 *
 * @author Mohammed Siddiq
 */
public class Chart {

    Analyze analyz;
    DB db;
    MongoClient mongoClient;

    public Chart() {
        mongoClient = new MongoClient("localhost", 27017);

    }

    void perSemPerformace(String batch, String sem, String[] colleges) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String college:colleges)
        {
            db = mongoClient.getDB(college);
        String collection_name = "cs_" + batch + "_" + sem + "_sem";
        DBCollection collection = db.getCollection(collection_name);
        
        
        analyz = new Analyze(db);
        double number = analyz.GetNumber(collection, "FAIL");
        dataset.setValue(number,college,"FAIL");
        number = analyz.GetNumber(collection, "FIRST CLASS");
        dataset.setValue(number,college,"First Class");

        number = analyz.GetNumber(collection, "SECOND CLASS");
        dataset.setValue(number,college,"Second class");

        number = analyz.GetNumber(collection, "FIRST CLASS WITH DISTINCTION");
        dataset.setValue( number,college,"First Class With Distinction");
        
        
            
        }
        JFreeChart pieChart = ChartFactory.createMultiplePieChart("Classwise Distribution", dataset, TableOrder.BY_ROW, true, true, true);
//        MultiplePiePlot plot = (MultiplePiePlot) pieChart.getPlot();
//        plot.setStartAngle(290);
//        plot.setDirection(Rotation.CLOCKWISE);
//        plot.setForegroundAlpha(0.5f);

        ChartFrame frame = new ChartFrame("Semester Wise Performance of " + batch + " year", pieChart);
        frame.setVisible(true);
        frame.setSize(500, 500);
        

    }

    void semWisePerformance(String batch, int odev, String[] colleges) {
        db = mongoClient.getDB("rnsit");
        analyz = new Analyze(db);

        final String fcd = "F.C.D";
        final String fc = "F.C";
        final String fail = "FAIL";
        final String sc = "S.C";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String collection_name;
        int start_index = 1, end_index = 7;
        if (odev == 2)//oddd
        {
            start_index = 2;
            end_index = 8;
        }

        for (int i = start_index; i <= end_index; i += 2) {
            collection_name = "cs_" + batch + "_" + i + "_sem";
            DBCollection collection = db.getCollection(collection_name);
            double number = analyz.GetNumber(collection, "FAIL");
            dataset.addValue(number, fail, "Sem " + i);
            number = analyz.GetNumber(collection, "FIRST CLASS");
            dataset.addValue(number, fc, "Sem " + i);
            number = analyz.GetNumber(collection, "SECOND CLASS");
            dataset.addValue(number, sc, "Sem " + i);
            number = analyz.GetNumber(collection, "FIRST CLASS WITH DISTINCTION");
            dataset.addValue(number, fcd, "Sem " + i);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Sem Wise Performance",
                "SEMESTER",
                "NUMBER",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartFrame frame = new ChartFrame("Semester Wise Performance of " + batch + " year", barChart);
        frame.setVisible(true);
        frame.setSize(500, 500);
    }

    void avgMarks(String batch, String sem, String[] colleges) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String college:colleges)
        {
            db = mongoClient.getDB(college);
            analyz = new Analyze(db);
        String collection_name = "cs_" + batch + "_" + sem + "_sem";
        DBCollection collection = db.getCollection(collection_name);
        

        String[] codes = analyz.GetSubCodes(collection);
        for (String code : codes) {
            double avg = analyz.GetAverageSubject(collection, code);
            dataset.setValue(avg, college, code);
        }
        }
        
        JFreeChart barChart = ChartFactory.createBarChart(
                "Average in each subject",
                "SUBJECT",
                "AVERAGE MARKS",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

// set the color (r,g,b) or (r,g,b,a)
        Color color = new Color(79, 129, 189);
        renderer.setSeriesPaint(0, color);
        ChartFrame frame = new ChartFrame("Subject-wise average marks of 20" + batch + " batch " + sem + "th semester", barChart);
        frame.setVisible(true);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

    }

    void subjectWisePerformance(String batch, String sem, String[] colleges, String code) {
        db = mongoClient.getDB("rnsit");
        analyz = new Analyze(db);
        String collection_name = "cs_" + batch + "_" + sem + "_sem";
        DBCollection collection = db.getCollection(collection_name);
        double passpercent = analyz.GetSubjectPassPercent(collection, code);
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Fail ", 1 - passpercent);
        dataset.setValue("Pass ", passpercent);

        JFreeChart pieChart = ChartFactory.createPieChart3D("Performance of " + batch + sem + " Semester in Subject " + code, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        ChartFrame frame = new ChartFrame("Subject Performance of " + batch + " year " + sem + " Semester ", pieChart);
        frame.setVisible(true);
        frame.setSize(500, 500);

    }

    void BatchsubjectPerformance(String batch, String sem, String[] colleges, String code) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String college:colleges)
        {
            db = mongoClient.getDB(college);
        analyz = new Analyze(db);
        
        for (int i = 11; i <= 13; i++) {
            String collection_name = "cs_" + i + "_" + sem + "_sem";
            DBCollection collection = db.getCollection(collection_name);
            double passpercent = analyz.GetSubjectPassPercent(collection, code);
           // dataset.addValue(passpercent*100, "Pass %", Integer.toString(i));
            dataset.addValue((1 - passpercent)*100, college, Integer.toString(i));

        }
            
        }
        

        JFreeChart barChart = ChartFactory.createBarChart(
                "Fail % for "+ code,
                "Batch", "Percentage",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartFrame frame = new ChartFrame("Subject Performance of 20" + batch + " year " + sem + " Semester ", barChart);
        frame.setVisible(true);
        frame.setSize(500, 500);

    }

}
