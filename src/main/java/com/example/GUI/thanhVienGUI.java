package com.example.GUI;

import com.example.BLL.thanhVienBLL;
import com.example.DAL.thanhVien;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class thanhVienGUI extends javax.swing.JPanel {

    private thanhVienBLL thanhVienBLL;
    private ArrayList<thanhVien> listThanhVien;
    private int selectingID = 0;

    public thanhVienGUI() {
        this.thanhVienBLL = new thanhVienBLL();
        this.listThanhVien = new ArrayList<thanhVien>();
        initComponents();
        eventHandler();
    }

    private void eventHandler() {

        this.listThanhVien = this.thanhVienBLL.getMembers();
        this.memberTable.getTableHeader().setReorderingAllowed(false);
        renderTable();
//table click
        this.memberTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getPoint().y > 0) {
                    selectingID = Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString());
                    memIdBox.setText(Integer.toString(selectingID));
                    memNameBox.setText(thanhVienBLL.getMember(selectingID).getHoten());
                    memMajorBox.setText(thanhVienBLL.getMember(selectingID).getKhoa());
                    memSubMajorBox.setText(thanhVienBLL.getMember(selectingID).getNganh());
                    memPhoneBox.setText(thanhVienBLL.getMember(selectingID).getSdt());
                    memEmailBox.setText(thanhVienBLL.getMember(selectingID).getEmail());
                    memPasswordBox.setText(thanhVienBLL.getMember(selectingID).getPassword());

                }
            }
        });

// button add
        this.addMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memIdBox.getText() != null && memNameBox.getText() != null && memMajorBox.getText() != null && memSubMajorBox.getText() != null && memPhoneBox.getText() != null && memEmailBox.getText() != null && jlabelpass.getText() != null) {
                    try {
                        int id = Integer.parseInt(memIdBox.getText());
                        String fullname = memNameBox.getText();
                        String major = memMajorBox.getText();
                        String submajor = memSubMajorBox.getText();
                        String phone = memPhoneBox.getText();
                        String email = memEmailBox.getText();
                        String pass = jlabelpass.getText();
                        thanhVienBLL.addMember(id, fullname, major, submajor, phone, email, pass);
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("id must be integer");
                    }
                } else {
                    System.out.println("please full fill ther boxes");
                }
                listThanhVien = thanhVienBLL.getMembers();
                renderTable();
            }
        });

//button update
        this.updateMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memIdBox.getText() != null && memNameBox.getText() != null && memMajorBox.getText() != null && memSubMajorBox.getText() != null && memPhoneBox.getText() != null && memEmailBox.getText() != null && jlabelpass.getText() != null) {
                    if (!memIdBox.getText().equals(Integer.toString(selectingID))
                        && !memNameBox.getText().equals(thanhVienBLL.getMember(selectingID).getHoten())
                        && !memMajorBox.getText().equals(thanhVienBLL.getMember(selectingID).getKhoa())
                        && !memSubMajorBox.getText().equals(thanhVienBLL.getMember(selectingID).getNganh())
                        && !memPhoneBox.getText().equals(thanhVienBLL.getMember(selectingID).getSdt())
                        && !memEmailBox.getText().equals(thanhVienBLL.getMember(selectingID).getEmail())
                        && !memPasswordBox.getText().equals(thanhVienBLL.getMember(selectingID).getPassword())) {
                        thanhVienBLL.updateMember(Integer.parseInt(memIdBox.getText()), memNameBox.getText(), memMajorBox.getText(), memSubMajorBox.getText(), memPhoneBox.getText(), memEmailBox.getText(), memPasswordBox.getText());
                        listThanhVien = thanhVienBLL.getMembers();
                        renderTable();
                    }
                } else {
                    System.out.println("every thing are up to date");
                }
            }
        });
        
// button delete
        this.delMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectingID > 0 && thanhVienBLL.getMember(selectingID) != null){
                    thanhVienBLL.deleteMember(selectingID);
                    listThanhVien = thanhVienBLL.getMembers();
                    renderTable();
                }else{
                    System.out.println("Error while finding member");
                }
            }
        });

// button import    
        this.importMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickFile.showOpenDialog(jScrollPane1);
                thanhVienBLL.addMembersViaExcel(pickFile.getSelectedFile().getAbsolutePath());
                listThanhVien = thanhVienBLL.getMembers();
                renderTable();
            }
        });

    }

    private void renderTable() {
        DefaultTableModel model = (DefaultTableModel) this.memberTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < thanhVienBLL.getMembers().size(); i++) {
            Object[] data = {Integer.toString(this.thanhVienBLL.getMembers().get(i).getMatv()), this.thanhVienBLL.getMembers().get(i).getHoten(), thanhVienBLL.getMembers().get(i).getKhoa(), thanhVienBLL.getMembers().get(i).getNganh(), thanhVienBLL.getMembers().get(i).getSdt(), thanhVienBLL.getMembers().get(i).getEmail()};
            model.addRow(data);
            this.memberTable.updateUI();
        }
    }

    private void reload(JPanel item) {
        item.repaint();
        item.revalidate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pickFile = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        memIdBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        memMajorBox = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        memNameBox = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        memSubMajorBox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        memPhoneBox = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        memEmailBox = new javax.swing.JTextField();
        jlabelpass = new javax.swing.JLabel();
        memPasswordBox = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        addMemBtn = new javax.swing.JButton();
        updateMemBtn = new javax.swing.JButton();
        delMemBtn = new javax.swing.JButton();
        importMemBtn = new javax.swing.JButton();

        pickFile.setToolTipText("");

        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(900, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(900, 400));

        memberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Name", "Major", "Submajor", "Phone", "Email"
            }
        ));
        memberTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(memberTable);

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

        memIdBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memIdBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel6.add(memIdBox, new java.awt.GridBagConstraints());

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Major:");
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel6.add(jLabel1, new java.awt.GridBagConstraints());

        memMajorBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memMajorBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel6.add(memMajorBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel6);

        jPanel8.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fullname:");
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel8.add(jLabel3, new java.awt.GridBagConstraints());

        memNameBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memNameBox.setPreferredSize(new java.awt.Dimension(340, 40));
        jPanel8.add(memNameBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel8);

        jPanel5.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(450, 150));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel9.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sub Marjor:");
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel9.add(jLabel5, new java.awt.GridBagConstraints());

        memSubMajorBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memSubMajorBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel9.add(memSubMajorBox, new java.awt.GridBagConstraints());

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Phone:");
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel9.add(jLabel6, new java.awt.GridBagConstraints());

        memPhoneBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memPhoneBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel9.add(memPhoneBox, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Email:");
        jLabel7.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(jLabel7, new java.awt.GridBagConstraints());

        memEmailBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memEmailBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel10.add(memEmailBox, new java.awt.GridBagConstraints());

        jlabelpass.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jlabelpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabelpass.setText("Password:");
        jlabelpass.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(jlabelpass, new java.awt.GridBagConstraints());

        memPasswordBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memPasswordBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel10.add(memPasswordBox, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel10);

        jPanel5.add(jPanel3);

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
    private javax.swing.JButton importMemBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabelpass;
    private javax.swing.JTextField memEmailBox;
    private javax.swing.JTextField memIdBox;
    private javax.swing.JTextField memMajorBox;
    private javax.swing.JTextField memNameBox;
    private javax.swing.JTextField memPasswordBox;
    private javax.swing.JTextField memPhoneBox;
    private javax.swing.JTextField memSubMajorBox;
    private javax.swing.JTable memberTable;
    private javax.swing.JFileChooser pickFile;
    private javax.swing.JButton updateMemBtn;
    // End of variables declaration//GEN-END:variables
}
