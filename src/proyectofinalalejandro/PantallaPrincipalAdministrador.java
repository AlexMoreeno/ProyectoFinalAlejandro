/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinalalejandro;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Aleja
 */
public class PantallaPrincipalAdministrador extends javax.swing.JFrame {
    String bbdd = "jdbc:hsqldb:hsql://localhost/";
    Connection con = null;
    private String[] datos;
    String nombre1 = "";
    int FilSelect;
    Connection conet;

    public PantallaPrincipalAdministrador() {
        initComponents();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(bbdd, "SA", "SA");
            if (con != null) {
                System.out.println("Connection created successfully");
            } else {
                System.out.println("Problem with creating connection");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }

 
         try {
            int numeroAlumnos = obtenerNumeroAlumnos(con);
            int numeroProfesores = obtenerNumeroProfesores(con);
            int numeroMaterias = obtenerNumeroMaterias(con);
            int numeroTotales = obtenerTotalUsuarios(con);

            MostrarAlumnos.setText(String.valueOf(numeroAlumnos));
            MostrarProfesores.setText(String.valueOf(numeroProfesores));
            MostrarMaterias.setText(String.valueOf(numeroMaterias));
            MostrarTotales.setText(String.valueOf(numeroTotales));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static int obtenerTotalUsuarios(Connection con) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios";
        try (Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }


    public static int obtenerNumeroAlumnos(Connection con) throws SQLException {
        return obtenerUsuariosPorTipo(con, "alumno");
    }


    public static int obtenerNumeroProfesores(Connection con) throws SQLException {
        return obtenerUsuariosPorTipo(con, "profesor");
    }


    public static int obtenerNumeroMaterias(Connection con) throws SQLException {
        String sql = "SELECT COUNT(*) FROM materias";
        try (Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }


    public static int obtenerUsuariosPorTipo(Connection con, String tipo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE tipo = '" + tipo + "'";
        try (Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MostrarAlumnos = new javax.swing.JLabel();
        MostrarProfesores = new javax.swing.JLabel();
        MostrarMaterias = new javax.swing.JLabel();
        MostrarTotales = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        PantallasMenu = new javax.swing.JMenu();
        InicioDeSesion = new javax.swing.JMenuItem();
        Alumnos = new javax.swing.JMenuItem();
        Profesores = new javax.swing.JMenuItem();
        Materias = new javax.swing.JMenuItem();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrador"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Alumnos Totales");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 24, -1, -1));

        jLabel5.setText("Profesores Totales");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 74, -1, -1));

        jLabel6.setText("Materias Totales");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 130, -1, -1));

        jLabel7.setText("Usuarios Totales");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 186, -1, -1));

        MostrarAlumnos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(MostrarAlumnos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 160, 20));

        MostrarProfesores.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(MostrarProfesores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 20));

        MostrarMaterias.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(MostrarMaterias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 160, 20));

        MostrarTotales.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(MostrarTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 160, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 220, 250));

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aleja\\OneDrive\\Documentos\\NetBeansProjects\\NetBeansProjects\\ProyectoFinalAlejandro\\src\\imagenes\\icons8-estudiante-masculino-100.png")); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aleja\\OneDrive\\Documentos\\NetBeansProjects\\NetBeansProjects\\ProyectoFinalAlejandro\\src\\imagenes\\icons8-teachers-100.png")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 90));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aleja\\OneDrive\\Documentos\\NetBeansProjects\\NetBeansProjects\\ProyectoFinalAlejandro\\src\\imagenes\\icons8-libros-100.png")); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aleja\\OneDrive\\Documentos\\NetBeansProjects\\NetBeansProjects\\ProyectoFinalAlejandro\\src\\imagenes\\adminFoto.png")); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 510, 280));

        PantallasMenu.setText("Pantallas");

        InicioDeSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/login.png"))); // NOI18N
        InicioDeSesion.setText("Inicio de Sesion");
        InicioDeSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioDeSesionActionPerformed(evt);
            }
        });
        PantallasMenu.add(InicioDeSesion);

        Alumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/alumno.png"))); // NOI18N
        Alumnos.setText("Alumnos");
        Alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlumnosActionPerformed(evt);
            }
        });
        PantallasMenu.add(Alumnos);

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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfesoresActionPerformed
         PantallaProfesoresAdministrador a = new PantallaProfesoresAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ProfesoresActionPerformed

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

    private void AlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlumnosActionPerformed
        PantallaAlumnosAdministrador a = new PantallaAlumnosAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AlumnosActionPerformed

    private void MateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MateriasActionPerformed
        PantallaMateriasAdministrador a = new PantallaMateriasAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MateriasActionPerformed

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

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        PantallaAlumnosAdministrador a = new PantallaAlumnosAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        PantallaProfesoresElegir a = new PantallaProfesoresElegir();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
       PantallaMateriasAdministrador a = new PantallaMateriasAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        PantallaAdministradoresAdministrador a = new PantallaAdministradoresAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

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
          //  UIManager.setLookAndFeel( new FlatLightLaf() );
            FlatArcDarkIJTheme.setup();
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipalAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjustesMenu;
    private javax.swing.JMenuItem Alumnos;
    private javax.swing.JMenuItem CambiarContra;
    private javax.swing.JMenuItem InicioDeSesion;
    private javax.swing.JMenuItem Materias;
    private javax.swing.JLabel MostrarAlumnos;
    private javax.swing.JLabel MostrarMaterias;
    private javax.swing.JLabel MostrarProfesores;
    private javax.swing.JLabel MostrarTotales;
    private javax.swing.JMenu PantallasMenu;
    private javax.swing.JMenuItem Profesores;
    private javax.swing.JMenuItem SalirMenu;
    private javax.swing.ButtonGroup ThemeGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuApariencia;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JCheckBoxMenuItem menuModoAzul;
    private javax.swing.JCheckBoxMenuItem menuModoClaro;
    private javax.swing.JCheckBoxMenuItem menuModoMorado;
    // End of variables declaration//GEN-END:variables

}
