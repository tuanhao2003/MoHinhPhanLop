package com.example.mhpl.GUI;

import com.example.mhpl.BLL.courseInformationManageBLL;
import com.example.mhpl.DTO.courseDTO;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.Time;
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
                String courseData = cimBLL.getCourseData(id);
                
                detailContainer.add(new JLabel("Course type: " + courseType));
                detailContainer.add(new JLabel("Course title: " + courseTitle));
                detailContainer.add(new JLabel("Course credits: " + courseCredits));
                detailContainer.add(new JLabel("Department: " + departmentName));
                detailContainer.add(new JLabel("Teacher: " + courseTeacher));
                detailContainer.add(new JLabel("Student quatity: " + courseQuantity));
                for(String i : courseData.split("#")){
                    detailContainer.add(new JLabel(i));
                }
                
                reload(detailContainer);
                
                if(cimBLL.getCourseStudent(id).size() >= 0){
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
                typeForm.removeAll();
                typeForm.add(onlineType);
                reload(typeForm);
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
                            Integer.parseInt(creditsInp.getText());
                            cimBLL.addCourse(titleInp.getText(), Integer.parseInt(creditsInp.getText()), cimBLL.getAllDepartment().get(departmentInp.getSelectedIndex()).getdepartmentID());
                            if(courseType.getSelectedIndex()==0){
                                cimBLL.addOnlineCourse(cimBLL.getLastedCourseID(), urlInp.getText());
                            }else{
                                cimBLL.addOnsiteCourse(cimBLL.getLastedCourseID(), locationInp.getText(), daysInp.getText(),Time.valueOf(timeInp.getText()));
                            }
                            titleInp.setText("");
                            creditsInp.setText("");
                            urlInp.setText("");
                            locationInp.setText("");
                            daysInp.setText("");
                            timeInp.setText("");
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
                titleInp.setText("");
                creditsInp.setText("");
                addDialog.dispose();
            }
        });
        
        this.courseType.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(courseType.getSelectedIndex() == 0){
                    typeForm.removeAll();
                    typeForm.add(onlineType);
                    reload(typeForm);
                }else{
                    timeInp.setText(new Time(System.currentTimeMillis()).toString());
                    typeForm.removeAll();
                    typeForm.add(onsiteType);
                    reload(typeForm);
                }
            }
        });
        
        this.courseType1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(courseType1.getSelectedIndex() == 0){
                    typeForm1.removeAll();
                    urlInp1.setText(cimBLL.getOnlineCourseByID(currentCourse.getcourseID()).geturl());
                    typeForm1.add(onlineType1);
                    reload(typeForm1);
                }else{
                    timeInp1.setText(new Time(System.currentTimeMillis()).toString());
                    locationInp1.setText(cimBLL.getOnsiteCourseByID(currentCourse.getcourseID()).getlocation());
                    daysInp1.setText(cimBLL.getOnsiteCourseByID(currentCourse.getcourseID()).getdays());
                    timeInp1.setText(cimBLL.getOnsiteCourseByID(currentCourse.getcourseID()).gettime().toString());
                    typeForm1.removeAll();
                    typeForm1.add(onsiteType1);
                    reload(typeForm1);
                }
            }
        });
        
        this.updateButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(cimBLL.getCourseType(currentCourse.getcourseID()).equals("Offline")){
                    courseType1.setSelectedIndex(1); 
                    typeForm1.removeAll();
                    typeForm1.add(onsiteType1);
                    reload(typeForm1);
                }else{
                    typeForm1.removeAll();
                    typeForm1.add(onlineType1);
                    reload(typeForm1);
                }
                
                titleInp1.setText(currentCourse.gettitle());
                creditsInp1.setText(Integer.toString(currentCourse.getcredits()));
                departmentInp1.setSelectedItem(cimBLL.getDepartmentName(currentCourse.getcourseID()));
                
                ArrayList<String> departmentName = new ArrayList<String>();
                cimBLL.getAllDepartment().forEach(department -> departmentName.add(department.getname()));
                String[] optionList = new String[departmentName.size()];
                for(int i =0; i < departmentName.size(); i++){
                    optionList[i] = departmentName.get(i);
                }
                departmentInp1.setModel(new javax.swing.DefaultComboBoxModel<>(optionList));
                changeDialog.setVisible(true);
                changeDialog.setLocationRelativeTo(null);
                addBtn1.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e1){
                        try {
                            Integer.parseInt(creditsInp1.getText());
                            cimBLL.updateCourse(currentCourse.getcourseID(), titleInp1.getText(), Integer.valueOf(creditsInp1.getText()), cimBLL.getAllDepartment().get(departmentInp1.getSelectedIndex()).getdepartmentID());
                            if(courseType1.getSelectedIndex()==0){
                                cimBLL.updateOnlineCourse(currentCourse.getcourseID(), urlInp1.getText());
                            }else{
                                cimBLL.updateOnsiteCourse(currentCourse.getcourseID(), locationInp1.getText(), daysInp1.getText(),Time.valueOf(timeInp1.getText()));
                            }
                            titleInp1.setText("");
                            creditsInp1.setText("");
                            urlInp1.setText("");
                            locationInp1.setText("");
                            daysInp1.setText("");
                            timeInp1.setText("");
                            changeDialog.dispose();
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
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        titleInp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        creditsInp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        departmentInp = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        courseType = new javax.swing.JComboBox<>();
        typeForm = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        onlineType = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        urlInp = new javax.swing.JTextField();
        onsiteType = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        locationInp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        daysInp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        timeInp = new javax.swing.JTextField();
        changeDialog = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        titleInp1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        creditsInp1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        departmentInp1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        courseType1 = new javax.swing.JComboBox<>();
        typeForm1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        cancelBtn1 = new javax.swing.JButton();
        addBtn1 = new javax.swing.JButton();
        onlineType1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        urlInp1 = new javax.swing.JTextField();
        onsiteType1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        locationInp1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        daysInp1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        timeInp1 = new javax.swing.JTextField();
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
        addDialog.setMinimumSize(new java.awt.Dimension(600, 400));
        addDialog.setResizable(false);
        addDialog.getContentPane().setLayout(new javax.swing.BoxLayout(addDialog.getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel9.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Course title");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        titleInp.setMinimumSize(new java.awt.Dimension(100, 30));
        titleInp.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 80);
        jPanel2.add(titleInp, gridBagConstraints);

        jLabel6.setText("Course credits");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 8, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        creditsInp.setMinimumSize(new java.awt.Dimension(100, 30));
        creditsInp.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 80);
        jPanel2.add(creditsInp, gridBagConstraints);

        jLabel7.setText("Department ID");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 8, 0, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        departmentInp.setMinimumSize(new java.awt.Dimension(100, 30));
        departmentInp.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 80);
        jPanel2.add(departmentInp, gridBagConstraints);

        jLabel9.setText("Course type");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 8, 0, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        courseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Online", "Onsite" }));
        courseType.setMinimumSize(new java.awt.Dimension(100, 30));
        courseType.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 60, 80);
        jPanel2.add(courseType, gridBagConstraints);

        jPanel9.add(jPanel2);

        typeForm.setPreferredSize(new java.awt.Dimension(300, 50));
        typeForm.setLayout(new java.awt.GridLayout(1, 0));
        jPanel9.add(typeForm);

        addDialog.getContentPane().add(jPanel9);

        jPanel5.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        cancelBtn.setText("Cancel");
        jPanel5.add(cancelBtn);

        addBtn.setText("Add");
        jPanel5.add(addBtn);

        addDialog.getContentPane().add(jPanel5);

        onlineType.setMinimumSize(new java.awt.Dimension(300, 300));
        onlineType.setPreferredSize(new java.awt.Dimension(300, 300));
        onlineType.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Course url");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        onlineType.add(jLabel2, gridBagConstraints);

        urlInp.setMinimumSize(new java.awt.Dimension(200, 50));
        urlInp.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onlineType.add(urlInp, gridBagConstraints);

        addDialog.getContentPane().add(onlineType);

        onsiteType.setMinimumSize(new java.awt.Dimension(300, 300));
        onsiteType.setPreferredSize(new java.awt.Dimension(300, 300));
        onsiteType.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Location");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        onsiteType.add(jLabel3, gridBagConstraints);

        locationInp.setMinimumSize(new java.awt.Dimension(200, 50));
        locationInp.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onsiteType.add(locationInp, gridBagConstraints);

        jLabel4.setText("Days");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        onsiteType.add(jLabel4, gridBagConstraints);

        daysInp.setMinimumSize(new java.awt.Dimension(200, 50));
        daysInp.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onsiteType.add(daysInp, gridBagConstraints);

        jLabel8.setText("Time");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        onsiteType.add(jLabel8, gridBagConstraints);

        timeInp.setMinimumSize(new java.awt.Dimension(200, 50));
        timeInp.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onsiteType.add(timeInp, gridBagConstraints);

        addDialog.getContentPane().add(onsiteType);

        changeDialog.setTitle("addCourse");
        changeDialog.setMinimumSize(new java.awt.Dimension(600, 400));
        changeDialog.setResizable(false);
        changeDialog.getContentPane().setLayout(new javax.swing.BoxLayout(changeDialog.getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel10.setLayout(new java.awt.GridLayout());

        jPanel11.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("Course title");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 0, 0);
        jPanel11.add(jLabel10, gridBagConstraints);

        titleInp1.setMinimumSize(new java.awt.Dimension(100, 30));
        titleInp1.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 80);
        jPanel11.add(titleInp1, gridBagConstraints);

        jLabel11.setText("Course credits");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 8, 0, 0);
        jPanel11.add(jLabel11, gridBagConstraints);

        creditsInp1.setMinimumSize(new java.awt.Dimension(100, 30));
        creditsInp1.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 80);
        jPanel11.add(creditsInp1, gridBagConstraints);

        jLabel12.setText("Department ID");
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 8, 0, 0);
        jPanel11.add(jLabel12, gridBagConstraints);

        departmentInp1.setMinimumSize(new java.awt.Dimension(100, 30));
        departmentInp1.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 80);
        jPanel11.add(departmentInp1, gridBagConstraints);

        jLabel13.setText("Course type");
        jLabel13.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 8, 0, 0);
        jPanel11.add(jLabel13, gridBagConstraints);

        courseType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Online", "Onsite" }));
        courseType1.setMinimumSize(new java.awt.Dimension(100, 30));
        courseType1.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 60, 80);
        jPanel11.add(courseType1, gridBagConstraints);

        jPanel10.add(jPanel11);

        typeForm1.setPreferredSize(new java.awt.Dimension(300, 50));
        typeForm1.setLayout(new java.awt.GridLayout());
        jPanel10.add(typeForm1);

        changeDialog.getContentPane().add(jPanel10);

        jPanel12.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel12.setLayout(new java.awt.GridLayout());

        cancelBtn1.setText("Cancel");
        jPanel12.add(cancelBtn1);

        addBtn1.setText("Change");
        jPanel12.add(addBtn1);

        changeDialog.getContentPane().add(jPanel12);

        onlineType1.setMinimumSize(new java.awt.Dimension(300, 300));
        onlineType1.setPreferredSize(new java.awt.Dimension(300, 300));
        onlineType1.setLayout(new java.awt.GridBagLayout());

        jLabel14.setText("Course url");
        jLabel14.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        onlineType1.add(jLabel14, gridBagConstraints);

        urlInp1.setMinimumSize(new java.awt.Dimension(200, 50));
        urlInp1.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onlineType1.add(urlInp1, gridBagConstraints);

        changeDialog.getContentPane().add(onlineType1);

        onsiteType1.setMinimumSize(new java.awt.Dimension(300, 300));
        onsiteType1.setPreferredSize(new java.awt.Dimension(300, 300));
        onsiteType1.setLayout(new java.awt.GridBagLayout());

        jLabel15.setText("Location");
        jLabel15.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        onsiteType1.add(jLabel15, gridBagConstraints);

        locationInp1.setMinimumSize(new java.awt.Dimension(200, 50));
        locationInp1.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onsiteType1.add(locationInp1, gridBagConstraints);

        jLabel16.setText("Days");
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        onsiteType1.add(jLabel16, gridBagConstraints);

        daysInp1.setMinimumSize(new java.awt.Dimension(200, 50));
        daysInp1.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onsiteType1.add(daysInp1, gridBagConstraints);

        jLabel17.setText("Time");
        jLabel17.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        onsiteType1.add(jLabel17, gridBagConstraints);

        timeInp1.setMinimumSize(new java.awt.Dimension(200, 50));
        timeInp1.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        onsiteType1.add(timeInp1, gridBagConstraints);

        changeDialog.getContentPane().add(onsiteType1);

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
    private javax.swing.JButton addBtn1;
    private javax.swing.JButton addButton;
    private javax.swing.JDialog addDialog;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cancelBtn1;
    private javax.swing.JDialog changeDialog;
    private javax.swing.JTable courseContainer;
    private javax.swing.JComboBox<String> courseType;
    private javax.swing.JComboBox<String> courseType1;
    private javax.swing.JTextField creditsInp;
    private javax.swing.JTextField creditsInp1;
    private javax.swing.JTextField daysInp;
    private javax.swing.JTextField daysInp1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> departmentInp;
    private javax.swing.JComboBox<String> departmentInp1;
    private javax.swing.JPanel detailContainer;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField locationInp;
    private javax.swing.JTextField locationInp1;
    private javax.swing.JPanel onlineType;
    private javax.swing.JPanel onlineType1;
    private javax.swing.JPanel onsiteType;
    private javax.swing.JPanel onsiteType1;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTextField timeInp;
    private javax.swing.JTextField timeInp1;
    private javax.swing.JTextField titleInp;
    private javax.swing.JTextField titleInp1;
    private javax.swing.JPanel typeForm;
    private javax.swing.JPanel typeForm1;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField urlInp;
    private javax.swing.JTextField urlInp1;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
