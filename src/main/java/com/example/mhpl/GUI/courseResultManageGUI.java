package com.example.mhpl.GUI;

import com.example.mhpl.BLL.courseResultManageBLL;
import com.example.mhpl.DTO.studentGradeDTO;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import javax.swing.event.*;

public class courseResultManageGUI extends javax.swing.JPanel {
    private courseResultManageBLL crmBLL;
    private ArrayList<studentGradeDTO> studentGradeList;
    
    public courseResultManageGUI() {
        this.crmBLL = new courseResultManageBLL();
        this.studentGradeList = new ArrayList<studentGradeDTO>();
        initComponents();
        eventHandler();
    }
    private void eventHandler(){
        this.addStudentBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
    }
    public void receiveData(int cid){
        this.crmBLL.setCourseID(cid);
        this.studentGradeList = crmBLL.getCourseResult();
        renderTable();
    }
    private void renderTable(){
        DefaultTableModel model = (DefaultTableModel) this.studentList.getModel();
        model.setRowCount(0);
        for(int i = 0; i< studentList.getColumnCount(); i++){
            if(i == 3){
                studentList.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()));
            }else{
                studentList.getColumnModel().getColumn(i).setCellEditor(null);
            }
        }

        for(int i = 0; i< crmBLL.getCourseStudent().size(); i++){
            Object[] data = {this.crmBLL.getCourseStudent().get(i).getfirstName(), this.crmBLL.getCourseStudent().get(i).getlastName(), crmBLL.getCourseStudent().get(i).getenrollmentDate().toString(), Double.toString(this.studentGradeList.get(i).getgrade())};
            model.addRow(data);
            this.studentList.getTableHeader().setReorderingAllowed(false);//unable dragging

            int sid = studentGradeList.get(i).getstudentID();
            int eid = crmBLL.getByCourseAndStudentID(sid).getenrollmentID();
            final int j = i;
            
            studentList.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if(studentList.columnAtPoint(e.getPoint())==3){
                        studentList.setValueAt("", studentList.rowAtPoint(e.getPoint()), 3);
                    }
                }
            });
                    
            studentList.getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener(){
                @Override
                public void editingStopped(ChangeEvent te){
                    double grade;
                    try {
                        grade = Double.parseDouble(studentList.getValueAt(j, 3).toString());
                        crmBLL.saveResult(eid, sid, grade);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Grade type must be double");
                    }
                }
                @Override
                public void editingCanceled(ChangeEvent e) {}
            });
            this.studentList.updateUI();
        }
    }
    
    private void reload(JPanel screen){
        screen.repaint();
        screen.revalidate();
    }
    public JButton backButton(){
        return this.backBtn;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        addStudentDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        addStudentBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentList = new javax.swing.JTable() {
            public boolean isCellEditable(int row,int column){
                if(column != 3) return false;
                return true;
            }
        };

        addStudentDialog.setMinimumSize(new java.awt.Dimension(300, 400));
        addStudentDialog.setResizable(false);
        addStudentDialog.getContentPane().setLayout(new java.awt.GridLayout(1, 0));
        addStudentDialog.getContentPane().add(jPanel3);

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 50));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        backBtn.setBackground(new java.awt.Color(0, 102, 255));
        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("←");
        backBtn.setMaximumSize(new java.awt.Dimension(100, 50));
        backBtn.setMinimumSize(new java.awt.Dimension(100, 50));
        backBtn.setPreferredSize(new java.awt.Dimension(100, 50));
        backBtn.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel2.add(backBtn, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student List ");
        jLabel1.setAlignmentX(0.5F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel2.add(jLabel1, gridBagConstraints);

        addStudentBtn.setBackground(new java.awt.Color(0, 102, 255));
        addStudentBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        addStudentBtn.setForeground(new java.awt.Color(255, 255, 255));
        addStudentBtn.setText("+");
        addStudentBtn.setAlignmentX(1.0F);
        addStudentBtn.setMaximumSize(new java.awt.Dimension(100, 50));
        addStudentBtn.setMinimumSize(new java.awt.Dimension(100, 50));
        addStudentBtn.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(addStudentBtn, gridBagConstraints);

        jPanel1.add(jPanel2);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 550));

        studentList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "First Name", "Last Name", "Enrollment Date", "Grade"
            }
        ));
        jScrollPane1.setViewportView(studentList);

        jPanel1.add(jScrollPane1);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudentBtn;
    private javax.swing.JDialog addStudentDialog;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentList;
    // End of variables declaration//GEN-END:variables
}
