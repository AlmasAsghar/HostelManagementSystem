




import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Almas Asghar
 */
public class AttendanceCtrl {
    DBHandler db;
    Attendance_Form fr;
    int i;
    public AttendanceCtrl(Attendance_Form fr1)
    {
        fr=fr1;
        db=new DBHandler();
    }
    public void BackPage()
    {
        Main_Page mfr=new Main_Page();
        mfr.setVisible(true);
        fr.hide(); 
    }
    public void LogOut()
    {
        LogInFrame lfr=new LogInFrame();
        lfr.setVisible(true);
        fr.hide();
    }
    private boolean isValid()
    {
        fr.valid.setText("");
        int i= fr.attndnce_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.attndnce_table.getModel();
        int value=Integer.parseInt(tb.getValueAt(i,2).toString());
        
        if( tb.getValueAt(i,0).toString().isEmpty() | tb.getValueAt(i,1).toString().isEmpty() |tb.getValueAt(i,2).toString().isEmpty() |tb.getValueAt(i,3).toString().isEmpty()
                | tb.getValueAt(i,4).toString().isEmpty()|tb.getValueAt(i,5).toString().isEmpty() |tb.getValueAt(i,6).toString().isEmpty()| tb.getValueAt(i,7).toString().isEmpty()
                |tb.getValueAt(i,8).toString().isEmpty()|tb.getValueAt(i,9).toString().isEmpty()|tb.getValueAt(i,10).toString().isEmpty()
                |tb.getValueAt(i,11).toString().isEmpty()|tb.getValueAt(i,12).toString().isEmpty())
        {
            fr.valid.setText("Fill All The Fields");
           
           return false;
        }
        if( tb.getValueAt(i,0).toString().length()>20| tb.getValueAt(i,1).toString().length()>20 |tb.getValueAt(i,2).toString().length()>20 |tb.getValueAt(i,3).toString().length()>1
                | tb.getValueAt(i,4).toString().length()>1|tb.getValueAt(i,5).toString().length()>1 |tb.getValueAt(i,6).toString().length()>1| tb.getValueAt(i,7).toString().length()>1
                |tb.getValueAt(i,8).toString().length()>1|tb.getValueAt(i,9).toString().length()>1|tb.getValueAt(i,10).toString().length()>1
                |tb.getValueAt(i,11).toString().length()>1|tb.getValueAt(i,12).toString().length()>1)
        {
            if(tb.getValueAt(i,0).toString().length()>20 || tb.getValueAt(i,1).toString().length()>20)
            fr.valid.setText("Fill with valid Length 20");
            else if(tb.getValueAt(i,2).toString().length()>2)
             fr.valid.setText("Fill with valid Length 2");
            else
                fr.valid.setText("Fill with valid Length 1");
           
           return false;
        } 
       if(isInteger(tb.getValueAt(i,3).toString()) |isInteger(tb.getValueAt(i,4).toString())|isInteger(tb.getValueAt(i,5).toString())|
               isInteger(tb.getValueAt(i,6).toString())|isInteger(tb.getValueAt(i,7).toString())|isInteger(tb.getValueAt(i,8).toString())|
               isInteger(tb.getValueAt(i,9).toString())|isInteger(tb.getValueAt(i,10).toString())|isInteger(tb.getValueAt(i,11).toString())|
               isInteger(tb.getValueAt(i,12).toString()))
        {
            fr.valid.setText("Enter numeric value");
            return false;
        }
       if(isValidNumber(tb.getValueAt(i,3).toString())|isValidNumber(tb.getValueAt(i,4).toString())|isValidNumber(tb.getValueAt(i,5).toString())|
               isValidNumber(tb.getValueAt(i,6).toString())|isValidNumber(tb.getValueAt(i,7).toString())|isValidNumber(tb.getValueAt(i,8).toString())|
               isValidNumber(tb.getValueAt(i,9).toString())|isValidNumber(tb.getValueAt(i,10).toString())|isValidNumber(tb.getValueAt(i,11).toString())|
               isValidNumber(tb.getValueAt(i,12).toString()))
       {
           fr.valid.setText("Enter 0 or 1 Only");
           return false;
       }
        if(value <1 | value>12)
        {
            fr.valid.setText("Enter num btw 1 to 12 Only");
            return false;
        }
       
        
        fr.valid.setText("");
        return true;
    }
     private boolean isInteger(String s) 
     {
         
      boolean isValid = true;
      try
      {
         Integer.parseInt(s);
         isValid = false;
      }
      catch (NumberFormatException ex)
      {
         System.out.println(""+ex);
      }
 
      return isValid;
   }
    private boolean isValidNumber(String s)
    {
        int i= fr.attndnce_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.attndnce_table.getModel();
       int value=Integer.parseInt(s);
        if(value==0 || value==1 )
        {
            return false;
        }
        
        return true;             
}
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.attndnce_table.getModel();
        tb.setRowCount(0);
    }
    public void ViewTableData()
    {
       
        fr.valid.setText("");
         db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from attendance");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.attndnce_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                 Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7),db.rs.getString(8),db.rs.getString(9)
                         ,db.rs.getString(10),db.rs.getString(11),db.rs.getString(12),db.rs.getString(13)};
                 tb.addRow(ob);
             }  
        } catch (SQLException ex) {
            Logger.getLogger(Room_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to update");
         if(check==0)
         {
        int i= fr.attndnce_table.getSelectedRow();
        fr.valid.setText("");
        if(i>=0)
        {
        if(isValid())
        {
        db.dbConnection();
        
  
        try {
            DefaultTableModel tb=(DefaultTableModel) fr.attndnce_table.getModel();
             db.stmt=db.con.prepareStatement("update attendance set emp_name=?,month=?,day1=?,day2=?,day3=?,day4=?,day5=?,day6=?,day7=?,day8=?,day9=?,day10=? where emp_id=?");                         
            db.stmt.setString(1,tb.getValueAt(i, 1).toString());
            db.stmt.setString(2,tb.getValueAt(i, 2).toString());
            db.stmt.setString(3,tb.getValueAt(i, 3).toString());
            db.stmt.setString(4,tb.getValueAt(i, 4).toString());
            db.stmt.setString(5,tb.getValueAt(i, 5).toString());
            db.stmt.setString(6,tb.getValueAt(i, 6).toString());
            db.stmt.setString(7,tb.getValueAt(i, 7).toString());
            db.stmt.setString(8,tb.getValueAt(i, 8).toString());
            db.stmt.setString(9,tb.getValueAt(i, 9).toString());
            db.stmt.setString(10,tb.getValueAt(i,10).toString());
            db.stmt.setString(11,tb.getValueAt(i,11).toString());
            db.stmt.setString(12,tb.getValueAt(i,12).toString());
            db.stmt.setString(13,tb.getValueAt(i, 0).toString());
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Updated"); 
            ViewTableData();
        }
       catch(Exception ex)
        {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        } 
        else 
            fr.valid.setText("Select some Row");
         }
    }
    public void DeleteData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
        db.dbConnection();
        try {
             int i= fr.attndnce_table.getSelectedRow();
            DefaultTableModel tb=(DefaultTableModel) fr.attndnce_table.getModel();
            if(i>=0)
            {
            db.stmt=db.con.prepareStatement("delete from attendance where emp_id=?");
            db.stmt.setString(1,tb.getValueAt(i, 0).toString());
            db.stmt.executeUpdate();
            ViewTableData();
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            }
            else
                fr.valid.setText("Select some Row");
        } catch (Exception ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    }
}
