
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
public class InvoiceCtrl {
    Invoice_Form fr;
    DBHandler db;
   
    public InvoiceCtrl(Invoice_Form ifr1)
    {
        fr=ifr1;
        db=new DBHandler();
    }
    public void backPage()
    {
        Main_Page mfr=new Main_Page();
        mfr.setVisible(true);
        fr.hide();
        
    }
    public void Logout()
    {
        LogInFrame lfr=new LogInFrame();
        lfr.setVisible(true);
        fr.hide();
    }
     public void ViewTableData()
    {   
        fr.valid.setText("");
        
        db.dbConnection();
        try {
            db.stmt=db.con.prepareStatement("select * from fee where fee_status='1'");
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
    public void ClearTable()
    {
        DefaultTableModel tb=(DefaultTableModel)fr.fee_table.getModel();
        tb.setRowCount(0);
    }
     public void GenerateInvoices()
    { 
         int check=JOptionPane.showConfirmDialog(null, "Are u sure u want to Delete");
         if(check==0)
         {
        int i= fr.fee_table.getSelectedRow();
        if(i>=0)
        {
            DefaultTableModel tb=(DefaultTableModel) fr.fee_table.getModel();
        
            fr.setVisible(true);
            fr.Invoice.setText("*******************************\n");
            fr.Invoice.setText("************INVOICE************\n");
            Date d=new Date();
            String date=d.toString();
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+date+"\n\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"Student Id                    "+tb.getValueAt(i, 0).toString()+"\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"Student name              "+tb.getValueAt(i, 1).toString()+"\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"Mess Charges              "+tb.getValueAt(i, 2).toString()+"\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"Utility Bills                 "+tb.getValueAt(i, 3).toString()+"\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"Room Charges            "+tb.getValueAt(i, 4).toString()+"\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"*********************************"+"\n");
            fr.Invoice.setText(fr.Invoice.getText()+"\n"+"Total Expenses          "+tb.getValueAt(i, 5).toString()+"\n");
       } 
        else 
            fr.valid.setText("Select some Row");
         }
    }
     public void clearData()
     {
         fr.Invoice.setText("");
     }
}
