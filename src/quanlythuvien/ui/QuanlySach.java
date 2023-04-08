/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlythuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import quanlythuvien.DAO.SachDAO;
import quanlythuvien.entity.Sach;
import quanlythuvien.util.MsgBox;
import quanlythuvien.util.XImage;

/**
 *
 * @author hoang
 */
public class QuanlySach extends javax.swing.JFrame {

    DefaultTableModel tblModel;
    int row = -1;
    SachDAO sDAO = new SachDAO();
    JFileChooser fileChooser = new JFileChooser();
    private String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Elib;user=sa;password=My27012003@";

    public QuanlySach() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Quản Lý Sách");
        initTable();
        loaddtheloaiToCombobox();
        loadngonguToCombobox();
        loadnxbToCombobox();
        this.fillTable();
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblQuanLySach.getModel();
        String[] nav = new String[]{"ID", "Tên Sách", "Tác Giả", "NXB", "Thể Loại", "Ngôn Ngữ", "Đơn Giá", "Vị Trí", "Số Lượng"};
        tblModel.setColumnIdentifiers(nav);
    }

    public void loaddtheloaiToCombobox() {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String sql = "Select TenLoai from THELOAI";
//            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cboTheLoai.addItem(rs.getString(1));
                cboDSHienThi.addItem(rs.getString(1));
            }

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadngonguToCombobox() {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String sql = "Select TenNN from NGONNGU";
//            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cboNgonNgu.addItem(rs.getString(1));
            }

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadnxbToCombobox() {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String sql = "Select TenNXB from NHAXUATBAN";
//            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cboNXB.addItem(rs.getString(1));
            }

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void clearForm() {
        txtMaS.setText("");
        txtTenS.setText("");
        txtTacGia.setText("");
        cboNXB.setSelectedIndex(0);
        cboTheLoai.setSelectedIndex(0);
        cboNgonNgu.setSelectedIndex(0);
        txtViTri.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        lblAnh.setIcon(null);

    }

    void setForm(Sach sa) {
        txtMaS.setText(String.valueOf(sa.getID()));
        txtTenS.setText(sa.getTenSach());
        txtTacGia.setText(sa.getTenTacGia());
        cboNXB.setSelectedItem(sa.getNXB());
        cboTheLoai.setSelectedItem(sa.getTheLoai());
        cboNgonNgu.setSelectedItem(sa.getNgonNgu());
        txtDonGia.setText(String.valueOf(sa.getDonGia()));
        txtViTri.setText(sa.getViTri());
        txtSoLuong.setText(String.valueOf(sa.getSoLuong()));
        if (sa.getAnh() != null) {
            lblAnh.setToolTipText(sa.getAnh());
            lblAnh.setIcon(XImage.read(sa.getAnh()));
        }

    }

    Sach getForm() {
        Sach sa = new Sach();
//        sa.setID(Integer.parseInt(txtMaS.getText()));
        sa.setTenSach(txtTenS.getText());
        sa.setTenTacGia(txtTacGia.getText());
        sa.setNXB(cboNXB.getSelectedItem().toString());
        sa.setTheLoai(cboTheLoai.getSelectedItem().toString());
        sa.setNgonNgu(cboNgonNgu.getSelectedItem().toString());
        sa.setDonGia(Double.parseDouble(txtDonGia.getText()));
        sa.setViTri(txtViTri.getText());
        sa.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        sa.setAnh(lblAnh.getToolTipText());
        return sa;
    }

    Sach getFormUp() {
        Sach sa = new Sach();
        sa.setID(Integer.parseInt(txtMaS.getText()));
        sa.setTenSach(txtTenS.getText());
        sa.setTenTacGia(txtTacGia.getText());
        sa.setNXB(cboNXB.getSelectedItem().toString());
        sa.setTheLoai(cboTheLoai.getSelectedItem().toString());
        sa.setNgonNgu(cboNgonNgu.getSelectedItem().toString());
        sa.setDonGia(Double.parseDouble(txtDonGia.getText()));
        sa.setViTri(txtViTri.getText());
        sa.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        sa.setAnh(lblAnh.getToolTipText());
        return sa;
    }

    void edit() {
        int id = (int) tblQuanLySach.getValueAt(this.row, 0);
        Sach s = sDAO.selectById(id);
        this.setForm(s);

    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblQuanLySach.getModel();
        model.setRowCount(0);
        try {
            List<Sach> list = sDAO.selectAll();
            for (Sach s : list) {
                Object[] row = {
                    s.getID(),
                    s.getTenSach(),
                    s.getTenTacGia(),
                    s.getNXB(),
                    s.getTheLoai(),
                    s.getNgonNgu(),
                    s.getDonGia(),
                    s.getViTri(),
                    s.getSoLuong(),
                    s.getAnh()
                };
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
                Sach s = getForm();
                sDAO.insert(s);
                this.fillTable();
                this.clearForm();
//                txtMaS.requestFocus();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                e.printStackTrace();
            }
        }

    }

    void update() {
        if (txtDonGia.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập Đơn giá");
            txtDonGia.requestFocus();
        } else {
            Sach s = getFormUp();
            try {
                sDAO.update(s);
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

        int id = Integer.parseInt(txtMaS.getText());
        if (MsgBox.confirm(this, "Bạn có chắc chắn xóa Sách này ?")) {
            try {
                sDAO.delete(id);
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
        if (this.row < tblQuanLySach.getRowCount() - 1) {
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
        this.row = tblQuanLySach.getRowCount() - 1;
        this.edit();
        this.updateInfo();
    }

    void updateInfo() {
        tblQuanLySach.setRowSelectionInterval(row, row);
        this.edit();
//        lblBanGhi.setText(ThongTinBanGhi());
    }

    void timSach() {

        if (txtTimKiem.getText().equalsIgnoreCase("")) {
            MsgBox.alert(this, "Vui lòng nhập sách cần tìm!");
        } else {
            DefaultTableModel model = (DefaultTableModel) tblQuanLySach.getModel();
            model.setRowCount(0);
            try {
                String timKiem = txtTimKiem.getText();
                List<Sach> list = sDAO.selectByKeyword(timKiem);
                for (Sach s : list) {
                    Object[] row = {
                        s.getID(),
                        s.getTenSach(),
                        s.getTenTacGia(),
                        s.getNXB(),
                        s.getTheLoai(),
                        s.getNgonNgu(),
                        s.getDonGia(),
                        s.getViTri(),
                        s.getSoLuong(),
                        s.getAnh()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
                e.printStackTrace();
            }

        }

    }

    void chooseImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblAnh.setIcon(icon);
            lblAnh.setToolTipText(file.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLySach = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cboDSHienThi = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaS = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTenS = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cboNXB = new javax.swing.JComboBox<>();
        cboTheLoai = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboNgonNgu = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtViTri = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRE = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("TÌM SÁCH");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        txtTimKiem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 170, 30));

        tblQuanLySach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLySach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLySachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuanLySach);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 610, 220));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, 80));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, 80));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, 80));

        jButton3.setBackground(new java.awt.Color(117, 76, 36));
        jButton3.setForeground(new java.awt.Color(255, 206, 41));
        jButton3.setText("QUAY LẠI");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 110, 40));

        jButton2.setBackground(new java.awt.Color(117, 76, 36));
        jButton2.setForeground(new java.awt.Color(255, 206, 41));
        jButton2.setText("XÓA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 90, 40));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-books.png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 60, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-staff.png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-bill.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/search3.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 40, 30));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/First.png"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 32, 32));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Prev.png"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 32, 32));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Next.png"))); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 32, 32));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Last.png"))); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 32, 32));

        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\DuAn1\\QuanLyThuVien\\QuanLyThuVien\\src\\quanlythuvien\\icon\\FiveO - ELib Low Opacity.png")); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 438));

        cboDSHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hiển Thị Theo", "ALL" }));
        cboDSHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDSHienThiActionPerformed(evt);
            }
        });
        jPanel1.add(cboDSHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 130, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\DuAn1\\QuanLyThuVien\\QuanLyThuVien\\src\\quanlythuvien\\icon\\FiveO - ELib Low Opacity.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 500, 438));

        jToolBar1.add(jPanel1);

        tabs.addTab("Danh Sách", jToolBar1);

        jToolBar2.setRollover(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("THÔNG TIN SÁCH");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 210, -1));

        jLabel11.setText("ID:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtMaS.setEditable(false);
        jPanel2.add(txtMaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, -1));

        jLabel13.setText("Tên sách:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
        jPanel2.add(txtTenS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 210, -1));

        jLabel14.setText("Tác giả:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));
        jPanel2.add(txtTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 210, -1));

        jLabel15.setText("NXB:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        cboNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Nhà Xuất Bản" }));
        jPanel2.add(cboNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 210, -1));

        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Thể Loại" }));
        jPanel2.add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 210, -1));

        jLabel16.setText("Thể loại:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jLabel17.setText("Ngôn Ngữ:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        cboNgonNgu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Ngôn Ngữ" }));
        jPanel2.add(cboNgonNgu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 210, -1));

        jLabel18.setText("Đơn giá:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));
        jPanel2.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 210, -1));

        jLabel19.setText("Vị trí:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));
        jPanel2.add(txtViTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 210, -1));

        jLabel20.setText("Số Lượng:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 110, 160));
        jPanel2.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 210, -1));

        jButton4.setText("Chọn Ảnh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, -1, -1));

        btnSave.setBackground(new java.awt.Color(117, 76, 36));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 206, 41));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 140, 30));

        btnRE.setBackground(new java.awt.Color(117, 76, 36));
        btnRE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRE.setForeground(new java.awt.Color(255, 206, 41));
        btnRE.setText("REFRESH");
        btnRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREActionPerformed(evt);
            }
        });
        jPanel2.add(btnRE, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 140, 30));

        btnUpdate.setBackground(new java.awt.Color(117, 76, 36));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 206, 41));
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 140, 30));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/First.png"))); // NOI18N
        jButton12.setBorderPainted(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 32, 32));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Prev.png"))); // NOI18N
        jButton13.setBorderPainted(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 32, 32));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Next.png"))); // NOI18N
        jButton14.setBorderPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 32, 32));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Last.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 32, 32));

        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\DuAn1\\QuanLyThuVien\\QuanLyThuVien\\src\\quanlythuvien\\icon\\FiveO - ELib Low Opacity.png")); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 438));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel25.setForeground(new java.awt.Color(255, 206, 41));
        jLabel25.setText("E");

        jLabel3.setForeground(new java.awt.Color(255, 206, 41));
        jLabel3.setText("L");

        jLabel26.setForeground(new java.awt.Color(255, 206, 41));
        jLabel26.setText("I");

        jLabel24.setForeground(new java.awt.Color(255, 206, 41));
        jLabel24.setText("B");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 100, 160));

        jLabel7.setIcon(new javax.swing.ImageIcon("D:\\DuAn1\\QuanLyThuVien\\QuanLyThuVien\\src\\quanlythuvien\\icon\\FiveO - ELib Low Opacity.png")); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 500, 438));

        jToolBar2.add(jPanel2);

        tabs.addTab("Cập Nhật", jToolBar2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/system.png"))); // NOI18N
        jMenu1.setText("Hệ Thống");
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage 3.png"))); // NOI18N
        jMenu2.setText("Quản Lý");
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statics view 3.png"))); // NOI18N
        jMenu3.setText("Thống Kê");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        chooseImage();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        this.insert();
        this.clearForm();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREActionPerformed
        // TODO add your handling code here:
        this.clearForm();
        this.editText1();
    }//GEN-LAST:event_btnREActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        this.update();
        this.clearForm();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblQuanLySachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLySachMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblQuanLySach.getSelectedRow();
            int id = (int) tblQuanLySach.getValueAt(this.row, 0);
            Sach s = sDAO.selectById(id);
            this.setForm(s);;
            this.editText();
            tabs.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblQuanLySachMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.first();
        this.editText();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.prev();
        this.editText();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        this.next();
        this.editText();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        this.last();
        this.editText();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        this.first();
        this.editText();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        this.prev();
        this.editText();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        this.next();
        this.editText();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        this.last();
        this.editText();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        this.row = tblQuanLySach.getSelectedRow();
        int id = (int) tblQuanLySach.getValueAt(this.row, 0);
        Sach s = sDAO.selectById(id);
        if (MsgBox.confirm(this, "Bạn có chắc chắn xóa Sách này ?")) {
            try {
                sDAO.delete(id);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.timSach();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboDSHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDSHienThiActionPerformed
        // TODO add your handling code here:
        if (cboDSHienThi.getSelectedIndex() == 1) {
            this.fillTable();
        } else {
//            this.chonTheLoai();
            this.fill();
            this.row = 0;
        }
    }//GEN-LAST:event_cboDSHienThiActionPerformed

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
            java.util.logging.Logger.getLogger(QuanlySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanlySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanlySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanlySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanlySach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRE;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboDSHienThi;
    private javax.swing.JComboBox<String> cboNXB;
    private javax.swing.JComboBox<String> cboNgonNgu;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblQuanLySach;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaS;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenS;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtViTri;
    // End of variables declaration//GEN-END:variables

    boolean validates() {
        if (txtTenS.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập Tên sách!");
            txtTenS.requestFocus();
            return false;
        } else if (txtTacGia.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập Tác Giả!");
            txtTacGia.requestFocus();
            return false;
        } else if (cboNXB.getSelectedIndex() == 0) {
            MsgBox.alert(this, "Vui lòng chọn NXB!");
            cboNXB.requestFocus();
            return false;
        } else if (cboTheLoai.getSelectedIndex() == 0) {
            MsgBox.alert(this, "Vui lòng chọn Thể Loại!");
            cboTheLoai.requestFocus();
            return false;
        } else if (cboNgonNgu.getSelectedIndex() == 0) {
            MsgBox.alert(this, "Vui lòng chọn Ngôn Ngữ!");
            cboNgonNgu.requestFocus();
            return false;
        } else if (txtViTri.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập Vị Trí!");
            txtViTri.requestFocus();
            return false;
        } else if (txtDonGia.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập Đợn Giá!");
            txtDonGia.requestFocus();
            return false;
        } else if (Double.valueOf(txtDonGia.getText()) < 0.0 || Double.valueOf(txtDonGia.getText()) == 0.0) {
            MsgBox.alert(this, "Đơn giá không hợp lệ!");
            txtDonGia.requestFocus();
            return false;
        } else if (txtSoLuong.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng nhập Số Lượng!");
            txtSoLuong.requestFocus();
            return false;
        } else if (Double.valueOf(txtSoLuong.getText()) < 0.0 || Double.valueOf(txtSoLuong.getText()) == 0.0) {
            MsgBox.alert(this, "Đơn giá không hợp lệ!");
            txtSoLuong.requestFocus();
            return false;
        }

        return true;
    }

    void editText() {
        boolean edit = (this.row >= 0);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        txtMaS.setEditable(!edit);
        txtTenS.setEditable(!edit);
        btnSave.setEnabled(!edit);
        btnUpdate.setEnabled(edit);
        txtMaS.setFont(f);
        txtTenS.setFont(f);
        txtMaS.setForeground(Color.red);
        txtTenS.setForeground(Color.red);
    }

    void editText1() {
        boolean edit = (this.row >= 0);
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        txtTenS.setEditable(edit);
        btnSave.setEnabled(edit);
        btnUpdate.setEnabled(!edit);
        txtMaS.setFont(f);
        txtTenS.setFont(f);
        txtMaS.setForeground(Color.black);
        txtTenS.setForeground(Color.black);
    }

    void fill() {
        DefaultTableModel model = (DefaultTableModel) tblQuanLySach.getModel();
        model.setRowCount(0);
        try {
            String loai = (String) cboDSHienThi.getSelectedItem();
            List<Sach> list = sDAO.selectByTheLoai(loai);
            for (Sach s : list) {
                Object[] row = {
                    s.getID(),
                    s.getTenSach(),
                    s.getTenTacGia(),
                    s.getNXB(),
                    s.getTheLoai(),
                    s.getNgonNgu(),
                    s.getDonGia(),
                    s.getViTri(),
                    s.getSoLuong(),
                    s.getAnh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }
//        void chonTheLoai(){
//            Sach s = (Sach) cboDSHienThi.getSelectedItem();
//            this.fill();
//            this.row = 0;
//        }
}
