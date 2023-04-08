/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.entity;

/**
 *
 * @author hoang
 */
public class ThongKe {
    private int ID;
    private String TenS;
    private String TenTacGia;
    private String NhaXuatBan;
    private String TheLoai;
    private String ViTri;
    private String Gia;

    public ThongKe() {
    }

    public ThongKe(int ID, String TenS, String TenTacGia, String NhaXuatBan, String TheLoai, String ViTri, String Gia) {
        this.ID = ID;
        this.TenS = TenS;
        this.TenTacGia = TenTacGia;
        this.NhaXuatBan = NhaXuatBan;
        this.TheLoai = TheLoai;
        this.ViTri = ViTri;
        this.Gia = Gia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenS() {
        return TenS;
    }

    public void setTenS(String TenS) {
        this.TenS = TenS;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String TenTacGia) {
        this.TenTacGia = TenTacGia;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String NhaXuatBan) {
        this.NhaXuatBan = NhaXuatBan;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String ViTri) {
        this.ViTri = ViTri;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }
    
}
