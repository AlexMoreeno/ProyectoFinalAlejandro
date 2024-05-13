/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;



import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Aleja
 */
public class PantallaPrincipalAlumno extends javax.swing.JFrame {

    String bbdd = "jdbc:hsqldb:hsql://localhost/";
    Connection con = null;
    private String[] datos;
    String nombre1 = "";
    int FilSelect ;
    Connection conet;
   
    public PantallaPrincipalAlumno() {
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
        actualizarTable(con);
    }
    public void actualizarTable(Connection con) {
    DefaultTableModel tm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    TablaProfesoresContratados.setModel(tm);

    try {
        String sql = "SELECT * FROM profesorescontratados";
        java.sql.Statement statement = con.createStatement();
        var resultado = statement.executeQuery(sql);

        // Agregar nombres de columnas al modelo de la tabla
        tm.addColumn("Id");
        tm.addColumn("Nombre");
        tm.addColumn("Apellidos");
        tm.addColumn("Email");
        tm.addColumn("Clave");
        tm.addColumn("Tipo");

        // Iterar sobre el resultado y agregar filas al modelo de la tabla
        while(resultado.next()) {
            String id = resultado.getString("ID");
            String nombre = resultado.getString("NOMBRE");
            String apellidos = resultado.getString("APELLIDOS");
            String email = resultado.getString("EMAIL");
            String clave = resultado.getString("CLAVE");
            String tipo = resultado.getString("TIPO");

            // Agregar fila al modelo de la tabla
            Object[] fila = {id, nombre, apellidos, email, clave, tipo};
            tm.addRow(fila);
        }

        // Ocultar las columnas de Id, Clave y Tipo
        TableColumnModel columnModel = TablaProfesoresContratados.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(4).setMinWidth(0);
        columnModel.getColumn(4).setMaxWidth(0);
        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);


    } catch(Exception e) {
        e.printStackTrace();
    }
}
    public void eliminaYAñadir(Connection con) {
    int filaSeleccionada = TablaProfesoresContratados.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        try {
            // Obtener los datos de la fila seleccionada, teniendo en cuenta las columnas ocultas
            String id = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 0);
            String nombre = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 1);
            String apellidos = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 2);
            String email = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 3);
            String clave = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 4);
            String tipo = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 5);
            
            System.out.println("Nombre: " + nombre + ", Apellidos: " + apellidos + ", Email: " + email);

            // Insertar en la tabla de usuarios sin incluir las columnas ocultas
            String sqlInsert = "INSERT INTO usuarios (nombre, apellidos, email, clave, tipo) VALUES (?, ?, ?, ?, ?)";
                try (var insertStatement = con.prepareStatement(sqlInsert)) {
                    insertStatement.setString(1, nombre);
                    insertStatement.setString(2, apellidos);
                    insertStatement.setString(3, email);
                    insertStatement.setString(4, clave);
                    insertStatement.setString(5, tipo);
                    int rowsInserted = insertStatement.executeUpdate();
                    System.out.println("Filas insertadas en usuarios: " + rowsInserted);
                }

            // Eliminar la fila de la tabla de profesorescontratados
            String sqlDelete = "DELETE FROM profesorescontratados WHERE id = ? AND nombre = ? AND apellidos = ? AND email = ? AND clave = ? AND tipo = ?";
                try (var deleteStatement = con.prepareStatement(sqlDelete)) {
                    deleteStatement.setString(1, id);
                    deleteStatement.setString(2, nombre);
                    deleteStatement.setString(3, apellidos);
                    deleteStatement.setString(4, email);
                    deleteStatement.setString(5, clave);
                    deleteStatement.setString(6, tipo);
                    int rowsDeleted = deleteStatement.executeUpdate();
                    System.out.println("Filas eliminadas de profesorescontratados: " + rowsDeleted);
                }

            // Actualizar la tabla después de realizar los cambios
            actualizarTable(con);
            JOptionPane.showMessageDialog(null, "Se ha eliminado al profesor de tu lista", "Profesor Eliminado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProfesoresContratados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        DesapuntarseBoton = new javax.swing.JButton();
        EnocntrarProfesorr = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        PantallasMenu = new javax.swing.JMenu();
        InicioDeSesion = new javax.swing.JMenuItem();
        EncontrarProfesores = new javax.swing.JMenuItem();
        SalirMenu = new javax.swing.JMenuItem();
        AjustesMenu = new javax.swing.JMenu();
        CambiarContra = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(" Profesores Contratados"));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        TablaProfesoresContratados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TablaProfesoresContratados);

        jPanel1.add(jScrollPane2);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        DesapuntarseBoton.setText("Desapuntarse");
        DesapuntarseBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesapuntarseBotonActionPerformed(evt);
            }
        });
        jPanel2.add(DesapuntarseBoton, new java.awt.GridBagConstraints());

        EnocntrarProfesorr.setText("Encontrar Profesor");
        EnocntrarProfesorr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnocntrarProfesorrActionPerformed(evt);
            }
        });
        jPanel2.add(EnocntrarProfesorr, new java.awt.GridBagConstraints());

        PantallasMenu.setText("Pantallas");

        InicioDeSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/login.png"))); // NOI18N
        InicioDeSesion.setText("Inicio de Sesion");
        InicioDeSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioDeSesionActionPerformed(evt);
            }
        });
        PantallasMenu.add(InicioDeSesion);

        EncontrarProfesores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/profesor.png"))); // NOI18N
        EncontrarProfesores.setText("Encontrar Profesores");
        EncontrarProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncontrarProfesoresActionPerformed(evt);
            }
        });
        PantallasMenu.add(EncontrarProfesores);

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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EncontrarProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncontrarProfesoresActionPerformed
    PantallaSecundariaAlumno a = new PantallaSecundariaAlumno();
    a.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_EncontrarProfesoresActionPerformed

    private void InicioDeSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioDeSesionActionPerformed
    PantallaInicioSesión a = new PantallaInicioSesión();
    a.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_InicioDeSesionActionPerformed

    private void SalirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirMenuActionPerformed

    private void CambiarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarContraActionPerformed
    PantallaCambiarContra a = new PantallaCambiarContra();
    a.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_CambiarContraActionPerformed

    private void EnocntrarProfesorrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnocntrarProfesorrActionPerformed
    PantallaSecundariaAlumno a = new PantallaSecundariaAlumno();
    a.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_EnocntrarProfesorrActionPerformed

    private void DesapuntarseBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesapuntarseBotonActionPerformed
    try {
        // Obtener una conexión válida a la base de datos
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        conet = DriverManager.getConnection(bbdd, "SA", "SA");

        // Llamar al método moverFila con la conexión
        eliminaYAñadir(conet);

        // Cerrar la conexión después de utilizarla
        conet.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_DesapuntarseBotonActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaPrincipalAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipalAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipalAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipalAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipalAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjustesMenu;
    private javax.swing.JMenuItem CambiarContra;
    private javax.swing.JButton DesapuntarseBoton;
    private javax.swing.JMenuItem EncontrarProfesores;
    private javax.swing.JButton EnocntrarProfesorr;
    private javax.swing.JMenuItem InicioDeSesion;
    private javax.swing.JMenu PantallasMenu;
    private javax.swing.JMenuItem SalirMenu;
    private javax.swing.JTable TablaProfesoresContratados;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
