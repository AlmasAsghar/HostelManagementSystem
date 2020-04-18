
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
public class RoomCtrl {
    DBHandler db;
    Room_Form fr;
   
    public RoomCtrl(Room_Form fr1)
    {
        
        db=new DBHandler();
        fr=fr1;      
        
    }
    
    private boolean isValidFields()
    {
        if(fr.tf_hstl_id.getText().isEmpty() | fr.tf_room_category.getText().isEmpty() | fr.tf_room_id.getText().isEmpty()
           | fr.tf_room_status.getText().isEmpty() | fr.tf_students.getText().isEmpty())
        {
           fr.valid.setText("Fill All The Fields");
           fr.search.setText("");
           return false;
        }
        if(fr.tf_hstl_id.getText().length()>20 | fr.tf_room_category.getText().length()>20 | fr.tf_room_id.getText().length()>20
           | fr.tf_room_status.getText().length()>20 | fr.tf_students.getText().length()>45)
             {
                 if(fr.tf_hstl_id.getText().length()>20)
                     fr.tf_hstl_id.setText("");
                else if(fr.tf_room_category.getText().length()>20)
                     fr.tf_room_category.setText("");
                else if(fr.tf_room_id.getText().length()>20)
                     fr.tf_room_id.setText("");
                else if(fr.tf_room_status.getText().length()>20)
                     fr.tf_room_status.setText("");
                else if(fr.tf_students.getText().length()>20)
                     fr.tf_students.setText("");
                 
                fr.valid.setText("Fill With Proper Length 20");
                fr.search.setText("");
                return false;
        }
          if(isInteger(fr.tf_room_status.getText()))
        {
            fr.valid.setText("Enter numeric value");
            return false;
        }
        if(isValidNumber(fr.tf_room_status.getText()))
        {
           fr.valid.setText("Enter 0 or 1 Only");
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
    private boolean isValidNumber(String s)
    {
       int value=Integer.parseInt(s);
        if(value==0 || value==1 )
        {
            return false;
        }
        
        return true;             
}
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.room_table.getModel();
        tb.setRowCount(0);
    }
    public void InsertData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Insert");
         if(check==0)
         {
        fr.valid.setText("");
        fr.search.setText("");
        if(isValidFields()){
        db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from room where room_id=?");
            db.stmt.setString(1, fr.tf_room_id.getText());
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
                    db.stmt=db.con.prepareStatement("Insert into room(room_id,category,room_status,students_id,hostel_id)values (?,?,?,?,?)");
                    db.stmt.setString(1, fr.tf_room_id.getText());
                    db.stmt.setString(2, fr. tf_room_category.getText());
                    db.stmt.setString(3, fr.tf_room_status.getText());
                    db.stmt.setString(4, fr.tf_students.getText());
                    db.stmt.setString(5, fr.tf_hstl_id.getText());
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
    public void deleteData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
        if(!fr.tf_room_id.getText().isEmpty())
        {
        db.dbConnection();
         fr.valid.setText("");
        try {
            db.stmt=db.con.prepareStatement("delete from room where room_id=?");
            db.stmt.setString(1,fr.tf_room_id.getText() );
            db.stmt.executeUpdate();
            fr.search.setText("");
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
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
            db.stmt=db.con.prepareStatement("select * from room");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.room_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                 Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5)};
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
        if(isValidFields()){
        fr.valid.setText("");
        fr.search.setText("");
       
        db.dbConnection();
         try {
            db.stmt=db.con.prepareStatement("select * from room where room_id=?");
            db.stmt.setString(1, fr.tf_room_id.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
        try{  
                    fr.valid.setText("");
                    db.stmt=db.con.prepareStatement("update room set category=?, room_status=?, students_id=? ,hostel_id=? where room_id=?");
                    db.stmt.setString(1, fr. tf_room_category.getText());
                    db.stmt.setString(2, fr.tf_room_status.getText()); 
                    db.stmt.setString(3, fr.tf_students.getText() );
                    db.stmt.setString(4, fr.tf_hstl_id.getText());
                    db.stmt.setString(5, fr.tf_room_id.getText());
                    db.stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Sucessfully Updated");
                    ViewTableData();
                    }
              catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             } 
            }
            else 
           fr.valid.setText("ID Already Exist");
         }
             catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             } 
        }
         }
     }
    public void SearchTable()
    {
        if(!fr.search.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from room where room_id like ? OR category like ? OR room_status like ? OR students_id like ? OR hostel_id like ?");
            db.stmt.setString(1,fr.search.getText()+"%");
            db.stmt.setString(2,fr.search.getText()+"%");
            db.stmt.setString(3,fr.search.getText()+"%");
            db.stmt.setString(4,fr.search.getText()+"%");
            db.stmt.setString(5,fr.search.getText()+"%");
            
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.room_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                 System.out.println(db.rs.getString(1));
                 Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5)};
                 tb.addRow(ob);
             }  
        } catch (SQLException ex) {
            Logger.getLogger(Room_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         else 
           fr.valid.setText("Enter Some Thing");
    }
    public void Search()
    {
        fr.valid.setText("");
        db.dbConnection();
         try {
            db.stmt=db.con.prepareStatement("select * from room where room_id=?");
            db.stmt.setString(1, fr.search.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
              try {
                    db.stmt=db.con.prepareStatement("select * from room where room_id=?");
                    db.stmt.setString(1, fr.search.getText());
                    db.rs=db.stmt.executeQuery();
                    if(db.rs.next())
                    {
                        fr.tf_room_id.setText(db.rs.getString(1));
                        fr.tf_room_category.setText(db.rs.getString(2));
                        fr. tf_room_status.setText(db.rs.getString(3));
                        fr.tf_students.setText(db.rs.getString(4));
                        fr.tf_hstl_id.setText(db.rs.getString(5));
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
          fr.tf_room_id.setText("");
          fr.tf_hstl_id.setText("");
          fr.tf_room_category.setText("");
          fr.tf_room_status.setText("");
          fr.tf_students.setText("");
          fr.valid.setText("");
      }
      public void FillDataInFields()
     {
        fr.valid.setText("");
        int i= fr.room_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.room_table.getModel();
        fr.tf_room_id.setText(tb.getValueAt(i, 0).toString());
        fr.tf_room_category.setText(tb.getValueAt(i, 1).toString());
        fr.tf_room_status.setText(tb.getValueAt(i, 2).toString());
        fr.tf_students.setText(tb.getValueAt(i, 3).toString());
         fr.tf_hstl_id.setText(tb.getValueAt(i, 4).toString());
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
}
