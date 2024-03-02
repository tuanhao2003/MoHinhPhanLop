package com.example.mhpl.GUI;

import javax.swing.JPanel;

public class mainFrame extends javax.swing.JFrame {
    public mainFrame() {
        initComponents();
        eventHandler();
    }
    
    public void eventHandler(){
        this.mainPanel.add(new courseInformationManageGUI());
        reload(this.mainPanel);
    }
    
    public void reload(JPanel panel){
        panel.repaint();
        panel.revalidate();
    }
    
    public static void main(String[] args) {
        mainFrame display = new mainFrame();
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Managemet");
        setPreferredSize(new java.awt.Dimension(1000, 650));
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setBackground(new java.awt.Color(0, 102, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        mainPanel.setLayout(new java.awt.GridLayout(1, 0));
        jTabbedPane1.addTab("Course Information", mainPanel);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
