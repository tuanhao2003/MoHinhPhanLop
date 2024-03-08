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
    private courseDTO currentCourse;

    public courseInformationManageGUI() {
        this.cimBLL = new courseInformationManageBLL();
        this.courseList = cimBLL.getInstructedCourse();
        this.currentCourse=null;
        initComponents();
        eventHandler();
    }
    
    private void eventHandler(){
        renderTable();
        this.courseContainer.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent m){
                detailContainer.removeAll();
                
                int id = Integer.parseInt(courseContainer.getValueAt(courseContainer.rowAtPoint(m.getPoint()), 0).toString());
                currentCourse = cimBLL.getCourseByID(id);
                String courseType = cimBLL.getCourseType(id);
                String courseTitle = currentCourse.gettitle();
                int courseCredits = currentCourse.getcredits();
                String departmentName = cimBLL.getDepartmentName(id);
                int courseQuantity = cimBLL.getCourseStudent(id).size();
                String courseTeacher = cimBLL.getCourseTeacher(id).getfirstName() + " " + cimBLL.getCourseTeacher(id).getlastName();
               
                
                detailContainer.add(new JLabel("Course type: " + courseType + "\n"));
                detailContainer.add(new JLabel("Course title: " + courseTitle + "\n"));
                detailContainer.add(new JLabel("Course credits: " + courseCredits + "\n"));
                detailContainer.add(new JLabel("Department: " + departmentName + "\n"));
                detailContainer.add(new JLabel("Teacher: " + courseTeacher + "\n"));
                detailContainer.add(new JLabel("Student quatity: " + courseQuantity + "\n"));
                reload(detailContainer);
                
                if(!cimBLL.getCourseStudent(id).isEmpty()){
                    viewButton.setEnabled(true);
                }else{
                    viewButton.setEnabled(false);
                }
            }
        });
        
        this.detailContainer.addContainerListener(new ContainerAdapter(){
            @Override
            public void componentRemoved(ContainerEvent e){
                if(detailContainer.getComponentCount() >= 0){
                    deleteButton.setEnabled(false);
                    updateButton.setEnabled(false);
                    reload(buttonPanel);
                }
            }
            @Override
            public void componentAdded(ContainerEvent e){
                if(detailContainer.getComponentCount() != 0){
                    deleteButton.setEnabled(true);
                    updateButton.setEnabled(true);
                    reload(buttonPanel);
                }
            }
        });
        
        this.deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cimBLL.deleteCourse(currentCourse.getcourseID());
                detailContainer.removeAll();
                courseList = cimBLL.getInstructedCourse();
                renderTable();
                reload(detailContainer);
            }
        });
        
        this.courseContainer.getTableHeader().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent m){
                if(courseContainer.columnAtPoint(m.getPoint()) == 0){
                    cimBLL.sortCourseByID(courseList);
                    if(courseContainer.getColumnModel().getColumn(0).getHeaderValue().toString().equals("Course ID ▼")){
                        courseContainer.getColumnModel().getColumn(0).setHeaderValue("Course ID ▲");
                    }else{
                        courseContainer.getColumnModel().getColumn(0).setHeaderValue("Course ID ▼");
                    }
                }
                renderTable();
            }
        });
        
        this.searchBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                courseList = cimBLL.getCourseByTitle(searchBox.getText());
                renderTable();
            }
        });
        
        this.addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<String> departmentName = new ArrayList<String>();
                cimBLL.getAllDepartment().forEach(department -> departmentName.add(department.getname()));
                String[] optionList = new String[departmentName.size()];
                for(int i =0; i < departmentName.size(); i++){
                    optionList[i] = departmentName.get(i);
                }
                departmentInp.setModel(new javax.swing.DefaultComboBoxModel<>(optionList));
                addDialog.setVisible(true);
                addDialog.setLocationRelativeTo(null);
                addBtn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e1){
                        try {
                            Integer.parseInt(creditsInp1.getText());
                            cimBLL.addCourse(titleInp1.getText(), Integer.parseInt(creditsInp1.getText()), cimBLL.getAllDepartment().get(departmentInp.getSelectedIndex()).getdepartmentID());
                            titleInp1.setText("");
                            creditsInp1.setText("");
                            addDialog.dispose();
                            courseList = cimBLL.getInstructedCourse();
                            renderTable();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Credits must be Integer");
                        }
                    }
                });
            }
        });
        this.cancelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e1){
                titleInp1.setText("");
                creditsInp1.setText("");
                addDialog.dispose();
            }
        });
    }
    private void renderTable(){
        DefaultTableModel model = (DefaultTableModel) this.courseContainer.getModel();
        model.setRowCount(0);   
        
        for(courseDTO i : courseList){
            Object[] data = {i.getcourseID(), i.gettitle(), this.cimBLL.getDepartmentName(i.getcourseID()), this.cimBLL.getCourseStatus(i.getcourseID())};
            model.addRow(data);
        }
        this.courseContainer.getTableHeader().setReorderingAllowed(false);//unable dragging
        this.courseContainer.updateUI();
    }

    private void reload(JPanel screen){
        screen.repaint();
        screen.revalidate();
    }
    
    public void reloadData(){
        this.courseList = cimBLL.getInstructedCourse();
        renderTable();
    }
    
    public JButton getviewButton(){
        return this.viewButton;
    }
    public int getCurrentCourseID(){
        return this.currentCourse.getcourseID();
    }
    
    public JComboBox getComboBox(){
        return jComboBox1;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        addDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        titleInp1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        creditsInp1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        departmentInp = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseContainer = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        detailContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();

        addDialog.setTitle("addCourse");
        addDialog.setMinimumSize(new java.awt.Dimension(300, 400));
        addDialog.setResizable(false);
        addDialog.getContentPane().setLayout(new javax.swing.BoxLayout(addDialog.getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Course title");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(65, 20, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        titleInp1.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 80);
        jPanel2.add(titleInp1, gridBagConstraints);

        jLabel6.setText("Course credits");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(65, 20, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        creditsInp1.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 80);
        jPanel2.add(creditsInp1, gridBagConstraints);

        jLabel7.setText("Department ID");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(65, 20, 0, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        departmentInp.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 17, 80);
        jPanel2.add(departmentInp, gridBagConstraints);

        addDialog.getContentPane().add(jPanel2);

        jPanel5.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        cancelBtn.setText("Cancel");
        jPanel5.add(cancelBtn);

        addBtn.setText("Add");
        jPanel5.add(addBtn);

        addDialog.getContentPane().add(jPanel5);

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jPanel8.setAlignmentX(0.0F);
        jPanel8.setAlignmentY(0.0F);
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        searchBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchBox.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.add(searchBox, new java.awt.GridBagConstraints());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Course Active", "Course Inactive" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox1.setMinimumSize(new java.awt.Dimension(150, 50));
        jComboBox1.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel3.add(jComboBox1, new java.awt.GridBagConstraints());

        addButton.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        addButton.setText("+");
        addButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jPanel8.add(jPanel3, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(500, 500));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 500));

        courseContainer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "Course ID ▼", "Title", "Department", "Status"
            }
        ));
        jScrollPane1.setViewportView(courseContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel8, gridBagConstraints);

        jPanel7.setAlignmentX(1.0F);
        jPanel7.setAlignmentY(0.0F);
        jPanel7.setPreferredSize(new java.awt.Dimension(300, 600));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setBackground(new java.awt.Color(0, 102, 255));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course Information");
        jPanel4.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 68;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel4, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        detailContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray));
        detailContainer.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        detailContainer.setMaximumSize(new java.awt.Dimension(300, 300));
        detailContainer.setMinimumSize(new java.awt.Dimension(300, 300));
        detailContainer.setPreferredSize(new java.awt.Dimension(300, 300));
        detailContainer.setLayout(new javax.swing.BoxLayout(detailContainer, javax.swing.BoxLayout.Y_AXIS));
        jPanel6.add(detailContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel6, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(300, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        buttonPanel.setLayout(new java.awt.GridLayout(1, 0, 2, 0));

        deleteButton.setBackground(new java.awt.Color(0, 102, 255));
        deleteButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete This Course");
        deleteButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        deleteButton.setEnabled(false);
        buttonPanel.add(deleteButton);

        updateButton.setBackground(new java.awt.Color(0, 102, 255));
        updateButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Change Information");
        updateButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        updateButton.setEnabled(false);
        buttonPanel.add(updateButton);

        jPanel1.add(buttonPanel);

        viewButton.setBackground(new java.awt.Color(0, 102, 255));
        viewButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        viewButton.setForeground(new java.awt.Color(255, 255, 255));
        viewButton.setText("View Student List");
        viewButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        viewButton.setEnabled(false);
        jPanel1.add(viewButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel7, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton addButton;
    private javax.swing.JDialog addDialog;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTable courseContainer;
    private javax.swing.JTextField creditsInp1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> departmentInp;
    private javax.swing.JPanel detailContainer;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTextField titleInp1;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
