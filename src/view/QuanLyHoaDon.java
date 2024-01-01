/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import service.HoaDonSV;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Admin
 */
public class QuanLyHoaDon extends javax.swing.JFrame {
//maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien

    HoaDonSV hdser = new HoaDonSV();
    String[] colums = {
        "Mã Hóa Đơn", "Mã phòng", "Mã KH", "Mã Nv", "Đơn giá", "Số người", "Ngày vào", "Ngày ra", "Thời gian lập HĐ", "Giảm giá", "Thành tiền"
    };
    DefaultTableModel model = new DefaultTableModel(colums, 0);
//    DefaultComboBoxModel dc=new DefaultComboBoxModel();
    /**
     * Creates new form QuanLyHoaDon
     */
    public QuanLyHoaDon() {
        initComponents();
        tablehd.setModel(model);
        btnsua.setEnabled(false);
        btnluu.setEnabled(false);
        btnxoa.setEnabled(false);
        txtthanhtien.setEnabled(false);
        txtdongia.setEnabled(false);
        txtngaylap.setEnabled(false);
        filltable(hdser.getAllKh());
        this.setLocationRelativeTo(null);
//        dc=(DefaultComboBoxModel) cbmaphong.getModel();
//        dc.removeAllElements();
//        for (String mp : hdser.getAllph()) {
//            dc.addElement(mp);
//        }
    }

    void resetForm() {
        txtma.setText("");
        txtmaph.setText("");
        txtmakh.setText("");
        txtmanv.setText("");
        txtsonguoi.setText("");
        txtngayvao.setText("");
        txtngayra.setText("");
        txtgiamgia.setText("");
        txtthanhtien.setText("");
        txtngaylap.setText("");
        txtdongia.setText("?");
        txtma.setEnabled(false);
        txtmaph.setEnabled(false);
        txtmakh.setEnabled(false);
        txtmanv.setEnabled(false);
        txtsonguoi.setEnabled(false);
        txtngayvao.setEnabled(false);
        txtngayra.setEnabled(false);
        txtgiamgia.setEnabled(false);

        btnthem.setEnabled(false);
    }

    void filltable(List<HoaDon> list) {
        model.setRowCount(0);
        for (HoaDon hd : list) {
            model.addRow(hd.toRow());
        }
        index = -1;
    }
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
    private int index = -1;

    void loadform(int inde) {
        //maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien
        txtma.setText(tablehd.getValueAt(inde, 0).toString());
        txtmaph.setText(tablehd.getValueAt(inde, 1).toString());
        txtmakh.setText(tablehd.getValueAt(inde, 2).toString());
        txtmanv.setText(tablehd.getValueAt(inde, 3).toString());
        txtdongia.setText(tablehd.getValueAt(inde, 4).toString());
        txtsonguoi.setText(tablehd.getValueAt(inde, 5).toString());
        txtngayvao.setText(tablehd.getValueAt(inde, 6).toString());
        txtngayra.setText(tablehd.getValueAt(inde, 7).toString());
        txtngaylap.setText(tablehd.getValueAt(inde, 8).toString());
        txtgiamgia.setText(tablehd.getValueAt(inde, 9).toString());
        txtthanhtien.setText(tablehd.getValueAt(inde, 10).toString());
    }

    void loadform1(HoaDon hd) {
        //maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien
        txtma.setText(hd.getMaHd());
        txtmaph.setText(hd.getMaPhong());
        txtmakh.setText(hd.getMaKh());
        txtmanv.setText(hd.getMaNv());
        txtdongia.setText(String.valueOf(hd.getGiaPhong()));
        txtsonguoi.setText(String.valueOf(hd.getSoNg()));
        txtngayvao.setText(hd.getNgayVao());
        txtngayra.setText(hd.getNgayRa());
        txtngaylap.setText(hd.getThoiGianLaphd());
        txtgiamgia.setText(String.valueOf(hd.getGiamGia()));
        txtthanhtien.setText(String.valueOf(hd.getThanhTien()));
    }

    HoaDon getform() {
        //maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien
        String mahd = txtma.getText();
        if (mahd.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma HD");
            return null;
        }
        String maph = txtmaph.getText();
        if (maph.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma PH");
            return null;
        } else {
            if (hdser.kiemtraPH(maph).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong ton tai phong nay");
                return null;
            }
        }
        String makh = txtmakh.getText();
        if (makh.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma PH");
            return null;
        } else {
            if (hdser.kiemtrakh(makh).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong ton tai khach hang nay !", "Ko ton tai KH", 3);
                return null;
            }
        }
        String manv = txtmanv.getText();
        if (manv.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma PH");
            return null;
        } else {
            if (hdser.kiemtranv(manv).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong ton tai Nhan vien nay !", "Ko ton tai NV", 3);
                return null;
            }
        }
        int dongia = hdser.kiemtraPH(maph).get(0).getGiaPhong();

        int songuoi = 1;
        try {
            songuoi = Integer.parseInt(txtsonguoi.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phai nhap vao 1 so!", "Loi du lieu", 3);
            return null;
        }

        String ngayvao = txtngayvao.getText();
        try {
            Date selectedDate = dateFormat.parse(txtngayvao.getText());
            System.out.println("Ngày đã chọn: " + ngayvao);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày vao không hợp lệ. Nhập theo định dạng yyyy-dd-MM");
            return null;
        }

        String ngayra = txtngayra.getText();
        try {
            Date selectedDate = dateFormat.parse(txtngayra.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày ra không hợp lệ. Nhập theo định dạng yyyy-dd-MM");
            return null;
        }
        DateTimeFormatter formatte;
        //tinh so nagy ở
        formatte = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(ngayvao, formatte);
        LocalDate date2 = LocalDate.parse(ngayra, formatte);
        if (date1.isAfter(date2)) {
            JOptionPane.showMessageDialog(this, "Ngày ra phải sau ngày vào !");
            return null;
        }
        LocalDateTime thoiGianLapHD = LocalDateTime.now();
        // Định dạng DateTime theo mẫu mong muốn (ví dụ: "yyyy-MM-dd HH:mm:ss")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String thoigianlap = thoiGianLapHD.format(formatter);
        System.out.println(thoigianlap);

        int songay = (int) ChronoUnit.DAYS.between(date1, date2);
        int giamgia = Integer.parseInt(txtgiamgia.getText());
        int thanhtien = dongia * songay - giamgia;
        return new HoaDon(mahd, maph, makh, manv, dongia, songuoi, ngayvao, ngayra, thoigianlap, giamgia, thanhtien);

    }

    void loadfalse() {
        txtma.setEnabled(false);
        txtmaph.setEnabled(false);
        txtmakh.setEnabled(false);
        txtmanv.setEnabled(false);
        txtsonguoi.setEnabled(false);
        txtngayvao.setEnabled(false);
        txtngayra.setEnabled(false);
        txtgiamgia.setEnabled(false);
    }

    void loadtrue() {
        txtma.setEnabled(true);
        txtmaph.setEnabled(true);
        txtmakh.setEnabled(true);
        txtmanv.setEnabled(true);
        txtsonguoi.setEnabled(true);
        txtngayvao.setEnabled(true);
        txtngayra.setEnabled(true);
        txtgiamgia.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        txtmaph = new javax.swing.JTextField();
        txtmakh = new javax.swing.JTextField();
        txtmanv = new javax.swing.JTextField();
        txtdongia = new javax.swing.JTextField();
        txtsonguoi = new javax.swing.JTextField();
        txtngayvao = new javax.swing.JTextField();
        txtngaylap = new javax.swing.JTextField();
        txtgiamgia = new javax.swing.JTextField();
        txtthanhtien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablehd = new javax.swing.JTable();
        btnnew = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btntimkiem = new javax.swing.JButton();
        txtngayra = new javax.swing.JFormattedTextField();
        btnthoat = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Hóa Đơn");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Mã Hóa đơn:");

        jLabel3.setText("Mã phòng:");

        jLabel4.setText("Nhân viên lập đơn:");

        jLabel5.setText("Mã Khách hàng:");

        jLabel6.setText("Đơn giá:");

        jLabel7.setText("Số người:");

        jLabel8.setText("Ngày vào:");

        jLabel9.setText("Thời gian lập hd:");

        jLabel10.setText("Ngày ra:");

        jLabel11.setText("Giảm giá:");

        jLabel12.setText("Thành tiền:");

        tablehd.setModel(new javax.swing.table.DefaultTableModel(
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
        tablehd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablehdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablehd);

        btnnew.setText("New");
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnluu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save.png"))); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
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
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmaph, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtngayvao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtngayra)
                                                .addGap(1, 1, 1))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(txtsonguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnnew)
                        .addGap(18, 18, 18)
                        .addComponent(btnthem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnluu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntimkiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnthoat)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngayvao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(txtmaph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngayra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtsonguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtthanhtien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnew)
                    .addComponent(btnthem)
                    .addComponent(btnluu)
                    .addComponent(btnxoa)
                    .addComponent(btntimkiem)
                    .addComponent(btnthoat)
                    .addComponent(btnsua))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablehdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablehdMouseClicked
        index = tablehd.getSelectedRow();
        if (index >= 0) {
            loadform(index);
            btnsua.setEnabled(true);
            btnxoa.setEnabled(true);
            loadfalse();
        }
    }//GEN-LAST:event_tablehdMouseClicked

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        index = tablehd.getSelectedRow();
        HoaDon kh = getform();
        if (kh != null) {
            if (index >= 0) {
                int te = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn Sửa HD không !", "Hỏi", JOptionPane.YES_NO_CANCEL_OPTION, 3, null);
                if (te == 0) {
                    String ma = tablehd.getValueAt(index, 0).toString();
                    System.out.println(kh.getNgayRa());
                    int x = hdser.updateHD(ma, kh);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "Sửa Thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa Thành công !");
                        filltable(hdser.getAllKh());
                        resetForm();
                        btnsua.setEnabled(false);
                        btnluu.setEnabled(false);
                        btnxoa.setEnabled(false);
                        loadtrue();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Đã hủy sửa !");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn Dòng muốn sửa !");
            }
        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        HoaDon hd = getform();
        if (hd != null) {

            List<HoaDon> lit = hdser.kiemtraHD(hd.getMaHd());
            if (lit != null && !lit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã đã được sử dụng vui lòng chọn mã khác !", "Trùng lặp dữ liệu", 2);
            } else {
                int te = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm HD không !", "Hỏi", JOptionPane.YES_NO_CANCEL_OPTION, 3, null);
                if (te == 0) {
                    int x = hdser.insertHD(hd);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "Thêm Thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm Thành công !");
                        filltable(hdser.getAllKh());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn đã hủy thêm !");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất bại !");
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        btnluu.setEnabled(false);
        btnsua.setEnabled(false);
        btnxoa.setEnabled(false);
        txtma.setText("");
        txtmaph.setText("");
        txtmakh.setText("");
        txtmanv.setText("");
        txtsonguoi.setText("");
        txtngayvao.setText("");
        txtngayra.setText("");
        txtgiamgia.setText("");
        txtthanhtien.setText("");
        txtngaylap.setText("");
        txtdongia.setText("?");
        loadtrue();
    }//GEN-LAST:event_btnnewActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        String ma = JOptionPane.showInputDialog(this, "Nhập mã cần tìm ", "Tìm kiếm theo mã KH .", 3);

        if (ma != null && ma.length() > 0) {
            List<HoaDon> list = hdser.kiemtraHD(ma);
            filltable(list);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy HD ");
            } else {
                loadform1(list.get(0));
            }
        }

    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        index = tablehd.getSelectedRow();
        if (index >= 0) {
            String ma = tablehd.getValueAt(index, 0).toString();
            int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hóa đơn này không!");
            if (h == 0) {
                int te = hdser.deleteHD(ma);
                if (te == 0) {
                    JOptionPane.showMessageDialog(this, "Xoa Thất bại !");
                } else {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công Hóa dơn này");
                    btnsua.setEnabled(false);
                    btnluu.setEnabled(false);
                    btnxoa.setEnabled(false);
                    loadtrue();
                    filltable(hdser.getAllKh());
                    resetForm();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn đã hủy xóa !");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn Dòng muốn xoa");
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không ?", "Thoát !", JOptionPane.YES_NO_OPTION, 3);
        if (h == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnthoatActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        txtmaph.setEnabled(true);
        txtmakh.setEnabled(true);
        txtmanv.setEnabled(true);
        txtsonguoi.setEnabled(true);
        txtngayvao.setEnabled(true);
        txtngayra.setEnabled(true);
        txtgiamgia.setEnabled(true);
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
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTable tablehd;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtgiamgia;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtmaph;
    private javax.swing.JTextField txtngaylap;
    private javax.swing.JFormattedTextField txtngayra;
    private javax.swing.JTextField txtngayvao;
    private javax.swing.JTextField txtsonguoi;
    private javax.swing.JTextField txtthanhtien;
    // End of variables declaration//GEN-END:variables
}
