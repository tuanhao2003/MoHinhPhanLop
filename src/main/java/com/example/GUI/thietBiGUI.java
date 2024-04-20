package com.example.GUI;

import com.example.BLL.thietBiBLL;
import com.example.DAL.thietBi;
import java.awt.event.*;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class thietBiGUI extends javax.swing.JPanel {

    private thietBiBLL thietBiBLL;
    private ArrayList<thietBi> listThietBi;

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
// button import    
        this.importMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickFile.showOpenDialog(jScrollPane1);
                thietBiBLL.addDevicesViaExcel(pickFile.getSelectedFile().getAbsolutePath());
                listThietBi = thietBiBLL.getDevices();
                renderTable();
            }
        });
    }

 // load list to JTable func
    private void renderTable() {
        DefaultTableModel model = (DefaultTableModel) this.deviceTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < thietBiBLL.getDevices().size(); i++) {
            Object[] data = {Integer.toString(this.thietBiBLL.getDevices().get(i).getMatb()), this.thietBiBLL.getDevices().get(i).getTentb(), thietBiBLL.getDevices().get(i).getMotatb()};
            model.addRow(data);
            this.deviceTable.updateUI();
        }
    }
// Reload component func (for update UI)
    private void reload(JPanel item) {
        item.repaint();
        item.revalidate();
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
        deviceDesriptionBox = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        addMemBtn = new javax.swing.JButton();
        updateMemBtn = new javax.swing.JButton();
        delMemBtn = new javax.swing.JButton();
        importMemBtn = new javax.swing.JButton();

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

        deviceDesriptionBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        deviceDesriptionBox.setPreferredSize(new java.awt.Dimension(715, 40));
        jPanel8.add(deviceDesriptionBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel8);

        jPanel5.add(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        addMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        addMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        addMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        addMemBtn.setText("ADD");
        addMemBtn.setAlignmentY(0.0F);
        addMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addMemBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        addMemBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(addMemBtn);

        updateMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        updateMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        updateMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        updateMemBtn.setText("UPDATE");
        updateMemBtn.setAlignmentY(0.0F);
        updateMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateMemBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(updateMemBtn);

        delMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        delMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        delMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        delMemBtn.setText("DELETE");
        delMemBtn.setAlignmentY(0.0F);
        delMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delMemBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(delMemBtn);

        importMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        importMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        importMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        importMemBtn.setText("IMPORT");
        importMemBtn.setAlignmentY(0.0F);
        importMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importMemBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(importMemBtn);

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
    private javax.swing.JButton addMemBtn;
    private javax.swing.JButton delMemBtn;
    private javax.swing.JTextField deviceDesriptionBox;
    private javax.swing.JTextField deviceIdBox;
    private javax.swing.JTextField deviceNameBox;
    private javax.swing.JTable deviceTable;
    private javax.swing.JButton importMemBtn;
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
    private javax.swing.JButton updateMemBtn;
    // End of variables declaration//GEN-END:variables
}
