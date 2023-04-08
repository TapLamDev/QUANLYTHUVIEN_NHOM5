/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.entity;

/**
 *
 * @author HAO
 */
public class NguoiDung {
    private boolean chucVu;
    private String ho;
    private String ten;
    private String ngaySinh;
    private boolean gioiTinh;
    private String cCCD;
    private String sDT;
    private String email;
    private String taiKhoan;
    private String matKhau;
    
    public NguoiDung(String username, String pass, String vaitro, String mand){
    }
    
    public NguoiDung(boolean chucVu, String taiKhoan, String matKhau){
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau; 
        this.chucVu = chucVu;
    }

    public NguoiDung(String ho, String ten, String ngaySinh, boolean gioiTinh, String cCCD, String sDT, String email, String taiKhoan) {
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cCCD = cCCD;
        this.sDT = sDT;
        this.email = email;
        this.taiKhoan = taiKhoan;
    }
    
    
    public NguoiDung(boolean chucVu, String ho, String ten, String ngaySinh, boolean gioiTinh, String cCCD, String sDT, String email, String taiKhoan, String matKhau){
        this.chucVu = chucVu;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cCCD = cCCD;
        this.sDT = sDT;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getcCCD() {
        return cCCD;
    }

    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
