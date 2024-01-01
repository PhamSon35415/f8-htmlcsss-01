/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;
import model.*;
import service.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Thongtinkh extends javax.swing.JFrame {

    Login lg = new Login();
    KhachHangSV khser = new KhachHangSV();
    HoaDonSV hdser = new HoaDonSV();
    String[] colums = {
        "Mã Hóa Đơn", "Mã phòng", "Mã KH", "Mã Nv", "Đơn giá", "Số người", "Ngày vào", "Ngày ra", "Thời gian lập HĐ", "Giảm giá", "Thành tiền"
    };
    DefaultTableModel model = new DefaultTableModel(colums, 0);
    /**
     * Creates new form khachhangcc
     */
    private String ma1;

    void filltable(List<HoaDon> list) {
        model.setRowCount(0);
        for (HoaDon hd : list) {
            model.addRow(hd.toRow());
        }

    }

    public Thongtinkh(String ma2) {
        initComponents();
        table.setModel(model);
        ma1 = ma2;
        btnluu.setVisible(false);
        loadform1(khser.kiemtra1(ma1));
        this.setLocationRelativeTo(null);
        loadfalse();
        txttthd.setText("Thông tin Hóa Đơn Của Khách hàng :" + ma1);
        filltable(hdser.kiemtraHDkh(ma2));
        this.setTitle("Thông tin khách hàng.");
        table.setEnabled(false);

    }

    void loadform1(KhachHang kh) {
        //makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi
        txtma.setText(kh.getMaKh());
        txtten.setText(kh.getTenKh());
        txtmk.setText(kh.getMatKhau());
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

    void loadtrue() {
//        txtma.setEnabled(true);
        txtten.setEnabled(true);
        txtmk.setEnabled(true);
        txtsdt.setEnabled(true);
        txtcmnd.setEnabled(true);
        txttuoi.setEnabled(true);
        rdkxd.setEnabled(true);
        rdnam.setEnabled(true);
        rdnu.setEnabled(true);
    }

    void loadfalse() {
        txtma.setEnabled(false);
        txtten.setEnabled(false);
        txtmk.setEnabled(false);
        txtsdt.setEnabled(false);
        txtcmnd.setEnabled(false);
        txttuoi.setEnabled(false);
        rdkxd.setEnabled(false);
        rdnam.setEnabled(false);
        rdnu.setEnabled(false);
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
        } else if (mk.length() > 10) {
            JOptionPane.showMessageDialog(this, "MK phai có ít hơn 10 kí tự!", "Thông Báo Thiếu Dữ Liệu !", 3);
            return null;
        }
        int sdt=-1;
        try {
            sdt=Integer.parseInt(txtsdt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập vòa 1 số !", "Sai kiểu đư liệu !", 0);
            return null;
        }
        if (sdt == -1) {
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
        return new KhachHang(ma, ten, mk, String.valueOf(sdt), cmnd, gt, tuoi);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        btnluu = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txttthd = new javax.swing.JLabel();
        btnthoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Mã khách hàng:");

        jLabel3.setText("Tên khách hàng:");

        jLabel4.setText("Mật khẩu:");

        jLabel5.setText("Số DT :");

        jLabel6.setText("CMND :");

        jLabel7.setText("Giới tính :");

        txtmk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmkActionPerformed(evt);
            }
        });

        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });

        rdnam.setText("Nam");

        rdnu.setText("Nữ");

        rdkxd.setText("KXD");

        jLabel8.setText("Tuổi:");

        btnluu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save.png"))); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnsua.setText("Sửa thông tin");
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
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtma, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(txtten)
                    .addComponent(txtmk)
                    .addComponent(txttuoi))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdnu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdkxd))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(rdnam)
                    .addComponent(rdnu)
                    .addComponent(rdkxd))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua)
                    .addComponent(btnluu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Khách Hàng", jPanel1);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        txttthd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txttthd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txttthd.setText("Thông tin Hóa Đơn Của Khách hàng :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txttthd)
                .addGap(133, 133, 133))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttthd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin Hóa đơn", jPanel2);

        btnthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Log out.png"))); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnthoat)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed

        KhachHang kh = getForm();
        if (kh != null) {
            int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn Lưu thông tin đã sửa không!");
            if (h == 0) {
                String ma = ma1;
                int x = khser.updateSV(ma, kh);
                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Thất bại !");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Thành công !");
                    loadfalse();
                    btnluu.setVisible(false);
                    btnsua.setVisible(true);
                    filltable(hdser.kiemtraHDkh(ma1));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy sửa !");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Sửa Thất bại !");
        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        btnluu.setVisible(true);
        btnsua.setVisible(false);
        loadtrue();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        int h = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không ?", "Thoát !", JOptionPane.YES_NO_OPTION, 3);
        if (h == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnthoatActionPerformed

    private void txtmkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmkActionPerformed

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed

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
            java.util.logging.Logger.getLogger(Thongtinkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Thongtinkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Thongtinkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Thongtinkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Thongtinkh("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthoat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdkxd;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JRadioButton rdnu;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtcmnd;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmk;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    private javax.swing.JLabel txttthd;
    private javax.swing.JTextField txttuoi;
    // End of variables declaration//GEN-END:variables
}
