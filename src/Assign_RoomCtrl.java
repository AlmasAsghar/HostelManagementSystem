
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
public class Assign_RoomCtrl {
    DBHandler db;
    Assign_Room fr;
   
    public Assign_RoomCtrl(Assign_Room fr1)
    {
       
        db=new DBHandler();
        fr=fr1;      
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
    public void setNameField()
    {
        fr.tf_students.addItem(fr.st.id);
    }
    public boolean isValidFields()
    {
        if(fr.tf_room_id.getText().isEmpty() | fr.tf_room_status.getText().isEmpty())
        {
           fr.valid.setText("Fill All The Fields");
           fr.search.setText("");
           return false;
        }
        else if(fr.tf_room_id.getText().length()>20 | fr.tf_room_status.getText().length()>20)
        {
            fr.valid.setText("Fil with proper lenth 20");
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
    
     public String getNames()
     {
        String name="";
         db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from room where room_id=?");
            db.stmt.setString(1, fr.tf_room_id.getText());
             db.rs=db.stmt.executeQuery();
            while(db.rs.next())
            {
                name=db.rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
     }
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.room_table.getModel();
        tb.setRowCount(0);
    }
     public void UpdateData()
     {
          int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to update");
        if(check==0)
        {
           
        fr.valid.setText("");
        fr.search.setText("");
        if(isValidFields())
        {
        String n=(getNames().concat(",")+fr.tf_students.getSelectedItem());
        db.dbConnection();
         try {
            db.stmt=db.con.prepareStatement("select * from room where room_id=?");
            db.stmt.setString(1, fr.tf_room_id.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
        try{  
                    fr.valid.setText("");
                    db.stmt=db.con.prepareStatement("update room set students_id=?,room_status=?  where room_id=?");
                    db.stmt.setString(1,n );
                    db.stmt.setString(2, fr.tf_room_status.getText()); 
                    db.stmt.setString(3, fr.tf_room_id.getText());
                    db.stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Sucessfully Updated");
                    ViewTableData();
                    }
              catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             } 
        }
         else 
           fr.valid.setText("ID not Exist");
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
     public void FillDataInFields()
     {
        fr.valid.setText("");
        int i= fr.room_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.room_table.getModel();
        fr.tf_room_id.setText(tb.getValueAt(i, 0).toString());
        fr.tf_room_id.setText(tb.getValueAt(i, 2).toString());
        
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
    public void BackPage()
    {
        Student_Form mfr=new Student_Form();
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
