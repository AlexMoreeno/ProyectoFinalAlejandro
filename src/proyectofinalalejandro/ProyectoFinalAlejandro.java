/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinalalejandro;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

/**
 *
 * @author Aleja
 */
public class ProyectoFinalAlejandro {
    
    public static void main(String[] args) {
         try {
          //  UIManager.setLookAndFeel( new FlatLightLaf() );
            FlatArcDarkIJTheme.setup();
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        PantallaInicioSesión p = new PantallaInicioSesión ();
        p.setVisible(true);
       
    }
    
}
