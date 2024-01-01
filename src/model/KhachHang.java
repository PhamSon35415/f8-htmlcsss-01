/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private String maKh;
    private String tenKh;
    private String matKhau;
    private String sdt;
    private String cmnd;
    private int gioiTinh;
    private int tuoi;

    public Object[] torow(){
        String gt;
        switch (gioiTinh) {
            case 0 -> gt = "Nữ";
            case 1 -> gt = "Nam";
            case 2 -> gt = "Không xác Định";
            default -> throw new AssertionError();
        }
        return new Object[]{
            maKh,tenKh,matKhau,sdt,cmnd,gt,tuoi
        };
    }
    public KhachHang(String maKh, String tenKh, String matKhau, String sdt, String cmnd, int gioiTinh, int tuoi) {
        this.maKh = maKh;
        this.tenKh = tenKh;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
    }

    public KhachHang() {
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
    
    
    
    
    
    
}
