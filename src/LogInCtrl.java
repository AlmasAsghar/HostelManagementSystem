
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Almas Asghar
 */
public class LogInCtrl {
    DBHandler db;
    LogInFrame fr;
    public LogInCtrl(LogInFrame fr1)
    {
       fr=fr1;
       db=new DBHandler();
    }
    public void LogIn()
    {
        String name;
         db.dbConnection();
        try
        {
           String sql="SELECT * from login where id=? and pswd=?";
           db.stmt=db.con.prepareStatement(sql);
           db.stmt.setString(1,fr.tf_id.getText());
           db.stmt.setString(2,fr.tf_pswd.getText());
           db.rs=db.stmt.executeQuery();
           if(db.rs.next())
           {
               name=db.rs.getString(1);
               if(fr.tf_id.getText().equals(name))
               {
               //JOptionPane.showMessageDialog(null, "Valid UserName and Pasword");
               Main_Page mfr=new Main_Page();
               mfr.setVisible(true);
               fr.hide();
               }
               else
                   JOptionPane.showMessageDialog(null, "InValid UserName and Pasword");
           }
           else 
           {
               JOptionPane.showMessageDialog(null, "InValid UserName and Pasword");
               fr.tf_id.setText("");
               fr.tf_pswd.setText("");
           }
           db.con.close();
           
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
