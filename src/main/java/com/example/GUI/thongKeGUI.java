package com.example.GUI;

import com.example.BLL.xuLyBLL;
import com.example.DAL.thanhVien;
import com.example.DAL.xuLy;
import java.awt.event.*;
import java.sql.*;
import java.time.Instant;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class thongKeGUI extends javax.swing.JPanel {

    public thongKeGUI() {
        initComponents();
        eventHandler();
    }

    private void eventHandler() {
        this.statisticTypes.add(this.type1);
        this.statisticTypes.add(this.type2);
        this.statisticTypes.add(this.type3);
    }

/*  
    tạo 3 table tương ứng vs 3 loại thống kê(cop từ form khác sang sử tên) bỏ vào other component, 
    xử lý sự kiện các nút bấm bên dưới để loadtable lên statisticContainer và load các nút kiểu thống kê
    type1-type12-type3 là các kiểu thống kê trong loại thống kê đó(vd theo ngày, theo mã)
    dùng reload() để update sau khi nhấn
*/
    private void reload(JPanel item) {
        item.repaint();
        item.revalidate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pickFile = new javax.swing.JFileChooser();
        type1 = new javax.swing.JButton();
        type2 = new javax.swing.JButton();
        type3 = new javax.swing.JButton();
        statisticContainer = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        statisticTypes = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        memberQuantityBtn = new javax.swing.JButton();
        hiredDevicesBtn = new javax.swing.JButton();
        hiringDevicesBtn = new javax.swing.JButton();
        punishments = new javax.swing.JButton();

        pickFile.setToolTipText("");

        type1.setBackground(new java.awt.Color(225, 202, 69));
        type1.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        type1.setForeground(new java.awt.Color(51, 51, 51));
        type1.setText("ADD");
        type1.setAlignmentY(0.0F);
        type1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        type1.setMaximumSize(new java.awt.Dimension(200, 400));
        type1.setPreferredSize(new java.awt.Dimension(150, 40));

        type2.setBackground(new java.awt.Color(225, 202, 69));
        type2.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        type2.setForeground(new java.awt.Color(51, 51, 51));
        type2.setText("ADD");
        type2.setAlignmentY(0.0F);
        type2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        type2.setMaximumSize(new java.awt.Dimension(200, 400));
        type2.setPreferredSize(new java.awt.Dimension(150, 40));

        type3.setBackground(new java.awt.Color(225, 202, 69));
        type3.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        type3.setForeground(new java.awt.Color(51, 51, 51));
        type3.setText("ADD");
        type3.setAlignmentY(0.0F);
        type3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        type3.setMaximumSize(new java.awt.Dimension(200, 400));
        type3.setPreferredSize(new java.awt.Dimension(150, 40));

        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridBagLayout());

        statisticContainer.setMinimumSize(new java.awt.Dimension(900, 300));
        statisticContainer.setPreferredSize(new java.awt.Dimension(900, 500));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(statisticContainer, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(900, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 100));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        statisticTypes.setPreferredSize(new java.awt.Dimension(900, 50));
        statisticTypes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel1.add(statisticTypes, gridBagConstraints);

        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        memberQuantityBtn.setBackground(new java.awt.Color(222, 184, 135));
        memberQuantityBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        memberQuantityBtn.setForeground(new java.awt.Color(51, 51, 51));
        memberQuantityBtn.setText("MEMBERS");
        memberQuantityBtn.setAlignmentY(0.0F);
        memberQuantityBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        memberQuantityBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        memberQuantityBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(memberQuantityBtn);

        hiredDevicesBtn.setBackground(new java.awt.Color(222, 184, 135));
        hiredDevicesBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        hiredDevicesBtn.setForeground(new java.awt.Color(51, 51, 51));
        hiredDevicesBtn.setText("HIRED DEVICES");
        hiredDevicesBtn.setAlignmentY(0.0F);
        hiredDevicesBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        hiredDevicesBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(hiredDevicesBtn);

        hiringDevicesBtn.setBackground(new java.awt.Color(222, 184, 135));
        hiringDevicesBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        hiringDevicesBtn.setForeground(new java.awt.Color(51, 51, 51));
        hiringDevicesBtn.setText("Hiring Devices");
        hiringDevicesBtn.setAlignmentY(0.0F);
        hiringDevicesBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        hiringDevicesBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(hiringDevicesBtn);

        punishments.setBackground(new java.awt.Color(222, 184, 135));
        punishments.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        punishments.setForeground(new java.awt.Color(51, 51, 51));
        punishments.setText("PUNISHMENTS");
        punishments.setAlignmentY(0.0F);
        punishments.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        punishments.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel4.add(punishments);

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
    private javax.swing.JButton hiredDevicesBtn;
    private javax.swing.JButton hiringDevicesBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton memberQuantityBtn;
    private javax.swing.JFileChooser pickFile;
    private javax.swing.JButton punishments;
    private javax.swing.JScrollPane statisticContainer;
    private javax.swing.JPanel statisticTypes;
    private javax.swing.JButton type1;
    private javax.swing.JButton type2;
    private javax.swing.JButton type3;
    // End of variables declaration//GEN-END:variables
}
