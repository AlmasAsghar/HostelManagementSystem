
import java.awt.event.KeyEvent;
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
public class Add_AttendanceCtrl {
    
    DBHandler db;
    Add_Attendance fr;
    int i;
   
    public Add_AttendanceCtrl(Add_Attendance fr1)
    {
        fr=fr1;
        db=new DBHandler();
    }
    public boolean isValidate()
    {
          int value=Integer.parseInt(fr.tf_month.getText());
        if(fr.tf_emp_name.getText().isEmpty() | fr.tf_id.getSelectedItem().toString().isEmpty() | fr.tf_month.getText().isEmpty())
            
        {
            fr.valid.setText("All The Fields Mandatory");
            return false;
        }
        if(fr.tf_emp_name.getText().length()>20 | fr.tf_id.getSelectedItem().toString().length()>20 | fr.tf_month.getText().length()>20)
        {
            if(fr.tf_emp_name.getText().length()>20)
                fr.tf_emp_name.setText("");
            else if(fr.tf_month.getText().length()>20)
                fr.tf_month.setText("");
            fr.valid.setText("Enter Length of 20");
            return false;
        }
        if(isInteger(fr.tf_month.getText()))
        {
            fr.valid.setText("Enter numeric value");
            return false;
        }
        if(value <0 | value>12)
        {
            fr.valid.setText("Enter num btw 1 to 12 Only");
            return false;
        }
        
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
    public void ValueInCombsobox()
    {
        fr.tf_id.addItem(fr.sf.id);
        fr.tf_emp_name.setText(fr.sf.name);
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
    public void Insert()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Insert");
         if(check==0)
         {
             if(isValidate())
             {
         db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from attendance where emp_id=?");
            db.stmt.setString(1, fr.tf_id.getSelectedItem().toString());
            db.rs=db.stmt.executeQuery();
           
            if(db.rs.next()==true)
            {
               fr.valid.setText("ID Already Exist");
            }
             else
            {  
               
                  try {
            db.stmt=db.con.prepareStatement("Insert into attendance(emp_id,emp_name,month)values(?,?,?)");
            db.stmt.setString(1, fr.tf_id.getSelectedItem().toString());
            db.stmt.setString(2, fr.tf_emp_name.getText());
            db.stmt.setString(3, fr.tf_month.getText());
            
            db.stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Sucessfully Inserted"); 
            ViewTableData();
        } catch (SQLException ex) {
            Logger.getLogger(Add_AttendanceCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            }
           catch (SQLException ex) {
            Logger.getLogger(Add_AttendanceCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
         }
}
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.attndnce_table.getModel();
        tb.setRowCount(0);
    }
     public void SearchTable()
    {
        if(!fr.search.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from attendance where emp_id like ? OR emp_name like ? OR month like ?");
            db.stmt.setString(1,fr.search.getText()+"%");
            db.stmt.setString(2,fr.search.getText()+"%");
            db.stmt.setString(3,fr.search.getText()+"%");
           
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.attndnce_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                  Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7),db.rs.getString(8),db.rs.getString(9),
                     db.rs.getString(10),db.rs.getString(11),db.rs.getString(12),db.rs.getString(13)};
                 tb.addRow(ob);
             }  
        } catch (SQLException ex) {
            Logger.getLogger(Room_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else 
           fr.valid.setText("Enter Some Thing");
    }
     public void Reset()
    {
       
        fr.tf_emp_name.setText("");
        fr.tf_month.setText("");      
        fr.valid.setText("");
    }
     public void UpdateData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Update");
         if(check==0)
         {
             if(isValidate())
             {
         db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from attendance where emp_id=?");
            db.stmt.setString(1, fr.tf_id.getSelectedItem().toString());
            db.rs=db.stmt.executeQuery();
           
            if(db.rs.next()==true)
            {
                try {
            db.stmt=db.con.prepareStatement("Update attendance set emp_name=?,month=? where emp_id=?");
           
            db.stmt.setString(1, fr.tf_emp_name.getText());
            db.stmt.setString(2, fr.tf_month.getText());
            db.stmt.setString(3, fr.tf_id.getSelectedItem().toString());
            
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Inserted"); 
            ViewTableData();
        } catch (SQLException ex) {
            Logger.getLogger(Add_AttendanceCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             else
            {  
                fr.valid.setText("ID Does Not Exist");
            }
            }
           catch (SQLException ex) {
            Logger.getLogger(Add_AttendanceCtrl.class.getName()).log(Level.SEVERE, null, ex);
           }
         }
         }
    }
  public void SelectValueDataInCb()
    {
         db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from attendance where emp_id=? ");
                    db.stmt.setString(1, fr.tf_id.getSelectedItem().toString());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next())
            {
                fr.tf_emp_name.setText(db.rs.getString(2));
                fr.tf_month.setText(db.rs.getString(4));
               
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             } 
    }  
   public void DeleteData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
             if(!fr.tf_id.getSelectedItem().toString().isEmpty())
             {
        db.dbConnection();
        try {
             
            db.stmt=db.con.prepareStatement("delete from attendance where emp_id=?");
            db.stmt.setString(1,fr.tf_id.getSelectedItem().toString());
            db.stmt.executeUpdate();
            ViewTableData();
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            Reset();
            }
           
        catch (Exception ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
             }
             else
                  fr.valid.setText("ID Does Not Exist");
    }
    }
 public void CharValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isLowerCase(c) ||Character.isUpperCase(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  ||c==KeyEvent.VK_SPACE))
        {
            if(evt.getComponent()== fr.tf_emp_name)
            fr.tf_emp_name.setEditable(false);
           
            
            fr.valid.setText("Invalid Character");
        }
        else
        {
            if(evt.getComponent()== fr.tf_emp_name)
            fr.tf_emp_name.setEditable(true);
           
            
            
            fr.valid.setText("");
        }
    }
    public void LogOut()
    {
        LogInFrame lfr=new LogInFrame();
        lfr.setVisible(true);
        fr.hide();
    } 
    public void BackForm()
    {
        Staff_form sfr=new Staff_form();
        sfr.setVisible(true);
        fr.hide();
    }
}
