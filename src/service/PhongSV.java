/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.*;
import java.sql.*;
import model.phong;

/**
 *
 * @author Admin
 */
public class PhongSV {

    List<phong> list;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<phong> getAllph() {
        list = new ArrayList<>();
        sql = "select maphong, tenphong, loaiphong, giaphong, trangthai, ghichu from phong";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                phong ql = new phong(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public int insertPH(phong hd) {
        sql = "INSERT INTO phong (maphong, tenphong, loaiphong, giaphong, trangthai, ghichu) VALUES (?,?,?,?,?,?)";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getMaPhong());
            ps.setString(2, hd.getTenPhong());
            ps.setString(3, hd.getLoaiPhong());
            ps.setInt(4, hd.getGiaPhong());
            ps.setInt(5, hd.getTrangThai());
            ps.setString(6, hd.getGhiChu());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
     public int deletehd(String ma) {
        sql = "delete HoaDon where maphong like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deletePH(String ma) {
        sql = "delete phong where maphong like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updatePH(String ma, phong ph) {
        sql = "update phong set tenphong=?, loaiphong=?, giaphong=?, trangthai=?, ghichu= ? where maphong like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ph.getTenPhong());
            ps.setString(2, ph.getLoaiPhong());
            ps.setInt(3, ph.getGiaPhong());
            ps.setInt(4, ph.getTrangThai());
            ps.setString(5, ph.getGhiChu());
            ps.setString(6, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public List<phong> kiemtraPH(String ma) {
        List<phong> lists = new ArrayList<>();
        sql = "select maphong, tenphong, loaiphong, giaphong, trangthai, ghichu from phong where maphong like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                phong ql = new phong(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                lists.add(ql);
            }
            return lists;
        } catch (SQLException e) {
            return null;
        }
    }
}
