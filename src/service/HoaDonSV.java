/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.*;
import java.sql.*;
import model.*;

/**
 *
 * @author Admin
 */
public class HoaDonSV {

    List<HoaDon> list;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDon> getAllKh() {
        list = new ArrayList<>();
        sql = "select maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien from HoaDon";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon ql = new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public int insertHD(HoaDon hd) {
        sql = "INSERT INTO HoaDon (maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getMaHd());
            ps.setString(2, hd.getMaPhong());
            ps.setString(3, hd.getMaKh());
            ps.setString(4, hd.getMaNv());
            ps.setInt(5, hd.getGiaPhong());
            ps.setInt(6, hd.getSoNg());
            ps.setString(7, hd.getNgayVao());
            ps.setString(8, hd.getNgayRa());
            ps.setString(9, hd.getThoiGianLaphd());
            ps.setInt(10, hd.getGiamGia());
            ps.setInt(11, hd.getThanhTien());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteHD(String ma) {
        sql = "delete HoaDon where maHD like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateHD(String ma, HoaDon hd) {
        sql = "update HoaDon set maphong=?, makh=?, maNv=?, donGia=?"
                + ", soNguoi=?, ngayVao=?, ngayRa=?,thoiGianLapHD=?, giamGia=?, thanhTien= ? where maHD like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hd.getMaPhong());
            ps.setString(2, hd.getMaKh());
            ps.setString(3, hd.getMaNv());
            ps.setInt(4, hd.getGiaPhong());
            ps.setInt(5, hd.getSoNg());
            ps.setString(6, hd.getNgayVao());
            ps.setString(7, hd.getNgayRa());
            ps.setString(8, hd.getThoiGianLaphd());
            ps.setInt(9, hd.getGiamGia());
            ps.setInt(10, hd.getThanhTien());
            ps.setString(11, ma);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public List<HoaDon> kiemtraHD(String ma) {
        list = new ArrayList<>();
        sql = "select maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien from HoaDon where maHD like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon ql = new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
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
     public List<HoaDon> kiemtraHDkh(String ma) {
        list = new ArrayList<>();
        sql = "select maHD, maphong, makh, maNv, donGia, soNguoi, ngayVao, ngayRa,thoiGianLapHD, giamGia, thanhTien from HoaDon where makh like ?";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon ql = new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
     public List<String> getAllph() {
        List<String> listr = new ArrayList<>();
        sql = "select maphong from phong";
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                listr.add(rs.getString(1));
            }
            return listr;
        } catch (SQLException e) {
            return null;
        }
    }
}
