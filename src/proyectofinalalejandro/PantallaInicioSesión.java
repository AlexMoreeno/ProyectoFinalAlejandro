/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aleja
 */
public class PantallaInicioSesión extends javax.swing.JFrame {
    String bbdd = "jdbc:hsqldb:hsql://localhost/";
    Connection con = null;
   
    public PantallaInicioSesión() {
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
////////////////////////////////////////////////////////////////////
 private void intentoIniciarSesion1() {
        
        String correo = emailTEXT.getText();
        String contrasena = new String(contraTEXT.getPassword());

        
        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el correo electrónico y la contraseña ");
            return;
        }

        iniciarSesion(correo, contrasena);
    }
    private void iniciarSesion(String correo, String contrasena) {
    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
        
        // Primero, comprobar si el correo y contraseña están en la tabla profesoresNOACEPTADOS
        String consultaNoAceptados = "SELECT id FROM profesoresNOACEPTADOS WHERE email = '" + correo + "' AND clave = '" + contrasena + "'";
        try (Statement statementNoAceptados = conec.createStatement()) {
            ResultSet resultSetNoAceptados = statementNoAceptados.executeQuery(consultaNoAceptados);
            
            if (resultSetNoAceptados.next()) {
                // Si existe en profesoresNOACEPTADOS, mostrar mensaje y salir
                JOptionPane.showMessageDialog(null, "Espere ser admitido.");
                return;
            }
        }

        // Si no está en profesoresNOACEPTADOS, comprobar en usuarios
        String consultaUsuarios = "SELECT id, nombre, apellidos, tipo FROM usuarios WHERE email = '" + correo + "' AND clave = '" + contrasena + "'";
        try (Statement statementUsuarios = conec.createStatement()) {
            ResultSet resultSetUsuarios = statementUsuarios.executeQuery(consultaUsuarios);
            
            if (resultSetUsuarios.next()) {
                int idUsuario = resultSetUsuarios.getInt("id");
                String nombre = resultSetUsuarios.getString("nombre");
                String apellidos = resultSetUsuarios.getString("apellidos");
                String tipo = resultSetUsuarios.getString("tipo");
                
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso \nNombre: " + nombre + " " + apellidos + "\nTipo: " + tipo);
                
                switch (tipo) {
                    case "alumno":
                        abrirAlumnoPantalla();
                        break;
                    case "profesor":
                        abrirProfesorPantalla();
                        break;
                    case "admin":
                        abrirAdminPantalla();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}

private void abrirAlumnoPantalla() {
        PantallaPrincipalAlumno a = new PantallaPrincipalAlumno();
        a.setVisible(true);
        this.dispose();
}

private void abrirProfesorPantalla() {
        PantallaPrincipalProfesor a = new PantallaPrincipalProfesor();
        a.setVisible(true);
        this.dispose();
}

private void abrirAdminPantalla() {
       PantallaPrincipalAdministrador a = new PantallaPrincipalAdministrador();
        a.setVisible(true);
        this.dispose();
}
public void Reiniciar(){
        emailTEXT.setText("");
        contraTEXT.setText("");
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
        emailTEXT = new javax.swing.JTextField();
        inicioSe = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        contraTEXT = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emailTEXT.setText("Escribe tu email");
        emailTEXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTEXTFocusLost(evt);
            }
        });
        emailTEXT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailTEXTMouseClicked(evt);
            }
        });
        jPanel1.add(emailTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 330, 30));

        inicioSe.setText("Iniciar Sesión");
        inicioSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSeActionPerformed(evt);
            }
        });
        jPanel1.add(inicioSe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jButton2.setText("Reiniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DAME CLASES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 210, -1));

        contraTEXT.setText("jPasswordField1");
        jPanel1.add(contraTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 330, 30));

        jButton1.setText("Registrate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/good_LogoSINFONDO.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 170, 170));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailTEXTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailTEXTMouseClicked
      
    }//GEN-LAST:event_emailTEXTMouseClicked

    private void emailTEXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTEXTFocusLost
       
    }//GEN-LAST:event_emailTEXTFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Reiniciar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inicioSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSeActionPerformed
       intentoIniciarSesion1();
    }//GEN-LAST:event_inicioSeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PantallaRegistrar a = new PantallaRegistrar();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       try {
          //  UIManager.setLookAndFeel( new FlatLightLaf() );
            FlatArcDarkIJTheme.setup();
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaInicioSesión().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contraTEXT;
    private javax.swing.JTextField emailTEXT;
    private javax.swing.JButton inicioSe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

   
}
