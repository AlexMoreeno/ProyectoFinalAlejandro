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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.JHelp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Aleja
 */
public class PantallaSecundariaProfesor extends javax.swing.JFrame {
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        Connection con = null;
        private String[] datos;
        String nombre1 = "";
        int FilSelect ;
        Connection conet;
    public PantallaSecundariaProfesor() {
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
        String AYUDA_HS = "Ayudaa/conayuda/ayudaPF/helpset.hs";
        try {
         ClassLoader cl = getClass().getClassLoader();
         HelpSet helpset = new HelpSet(cl, cl.getResource(AYUDA_HS));
         HelpBroker hb = helpset.createHelpBroker();
         JHelp jhelp = new JHelp(helpset);
         //jhelp.setCurrentID("inicio");
         hb.enableHelpOnButton(ayuda1, "primero", helpset);

        } catch (HelpSetException ex) {
         System.err.println("Error al cargar la ayuda: " + ex);
        }
        ActualizarTablaAlumnos(con);
        ActualizarTablaMaterias(con);
        
    }
   public void ActualizarTablaAlumnos(Connection con) {
        try {
            String sql = "SELECT * FROM usuarios WHERE tipo = 'alumno'";
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            DefaultTableModel tmAlumnos = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tmAlumnos.addColumn("Id");
            tmAlumnos.addColumn("Nombre");
            tmAlumnos.addColumn("Apellidos");
            tmAlumnos.addColumn("Email");
            tmAlumnos.addColumn("Clave");
            tmAlumnos.addColumn("Tipo");

            while (resultado.next()) {
                String id = resultado.getString("ID");
                String nombre = resultado.getString("NOMBRE");
                String apellidos = resultado.getString("APELLIDOS");
                String email = resultado.getString("EMAIL");
                String clave = resultado.getString("CLAVE");
                String tipo = resultado.getString("TIPO");

                Object[] fila = {id, nombre, apellidos, email, clave, tipo};
                tmAlumnos.addRow(fila);
            }

            TablaAlumnos.setModel(tmAlumnos);

            TableColumnModel columnModel = TablaAlumnos.getColumnModel();
            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);
            columnModel.getColumn(4).setMinWidth(0);
            columnModel.getColumn(4).setMaxWidth(0);
            columnModel.getColumn(5).setMinWidth(0);
            columnModel.getColumn(5).setMaxWidth(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ActualizarTablaMaterias(Connection con) {
        DefaultTableModel tmMaterias = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        TablaMaterias.setModel(tmMaterias);

        try {
            String sql = "SELECT * FROM materias";
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            tmMaterias.addColumn("Id");
            tmMaterias.addColumn("Nombre");
            tmMaterias.addColumn("IdNivel");

            while (resultado.next()) {
                String id = resultado.getString("ID");
                String nombre = resultado.getString("NOMBRE");
                String idNivel = resultado.getString("ID_NIVEL");

                Object[] fila = {id, nombre, idNivel};
                tmMaterias.addRow(fila);
            }

            TableColumnModel columnModel = TablaMaterias.getColumnModel();
            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);
            columnModel.getColumn(2).setMinWidth(0);
            columnModel.getColumn(2).setMaxWidth(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filtrarPorApellidoAlumno(String apellidos) {
        DefaultTableModel modelo = new DefaultTableModel() {};

        String consultaSQL = "SELECT nombre, apellidos, email FROM usuarios WHERE tipo = 'alumno'";
        if (!apellidos.isEmpty()) {
            consultaSQL += " AND apellidos LIKE '%" + apellidos + "%'";
        }
        consultaSQL += " LIMIT 2";

        try {
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(consultaSQL);

            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Email");

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String apellidosResultado = resultado.getString("apellidos");
                String email = resultado.getString("email");
                modelo.addRow(new Object[]{nombre, apellidosResultado, email});
            }

            TablaAlumnos.setModel(modelo);

            if (!apellidos.isEmpty()) {
                obtenerMaterias();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void obtenerMaterias() {
        DefaultTableModel tm = new DefaultTableModel() {};

        String consultaSQL = "SELECT nombre FROM materias ORDER BY RAND() LIMIT 2";

        try {
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(consultaSQL);

            ArrayList<String> materias = new ArrayList<>();
            while (resultado.next()) {
                materias.add(resultado.getString("nombre"));
            }

            tm.addColumn("Nombre");

            for (String nombreMateria : materias) {
                tm.addRow(new Object[]{nombreMateria});
            }

            TablaMaterias.setModel(tm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public void insertarAlumnoDesdeTabla(Connection con) {
    int filaSeleccionada = TablaAlumnos.getSelectedRow();

    if (filaSeleccionada != -1) {
        try {
            String id = (String) TablaAlumnos.getValueAt(filaSeleccionada, 0);
            String nombre = (String) TablaAlumnos.getValueAt(filaSeleccionada, 1);
            String apellidos = (String) TablaAlumnos.getValueAt(filaSeleccionada, 2);
            String email = (String) TablaAlumnos.getValueAt(filaSeleccionada, 3);
            String tipo = (String) TablaAlumnos.getValueAt(filaSeleccionada, 5);

            System.out.println("Nombre: " + nombre + ", Apellidos: " + apellidos + ", Email: " + email);

            String sqlInsert = "INSERT INTO alumnoscontratados (nombre, apellidos, email, clave, tipo) VALUES ('" + nombre + "', '" + apellidos + "', '" + email + "', 'clave_predeterminada', '" + tipo + "')";

            try (Statement statement = con.createStatement()) {
                int rowsInserted = statement.executeUpdate(sqlInsert);
                System.out.println("Filas insertadas en alumnoscontratados: " + rowsInserted);
            }

            String sqlDelete = "DELETE FROM usuarios WHERE id = '" + id + "' AND nombre = '" + nombre + "' AND apellidos = '" + apellidos + "' AND email = '" + email + "' AND tipo = '" + tipo + "'";
            try (Statement statement = con.createStatement()) {
                int rowsDeleted = statement.executeUpdate(sqlDelete);
                System.out.println("Filas eliminadas de usuarios: " + rowsDeleted);
            }

            ActualizarTablaAlumnos(con);
            JOptionPane.showMessageDialog(null, "Se ha añadido el alumno a tu lista", "Alumno Escogido", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    public void Reiniciar() {
        ApellidoTEXT.setText("");
        try {
            ActualizarTablaAlumnos(con);
            ActualizarTablaMaterias(con);
            
        } catch (Exception e) {
            e.printStackTrace();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaAlumnos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ApellidoTEXT = new javax.swing.JTextField();
        FiltrarBotón = new javax.swing.JButton();
        ContactarBoton = new javax.swing.JButton();
        ReiniciarBoton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        PantallasMenu = new javax.swing.JMenu();
        InicioDeSesion = new javax.swing.JMenuItem();
        VerAlumnos = new javax.swing.JMenuItem();
        SalirMenu = new javax.swing.JMenuItem();
        AjustesMenu = new javax.swing.JMenu();
        CambiarContra = new javax.swing.JMenuItem();
        menuApariencia = new javax.swing.JMenu();
        menuDarkMode = new javax.swing.JCheckBoxMenuItem();
        menuModoClaro = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuModoAzul = new javax.swing.JCheckBoxMenuItem();
        menuModoMorado = new javax.swing.JCheckBoxMenuItem();
        Ayuda = new javax.swing.JMenu();
        ayuda1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Materias"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        TablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jScrollPane4.setViewportView(TablaMaterias);

        jPanel2.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Apellido Alumno");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));
        jPanel3.add(ApellidoTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 240, 30));

        FiltrarBotón.setText("Filtrar");
        FiltrarBotón.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarBotónActionPerformed(evt);
            }
        });
        jPanel3.add(FiltrarBotón, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, 30));

        ContactarBoton.setText("Contactar");
        ContactarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactarBotonActionPerformed(evt);
            }
        });
        jPanel3.add(ContactarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 100, 30));

        ReiniciarBoton.setText("Reiniciar");
        ReiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarBotonActionPerformed(evt);
            }
        });
        jPanel3.add(ReiniciarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 30));

        PantallasMenu.setText("Pantallas");

        InicioDeSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/login.png"))); // NOI18N
        InicioDeSesion.setText("Inicio de Sesion");
        InicioDeSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioDeSesionActionPerformed(evt);
            }
        });
        PantallasMenu.add(InicioDeSesion);

        VerAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/alumno.png"))); // NOI18N
        VerAlumnos.setText("Ver Alumnos");
        VerAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerAlumnosActionPerformed(evt);
            }
        });
        PantallasMenu.add(VerAlumnos);

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

        Ayuda.setText("Dudas");

        ayuda1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinalalejandro/pregunta.png"))); // NOI18N
        ayuda1.setText("Ayuda");
        ayuda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayuda1ActionPerformed(evt);
            }
        });
        Ayuda.add(ayuda1);

        jMenuBar1.add(Ayuda);

        jMenu1.setText("Acerca de");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jMenuItem1.setText("Información");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void VerAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerAlumnosActionPerformed
        PantallaPrincipalProfesor a = new PantallaPrincipalProfesor();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_VerAlumnosActionPerformed

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

    private void ReiniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarBotonActionPerformed
        Reiniciar();
    }//GEN-LAST:event_ReiniciarBotonActionPerformed

    private void FiltrarBotónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarBotónActionPerformed
       String apellidos = ApellidoTEXT.getText();
       filtrarPorApellidoAlumno(apellidos);
    }//GEN-LAST:event_FiltrarBotónActionPerformed

    private void ContactarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactarBotonActionPerformed
       insertarAlumnoDesdeTabla(con);
    }//GEN-LAST:event_ContactarBotonActionPerformed

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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        PantallaAcercaDe a = new PantallaAcercaDe();
        a.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ayuda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayuda1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ayuda1ActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaSecundariaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaSecundariaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaSecundariaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaSecundariaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaSecundariaProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjustesMenu;
    private javax.swing.JTextField ApellidoTEXT;
    private javax.swing.JMenu Ayuda;
    private javax.swing.JMenuItem CambiarContra;
    private javax.swing.JButton ContactarBoton;
    private javax.swing.JButton FiltrarBotón;
    private javax.swing.JMenuItem InicioDeSesion;
    private javax.swing.JMenu PantallasMenu;
    private javax.swing.JButton ReiniciarBoton;
    private javax.swing.JMenuItem SalirMenu;
    private javax.swing.JTable TablaAlumnos;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.ButtonGroup ThemeGrupo;
    private javax.swing.JMenuItem VerAlumnos;
    private javax.swing.JMenuItem ayuda1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuApariencia;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JCheckBoxMenuItem menuModoAzul;
    private javax.swing.JCheckBoxMenuItem menuModoClaro;
    private javax.swing.JCheckBoxMenuItem menuModoMorado;
    // End of variables declaration//GEN-END:variables
}
