/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import service.*;

/**
 *
 * @author Admin
 */
public class QuanLyKhachHang extends javax.swing.JFrame {

    KhachHangSV khser = new KhachHangSV();
    HoaDonSV hdser = new HoaDonSV();
    String[] colums = {
        //makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi
        "Mã KH", "Tên KH", "Mật khẩu", "Số ĐT", "CMND", "Giới tính", "Tuổi"
    };
    DefaultTableModel model = new DefaultTableModel(colums, 0);
    /**
     * Creates new form QuanLyKhachHang
     */
    private int index = -1;

    public QuanLyKhachHang() {
        initComponents();
        tablekh.setModel(model);
        rdnam.setSelected(true);
        btnluu.setEnabled(false);
        btnsua.setEnabled(false);
        btndelete.setEnabled(false);
        this.setLocationRelativeTo(null);
        filltable(khser.getAllKh());
    }

    void filltable(List<KhachHang> list) {
        model.setRowCount(0);
        for (KhachHang kh : list) {
            model.addRow(kh.torow());
        }
        index = -1;
    }

    KhachHang getForm() {
        String ma = txtma.getText();
        if (ma.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String ten = txtten.getText();
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tên Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String mk = txtmk.getText();
        if (mk.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa đặt Mật khẩu Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String sdt = txtsdt.getText();
        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập SĐT Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String cmnd = txtcmnd.getText();
        if (cmnd.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Số CMND Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        int tuoi = -1;
        try {
            tuoi = Integer.parseInt(txttuoi.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập vòa 1 số !", "Sai kiểu đư liệu !", 0);
            return null;
        }
        if (tuoi == -1) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tuổi Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        } else if (tuoi < 18) {
            JOptionPane.showMessageDialog(this, "Tuổi phải Lớn hơn hoặc bàng 18 !", "Thông Báo Không Đủ tuổi!", 1);
            return null;
        }
        int gt;
        if (rdnam.isSelected()) {
            gt = 1;
        } else if (rdnu.isSelected()) {
            gt = 0;
        } else {
            gt = 2;
        }
        return new KhachHang(ma, ten, mk, sdt, cmnd, gt, tuoi);

    }

    void loadtrue() {
        rdkxd.setEnabled(true);
        rdnam.setEnabled(true);
        rdnu.setEnabled(true);
        txtma.setEnabled(true);
        txtten.setEnabled(true);
        txtmk.setEnabled(true);
        txtsdt.setEnabled(true);
        txtcmnd.setEnabled(true);
        txttuoi.setEnabled(true);
    }

    void loadfalse() {
        rdkxd.setEnabled(false);
        rdnam.setEnabled(false);
        rdnu.setEnabled(false);
        txtma.setEnabled(false);
        txtten.setEnabled(false);
        txtmk.setEnabled(false);
        txtsdt.setEnabled(false);
        txtcmnd.setEnabled(false);
        txttuoi.setEnabled(false);
    }

    void loadform(int inde) {
        //makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi
        txtma.setText(tablekh.getValueAt(inde, 0).toString());
        txtten.setText(tablekh.getValueAt(inde, 1).toString());
        txtmk.setText(tablekh.getValueAt(inde, 2).toString());
        txtsdt.setText(tablekh.getValueAt(inde, 3).toString());
        txtcmnd.setText(tablekh.getValueAt(inde, 4).toString());
        txttuoi.setText(tablekh.getValueAt(inde, 6).toString());
        switch (tablekh.getValueAt(inde, 5).toString()) {
            case "Nam" ->
                rdnam.setSelected(true);
            case "Nữ" ->
                rdnu.setSelected(true);
            case "Không xác Định" ->
                rdkxd.setSelected(true);
            default ->
                throw new AssertionError();
        }

    }

    void loadform1(KhachHang kh) {
        //makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi
        txtma.setText(kh.getMaKh());
        txtten.setText(kh.getTenKh());
        txtmk.setText(kh.getMaKh());
        txtsdt.setText(kh.getSdt());
        txtcmnd.setText(kh.getCmnd());
        txttuoi.setText(String.valueOf(kh.getTuoi()));

        switch (kh.getGioiTinh()) {
            case 1 ->
                rdnam.setSelected(true);
            case 0 ->
                rdnu.setSelected(true);
            case 2 ->
                rdkxd.setSelected(true);
            default ->
                throw new AssertionError();
        }

    }

    void resetForm() {
        txtma.setText("");
        txtten.setText("");
        txtmk.setText("");
        txtsdt.setText("");
        txtcmnd.setText("");
        txttuoi.setText("");
        rdnam.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        txtmk = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        txtcmnd = new javax.swing.JTextField();
        rdnam = new javax.swing.JRadioButton();
        rdnu = new javax.swing.JRadioButton();
        rdkxd = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txttuoi = new javax.swing.JTextField();
        btnnew = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btntimkiem = new javax.swing.JButton();
        btnthoat = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablekh = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Khách Hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Mã khách hàng:");

        jLabel3.setText("Tên khách hàng:");

        jLabel4.setText("Mật khẩu:");

        jLabel5.setText("Số DT :");

        jLabel6.setText("CMND :");

        jLabel7.setText("Giới tính :");

        buttonGroup1.add(rdnam);
        rdnam.setText("Nam");

        buttonGroup1.add(rdnu);
        rdnu.setText("Nữ");

        buttonGroup1.add(rdkxd);
        rdkxd.setText("KXD");

        jLabel8.setText("Tuổi:");

        btnnew.setText("New");
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btnadd.setText("Thêm");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnluu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save as.png"))); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btndelete.setText("Xóa");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btntimkiem.setText("Tìm kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        btnthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Log out.png"))); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdnu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdkxd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btntimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnthoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnluu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnadd))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnsua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnluu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btntimkiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnthoat)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdnu)
                            .addComponent(rdnam)
                            .addComponent(rdkxd)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );

        tablekh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablekh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablekh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablekh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablekhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekhMouseClicked
        index = tablekh.getSelectedRow();
        if (index >= 0) {
            loadform(index);
            btnsua.setEnabled(true);
            btndelete.setEnabled(true);

            loadfalse();
        }
    }//GEN-LAST:event_tablekhMouseClicked

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        String ma = JOptionPane.showInputDialog(this, "Nhập mã cần tìm ", "Tìm kiếm theo mã KH .", 3);
        if (ma != null && ma.length() > 0) {
            List<KhachHang> list = khser.kiemtrakh(ma);
            filltable(list);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy KH ");
            } else {
                loadform1(list.get(0));
            }
        }

    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        index = tablekh.getSelectedRow();
        if (index >= 0) {
            String ma = tablekh.getValueAt(index, 0).toString();
            int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa khách hàng này\n và toàn bộ Hóa đơn của Vị khách này hay không!");
            if (h == 0) {
                int te = khser.deletehd(ma);
                int x = khser.deleteKh(ma);
                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "Xoa Thất bại !");
                } else {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công Khách hàng này và " + te + " hóa đơn của vị khách này !");
                    filltable(khser.getAllKh());
                    loadtrue();
                    btnluu.setEnabled(false);
                    btnsua.setEnabled(false);
                    btndelete.setEnabled(false);
                    resetForm();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn Dòng muốn xoa");
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        index = tablekh.getSelectedRow();
        KhachHang kh = getForm();
        if (kh != null) {
            if (index >= 0) {
                int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn Lưu thông tin đã sửa Nhân Viên không!");
                if (h == 0) {
                    String ma = tablekh.getValueAt(index, 0).toString();
                    int x = khser.updateSV(ma, kh);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "Lưu Thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Đã lưu thay đổi !");
                        filltable(khser.getAllKh());
                        btnluu.setEnabled(false);
                        btnsua.setEnabled(false);
                        btndelete.setEnabled(false);
                        txtma.setEnabled(true);
                        resetForm();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Đã hủy sửa !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn Dòng muốn sửa !");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Lưu Thất bại !");
        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        KhachHang kh = getForm();
        if (kh != null) {

            List<KhachHang> lit = khser.kiemtrakh(kh.getMaKh());
            if (lit != null && !lit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã đã được sử dụng vui lòng chọn mã khác !", "Trùng lặp dữ liệu", 2);
            } else {
                int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm Nhân Viên không!");
                if (h == 0) {
                    int x = khser.insertKH(kh);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "Thêm Thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm Thành công !");
                        filltable(khser.getAllKh());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " Đã hủy thêm !");
                }

            }

        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất bại !");
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        resetForm();
        txtma.setEnabled(true);
        btnluu.setEnabled(false);
        btnsua.setEnabled(false);
        btndelete.setEnabled(false);
        rdkxd.setEnabled(true);
        rdnam.setEnabled(true);
        rdnu.setEnabled(true);
        txtten.setEnabled(true);
        txtmk.setEnabled(true);
        txtsdt.setEnabled(true);
        txtcmnd.setEnabled(true);
        txttuoi.setEnabled(true);
    }//GEN-LAST:event_btnnewActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không ?", "Thoát !", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (h == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnthoatActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        loadtrue();
        txtma.setEnabled(false);
        btnluu.setEnabled(true);
    }//GEN-LAST:event_btnsuaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btntimkiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdkxd;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JRadioButton rdnu;
    private javax.swing.JTable tablekh;
    private javax.swing.JTextField txtcmnd;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmk;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txttuoi;
    // End of variables declaration//GEN-END:variables
}
