/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class NhanVien {

    private String maNv;
    private String tenNv;
    private String chucVu;
    private String matKhau;
    private int luong;
    private String namSinh;
    private String sdt;
    private int gioiTinh;
    private String ghiChu;

    public Object[] torow() {
        String gt;
        switch (gioiTinh) {
            case 0 -> gt = "Nữ";
            case 1 -> gt = "Nam";
            case 2 -> gt = "Không xác Định";
            default -> throw new AssertionError();
        }
        return new Object[]{
            maNv, tenNv, chucVu, matKhau, luong, namSinh, sdt, gt, ghiChu
        };
    }

    public NhanVien(String maNv, String tenNv, String chucVu, String matKhau, int luong, String namSinh, String sdt, int gioiTinh, String ghiChu) {
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
        this.luong = luong;
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ghiChu = ghiChu;
    }

    public NhanVien() {
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
