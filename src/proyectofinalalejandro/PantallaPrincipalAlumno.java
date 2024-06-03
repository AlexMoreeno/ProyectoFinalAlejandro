/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;



import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import java.awt.EventQueue;
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

        tm.addColumn("Id");
        tm.addColumn("Nombre");
        tm.addColumn("Apellidos");
        tm.addColumn("Email");
        tm.addColumn("Clave");
        tm.addColumn("Tipo");

        while(resultado.next()) {
            String id = resultado.getString("ID");
            String nombre = resultado.getString("NOMBRE");
            String apellidos = resultado.getString("APELLIDOS");
            String email = resultado.getString("EMAIL");
            String clave = resultado.getString("CLAVE");
            String tipo = resultado.getString("TIPO");

            Object[] fila = {id, nombre, apellidos, email, clave, tipo};
            tm.addRow(fila);
        }

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
            String id = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 0);
            String nombre = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 1);
            String apellidos = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 2);
            String email = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 3);
            String clave = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 4);
            String tipo = (String) TablaProfesoresContratados.getValueAt(filaSeleccionada, 5);
            
            System.out.println("Nombre: " + nombre + ", Apellidos: " + apellidos + ", Email: " + email);

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

        ThemeGrupo = new javax.swing.ButtonGroup();
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
        menuApariencia = new javax.swing.JMenu();
        menuDarkMode = new javax.swing.JCheckBoxMenuItem();
        menuModoClaro = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuModoAzul = new javax.swing.JCheckBoxMenuItem();
        menuModoMorado = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DesapuntarseBoton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DesapuntarseBoton.setText("Desapuntarse");
        DesapuntarseBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesapuntarseBotonActionPerformed(evt);
            }
        });
        jPanel2.add(DesapuntarseBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 180, 40));

        EnocntrarProfesorr.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EnocntrarProfesorr.setText("Encontrar Profesor");
        EnocntrarProfesorr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnocntrarProfesorrActionPerformed(evt);
            }
        });
        jPanel2.add(EnocntrarProfesorr, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 180, 40));

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

        menuApariencia.setText("Apariencia");

        menuDarkMode.setText("Modo oscuro");
        menuDarkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDarkModeActionPerformed(evt);
            }
        });
        menuApariencia.add(menuDarkMode);

        menuModoClaro.setText("Modo claro");
        menuModoClaro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModoClaroActionPerformed(evt);
            }
        });
        menuApariencia.add(menuModoClaro);
        menuApariencia.add(jSeparator1);

        menuModoAzul.setText("Modo azul");
        menuModoAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModoAzulActionPerformed(evt);
            }
        });
        menuApariencia.add(menuModoAzul);

        menuModoMorado.setSelected(true);
        menuModoMorado.setText("Modo morado");
        menuModoMorado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModoMoradoActionPerformed(evt);
            }
        });
        menuApariencia.add(menuModoMorado);

        jMenuBar1.add(menuApariencia);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
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

    }//GEN-LAST:event_CambiarContraActionPerformed

    private void EnocntrarProfesorrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnocntrarProfesorrActionPerformed
    PantallaSecundariaAlumno a = new PantallaSecundariaAlumno();
    a.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_EnocntrarProfesorrActionPerformed

    private void DesapuntarseBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesapuntarseBotonActionPerformed
    try {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        conet = DriverManager.getConnection(bbdd, "SA", "SA");
        eliminaYAñadir(conet);
        conet.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_DesapuntarseBotonActionPerformed

    private void menuDarkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDarkModeActionPerformed
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                FlatArcDarkIJTheme.setup();
                FlatLaf.updateUI();
            }
        });
    }//GEN-LAST:event_menuDarkModeActionPerformed

    private void menuModoClaroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModoClaroActionPerformed
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                FlatIntelliJLaf.setup();
                FlatLaf.updateUI();
            }
        });
    }//GEN-LAST:event_menuModoClaroActionPerformed

    private void menuModoAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModoAzulActionPerformed
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                FlatCobalt2IJTheme.setup();
                FlatLaf.updateUI();
            }
        });
    }//GEN-LAST:event_menuModoAzulActionPerformed

    private void menuModoMoradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModoMoradoActionPerformed
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                FlatGradiantoMidnightBlueIJTheme.setup();
                FlatLaf.updateUI();
            }
        });
    }//GEN-LAST:event_menuModoMoradoActionPerformed

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
    private javax.swing.ButtonGroup ThemeGrupo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuApariencia;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JCheckBoxMenuItem menuModoAzul;
    private javax.swing.JCheckBoxMenuItem menuModoClaro;
    private javax.swing.JCheckBoxMenuItem menuModoMorado;
    // End of variables declaration//GEN-END:variables
}
