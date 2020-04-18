
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
public class MessCtrl {
    DBHandler db;
    Mess_Form fr;
    public MessCtrl(Mess_Form fr1)
    {
       fr=fr1;
       db=new DBHandler();
    }
    private boolean isValidFields()
    {
        if( fr.tf_incharge_id.getText().isEmpty()| fr.tf_hstl_id.getText().isEmpty() | fr.tf_mess_expnses.getText().isEmpty()| 
            fr.tf_bf_time.getText().isEmpty()| fr.tf_dn_time.getText().isEmpty() |fr.tf_ln_time.getText().isEmpty()
                |fr.tf_sbf_time.getText().isEmpty())
        {
           fr.valid.setText("Fill All The Fields");
           fr.search.setText("");
           return false;
        }
        if( fr.tf_incharge_id.getText().length()>20| fr.tf_hstl_id.getText().length()>20 | fr.tf_mess_expnses.getText().length()>20| 
            fr.tf_bf_time.getText().length()>20| fr.tf_dn_time.getText().length()>20 |fr.tf_ln_time.getText().length()>20
                |fr.tf_sbf_time.getText().length()>20)
        {
            if(fr.tf_incharge_id.getText().length()>20)
            fr.tf_incharge_id.setText("");
            else if(fr.tf_hstl_id.getText().length()>20)
            fr.tf_hstl_id.setText("");
            else if(fr.tf_mess_expnses.getText().length()>20)
            fr.tf_mess_expnses.setText("");
            else if(fr.tf_bf_time.getText().length()>20)
            fr.tf_bf_time.setText("");
            else if(fr.tf_ln_time.getText().length()>20)
            fr.tf_ln_time.setText("");
            else if(fr.tf_dn_time.getText().length()>20)
            fr.tf_dn_time.setText("");
            else if(fr.tf_sbf_time.getText().length()>20)
            fr.tf_sbf_time.setText("");
            
           fr.valid.setText("Fill with valid length 20");
           fr.search.setText("");
           return false;
        }
        return true;
    }
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.mess_table.getModel();
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
            db.stmt=db.con.prepareStatement("select * from mess where incharge_id=?");
            db.stmt.setString(1, fr.tf_incharge_id.getText());
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
                    db.stmt=db.con.prepareStatement("Insert into mess(incharge_id,hostel_id,monthly_avg_exp,mess_bf_time,mess_luch_time,mess_diner_time,sunday_bf_time)values (?,?,?,?,?,?,?)");
                    db.stmt.setString(1, fr.tf_incharge_id.getText());
                    db.stmt.setString(2, fr.tf_hstl_id.getText());
                    db.stmt.setString(3, fr.tf_mess_expnses.getText());
                    db.stmt.setString(4, fr.tf_bf_time.getText());
                    db.stmt.setString(5, fr.tf_ln_time.getText());
                    db.stmt.setString(6, fr.tf_dn_time.getText());
                    db.stmt.setString(7, fr.tf_sbf_time.getText());
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
            db.stmt=db.con.prepareStatement("select * from mess");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.mess_table.getModel();
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
                    
                    db.stmt=db.con.prepareStatement("update mess set hostel_id=? ,monthly_avg_exp=?, mess_bf_time=?, mess_luch_time=?, mess_diner_time=? ,sunday_bf_time=? where incharge_id=? ");
                 
                    db.stmt.setString(1, fr.tf_hstl_id.getText());
                    db.stmt.setString(2, fr.tf_mess_expnses.getText());
                    db.stmt.setString(3, fr.tf_bf_time.getText());
                    db.stmt.setString(4, fr.tf_ln_time.getText());
                    db.stmt.setString(5, fr.tf_dn_time.getText());
                    db.stmt.setString(6, fr.tf_sbf_time.getText());
                    db.stmt.setString(7, fr.tf_incharge_id.getText());
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
     public void SearchTable()
    {
        if(!fr.search.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from mess where incharge_id like ? OR hostel_id like ? ");
            db.stmt.setString(1,fr.search.getText()+"%");
            db.stmt.setString(2,fr.search.getText()+"%");
           
            
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.mess_table.getModel();
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
        } else 
           fr.valid.setText("Enter Some Thing");
    }
    public void Search()
    {
        fr.valid.setText("");
        db.dbConnection();
         try {
            db.stmt=db.con.prepareStatement("select * from mess where incharge_id=?");
            db.stmt.setString(1, fr.search.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
              try {
                    db.stmt=db.con.prepareStatement("select * from mess where incharge_id=?");
                    db.stmt.setString(1, fr.search.getText());
                    db.rs=db.stmt.executeQuery();
                    if(db.rs.next())
                    {
                        fr.tf_incharge_id.setText(db.rs.getString(1));
                        fr.tf_hstl_id.setText(db.rs.getString(2));
                        fr.tf_mess_expnses.setText(db.rs.getString(3));
                        fr.tf_bf_time.setText(db.rs.getString(4));
                        fr.tf_ln_time.setText(db.rs.getString(5));
                        fr.tf_dn_time.setText(db.rs.getString(6));
                        fr.tf_sbf_time.setText(db.rs.getString(7));
                       
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
          fr.tf_incharge_id.setText("");
          fr.tf_hstl_id.setText("");
          fr.tf_mess_expnses.setText("");
          fr.tf_bf_time.setText("");
          fr.tf_ln_time.setText("");
          fr.tf_dn_time.setText("");
          fr.tf_sbf_time.setText("");
          fr.valid.setText("");
      }
    public void FillDataInFields()
     {
        fr.valid.setText("");
        int i= fr.mess_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.mess_table.getModel();
        fr.tf_incharge_id.setText(tb.getValueAt(i, 0).toString());
        fr.tf_hstl_id.setText(tb.getValueAt(i, 1).toString());
        fr.tf_mess_expnses.setText(tb.getValueAt(i, 2).toString());
        fr.tf_bf_time.setText(tb.getValueAt(i, 3).toString());
        fr.tf_ln_time.setText(tb.getValueAt(i, 4).toString());
        fr.tf_dn_time.setText(tb.getValueAt(i, 5).toString());
        fr.tf_sbf_time.setText(tb.getValueAt(i, 6).toString());
     }
     public void NumberValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)|| c==KeyEvent.VK_BACK_SPACE))
        {
            if(evt.getComponent()== fr.tf_mess_expnses)
            fr.tf_mess_expnses.setEditable(false);
            fr.valid.setText("Invalid num");
        }
        else
        {
            if(evt.getComponent()== fr.tf_mess_expnses)
            fr.tf_mess_expnses.setEditable(true);
            fr.valid.setText("");
        }
        
    }
     public void DeleteData()
    {
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
        if(!fr.tf_incharge_id.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("delete from mess where incharge_id=?");
            db.stmt.setString(1,fr.tf_incharge_id.getText() );
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            fr.search.setText("");
            Reset();
            ViewTableData();
        } catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
            fr.valid.setText("Enter Id");
         }
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
