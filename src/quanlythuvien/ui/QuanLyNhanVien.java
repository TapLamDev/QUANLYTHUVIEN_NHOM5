/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlythuvien.ui;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quanlythuvien.DAO.NhanVienDAO;
import quanlythuvien.entity.NhanVien;
import quanlythuvien.util.Auth;
import quanlythuvien.util.MsgBox;
import quanlythuvien.util.XDate;
import quanlythuvien.util.XImage;

/**
 *
 * @author hoang
 */
public class QuanLyNhanVien extends javax.swing.JFrame {
    
    DefaultTableModel tblModel;
    int row = -1;
    NhanVienDAO nvDAO = new NhanVienDAO();
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Elib;user=sa;password=My27012003@";
    
    public QuanLyNhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Quản Lý Nhân Viên");
        initTable();
        this.fillTable();
    }
    
    public void initTable() {
        tblModel = (DefaultTableModel) tblQuanLyNV.getModel();
        String[] nav = new String[]{"Mã nhân viên", "Họ", "Tên", "Ngày sinh", "Giới tính", "CCCD", "SĐT", "Email", "Tài khoản"};
        tblModel.setColumnIdentifiers(nav);
    }
    
    public void quanLySach() {
        if (Auth.isLogin()) {
            new QuanlySach().setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    public void quanLyNhanVien() {
        if (Auth.isLogin()) {
            new QuanLyNhanVien().setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }
    
    public void quanLyHoaDon() {
        if (Auth.isLogin()) {
            new QuanLyDonMuonSach().setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    public void hoSo() {
        if (Auth.isLogin()) {
            new HoSo().setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    public void doiMK() {
        if (Auth.isLogin()) {
            new DoiMatKhau().setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    public void thongKeSach() {
        if (Auth.isLogin()) {
            new ThongKeSach().setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    public void thoatChuongTrinh() {
        if (MsgBox.confirm(this, "Thoát chương trình?")) {
            System.exit(0);
        }
    }
    
    void clearForm() {
        txtMaNV.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        buttonGroup1.clearSelection();
        txtCCCD.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtTaiKhoan.setText("");
        
    }
    
    void setForm(NhanVien nv) {
        txtMaNV.setText(String.valueOf(nv.getId()));
        txtHo.setText(nv.getHo());
        txtTen.setText(nv.getTen());
        txtNgaySinh.setText(XDate.toString(nv.getNgaySinh(), "dd/MM/yyyy"));
//        txtNgaySinh.setText(nv.getNgaySinh());
        boolean gt = nv.getGioiTinh();
        if (gt == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtCCCD.setText(nv.getcCCD());
        txtSDT.setText(nv.getcCCD());
        txtEmail.setText(nv.getEmail());
        txtTaiKhoan.setText(nv.getTaiKhoan());
    }
    
    NhanVien getForm() {
        NhanVien nv = new NhanVien();
//        nv.setId(Auth.user.getId());
        nv.setHo(txtHo.getText());
        nv.setTen(txtTen.getText());
        nv.setNgaySinh(XDate.toDate(txtNgaySinh.getText(), "dd/MM/yyyy"));
//        nv.setNgaySinh(txtNgaySinh.getText());
        nv.setcCCD(txtCCCD.getText());
        nv.setsDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setTaiKhoan(txtTaiKhoan.getText());
        return nv;
    }

    NhanVien getForm1() {
        NhanVien nv = new NhanVien();
        nv.setId(Integer.parseInt(txtMaNV.getText()));
        nv.setHo(txtHo.getText());
        nv.setTen(txtTen.getText());
        nv.setNgaySinh(XDate.toDate(txtNgaySinh.getText(), "dd/MM/yyyy"));
        nv.setGioiTinh(rdoNam.isSelected());
        nv.setcCCD(txtCCCD.getText());
        nv.setsDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setTaiKhoan(txtTaiKhoan.getText());
        return nv;
    }
    
    void edit() {
        int id = (int) tblQuanLyNV.getValueAt(this.row, 0);
        NhanVien nv = nvDAO.selectById(id);
        this.setForm(nv);
        
    }
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblQuanLyNV.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = nvDAO.selectAll();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getId(),
                    nv.getHo(),
                    nv.getTen(),
                    XDate.toString(nv.getNgaySinh(), "yyyy/MM/dd"),
                    nv.getGioiTinh() ? "Nam" : "Nữ",
                    nv.getcCCD(),
                    nv.getsDT(),
                    nv.getEmail(),
                    nv.getTaiKhoan(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!!!");
            e.printStackTrace();
        }
    }
    
    void insert() {        
        if (validates()) {
            try {
                NhanVien nv = new NhanVien(false, txtHo.getText(), txtTen.getText(), XDate.toDate(txtNgaySinh.getText(), "yyyy/MM/dd"), rdoNam.isSelected(), txtCCCD.getText(), txtSDT.getText(), txtEmail.getText(), txtTaiKhoan.getText(), "123");
                nvDAO.insert(nv);
                this.fillTable();
                this.clearForm();
                txtMaNV.requestFocus();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                e.printStackTrace();
            }
        }
        
    }
    
    void update() {
        
        if (validates()) {
            try {
                NhanVien nv = getForm1();
                nvDAO.update(nv);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
                e.printStackTrace();
            }
        }
        
    }
    
    void delete() {
        String maNV = txtMaNV.getText();
        if (MsgBox.confirm(this, "Bạn có chắc chắn xóa nhân viên này?")) {
            try {
                nvDAO.delete(maNV);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
                e.printStackTrace();
            }
        }
    }
    
    void first() {
        this.row = 0;
        this.edit();
        this.updateInfo();
    }
    
    void next() {
        if (this.row < tblQuanLyNV.getRowCount() - 1) {
            this.row++;
            this.edit();
            this.updateInfo();
        }
    }
    
    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
            this.updateInfo();
        }
    }
    
    void last() {
        this.row = tblQuanLyNV.getRowCount() - 1;
        this.edit();
        this.updateInfo();
    }
    
    void updateInfo() {
        tblQuanLyNV.setRowSelectionInterval(row, row);
        this.edit();
//        lblBanGhi.setText(ThongTinBanGhi());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLyNV = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnFirst1 = new javax.swing.JButton();
        btnPrev1 = new javax.swing.JButton();
        btnNext1 = new javax.swing.JButton();
        btnLast1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnChinhSua = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuDoiMK = new javax.swing.JMenuItem();
        mnuHoSo = new javax.swing.JMenuItem();
        mnuDangXuat = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuThoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuQLSach = new javax.swing.JMenuItem();
        mnuQLNV = new javax.swing.JMenuItem();
        mnuQLDonMuonSach = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuThongKeSach = new javax.swing.JMenuItem();
        mnuThongKeDonMuonSach = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setRollover(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblQuanLyNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLyNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLyNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuanLyNV);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 610, 220));

        btnXoa.setBackground(new java.awt.Color(117, 76, 36));
        btnXoa.setForeground(new java.awt.Color(255, 206, 41));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/delete.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, 90, 40));

        btnFirst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/First.png"))); // NOI18N
        btnFirst1.setBorderPainted(false);
        btnFirst1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnFirst1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 32, 32));

        btnPrev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Prev.png"))); // NOI18N
        btnPrev1.setBorderPainted(false);
        btnPrev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrev1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 32, 32));

        btnNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Next.png"))); // NOI18N
        btnNext1.setBorderPainted(false);
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 32, 32));

        btnLast1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Last.png"))); // NOI18N
        btnLast1.setBorderPainted(false);
        btnLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnLast1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("QUẢN LÝ NHÂN VIÊN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 190, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 420, 440));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-books.png"))); // NOI18N
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 60, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-staff.png"))); // NOI18N
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-bill.png"))); // NOI18N
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/back 2.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 440));

        jToolBar1.add(jPanel1);

        tabs.addTab("Danh Sách", jToolBar1);

        jToolBar2.setRollover(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("ID:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        txtMaNV.setEditable(false);
        txtMaNV.setForeground(new java.awt.Color(255, 153, 51));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        jPanel2.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 70, -1));

        jLabel13.setText("Họ:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        txtHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoActionPerformed(evt);
            }
        });
        jPanel2.add(txtHo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 70, -1));

        jLabel14.setText("Tên:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });
        jPanel2.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 70, -1));

        jLabel18.setText("Ngày sinh:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });
        jPanel2.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 210, -1));

        jLabel20.setText("Giới tính:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        btnLuu.setBackground(new java.awt.Color(117, 76, 36));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 206, 41));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/done.png"))); // NOI18N
        btnLuu.setText("SAVE");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel2.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 140, 30));

        btnLamMoi.setBackground(new java.awt.Color(117, 76, 36));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 206, 41));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/new.png"))); // NOI18N
        btnLamMoi.setText("REFRESH");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel2.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 140, 30));

        btnChinhSua.setBackground(new java.awt.Color(117, 76, 36));
        btnChinhSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChinhSua.setForeground(new java.awt.Color(255, 206, 41));
        btnChinhSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/edit.png"))); // NOI18N
        btnChinhSua.setText("UPDATE");
        btnChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnChinhSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 140, 30));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/First.png"))); // NOI18N
        btnFirst.setBorderPainted(false);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel2.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 32, 32));

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Prev.png"))); // NOI18N
        btnPrev.setBorderPainted(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 32, 32));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Next.png"))); // NOI18N
        btnNext.setBorderPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel2.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 32, 32));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Last.png"))); // NOI18N
        btnLast.setBorderPainted(false);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel2.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 32, 32));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setText("THÔNG TIN NHÂN VIÊN");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 280, -1));

        jLabel26.setText("Tài khoản:");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));
        jPanel2.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 210, -1));

        jLabel27.setText("Email:");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 210, -1));

        jLabel28.setText("CCCD:");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));
        jPanel2.add(txtCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 210, -1));

        jLabel29.setText("SĐT:");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        jPanel2.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 210, -1));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });
        jPanel2.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });
        jPanel2.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 50, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 500, 450));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 450));

        jToolBar2.add(jPanel2);

        tabs.addTab("Cập Nhật", jToolBar2);

        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/system.png"))); // NOI18N
        jMenu1.setText("Hệ thống");

        mnuDoiMK.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuDoiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/1.png"))); // NOI18N
        mnuDoiMK.setText("Đổi mật khẩu");
        mnuDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDoiMKActionPerformed(evt);
            }
        });
        jMenu1.add(mnuDoiMK);

        mnuHoSo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/2.png"))); // NOI18N
        mnuHoSo.setText("Hồ sơ");
        mnuHoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHoSoActionPerformed(evt);
            }
        });
        jMenu1.add(mnuHoSo);

        mnuDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/3.png"))); // NOI18N
        mnuDangXuat.setText("Đăng xuất");
        mnuDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(mnuDangXuat);
        jMenu1.add(jSeparator1);

        mnuThoat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mnuThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/close-windows.png"))); // NOI18N
        mnuThoat.setText("Thoát");
        mnuThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThoatActionPerformed(evt);
            }
        });
        jMenu1.add(mnuThoat);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage 3.png"))); // NOI18N
        jMenu2.setText("Quản lý");

        mnuQLSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/1.png"))); // NOI18N
        mnuQLSach.setText("Quản lý sách");
        mnuQLSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuQLSachActionPerformed(evt);
            }
        });
        jMenu2.add(mnuQLSach);

        mnuQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/2.png"))); // NOI18N
        mnuQLNV.setText("Quản lý nhân viên");
        mnuQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuQLNVActionPerformed(evt);
            }
        });
        jMenu2.add(mnuQLNV);

        mnuQLDonMuonSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/3.png"))); // NOI18N
        mnuQLDonMuonSach.setText("Quản lý đơn mượn sách");
        mnuQLDonMuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuQLDonMuonSachActionPerformed(evt);
            }
        });
        jMenu2.add(mnuQLDonMuonSach);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statics view 3.png"))); // NOI18N
        jMenu3.setText("Thống kê");

        mnuThongKeSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/1.png"))); // NOI18N
        mnuThongKeSach.setText("Thống kê sách");
        mnuThongKeSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThongKeSachActionPerformed(evt);
            }
        });
        jMenu3.add(mnuThongKeSach);

        mnuThongKeDonMuonSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/2.png"))); // NOI18N
        mnuThongKeDonMuonSach.setText("Thống kê đơn mượn sách");
        mnuThongKeDonMuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThongKeDonMuonSachActionPerformed(evt);
            }
        });
        jMenu3.add(mnuThongKeDonMuonSach);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        this.insert();
        this.clearForm();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        this.clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaActionPerformed
        // TODO add your handling code here:
        this.update();
        this.clearForm();
    }//GEN-LAST:event_btnChinhSuaActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void mnuDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDoiMKActionPerformed
        doiMK();
    }//GEN-LAST:event_mnuDoiMKActionPerformed

    private void mnuHoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHoSoActionPerformed
        hoSo();
    }//GEN-LAST:event_mnuHoSoActionPerformed

    private void mnuDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDangXuatActionPerformed
        this.dispose();
        new DangNhap(this,true).setVisible(true);
    }//GEN-LAST:event_mnuDangXuatActionPerformed

    private void mnuThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThoatActionPerformed
        thoatChuongTrinh();
    }//GEN-LAST:event_mnuThoatActionPerformed

    private void mnuQLSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuQLSachActionPerformed
        quanLySach();
    }//GEN-LAST:event_mnuQLSachActionPerformed

    private void mnuQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuQLNVActionPerformed
        quanLyNhanVien();
    }//GEN-LAST:event_mnuQLNVActionPerformed

    private void mnuQLDonMuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuQLDonMuonSachActionPerformed
        quanLyHoaDon();
    }//GEN-LAST:event_mnuQLDonMuonSachActionPerformed

    private void mnuThongKeSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThongKeSachActionPerformed
        thongKeSach();
    }//GEN-LAST:event_mnuThongKeSachActionPerformed

    private void mnuThongKeDonMuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThongKeDonMuonSachActionPerformed
        quanLyHoaDon();
    }//GEN-LAST:event_mnuThongKeDonMuonSachActionPerformed

    private void btnLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast1ActionPerformed
        // TODO add your handling code here:
        this.last();
    }//GEN-LAST:event_btnLast1ActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
        this.next();
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnPrev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrev1ActionPerformed
        // TODO add your handling code here:
        this.prev();
    }//GEN-LAST:event_btnPrev1ActionPerformed

    private void btnFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst1ActionPerformed
        // TODO add your handling code here:
        this.first();
    }//GEN-LAST:event_btnFirst1ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        this.row = tblQuanLyNV.getSelectedRow();
        int id = (int) tblQuanLyNV.getValueAt(this.row, 0);
        NhanVien s = nvDAO.selectById(id);
        if (MsgBox.confirm(this, "Bạn có chắc chắn xóa Sách này ?")) {
            try {
                nvDAO.delete(id);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblQuanLyNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLyNVMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblQuanLyNV.getSelectedRow();
            int id = (int) tblQuanLyNV.getValueAt(this.row, 0);
            NhanVien nv = nvDAO.selectById(id);
            this.setForm(nv);;
            tabs.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblQuanLyNVMouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
        this.openMainForm();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst1;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast1;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrev1;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JMenuItem mnuDangXuat;
    private javax.swing.JMenuItem mnuDoiMK;
    private javax.swing.JMenuItem mnuHoSo;
    private javax.swing.JMenuItem mnuQLDonMuonSach;
    private javax.swing.JMenuItem mnuQLNV;
    private javax.swing.JMenuItem mnuQLSach;
    private javax.swing.JMenuItem mnuThoat;
    private javax.swing.JMenuItem mnuThongKeDonMuonSach;
    private javax.swing.JMenuItem mnuThongKeSach;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblQuanLyNV;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    boolean validates() {
        if (txtHo.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập họ!");
            txtHo.requestFocus();
            return false;
        } else if (txtTen.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập tên!");
            txtTen.requestFocus();
            return false;
        } else if (txtNgaySinh.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập ngày sinh!");
            txtNgaySinh.requestFocus();
            return false;
        } else if (rdoNam.isSelected() == false && rdoNu.isSelected() == false) {
            MsgBox.alert(this, "Vui lòng chọn giới tính!");
            return false;
        } else if (txtCCCD.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập CCCD!");
            txtCCCD.requestFocus();
            return false;
        }
        if (txtEmail.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập email!");
            txtEmail.requestFocus();
        }
        if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", txtEmail.getText()))) {
            MsgBox.alert(this, "Vui lòng kiểm tra lại thông tin email!");
            return false;
        }
        return true;
    }
    
    private void openMainForm(){
        new ManHinhChinh_Admin().setVisible(true);
        this.dispose();
    }
}
