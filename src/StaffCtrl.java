
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
public class StaffCtrl {
     DBHandler db;
     Staff_form fr;
     String id,name;
    public StaffCtrl(Staff_form fr1) {
        db=new DBHandler();
        fr=fr1;    
    }
    private boolean isValidFields()
   {
       if( fr.tf_emp_id.getText().trim().isEmpty() | fr.tf_emp_name.getText().trim().isEmpty() | fr.tf_emp_sal.getText().trim().isEmpty() | 
           fr.tf_designtn.getText().trim().isEmpty() | fr.tf_emp_addrs.getText().trim().isEmpty() | fr.tf_phn.getText().trim().isEmpty() |
           fr.date.getEditor().getText().trim().isEmpty() | fr.tf_emp_age.getText().trim().isEmpty() | fr.tf_hstl_id.getText().trim().isEmpty() )
       {
           fr.valid.setText("Fill All The Fields");
           fr.search.setText("");
           return false;
       }
       else  if( fr.tf_emp_id.getText().length()>20 | fr.tf_emp_name.getText().length()>20 | fr.tf_emp_sal.getText().length()>20 | 
           fr.tf_designtn.getText().length()>20 | fr.tf_emp_addrs.getText().length()>40 | fr.tf_phn.getText().length()>20 |
           fr.date.getEditor().getText().length()>50 | fr.tf_emp_age.getText().length()>20 | fr.tf_hstl_id.getText().length()>20 )
       {
           if(fr.tf_emp_id.getText().length()>20)
            fr.tf_emp_id.setText("");
           else if(fr.tf_emp_name.getText().length()>20)
            fr.tf_emp_name.setText("");
           else if(fr.tf_emp_sal.getText().length()>20)
            fr.tf_emp_sal.setText(""); 
           else if(fr.tf_designtn.getText().length()>20)
            fr.tf_designtn.setText("");
           else if(fr.tf_emp_addrs.getText().length()>20)
            fr.tf_emp_addrs.setText("");
           else if(fr.tf_phn.getText().length()>20)
            fr.tf_phn.setText("");
           else if(fr.date.getEditor().getText().length()>50)
            fr.date.getEditor().setText("");
           else if(fr.tf_emp_age.getText().length()>20)
            fr.tf_emp_age.setText("");
           else if(fr.tf_hstl_id.getText().length()>20)
            fr.tf_hstl_id.setText("");
           
           fr.valid.setText("Fill with valid length 20");
           fr.search.setText("");
           return false;
       }
        if(fr.tf_emp_name.getText().charAt(0)<='a' && fr.tf_emp_name.getText().charAt(0)>='z' )
        {
            fr.valid.setText("Enter Alphabets a to z");
            return false;
        }
        if(fr.tf_emp_name.getText().charAt(0)<='A' && fr.tf_emp_name.getText().charAt(0)>='Z' )
        {
            fr.valid.setText("Enter Alphabets A to Z");
            return false;
        }
       return true;
   }
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.staff_table.getModel();
        tb.setRowCount(0);
    }
     public void InsertInfo()
   {
       int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Insert");
         if(check==0)
         {
      if(isValidFields())
      {
        fr.valid.setText("");
        fr.search.setText("");
        db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from employe where emp_id=?");
            db.stmt.setString(1, fr.tf_emp_id.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
                fr.valid.setText("ID Already Exist");
            }
            else
            {    
                fr.valid.setText("");
              try{ 
                    
                    fr.valid.setText("");
                    db.stmt=db.con.prepareStatement("Insert into employe(emp_id,emp_name,salary,designation,Address,phone,emp_dob,emp_age,hostel_id)values (?,?,?,?,?,?,?,?,?)");
                    db.stmt.setString(1, fr.tf_emp_id.getText());
                    db.stmt.setString(2, fr.tf_emp_name.getText());
                    db.stmt.setString(3, fr.tf_emp_sal.getText());
                    db.stmt.setString(4, fr.tf_designtn.getText());
                    db.stmt.setString(5, fr.tf_emp_addrs.getText());
                    db.stmt.setString(6, fr.tf_phn.getText());
                    db.stmt.setString(7, fr.date.getEditor().getText());
                    db.stmt.setString(8, fr.tf_emp_age.getText());
                    db.stmt.setString(9,fr.tf_hstl_id.getText());
                   
                    db.stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Sucessfully Inserted");
                    ViewTableData();
                    }
              catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             }    
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
         }
   }
    public void DeleteInfo()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
        if(!fr.tf_emp_id.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("delete from employe where emp_id=?");
            db.stmt.setString(1,fr.tf_emp_id.getText() );
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            fr.search.setText("");
            Reset();
            ViewTableData();
        } catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
            fr.valid.setText("Enter Id");
         }
    }
    public void ViewTableData()
    {   
        fr.valid.setText("");
        fr.search.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from employe");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.staff_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                 Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7),db.rs.getString(8),db.rs.getString(9)};
                 tb.addRow(ob);
             }  
        } catch (SQLException ex) {
            Logger.getLogger(Room_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SearchTable()
    {
        if(!fr.search.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from employe where emp_id like ? OR emp_name like ? OR designation like ?  OR hostel_id ?");
            db.stmt.setString(1,fr.search.getText()+"%");
            db.stmt.setString(2,fr.search.getText()+"%");
            db.stmt.setString(3,fr.search.getText()+"%");
            db.stmt.setString(4,fr.search.getText()+"%");
           
            
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.staff_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                  Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7),db.rs.getString(8),db.rs.getString(9)};
                 tb.addRow(ob);
             }  
        } catch (SQLException ex) {
            Logger.getLogger(Room_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else 
           fr.valid.setText("Enter Some Thing");
    }
    public void UpdateInfo()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to update");
         if(check==0)
         {
        if(isValidFields()){
        fr.valid.setText("");
        fr.search.setText("");
        db.dbConnection();
        try {
             db.stmt=db.con.prepareStatement("update employe set emp_name=?,salary=?,designation=?,Address=?,phone=?,"
                   + "emp_dob=?,emp_age=?,hostel_id=? where emp_id=?");                         
            db.stmt.setString(1, fr.tf_emp_name.getText());
            db.stmt.setString(2, fr.tf_emp_sal.getText());
            db.stmt.setString(3, fr.tf_designtn.getText());
            db.stmt.setString(4, fr.tf_emp_addrs.getText());
            db.stmt.setString(5, fr.tf_phn.getText());
            db.stmt.setString(6, fr.date.getEditor().getText());
            db.stmt.setString(7, fr.tf_emp_age.getText());
            db.stmt.setString(8,fr.tf_hstl_id.getText());
            db.stmt.setString(9, fr.tf_emp_id.getText());
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
    }
    public void Search()
    {
        fr.valid.setText("");
        db.dbConnection();
         try {
            db.stmt=db.con.prepareStatement("select * from employe where emp_id=?");
            db.stmt.setString(1, fr.search.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
              try {
                    db.stmt=db.con.prepareStatement("select * from employe where emp_id=?");
                    db.stmt.setString(1, fr.search.getText());
                    db.rs=db.stmt.executeQuery();
                    if(db.rs.next())
                    {
                        fr.tf_emp_id.setText(db.rs.getString(1));
                        fr.tf_emp_name.setText(db.rs.getString(2));
                        fr.tf_emp_sal.setText(db.rs.getString(3));
                        fr.tf_designtn.setText(db.rs.getString(4));
                        fr.tf_emp_addrs.setText(db.rs.getString(5));
                        fr.tf_phn.setText(db.rs.getString(6));
                        fr.date.getEditor().setText(db.rs.getString(7));
                        fr.tf_emp_age.setText(db.rs.getString(8));
                        fr.tf_hstl_id.setText(db.rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            else
            {
                Reset();
                JOptionPane.showMessageDialog(null, "Data Not Found");
            }
    } catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Reset()
    {
        fr.tf_emp_id.setText("");
        fr.tf_emp_name.setText("");
        fr.tf_emp_sal.setText("");      
        fr.tf_designtn.setText("");
        fr.tf_emp_addrs.setText("");
        fr.tf_phn.setText("");
        fr.date.getEditor().setText("");
        fr.tf_emp_age.setText("");
        fr.tf_hstl_id.setText("");
        fr.valid.setText("");
    }
    public void FillDataInFields()
    {
        fr.valid.setText("");
        int i= fr.staff_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.staff_table.getModel();
         fr.tf_emp_id.setText(tb.getValueAt(i, 0).toString());
         id=tb.getValueAt(i, 0).toString();
         
         fr.tf_emp_name.setText(tb.getValueAt(i, 1).toString());
         name=tb.getValueAt(i, 1).toString();
         
         fr.tf_emp_sal.setText(tb.getValueAt(i, 2).toString());
         fr.tf_designtn.setText(tb.getValueAt(i, 3).toString());
         fr.tf_emp_addrs.setText(tb.getValueAt(i, 4).toString());
         fr.tf_phn.setText(tb.getValueAt(i, 5).toString());
         fr.date.getEditor().setText(tb.getValueAt(i, 6).toString());
         fr.tf_emp_age.setText(tb.getValueAt(i, 7).toString());
         fr.tf_hstl_id.setText(tb.getValueAt(i, 8).toString());
         fr.search.setText("");
    }
    public void CharValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isLowerCase(c) ||Character.isUpperCase(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_SPACE ))
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
    public void NumberValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE ))
        {
            if(evt.getComponent()== fr.tf_phn)
            fr.tf_phn.setEditable(false);
            else if(evt.getComponent()== fr.tf_emp_age)
            fr.tf_emp_age.setEditable(false);
            else if(evt.getComponent()== fr.tf_emp_sal)
            fr.tf_emp_sal.setEditable(false);
            
            fr.valid.setText("Invalid num");
        }
        else
        {
            if(evt.getComponent()== fr.tf_phn)
            fr.tf_phn.setEditable(true);
            else if(evt.getComponent()== fr.tf_emp_age)
            fr.tf_emp_age.setEditable(true);
            else if(evt.getComponent()== fr.tf_emp_sal)
            fr.tf_emp_sal.setEditable(true);
            
            fr.valid.setText("");
        } 
    }
     public void BackPage()
    {
        Main_Page mfr=new Main_Page();
        mfr.setVisible(true);
        fr.hide(); 
    }
    public void OpenAttendanceForm()
    {
         int i= fr.staff_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.staff_table.getModel();
        if(i>=0)
        {
            Add_Attendance afr=new Add_Attendance(this);
            afr.setVisible(true);
            fr.hide();
         }
         else 
            fr.valid.setText("Select some Row");
    }
    public void LogOut()
    {
        LogInFrame lfr=new LogInFrame();
        lfr.setVisible(true);
        fr.hide();
    }
}
