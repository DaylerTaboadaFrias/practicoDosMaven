/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.edd.proyectoArboles;

/**
 *
 * @author daylerTaboada
 */
public class DatoNoExisteExcepcion extends Exception {
    public DatoNoExisteExcepcion(){
    super("Dato no existe exception");
    }
    public DatoNoExisteExcepcion(String mensaje){
    super(mensaje);
    }
}
