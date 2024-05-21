/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;

import java.awt.Color;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;


/**
 *
 * @author Aleja
 */
public class PantallaCambiarContra extends javax.swing.JFrame {
    String bbdd = "jdbc:hsqldb:hsql://localhost/";
    Connection con = null;
    
    public PantallaCambiarContra() {
        initComponents();
        try {
             Class.forName("org.hsqldb.jdbc.JDBCDriver");
             con = DriverManager.getConnection(bbdd, "SA", "SA");
        if (con!= null) {
             System.out.println("Connection created successfully");
        }else{
             System.out.println("Problem with creating connection");
        }
        } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace(System.out);
            }
    }
///////////////////////////////////////////////////////////
private void cambiarContrasena(String correo, String contrasenaActual, String nuevaContrasena1, String nuevaContrasena2) {
    if (!nuevaContrasena1.equals(nuevaContrasena2)) {
        JOptionPane.showMessageDialog(null, "Las nuevas contraseñas no coinciden.");
        nuevaContra2.setBorder(BorderFactory.createLineBorder(Color.RED));
        nuevaContra2.setForeground(Color.RED);
        return;
    }

    nuevaContra2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    nuevaContra1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    correoTEXT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    nuevaContra2.setForeground(Color.BLACK);
    nuevaContra1.setForeground(Color.BLACK);
    correoTEXT.setForeground(Color.BLACK);

    if (nuevaContrasena1.length() < 5) {
        JOptionPane.showMessageDialog(null, "La nueva contraseña debe tener al menos 5 caracteres.");
        nuevaContra2.setBorder(BorderFactory.createLineBorder(Color.RED));
        nuevaContra2.setForeground(Color.RED);
        return;
    }

    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
        actualizarContrasena(conec, correo, nuevaContrasena1);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}

private void actualizarContrasena(Connection conexion, String correo, String nuevaContrasena) {
    String consulta = "UPDATE usuarios SET clave = ? WHERE email = ?";
    
    try (java.sql.PreparedStatement statement = conexion.prepareStatement(consulta)) {
        statement.setString(1, nuevaContrasena);
        statement.setString(2, correo);
        
        int filasActualizadas = statement.executeUpdate();
        
        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Contraseña actualizada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña: " + e.getMessage());
    }
}
/* //comprobar al mismo tiempo si la nueva contraseña 1 y 2 es la misma pero que no sea igual a la contraseña que ya existe NO FUNCIONA //
private void cambiarContrasena(String correo, String contrasenaActual, String nuevaContrasena1, String nuevaContrasena2) {
    if (nuevaContrasena1.equals(contrasenaActual)) {
        JOptionPane.showMessageDialog(null, "No puede repetir la contraseña actual.");
        return;
    }

    if (nuevaContrasena1.equals(nuevaContrasena2)) {
        JOptionPane.showMessageDialog(null, "No puede repetir la contraseña anterior.");
        return;
    }


    if (!nuevaContrasena1.equals(nuevaContrasena2)) {
        JOptionPane.showMessageDialog(null, "Las nuevas contraseñas no coinciden.");
        nuevaContra2.setBorder(BorderFactory.createLineBorder(Color.RED));
        nuevaContra2.setForeground(Color.RED);
        return;
    }

  
    nuevaContra2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    nuevaContra2.setForeground(Color.BLACK);

    if (nuevaContrasena1.length() < 5) {
        JOptionPane.showMessageDialog(null, "La nueva contraseña debe tener al menos 5 caracteres.");
        return;
    }

 
    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
     
        String consulta = "SELECT clave FROM usuarios WHERE email = ?";
        try (java.sql.PreparedStatement statement = conec.prepareStatement(consulta)) {
            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String claveAlmacenada = resultSet.getString("clave");
                if (!contrasenaActual.equals(claveAlmacenada)) {
                    JOptionPane.showMessageDialog(null, "La contraseña actual no es correcta.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Correo no encontrado.");
                return;
            }
        }

       
        actualizarContrasena(conec, correo, nuevaContrasena1);
    } catch (SQLException e) {
     
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}

private void actualizarContrasena(Connection conexion, String correo, String nuevaContrasena) {
    
    String consulta = "UPDATE usuarios SET clave = ? WHERE email = ?";
    
    try (java.sql.PreparedStatement statement = conexion.prepareStatement(consulta)) {
        statement.setString(1, nuevaContrasena);
        statement.setString(2, correo);
        
        int filasActualizadas = statement.executeUpdate();
        
        if (filasActualizadas > 0) {
          
            JOptionPane.showMessageDialog(null, "Contraseña actualizada exitosamente.");
        } else {
           
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la contraseña.");
        }
    } catch (SQLException e) {
      
        JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña: " + e.getMessage());
    }
}
*/
    public void Reiniciar(){
        nuevaContra1.setText("");
        nuevaContra2.setText("");
        correoTEXT.setText("");
        nuevaContra2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nuevaContra1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        correoTEXT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nuevaContra2.setForeground(Color.BLACK);
        nuevaContra2.setForeground(Color.BLACK);
        nuevaContra1.setForeground(Color.BLACK);
        correoTEXT.setForeground(Color.BLACK);
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
        nuevaContra2 = new javax.swing.JTextField();
        cambiarContraBoton = new javax.swing.JButton();
        ReinicarBoton = new javax.swing.JButton();
        correoTEXT = new javax.swing.JTextField();
        nuevaContra1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cambiar Contraseña");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        nuevaContra2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nuevaContra2.setText("Repite la contraseña");
        nuevaContra2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaContra2MouseClicked(evt);
            }
        });
        nuevaContra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaContra2ActionPerformed(evt);
            }
        });
        jPanel1.add(nuevaContra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 210, -1));

        cambiarContraBoton.setText("Cambiar Contraseña");
        cambiarContraBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarContraBotonActionPerformed(evt);
            }
        });
        jPanel1.add(cambiarContraBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        ReinicarBoton.setText("Reiniciar");
        ReinicarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReinicarBotonActionPerformed(evt);
            }
        });
        jPanel1.add(ReinicarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        correoTEXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        correoTEXT.setText("Correo");
        correoTEXT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                correoTEXTMouseClicked(evt);
            }
        });
        correoTEXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoTEXTActionPerformed(evt);
            }
        });
        jPanel1.add(correoTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, -1));

        nuevaContra1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nuevaContra1.setText("Nueva Contraseña");
        nuevaContra1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaContra1MouseClicked(evt);
            }
        });
        nuevaContra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaContra1ActionPerformed(evt);
            }
        });
        jPanel1.add(nuevaContra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 210, -1));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aleja\\OneDrive\\Documentos\\NetBeansProjects\\NetBeansProjects\\ProyectoFinalAlejandro\\src\\imagenes\\imagenContraLogo.png")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaContra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaContra2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevaContra2ActionPerformed

    private void correoTEXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoTEXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoTEXTActionPerformed

    private void nuevaContra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaContra1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevaContra1ActionPerformed

    private void ReinicarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReinicarBotonActionPerformed
        Reiniciar();
    }//GEN-LAST:event_ReinicarBotonActionPerformed

    private void cambiarContraBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarContraBotonActionPerformed
    String correo = correoTEXT.getText();
    String contrasenaActual = nuevaContra1.getText();
    String nuevaContrasena = nuevaContra2.getText();
    cambiarContrasena(correo, contrasenaActual, contrasenaActual, nuevaContrasena);
    }//GEN-LAST:event_cambiarContraBotonActionPerformed

    private void correoTEXTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_correoTEXTMouseClicked
       correoTEXT.setText("");
    }//GEN-LAST:event_correoTEXTMouseClicked

    private void nuevaContra1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevaContra1MouseClicked
       nuevaContra1.setText("");
    }//GEN-LAST:event_nuevaContra1MouseClicked

    private void nuevaContra2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevaContra2MouseClicked
       nuevaContra2.setText("");
    }//GEN-LAST:event_nuevaContra2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaCambiarContra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaCambiarContra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaCambiarContra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaCambiarContra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaCambiarContra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ReinicarBoton;
    private javax.swing.JButton cambiarContraBoton;
    private javax.swing.JTextField correoTEXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nuevaContra1;
    private javax.swing.JTextField nuevaContra2;
    // End of variables declaration//GEN-END:variables
}
