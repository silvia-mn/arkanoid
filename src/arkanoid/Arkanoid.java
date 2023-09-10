/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import Controlers.MenuBienvenida;
import modelos.ModeloAjustes;
/**
 *
 * @author Celia
 */
public class Arkanoid {
    public static void main(String[] args) {        

        MenuBienvenida controlador = new MenuBienvenida();   
        controlador.iniciarControlador(new ModeloAjustes(true, 1, false,false));
        //Juego juego = new Juego();
        //juego.iniciarJuego();

    }
}
