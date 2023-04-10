/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.DAO;

import quanlythuvien.entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quanlythuvien.entity.NguoiDung;
import quanlythuvien.util.JDBCHelper;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO extends ElibDAO<NhanVien, String> {

    String INSERT_SQL = "exec sp_TaoTaiKhoan ?,?,?,?,?,?,?,?,?,?";
    String UPDATE_SQL = "UPDATE NHANVIEN SET Ho =?,Ten = ?,NgaySinh = ?,GioiTinh = ?,CCCD = ?,Sdt = ?,Email = ?,TaiKhoan = ? WHERE ID =?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE ID =?";
    String SELECT_ALL_SQL = "SELECT * FROM NHANVIEN";
    String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE ID = ?";
    String SELECT_BY_IDSQL = "SELECT * FROM TAIKHOAN WHERE TaiKhoan = ?"; 

    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.update(INSERT_SQL,entity.getTaiKhoan(),entity.getMatKhau(),entity.isChucVu(),entity.getHo(),entity.getTen(),entity.getNgaySinh(),entity.getGioiTinh(),entity.getcCCD(),entity.getsDT(),entity.getEmail());
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getHo(), entity.getTen(), entity.getNgaySinh(),entity.getGioiTinh(), entity.getcCCD(), entity.getsDT(), entity.getEmail(), entity.getTaiKhoan(),entity.getId());
    }

    public void delete(int id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public NhanVien selectById(int id) {
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setId(rs.getInt("ID"));
                    nv.setHo(rs.getString("Ho"));
                    nv.setTen(rs.getString("Ten"));
                    nv.setNgaySinh(rs.getDate("NgaySinh"));
                    nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                    nv.setcCCD(rs.getString("CCCD"));
                    nv.setsDT(rs.getString("Sdt"));
                    nv.setEmail(rs.getString("Email"));
                    nv.setTaiKhoan(rs.getString("TaiKhoan"));

                    list.add(nv);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<NhanVien> selectByKeywords(String key) {
        String sql = "SELECT * FROM NhanVien WHERE Ho LIKE ?";
        return this.selectBySQL(sql, "%" + key + "%");
    }

//    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
//        NhanVien nv = new NhanVien();
//        nv.setId(rs.getInt("ID"));
//        nv.setHo(rs.getString("Ho"));
//        nv.setTen(rs.getString("Ten"));
//        nv.setNgaySinh(rs.getDate("NgaySinh"));
//        nv.setGioiTinh(rs.getBoolean("GioiTinh"));
//        nv.setCccd(rs.getString("CCCD"));
//        nv.setSdt(rs.getString("Sdt"));
//        nv.setEmail(rs.getString("Email"));
//        nv.setTaiKhoan(rs.getString("TaiKhoan"));
//        return nv;
//    }
    
        public boolean check(String taiKhoan){
        List<NhanVien> list = checkTK(taiKhoan);
        return list.size() > 0 ? true : false;
    }

    public List<NhanVien> checkTK(String taiKhoan){
        java.util.List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = null;
            try {
               rs = JDBCHelper.query(SELECT_BY_IDSQL, taiKhoan);
               while(rs.next()){
               NhanVien nd = new NhanVien(rs.getBoolean("ChucVu"), rs.getString("TaiKhoan"), rs.getString("MatKhau"));
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
    @Override
    public NhanVien selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
