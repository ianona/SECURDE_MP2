/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SQLite;
import Controller.SecurityConfig;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author beepxD
 */
public class MgmtUser extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;

    public MgmtUser(SQLite sqlite) {
        initComponents();
        this.sqlite = sqlite;
        tableModel = (DefaultTableModel) table.getModel();
        table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (table.getSelectedRow() < 0 || Frame.getCurUser().getRole() == 2) {
                return;
            }

            String selectedUsername = table.getValueAt(table.getSelectedRow(), 0).toString();
            int selectedRole = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 1).toString().substring(0, 1));
            User curUser = Frame.getCurUser();
//            switch (curUser.getRole()){
//                // managers can only 
//                case 4:
//            }
//            
            // only admin can change everyone's password
            // every other account can only change their own password
            chgpassBtn.setVisible(true);
            if (curUser.getRole() != 5) {
                if (!curUser.getUsername().equals(selectedUsername)) {
                    chgpassBtn.setVisible(false);
                }
            }

            deleteBtn.setVisible(true);
            lockBtn.setVisible(true);
            editRoleBtn.setVisible(true);
            if (curUser.getUsername().equals(selectedUsername)) {
                deleteBtn.setVisible(false);
                lockBtn.setVisible(false);
                editRoleBtn.setVisible(false);
            }
        });

//        UNCOMMENT TO DISABLE BUTTONS
//        editBtn.setVisible(false);
//        deleteBtn.setVisible(false);
//        lockBtn.setVisible(false);
//        chgpassBtn.setVisible(false);
    }

    public void init() {
        // only admin can delete accounts
        deleteBtn.setVisible(true);
        if (Frame.getCurUser().getRole() != 5) {
            deleteBtn.setVisible(false);
        }
        if (Frame.getCurUser().getRole() == 2) {
            editRoleBtn.setVisible(false);
            lockBtn.setVisible(false);
            tableModel.setColumnCount(1);
        }

        //      CLEAR TABLE
        for (int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--) {
            tableModel.removeRow(0);
        }

        //      LOAD CONTENTS
        List<User> users = sqlite.getUsers();
        String[] roles = {"", "Client", "Staff", "Manager", "Admin"};
        User curUser = Frame.getCurUser();
        switch (curUser.getRole()) {
            case 4:
                users = users.stream().filter(u -> u.getRole() < 4 || u.getId() == curUser.getId()).collect(Collectors.toList());
                break;
            case 3:
                users = users.stream().filter(u -> u.getRole() < 3 || u.getId() == curUser.getId()).collect(Collectors.toList());
                break;
            case 2:
                users = users.stream().filter(u -> u.getId() == curUser.getId()).collect(Collectors.toList());
                break;
        }

        for (int nCtr = 0; nCtr < users.size(); nCtr++) {
            tableModel.addRow(new Object[]{
                users.get(nCtr).getUsername(),
                //                users.get(nCtr).getPassword(), 
                users.get(nCtr).getRole() + " (" + roles[users.get(nCtr).getRole() - 1] + ")",
                users.get(nCtr).getLocked()});
        }
    }

    public void designer(JTextField component, String text) {
        component.setSize(70, 600);
        component.setFont(new java.awt.Font("Tahoma", 0, 18));
        component.setBackground(new java.awt.Color(240, 240, 240));
        component.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        component.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), text, javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        editRoleBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lockBtn = new javax.swing.JButton();
        chgpassBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Role", "Locked"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(160);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        editRoleBtn.setBackground(new java.awt.Color(255, 255, 255));
        editRoleBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editRoleBtn.setText("EDIT ROLE");
        editRoleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRoleBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(255, 255, 255));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        lockBtn.setBackground(new java.awt.Color(255, 255, 255));
        lockBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lockBtn.setText("LOCK/UNLOCK");
        lockBtn.setToolTipText("");
        lockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockBtnActionPerformed(evt);
            }
        });

        chgpassBtn.setBackground(new java.awt.Color(255, 255, 255));
        chgpassBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chgpassBtn.setText("CHANGE PASS");
        chgpassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chgpassBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editRoleBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(lockBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(chgpassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chgpassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editRoleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editRoleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRoleBtnActionPerformed
        if (table.getSelectedRow() >= 0) {
            String[] options = {"1-DISABLED", "2-CLIENT", "3-STAFF", "4-MANAGER", "5-ADMIN"};
            JComboBox optionList = new JComboBox(options);
            String username = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
            if (Frame.getCurUser().getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(null,
                        "Cannot change own status",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int curRole = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 1).toString().substring(0, 1));
            optionList.setSelectedIndex(curRole - 1);
            String result = (String) JOptionPane.showInputDialog(null, "USER: " + tableModel.getValueAt(table.getSelectedRow(), 0),
                    "EDIT USER ROLE", JOptionPane.QUESTION_MESSAGE, null, options, options[curRole - 1]);

            if (result != null) {
                int newRole = Integer.parseInt(result.substring(0, 1));
                if (curRole == newRole) {
                    return;
                }
                System.out.println("Changing role of " + username + " to " + newRole);
                sqlite.updateRoleByUsername(username, newRole);
                init();
                SecurityConfig.log(sqlite, 0, "NOTICE", "Changed role of " + username + " from " + options[curRole - 1] + " to " + options[newRole - 1]);
            }
        }
    }//GEN-LAST:event_editRoleBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (table.getSelectedRow() >= 0) {
            String deleteMsg = "Are you sure you want to delete ";
            for (int i = 0; i < table.getSelectedRows().length; i++) {
                String username = tableModel.getValueAt(table.getSelectedRows()[i], 0).toString();
                if (Frame.getCurUser().getUsername().equalsIgnoreCase(username)) {
                    JOptionPane.showMessageDialog(null,
                            "Cannot delete own account",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                deleteMsg += username;
                if (i != table.getSelectedRows().length - 1) {
                    deleteMsg += ", ";
                }
            }
            deleteMsg += "?\nNote: Accounts will first be archived before being deleted. ";
            int result = JOptionPane.showConfirmDialog(null, deleteMsg, "DELETE USER", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                for (int i = 0; i < table.getSelectedRows().length; i++) {
                    String username = tableModel.getValueAt(table.getSelectedRows()[i], 0).toString();
                    User toDelete = sqlite.getUsersByUsername(username).get(0);
                    sqlite.removeUser(username);
                    SecurityConfig.updateUserArchive(toDelete);
                    SecurityConfig.log(sqlite, 0, "NOTICE", "Archived and deleted the account of " + username);
                }
                init();
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void lockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockBtnActionPerformed
        if (table.getSelectedRow() >= 0) {
            String state = "lock";
            if ("1".equals(tableModel.getValueAt(table.getSelectedRow(), 2) + "")) {
                state = "unlock";
            }

            String username = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
            if (Frame.getCurUser().getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(null,
                        "Cannot toggle own account",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to " + state + " " + username + "?", "DELETE USER", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                System.out.println(tableModel.getValueAt(table.getSelectedRow(), 0));
                sqlite.updateLockedByUsername(username, state.equalsIgnoreCase("lock") ? 1 : 0);
                init();
                SecurityConfig.log(sqlite, 0, "NOTICE", state.substring(0, 1).toUpperCase() + state.substring(1) + "ed account of " + username);
            }
        }
    }//GEN-LAST:event_lockBtnActionPerformed

    private void chgpassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chgpassBtnActionPerformed
        if (table.getSelectedRow() >= 0) {
            String username = tableModel.getValueAt(table.getSelectedRow(), 0).toString();

            JTextField oldpass = new JPasswordField();
            JTextField password = new JPasswordField();
            JTextField confpass = new JPasswordField();

            designer(oldpass, "OLD PASSWORD");
            designer(password, "PASSWORD");
            designer(confpass, "CONFIRM PASSWORD");

            Object[] message = {
                "Enter New Password (note: passwords must contain\nat least one uppercase, one lowercase, one special, and one numeric character\nand have a minimum length of 8):",
                oldpass,
                password,
                confpass
            };

            if (!Frame.getCurUser().getUsername().equalsIgnoreCase(username)) {
                oldpass.setVisible(false);
            }

            int result = JOptionPane.showConfirmDialog(null, message, "CHANGE PASSWORD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

            User selectedUser = sqlite.getUsersByUsername(username).get(0);
            if (result == JOptionPane.OK_OPTION) {
                List<String> errors = SecurityConfig.checkPassword(username, password.getText(), confpass.getText());
                if (errors.size() != 0) {
                    JOptionPane.showMessageDialog(null,
                            errors.get(errors.size() - 1),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    SecurityConfig.log(sqlite, 1, "FAILED ATTEMPT", "Failed attempt to change the password of " + username + " due to: " + errors.get(errors.size() - 1));
                    return;
                }

                if (Frame.getCurUser().getUsername().equals(username)
                        && !selectedUser.getPassword().equals(SecurityConfig.hash(oldpass.getText()))) {
                    JOptionPane.showMessageDialog(null,
                            "Incorrect old password",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    SecurityConfig.log(sqlite, 1, "FAILED ATTEMPT", "Failed attempt to change the password of " + username + " due to incorrect old password");
                    return;
                }

                if (selectedUser.getPassword().equals(SecurityConfig.hash(password.getText()))) {
                    JOptionPane.showMessageDialog(null,
                            "New password same as current password",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    SecurityConfig.log(sqlite, 1, "FAILED ATTEMPT", "Failed attempt to change the password of " + username + " due to new password being same as old password");
                    return;
                }

                sqlite.updatePasswordByUsername(username, password.getText());
                SecurityConfig.log(sqlite, 0, "NOTICE", "Changed the password of " + username);
            }
        }
    }//GEN-LAST:event_chgpassBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chgpassBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editRoleBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lockBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
