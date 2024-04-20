package com.example.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainGUI extends javax.swing.JFrame {

    private String mainFont = "Sitka Text";

    public mainGUI() {
        initComponents();
        addComponent("Members", new thanhVienGUI());
        addComponent("Devices", new thietBiGUI());
        addComponent("Punishments", new Xulivipham());
        addComponent("Statistics", new ThongKe());
        eventHandler();
    }

    private void addComponent(String buttonName, JPanel panelName, String path) {
        int compQuantity = this.toolsPanel.getComponentCount();
        this.toolsPanel.setPreferredSize(new Dimension(this.toolsPanel.getWidth(), 50 * (compQuantity + 1)));
        JButton btn = new JButton(buttonName);
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        btn.setBackground(new Color(222,184,135));
        btn.setForeground(Color.DARK_GRAY);
        btn.setFont(new Font(this.mainFont, Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setIconTextGap(30);
        if (path == null) {
            btn.setIcon(null);
        } else {
            btn.setIcon(new ImageIcon(new ImageIcon(mainGUI.class.getResource("/Comp/" + path)).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainDisplay.removeAll();
                mainDisplay.add(panelName);
                mainDisplay.repaint();
                mainDisplay.revalidate();
                title.setText(buttonName);
            }
        });
        this.toolsPanel.add(btn);
        this.title.requestFocusInWindow();
    }

    private void addComponent(String buttonName, JPanel panelName) {
        addComponent(buttonName, panelName, null);
    }

    private void eventHandler() {

        this.title.setText("Wellcome");
    }

    public static void main(String[] args) {
        mainGUI display = new mainGUI();
        display.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        logoContainer = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        scrollBar = new javax.swing.JScrollPane();
        toolsPanel = new javax.swing.JPanel();
        mainDisplay = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Members management");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1150, 650));

        logoContainer.setBackground(new java.awt.Color(219, 169, 121));
        logoContainer.setMaximumSize(new java.awt.Dimension(300, 100));
        logoContainer.setMinimumSize(new java.awt.Dimension(200, 100));
        logoContainer.setPreferredSize(new java.awt.Dimension(200, 100));
        logoContainer.setLayout(new java.awt.CardLayout());

        title.setFont(new java.awt.Font("Sitka Text", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        title.setIconTextGap(0);
        logoContainer.add(title, "card2");

        scrollBar.setBackground(new java.awt.Color(200, 200, 200));
        scrollBar.setBorder(null);
        scrollBar.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setPreferredSize(new java.awt.Dimension(200, 550));
        JScrollBar scb = new JScrollBar(JScrollBar.VERTICAL);
        scb.setPreferredSize(new Dimension(0,0));
        scrollBar.setVerticalScrollBar(scb);

        toolsPanel.setBackground(new java.awt.Color(175, 209, 152));
        toolsPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        toolsPanel.setPreferredSize(new java.awt.Dimension(0, 0));
        toolsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        scrollBar.setViewportView(toolsPanel);

        mainDisplay.setAlignmentX(0.0F);
        mainDisplay.setAlignmentY(0.0F);
        mainDisplay.setMaximumSize(new java.awt.Dimension(900, 600));
        mainDisplay.setMinimumSize(new java.awt.Dimension(900, 600));
        mainDisplay.setPreferredSize(new java.awt.Dimension(950, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(mainDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(scrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel logoContainer;
    private javax.swing.JPanel mainDisplay;
    private javax.swing.JScrollPane scrollBar;
    private javax.swing.JLabel title;
    private javax.swing.JPanel toolsPanel;
    // End of variables declaration//GEN-END:variables
}
