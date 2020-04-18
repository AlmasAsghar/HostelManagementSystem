
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Almas Asghar
 */
public class Assign_Room extends javax.swing.JFrame {

    Assign_RoomCtrl rm;
    StudentCtrl st;
    public Assign_Room(StudentCtrl stt) {
        initComponents();
        rm=new Assign_RoomCtrl(this);
        st=stt;
        rm.setNameField();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Room_pan = new javax.swing.JPanel();
        room_title = new javax.swing.JLabel();
        students = new javax.swing.JLabel();
        std_adr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        room_table = new javax.swing.JTable();
        clear = new javax.swing.JButton();
        back_btn1 = new javax.swing.JButton();
        Logout_btn3 = new javax.swing.JButton();
        search_btn = new javax.swing.JButton();
        View_btn1 = new javax.swing.JButton();
        valid = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        Update_btn1 = new javax.swing.JButton();
        room_id = new javax.swing.JLabel();
        tf_room_id = new javax.swing.JTextField();
        tf_students = new javax.swing.JComboBox();
        room_status = new javax.swing.JLabel();
        tf_room_status = new javax.swing.JTextField();
        ClearT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Room_Information");
        setName("room_frame"); // NOI18N
        setResizable(false);

        Room_pan.setBackground(new java.awt.Color(0, 204, 204));
        Room_pan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 10));
        Room_pan.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        room_title.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        room_title.setText("Room Information Page");

        students.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        students.setText("Students");

        std_adr.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        room_table.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        room_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room_id", "Room_category", "Room_Status", "Students", "Hostel_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        room_table.setRowHeight(20);
        room_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                room_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(room_table);

        clear.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        clear.setText("Clear");
        clear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        back_btn1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        back_btn1.setText("Back");
        back_btn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        back_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn1ActionPerformed(evt);
            }
        });

        Logout_btn3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        Logout_btn3.setText("Logout");
        Logout_btn3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Logout_btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout_btn3ActionPerformed(evt);
            }
        });

        search_btn.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        search_btn.setText("Search");
        search_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        View_btn1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        View_btn1.setText("View Info");
        View_btn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        View_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_btn1ActionPerformed(evt);
            }
        });

        valid.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        valid.setForeground(new java.awt.Color(255, 0, 0));

        search.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        Update_btn1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        Update_btn1.setText("Update Info");
        Update_btn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Update_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_btn1ActionPerformed(evt);
            }
        });

        room_id.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        room_id.setText("Room Id");

        tf_room_id.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        tf_room_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tf_room_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_room_idActionPerformed(evt);
            }
        });

        tf_students.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        tf_students.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tf_students.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                tf_studentsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        room_status.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        room_status.setText("Room Status");

        tf_room_status.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        tf_room_status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        ClearT.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        ClearT.setText("ClearTable");
        ClearT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ClearT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Room_panLayout = new javax.swing.GroupLayout(Room_pan);
        Room_pan.setLayout(Room_panLayout);
        Room_panLayout.setHorizontalGroup(
            Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Room_panLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(room_title, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(362, 362, 362))
            .addGroup(Room_panLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Room_panLayout.createSequentialGroup()
                        .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Room_panLayout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(std_adr, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Room_panLayout.createSequentialGroup()
                        .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(room_status, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(students, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(room_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Update_btn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Room_panLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_room_status, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_room_id)
                                        .addComponent(tf_students, 0, 151, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Room_panLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))))
                    .addComponent(valid, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Room_panLayout.createSequentialGroup()
                        .addGap(0, 56, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Room_panLayout.createSequentialGroup()
                        .addComponent(View_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(ClearT, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(Logout_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        Room_panLayout.setVerticalGroup(
            Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Room_panLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(room_id)
                    .addComponent(tf_room_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Room_panLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(students))
                    .addGroup(Room_panLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(tf_students, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(std_adr)
                .addGap(21, 21, 21)
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_room_status, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_status))
                .addGap(33, 33, 33)
                .addComponent(valid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(Room_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(View_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Logout_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(Room_panLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(room_title, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Room_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Room_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn1ActionPerformed
        rm.BackPage();
    }//GEN-LAST:event_back_btn1ActionPerformed

    private void Logout_btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_btn3ActionPerformed
        rm.LogOut();
    }//GEN-LAST:event_Logout_btn3ActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed

        rm.SearchTable();
    }//GEN-LAST:event_search_btnActionPerformed

    private void View_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_btn1ActionPerformed
        rm.ViewTableData();
    }//GEN-LAST:event_View_btn1ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        rm.ClearTable();
    }//GEN-LAST:event_clearActionPerformed

    private void room_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_room_tableMouseClicked
        //rm.FillDataInFields();
    }//GEN-LAST:event_room_tableMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void Update_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_btn1ActionPerformed
       rm.UpdateData();
    }//GEN-LAST:event_Update_btn1ActionPerformed

    private void tf_room_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_room_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_room_idActionPerformed

    private void tf_studentsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_tf_studentsPopupMenuWillBecomeInvisible
        //RM.SelectValueDataInCb();
    }//GEN-LAST:event_tf_studentsPopupMenuWillBecomeInvisible

    private void ClearTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearTActionPerformed
        rm.ClearTable();                                                        // TODO add your handling code here:
    }//GEN-LAST:event_ClearTActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearT;
    private javax.swing.JButton Logout_btn3;
    private javax.swing.JPanel Room_pan;
    private javax.swing.JButton Update_btn1;
    private javax.swing.JButton View_btn1;
    private javax.swing.JButton back_btn1;
    private javax.swing.JButton clear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel room_id;
    private javax.swing.JLabel room_status;
    javax.swing.JTable room_table;
    private javax.swing.JLabel room_title;
    javax.swing.JTextField search;
    private javax.swing.JButton search_btn;
    private javax.swing.JLabel std_adr;
    private javax.swing.JLabel students;
    javax.swing.JTextField tf_room_id;
    javax.swing.JTextField tf_room_status;
    javax.swing.JComboBox tf_students;
    javax.swing.JLabel valid;
    // End of variables declaration//GEN-END:variables
}
