/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.entity;

import java.util.Date;
import quanlythuvien.util.XDate;

/**
 *
 * @author ADMIN
 */
public class NhanVien {
    
    private boolean chucVu;
    private int id;
    private String ho;
    private String ten;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String cCCD;
    private String sDT;
    private String email;
    private String taiKhoan;
    private String matKhau;

    public NhanVien(){
    }
    
    public NhanVien(boolean chucVu, String taiKhoan, String matKhau){
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau; 
        this.chucVu = chucVu;
    }

    public NhanVien(int id, String ho, String ten, Date ngaySinh, boolean gioiTinh, String cCCD, String sDT, String email, String taiKhoan) {
        this.id = id;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cCCD = cCCD;
        this.sDT = sDT;
        this.email = email;
        this.taiKhoan = taiKhoan;
    }
    

    public NhanVien(String ho, String ten, Date ngaySinh, boolean gioiTinh, String cCCD, String sDT, String email, String taiKhoan) {
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cCCD = cCCD;
        this.sDT = sDT;
        this.email = email;
        this.taiKhoan = taiKhoan;
    }

    public NhanVien(boolean chucVu, String ho, String ten, Date ngaySinh, boolean gioiTinh, String cCCD, String sDT, String email, String taiKhoan, String matKhau) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean getGioiTinh() {
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
