package quanlythuvien.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quanlythuvien.entity.Sach;
import quanlythuvien.util.JDBCHelper;

public class SachDAO extends ElibDAO<Sach, String> {

    String INSERT_SQL = "INSERT INTO SACH(TenS,TenTacGia,NhaXuatBan,TheLoai,NgonNgu,Gia,ViTri,SoLuong,Anh) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SACH SET TenS=?,TenTacGia=?,NhaXuatBan=?,TheLoai=?,NgonNgu=?,Gia=?,ViTri=?,SoLuong=?,Anh=? where ID=?";
    String DELETE_SQL = "DELETE FROM SACH where ID= ?";
    String SELECT_ALL_SQL = "select * from sach";
    String SELECT_BY_ID_SQL = "SELECT * FROM SACH WHERE ID = ?";

    @Override
    public void insert(Sach entity) {
        JDBCHelper.update(INSERT_SQL, entity.getTenSach(), entity.getTenTacGia(), entity.getNXB(), entity.getTheLoai(), entity.getNgonNgu(), entity.getDonGia(), entity.getViTri(), entity.getSoLuong(), entity.getAnh());
    }

    @Override
    public void update(Sach entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenSach(), entity.getTenTacGia(), entity.getNXB(), entity.getTheLoai(), entity.getNgonNgu(), entity.getDonGia(), entity.getViTri(), entity.getSoLuong(), entity.getAnh(), entity.getID());

    }

    public void delete(int id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<Sach> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    public Sach selectById(int id) {
        List<Sach> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<Sach> selectBySQL(String sql, Object... args) {
        List<Sach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setID(rs.getInt("ID"));
                    s.setTenSach(rs.getString("TenS"));
                    s.setTenTacGia(rs.getString("TenTacGia"));
                    s.setNXB(rs.getString("NhaXuatBan"));
                    s.setTheLoai(rs.getString("TheLoai"));
                    s.setNgonNgu(rs.getString("NgonNgu"));
                    s.setDonGia(rs.getDouble("Gia"));
                    s.setViTri(rs.getString("ViTri"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    s.setAnh(rs.getString("Anh"));

                    list.add(s);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public Sach selectByTenSach(String tenSach) {
        String sql = "SELECT * FROM Sach WHERE TenS LIKE ?";
        List<Sach> list = this.selectBySQL(sql, "%" + tenSach + "%");
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Sach> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Sach WHERE TenS LIKE ?";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

    public List<Sach> selectByTheLoai(String tenLoai) {
        String sql = "SELECT * FROM SACH WHERE TheLoai = ?";
        return this.selectBySQL(sql, tenLoai);
    }

    @Override
    public Sach selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
