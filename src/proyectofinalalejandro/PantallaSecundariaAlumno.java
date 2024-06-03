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
import java.awt.List;
import java.sql.Connection;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleja
 */
public class PantallaSecundariaAlumno extends javax.swing.JFrame {
    String bbdd = "jdbc:hsqldb:hsql://localhost/";
    Connection con = null;
    private String[] datos;
    String nombre1 = "";
    int FilSelect ;
    Connection conet;
  
    public PantallaSecundariaAlumno() {
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
        ActualizarTablaProfesores(con);
        ActualizarTablaMaterias(con);
    }
   DefaultTableModel tmProfesores = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};

public void ActualizarTablaProfesores(Connection con) {
    try {
        String sql = "SELECT * FROM usuarios WHERE tipo = 'profesor'";
        Statement statement = con.createStatement();
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

        TablaProfesores.setModel(tmProfesores);

        TableColumnModel columnModel = TablaProfesores.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(4).setMinWidth(0);
        columnModel.getColumn(4).setMaxWidth(0);
        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);

    } catch (Exception e) {
        e.printStackTrace();
    }
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
        Statement statement = con.createStatement();
        ResultSet resultado = statement.executeQuery(sql);

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

private void filtrarPorApellido(String apellidos) {
    DefaultTableModel modeloProfesores = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String consultaSQLProfesores = "SELECT ID, NOMBRE, APELLIDOS, EMAIL, CLAVE, TIPO FROM usuarios WHERE tipo = 'profesor'";
    if (!apellidos.isEmpty()) {
        consultaSQLProfesores += " AND apellidos LIKE '%" + apellidos + "%'";
    }
    consultaSQLProfesores += " LIMIT 2";

    try {
        Statement statement = con.createStatement();
        ResultSet resultadoProfesores = statement.executeQuery(consultaSQLProfesores);

        modeloProfesores.addColumn("Id");
        modeloProfesores.addColumn("Nombre");
        modeloProfesores.addColumn("Apellidos");
        modeloProfesores.addColumn("Email");
        modeloProfesores.addColumn("Clave");
        modeloProfesores.addColumn("Tipo");

        while (resultadoProfesores.next()) {
            String id = resultadoProfesores.getString("ID");
            String nombre = resultadoProfesores.getString("NOMBRE");
            String apellidosResultado = resultadoProfesores.getString("APELLIDOS");
            String email = resultadoProfesores.getString("EMAIL");
            String clave = resultadoProfesores.getString("CLAVE");
            String tipo = resultadoProfesores.getString("TIPO");
            modeloProfesores.addRow(new Object[]{id, nombre, apellidosResultado, email, clave, tipo});
        }

        TablaProfesores.setModel(modeloProfesores);

        TableColumnModel columnModel = TablaProfesores.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(4).setMinWidth(0);
        columnModel.getColumn(4).setMaxWidth(0);
        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);

        obtenerMaterias2("");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void obtenerMaterias2(String filtro) {
    DefaultTableModel modeloMaterias = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String consultaSQLMaterias = "SELECT nombre FROM materias";
    if (!filtro.isEmpty()) {
        consultaSQLMaterias += " WHERE nombre LIKE '%" + filtro + "%' LIMIT 2";
    } else {
        consultaSQLMaterias += " ORDER BY RAND() LIMIT 2";
    }

    try {
        Statement statement = con.createStatement();
        ResultSet resultadoMaterias = statement.executeQuery(consultaSQLMaterias);

        modeloMaterias.addColumn("Nombre");

        while (resultadoMaterias.next()) {
            String nombreMateria = resultadoMaterias.getString("nombre");
            modeloMaterias.addRow(new Object[]{nombreMateria});
        }

        TablaMaterias.setModel(modeloMaterias);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void filtrarPorMateria(String materia) {
    DefaultTableModel modeloProfesores = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String consultaSQLProfesores = "SELECT ID, NOMBRE, APELLIDOS, EMAIL, CLAVE, TIPO FROM usuarios WHERE tipo = 'profesor'";
    if (!materia.isEmpty()) {
        consultaSQLProfesores += " ORDER BY RAND() LIMIT 2";
    }

    try {
        Statement statement = con.createStatement();
        ResultSet resultadoProfesores = statement.executeQuery(consultaSQLProfesores);

        modeloProfesores.addColumn("Id");
        modeloProfesores.addColumn("Nombre");
        modeloProfesores.addColumn("Apellidos");
        modeloProfesores.addColumn("Email");
        modeloProfesores.addColumn("Clave");
        modeloProfesores.addColumn("Tipo");

        while (resultadoProfesores.next()) {
            String id = resultadoProfesores.getString("ID");
            String nombre = resultadoProfesores.getString("NOMBRE");
            String apellidos = resultadoProfesores.getString("APELLIDOS");
            String email = resultadoProfesores.getString("EMAIL");
            String clave = resultadoProfesores.getString("CLAVE");
            String tipo = resultadoProfesores.getString("TIPO");
            modeloProfesores.addRow(new Object[]{id, nombre, apellidos, email, clave, tipo});
        }

        TablaProfesores.setModel(modeloProfesores);

        TableColumnModel columnModel = TablaProfesores.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(4).setMinWidth(0);
        columnModel.getColumn(4).setMaxWidth(0);
        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);

        obtenerMaterias2(materia);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void insertarProfesorDesdeTabla(Connection con) {
    int filaSeleccionada = TablaProfesores.getSelectedRow();

    if (filaSeleccionada != -1) {
        try {
            String id = (String) TablaProfesores.getValueAt(filaSeleccionada, 0);
            String nombre = (String) TablaProfesores.getValueAt(filaSeleccionada, 1);
            String apellidos = (String) TablaProfesores.getValueAt(filaSeleccionada, 2);
            String email = (String) TablaProfesores.getValueAt(filaSeleccionada, 3);
            String clave = (String) TablaProfesores.getValueAt(filaSeleccionada, 4);
            String tipo = (String) TablaProfesores.getValueAt(filaSeleccionada, 5);

            String sqlInsert = "INSERT INTO profesorescontratados (nombre, apellidos, email, clave, tipo) VALUES (?, ?, ?, ?, ?)";
            try (var insertStatement = con.prepareStatement(sqlInsert)) {
                insertStatement.setString(1, nombre);
                insertStatement.setString(2, apellidos);
                insertStatement.setString(3, email);
                insertStatement.setString(4, clave);
                insertStatement.setString(5, tipo);
                int rowsInserted = insertStatement.executeUpdate();
                System.out.println("Filas insertadas en profesorescontratados: " + rowsInserted);
            }

            String sqlDelete = "DELETE FROM usuarios WHERE id = ? AND nombre = ? AND apellidos = ? AND email = ? AND clave = ? AND tipo = ?";
            try (var deleteStatement = con.prepareStatement(sqlDelete)) {
                deleteStatement.setString(1, id);
                deleteStatement.setString(2, nombre);
                deleteStatement.setString(3, apellidos);
                deleteStatement.setString(4, email);
                deleteStatement.setString(5, clave);
                deleteStatement.setString(6, tipo);
                int rowsDeleted = deleteStatement.executeUpdate();
                System.out.println("Filas eliminadas de usuarios: " + rowsDeleted);
            }

            ActualizarTablaProfesores(con);
            JOptionPane.showMessageDialog(null, "Se ha contratado al profesor", "Profesor Eliminado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
/* PRUEBAS DE BUCLES CASI AL 100% 
 private void filtrarPorApellido(String apellidos) {
    DefaultTableModel modelo = new DefaultTableModel() {};

    String consultaSQL = "SELECT nombre, apellidos, email " +
                        "FROM usuarios " +
                        "WHERE tipo = 'profesor'";
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

        TablaProfesores.setModel(modelo);

        if (!apellidos.isEmpty()) {
            obtenerMateriasAleatorias();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void obtenerMateriasAleatorias() {
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

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void filtrarPorApellidoYMateria(String apellidos, String materia) {
    DefaultTableModel modeloProfesores = new DefaultTableModel() {};
    DefaultTableModel modeloMaterias = new DefaultTableModel() {};

    String consultaSQLProfesores = "SELECT nombre, apellidos, email " +
                                    "FROM usuarios " +
                                    "WHERE tipo = 'profesor'";
    if (!apellidos.isEmpty()) {
        consultaSQLProfesores += " AND apellidos LIKE '%" + apellidos + "%'";
    }
    consultaSQLProfesores += " LIMIT 2";

    try {
        Statement statement = con.createStatement();
        ResultSet resultadoProfesores = statement.executeQuery(consultaSQLProfesores);

        modeloProfesores.addColumn("Nombre");
        modeloProfesores.addColumn("Apellidos");
        modeloProfesores.addColumn("Email");

        while (resultadoProfesores.next()) {
            String nombre = resultadoProfesores.getString("nombre");
            String apellidosProfesor = resultadoProfesores.getString("apellidos");
            String email = resultadoProfesores.getString("email");
            modeloProfesores.addRow(new Object[]{nombre, apellidosProfesor, email});
        }

        TablaProfesores.setModel(modeloProfesores);

    } catch (Exception e) {
        e.printStackTrace();
    }

    String consultaSQLMaterias = "SELECT nombre FROM materias";
    if (!materia.isEmpty()) {
        consultaSQLMaterias += " WHERE nombre LIKE '%" + materia + "%'";
    }
    consultaSQLMaterias += " LIMIT 2";

    try {
        Statement statement = con.createStatement();
        ResultSet resultadoMaterias = statement.executeQuery(consultaSQLMaterias);

        modeloMaterias.addColumn("Nombre");

        while (resultadoMaterias.next()) {
            String nombreMateria = resultadoMaterias.getString("nombre");
            modeloMaterias.addRow(new Object[]{nombreMateria});
        }

        TablaMaterias.setModel(modeloMaterias);

    } catch (Exception e) {
        e.printStackTrace();
    }
}*/
////////////////////////////////////////////////////////////////////////////////////////////////
    public void Reiniciar() {
        ApellidoTEXT.setText("");
        MateriaTEXT.setText("");
        try {
            ActualizarTablaProfesores(con);
            ActualizarTablaMaterias(con);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CursoAcademico = new javax.swing.ButtonGroup();
        ThemeGrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProfesores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ApellidoTEXT = new javax.swing.JTextField();
        FiltrarBoton = new javax.swing.JButton();
        ReiniciarBoton = new javax.swing.JButton();
        ContratarBoton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        MateriaTEXT = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        PantallasMenu = new javax.swing.JMenu();
        InicioDeSesion = new javax.swing.JMenuItem();
        VerProfesores = new javax.swing.JMenuItem();
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla Profesores"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        TablaProfesores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TablaProfesores);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Apellido Profesor");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, -1));
        jPanel2.add(ApellidoTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 210, 30));

        FiltrarBoton.setText("Filtrar");
        FiltrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(FiltrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 40));

        ReiniciarBoton.setText("Reiniciar");
        ReiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(ReiniciarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 100, 40));

        ContratarBoton.setText("Contratar");
        ContratarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(ContratarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 100, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Materias");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 90, 20));
        jPanel2.add(MateriaTEXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 210, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Materias"));
        jPanel3.setLayout(new java.awt.BorderLayout());

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

        jPanel3.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        PantallasMenu.setText("Pantallas");

        InicioDeSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/login.png"))); // NOI18N
        InicioDeSesion.setText("Inicio de Sesion");
        InicioDeSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioDeSesionActionPerformed(evt);
            }
        });
        PantallasMenu.add(InicioDeSesion);

        VerProfesores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosPropios/profesor.png"))); // NOI18N
        VerProfesores.setText("Ver profesores");
        VerProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerProfesoresActionPerformed(evt);
            }
        });
        PantallasMenu.add(VerProfesores);

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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void VerProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerProfesoresActionPerformed
        PantallaPrincipalAlumno a = new PantallaPrincipalAlumno();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_VerProfesoresActionPerformed

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

    private void FiltrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarBotonActionPerformed
    String apellidos = ApellidoTEXT.getText();
    String materia = MateriaTEXT.getText();
    if (!apellidos.isEmpty() && materia.isEmpty()) {
        filtrarPorApellido(apellidos);
        return; 
    }
    filtrarPorMateria(materia);
    }//GEN-LAST:event_FiltrarBotonActionPerformed

    private void ContratarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContratarBotonActionPerformed
       insertarProfesorDesdeTabla(con);
       ActualizarTablaProfesores(con);
       ActualizarTablaMaterias(con);
    }//GEN-LAST:event_ContratarBotonActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaSecundariaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaSecundariaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaSecundariaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaSecundariaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaSecundariaAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjustesMenu;
    private javax.swing.JTextField ApellidoTEXT;
    private javax.swing.JMenuItem CambiarContra;
    private javax.swing.JButton ContratarBoton;
    private javax.swing.ButtonGroup CursoAcademico;
    private javax.swing.JButton FiltrarBoton;
    private javax.swing.JMenuItem InicioDeSesion;
    private javax.swing.JTextField MateriaTEXT;
    private javax.swing.JMenu PantallasMenu;
    private javax.swing.JButton ReiniciarBoton;
    private javax.swing.JMenuItem SalirMenu;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JTable TablaProfesores;
    private javax.swing.ButtonGroup ThemeGrupo;
    private javax.swing.JMenuItem VerProfesores;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuApariencia;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JCheckBoxMenuItem menuModoAzul;
    private javax.swing.JCheckBoxMenuItem menuModoClaro;
    private javax.swing.JCheckBoxMenuItem menuModoMorado;
    // End of variables declaration//GEN-END:variables
}
