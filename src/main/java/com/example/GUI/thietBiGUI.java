package com.example.GUI;

import com.example.BLL.thietBiBLL;
import com.example.DAL.thietBi;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class thietBiGUI extends javax.swing.JPanel {

    private thietBiBLL thietBiBLL;
    private ArrayList<thietBi> listThietBi;
    private int selectingID = 0;

    public thietBiGUI() {
        this.thietBiBLL = new thietBiBLL();
        this.listThietBi = new ArrayList<thietBi>();
        initComponents();
        eventHandler();
    }

    private void eventHandler() {
        this.listThietBi = thietBiBLL.getDevices();
        this.deviceTable.getTableHeader().setReorderingAllowed(false);
        renderTable();

        //table click
        this.deviceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (deviceTable.rowAtPoint(e.getPoint()) >= 0) {
                    selectingID = Integer.parseInt(deviceTable.getValueAt(deviceTable.rowAtPoint(e.getPoint()), 0).toString());
                    deviceIdBox.setText(Integer.toString(selectingID));
                    deviceNameBox.setText(thietBiBLL.getDevice(selectingID).getTentb());
                    deviceDescriptionBox.setText(thietBiBLL.getDevice(selectingID).getMotatb());
                }
            }
        });

        // button import    
        this.importDeviceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickFile.showOpenDialog(jScrollPane1);
                boolean success = thietBiBLL.addDevicesViaExcel(pickFile.getSelectedFile().getAbsolutePath());
                if(success){
                    listThietBi = thietBiBLL.getDevices();
                    renderTable();
                }
            }
        });

        //button add
        this.addDeviceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deviceIdBox.getText() != null && deviceNameBox.getText() != null && deviceDescriptionBox.getText() != null) {
                    try {
                        int deviceId = Integer.parseInt(deviceIdBox.getText());
                        String deviceName = deviceNameBox.getText();
                        String deviceDes = deviceDescriptionBox.getText();
                        boolean success = thietBiBLL.addDevice(deviceId, deviceName, deviceDes);
                        if(success){
                            listThietBi = thietBiBLL.getDevices();
                            renderTable();
                        }else{
                            JOptionPane.showMessageDialog(null,"Device already stored");
                        }
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null,"ID must be numeric");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please full fill device's information!");
                }
            }
        });

        //button update
        this.updateDeviceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deviceIdBox.getText() != null) {
                    if (thietBiBLL.getDevice(Integer.parseInt(deviceIdBox.getText())) != null) {
                        boolean success = thietBiBLL.updateDevice(Integer.parseInt(deviceIdBox.getText()), deviceNameBox.getText(), deviceDescriptionBox.getText());
                        if (success) {
                            listThietBi = thietBiBLL.getDevices();
                            renderTable();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(jScrollPane1, "every thing are up to date");
                }
            }
        });

        //button delete
        this.delDeviceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectingID > 0 && thietBiBLL.getDevice(selectingID) != null) {
                    boolean success = thietBiBLL.deleteDevice(selectingID);
                    if(success){
                        listThietBi = thietBiBLL.getDevices();
                        renderTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Cannot find device");
                }
            }
        });
        
        this.delMultiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = JOptionPane.showInputDialog(jScrollPane1, "Input the type code to delete");
                try {
                    if(Integer.parseInt(course) < 0){
                        JOptionPane.showMessageDialog(jScrollPane1, "Invalid course");
                    }else{
                        boolean success = thietBiBLL.deleteDevices(Integer.parseInt(course));
                        if(success){
                            listThietBi = thietBiBLL.getDevices();
                            renderTable();
                            JOptionPane.showMessageDialog(jScrollPane1, "Delete successfully");
                        }else {
                            JOptionPane.showMessageDialog(jScrollPane1, "Some error when deleting");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jScrollPane1, "Type must be integer");
                }
            }
        });
    }

    // load list to JTable func
    private void renderTable() {
        DefaultTableModel model = (DefaultTableModel) this.deviceTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < this.listThietBi.size(); i++) {
            String deviId = Integer.toString(this.listThietBi.get(i).getMatb());
            String deviName = this.listThietBi.get(i).getTentb();
            String deviDescript = listThietBi.get(i).getMotatb();
            Object[] data = {deviId, deviName, deviDescript};
            model.addRow(data);
            this.deviceTable.updateUI();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pickFile = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        deviceTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        deviceIdBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        deviceNameBox = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        deviceDescriptionBox = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        addDeviceBtn = new javax.swing.JButton();
        updateDeviceBtn = new javax.swing.JButton();
        delDeviceBtn = new javax.swing.JButton();
        importDeviceBtn = new javax.swing.JButton();
        delMultiBtn = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(900, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(900, 400));

        deviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Device Name", "Descriptions"
            }
        ));
        deviceTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(deviceTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(jScrollPane1, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(900, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 200));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(900, 150));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setPreferredSize(new java.awt.Dimension(450, 150));
        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setPreferredSize(new java.awt.Dimension(450, 75));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ID: ");
        jLabel2.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel6.add(jLabel2, new java.awt.GridBagConstraints());

        deviceIdBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        deviceIdBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel6.add(deviceIdBox, new java.awt.GridBagConstraints());

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Name:");
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel6.add(jLabel1, new java.awt.GridBagConstraints());

        deviceNameBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        deviceNameBox.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel6.add(deviceNameBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel6);

        jPanel8.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Description:");
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel8.add(jLabel3, new java.awt.GridBagConstraints());

        deviceDescriptionBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        deviceDescriptionBox.setPreferredSize(new java.awt.Dimension(715, 40));
        jPanel8.add(deviceDescriptionBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel8);

        jPanel5.add(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        addDeviceBtn.setBackground(new java.awt.Color(222, 184, 135));
        addDeviceBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        addDeviceBtn.setForeground(new java.awt.Color(51, 51, 51));
        addDeviceBtn.setText("ADD");
        addDeviceBtn.setAlignmentY(0.0F);
        addDeviceBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addDeviceBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        addDeviceBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(addDeviceBtn);

        updateDeviceBtn.setBackground(new java.awt.Color(222, 184, 135));
        updateDeviceBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        updateDeviceBtn.setForeground(new java.awt.Color(51, 51, 51));
        updateDeviceBtn.setText("UPDATE");
        updateDeviceBtn.setAlignmentY(0.0F);
        updateDeviceBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateDeviceBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(updateDeviceBtn);

        delDeviceBtn.setBackground(new java.awt.Color(222, 184, 135));
        delDeviceBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        delDeviceBtn.setForeground(new java.awt.Color(51, 51, 51));
        delDeviceBtn.setText("DELETE");
        delDeviceBtn.setAlignmentY(0.0F);
        delDeviceBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delDeviceBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(delDeviceBtn);

        importDeviceBtn.setBackground(new java.awt.Color(222, 184, 135));
        importDeviceBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        importDeviceBtn.setForeground(new java.awt.Color(51, 51, 51));
        importDeviceBtn.setText("IMPORT");
        importDeviceBtn.setAlignmentY(0.0F);
        importDeviceBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importDeviceBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(importDeviceBtn);

        delMultiBtn.setBackground(new java.awt.Color(222, 184, 135));
        delMultiBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        delMultiBtn.setForeground(new java.awt.Color(51, 51, 51));
        delMultiBtn.setText("DELS");
        delMultiBtn.setAlignmentY(0.0F);
        delMultiBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delMultiBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(delMultiBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel1.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDeviceBtn;
    private javax.swing.JButton delDeviceBtn;
    private javax.swing.JButton delMultiBtn;
    private javax.swing.JTextField deviceDescriptionBox;
    private javax.swing.JTextField deviceIdBox;
    private javax.swing.JTextField deviceNameBox;
    private javax.swing.JTable deviceTable;
    private javax.swing.JButton importDeviceBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFileChooser pickFile;
    private javax.swing.JButton updateDeviceBtn;
    // End of variables declaration//GEN-END:variables
}
