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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import quanlythuvien.DAO.SachDAO;
import quanlythuvien.entity.Sach;
import quanlythuvien.util.MsgBox;
import quanlythuvien.util.XImage;

/**
 *
 * @author phamb
 */
public class ThongTinSach extends javax.swing.JFrame {

    int row = -1;
    SachDAO sDAO = new SachDAO();
    JFileChooser fileChooser = new JFileChooser();

    private String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Elib;user=sa;password=My27012003@";

    public ThongTinSach() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Thông Tin Sách");
        loaddtheloaiToCombobox();
        loadngonguToCombobox();
        loadnxbToCombobox();
    }

    public void loaddtheloaiToCombobox() {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String sql = "Select TenLoai from THELOAI";
//            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cboTheLoai.addItem(rs.getString(1));
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
        txtMaS.setText(sa.getMaSach());
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
        sa.setMaSach(txtMaS.getText());
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

    void insert() {
        Sach sa = getForm();
        try {
            sDAO.insert(sa);
            this.clearForm();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại! ");
        }

    }

    void update() {
        Sach sa = getForm();
        try {
            sDAO.update(sa);
            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại");
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

//    void delete(){
//        if(!Auth.isManager()){
//            MsgBox.alert(this, "Bạn không có quyền xoá nhân viên này");
//        }else{
//            String maSach = txtMaS.getText();
//            if(MsgBox.confirm(this, "Bạn thực sự muốn xoá sách này?")){
//                sDAO.delete(maSach);
//                clearForm();
//                MsgBox.alert(this, "Xoá thành công");
//            }
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        txtMaS = new javax.swing.JTextField();
        txtTenS = new javax.swing.JTextField();
        txtTacGia = new javax.swing.JTextField();
        txtViTri = new javax.swing.JTextField();
        cboTheLoai = new javax.swing.JComboBox<>();
        lblAnh = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboNgonNgu = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cboNXB = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/back 2.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-books.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, 80));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-staff.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, 80));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage-bill.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, 80));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("THÔNG TIN SÁCH");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 210, -1));

        jLabel4.setText("Mã sách:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel7.setText("Tên sách:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel5.setText("Tác giả:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jLabel6.setText("NXB:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jLabel8.setText("Đơn giá:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, -1, -1));

        jLabel9.setText("Thể loại:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));

        jLabel12.setText("Vị trí:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, -1));
        getContentPane().add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 210, -1));
        getContentPane().add(txtMaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 210, -1));
        getContentPane().add(txtTenS, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 210, -1));
        getContentPane().add(txtTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 210, -1));
        getContentPane().add(txtViTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 210, -1));

        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Thể Loại" }));
        getContentPane().add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 210, -1));

        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 130, 160));

        jButton5.setBackground(new java.awt.Color(117, 76, 36));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 206, 41));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/done 2.png"))); // NOI18N
        jButton5.setText("XÁC NHẬN");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 140, 30));

        jButton1.setText("Chọn Ảnh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, -1));

        jButton6.setBackground(new java.awt.Color(117, 76, 36));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 206, 41));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/return.png"))); // NOI18N
        jButton6.setText("TRỞ LẠI");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 140, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 110, -1));

        cboNgonNgu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Ngôn Ngữ" }));
        getContentPane().add(cboNgonNgu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 210, -1));

        jLabel17.setText("Ngôn Ngữ:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel18.setText("Số Lượng:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, -1));
        getContentPane().add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 210, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 500, 100));

        cboNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Nhà Xuất Bản" }));
        getContentPane().add(cboNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 210, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        jLabel2.setText("âcsdasd");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 500, 440));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/system.png"))); // NOI18N
        jMenu1.setText("Hệ thống");
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/manage 3.png"))); // NOI18N
        jMenu2.setText("Quản lý");
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statics view 3.png"))); // NOI18N
        jMenu3.setText("Thống kê");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        chooseImage();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboNXB;
    private javax.swing.JComboBox<String> cboNgonNgu;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaS;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenS;
    private javax.swing.JTextField txtViTri;
    // End of variables declaration//GEN-END:variables
}
