
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
public class StudentCtrl {
     DBHandler db;
     Student_Form fr;
     String id,name;
    
    public StudentCtrl(Student_Form fr1) {
       
        db=new DBHandler();
        fr=fr1;
    }
    
   
   private boolean isValidFields()
   {
       if( fr.tf_std_id.getText().trim().isEmpty() | fr.tf_std_name.getText().trim().isEmpty() | fr.tf_std_fn.getText().trim().isEmpty() | 
           fr.tf_std_dept.getText().trim().isEmpty() | fr.tf_std_addrs.getText().trim().isEmpty() | fr.tf_phn.getText().trim().isEmpty() |
           fr.date.getEditor().getText().trim().isEmpty() | fr.tf_std_age.getText().trim().isEmpty() | fr.tf_hstl_id.getText().trim().isEmpty() )
       {
           fr.valid.setText("Fill All The Fields");
           fr.tf_search.setText("");
           return false;
       }
       else if( fr.tf_std_id.getText().length()>20 | fr.tf_std_name.getText().length()>20 | fr.tf_std_fn.getText().length()>20 | 
           fr.tf_std_dept.getText().length()>20 | fr.tf_std_addrs.getText().length()>40 | fr.tf_phn.getText().length()>20 |
           fr.date.getEditor().getText().length()>50 | fr.tf_std_age.getText().length()>20 | fr.tf_hstl_id.getText().length()>20  )
       {
           if(fr.tf_std_id.getText().length()>20)
            fr.tf_std_id.setText("");
           else if(fr.tf_std_name.getText().length()>20)
            fr.tf_std_name.setText("");
           else if(fr.tf_std_fn.getText().length()>20)
            fr.tf_std_fn.setText(""); 
            else if(fr.tf_std_dept.getText().length()>20)
             fr.tf_std_dept.setText("");
            else if(fr.tf_std_addrs.getText().length()>20)
             fr.tf_std_addrs.setText("");
            else if(fr.tf_phn.getText().length()>20)
             fr.tf_phn.setText("");
            else if(fr.date.getEditor().getText().length()>50 )
             fr.date.getEditor().setText("");
            else if(fr.tf_std_age.getText().length()>20)
             fr.tf_std_age.setText("");
            else if (fr.tf_hstl_id.getText().length()>20)
             fr.tf_hstl_id.setText("");
           
           
           fr.valid.setText("Fill with valid length 20");
           fr.tf_search.setText("");
           return false;
       }
        return true;
   }
   public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.std_table.getModel();
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
        fr.tf_search.setText("");
        db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from student where std_id=?");
            db.stmt.setString(1, fr.tf_std_id.getText());
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
                    db.stmt=db.con.prepareStatement("Insert into student(std_id,std_name,father_name,department,Address,phone_no,std_dob,std_age,hostel_id)values (?,?,?,?,?,?,?,?,?)");
                    db.stmt.setString(1, fr.tf_std_id.getText());
                    db.stmt.setString(2, fr.tf_std_name.getText());
                    db.stmt.setString(3, fr.tf_std_fn.getText());
                    db.stmt.setString(4, fr.tf_std_dept.getText());
                    db.stmt.setString(5, fr.tf_std_addrs.getText());
                    db.stmt.setString(6, fr.tf_phn.getText());
                    db.stmt.setString(7, fr.date.getEditor().getText());
                    db.stmt.setString(8, fr.tf_std_age.getText());
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
        if(!fr.tf_std_id.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("delete from student where std_id=?");
            db.stmt.setString(1,fr.tf_std_id.getText() );
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            fr.tf_search.setText("");
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
        fr.tf_search.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from student");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.std_table.getModel();
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
        if(!fr.tf_search.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from student where std_id like ? OR std_name like ? OR father_name like ? OR department like ? OR hostel_id like ? OR room_id like ?");
            db.stmt.setString(1,fr.tf_search.getText()+"%");
            db.stmt.setString(2,fr.tf_search.getText()+"%");
            db.stmt.setString(3,fr.tf_search.getText()+"%");
            db.stmt.setString(4,fr.tf_search.getText()+"%");
            db.stmt.setString(5,fr.tf_search.getText()+"%");
            db.stmt.setString(6,fr.tf_search.getText()+"%");
            
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.std_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                  Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7),db.rs.getString(8),db.rs.getString(9),db.rs.getString(10)};
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
        fr.tf_search.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from student where std_id=?");
            db.stmt.setString(1, fr.tf_std_id.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
            try {
             db.stmt=db.con.prepareStatement("update student set std_name=?,father_name=?,department=?,Address=?,phone_no=?,"
                   + "std_dob=?,std_age=?,hostel_id=? where std_id=?");                         
            db.stmt.setString(1, fr.tf_std_name.getText());
            db.stmt.setString(2, fr.tf_std_fn.getText());
            db.stmt.setString(3, fr.tf_std_dept.getText());
            db.stmt.setString(4, fr.tf_std_addrs.getText());
            db.stmt.setString(5, fr.tf_phn.getText());
            db.stmt.setString(6, fr.date.getEditor().getText());
            db.stmt.setString(7, fr.tf_std_age.getText());
            db.stmt.setString(8,fr.tf_hstl_id.getText());
            db.stmt.setString(9, fr.tf_std_id.getText());
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Updated");
            ViewTableData();
            }
            catch(Exception ex)
             {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else
                fr.valid.setText("Select Valid ID");
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
            db.stmt=db.con.prepareStatement("select * from student where std_id=?");
            db.stmt.setString(1, fr.tf_search.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
              try {
                    db.stmt=db.con.prepareStatement("select * from student where std_id=?");
                    db.stmt.setString(1, fr.tf_search.getText());
                    db.rs=db.stmt.executeQuery();
                    if(db.rs.next())
                    {
                        fr.tf_std_id.setText(db.rs.getString(1));
                        fr.tf_std_name.setText(db.rs.getString(2));
                        fr.tf_std_fn.setText(db.rs.getString(3));
                        fr.tf_std_dept.setText(db.rs.getString(4));
                        fr.tf_std_addrs.setText(db.rs.getString(5));
                        fr.tf_phn.setText(db.rs.getString(6));
                        fr.date.getEditor().setText(db.rs.getString(7));
                        fr.tf_std_age.setText(db.rs.getString(8));
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
        fr.tf_std_id.setText("");
        fr.tf_std_name.setText("");
        fr.tf_std_fn.setText("");      
        fr.tf_std_dept.setText("");
        fr.tf_std_addrs.setText("");
        fr.tf_phn.setText("");
        fr.date.getEditor().setText("");
        fr.tf_std_age.setText("");
        fr.tf_hstl_id.setText("");
        fr.valid.setText("");
    }
    public void FillDataInFields()
    {
       
        fr.valid.setText("");
        int i= fr.std_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.std_table.getModel();
         fr.tf_std_id.setText(tb.getValueAt(i, 0).toString());
         id=tb.getValueAt(i, 0).toString();
         
         fr.tf_std_name.setText(tb.getValueAt(i, 1).toString());
         name=tb.getValueAt(i, 1).toString();
         
         fr.tf_std_fn.setText(tb.getValueAt(i, 2).toString());
         fr.tf_std_dept.setText(tb.getValueAt(i, 3).toString());
         fr.tf_std_addrs.setText(tb.getValueAt(i, 4).toString());
         fr.tf_phn.setText(tb.getValueAt(i, 5).toString());
         fr.date.getEditor().setText(tb.getValueAt(i, 6).toString());
         fr.tf_std_age.setText(tb.getValueAt(i, 7).toString());
         fr.tf_hstl_id.setText(tb.getValueAt(i, 8).toString());
         fr.tf_search.setText("");
    }
    public void NumberValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE ||c==KeyEvent.VK_SPACE ))
        {
            if(evt.getComponent()== fr.tf_phn)
            fr.tf_phn.setEditable(false);
            else if(evt.getComponent()== fr.tf_std_age)
            fr.tf_std_age.setEditable(false);
            
            fr.valid.setText("Invalid num");
        }
        else
        {
            if(evt.getComponent()== fr.tf_phn)
            fr.tf_phn.setEditable(true);
            else if(evt.getComponent()== fr.tf_phn)
            fr.tf_std_age.setEditable(true);
            
            fr.valid.setText("");
        }
    }
     public void CharValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isLowerCase(c) ||Character.isUpperCase(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_SPACE  ))
        {
            if(evt.getComponent()== fr.tf_std_name)
            fr.tf_std_name.setEditable(false);
            else if(evt.getComponent()== fr.tf_std_fn)
            fr.tf_std_fn.setEditable(false);
            
            fr.valid.setText("Invalid Character");
        }
        else
        {
            if(evt.getComponent()== fr.tf_std_name)
            fr.tf_std_name.setEditable(true);
            else if(evt.getComponent()== fr.tf_std_fn)
            fr.tf_std_fn.setEditable(true);
            
            
            fr.valid.setText("");
        }
    }
    public void OpenAssignRoomForm()
    {
        int i= fr.std_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.std_table.getModel();
        if(i>=0)
        {
        Assign_Room mfr=new Assign_Room(this);
        mfr.setVisible(true);
        fr.hide();
        }
         else 
            fr.valid.setText("Select some Row");
    }
    public void BackPage()
    {
        Main_Page mfr=new Main_Page();
        mfr.setVisible(true);
        fr.hide(); 
    }
     public void OpenFeeForm()
    {
        int i= fr.std_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.std_table.getModel();
        if(i>=0)
        {
        Fee_Form mfr=new Fee_Form(this);
        mfr.setVisible(true);
        fr.hide();
        }
         else 
            fr.valid.setText("Select some Row");
    }
    public void OpenVisitorsForm()
    {
         int i= fr.std_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.std_table.getModel();
        if(i>=0)
        {
         Visitors_form vfr=new Visitors_form(this);
        vfr.setVisible(true);
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
