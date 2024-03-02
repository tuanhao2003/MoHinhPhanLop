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

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Managemet");
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        mainPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(mainPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
