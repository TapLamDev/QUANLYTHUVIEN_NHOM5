/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.DAO;

//import com.elib.utils.XJdbc;
import java.util.ArrayList;
import quanlythuvien.entity.NguoiDung;
import java.sql.ResultSet;
import quanlythuvien.util.JDBCHelper;

/**
 *
 * @author HAO
 */
public class NguoiDungDAO {
     String INSERT_SQL = "exec sp_TaoTaiKhoan ?,?,?,?,?,?,?,?,?,?";
     String UPDATE_SQL = "UPDATE NhanVien SET MatKhau =? ,HoTen = ?, VaiTro = ? WHERE MaNV =?";
     String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV =?";
     String SELECT_ALL_SQL ="SELECT * FROM TAIKHOAN";
     String SELECT_BY_ID_SQL = "SELECT * FROM TAIKHOAN WHERE TaiKhoan = ?"; 
    

    public void insert(NguoiDung entity) {
                JDBCHelper.update(INSERT_SQL,entity.getTaiKhoan(),entity.getMatKhau(),entity.isChucVu(),entity.getHo(),entity.getTen(),entity.getNgaySinh(),entity.isGioiTinh(),entity.getcCCD(),entity.getsDT(),entity.getEmail());
        }
    
    public boolean check(String taiKhoan){
        java.util.List<NguoiDung> list = checkTK(taiKhoan);
        return list.size() > 0 ? true : false;
    }

    public java.util.List<NguoiDung> checkTK(String taiKhoan){
        java.util.List<NguoiDung> list = new ArrayList<NguoiDung>();
        try {
            ResultSet rs = null;
            try {
               rs = JDBCHelper.query(SELECT_BY_ID_SQL, taiKhoan);
               while(rs.next()){
               NguoiDung nd = new NguoiDung(rs.getBoolean("ChucVu"), rs.getString("TaiKhoan"), rs.getString("MatKhau"));
               list.add(nd);
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