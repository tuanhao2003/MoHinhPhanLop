package com.example.mhpl.GUI;

import com.example.mhpl.BLL.courseInstructorManageBLL;
import com.example.mhpl.DTO.courseDTO;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import javax.swing.event.*;

public class courseInstructorManageGUI extends javax.swing.JPanel {
    private courseInstructorManageBLL cinmBLL;
    private ArrayList<courseDTO> courseList;
    private ArrayList<Integer> addIntendList;
    
    public courseInstructorManageGUI() {
        this.cinmBLL = new courseInstructorManageBLL();
        this.courseList = new ArrayList<courseDTO>();
        this.addIntendList = new ArrayList<Integer>();
        initComponents();
        eventHandler();
    }
    private void eventHandler(){
        this.courseList=cinmBLL.getNonInstructedCourse();
        renderTable();
//        this.courseContainer.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent m){
//                addTeacherDialog.setVisible(true);
//                addTeacherDialog.setLocationRelativeTo(null);
//                availableStudent.removeAll();
//                cinmBLL.getNoneInCourse().forEach(stu -> {
//                    JPanel panel = new JPanel();
//                    JCheckBox cb = new JCheckBox();
//                    cb.addActionListener(new ActionListener(){
//                        @Override
//                        public void actionPerformed(ActionEvent cbe) {
//                            if(cb.isSelected()){
//                                addIntendList.add(stu.getpersonID());
//                            }else{
//                                addIntendList.remove(stu.getpersonID());
//                            }
//                        }
//                    });
//                    panel.setSize(300, 50);
//                    panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
//                    panel.add(cb);
//                    panel.add(new JLabel(Integer.toString(stu.getpersonID())+" "));
//                    panel.add(new JLabel(stu.getfirstName()+" "));
//                    panel.add(new JLabel(stu.getlastName()));
//                    availableStudent.add(panel);
//                });
//                formContainer.add(availableStudentForm);
//                reload(formContainer);
//            }
//        });
    }
    
    private void renderTable(){
        DefaultTableModel model = (DefaultTableModel) this.courseContainer.getModel();
        model.setRowCount(0);   
        
        for(courseDTO i : courseList){
            Object[] data = {i.getcourseID(), i.gettitle(), this.cinmBLL.getDepartmentName(i.getcourseID()), this.cinmBLL.getCourseStatus(i.getcourseID())};
            model.addRow(data);
        }
        this.courseContainer.getTableHeader().setReorderingAllowed(false);//unable dragging
        this.courseContainer.updateUI();
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

        addTeacherDialog = new javax.swing.JDialog();
        dialogContainer = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cancelAddStudent = new javax.swing.JButton();
        addStudent = new javax.swing.JButton();
        chooseFunction = new javax.swing.JComboBox<>();
        formContainer = new javax.swing.JPanel();
        availableTeacherForm = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        availableStudent = new javax.swing.JPanel();
        newTeacherForm = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        firstNameInp = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lastNameInp = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseContainer = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        addTeacherDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addTeacherDialog.setTitle("Student Assignment");
        addTeacherDialog.setBackground(new java.awt.Color(255, 255, 255));
        addTeacherDialog.setMinimumSize(new java.awt.Dimension(300, 450));
        addTeacherDialog.setResizable(false);
        addTeacherDialog.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        dialogContainer.setMinimumSize(new java.awt.Dimension(300, 400));
        dialogContainer.setPreferredSize(new java.awt.Dimension(300, 400));
        dialogContainer.setLayout(new java.awt.GridBagLayout());

        jPanel4.setMinimumSize(new java.awt.Dimension(300, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        cancelAddStudent.setText("Cancel");
        jPanel6.add(cancelAddStudent);

        addStudent.setText("Add");
        jPanel6.add(addStudent);

        jPanel4.add(jPanel6);

        chooseFunction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available Student", "New Student" }));
        jPanel4.add(chooseFunction);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        dialogContainer.add(jPanel4, gridBagConstraints);

        formContainer.setLayout(new java.awt.GridLayout(1, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        dialogContainer.add(formContainer, gridBagConstraints);

        addTeacherDialog.getContentPane().add(dialogContainer);

        availableTeacherForm.setEnabled(false);
        availableTeacherForm.setPreferredSize(new java.awt.Dimension(300, 300));
        availableTeacherForm.setLayout(new java.awt.GridLayout(1, 0));

        availableStudent.setLayout(new javax.swing.BoxLayout(availableStudent, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(availableStudent);

        availableTeacherForm.add(jScrollPane2);

        newTeacherForm.setPreferredSize(new java.awt.Dimension(300, 300));
        newTeacherForm.setLayout(new java.awt.GridLayout(2, 0, 0, 50));

        jPanel7.setBackground(new java.awt.Color(0, 102, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Firsr Name");
        jLabel2.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel7.add(jLabel2, gridBagConstraints);

        firstNameInp.setToolTipText("");
        firstNameInp.setMinimumSize(new java.awt.Dimension(200, 125));
        firstNameInp.setPreferredSize(new java.awt.Dimension(200, 125));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(firstNameInp, gridBagConstraints);

        newTeacherForm.add(jPanel7);

        jPanel10.setBackground(new java.awt.Color(0, 102, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Last name");
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel3.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanel10.add(jLabel3, gridBagConstraints);

        lastNameInp.setToolTipText("");
        lastNameInp.setMinimumSize(new java.awt.Dimension(200, 125));
        lastNameInp.setPreferredSize(new java.awt.Dimension(200, 125));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(lastNameInp, gridBagConstraints);

        newTeacherForm.add(jPanel10);

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
        jLabel1.setText("Non Instructed Course");
        jLabel1.setAlignmentX(0.5F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel4.setMaximumSize(new java.awt.Dimension(100, 50));
        jLabel4.setMinimumSize(new java.awt.Dimension(100, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel2.add(jLabel4, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel2);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(800, 600));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        courseContainer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "Course ID", "Title", "Department", "Status"
            }
        ));
        jScrollPane1.setViewportView(courseContainer);

        jPanel1.add(jScrollPane1);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudent;
    private javax.swing.JDialog addTeacherDialog;
    private javax.swing.JPanel availableStudent;
    private javax.swing.JPanel availableTeacherForm;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelAddStudent;
    private javax.swing.JComboBox<String> chooseFunction;
    private javax.swing.JTable courseContainer;
    private javax.swing.JPanel dialogContainer;
    private javax.swing.JTextField firstNameInp;
    private javax.swing.JPanel formContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lastNameInp;
    private javax.swing.JPanel newTeacherForm;
    // End of variables declaration//GEN-END:variables
}
