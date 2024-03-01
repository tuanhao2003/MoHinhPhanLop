package com.example.mhpl.GUI;

import com.example.mhpl.BLL.courseInformationManageBLL;
import com.example.mhpl.DTO.courseDTO;
import com.example.mhpl.DTO.teacherDTO;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
//detailContainer đổi thành add panel vào
public class courseInformationManageGUI extends javax.swing.JPanel {
    private courseInformationManageBLL cimBLL;
    private ArrayList<courseDTO> courseList;

    public courseInformationManageGUI() {
        this.cimBLL = new courseInformationManageBLL();
        this.courseList = this.cimBLL.getAllCourse();
        initComponents();
        eventHandler();
    }
    
    public void eventHandler(){
        renderList(this.courseList);
        this.courseContainer.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent m){
                detailContainer.removeAll();
                int id = Integer.parseInt(courseContainer.getValueAt(courseContainer.rowAtPoint(m.getPoint()), 0).toString());
                detailContainer.add(new JLabel("Course type: " + cimBLL.getCourseType(id) + "\n"));
                detailContainer.add(new JLabel("Course title: " + cimBLL.getCourseByID(id).gettitle() + "\n"));
                detailContainer.add(new JLabel("Course credits: " + cimBLL.getCourseByID(id).getcredits() + "\n"));
                detailContainer.add(new JLabel("Department: " + cimBLL.getDepartmentName(id) + "\n"));
                String courseTeacher = "";
                for(teacherDTO i : cimBLL.getCourseTeacher(id)){
                    courseTeacher = courseTeacher + "- " + i.getfirstName() + " " + i.getlastName() + "\n";
                }
                detailContainer.add(new JLabel("Teacher: " + courseTeacher + "\n"));
                detailContainer.add(new JLabel("Student quatity: " + cimBLL.getCourseStudent(id).size() + "\n"));
                reload(detailContainer);
            }
        });
    }
    
    public void renderList(ArrayList<courseDTO> lst){
        DefaultTableModel model = (DefaultTableModel) this.courseContainer.getModel();
        model.setRowCount(0);   
        
        for(courseDTO i : courseList){
            Object[] data = {i.getcourseID(), i.gettitle(), this.cimBLL.getDepartmentName(i.getcourseID()), this.cimBLL.getCourseStatus(i.getcourseID())};
            model.addRow(data);
        }
        this.courseContainer.getTableHeader().setReorderingAllowed(false);//unable dragging
        this.courseContainer.updateUI();
    }

    public void reload(JPanel screen){
        screen.repaint();
        screen.revalidate();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        courseContainer = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        detailContainer = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 500));

        courseContainer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "Course ID", "Title", "Department", "Status"
            }
        ));
        jScrollPane1.setViewportView(courseContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 484;
        gridBagConstraints.ipady = 480;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        detailContainer.setBackground(new java.awt.Color(255, 255, 255));
        detailContainer.setPreferredSize(new java.awt.Dimension(300, 300));
        detailContainer.setLayout(new java.awt.GridLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        detailContainer.add(jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(detailContainer, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(300, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 2, 0));

        deleteButton.setBackground(new java.awt.Color(0, 102, 255));
        deleteButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete This Course");
        deleteButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.add(deleteButton);

        updateButton.setBackground(new java.awt.Color(0, 102, 255));
        updateButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Change Information");
        updateButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.add(updateButton);

        jPanel1.add(jPanel2);

        viewButton.setBackground(new java.awt.Color(0, 102, 255));
        viewButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        viewButton.setForeground(new java.awt.Color(255, 255, 255));
        viewButton.setText("View Student List");
        viewButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel1.add(viewButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel1, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTextField1.setText("jTextField1");
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.add(jTextField1, new java.awt.GridBagConstraints());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBox1.setMinimumSize(new java.awt.Dimension(100, 50));
        jComboBox1.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel3.add(jComboBox1, new java.awt.GridBagConstraints());

        addButton.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        addButton.setText("+");
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel3.add(addButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 299;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setBackground(new java.awt.Color(0, 102, 255));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course Information");
        jPanel4.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 68;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable courseContainer;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel detailContainer;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
