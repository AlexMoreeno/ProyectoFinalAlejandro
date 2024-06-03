/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ayudaa.conayuda;

import proyectofinalalejandro.PantallaPrincipalAdministrador;
import proyectofinalalejandro.PantallaSecundariaAlumno;
import proyectofinalalejandro.PantallaSecundariaProfesor;

/**
 *
 * @author Aleja
 */
public class ConAyuda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PantallaPrincipalAdministrador p= new PantallaPrincipalAdministrador();
        p.setVisible(true);
        PantallaSecundariaAlumno p1 = new PantallaSecundariaAlumno();
        p1.setVisible(true);
        PantallaSecundariaProfesor p2 = new PantallaSecundariaProfesor();
        p2.setVisible(true);
    }
    
}
