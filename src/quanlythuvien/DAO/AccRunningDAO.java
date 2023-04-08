/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.DAO;

import quanlythuvien.entity.AccRunning;
//import quanlythuvien.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import quanlythuvien.util.JDBCHelper;

/**
 *
 * @author Admin
 */
public class AccRunningDAO {
    private String accRunning_SQL = "exec sp_AccRunning ?";
    
    public AccRunningDAO(){
    }
    
    public Boolean CheckDangNhap(String taiKhoan, String matKhau){
        java.util.List<AccRunning> arn = DangNhap(taiKhoan);
        if (arn.get(0).getTaiKhoan().equals(taiKhoan)  && arn.get(0).getMatKhau().equals(matKhau)){
            return true;
        } else {
            return false; 
        }
    }
    
    public java.util.List<AccRunning> DangNhap(String taiKhoan){
        java.util.List<AccRunning> list = new ArrayList<AccRunning>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(accRunning_SQL, taiKhoan);
                while(rs.next()){
                AccRunning arn = new AccRunning(rs.getBoolean("ChucVu"), rs.getInt("ID"), rs.getString("Ho"), rs.getString("Ten"), rs.getString("NgaySinh"), rs.getBoolean("GioiTinh"), rs.getString("CCCD"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TaiKhoan"), rs.getString("MatKhau"));
                list.add(arn);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
