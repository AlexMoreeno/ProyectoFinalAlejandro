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
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Random;
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
public class PantallaMateriasAdministrador extends javax.swing.JFrame {
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        Connection con = null;
        private String[] datos;
        String nombre1 = "";
        int FilSelect ;
        Connection conet;
    public PantallaMateriasAdministrador() {
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
        ActualizarTablaMaterias(con);
        EditarBoton.setVisible(false);
        AnadirBoton.setVisible(false);
        EliminarBoton.setVisible(false);
    }
    public void ActualizarTablaMaterias(Connection con) {
    DefaultTableModel tm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    TablaMaterias.setModel(tm);

    try {
        String sql = "SELECT * FROM materias";
        java.sql.Statement statement = con.createStatement();
        var resultado = statement.executeQuery(sql);

        tm.addColumn("Id");
        tm.addColumn("Nombre");
        tm.addColumn("IdNivel");

        while (resultado.next()) {
            String id = resultado.getString("ID");
            String nombre = resultado.getString("NOMBRE");
            String idNivel = resultado.getString("ID_NIVEL");

            Object[] fila = {id, nombre, idNivel};
            tm.addRow(fila);
        }

        TableColumnModel columnModel = TablaMaterias.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(2).setMinWidth(0);
        columnModel.getColumn(2).setMaxWidth(0);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
////////////////////////////////////////////////
    public void Reiniciar(){   
           NombreTEXT.setText("");   
           ActualizarTablaMaterias(con);   
           cambiarColorCampo(NombreTEXT, Color.BLACK);
       }
    private void cambiarColorCampo(JTextField campo, Color color) {
        campo.setBorder(BorderFactory.createLineBorder(color));
    }
////////////////////////////////////////////////
    public void buscarMateria(JTextField nombreTEXT, JTable tabla) {
    String nombre = nombreTEXT.getText();

    String consulta = "SELECT * FROM materias WHERE 1=1";
    if (!nombre.isEmpty()) {
        consulta += " AND nombre LIKE '" + nombre + "%'";
    }

    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA");
         Statement statement = conec.createStatement();
         ResultSet resultSet = statement.executeQuery(consulta)) {

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombreResultado = resultSet.getString("nombre");

            model.addRow(new Object[]{id, nombreResultado});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}
////////////////////////////////////////////////
    public void registrarMateria(JTextField nombreTEXT) {
    String nombre = nombreTEXT.getText();

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la materia.", "Error", JOptionPane.ERROR_MESSAGE);
        cambiarColorCampo(nombreTEXT, Color.RED);
        return;
    }
    Random rand = new Random();
    int idNivel = rand.nextInt(5);

    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
        String consulta = "INSERT INTO materias (nombre, id_nivel) VALUES (?, ?)";

        try (java.sql.PreparedStatement statement = conec.prepareStatement(consulta)) {
            statement.setString(1, nombre);
            statement.setInt(2, idNivel);

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "Materia registrada exitosamente.");
                cambiarColorCampo(nombreTEXT, Color.BLACK);
                ActualizarTablaMaterias(conec);
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la materia.");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}
//////////////////////////////////////////////////
    public void eliminarMateria(JTable tabla) {
    int filaSeleccionada = tabla.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int idMateria = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());

    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
        String consulta = "DELETE FROM materias WHERE id = ?";

        try (java.sql.PreparedStatement statement = conec.prepareStatement(consulta)) {
            statement.setInt(1, idMateria);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null, "Materia eliminada exitosamente.");
                ActualizarTablaMaterias(conec);
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la materia.");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}
//////////////////////////////////////////////////
   public void cargarDatosMateria(Connection con) {
    try {
        int filaSeleccionada = TablaMaterias.getSelectedRow();
        if (filaSeleccionada != -1) {
            String idSeleccionado = TablaMaterias.getValueAt(filaSeleccionada, 0).toString();
            String sqlSelect = "SELECT nombre FROM materias WHERE id = '" + idSeleccionado + "'";
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(sqlSelect);

            if (resultado.next()) {
                String nombre = resultado.getString("nombre");

                NombreTEXT.setText(nombre);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   ////////////////////////////////////////////
   public void editarMateria(Connection con, JTextField nombreMateriaTEXT) {
    String nuevoNombre = nombreMateriaTEXT.getText();

    int filaSeleccionada = TablaMaterias.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una materia para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String idSeleccionado = TablaMaterias.getValueAt(filaSeleccionada, 0).toString();

    if (nuevoNombre.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, complete el campo del nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection conec = DriverManager.getConnection(bbdd, "SA", "SA")) {
        String consulta = "UPDATE materias SET nombre = '" + nuevoNombre + "' WHERE ID = '" + idSeleccionado + "'";

        try (Statement statement = conec.createStatement()) {
            int filasActualizadas = statement.executeUpdate(consulta);

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");

                nombreMateriaTEXT.setText("");

                cargarDatosMateria(con);
                ActualizarTablaMaterias(conec);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la materia.");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ThemeGrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NombreTEXT = new javax.swing.JTextField();
        BuscarBoton = new javax.swing.JButton();
        ReiniciarBoton = new javax.swing.JButton();
        AnadirBoton = new javax.swing.JButton();
        EliminarBoton = new javax.swing.JButton();
        EditarBoton = new javax.swing.JButton();
        EditarCB = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        PantallasMenu = new javax.swing.JMenu();
        InicioDeSesion = new javax.swing.JMenuItem();
        administradorVER = new javax.swing.JMenuItem();
        Alumnos = new javax.swing.JMenuItem();
        Profesores = new javax.swing.JMenuItem();
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Materias"));
        jPanel1.setLayout(new java.awt.BorderLayout());

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
        TablaMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMateriasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaMaterias);

        jPanel1.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre de la materia");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));
        jPanel2.add(NombreTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 220, -1));

        BuscarBoton.setText("Buscar");
        BuscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(BuscarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 80, 30));

        ReiniciarBoton.setText("Reiniciar");
        ReiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(ReiniciarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 80, 30));

        AnadirBoton.setText("Añadir");
        AnadirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnadirBotonActionPerformed(evt);
            }
        });
        jPanel2.add(AnadirBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 80, 30));

        EliminarBoton.setText("Eliminar");
        EliminarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(EliminarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 80, 30));

        EditarBoton.setText("Editar");
        EditarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(EditarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 80, 30));

        EditarCB.setText("¿Editar?");
        EditarCB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        EditarCB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EditarCB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        EditarCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditarCBMouseClicked(evt);
            }
        });
        EditarCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarCBActionPerformed(evt);
            }
        });
        jPanel2.add(EditarCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 80, 30));

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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
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
     
    }//GEN-LAST:event_CambiarContraActionPerformed

    private void AlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlumnosActionPerformed
        PantallaAlumnosAdministrador a = new PantallaAlumnosAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AlumnosActionPerformed

    private void administradorVERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administradorVERActionPerformed
        PantallaPrincipalAdministrador a = new PantallaPrincipalAdministrador();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_administradorVERActionPerformed

    private void EliminarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBotonActionPerformed
      eliminarMateria(TablaMaterias);
      ActualizarTablaMaterias(con);
    }//GEN-LAST:event_EliminarBotonActionPerformed

    private void AnadirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnadirBotonActionPerformed
      registrarMateria(NombreTEXT);
      ActualizarTablaMaterias(con);
    }//GEN-LAST:event_AnadirBotonActionPerformed

    private void ReiniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarBotonActionPerformed
        Reiniciar();
    }//GEN-LAST:event_ReiniciarBotonActionPerformed

    private void BuscarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBotonActionPerformed
       buscarMateria(NombreTEXT, TablaMaterias);
    }//GEN-LAST:event_BuscarBotonActionPerformed

    private void TablaMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMateriasMouseClicked
       cargarDatosMateria(con);
    }//GEN-LAST:event_TablaMateriasMouseClicked

    private void EditarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarBotonActionPerformed
       editarMateria(con, NombreTEXT);
    }//GEN-LAST:event_EditarBotonActionPerformed

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

    private void EditarCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarCBMouseClicked
        // EditarBoton.setVisible(true);
    }//GEN-LAST:event_EditarCBMouseClicked

    private void EditarCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarCBActionPerformed
        if (EditarCB.isSelected()) {
            EditarBoton.setVisible(true);
            AnadirBoton.setVisible(true);
            EliminarBoton.setVisible(true);
        } else {
            EditarBoton.setVisible(false);
            AnadirBoton.setVisible(false);
            EliminarBoton.setVisible(false);
        }
    }//GEN-LAST:event_EditarCBActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaMateriasAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaMateriasAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaMateriasAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaMateriasAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaMateriasAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjustesMenu;
    private javax.swing.JMenuItem Alumnos;
    private javax.swing.JButton AnadirBoton;
    private javax.swing.JButton BuscarBoton;
    private javax.swing.JMenuItem CambiarContra;
    private javax.swing.JButton EditarBoton;
    private javax.swing.JCheckBox EditarCB;
    private javax.swing.JButton EliminarBoton;
    private javax.swing.JMenuItem InicioDeSesion;
    private javax.swing.JTextField NombreTEXT;
    private javax.swing.JMenu PantallasMenu;
    private javax.swing.JMenuItem Profesores;
    private javax.swing.JButton ReiniciarBoton;
    private javax.swing.JMenuItem SalirMenu;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.ButtonGroup ThemeGrupo;
    private javax.swing.JMenuItem administradorVER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuApariencia;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JCheckBoxMenuItem menuModoAzul;
    private javax.swing.JCheckBoxMenuItem menuModoClaro;
    private javax.swing.JCheckBoxMenuItem menuModoMorado;
    // End of variables declaration//GEN-END:variables
}
