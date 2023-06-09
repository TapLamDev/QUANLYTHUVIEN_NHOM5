/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlythuvien.ui;

/**
 *
 * @author phamb
 */
public class XoaTaiKhoan extends javax.swing.JFrame {

    /**
     * Creates new form XoaTaiKhoan
     */
    public XoaTaiKhoan() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Xóa Tài Khoản");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(117, 76, 36));
        jLabel5.setText("Vui lòng chọn 1 trong những lý do dưới đây:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 380, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/back 2.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/user manual 2.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 60, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/history 2.png"))); // NOI18N
        jLabel7.setText("jLabel3");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 60, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/user 2.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 70, -1));

        jCheckBox1.setText("Lý do khác");
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jCheckBox2.setText("Tôi đã có tài khoản khác");
        getContentPane().add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jCheckBox3.setText("Tôi không muốn sử dụng ứng dụng nữa");
        getContentPane().add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 380, 100));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/Mascot.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 500, 110, 110));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/text bubble 1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 440, 160));

        jButton5.setBackground(new java.awt.Color(117, 76, 36));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 206, 41));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/done 2.png"))); // NOI18N
        jButton5.setText("XÁC NHẬN XÓA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 170, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 520, 500));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/FiveO - ELib Low Opacity.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 500));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/system.png"))); // NOI18N
        jMenu1.setText("Hệ thống");
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/help.png"))); // NOI18N
        jMenu2.setText("Trợ giúp");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XoaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XoaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XoaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XoaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XoaTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
