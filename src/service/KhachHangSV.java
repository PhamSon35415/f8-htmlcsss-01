/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.KhachHang;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class KhachHangSV {

    List<KhachHang> list;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<KhachHang> getAllKh() {
        list = new ArrayList<>();
        sql = "select makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi from khachhang";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang ql = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public int insertKH(KhachHang kh) {
        sql = "INSERT INTO khachhang (makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi) VALUES (?,?,?,?,?,?,?)";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMaKh());
            ps.setString(2, kh.getTenKh());
            ps.setString(3, kh.getMatKhau());
            ps.setString(4, kh.getSdt());
            ps.setString(5, kh.getCmnd());
            ps.setInt(6, kh.getGioiTinh());
            ps.setInt(7, kh.getTuoi());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteKh(String ma) {
        sql = "delete khachhang where makh like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deletehd(String ma) {
        sql = "delete HoaDon where makh like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateSV(String ma, KhachHang kh) {
        sql = "update khachhang set tenkhachhang=?,matKhau=?, sdt=?, CMND=?, gioitinh=?, tuoi=? where makh=?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getTenKh());
            ps.setString(2, kh.getMatKhau());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getCmnd());
            ps.setInt(5, kh.getGioiTinh());
            ps.setInt(6, kh.getTuoi());
            ps.setString(7, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public List<KhachHang> kiemtrakh(String ma) {
        List<KhachHang> lists = new ArrayList<>();
        sql = "select makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi from khachhang where makh=?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang ql = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7));
                lists.add(ql);
            }
            return lists;
        } catch (SQLException e) {
            return null;
        }
    }

    public KhachHang kiemtra1(String ma) {

        sql = "select makh, tenkhachhang,matKhau, sdt, CMND, gioitinh, tuoi from khachhang where makh=?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            KhachHang ql=null;
            while (rs.next()) {
                 ql= new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7));

                
            }
            return ql;

        } catch (SQLException e) {
            return null;
        }
    }

}
