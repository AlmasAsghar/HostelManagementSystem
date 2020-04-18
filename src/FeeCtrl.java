
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;
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
public class FeeCtrl {
    DBHandler db;
    Fee_Form fr;
   
    public FeeCtrl(Fee_Form fr1)
    {
        fr=fr1;
        db=new DBHandler();
        
    }
    private boolean isValidFields()
    {
        if( fr.tf_std_id.getSelectedItem().toString().isEmpty() | fr.tf_std_name.getText().isEmpty() | fr.tf_mess_exp.getText().isEmpty()
                | fr.tf_utility_bills.getText().isEmpty() | fr.tf_room_fee.getText().isEmpty() | fr.tf_fee_status.getText().isEmpty())
        {
           fr.valid.setText("Fill All The Fields");
           fr.search.setText("");
           return false;
        }
        if(   fr.tf_std_name.getText().length()>20 | fr.tf_mess_exp.getText().length()>20
                | fr.tf_utility_bills.getText().length()>20 | fr.tf_room_fee.getText().length()>20 | fr.tf_fee_status.getText().length()>20)
        {
           
            if(fr.tf_std_name.getText().length()>20)
            fr.tf_std_name.setText("");
            else if(fr.tf_mess_exp.getText().length()>20)
            fr.tf_mess_exp.setText("");
            else if(fr.tf_utility_bills.getText().length()>20)
            fr.tf_utility_bills.setText("");
            else if(fr.tf_room_fee.getText().length()>20)
            fr.tf_room_fee.setText("");
            else if(fr.tf_fee_status.getText().length()>20)
            fr.tf_fee_status.setText("");
            
           fr.valid.setText("Fill with valid length 20");
           fr.search.setText("");
           return false;
        }
        if(isInteger(fr.tf_fee_status.getText()))
        {
            fr.valid.setText("Enter numeric value");
            return false;
        }
        if(isValidNumber(fr.tf_fee_status.getText()))
        {
           fr.valid.setText("Enter 0 or 1 Only");
           return false;
        }
        return true;
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
     private boolean isInteger(String s) {
         
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
        fr.tf_std_id.addItem(fr.st.id);
        fr.tf_std_name.setText(fr.st.name);
         /*db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select std_id from Student ");
            db.rs=db.stmt.executeQuery();
            while(db.rs.next())
            {
                fr.tf_std_id.addItem(db.rs.getString("std_id"));
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             }*/ 
    }
    public void SelectValueDataInCb()
    {
         db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from fee where std_id=? ");
                    db.stmt.setString(1, fr.tf_std_id.getSelectedItem().toString());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next())
            {
                //fr.tf_std_name.setText(db.rs.getString("std_name"));
                fr.tf_std_id.setSelectedItem(db.rs.getString(1));
                fr.tf_std_name.setText(db.rs.getString(2));
                fr.tf_mess_exp.setText(db.rs.getString(3));
                fr.tf_utility_bills.setText(db.rs.getString(4));
                fr.tf_room_fee.setText(db.rs.getString(5));
                fr.tf_fee_status.setText(db.rs.getString(7));
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             } 
    }
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.fee_table.getModel();
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
            db.stmt=db.con.prepareStatement("select * from fee where std_id=?");
            db.stmt.setString(1, fr.tf_std_id.getSelectedItem().toString());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {    
                 fr.valid.setText("ID Already Exist ");
            }
            else
            {  
                  fr.valid.setText("");
              try{ 
                    
                    fr.valid.setText("");
                    db.stmt=db.con.prepareStatement("Insert into fee(std_id,std_name,mess_fee,utility_bills,room_fee,total_expenses,fee_status)values (?,?,?,?,?,?,?)");
                    db.stmt.setString(1, fr.tf_std_id.getSelectedItem().toString());
                    db.stmt.setString(2, fr.tf_std_name.getText());
                    db.stmt.setString(3, fr.tf_mess_exp.getText());
                    db.stmt.setString(4, fr.tf_utility_bills.getText());
                    db.stmt.setString(5, fr.tf_room_fee.getText());
                     int total=Integer.parseInt(fr.tf_mess_exp.getText())+Integer.parseInt(fr.tf_utility_bills.getText())+
                            Integer.parseInt(fr.tf_room_fee.getText());
  
                    db.stmt.setString(6,Integer.toString(total));
                    db.stmt.setString(7,fr.tf_fee_status.getText() );
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
    public void ViewTableData()
    {   
        fr.valid.setText("");
        fr.search.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from fee");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.fee_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                 Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7)};
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
         try{ 
                    
                    fr.valid.setText("");
                    db.stmt=db.con.prepareStatement("update fee set std_name=?,mess_fee=?,utility_bills=?,room_fee=?,total_expenses=?,fee_status=? where std_id=?");
                    db.stmt.setString(1, fr.tf_std_name.getText());
                    db.stmt.setString(2, fr.tf_mess_exp.getText());
                    db.stmt.setString(3, fr.tf_utility_bills.getText());
                    db.stmt.setString(4, fr.tf_room_fee.getText());
                     int total=Integer.parseInt(fr.tf_mess_exp.getText())+Integer.parseInt(fr.tf_utility_bills.getText())+
                            Integer.parseInt(fr.tf_room_fee.getText());
                    db.stmt.setString(5, Integer.toString(total));
                    db.stmt.setString(6, fr.tf_fee_status.getText());
                    db.stmt.setString(7, fr.tf_std_id.getSelectedItem().toString());
                    db.stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Sucessfully Updated");
                    ViewTableData();
                    }
              catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             }    
      }
         }
     }
   public void SearchData()
   {
       if(!fr.search.getText().isEmpty())
       {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from fee where std_id like ? OR std_name like ?");
            db.stmt.setString(1,fr.search.getText()+"%");
            db.stmt.setString(2,fr.search.getText()+"%");
           
            
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.fee_table.getModel();
             tb.setRowCount(0);
             while(db.rs.next())
             {
                 Object ob[]={db.rs.getString(1),db.rs.getString(2),db.rs.getString(3),db.rs.getString(4),
                     db.rs.getString(5),db.rs.getString(6),db.rs.getString(7)};
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
            db.stmt=db.con.prepareStatement("select * from fee where std_id=?");
            db.stmt.setString(1, fr.search.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
              try {
                    db.stmt=db.con.prepareStatement("select * from fee where std_id=?");
                    db.stmt.setString(1, fr.search.getText());
                    db.rs=db.stmt.executeQuery();
                    if(db.rs.next())
                    {
                        fr.tf_std_id.setSelectedItem(db.rs.getString(1));
                        fr.tf_std_name.setText(db.rs.getString(2));
                        fr.tf_mess_exp.setText(db.rs.getString(3));
                        fr.tf_utility_bills.setText(db.rs.getString(4));
                        fr.tf_room_fee.setText(db.rs.getString(5));
                        fr.tf_fee_status.setText(db.rs.getString(7));
                       
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
          fr.tf_std_id.setSelectedItem("");
          fr.tf_std_name.setText("");
          fr.tf_mess_exp.setText("");
          fr.tf_utility_bills.setText("");
          fr.tf_room_fee.setText("");
          fr.tf_fee_status.setText("");
          fr.valid.setText("");
      }
    public void FillDataInFields()
     {
        fr.valid.setText("");
        int i= fr.fee_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.fee_table.getModel();
        fr.tf_std_id.setSelectedItem(tb.getValueAt(i, 0).toString());
        fr.tf_std_name.setText(tb.getValueAt(i, 1).toString());
        fr.tf_mess_exp.setText(tb.getValueAt(i, 2).toString());
        fr.tf_utility_bills.setText(tb.getValueAt(i, 3).toString());
        fr.tf_room_fee.setText(tb.getValueAt(i, 4).toString());
        fr.tf_fee_status.setText(tb.getValueAt(i, 6).toString());
     }
     public void DeleteData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
        if(!fr.tf_std_id.getSelectedItem().toString().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("delete from fee where std_id=?");
            db.stmt.setString(1,fr.tf_std_id.getSelectedItem().toString() );
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
      public void CharValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isLowerCase(c) ||Character.isUpperCase(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_SPACE ))
        {
            if(evt.getComponent()== fr.tf_std_name)
            fr.tf_std_name.setEditable(false);
          
            
            fr.valid.setText("Invalid Character");
        }
        else
        {
            if(evt.getComponent()== fr.tf_std_name)
            fr.tf_std_name.setEditable(true);
            
            
            
            fr.valid.setText("");
        }
    }
    public void NumberValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE ))
        {
            if(evt.getComponent()== fr.tf_mess_exp)
            fr.tf_mess_exp.setEditable(false);
            else if(evt.getComponent()== fr.tf_utility_bills)
            fr.tf_utility_bills.setEditable(false);
            else if(evt.getComponent()== fr.tf_room_fee)
            fr.tf_room_fee.setEditable(false);
            
            fr.valid.setText("Invalid num");
        }
        else
        {
            if(evt.getComponent()== fr.tf_mess_exp)
            fr.tf_mess_exp.setEditable(true);
            else if(evt.getComponent()== fr.tf_utility_bills)
            fr.tf_utility_bills.setEditable(true);
            else if(evt.getComponent()== fr.tf_room_fee)
            fr.tf_room_fee.setEditable(true);
            
            fr.valid.setText("");
        }   
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
