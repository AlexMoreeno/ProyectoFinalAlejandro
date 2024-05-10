/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Aleja
 */
public class PantallaRegistrar extends javax.swing.JFrame {
    String bbdd = "jdbc:hsqldb:hsql://localhost/";
    Connection con = null;
    
    public PantallaRegistrar() {
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
     public void registrarUsuario(JTextField nombreTEXT, JTextField ApellidoTEXT, JTextField correoTEXT, JTextField contraTEXT, ButtonGroup TipoUsuario) {
        String nombre = nombreTEXT.getText();
        String apellido = ApellidoTEXT.getText();
        String correo = correoTEXT.getText();
        String clave = contraTEXT.getText();

        String tipo = "";
        for (Enumeration<AbstractButton> buttons = TipoUsuario.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                tipo = button.getActionCommand().toLowerCase();
                break;
            }
        }

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || clave.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        
            if (nombre.isEmpty()) {
                cambiarColorCampo(nombreTEXT, Color.RED);
            }
            if (apellido.isEmpty()) {
                cambiarColorCampo(ApellidoTEXT, Color.RED);
            }
            if (correo.isEmpty()) {
                cambiarColorCampo(correoTEXT, Color.RED);
            }
            if (clave.isEmpty()) {
                cambiarColorCampo(contraTEXT, Color.RED);
            }
            if (tipo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el tipo de usuario (alumno o profesor).", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
            String consulta = "INSERT INTO usuarios (nombre, apellidos, email, clave, tipo) VALUES (?, ?, ?, ?, ?)";

            try (java.sql.PreparedStatement statement = conec.prepareStatement(consulta)) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, correo);
                statement.setString(4, clave);
                statement.setString(5, tipo);

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }


    private void cambiarColorCampo(JTextField campo, Color color) {
        campo.setBorder(BorderFactory.createLineBorder(color));
    }

    public void Reiniciar(){
        correoTEXT.setText("");
        nombreTEXT.setText("");
        ApellidoTEXT.setText("");
        contraTEXT.setText("");
        TipoUsuario.clearSelection();
        //prueba

        cambiarColorCampo(correoTEXT, Color.BLACK);
        cambiarColorCampo(nombreTEXT, Color.BLACK);
        cambiarColorCampo(ApellidoTEXT, Color.BLACK);
        cambiarColorCampo(contraTEXT, Color.BLACK);
    }
    public void Prueba(){
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipoUsuario = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        correoTEXT = new javax.swing.JTextField();
        nombreTEXT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ApellidoTEXT = new javax.swing.JTextField();
        Alumno = new javax.swing.JRadioButton();
        Profesor = new javax.swing.JRadioButton();
        contraTEXT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        correoTEXT.setText("Correo electronico");
        correoTEXT.setPreferredSize(new java.awt.Dimension(115, 20));
        jPanel1.add(correoTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 330, -1));

        nombreTEXT.setText("Nombre");
        nombreTEXT.setPreferredSize(new java.awt.Dimension(115, 20));
        jPanel1.add(nombreTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, -1));

        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jButton2.setText("Reiniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 120, 20));

        ApellidoTEXT.setText("Apellido");
        ApellidoTEXT.setPreferredSize(new java.awt.Dimension(115, 20));
        jPanel1.add(ApellidoTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 330, -1));

        TipoUsuario.add(Alumno);
        Alumno.setText("Alumno");
        jPanel1.add(Alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        TipoUsuario.add(Profesor);
        Profesor.setText("Profesor");
        jPanel1.add(Profesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        contraTEXT.setText("Contraseña");
        contraTEXT.setPreferredSize(new java.awt.Dimension(115, 20));
        jPanel1.add(contraTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 330, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Reiniciar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    registrarUsuario(nombreTEXT, ApellidoTEXT, correoTEXT, contraTEXT, TipoUsuario);
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
            java.util.logging.Logger.getLogger(PantallaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaRegistrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Alumno;
    private javax.swing.JTextField ApellidoTEXT;
    private javax.swing.JRadioButton Profesor;
    private javax.swing.ButtonGroup TipoUsuario;
    private javax.swing.JTextField contraTEXT;
    private javax.swing.JTextField correoTEXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreTEXT;
    // End of variables declaration//GEN-END:variables


}
