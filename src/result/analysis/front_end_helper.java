/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front_end_ra;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class front_end_helper {
    
    public void change_panel(JPanel current, JPanel next){
        current.setVisible(false);
        next.setVisible(true);
            
        
    }
    public boolean validate_year(String year)
    {
        year = year.trim();
        if(year.length() != 4)
        {
            return false;
        }
        int y;
        try{
             y = Integer.parseInt(year);
        }
        catch (NumberFormatException ne)
        {
            return false;
        }
       if(y <=2013 && y >=2011)
       {
           return true;
           
       }
       else
       {
           return false;
       }
        
    }
    public void year_error_message()
    {
                    JOptionPane.showMessageDialog(null," Invalid year or results not available. Please enter a year between 2013 and 2011 ");

    }
    public void clear_textfield(JTextField tf)
    {
        tf.setText("");
    }
    
    public void hide_selection_objects(JComponent[] jcs)
    {
        for(JComponent jc : jcs)
        {
            jc.setVisible(false);
            
        }
}
    
    public boolean validate_sub_code(String sc)
    {
        sc = sc.trim();
        if(sc.matches("$10CS[5-8][5-8][1-8]^"))
        {
            return true;
        }
        return false;
    }
}
