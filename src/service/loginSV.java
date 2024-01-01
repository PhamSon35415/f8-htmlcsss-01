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
public class loginSV {
    List<QuanLy> list;
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    String sql=null;
    public List<QuanLy> gettkql(){
        list=new ArrayList<>();
        sql="select taikhoan, matkhau from manager";
        try {
            conn=DBConnect.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                QuanLy ql=new QuanLy(rs.getString(1), rs.getString(2));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
    public List<QuanLy> gettknv(){
        list=new ArrayList<>();
        sql="select maNv, matKhau from nhanvien";
        try {
            conn=DBConnect.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                QuanLy ql=new QuanLy(rs.getString(1), rs.getString(2));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
    public List<QuanLy> gettkkh(){
        list=new ArrayList<>();
        sql="select makh, matKhau from khachhang";
        try {
            conn=DBConnect.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                QuanLy ql=new QuanLy(rs.getString(1), rs.getString(2));
                list.add(ql);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
    public boolean checkLogin(String username,String pass){
        for (QuanLy ur : list) {
            if(ur.getTaiKhoan().equals(username)&& ur.getMk().equals(pass)){
                return true;
            }
        }
        return false;
    }
}
