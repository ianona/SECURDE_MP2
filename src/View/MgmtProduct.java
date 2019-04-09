/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SQLite;
import Controller.SecurityConfig;
import Model.Product;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author beepxD
 */
public class MgmtProduct extends javax.swing.JPanel {

    public SQLite sqlite;
    public DefaultTableModel tableModel;

    public MgmtProduct(SQLite sqlite) {
        initComponents();
        this.sqlite = sqlite;
        tableModel = (DefaultTableModel) table.getModel();
        table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));

//        UNCOMMENT TO DISABLE BUTTONS
//        purchaseBtn.setVisible(false);    
//        addBtn.setVisible(false);
//        editBtn.setVisible(false);
//        deleteBtn.setVisible(false)
    }

    public void init() {
        //      CLEAR TABLE
        for (int nCtr = tableModel.getRowCount(); nCtr > 0; nCtr--) {
            tableModel.removeRow(0);
        }

//      LOAD CONTENTS
        ArrayList<Product> products = sqlite.getProduct();
        for (int nCtr = 0; nCtr < products.size(); nCtr++) {
            tableModel.addRow(new Object[]{
                products.get(nCtr).getName(),
                products.get(nCtr).getStock(),
                products.get(nCtr).getPrice()});
        }
        purchaseBtn.setVisible(true);
        addBtn.setVisible(true);
        editBtn.setVisible(true);
        deleteBtn.setVisible(true);

        switch (Frame.getCurUser().getRole()) {
            case 2:
                addBtn.setVisible(false);
                editBtn.setVisible(false);
                deleteBtn.setVisible(false);
                break;
            case 3:
                purchaseBtn.setVisible(false);
                deleteBtn.setVisible(false);
                break;
            case 4:
                purchaseBtn.setVisible(false);
                break;
            case 5:
                purchaseBtn.setVisible(false);
                break;
            default:
                break;
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
        purchaseBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Stock", "Price"
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
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        purchaseBtn.setBackground(new java.awt.Color(255, 255, 255));
        purchaseBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        purchaseBtn.setText("PURCHASE");
        purchaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(255, 255, 255));
        addBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(255, 255, 255));
        editBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editBtn.setText("EDIT");
        editBtn.setToolTipText("");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(purchaseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchaseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    //Won Suk Cho: for error messages
    public void popuperror(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    //Won Suk Cho: for warning messages
    public void popupwarning(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "WARNING", JOptionPane.WARNING_MESSAGE);
    }

    //Won Suk Cho: for reminder messages
    public void popupmessage(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "REMINDER", JOptionPane.PLAIN_MESSAGE);
    }

    private void purchaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseBtnActionPerformed
        int stock = 0;
        int chosen = 0;

        if (table.getSelectedRow() >= 0) {
            JTextField stockFld = new JTextField("");
            designer(stockFld, "PRODUCT STOCK");

            if ((int) tableModel.getValueAt(table.getSelectedRow(), 1) != 0) {
                Object[] message = {
                    "How many " + tableModel.getValueAt(table.getSelectedRow(), 0) + " do you want to purchase?", stockFld
                };

                int result = JOptionPane.showConfirmDialog(null, message, "PURCHASE PRODUCT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

                if (result == JOptionPane.OK_OPTION) {
                    if (stockFld.getText().equals("")) {
                        popuperror("Field empty, please fill it up.");
                    } else {
                        try {
                            if (stockFld.getText().matches("[\\d]+")) {
                                chosen = Integer.parseInt(stockFld.getText());
                                stock = (int) tableModel.getValueAt(table.getSelectedRow(), 1);

                                if (stock > 0) {
                                    if (stock >= chosen) {
                                        SecurityConfig.log(sqlite, 0, "NOTICE", chosen + " items of " + tableModel.getValueAt(table.getSelectedRow(), 0) + " purchased");

                                        popupmessage("Purchased Successful!");

                                        sqlite.sellProduct((String) tableModel.getValueAt(table.getSelectedRow(), 0), stock - chosen);
                                        sqlite.addHistory(Frame.getCurUser().getUsername(), (String) tableModel.getValueAt(table.getSelectedRow(), 0), chosen, new Timestamp(new Date().getTime()).toString());
                                        init();
                                    } else {
                                        SecurityConfig.log(sqlite, 1, "FAILED ATTEMPT", "Restock " + tableModel.getValueAt(table.getSelectedRow(), 0) + " not a successful purchase.");

                                        popupwarning("Not enough stock available.");
                                    }
                                } else {
                                    popuperror("No negative values!");
                                }

                            } else {
                                popuperror("Only numerical values are allowed.");
                            }
                        } catch (Exception e) {
                            popuperror("Exceeding value!");
                        }
                    }

                    //System.out.println(stockFld.getText());
                }
            } else {
                popupwarning("No Stocks Available");
            }
        } else {
            popupmessage("Please select any given product.");

        }
    }//GEN-LAST:event_purchaseBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        JTextField nameFld = new JTextField();
        JTextField stockFld = new JTextField();
        JTextField priceFld = new JTextField();

        designer(nameFld, "PRODUCT NAME");
        designer(stockFld, "PRODUCT STOCK");
        designer(priceFld, "PRODUCT PRICE");

        double price = 0;

        Object[] message = {
            "Insert New Product Details:", nameFld, stockFld, priceFld
        };

        int result = JOptionPane.showConfirmDialog(null, message, "ADD PRODUCT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

        if (result == JOptionPane.OK_OPTION) {
            String newprice, newnumstock;
            newnumstock = stockFld.getText().replaceFirst("^0+(?!$)", "");
            newprice = priceFld.getText().replaceFirst("^0+(?!$)", "");
            boolean isAtMax32 = nameFld.getText().length() <= 32;
            boolean onlyNumber = newnumstock.matches("[\\d]+");
            boolean curAllnum = newprice.matches("[\\d]+");
            boolean curDecnum = newprice.matches("[\\d]+\\.+[\\d]+");
            boolean curPernum = newprice.matches("[\\d]+\\.");

            if (newnumstock.equals("") || nameFld.getText().equals("") || newprice.equals("")) {
                popuperror("Field/s empty, please fill it up.");
            } else {
                if (sqlite.getProductByProductname(nameFld.getText()).isEmpty()) {
                    if (isAtMax32) {
                        try {
                            if (onlyNumber && Integer.parseInt(newnumstock) >= 0 && Double.parseDouble(newprice) >= 0) {

                                if (curDecnum) {
                                    price = Double.parseDouble(newprice);
                                    price = Math.floor(price * 100) / 100;
                                    System.out.println(price);

                                    popupmessage("Product Added Successful!");

                                    SecurityConfig.log(sqlite, 0, "NOTICE", "Product " + nameFld.getText() + " added successfully. Stock = " + newnumstock + " Price = " + newprice);

                                    sqlite.addProduct(nameFld.getText(), Integer.parseInt(newnumstock), price);
                                    init();
                                } else if (curPernum) {
                                    price = Double.parseDouble(newprice);
                                    price = Math.floor(price * 100) / 100;
                                    System.out.println(price);

                                    popupmessage("Product Added Successful!");

                                    SecurityConfig.log(sqlite, 0, "NOTICE", "Product " + nameFld.getText() + " added successfully. Stock = " + newnumstock + " Price = " + newprice);

                                    sqlite.addProduct(nameFld.getText(), Integer.parseInt(newnumstock), price);
                                    init();
                                } else if (curAllnum) {
                                    price = Double.parseDouble(newprice);
                                    price = Math.floor(price * 100) / 100;
                                    System.out.println(price);

                                    popupmessage("Product Added Successful!");

                                    SecurityConfig.log(sqlite, 0, "NOTICE", "Product " + nameFld.getText() + " added successfully. Stock = " + newnumstock + " Price = " + newprice);

                                    sqlite.addProduct(nameFld.getText(), Integer.parseInt(newnumstock), price);
                                    init();
                                }
                            } else {
                                popuperror("Invalid input!");
                            }
                        } catch (Exception e) {
                            popuperror("Exceeding value!");
                        }
                    } else {
                        popuperror("Invalid input!");
                    }
                } else {
                    SecurityConfig.log(sqlite, 1, "FAILED ATTEMPT", "Product name " + nameFld.getText() + " already taken, product not added.");

                    popuperror("Product name not unique, choose something else.");
                }
            }

//                System.out.println(nameFld.getText());
//                System.out.println(stockFld.getText());
//                System.out.println(priceFld.getText());
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if (table.getSelectedRow() >= 0) {
            JTextField nameFld = new JTextField(tableModel.getValueAt(table.getSelectedRow(), 0) + "");
            JTextField stockFld = new JTextField(tableModel.getValueAt(table.getSelectedRow(), 1) + "");
            JTextField priceFld = new JTextField(tableModel.getValueAt(table.getSelectedRow(), 2) + "");

            String oldname = nameFld.getText();
            double price = 0;

            designer(nameFld, "PRODUCT NAME");
            designer(stockFld, "PRODUCT STOCK");
            designer(priceFld, "PRODUCT PRICE");

            Object[] message = {
                "Edit Product Details:", nameFld, stockFld, priceFld
            };

            int result = JOptionPane.showConfirmDialog(null, message, "EDIT PRODUCT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

            if (result == JOptionPane.OK_OPTION) {
                String newprice, newnumstock;
                newnumstock = stockFld.getText().replaceFirst("^0+(?!$)", "");
                newprice = priceFld.getText().replaceFirst("^0+(?!$)", "");
                boolean isAtMax32 = nameFld.getText().length() <= 32;
                boolean onlyNumber = newnumstock.matches("[\\d]+");
                boolean curAllnum = newprice.matches("[\\d]+");
                boolean curDecnum = newprice.matches("[\\d]+\\.+[\\d]+");
                boolean curPernum = newprice.matches("[\\d]+\\.");

                if (newnumstock.equals("") || nameFld.getText().equals("") || newprice.equals("")) {
                    popuperror("Field/s empty, please fill it up.");
                } else {
                    if (sqlite.getProductByProductname(nameFld.getText()).isEmpty() || nameFld.getText().equals(oldname)) {
                        if (isAtMax32) {
                            try {
                                if (onlyNumber && Integer.parseInt(newnumstock) >= 0 && Double.parseDouble(newprice) >= 0) {
                                    System.out.println(stockFld.getText().replaceFirst("^0+(?!$)", ""));

                                    if (curDecnum) {
                                        price = Double.parseDouble(newprice);
                                        price = Math.floor(price * 100) / 100;
                                        System.out.println(price);

                                        popupmessage("Edit Successful!");

                                        SecurityConfig.log(sqlite, 0, "NOTICE", "Product " + nameFld.getText() + " edited successfully. Stock = " + newnumstock + " Price = " + newprice);

                                        sqlite.editProduct(oldname, nameFld.getText(), Integer.parseInt(newnumstock), price);
                                        init();
                                    } else if (curPernum) {
                                        price = Double.parseDouble(newprice);
                                        price = Math.floor(price * 100) / 100;
                                        System.out.println(price);

                                        popupmessage("Edit Successful!");

                                        SecurityConfig.log(sqlite, 0, "NOTICE", "Product " + nameFld.getText() + " edited successfully. Stock = " + newnumstock + " Price = " + newprice);

                                        sqlite.editProduct(oldname, nameFld.getText(), Integer.parseInt(newnumstock), price);
                                        init();
                                    } else if (curAllnum) {
                                        price = Double.parseDouble(newprice);
                                        price = Math.floor(price * 100) / 100;
                                        System.out.println(price);

                                        popupmessage("Edit Successful!");

                                        SecurityConfig.log(sqlite, 0, "NOTICE", "Product " + nameFld.getText() + " edited successfully. Stock = " + newnumstock + " Price = " + newprice);

                                        sqlite.editProduct(oldname, nameFld.getText(), Integer.parseInt(newnumstock), price);
                                        init();
                                    }
                                } else {
                                    popuperror("Invalid input!");
                                }
                            } catch (Exception e) {
                                popuperror("Exceeding value!");
                            }
                        } else {
                            popuperror("Invalid input!");
                        }
                    } else {
                        SecurityConfig.log(sqlite, 1, "FAILED ATTEMPT", "Product name " + nameFld.getText() + " already taken, product not edited.");
                        popuperror("Product name not unique, choose something else.");
                    }
                }

//                System.out.println(nameFld.getText());
//                System.out.println(stockFld.getText());
//                System.out.println(priceFld.getText());
            }
        } else {
            popupmessage("Please select any given product.");
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (table.getSelectedRow() >= 0) {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + tableModel.getValueAt(table.getSelectedRow(), 0) + "?", "DELETE PRODUCT", JOptionPane.YES_NO_OPTION);

            SecurityConfig.log(sqlite, 0, "NOTICE", "Deleted " + tableModel.getValueAt(table.getSelectedRow(), 0) + " with " + tableModel.getValueAt(table.getSelectedRow(), 1) + " in stock at the price of " + tableModel.getValueAt(table.getSelectedRow(), 2));

            if (result == JOptionPane.YES_OPTION) {
                sqlite.removeProduct((String) tableModel.getValueAt(table.getSelectedRow(), 0));
                popupmessage("Deleted Successful!");
                init();
                //System.out.println(tableModel.getValueAt(table.getSelectedRow(), 0));
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton purchaseBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
