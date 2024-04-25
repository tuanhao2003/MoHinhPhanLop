package com.example.GUI;

import com.example.BLL.thongKeBLL;
import com.example.DAL.thanhVien;
import com.example.DAL.thietBi;
import com.example.DAL.thongTinSd;
import com.example.DAL.xuLy;
import java.util.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class thongKeGUI extends javax.swing.JPanel {
    private thongKeBLL thongKeBLL;
    private ArrayList<thongTinSd> listInfor = new ArrayList<thongTinSd>();
    private ArrayList<xuLy> listPunish = new ArrayList<xuLy>();

    public thongKeGUI() {
        this.thongKeBLL = new thongKeBLL();
        initComponents();
        eventHandler();
    }

    private void eventHandler() {
        this.membersBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Timestamp start = Timestamp.valueOf(new Timestamp(memTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
                Timestamp end = Timestamp.valueOf(new Timestamp(memTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
                listInfor = thongKeBLL.getInforByTime(start, end);
                funcContainer.removeAll();
                statisticContainer.removeAll();
                funcContainer.add(memberStatistic);
                statisticContainer.add(memberContainer);
                reload(funcContainer);
                reload(statisticContainer);
                renderTable(1);
            }
        });
        this.devicesBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Timestamp start = Timestamp.valueOf(new Timestamp(diveTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
                Timestamp end = Timestamp.valueOf(new Timestamp(deviTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
                listInfor = thongKeBLL.getInforByLendTime(start, end);
                funcContainer.removeAll();
                statisticContainer.removeAll();
                funcContainer.add(deviceStatistic);
                statisticContainer.add(deviceContainer);
                reload(funcContainer);
                reload(statisticContainer);
                renderTable(2);
            }
        });
        this.punishmentsBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                listPunish = thongKeBLL.getProcessedPunishment();
                funcContainer.removeAll();
                statisticContainer.removeAll();
                funcContainer.add(punishmentStatistic);
                statisticContainer.add(punishContainer);
                reload(funcContainer);
                reload(statisticContainer);
                renderTable(3);
            }
        });

        this.memTimeStart.addPropertyChangeListener(new PropertyChangeListener() {
            Timestamp start = Timestamp.valueOf(new Timestamp(memTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
            Timestamp end = Timestamp.valueOf(new Timestamp(memTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
            @Override
            public void propertyChange(PropertyChangeEvent e){
                if(start.compareTo(end) > 0){
                    JOptionPane.showMessageDialog(statisticContainer, "start date cannot after end date");
                    memTimeStart.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay()));
                    memTimeEnd.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atTime(23, 59, 59)));
                    start = Timestamp.valueOf(new Timestamp(memTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
                    end = Timestamp.valueOf(new Timestamp(memTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
                }
                listInfor = thongKeBLL.getInforByTime(start, end);
                renderTable(1);
            }
        });
        
        this.memTimeEnd.addPropertyChangeListener(new PropertyChangeListener() {
            Timestamp start = Timestamp.valueOf(new Timestamp(memTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
            Timestamp end = Timestamp.valueOf(new Timestamp(memTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
            @Override
            public void propertyChange(PropertyChangeEvent e){
                if(end.compareTo(start) < 0){
                    JOptionPane.showMessageDialog(statisticContainer, "end date cannot before start date");
                    memTimeStart.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay()));
                    memTimeEnd.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atTime(23, 59, 59)));
                    start = Timestamp.valueOf(new Timestamp(memTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
                    end = Timestamp.valueOf(new Timestamp(memTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
                }
                listInfor = thongKeBLL.getInforByTime(start, end);
                renderTable(1);
            }
        });


        this.deviTimeEnd.addPropertyChangeListener(new PropertyChangeListener() {
            Timestamp start = Timestamp.valueOf(new Timestamp(diveTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
            Timestamp end = Timestamp.valueOf(new Timestamp(deviTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
            @Override
            public void propertyChange(PropertyChangeEvent e){
                if(end.compareTo(start) < 0){
                    JOptionPane.showMessageDialog(statisticContainer, "end date cannot before start date");
                    diveTimeStart.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay()));
                    deviTimeEnd.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atTime(23, 59, 59)));
                    start = Timestamp.valueOf(new Timestamp(diveTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
                    end = Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atTime(23, 59, 59));
                }
                listInfor = thongKeBLL.getInforByLendTime(start, end);
                renderTable(2);
            }
        });

        this.diveTimeStart.addPropertyChangeListener(new PropertyChangeListener() {
            Timestamp start = Timestamp.valueOf(new Timestamp(diveTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
            Timestamp end = Timestamp.valueOf(new Timestamp(deviTimeEnd.getDate().getTime()).toLocalDateTime().toLocalDate().atTime(23,59,59));
            @Override
            public void propertyChange(PropertyChangeEvent e){
                if(start.compareTo(end) > 0){
                    JOptionPane.showMessageDialog(statisticContainer, "start date cannot after end date");
                    diveTimeStart.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay()));
                    deviTimeEnd.setDate(Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atTime(23, 59, 59)));
                    start = Timestamp.valueOf(new Timestamp(diveTimeStart.getDate().getTime()).toLocalDateTime().toLocalDate().atStartOfDay());
                    end = Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atTime(23, 59, 59));
                }
                listInfor = thongKeBLL.getInforByLendTime(start, end);
                renderTable(2);
            }
        });
    }
    
    private void renderTable(int mode) {
        switch (mode) {
            case 1:
                DefaultTableModel modelMember = (DefaultTableModel) this.memberTable.getModel();
                modelMember.setRowCount(0);
                for(thongTinSd i : listInfor){
                    
                    String memid = Integer.toString(i.getThanhvien().getMatv());
                    thanhVien mem = thongKeBLL.getMember(i.getThanhvien().getMatv());
                    String memName = mem.getHoten();
                    String memMajor = mem.getKhoa();
                    String memSubmajor = mem.getNganh();
                    String memPhone = mem.getSdt();
                    String memMail = mem.getEmail();
                    Object[] data = {memid, memName, memMajor, memSubmajor, memPhone, memMail, i.getTgvao().toString()};
                    modelMember.addRow(data);
                    this.memberTable.updateUI();
                }
                break;
            case 2:
                DefaultTableModel modelDevice = (DefaultTableModel) this.deviceTable.getModel();
                modelDevice.setRowCount(0);

                for (thongTinSd i : listInfor) {
                    String deviId = Integer.toString(i.getThietbi().getMatb());
                    thietBi devi = thongKeBLL.getDevice(i.getThietbi().getMatb());
                    String deviName = devi.getTentb();
                    String deviDescript = devi.getMotatb();
                    Object[] data = {deviId, deviName, deviDescript};
                    modelDevice.addRow(data);
                    this.deviceTable.updateUI();
                }
                break;
            case 3:
                DefaultTableModel modelPunish = (DefaultTableModel) this.punishmentTable.getModel();
                modelPunish.setRowCount(0);

                for (xuLy i : listPunish) {
                    String id = Integer.toString(i.getMaxl());
                    thanhVien offender = thongKeBLL.getPunishment(i.getMaxl()).getThanhvien();
                    String offenderName = offender.getHoten();
                    String phone = offender.getSdt();
                    String type = i.getHinhthucxl();
                    String money = Integer.toString(i.getSotien());
                    String punishDate =  i.getNgayxl() == null ? "" : i.getNgayxl().toString();
                    Object[] data = {id, offenderName, phone, type, money, punishDate};
                    modelPunish.addRow(data);
                    this.punishmentTable.updateUI();
                }
                break;
        }
    }

    private void reload(JPanel item) {
        item.repaint();
        item.revalidate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        memberStatistic = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        memTimeStart = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        memTimeEnd = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        major = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        submajor = new javax.swing.JTextField();
        deviceStatistic = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        diveTimeStart = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        deviTimeEnd = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        deviceNameBox = new javax.swing.JTextField();
        lendingBtn = new javax.swing.JToggleButton();
        punishmentStatistic = new javax.swing.JPanel();
        processed = new javax.swing.JButton();
        processing = new javax.swing.JButton();
        memberContainer = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        deviceContainer = new javax.swing.JScrollPane();
        deviceTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        punishContainer = new javax.swing.JScrollPane();
        punishmentTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        statisticContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        funcContainer = new javax.swing.JPanel();
        jpanel = new javax.swing.JPanel();
        membersBtn = new javax.swing.JButton();
        devicesBtn = new javax.swing.JButton();
        punishmentsBtn = new javax.swing.JButton();
        totalQuantity = new javax.swing.JLabel();

        memberStatistic.setMinimumSize(new java.awt.Dimension(900, 50));
        memberStatistic.setPreferredSize(new java.awt.Dimension(900, 50));
        memberStatistic.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("From");
        jLabel6.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 50));
        memberStatistic.add(jLabel6, new java.awt.GridBagConstraints());

        jPanel5.setMinimumSize(new java.awt.Dimension(200, 30));
        jPanel5.setLayout(new java.awt.CardLayout());

        memTimeStart.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel5.add(memTimeStart, "card2");

        memberStatistic.add(jPanel5, new java.awt.GridBagConstraints());

        jLabel7.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("To");
        jLabel7.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 50));
        memberStatistic.add(jLabel7, new java.awt.GridBagConstraints());

        jPanel6.setMinimumSize(new java.awt.Dimension(150, 40));
        jPanel6.setLayout(new java.awt.CardLayout());

        memTimeEnd.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel6.add(memTimeEnd, "card2");

        memberStatistic.add(jPanel6, new java.awt.GridBagConstraints());

        jLabel8.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Major");
        jLabel8.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 50));
        memberStatistic.add(jLabel8, new java.awt.GridBagConstraints());

        major.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        major.setPreferredSize(new java.awt.Dimension(100, 40));
        memberStatistic.add(major, new java.awt.GridBagConstraints());

        jLabel9.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Submajor");
        jLabel9.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel9.setPreferredSize(new java.awt.Dimension(90, 50));
        memberStatistic.add(jLabel9, new java.awt.GridBagConstraints());

        submajor.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        submajor.setPreferredSize(new java.awt.Dimension(100, 40));
        memberStatistic.add(submajor, new java.awt.GridBagConstraints());

        deviceStatistic.setPreferredSize(new java.awt.Dimension(900, 50));
        deviceStatistic.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("From");
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 50));
        deviceStatistic.add(jLabel3, new java.awt.GridBagConstraints());

        jPanel3.setMinimumSize(new java.awt.Dimension(200, 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel3.setLayout(new java.awt.CardLayout());

        diveTimeStart.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel3.add(diveTimeStart, "card2");

        deviceStatistic.add(jPanel3, new java.awt.GridBagConstraints());

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("To");
        jLabel4.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 50));
        deviceStatistic.add(jLabel4, new java.awt.GridBagConstraints());

        jPanel2.setMinimumSize(new java.awt.Dimension(150, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel2.setLayout(new java.awt.CardLayout());

        deviTimeEnd.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel2.add(deviTimeEnd, "card2");

        deviceStatistic.add(jPanel2, new java.awt.GridBagConstraints());

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Name");
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 50));
        deviceStatistic.add(jLabel5, new java.awt.GridBagConstraints());

        deviceNameBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        deviceNameBox.setPreferredSize(new java.awt.Dimension(150, 40));
        deviceStatistic.add(deviceNameBox, new java.awt.GridBagConstraints());

        lendingBtn.setBackground(new java.awt.Color(222, 184, 135));
        lendingBtn.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        lendingBtn.setText("LENDING");
        lendingBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        deviceStatistic.add(lendingBtn, new java.awt.GridBagConstraints());

        punishmentStatistic.setPreferredSize(new java.awt.Dimension(900, 50));
        punishmentStatistic.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        processed.setBackground(new java.awt.Color(222, 184, 135));
        processed.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        processed.setForeground(new java.awt.Color(51, 51, 51));
        processed.setText("PROCESSED");
        processed.setAlignmentY(0.0F);
        processed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        processed.setMaximumSize(new java.awt.Dimension(200, 400));
        processed.setPreferredSize(new java.awt.Dimension(150, 40));
        punishmentStatistic.add(processed);

        processing.setBackground(new java.awt.Color(222, 184, 135));
        processing.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        processing.setForeground(new java.awt.Color(51, 51, 51));
        processing.setText("PROCESSING");
        processing.setAlignmentY(0.0F);
        processing.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        processing.setPreferredSize(new java.awt.Dimension(150, 40));
        punishmentStatistic.add(processing);

        memberContainer.setMinimumSize(new java.awt.Dimension(900, 300));
        memberContainer.setPreferredSize(new java.awt.Dimension(900, 500));

        memberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Name", "Major", "Submajor", "Phone", "Email", "Check in time"
            }
        ));
        memberTable.setUpdateSelectionOnSort(false);
        memberContainer.setViewportView(memberTable);

        deviceContainer.setMinimumSize(new java.awt.Dimension(900, 300));
        deviceContainer.setPreferredSize(new java.awt.Dimension(900, 500));

        deviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Device Name", "Descriptions"
            }
        ));
        deviceTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        deviceContainer.setViewportView(deviceTable);

        punishContainer.setMinimumSize(new java.awt.Dimension(900, 300));
        punishContainer.setPreferredSize(new java.awt.Dimension(900, 500));

        punishmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Offender", "Phone", "Punish type", "price", "Punish date"
            }
        ));
        punishmentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        punishContainer.setViewportView(punishmentTable);

        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridBagLayout());

        statisticContainer.setMinimumSize(new java.awt.Dimension(800, 310));
        statisticContainer.setPreferredSize(new java.awt.Dimension(900, 500));
        statisticContainer.setLayout(new java.awt.GridLayout(1, 0));
        add(statisticContainer, new java.awt.GridBagConstraints());

        jPanel1.setMinimumSize(new java.awt.Dimension(900, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 100));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        funcContainer.setPreferredSize(new java.awt.Dimension(900, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jPanel1.add(funcContainer, gridBagConstraints);

        jpanel.setPreferredSize(new java.awt.Dimension(900, 50));
        jpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        membersBtn.setBackground(new java.awt.Color(222, 184, 135));
        membersBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        membersBtn.setForeground(new java.awt.Color(51, 51, 51));
        membersBtn.setText("MEMBERS");
        membersBtn.setAlignmentY(0.0F);
        membersBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        membersBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        membersBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jpanel.add(membersBtn);

        devicesBtn.setBackground(new java.awt.Color(222, 184, 135));
        devicesBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        devicesBtn.setForeground(new java.awt.Color(51, 51, 51));
        devicesBtn.setText("DEVICES");
        devicesBtn.setAlignmentY(0.0F);
        devicesBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        devicesBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jpanel.add(devicesBtn);

        punishmentsBtn.setBackground(new java.awt.Color(222, 184, 135));
        punishmentsBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        punishmentsBtn.setForeground(new java.awt.Color(51, 51, 51));
        punishmentsBtn.setText("PUNISHMENTS");
        punishmentsBtn.setAlignmentY(0.0F);
        punishmentsBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        punishmentsBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jpanel.add(punishmentsBtn);

        totalQuantity.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        totalQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalQuantity.setText("TOTAL: ");
        totalQuantity.setMinimumSize(new java.awt.Dimension(50, 50));
        totalQuantity.setPreferredSize(new java.awt.Dimension(90, 50));
        jpanel.add(totalQuantity);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel1.add(jpanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser deviTimeEnd;
    private javax.swing.JScrollPane deviceContainer;
    private javax.swing.JTextField deviceNameBox;
    private javax.swing.JPanel deviceStatistic;
    private javax.swing.JTable deviceTable;
    private javax.swing.JButton devicesBtn;
    private com.toedter.calendar.JDateChooser diveTimeStart;
    private javax.swing.JPanel funcContainer;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jpanel;
    private javax.swing.JToggleButton lendingBtn;
    private javax.swing.JTextField major;
    private com.toedter.calendar.JDateChooser memTimeEnd;
    private com.toedter.calendar.JDateChooser memTimeStart;
    private javax.swing.JScrollPane memberContainer;
    private javax.swing.JPanel memberStatistic;
    private javax.swing.JTable memberTable;
    private javax.swing.JButton membersBtn;
    private javax.swing.JButton processed;
    private javax.swing.JButton processing;
    private javax.swing.JScrollPane punishContainer;
    private javax.swing.JPanel punishmentStatistic;
    private javax.swing.JTable punishmentTable;
    private javax.swing.JButton punishmentsBtn;
    private javax.swing.JPanel statisticContainer;
    private javax.swing.JTextField submajor;
    private javax.swing.JLabel totalQuantity;
    // End of variables declaration//GEN-END:variables
}
