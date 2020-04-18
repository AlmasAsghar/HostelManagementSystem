
import javax.swing.*;
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
public class LogInFrame extends javax.swing.JFrame {

    LogInCtrl l;
    public LogInFrame() {
        initComponents();
        l=new LogInCtrl(this);
         
    }
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jpane = new javax.swing.JPanel();
        tf_id = new javax.swing.JTextField();
        tf_pswd = new javax.swing.JPasswordField();
        lb_pswd = new javax.swing.JLabel();
        lb_name = new javax.swing.JLabel();
        SignIn = new javax.swing.JButton();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SignIn _Frame");
        setResizable(false);

        Jpane.setBackground(new java.awt.Color(0, 204, 204));
        Jpane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        Jpane.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        Jpane.setName("LogIn_Frame"); // NOI18N

        tf_id.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        tf_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tf_pswd.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        tf_pswd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lb_pswd.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lb_pswd.setText("Pasword");
        lb_pswd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lb_name.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lb_name.setText("UserName");
        lb_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SignIn.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        SignIn.setText("LogIn");
        SignIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        SignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        title.setText("Hostel Management System");

        javax.swing.GroupLayout JpaneLayout = new javax.swing.GroupLayout(Jpane);
        Jpane.setLayout(JpaneLayout);
        JpaneLayout.setHorizontalGroup(
            JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JpaneLayout.createSequentialGroup()
                        .addGroup(JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_name, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(lb_pswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tf_pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpaneLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        JpaneLayout.setVerticalGroup(
            JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(JpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInActionPerformed
        l.LogIn();
    }//GEN-LAST:event_SignInActionPerformed

    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInFrame().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpane;
    private javax.swing.JButton SignIn;
    private javax.swing.JLabel lb_name;
    private javax.swing.JLabel lb_pswd;
    javax.swing.JTextField tf_id;
    javax.swing.JPasswordField tf_pswd;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
