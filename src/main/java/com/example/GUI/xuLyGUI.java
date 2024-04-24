package com.example.GUI;

import com.example.BLL.xuLyBLL;
import com.example.DAL.thanhVien;
import com.example.DAL.xuLy;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.*;
import java.time.Instant;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class xuLyGUI extends javax.swing.JPanel {

    private xuLyBLL xuLyBLL;
    private ArrayList<xuLy> listXuLy;
    private int selectingID = 0;
    private thanhVien choosenMem = new thanhVien();

    public xuLyGUI() {
        this.xuLyBLL = new xuLyBLL();
        this.listXuLy = new ArrayList<xuLy>();
        initComponents();
        eventHandler();
    }

    private void eventHandler() {
        this.listXuLy = this.xuLyBLL.getPunishments();
        this.punishmentTable.getTableHeader().setReorderingAllowed(false);
        offenderNameBox.setDisabledTextColor(Color.BLACK);
        renderTable();

        this.punishmentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (punishmentTable.rowAtPoint(e.getPoint()) >= 0) {
                    selectingID = Integer.parseInt(punishmentTable.getValueAt(punishmentTable.rowAtPoint(e.getPoint()), 0).toString());
                    punishStatusBox.setText(Integer.toString(xuLyBLL.getPunishment(selectingID).getTrangthaixl()));
                    punishTypeBox.setText(xuLyBLL.getPunishment(selectingID).getHinhthucxl());
                    offenderNameBox.setText(xuLyBLL.getPunishment(selectingID).getThanhvien().getHoten());
                    punishMoneyBox.setText(Integer.toString(xuLyBLL.getPunishment(selectingID).getSotien()));
                    punishDateBox.setText(xuLyBLL.getPunishment(selectingID).getNgayxl() == null ? "" : xuLyBLL.getPunishment(selectingID).getNgayxl().toString());
                }
            }
        });
        
        this.offenderNameBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseMember.setVisible(true);
                chooseMember.setLocationRelativeTo(null);
                allMemberContainer.removeAll();
                xuLyBLL.getAllMembers().forEach(mem -> {
                    JPanel panel = new JPanel();
                    panel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent pe) {
                            choosenMem = mem;
                            offenderNameBox.setText(choosenMem.getHoten());
                            chooseMember.dispose();
                        }
                    });
                    
                    panel.setSize(500, 50);
                    panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
                    panel.add(new JLabel(mem.getMatv() + " "));
                    panel.add(new JLabel(mem.getHoten()+ " "));
                    panel.add(new JLabel(mem.getKhoa()+ " "));
                    panel.add(new JLabel(mem.getNganh()+ " "));
                    panel.add(new JLabel(mem.getSdt()+ " "));
                    panel.add(new JLabel(mem.getEmail()+ " "));

                    allMemberContainer.add(panel);
                });
                reload(allMemberContainer);
            }
        });
                
        this.addPunishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(punishStatusBox.getText()) > 2 || Integer.parseInt(punishStatusBox.getText()) < 0){
                    JOptionPane.showMessageDialog(null,"Status must between 0 and 2");
                }else{
                    

                    if (punishStatusBox.getText() != null) {
                        try {
                            int status = Integer.parseInt(punishStatusBox.getText());
                            String type = punishTypeBox.getText();
                            int money = Integer.parseInt(punishMoneyBox.getText());
                            Timestamp date = Timestamp.valueOf(punishDateBox.getText());
                            boolean success = xuLyBLL.addPunishment(choosenMem.getMatv(), type, money, date, status);
                            if(success){
                                listXuLy = xuLyBLL.getPunishments();
                                renderTable();
                            }else{
                                JOptionPane.showMessageDialog(null,"punishment already stored");
                            }
                        } catch (NumberFormatException numberFormatException) {
                            JOptionPane.showMessageDialog(null,"status must be numeric");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please full fill punishment's information!");
                    }
                }
            }
        });
        
        this.punishDateBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(punishDateBox.getText().equals("") || punishDateBox.getText() == null){
                    punishDateBox.setText(Timestamp.from(Instant.now()).toString());
                }
            }
        });
        
        this.updatePunishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (punishStatusBox.getText() != null) {
                    if (xuLyBLL.getPunishment(Integer.parseInt(punishStatusBox.getText())) != null) {
                        boolean success = xuLyBLL.updatePunishment(Integer.parseInt(punishStatusBox.getText()), xuLyBLL.getPunishment(Integer.parseInt(punishStatusBox.getText())).getThanhvien().getMatv(), punishTypeBox.getText(), Integer.parseInt(punishMoneyBox.getText()), Timestamp.valueOf(punishDateBox.getText()), 0);
                        if (success) {
                            listXuLy = xuLyBLL.getPunishments();
                            renderTable();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(jScrollPane1, "every thing are up to date");
                }
            }
        });
        
        this.delPunishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(punishStatusBox.getText()) > 0 && xuLyBLL.getPunishment(Integer.parseInt(punishStatusBox.getText())) != null) {
                    boolean success = xuLyBLL.deletePunishment(Integer.parseInt(punishStatusBox.getText()));
                    if(success){
                        listXuLy = xuLyBLL.getPunishments();
                        renderTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Cannot find punishment infor");
                }
            }
        });
    }

    private void renderTable() {
        DefaultTableModel model = (DefaultTableModel) this.punishmentTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < this.listXuLy.size(); i++) {
            String id = Integer.toString(this.listXuLy.get(i).getMaxl());
            thanhVien offender = xuLyBLL.getPunishment(this.listXuLy.get(i).getMaxl()).getThanhvien();
            String offenderName = offender.getHoten();
            String phone = offender.getSdt();
            String type = this.listXuLy.get(i).getHinhthucxl();
            String money = Integer.toString(this.listXuLy.get(i).getSotien());
            String punishDate =  this.listXuLy.get(i).getNgayxl() == null ? "" : this.listXuLy.get(i).getNgayxl().toString();
            Object[] data = {id, offenderName, phone, type, money, punishDate};
            model.addRow(data);
            this.punishmentTable.updateUI();
        }
    }
    
    private void reload(JPanel screen){
        screen.repaint();
        screen.revalidate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        chooseMember = new javax.swing.JDialog();
        chooseSkeleton = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        allMemberContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        punishmentTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        punishStatusBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        punishTypeBox = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        offenderNameBox = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        punishMoneyBox = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jlabelpass = new javax.swing.JLabel();
        punishDateBox = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        addPunishBtn = new javax.swing.JButton();
        updatePunishBtn = new javax.swing.JButton();
        delPunishBtn = new javax.swing.JButton();

        chooseMember.setTitle("Choose Member");
        chooseMember.setAlwaysOnTop(true);
        chooseMember.setLocationByPlatform(true);
        chooseMember.setMinimumSize(new java.awt.Dimension(600, 400));
        chooseMember.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        chooseSkeleton.setPreferredSize(new java.awt.Dimension(550, 550));
        chooseSkeleton.setLayout(new java.awt.GridBagLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(500, 300));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(500, 500));

        allMemberContainer.setLayout(new javax.swing.BoxLayout(allMemberContainer, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane4.setViewportView(allMemberContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        chooseSkeleton.add(jScrollPane4, gridBagConstraints);

        chooseMember.getContentPane().add(chooseSkeleton);

        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(900, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(900, 400));

        punishmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Offender", "Phone", "Punish type", "price", "Punish date"
            }
        ));
        punishmentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(punishmentTable);

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

        jPanel8.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Status");
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel8.add(jLabel3, new java.awt.GridBagConstraints());

        punishStatusBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        punishStatusBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel8.add(punishStatusBox, new java.awt.GridBagConstraints());

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Type");
        jLabel4.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel8.add(jLabel4, new java.awt.GridBagConstraints());

        punishTypeBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        punishTypeBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel8.add(punishTypeBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel8);

        jPanel6.setPreferredSize(new java.awt.Dimension(450, 75));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Member name");
        jLabel2.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanel6.add(jLabel2, new java.awt.GridBagConstraints());

        offenderNameBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        offenderNameBox.setEnabled(false);
        offenderNameBox.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanel6.add(offenderNameBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel6);

        jPanel5.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(450, 150));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel9.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Price:");
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel9.add(jLabel6, new java.awt.GridBagConstraints());

        punishMoneyBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        punishMoneyBox.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanel9.add(punishMoneyBox, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jlabelpass.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jlabelpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabelpass.setText("Date");
        jlabelpass.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(jlabelpass, new java.awt.GridBagConstraints());

        punishDateBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        punishDateBox.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanel10.add(punishDateBox, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel10);

        jPanel5.add(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        addPunishBtn.setBackground(new java.awt.Color(222, 184, 135));
        addPunishBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        addPunishBtn.setForeground(new java.awt.Color(51, 51, 51));
        addPunishBtn.setText("ADD");
        addPunishBtn.setAlignmentY(0.0F);
        addPunishBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addPunishBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        addPunishBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(addPunishBtn);

        updatePunishBtn.setBackground(new java.awt.Color(222, 184, 135));
        updatePunishBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        updatePunishBtn.setForeground(new java.awt.Color(51, 51, 51));
        updatePunishBtn.setText("UPDATE");
        updatePunishBtn.setAlignmentY(0.0F);
        updatePunishBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updatePunishBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(updatePunishBtn);

        delPunishBtn.setBackground(new java.awt.Color(222, 184, 135));
        delPunishBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        delPunishBtn.setForeground(new java.awt.Color(51, 51, 51));
        delPunishBtn.setText("DELETE");
        delPunishBtn.setAlignmentY(0.0F);
        delPunishBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delPunishBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(delPunishBtn);

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
    private javax.swing.JButton addPunishBtn;
    private javax.swing.JPanel allMemberContainer;
    private javax.swing.JDialog chooseMember;
    private javax.swing.JPanel chooseSkeleton;
    private javax.swing.JButton delPunishBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jlabelpass;
    private javax.swing.JTextField offenderNameBox;
    private javax.swing.JTextField punishDateBox;
    private javax.swing.JTextField punishMoneyBox;
    private javax.swing.JTextField punishStatusBox;
    private javax.swing.JTextField punishTypeBox;
    private javax.swing.JTable punishmentTable;
    private javax.swing.JButton updatePunishBtn;
    // End of variables declaration//GEN-END:variables
}
