package com.example.mhpl.GUI;

import java.awt.event.*;

public class mainFrame extends javax.swing.JFrame {
    private courseInformationManageGUI courseInformationGUI;
    private courseResultManageGUI courseResultManageGUI;
    private courseInstructorManageGUI courseInstructorManageGUI;
    
    public mainFrame() {
        this.courseInformationGUI = new courseInformationManageGUI();
        this.courseResultManageGUI = new courseResultManageGUI();
        this.courseInstructorManageGUI = new courseInstructorManageGUI();
        initComponents();
        eventHandler();
    }
    
    private void eventHandler(){
        this.mainPanel.add(this.courseInformationGUI);
        reload();
        this.courseInformationGUI.getviewButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                courseResultManageGUI.receiveData(courseInformationGUI.getCurrentCourseID());
                mainPanel.removeAll();
                mainPanel.add(courseResultManageGUI);
                reload();
            }
        });
        this.courseInformationGUI.getComboBox().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(courseInformationGUI.getComboBox().getSelectedIndex() != 0){
                    mainPanel.removeAll();
                    courseInstructorManageGUI.reloadData();
                    mainPanel.add(courseInstructorManageGUI);
                    reload();
                }
                
            }
        });
        this.courseResultManageGUI.backButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mainPanel.removeAll();
                courseInformationGUI.reloadData();
                mainPanel.add(courseInformationGUI);
                reload();
            }
        });
        
        this.courseInstructorManageGUI.backButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mainPanel.removeAll();
                courseInformationGUI.reloadData();
                courseInformationGUI.getComboBox().setSelectedIndex(0);
                mainPanel.add(courseInformationGUI);
                reload();
            }
        });
    }
    
    private void reload(){
        mainPanel.repaint();
        mainPanel.revalidate();
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
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        mainPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(mainPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
