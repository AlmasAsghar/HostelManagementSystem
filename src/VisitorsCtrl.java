
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
public class VisitorsCtrl {
     DBHandler db;
     Visitors_form fr;
     
    public VisitorsCtrl(Visitors_form fr1) {
        db=new DBHandler();
        fr=fr1;    
    }
    private boolean isValidFields()
   {
       if( fr.tf_vst_id.getText().trim().isEmpty() | fr.tf_vst_name.getText().trim().isEmpty() | fr.tf_std_id.getSelectedItem().toString().isEmpty() | 
           fr.tf_std_name.getText().trim().isEmpty() | fr.tf_time_in.getText().trim().isEmpty() | fr.tf_time_out.getText().trim().isEmpty() |
           fr.date.getEditor().getText().trim().isEmpty() )
       {
           fr.valid.setText("Fill All The Fields");
           fr.search.setText("");
           return false;
       }
       else if( fr.tf_vst_id.getText().length()>20 | fr.tf_vst_name.getText().length()>20  | 
           fr.tf_std_name.getText().length()>20 | fr.tf_time_in.getText().length()>20 | fr.tf_time_out.getText().length()>20 |
           fr.date.getEditor().getText().length()>50 )
       {
           if(fr.tf_vst_id.getText().length()>20)
           fr.tf_vst_id.setText("");
           else if(fr.tf_vst_name.getText().length()>20)
           fr.tf_vst_name.setText("");
           else if(fr.tf_std_name.getText().length()>20)
           fr.tf_std_name.setText("");
           else if(fr.tf_time_in.getText().length()>20)
           fr.tf_time_in.setText("");
           else if(fr.tf_time_out.getText().length()>20)
           fr.tf_time_out.setText("");
           if(fr.date.getEditor().getText().length()>50 )
           fr.date.getEditor().setText("");
           
           fr.valid.setText("Fill with valid legth 20");
           fr.search.setText("");
           return false;
       }
        
       return true;
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
             } */
    }
    public void SelectValueDataInCb()
    {
         db.dbConnection(); 
        try {
            db.stmt=db.con.prepareStatement("select * from visitors where std_id=? ");
                    db.stmt.setString(1, fr.tf_std_id.getSelectedItem().toString());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next())
            {
                //fr.tf_std_name.setText(db.rs.getString("std_name"));
                fr.tf_vst_id.setText(db.rs.getString(1));
                fr.tf_vst_name.setText(db.rs.getString(2));
                fr.tf_std_id.setSelectedItem((db.rs.getString(3)));
                fr.tf_std_name.setText(db.rs.getString(4));
                fr.tf_time_in.setText(db.rs.getString(5));
                fr.tf_time_out.setText(db.rs.getString(6));
                fr.date.getEditor().setText(db.rs.getString(7));
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
             } 
    }
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.vst_table.getModel();
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
            db.stmt=db.con.prepareStatement("select * from visitors where Vst_id=?");
            db.stmt.setString(1, fr.tf_vst_id.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
                fr.valid.setText("ID Aready Exist");
            }
            else
            {   
                 fr.valid.setText("");
              try{ 
                    
                    fr.valid.setText("");
                    db.stmt=db.con.prepareStatement("Insert into visitors(Vst_id,Vst_name,std_id,std_name,time_in,time_out,date)values (?,?,?,?,?,?,?)");
                    db.stmt.setString(1, fr.tf_vst_id.getText());
                    db.stmt.setString(2, fr.tf_vst_name.getText());
                    db.stmt.setString(3, fr.tf_std_id.getSelectedItem().toString());
                    db.stmt.setString(4, fr.tf_std_name.getText());
                    db.stmt.setString(5, fr.tf_time_in.getText());
                    db.stmt.setString(6, fr.tf_time_out.getText());
                    db.stmt.setString(7, fr.date.getEditor().getText());
                    
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
        if(!fr.tf_vst_id.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("delete from visitors where Vst_id=?");
            db.stmt.setString(1,fr.tf_vst_id.getText() );
            db.stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            fr.search.setText("");
            Reset();
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
            db.stmt=db.con.prepareStatement("select * from visitors");
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.vst_table.getModel();
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
    public void SearchTable()
    {
        if(!fr.search.getText().isEmpty())
        {
        fr.valid.setText("");
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("Select * from visitors where Vst_id like ? OR Vst_name like ? OR std_id like ? OR std_name like ? ");
            db.stmt.setString(1,fr.search.getText()+"%");
            db.stmt.setString(2,fr.search.getText()+"%");
            db.stmt.setString(3,fr.search.getText()+"%");
            db.stmt.setString(4,fr.search.getText()+"%");
           
            
             db.rs=db.stmt.executeQuery();
             DefaultTableModel tb=(DefaultTableModel)fr.vst_table.getModel();
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
             db.stmt=db.con.prepareStatement("update visitors set Vst_name=?,std_id=?,std_name=?,time_in=?,time_out=?,"
                   + "date=? where Vst_id=?");                         
            db.stmt.setString(1, fr.tf_vst_name.getText());
            db.stmt.setString(2, fr.tf_std_id.getSelectedItem().toString());
            db.stmt.setString(3, fr.tf_std_name.getText());
            db.stmt.setString(4, fr.tf_time_in.getText());
            db.stmt.setString(5, fr.tf_time_out.getText());
            db.stmt.setString(6, fr.date.getEditor().getText());
            db.stmt.setString(7, fr.tf_vst_id.getText());
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
            db.stmt=db.con.prepareStatement("select * from visitors where Vst_id=?");
            db.stmt.setString(1, fr.search.getText());
            db.rs=db.stmt.executeQuery();
            if(db.rs.next()==true)
            {
              try {
                    db.stmt=db.con.prepareStatement("select * from visitors where Vst_id=?");
                    db.stmt.setString(1, fr.search.getText());
                    db.rs=db.stmt.executeQuery();
                    if(db.rs.next())
                    {
                        fr.tf_vst_id.setText(db.rs.getString(1));
                        fr.tf_vst_name.setText(db.rs.getString(2));
                        fr.tf_std_id.setSelectedItem((db.rs.getString(3)));
                        fr.tf_std_name.setText(db.rs.getString(4));
                        fr.tf_time_in.setText(db.rs.getString(5));
                        fr.tf_time_out.setText(db.rs.getString(6));
                        fr.date.getEditor().setText(db.rs.getString(7));
                        
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
        fr.tf_vst_id.setText("");
        fr.tf_vst_name.setText("");
        fr.tf_std_id.setSelectedItem((""));      
        fr.tf_std_name.setText("");
        fr.tf_time_in.setText("");
        fr.tf_time_out.setText("");
        fr.date.getEditor().setText("");
        fr.valid.setText("");
    }
    public void FillDataInFields()
    {
        fr.valid.setText("");
        int i= fr.vst_table.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel) fr.vst_table.getModel();
         fr.tf_vst_id.setText(tb.getValueAt(i, 0).toString());
         fr.tf_vst_name.setText(tb.getValueAt(i, 1).toString());
         fr.tf_std_id.setSelectedItem(tb.getValueAt(i, 2).toString());
         fr.tf_std_name.setText(tb.getValueAt(i, 3).toString());
         fr.tf_time_in.setText(tb.getValueAt(i, 4).toString());
         fr.tf_time_out.setText(tb.getValueAt(i, 5).toString());
         fr.date.getEditor().setText(tb.getValueAt(i, 6).toString());
         fr.search.setText("");
    }
      public void CharValidation(java.awt.event.KeyEvent evt)
    {    
        char c=evt.getKeyChar();
        if(!(Character.isLowerCase(c) ||Character.isUpperCase(c) || c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_SPACE ))
        {
            if(evt.getComponent()== fr.tf_std_name)
            fr.tf_std_name.setEditable(false);
            else if(evt.getComponent()== fr.tf_vst_name)
            fr.tf_vst_name.setEditable(false);
            
            fr.valid.setText("Invalid Character");
        }
        else
        {
            if(evt.getComponent()== fr.tf_std_name)
            fr.tf_std_name.setEditable(true);
            else if(evt.getComponent()== fr.tf_vst_name)
            fr.tf_vst_name.setEditable(true);
            
            
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
