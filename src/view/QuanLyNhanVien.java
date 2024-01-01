/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.NhanVienSV;
import model.NhanVien;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class QuanLyNhanVien extends javax.swing.JFrame {

    NhanVienSV nvser = new NhanVienSV();
    String[] colums = {
        //maNv, tenNv, chucVu,matKhau, luong, namSinh, sdtNv, gioiTinh, ghiChu
        "Mã NV", "Tên NV", "Chức vụ", "Mật khẩu", "Lương", "Năm sinh", "Số ĐT", "Giới Tính", "Ghi chú"
    };
    DefaultTableModel model = new DefaultTableModel(colums, 0);

    /**
     * Creates new form QuanLyNhanVien
     */
    public QuanLyNhanVien() {
        initComponents();
        tablenv.setModel(model);
        this.setLocationRelativeTo(null);
        btnluu.setEnabled(false);
        btnxoa.setEnabled(false);
        btnsua.setEnabled(false);
        rdnam.setSelected(true);

        filltable(nvser.getAllKh());
    }
    private int index = -1;

    void filltable(List<NhanVien> list) {
        model.setRowCount(0);
        for (NhanVien nv : list) {
            model.addRow(nv.torow());
        }
        index = -1;
    }

    void resetform() {
        txtma.setText("");
        txtten.setText("");
        txtchucvu.setText("");
        txtmk.setText("");
        txtluong.setText("");
        txtnamsinh.setText("");
        txtsdt.setText("");
        rdnam.setSelected(true);
        txtghichu.setText("");
    }

    void loadform(int inde) {
        //maNv, tenNv, chucVu,matKhau, luong, namSinh, sdtNv, gioiTinh, ghiChu
        txtma.setText(tablenv.getValueAt(inde, 0).toString());
        txtten.setText(tablenv.getValueAt(inde, 1).toString());
        txtchucvu.setText(tablenv.getValueAt(inde, 2).toString());
        txtmk.setText(tablenv.getValueAt(inde, 3).toString());
        txtluong.setText(tablenv.getValueAt(inde, 4).toString());
        txtnamsinh.setText(tablenv.getValueAt(inde, 5).toString());
        txtsdt.setText(tablenv.getValueAt(inde, 6).toString());
        switch (tablenv.getValueAt(inde, 7).toString()) {
            case "Nam" ->
                rdnam.setSelected(true);
            case "Nữ" ->
                rdnu.setSelected(true);
            case "Không xác Định" ->
                rdkxd.setSelected(true);
            default ->
                throw new AssertionError();
        }
        txtghichu.setText(tablenv.getValueAt(inde, 8).toString());
    }

    void loadform1(NhanVien nv) {
        //maNv, tenNv, chucVu,matKhau, luong, namSinh, sdtNv, gioiTinh, ghiChu
        txtma.setText(nv.getMaNv());
        txtten.setText(nv.getTenNv());
        txtchucvu.setText(nv.getChucVu());
        txtmk.setText(nv.getMatKhau());
        txtluong.setText(String.valueOf(nv.getLuong()));
        txtnamsinh.setText(nv.getNamSinh());
        txtsdt.setText(nv.getSdt());
        switch (nv.getGioiTinh()) {
            case 1 ->
                rdnam.setSelected(true);
            case 0 ->
                rdnu.setSelected(true);
            case 2 ->
                rdkxd.setSelected(true);
            default ->
                throw new AssertionError();
        }
        txtghichu.setText(nv.getGhiChu());
    }
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");

    NhanVien getForm() {
        String ma = txtma.getText();
        if (ma.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String ten = txtten.getText();
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String chucvu = txtchucvu.getText();
        if (chucvu.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        String mk = txtmk.getText();
        if (mk.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        int luong = -1;
        try {
            luong = Integer.parseInt(txtluong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập vòa 1 số !", "Sai kiểu đư liệu !", 0);
            return null;
        }
        if (luong == -1) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tuổi Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }

        String namsinh = txtnamsinh.getText();
        if (namsinh.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập năm Sinh Kìa !", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        } else {
            try {
                dateFormat.parse(txtnamsinh.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Ngày vao không hợp lệ. Nhập theo định dạng yyyy-dd-MM");
                return null;
            }
        }

        String sdt = txtsdt.getText();
        
        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Số Đt!", "Thông Báo Thiếu Dữ Liệu !", 3);
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

        return new NhanVien(ma, ten, chucvu, mk, luong, namsinh, sdt, gt, chucvu);

    }

    void loadtrue() {
        rdkxd.setEnabled(true);
        rdnam.setEnabled(true);
        rdnu.setEnabled(true);
        txtghichu.setEnabled(true);
        txtma.setEnabled(true);
        txtten.setEnabled(true);
        txtchucvu.setEnabled(true);
        txtmk.setEnabled(true);
        txtluong.setEnabled(true);
        txtnamsinh.setEnabled(true);
        txtsdt.setEnabled(true);
    }

    void loadfalse() {
        rdkxd.setEnabled(false);
        rdnam.setEnabled(false);
        rdnu.setEnabled(false);
        txtghichu.setEnabled(false);
        txtma.setEnabled(false);
        txtten.setEnabled(false);
        txtchucvu.setEnabled(false);
        txtmk.setEnabled(false);
        txtluong.setEnabled(false);
        txtnamsinh.setEnabled(false);
        txtsdt.setEnabled(false);
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        txtchucvu = new javax.swing.JTextField();
        txtmk = new javax.swing.JTextField();
        txtluong = new javax.swing.JTextField();
        txtnamsinh = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        rdnam = new javax.swing.JRadioButton();
        rdnu = new javax.swing.JRadioButton();
        rdkxd = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtghichu = new javax.swing.JTextArea();
        btnnew = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btntimkiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablenv = new javax.swing.JTable();
        btnthoat = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Nhân Viên");

        jLabel2.setText("Mã NV:");

        jLabel3.setText("Tên NV:");

        jLabel4.setText("Chức vụ :");

        jLabel5.setText("Mật khẩu:");

        jLabel6.setText("Lương:");

        jLabel7.setText("Năm Sinh:");

        jLabel8.setText("Số điện thoại:");

        jLabel9.setText("Giới tính:");

        jLabel10.setText("Ghi chu:");

        buttonGroup1.add(rdnam);
        rdnam.setText("Nam");

        buttonGroup1.add(rdnu);
        rdnu.setText("Nữ");

        buttonGroup1.add(rdkxd);
        rdkxd.setText("KXD");

        txtghichu.setColumns(20);
        txtghichu.setRows(5);
        jScrollPane1.setViewportView(txtghichu);

        btnnew.setText("Reset");
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

        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnluu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save.png"))); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btntimkiem.setText("Tìm Kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        tablenv.setModel(new javax.swing.table.DefaultTableModel(
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
        tablenv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablenvMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablenv);

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnluu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxoa)
                        .addGap(6, 6, 6)
                        .addComponent(btntimkiem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtnamsinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txtten, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtma, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmk)
                                    .addComponent(txtchucvu)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtluong))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdnu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdkxd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(rdnam)
                            .addComponent(rdnu)
                            .addComponent(rdkxd)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnxoa)
                        .addComponent(btnluu)
                        .addComponent(btntimkiem)
                        .addComponent(btnsua)
                        .addComponent(btnadd)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnthoat))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablenvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablenvMouseClicked
        index = tablenv.getSelectedRow();
        if (index >= 0) {
            loadform(index);
            btnsua.setEnabled(true);
            btnxoa.setEnabled(true);

            loadfalse();
        }
    }//GEN-LAST:event_tablenvMouseClicked

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        btnluu.setEnabled(false);
        btnxoa.setEnabled(false);
        btnsua.setEnabled(false);
        txtma.setEnabled(true);
        btnadd.setEnabled(true);
        resetform();
        loadtrue();

    }//GEN-LAST:event_btnnewActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        NhanVien nv = getForm();

        if (nv != null) {

            List<NhanVien> lit = nvser.kiemtranv(nv.getMaNv());
            if (lit != null && !lit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã đã được sử dụng vui lòng chọn mã khác !", "Trùng lặp dữ liệu", 2);
            } else {
                int te = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm NV không !", "Hỏi", JOptionPane.YES_NO_CANCEL_OPTION, 3, null);
                if (te == 0) {
                    int x = nvser.insertNV(nv);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "Thêm Thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm Thành công !");
                        filltable(nvser.getAllKh());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " Đã hủy thêm !");
                }

            }

        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất bại !");
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        index = tablenv.getSelectedRow();
        if (index >= 0) {
            String ma = tablenv.getValueAt(index, 0).toString();
            int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa Nhân Viên này\n và toàn bộ Hóa đơn Mà NV này đang phụ trách không!");
            if (h == 0) {
                int te = nvser.deleteHD(ma);
                int x = nvser.deleteNV(ma);
                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "Xoa Thất bại !");
                } else {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công Khách hàng này và " + te + " hóa đơn của vị khách này !");
                    filltable(nvser.getAllKh());
                    loadtrue();
                    btnluu.setEnabled(false);
                    btnxoa.setEnabled(false);
                    btnsua.setEnabled(false);
                    resetform();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn Dòng muốn xoa");
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        index = tablenv.getSelectedRow();
        NhanVien kh = getForm();
        if (kh != null) {
            int te = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn Lưu thông tin đã sửa của NV không !", "Hỏi", JOptionPane.YES_NO_CANCEL_OPTION, 3, null);
            if (te == 0) {
                if (index >= 0) {
                    String ma = tablenv.getValueAt(index, 0).toString();
                    int x = nvser.updateNV(ma, kh);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "Sửa Thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa Thành công !");
                        btnluu.setEnabled(false);
                        btnxoa.setEnabled(false);
                        btnsua.setEnabled(false);
                        txtma.setEnabled(true);
                        resetform();
                        filltable(nvser.getAllKh());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chưa chọn Dòng muốn sửa !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy sửa !");
            }

        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        String ma = JOptionPane.showInputDialog(this, "Nhập mã cần tìm ", "Tìm kiếm theo mã KH .", 3);
        if (ma != null && ma.length() > 0) {
            List<NhanVien> list = nvser.kiemtranv(ma);
            filltable(list);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy NV ");
            } else {
                loadform1(list.get(0));
            }
        }

    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không ?", "Thoát !", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (h == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnthoatActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        btnluu.setEnabled(true);
        txtghichu.setEnabled(true);

        txtten.setEnabled(true);
        txtchucvu.setEnabled(true);
        txtmk.setEnabled(true);
        txtluong.setEnabled(true);
        txtnamsinh.setEnabled(true);
        txtsdt.setEnabled(true);
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
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdkxd;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JRadioButton rdnu;
    private javax.swing.JTable tablenv;
    private javax.swing.JTextField txtchucvu;
    private javax.swing.JTextArea txtghichu;
    private javax.swing.JTextField txtluong;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmk;
    private javax.swing.JTextField txtnamsinh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
