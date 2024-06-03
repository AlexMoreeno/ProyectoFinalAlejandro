/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;

/**
 *
 * @author Aleja
 */
public class PantallaProfesoresElegir extends javax.swing.JFrame {

    /**
     * Creates new form PantallaProfesoresElegir
     */
    public PantallaProfesoresElegir() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        AceptarProfeBoton = new javax.swing.JButton();
        PantallaProfesorBoton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones "));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aceptar Profesores");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel1.setMaximumSize(new java.awt.Dimension(59, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(59, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(59, 20));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Alumnos");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel2.setMaximumSize(new java.awt.Dimension(59, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(59, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(59, 20));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 170, 110));

        AceptarProfeBoton.setText("Acceder");
        AceptarProfeBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarProfeBotonActionPerformed(evt);
            }
        });
        jPanel1.add(AceptarProfeBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, 30));

        PantallaProfesorBoton.setText("Acceder");
        PantallaProfesorBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaProfesorBotonActionPerformed(evt);
            }
        });
        jPanel1.add(PantallaProfesorBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 170, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aleja\\OneDrive\\Documentos\\NetBeansProjects\\NetBeansProjects\\ProyectoFinalAlejandro\\src\\iconosPropios\\iconoElegir.png")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarProfeBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarProfeBotonActionPerformed
      PantallaAceptarProfesoresAdministrador a = new PantallaAceptarProfesoresAdministrador();
      a.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_AceptarProfeBotonActionPerformed

    private void PantallaProfesorBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaProfesorBotonActionPerformed
      PantallaProfesoresAdministrador a = new PantallaProfesoresAdministrador();
      a.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_PantallaProfesorBotonActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaProfesoresElegir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaProfesoresElegir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaProfesoresElegir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaProfesoresElegir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaProfesoresElegir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AceptarProfeBoton;
    private javax.swing.JButton PantallaProfesorBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
