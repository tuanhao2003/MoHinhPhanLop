package com.example.GUI;

import com.example.BLL.thanhVienBLL;
import com.example.DAL.thanhVien;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class thanhVienGUI extends javax.swing.JPanel {

    private thanhVienBLL thanhVienBLL;
    private ArrayList<thanhVien> listThanhVien;
    private int selectingID = 0;

    public thanhVienGUI() {
        this.thanhVienBLL = new thanhVienBLL();
        this.listThanhVien = new ArrayList<thanhVien>();
        initComponents();
        eventHandler();
    }

    private void eventHandler() {
        this.listThanhVien = this.thanhVienBLL.getMembers();
        this.memberTable.getTableHeader().setReorderingAllowed(false);
        renderTable();

        this.memberTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (memberTable.rowAtPoint(e.getPoint()) >= 0) {
                    selectingID = Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString());
                    memIdBox.setText(Integer.toString(selectingID));
                    memNameBox.setText(thanhVienBLL.getMember(selectingID).getHoten());
                    memMajorBox.setText(thanhVienBLL.getMember(selectingID).getKhoa());
                    memSubMajorBox.setText(thanhVienBLL.getMember(selectingID).getNganh());
                    memPhoneBox.setText(thanhVienBLL.getMember(selectingID).getSdt());
                    memEmailBox.setText(thanhVienBLL.getMember(selectingID).getEmail());
                    memPasswordBox.setText(thanhVienBLL.getMember(selectingID).getPassword());
                    
                    if (memberTable.columnAtPoint(e.getPoint()) == 6) {
                        int status = thanhVienBLL.checkIn(Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString()));
                        if (status == -1) {
                            JOptionPane.showMessageDialog(jScrollPane1, "Check in success");
                        }else if(status == 0){
                            JOptionPane.showMessageDialog(jScrollPane1, "Caution: offender alert");
                        }else if(status == 1){
                            JOptionPane.showMessageDialog(jScrollPane1, "Check in fail: member got banned!");
                        }
                    }
               
                    if (memberTable.columnAtPoint(e.getPoint()) == 7) {
                        if(thanhVienBLL.checkOffender(Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString())) == -1
                        || thanhVienBLL.checkOffender(Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString()))== 0){
                            if(thanhVienBLL.checkOffender(Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString())) == 0){
                                JOptionPane.showMessageDialog(jScrollPane1, "Caution: offender alert");
                            }
                            if(thanhVienBLL.isCheckInToday(Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString())) == false){
                                JOptionPane.showMessageDialog(jScrollPane1, "Please check in before borrow!");
                            }else{
                                chooseDevice.setVisible(true);
                                chooseDevice.setLocationRelativeTo(null);
                                deviceLenContainer.removeAll();
                                ArrayList<Integer> borrowList = new ArrayList<Integer>();
                                
                                thanhVienBLL.getDevicesForLen().forEach(devi -> {
                                    JPanel panel = new JPanel();
                                    JCheckBox cb = new JCheckBox();
                                    cb.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent cbe) {
                                            if (cb.isSelected()) {
                                                borrowList.add(devi.getMatb());
                                            } else {
                                                borrowList.remove(devi.getMatb());
                                            }
                                        }
                                    });
                                    panel.setSize(500, 50);
                                    panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
                                    panel.add(cb);
                                    panel.add(new JLabel(Integer.toString(devi.getMatb()) + " "));
                                    panel.add(new JLabel(devi.getTentb() + " "));
                                    panel.add(new JLabel(devi.getMotatb()));
                                    deviceLenContainer.add(panel);
                                });
                                reload(deviceLenContainer);

                                dialogLenBtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        for (int i : borrowList) {
                                            thanhVienBLL.lenDevice(selectingID, i);
                                        }
                                        chooseDevice.dispose();
                                    }
                                });

                                dialogClearBtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        borrowList.clear();
                                        deviceLenContainer.removeAll();
                                        thanhVienBLL.getDevicesForLen().forEach(devi -> {
                                        JPanel panel = new JPanel();
                                        JCheckBox cb = new JCheckBox();
                                        cb.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent cbe) {
                                                if (cb.isSelected()) {
                                                    borrowList.add(devi.getMatb());
                                                } else {
                                                    borrowList.remove(devi.getMatb());
                                                }
                                            }
                                        });
                                        panel.setSize(500, 50);
                                        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
                                        panel.add(cb);
                                        panel.add(new JLabel(Integer.toString(devi.getMatb()) + " "));
                                        panel.add(new JLabel(devi.getTentb() + " "));
                                        panel.add(new JLabel(devi.getMotatb()));
                                        deviceLenContainer.add(panel);
                                    });
                                    reload(deviceLenContainer);
                                    }
                                });
                            }
                        }
                    }
                   
                   if (memberTable.columnAtPoint(e.getPoint()) == 8) {
                       borrowingDevices.setVisible(true);
                       borrowingDevices.setLocationRelativeTo(null);
                       borrowingContainer.removeAll();
                       ArrayList<Integer> backIntend = new ArrayList<Integer>();
                       thanhVienBLL.getDevicesBorrowing(Integer.parseInt(memberTable.getValueAt(memberTable.rowAtPoint(e.getPoint()), 0).toString())).forEach(devi -> {
                           JPanel panel = new JPanel();
                           JCheckBox cb = new JCheckBox();
                           cb.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent cbe) {
                                   if (cb.isSelected()) {
                                       backIntend.add(devi.getMatb());
                                   } else {
                                       backIntend.remove(devi.getMatb());
                                   }
                               }
                           });
                           panel.setSize(500, 50);
                           panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
                           panel.add(cb);
                           panel.add(new JLabel(Integer.toString(devi.getMatb()) + " "));
                           panel.add(new JLabel(devi.getTentb() + " "));
                           panel.add(new JLabel(devi.getMotatb()));
                           borrowingContainer.add(panel);
                       });
                       reload(borrowingContainer);

                       backSelected.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               for (int i : backIntend) {
                                   thanhVienBLL.backDevice(selectingID, i);
                               }
                               chooseDevice.dispose();
                           }
                       });
                   }
                }
            }
        });

// button add
        this.addMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memIdBox.getText() != null && memNameBox.getText() != null && memMajorBox.getText() != null && memSubMajorBox.getText() != null && memPhoneBox.getText() != null && memEmailBox.getText() != null && jlabelpass.getText() != null) {
                    try {
                        int id = Integer.parseInt(memIdBox.getText());
                        String fullname = memNameBox.getText();
                        String major = memMajorBox.getText();
                        String submajor = memSubMajorBox.getText();
                        String phone = memPhoneBox.getText();
                        String email = memEmailBox.getText();
                        String pass = jlabelpass.getText();
                        boolean success = thanhVienBLL.addMember(id, fullname, major, submajor, phone, email, pass);
                        if (success) {
                            listThanhVien = thanhVienBLL.getMembers();
                            renderTable();
                        }else{
                            JOptionPane.showMessageDialog(jScrollPane1, "Member already stored in system");
                        }
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(jScrollPane1, "id must be integer");
                    }
                } else {
                    JOptionPane.showMessageDialog(jScrollPane1, "please full fill ther boxes");
                }
            }
        });

//button update
        this.updateMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memIdBox.getText() != null) {
                    if (thanhVienBLL.getMember(Integer.parseInt(memIdBox.getText())) != null) {
                        boolean success = thanhVienBLL.updateMember(Integer.parseInt(memIdBox.getText()), memNameBox.getText(), memMajorBox.getText(), memSubMajorBox.getText(), memPhoneBox.getText(), memEmailBox.getText(), memPasswordBox.getText());
                        if (success) {
                            listThanhVien = thanhVienBLL.getMembers();
                            renderTable();
                        }else{
                            JOptionPane.showMessageDialog(jScrollPane1, "Some error when updating member informations");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(jScrollPane1, "every thing are up to date");
                }
            }
        });

// button delete
        this.delMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectingID > 0 && thanhVienBLL.getMember(selectingID) != null) {
                    boolean success = thanhVienBLL.deleteMember(selectingID);
                    if (success) {
                        listThanhVien = thanhVienBLL.getMembers();
                        renderTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(jScrollPane1, "Error while finding member");
                }
            }
        });

// button import    
        this.importMemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickFile.showOpenDialog(jScrollPane1);
                boolean success = thanhVienBLL.addMembersViaExcel(pickFile.getSelectedFile().getAbsolutePath());
                if (success) {
                    listThanhVien = thanhVienBLL.getMembers();
                    renderTable();
                }
            }
        });
// button del all
        this.delMultiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = JOptionPane.showInputDialog(jScrollPane1, "Input the course to delete");
                try {
                    if(Integer.parseInt(course) > 99 || Integer.parseInt(course)<10){
                        JOptionPane.showMessageDialog(jScrollPane1, "Invalid course");
                    }else{
                        boolean success = thanhVienBLL.deleteMembers(Integer.parseInt(course));
                        if(success){
                            listThanhVien = thanhVienBLL.getMembers();
                            renderTable();
                            JOptionPane.showMessageDialog(jScrollPane1, "Delete successfully");
                        }else {
                            JOptionPane.showMessageDialog(jScrollPane1, "Some error when deleting");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jScrollPane1, "Course must be integer");
                }
            }
        });
    }

    private void renderTable() {
        DefaultTableModel model = (DefaultTableModel) this.memberTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < this.listThanhVien.size(); i++) {
            String memid = Integer.toString(this.listThanhVien.get(i).getMatv());
            String memName = this.listThanhVien.get(i).getHoten();
            String memMajor = this.listThanhVien.get(i).getKhoa();
            String memSubmajor = this.listThanhVien.get(i).getNganh();
            String memPhone = this.listThanhVien.get(i).getSdt();
            String memMail = this.listThanhVien.get(i).getEmail();
            Object[] data = {memid, memName, memMajor, memSubmajor, memPhone, memMail};
            model.addRow(data);
            this.memberTable.updateUI();
        }
    }

    private void reload(JPanel item) {
        item.repaint();
        item.revalidate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pickFile = new javax.swing.JFileChooser();
        chooseDevice = new javax.swing.JDialog();
        chooseSkeleton = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        deviceLenContainer = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        dialogClearBtn = new javax.swing.JButton();
        dialogLenBtn = new javax.swing.JButton();
        borrowingDevices = new javax.swing.JDialog();
        borrowingSkeleton = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        borrowingContainer = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        backAll = new javax.swing.JButton();
        backSelected = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        memIdBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        memMajorBox = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        memNameBox = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        memSubMajorBox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        memPhoneBox = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        memEmailBox = new javax.swing.JTextField();
        jlabelpass = new javax.swing.JLabel();
        memPasswordBox = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        addMemBtn = new javax.swing.JButton();
        updateMemBtn = new javax.swing.JButton();
        delMemBtn = new javax.swing.JButton();
        importMemBtn = new javax.swing.JButton();
        delMultiBtn = new javax.swing.JButton();

        pickFile.setToolTipText("");

        chooseDevice.setTitle("Choose Device");
        chooseDevice.setAlwaysOnTop(true);
        chooseDevice.setLocationByPlatform(true);
        chooseDevice.setMinimumSize(new java.awt.Dimension(600, 400));
        chooseDevice.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        chooseSkeleton.setPreferredSize(new java.awt.Dimension(550, 550));
        chooseSkeleton.setLayout(new java.awt.GridBagLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(500, 300));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(500, 400));

        deviceLenContainer.setLayout(new javax.swing.BoxLayout(deviceLenContainer, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane4.setViewportView(deviceLenContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        chooseSkeleton.add(jScrollPane4, gridBagConstraints);

        jPanel11.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        dialogClearBtn.setBackground(new java.awt.Color(222, 184, 135));
        dialogClearBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        dialogClearBtn.setForeground(new java.awt.Color(51, 51, 51));
        dialogClearBtn.setText("Clear");
        dialogClearBtn.setAlignmentY(0.0F);
        dialogClearBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dialogClearBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        dialogClearBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel11.add(dialogClearBtn);

        dialogLenBtn.setBackground(new java.awt.Color(222, 184, 135));
        dialogLenBtn.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        dialogLenBtn.setForeground(new java.awt.Color(51, 51, 51));
        dialogLenBtn.setText("Len");
        dialogLenBtn.setAlignmentY(0.0F);
        dialogLenBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dialogLenBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        dialogLenBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel11.add(dialogLenBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        chooseSkeleton.add(jPanel11, gridBagConstraints);

        chooseDevice.getContentPane().add(chooseSkeleton);

        borrowingDevices.setTitle("Give Back Device");
        borrowingDevices.setAlwaysOnTop(true);
        borrowingDevices.setLocationByPlatform(true);
        borrowingDevices.setMinimumSize(new java.awt.Dimension(600, 400));
        borrowingDevices.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        borrowingSkeleton.setPreferredSize(new java.awt.Dimension(550, 550));
        borrowingSkeleton.setLayout(new java.awt.GridBagLayout());

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setMinimumSize(new java.awt.Dimension(500, 300));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(500, 400));

        borrowingContainer.setLayout(new javax.swing.BoxLayout(borrowingContainer, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane5.setViewportView(borrowingContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        borrowingSkeleton.add(jScrollPane5, gridBagConstraints);

        jPanel12.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        backAll.setBackground(new java.awt.Color(222, 184, 135));
        backAll.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        backAll.setForeground(new java.awt.Color(51, 51, 51));
        backAll.setText("Back all");
        backAll.setAlignmentY(0.0F);
        backAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backAll.setMaximumSize(new java.awt.Dimension(200, 400));
        backAll.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel12.add(backAll);

        backSelected.setBackground(new java.awt.Color(222, 184, 135));
        backSelected.setFont(new java.awt.Font("Sitka Text", 1, 17)); // NOI18N
        backSelected.setForeground(new java.awt.Color(51, 51, 51));
        backSelected.setText("Give back");
        backSelected.setAlignmentY(0.0F);
        backSelected.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backSelected.setMaximumSize(new java.awt.Dimension(200, 400));
        backSelected.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel12.add(backSelected);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        borrowingSkeleton.add(jPanel12, gridBagConstraints);

        borrowingDevices.getContentPane().add(borrowingSkeleton);

        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(900, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(900, 400));

        memberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{}},
            new String [] {
                "ID", "Name", "Major", "Submajor", "Phone", "Email", "Check in", "Len Device", "Give back"
            }
        ));
        memberTable.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(memberTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(jScrollPane1, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(900, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 200));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(900, 150));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setPreferredSize(new java.awt.Dimension(450, 150));
        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setPreferredSize(new java.awt.Dimension(450, 75));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ID: ");
        jLabel2.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel6.add(jLabel2, new java.awt.GridBagConstraints());

        memIdBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memIdBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel6.add(memIdBox, new java.awt.GridBagConstraints());

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Major:");
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel6.add(jLabel1, new java.awt.GridBagConstraints());

        memMajorBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memMajorBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel6.add(memMajorBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel6);

        jPanel8.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fullname:");
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel8.add(jLabel3, new java.awt.GridBagConstraints());

        memNameBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memNameBox.setPreferredSize(new java.awt.Dimension(340, 40));
        jPanel8.add(memNameBox, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel8);

        jPanel5.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(450, 150));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel9.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sub Marjor:");
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel9.add(jLabel5, new java.awt.GridBagConstraints());

        memSubMajorBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memSubMajorBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel9.add(memSubMajorBox, new java.awt.GridBagConstraints());

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Phone:");
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel9.add(jLabel6, new java.awt.GridBagConstraints());

        memPhoneBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memPhoneBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel9.add(memPhoneBox, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Email:");
        jLabel7.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(jLabel7, new java.awt.GridBagConstraints());

        memEmailBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memEmailBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel10.add(memEmailBox, new java.awt.GridBagConstraints());

        jlabelpass.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jlabelpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabelpass.setText("Password:");
        jlabelpass.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(jlabelpass, new java.awt.GridBagConstraints());

        memPasswordBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        memPasswordBox.setPreferredSize(new java.awt.Dimension(125, 40));
        jPanel10.add(memPasswordBox, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel10);

        jPanel5.add(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel4.setPreferredSize(new java.awt.Dimension(900, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        addMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        addMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        addMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        addMemBtn.setText("ADD");
        addMemBtn.setAlignmentY(0.0F);
        addMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addMemBtn.setMaximumSize(new java.awt.Dimension(200, 400));
        addMemBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(addMemBtn);

        updateMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        updateMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        updateMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        updateMemBtn.setText("UPDATE");
        updateMemBtn.setAlignmentY(0.0F);
        updateMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateMemBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(updateMemBtn);

        delMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        delMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        delMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        delMemBtn.setText("DELETE");
        delMemBtn.setAlignmentY(0.0F);
        delMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delMemBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(delMemBtn);

        importMemBtn.setBackground(new java.awt.Color(222, 184, 135));
        importMemBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        importMemBtn.setForeground(new java.awt.Color(51, 51, 51));
        importMemBtn.setText("IMPORT");
        importMemBtn.setAlignmentY(0.0F);
        importMemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importMemBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(importMemBtn);

        delMultiBtn.setBackground(new java.awt.Color(222, 184, 135));
        delMultiBtn.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        delMultiBtn.setForeground(new java.awt.Color(51, 51, 51));
        delMultiBtn.setAlignmentY(0.0F);
        delMultiBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delMultiBtn.setLabel("DELS");
        delMultiBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(delMultiBtn);

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
    private javax.swing.JButton addMemBtn;
    private javax.swing.JButton backAll;
    private javax.swing.JButton backSelected;
    private javax.swing.JPanel borrowingContainer;
    private javax.swing.JDialog borrowingDevices;
    private javax.swing.JPanel borrowingSkeleton;
    private javax.swing.JDialog chooseDevice;
    private javax.swing.JPanel chooseSkeleton;
    private javax.swing.JButton delMemBtn;
    private javax.swing.JButton delMultiBtn;
    private javax.swing.JPanel deviceLenContainer;
    private javax.swing.JButton dialogClearBtn;
    private javax.swing.JButton dialogLenBtn;
    private javax.swing.JButton importMemBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jlabelpass;
    private javax.swing.JTextField memEmailBox;
    private javax.swing.JTextField memIdBox;
    private javax.swing.JTextField memMajorBox;
    private javax.swing.JTextField memNameBox;
    private javax.swing.JTextField memPasswordBox;
    private javax.swing.JTextField memPhoneBox;
    private javax.swing.JTextField memSubMajorBox;
    private javax.swing.JTable memberTable;
    private javax.swing.JFileChooser pickFile;
    private javax.swing.JButton updateMemBtn;
    // End of variables declaration//GEN-END:variables
}
