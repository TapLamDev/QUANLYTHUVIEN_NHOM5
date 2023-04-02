
package quanlythuvien.entity;


public class Sach {
    private String MaSach;
    private String TenSach;
    private String TenTacGia;
    private String NXB;
    private String TheLoai;
    private String NgonNgu;
    private double DonGia;
    private String ViTri;
    private int SoLuong;
    private String Anh;

    public Sach() {
    }

    public Sach(String MaSach, String TenSach, String TenTacGia, String NXB, String TheLoai, String NgonNgu, double DonGia, String ViTri, int SoLuong, String Anh) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.TenTacGia = TenTacGia;
        this.NXB = NXB;
        this.TheLoai = TheLoai;
        this.NgonNgu = NgonNgu;
        this.DonGia = DonGia;
        this.ViTri = ViTri;
        this.SoLuong = SoLuong;
        this.Anh = Anh;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String TenTacGia) {
        this.TenTacGia = TenTacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public String getNgonNgu() {
        return NgonNgu;
    }

    public void setNgonNgu(String NgonNgu) {
        this.NgonNgu = NgonNgu;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String ViTri) {
        this.ViTri = ViTri;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

    
    
}
