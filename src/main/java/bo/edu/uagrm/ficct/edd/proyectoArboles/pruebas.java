/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.edd.proyectoArboles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author daylerTaboada
 */
public class pruebas {
    public static void main(String[] argumentos) throws Exception{
        int tipoArbol ;
        boolean b = true;
        ArbolBinarioBusqueda<Integer,String> arbol3 ;
         BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(System.in));
        ArbolBinarioBusqueda<Integer,String> arbol = null ;
        System.out.println("ELIJA UN ARBOL PARA REALIZAR LOS EJERCICIOS (introduzca solo el numero) :");
        System.out.println("( 1 ) ARBOL BINARIO DE BUSQUEDA.");
        System.out.println("( 2 ) ARBOL AVL");
        tipoArbol =Integer.parseInt(reader.readLine()) ;
        if(tipoArbol == 1){
            arbol = new ArbolBinarioBusqueda<>();
        }else if(tipoArbol == 2){
            arbol = new AVL<>();
        }else{
            System.out.println("El numero introducido no pertenece al arbol 1 y 2");
        }
        
        while( b ){
            System.out.println("Lista de metodos:");
            System.out.println("(1) Insertar:");
            System.out.println("(2) Cantidad de nodos hoja recursivo:");
            System.out.println("(3) Cantidad de nodos hoja iterativo:");
            System.out.println("(4) Cantidad de nodos hoja en un solo nivel recursivo:");
            System.out.println("(5) Cantidad de nodos hoja en un solo nivel iterativo:");
            System.out.println("(6) Cantidad de nodos hoja antes de un nivel iterativo:");
            System.out.println("(7) Verificar si el arbol es balanceado recursivo");
            System.out.println("(8) Verificar si el arbol es balanceado iterativo");
            System.out.println("(9) Reconstruir arbol:");
            System.out.println("(10) Buscar sucesor inorden:");
            System.out.println("(11) Buscar predecesor inorden:");
            System.out.println("(12) Eliminar nodo arbol AVL:");
            System.out.println("(13) Contar nodos desde un nivel:");
            System.out.println("(14) FINALIZAR PROGRAMA:");
            System.out.println("Elije un metodo (Introducir solamente el numero!!!!) :");
            int metodo = Integer.parseInt(reader.readLine());
            int clave;
		switch(metodo) {
			case 1:
                            System.out.println("Clave del nodo :");
                            clave = Integer.parseInt(reader.readLine());
                            System.out.println("Valor del nodo :");
                            String valor = (reader.readLine());
                            arbol.insertar(clave, valor);
                            
                            System.out.println("-----------------Arbol Binario------------ ");
                            System.out.println("\n");
                            arbol.printBinaryTree();
                            System.out.println("\n");
                            System.out.println("------------------------------------------------- ");
                            System.out.println("\n");
                            break;
                        case 2:
                            System.out.println("Resultado : "+ arbol.cantidadDeNodosHojas());
                            break;
                        case 3:
                            System.out.println("Resultado : "+ arbol.cantidadDeNodosHojasIterativo());
                            break;
                        case 4:
                            System.out.println("Introduzca un nivel :");
                            int nivel2 = Integer.parseInt(reader.readLine());
                            System.out.println("Resultado : "+ arbol.cantidadDeNodosHojasEnUnNivel(nivel2));
                            break;
                        case 5:
                            System.out.println("Introduzca un nivel :");
                            int nivel3 = Integer.parseInt(reader.readLine());
                            System.out.println("Resultado : "+ arbol.cantidadNodosHojasEnUnNivelIterativo(nivel3));
                            break;
                        case 6:
                            System.out.println("Introduzca un nivel :");
                            int nivel = Integer.parseInt(reader.readLine());
                            System.out.println("Resultado : "+ arbol.cantidadNodosHojasAntesNivelIterativo(nivel));
                            break;
                        case 7:
                            System.out.println("Resultado : "+ arbol.verificarSiEsBalanceado());
                            break;
                        case 8:
                            System.out.println("Resultado : "+ arbol.verificarSiElArbolEstaBalanceadoIterativo());
                            break;
                        case 9:
                            System.out.println("No se pudo implementar");
                            break;
                        case 10:
                            System.out.println("Introduzca la clave del nodo :");
                            int nn = Integer.parseInt(reader.readLine());
                            System.out.println("Resultado : "+ arbol.buscarSucesorPublico(nn));
                            break;
                        case 11:
                            System.out.println("Introduzca la clave del nodo :");
                            int ne = Integer.parseInt(reader.readLine());
                            System.out.println("Resultado : "+ arbol.buscarPredecesorPublico(ne));
                            break;
                        case 12:
                            System.out.println("Introduzca una clave:");
                            int claven = Integer.parseInt(reader.readLine());
                            System.out.println(arbol.eliminar(claven));
                            break;
                        case 13:
                            System.out.println("Introduzca un nivel :");
                            int n = Integer.parseInt(reader.readLine());
                             System.out.println("Resultado : "+ arbol.contarNodosDesdeUnNivel(n));
                            break;
			case 14:
                            b = false;
                            break;
		}

        }
//arbolito.insertar(12,"mama");
//arbolito.insertar(11,"mama");
//arbolito.insertar(14,"mama");
//arbolito.insertar(15,"mama");
//arbolito.printBinaryTree();

            
            

        
    }


}
