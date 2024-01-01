/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private String maHd;
    private String maPhong;
    private String maKh;
    private String maNv;
    private int giaPhong;
    private int soNg;
    private String ngayVao;
    private String ngayRa;
    private String thoiGianLaphd;
    private int giamGia;
    private int thanhTien;//(ngayRa-ngayVoa)*giaPhong-giamGia

    public Object[] toRow(){
        return new Object[]{
            maHd,maPhong,maKh,maNv,giaPhong,soNg,ngayVao,ngayRa,thoiGianLaphd,giamGia,thanhTien
        };
    }
    public HoaDon(String maHd, String maPhong, String maKh, String maNv, int giaPhong, int soNg, String ngayVao, String ngayRa, String thoiGianLaphd, int giamGia, int thanhTien) {
        this.maHd = maHd;
        this.maPhong = maPhong;
        this.maKh = maKh;
        this.maNv = maNv;
        this.giaPhong = giaPhong;
        this.soNg = soNg;
        this.ngayVao = ngayVao;
        this.ngayRa = ngayRa;
        this.thoiGianLaphd = thoiGianLaphd;
        this.giamGia = giamGia;
        this.thanhTien = thanhTien;
    }

    public HoaDon() {
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public int getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(int giaPhong) {
        this.giaPhong = giaPhong;
    }

    public int getSoNg() {
        return soNg;
    }

    public void setSoNg(int soNg) {
        this.soNg = soNg;
    }

    public String getNgayVao() {
        return ngayVao;
    }

    public void setNgayVao(String ngayVao) {
        this.ngayVao = ngayVao;
    }

    public String getNgayRa() {
        return ngayRa;
    }

    public void setNgayRa(String ngayRa) {
        this.ngayRa = ngayRa;
    }

    public String getThoiGianLaphd() {
        return thoiGianLaphd;
    }

    public void setThoiGianLaphd(String thoiGianLaphd) {
        this.thoiGianLaphd = thoiGianLaphd;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
    
    
    
}
