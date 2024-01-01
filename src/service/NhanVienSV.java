/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.util.*;
import java.sql.*;
import model.NhanVien;
/**
 *
 * @author Admin
 */
public class NhanVienSV {
    List<NhanVien> list;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
     public List<NhanVien> getAllKh() {
        list = new ArrayList<>();
        sql = "select maNv, tenNv, chucVu,matKhau, luong, namSinh, sdtNv, gioiTinh, ghiChu from nhanvien";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien ql = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),rs.getString(7), rs.getInt(8),rs.getString(9));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
     public int insertNV(NhanVien kh) {
        sql = "INSERT INTO nhanvien (maNv, tenNv, chucVu,matKhau, luong, namSinh, sdtNv, gioiTinh, ghiChu) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMaNv());
            ps.setString(2, kh.getTenNv());
            ps.setString(3, kh.getChucVu());
            ps.setString(4, kh.getMatKhau());
            ps.setInt(5, kh.getLuong());
            ps.setString(6, kh.getNamSinh());
            ps.setString(7, kh.getSdt());
            ps.setInt(8, kh.getGioiTinh());
            ps.setString(9, kh.getGhiChu());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteNV(String ma) {
        sql = "delete nhanvien where maNv like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public int deleteHD(String ma) {
        sql = "delete HoaDon where maNv like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateNV(String ma, NhanVien kh) {
        sql = "update nhanvien set tenNv=?, chucVu=?,matKhau=?, luong=?, namSinh=?, sdtNv=?, gioiTinh=?, ghiChu= ? where maNv like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getTenNv());
            ps.setString(2, kh.getChucVu());
            ps.setString(3, kh.getMatKhau());
            ps.setInt(4, kh.getLuong());
            ps.setString(5, kh.getNamSinh());
            ps.setString(6, kh.getSdt());
            ps.setInt(7, kh.getGioiTinh());
            ps.setString(8, kh.getGhiChu());
            ps.setString(9, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public List<NhanVien> kiemtranv(String ma) {
        List<NhanVien >lists = new ArrayList<>();
        sql = "select maNv, tenNv, chucVu,matKhau, luong, namSinh, sdtNv, gioiTinh, ghiChu from nhanvien where maNv like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien ql = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),rs.getString(7), rs.getInt(8),rs.getString(9));
                lists.add(ql);
            }
            return lists;
        } catch (SQLException e) {
            return null;
        }
    }
}
