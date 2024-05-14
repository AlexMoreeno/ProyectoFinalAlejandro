/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Aleja
 */
public class PantallaAlumnosAdministrador extends javax.swing.JFrame {
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        Connection con = null;
        private String[] datos;
        String nombre1 = "";
        int FilSelect ;
        Connection conet;
    public PantallaAlumnosAdministrador() {
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
        ActualizarTablaAlumnos(con);
    }
    public void ActualizarTablaAlumnos(Connection con) {
    try {
        String sql = "SELECT * FROM usuarios WHERE tipo = 'alumno'";
        java.sql.Statement statement = con.createStatement();
        ResultSet resultado = statement.executeQuery(sql);

        DefaultTableModel tmProfesores = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tmProfesores.addColumn("Id");
        tmProfesores.addColumn("Nombre");
        tmProfesores.addColumn("Apellidos");
        tmProfesores.addColumn("Email");
        tmProfesores.addColumn("Clave");
        tmProfesores.addColumn("Tipo");

        while (resultado.next()) {
            String id = resultado.getString("ID");
            String nombre = resultado.getString("NOMBRE");
            String apellidos = resultado.getString("APELLIDOS");
            String email = resultado.getString("EMAIL");
            String clave = resultado.getString("CLAVE");
            String tipo = resultado.getString("TIPO");

            Object[] fila = {id, nombre, apellidos, email, clave, tipo};
            tmProfesores.addRow(fila);
        }

        TablaAlumnos.setModel(tmProfesores);

        TableColumnModel columnModel = TablaAlumnos.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void registrarUsuario(JTextField nombreTEXT, JTextField ApellidoTEXT, JTextField correoTEXT, JTextField contraTEXT) {
      String nombre = nombreTEXT.getText();
      String apellido = ApellidoTEXT.getText();
      String correo = correoTEXT.getText();
      String clave = contraTEXT.getText();

      String tipo = "alumno"; // Tipo de usuario siempre será "alumno"

      if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || clave.isEmpty()) {
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

                  // Llamar a ActualizarTablaAlumnos
                  ActualizarTablaAlumnos(conec);
              } else {
                  JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
              }
          }
      } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
      }
  }
    ////////////////////////////////////////////////////////////////////////////////
        public void eliminarUsuario(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de la fila seleccionada
        String nombre = tabla.getValueAt(filaSeleccionada, 1).toString();
        String apellido = tabla.getValueAt(filaSeleccionada, 2).toString();
        String correo = tabla.getValueAt(filaSeleccionada, 3).toString();
        String clave = tabla.getValueAt(filaSeleccionada, 4).toString();

        try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
            String consulta = "DELETE FROM usuarios WHERE nombre = ? AND apellidos = ? AND email = ? AND clave = ? AND tipo = 'alumno'";

            try (java.sql.PreparedStatement statement = conec.prepareStatement(consulta)) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, correo);
                statement.setString(4, clave);

                int filasEliminadas = statement.executeUpdate();

                if (filasEliminadas > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");

                    // Mostrar ventana emergente con los datos del usuario eliminado
                    JOptionPane.showMessageDialog(null, "Nombre: " + nombre + "\nApellido: " + apellido + "\nCorreo: " + correo);

                    // Llamar a ActualizarTablaAlumnos
                    ActualizarTablaAlumnos(conec);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    private void cambiarColorCampo(JTextField campo, Color color) {
        campo.setBorder(BorderFactory.createLineBorder(color));
    }
    /////////////////////////////////////////////////////////////////////////////////////
    public void buscarUsuario(JTextField nombreTEXT, JTextField apellidoTEXT, JTextField emailTEXT, JTextField contraTEXT, JTable tabla) {
    String nombre = NombreTEXT.getText();
    String apellido = ApellidooTEXT.getText();
    String correo = EmailTEXT.getText();
    String clave = ContraTEXT.getText();

    String consulta = "SELECT * FROM usuarios WHERE 1=1";
    if (!nombre.isEmpty()) {
        consulta += " AND nombre LIKE '" + nombre + "%'";
    }
    if (!apellido.isEmpty()) {
        consulta += " AND apellidos LIKE '" + apellido + "%'";
    }
    if (!correo.isEmpty()) {
        consulta += " AND email LIKE '" + correo + "%'";
    }
    if (!clave.isEmpty()) {
        consulta += " AND clave LIKE '" + clave + "%'";
    }

    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA");
         Statement statement = conec.createStatement();
         ResultSet resultSet = statement.executeQuery(consulta)) {

        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        // Limpiar la tabla
        model.setRowCount(0);

        // Rellenar la tabla con los resultados de la consulta
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombreResultado = resultSet.getString("nombre");
            String apellidosResultado = resultSet.getString("apellidos");
            String emailResultado = resultSet.getString("email");
            String claveResultado = resultSet.getString("clave");
            String tipoResultado = resultSet.getString("tipo");

            model.addRow(new Object[]{id, nombreResultado, apellidosResultado, emailResultado, claveResultado, tipoResultado});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}
    public void Reiniciar(){
           EmailTEXT.setText("");
           NombreTEXT.setText("");
           ApellidooTEXT.setText("");
           ContraTEXT.setText("");
           ActualizarTablaAlumnos(con);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaAlumnos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ContraTEXT = new javax.swing.JTextField();
        NombreTEXT = new javax.swing.JTextField();
        ApellidooTEXT = new javax.swing.JTextField();
        EmailTEXT = new javax.swing.JTextField();
        BuscarBoton = new javax.swing.JButton();
        ReiniciarBoton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        EliminarBoton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        PantallasMenu = new javax.swing.JMenu();
        InicioDeSesion = new javax.swing.JMenuItem();
        administradorVER = new javax.swing.JMenuItem();
        Profesores = new javax.swing.JMenuItem();
        Materias = new javax.swing.JMenuItem();
        SalirMenu = new javax.swing.JMenuItem();
        AjustesMenu = new javax.swing.JMenu();
        CambiarContra = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumnos"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        TablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellidos", "Email", "Contraseña", "Tipo"
            }
        ));
        jScrollPane3.setViewportView(TablaAlumnos);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setText("Apellido");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jLabel3.setText("Email");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setText("Contraseña");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));
        jPanel2.add(ContraTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 320, -1));
        jPanel2.add(NombreTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 320, -1));
        jPanel2.add(ApellidooTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 320, -1));
        jPanel2.add(EmailTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 320, -1));

        BuscarBoton.setText("Buscar");
        BuscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(BuscarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        ReiniciarBoton.setText("Reiniciar");
        ReiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(ReiniciarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        EliminarBoton.setText("Eliminar");
        EliminarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(EliminarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, -1, -1));

        PantallasMenu.setText("Pantallas");

        InicioDeSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/login.png"))); // NOI18N
        InicioDeSesion.setText("Inicio de Sesion");
        InicioDeSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioDeSesionActionPerformed(evt);
            }
        });
        PantallasMenu.add(InicioDeSesion);

        administradorVER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/admin.png"))); // NOI18N
        administradorVER.setText("Administrador");
        administradorVER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administradorVERActionPerformed(evt);
            }
        });
        PantallasMenu.add(administradorVER);

        Profesores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/profesor.png"))); // NOI18N
        Profesores.setText("Profesores");
        Profesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfesoresActionPerformed(evt);
            }
        });
        PantallasMenu.add(Profesores);

        Materias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/Libros.png"))); // NOI18N
        Materias.setText("Materias");
        Materias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MateriasActionPerformed(evt);
            }
        });
        PantallasMenu.add(Materias);

        SalirMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos1/Salir (1).png"))); // NOI18N
        SalirMenu.setText("Salir");
        SalirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirMenuActionPerformed(evt);
            }
        });
        PantallasMenu.add(SalirMenu);

        jMenuBar1.add(PantallasMenu);

        AjustesMenu.setText("Ajustes");

        CambiarContra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos1/candado.png"))); // NOI18N
        CambiarContra.setText("Cambiar Contraseña");
        CambiarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarContraActionPerformed(evt);
            }
        });
        AjustesMenu.add(CambiarContra);

        jMenuBar1.add(AjustesMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InicioDeSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioDeSesionActionPerformed
        PantallaInicioSesión a = new PantallaInicioSesión();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_InicioDeSesionActionPerformed

    private void ProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfesoresActionPerformed
        PantallaProfesoresAdministrador a = new PantallaProfesoresAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ProfesoresActionPerformed

    private void SalirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirMenuActionPerformed

    private void CambiarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarContraActionPerformed
        PantallaCambiarContra a = new PantallaCambiarContra();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CambiarContraActionPerformed

    private void MateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MateriasActionPerformed
        PantallaMateriasAdministrador a = new PantallaMateriasAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MateriasActionPerformed

    private void administradorVERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administradorVERActionPerformed
        PantallaPrincipalAdministrador a = new PantallaPrincipalAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_administradorVERActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      registrarUsuario(NombreTEXT, ApellidooTEXT, EmailTEXT, ContraTEXT);
      ActualizarTablaAlumnos(con);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void EliminarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBotonActionPerformed
      eliminarUsuario(TablaAlumnos);
      ActualizarTablaAlumnos(con);
    }//GEN-LAST:event_EliminarBotonActionPerformed

    private void BuscarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBotonActionPerformed
    buscarUsuario(NombreTEXT, ApellidooTEXT, EmailTEXT,ContraTEXT, TablaAlumnos);
    }//GEN-LAST:event_BuscarBotonActionPerformed

    private void ReiniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarBotonActionPerformed
        Reiniciar();
    }//GEN-LAST:event_ReiniciarBotonActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaAlumnosAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaAlumnosAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaAlumnosAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaAlumnosAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaAlumnosAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjustesMenu;
    private javax.swing.JTextField ApellidooTEXT;
    private javax.swing.JButton BuscarBoton;
    private javax.swing.JMenuItem CambiarContra;
    private javax.swing.JTextField ContraTEXT;
    private javax.swing.JButton EliminarBoton;
    private javax.swing.JTextField EmailTEXT;
    private javax.swing.JMenuItem InicioDeSesion;
    private javax.swing.JMenuItem Materias;
    private javax.swing.JTextField NombreTEXT;
    private javax.swing.JMenu PantallasMenu;
    private javax.swing.JMenuItem Profesores;
    private javax.swing.JButton ReiniciarBoton;
    private javax.swing.JMenuItem SalirMenu;
    private javax.swing.JTable TablaAlumnos;
    private javax.swing.JMenuItem administradorVER;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
