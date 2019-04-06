/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SQLite;
import Controller.SecurityConfig;
import Model.Logs;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author beepxD
 */
public class MgmtLogs extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;

    public MgmtLogs(SQLite sqlite) {
        initComponents();
        this.sqlite = sqlite;
        tableModel = (DefaultTableModel) table.getModel();
        table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));

//        UNCOMMENT TO DISABLE BUTTONS
//        clearBtn.setVisible(false);
//        debugBtn.setVisible(false);
    }

    public void init() {
        //      CLEAR TABLE
        for (int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--) {
            tableModel.removeRow(0);
        }

//      LOAD CONTENTS
        ArrayList<Logs> logs = sqlite.getLogs();
        for (int nCtr = 0; nCtr < logs.size(); nCtr++) {
            tableModel.addRow(new Object[]{
                logs.get(nCtr).getEvent(),
                logs.get(nCtr).getUsername(),
                logs.get(nCtr).getDesc(),
                logs.get(nCtr).getTimestamp()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        clearBtn = new javax.swing.JButton();
        debugBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Event", "Username", "Description", "Timestamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(80);
            table.getColumnModel().getColumn(1).setPreferredWidth(160);
            table.getColumnModel().getColumn(2).setPreferredWidth(400);
            table.getColumnModel().getColumn(3).setPreferredWidth(240);
        }

        clearBtn.setBackground(new java.awt.Color(255, 255, 255));
        clearBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        debugBtn.setBackground(new java.awt.Color(255, 255, 255));
        debugBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        debugBtn.setText("ENABLE/DISABLE DEBUG MODE");
        debugBtn.setToolTipText("");
        debugBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(debugBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debugBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        int dialogButton = 0;
        //clear a single log and archive
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete selected logs?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            ArrayList<Logs> logsList = new ArrayList<>();
            int[] selectedRows = table.getSelectedRows();
            int columns = 4;

            for (int i = 0; i < selectedRows.length; i++) {
                Logs temp = new Logs("", "");
                for (int j = 0; j < columns; j++) {
//                table.getValueAt(selectedRows[i], columns);
                    switch (j) {
                        case 0:
                            temp.setEvent((String) table.getValueAt(selectedRows[i], j));
                            break;
                        case 1:
                            temp.setUsername((String) table.getValueAt(selectedRows[i], j));
                            break;
                        case 2:
                            temp.setDesc((String) table.getValueAt(selectedRows[i], j));
                            break;
                        case 3:
                            temp.setTimestamp((Timestamp) table.getValueAt(selectedRows[i], j));
                            break;
                        default:
                            break;
                    }
                }
                logsList.add(temp);
            }
            Logger logger = Logger.getLogger("SecurdeLog");
            FileHandler fh;

            try {

                // This block configure the logger with handler and formatter  
                fh = new FileHandler("./logs/SecurdeLog.log");
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);

            } catch (SecurityException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < logsList.size(); i++) {
//            System.out.println("Event: " +logsList.get(i).getEvent() + " Username: " +logsList.get(i).getUsername() + " Desc: " + logsList.get(i).getDesc() + " Timestamp: " + logsList.get(i).getTimestamp());
                logger.log(Level.INFO, "Event: {0} Username: {1} Desc: {2} Timestamp: {3}", new Object[]{logsList.get(i).getEvent(), logsList.get(i).getUsername(), logsList.get(i).getDesc(), logsList.get(i).getTimestamp()});
                sqlite.removeLogs(logsList.get(i));
            }

            init();
        }

    }//GEN-LAST:event_clearBtnActionPerformed

    private void debugBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugBtnActionPerformed
        if (sqlite.DEBUG_MODE == 1) {
//            System.out.println("Debug mode is " + sqlite.DEBUG_MODE + ". Changing value.");
            sqlite.DEBUG_MODE = 0;
            SecurityConfig.updateConfig(0);
        } else {
//            System.out.println("Debug mode is " + sqlite.DEBUG_MODE + ". Changing value.");
            sqlite.DEBUG_MODE = 1;
            SecurityConfig.updateConfig(1);
        }
    }//GEN-LAST:event_debugBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton debugBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
