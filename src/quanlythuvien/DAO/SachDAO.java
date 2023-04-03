
package quanlythuvien.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import quanlythuvien.entity.Sach;
import quanlythuvien.util.JDBCHelper;


public class SachDAO extends ElibDAO<Sach, String>{
    
    String INSERT_SQL = "INSERT INTO SACH(MaS,TenS,TenTacGia,NhaXuatBan,TheLoai,NgonNgu,Gia,ViTri,SoLuong,Anh) VALUES(?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SACH SET TenS=?,TenTacGia=?,NhaXuatBan=?,TheLoai=?,NgonNgu=?,Gia=?,ViTri=?,SoLuong=?,Anh=? where MaS=?";
    String DELETE_SQL = "DELETE FROM SACH where MaS= ?";
    String SELECT_ALL_SQL = "SELECT * FROM SACH";
    String SELECT_BY_ID_SQL = "SELECT * FROM SACH WHERE MaS = ?";

    @Override
    public void insert(Sach entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaSach(),entity.getTenSach(),entity.getTenTacGia(),entity.getNXB(),entity.getTheLoai(),entity.getNgonNgu(),entity.getDonGia(),entity.getViTri(),entity.getSoLuong(),entity.getAnh());
    }

    @Override
    public void update(Sach entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenSach(),entity.getTenTacGia(),entity.getNXB(),entity.getTheLoai(),entity.getNgonNgu(),entity.getDonGia(),entity.getViTri(),entity.getSoLuong(),entity.getAnh(),entity.getMaSach());

    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<Sach> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Sach selectById(String id) {
                List<Sach> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<Sach> selectBySQL(String sql, Object... args) {
                List<Sach> list = new ArrayList<Sach>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Sach s = new Sach(rs.getString("MaS"),rs.getString("TenS"),rs.getString("TenTacGia"),rs.getString("NhaXuatBan"),rs.getString("TheLoai"),rs.getString("NgonNgu"),rs.getDouble("Gia"),rs.getString("ViTri"),rs.getInt("SoLuong"),rs.getString("Anh"));
                    list.add(s);
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
