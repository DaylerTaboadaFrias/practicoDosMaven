/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.edd.proyectoArboles;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author daylerTaboada
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>,V> implements IArbolBinarioBusqueda<K,V>{
    protected NodoBinario<K,V> raiz;
    public ArbolBinarioBusqueda(){
        this.raiz=null;
    }
    protected NodoBinario<K,V> nodoVacioParaElArbol(){
        return (NodoBinario<K, V>) NodoBinario.nodoVacio();
    }

    @Override
    public String toString() {
       return GenerarCadenaArbol(raiz,"",true);
    }
    
    

    private String GenerarCadenaArbol(NodoBinario<K,V> nodo, String prefijo, boolean PonerCodo) {
    StringBuilder  cadena= new StringBuilder();
       cadena.append(prefijo);
       //es lo mismo :
       // cadena.append(ponerCodo ? "└- ":"├- ");
       if(PonerCodo){
       cadena.append("└-");
       }else{
       cadena.append("├-");
       }
       if(NodoBinario.esNodoVacio(nodo)){
       cadena.append("\n");
       return cadena.toString();
       }
       
       cadena.append(nodo.getValor().toString());
       cadena.append("\n");
       NodoBinario nodoIzq= nodo.getHijoIzquierdo();
       String prefijoAux= cadena.toString() + (PonerCodo ? "  ": "|  ");
       cadena.append(GenerarCadenaArbol(nodoIzq,prefijoAux, false));
       
       NodoBinario nodoDer= nodo.getHijoDerecho();
       prefijoAux= cadena.toString() + (PonerCodo ? "  " : "|   ");
       cadena.append(GenerarCadenaArbol(nodoDer, prefijoAux, true));
       
       return cadena.toString();
    }
    public void insertar(K clave,V valor) throws DatoYaExisteExcepcion{
    if(this.esArbolVacio()){
            this.raiz=new NodoBinario<>(clave,valor);
            return;
        }
        NodoBinario<K,V> nodoActual = this.raiz;
        NodoBinario<K,V> nodoAnterior = this.nodoVacioParaElArbol();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior= nodoActual;
            if(clave.compareTo(nodoActual.getClave())>0){
                nodoActual =nodoActual.getHijoDerecho();
            }else if(clave.compareTo(nodoActual.getClave())<0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
                return ;
            }
        }
        NodoBinario<K,V> nodoNuevo = new NodoBinario<K,V>(clave,valor);
        if(clave.compareTo(nodoAnterior.getClave())>0){
            nodoAnterior.setHijoDerecho(nodoNuevo);
        }else{
            nodoAnterior.setHijoIzquierdo(nodoNuevo);
        }
    }
    public void vaciar(){
        this.raiz=(NodoBinario<K, V>) NodoBinario.nodoVacio();
    }
    public boolean esArbolVacio(){
        return (NodoBinario.esNodoVacio(this.raiz));
    }
    public V buscar(K clave){
        NodoBinario<K,V> nodoActual = this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            if(clave.compareTo(nodoActual.getClave())>0){
                nodoActual=nodoActual.getHijoDerecho();
            }else if(clave.compareTo(nodoActual.getClave())<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                return nodoActual.getValor();
            }
        }
        return null;
    }
    public List<K> recorridoPorNiveles(){
        List<K> listakeys= new LinkedList<>();
        if(this.esArbolVacio()){
            return listakeys;
        }
        Queue<NodoBinario<K,V>> colaNodos = new LinkedList<>();
        colaNodos.offer(this.raiz);
        while(!colaNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaNodos.poll();
            listakeys.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return listakeys;
    }
    
    public int size(){
        List<K> listakeys= new LinkedList<>();
        if(this.esArbolVacio()){
            return 0;
        }
        int contador = 0;
        Queue<NodoBinario<K,V>> colaNodos = new LinkedList<>();
        colaNodos.offer(this.raiz);
        while(!colaNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaNodos.poll();
            contador=contador+1;
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return contador;
    }
    @Override
    public List<K> recorridoPreOrden(){
        List<K> listakeys = new LinkedList<>();
        if(this.esArbolVacio()){
            return listakeys;
        }
        Stack<NodoBinario<K,V>> pilaNodos = new Stack<>();
        pilaNodos.push(this.raiz);
        while(!pilaNodos.isEmpty()){
            NodoBinario<K,V> nodoActual= pilaNodos.pop();
            listakeys.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoDerecho()){
                pilaNodos.push(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                pilaNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return listakeys;
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> listaClaves=new LinkedList<>();
        if(this.esArbolVacio()){
            return listaClaves;
        }
        NodoBinario<K,V> nodoActual = this.raiz;
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActuals=pilaDeNodos.pop();
            listaClaves.add(nodoActuals.getClave());
            if(!nodoActuals.esVacioHijoDerecho()){
                NodoBinario<V,K> nodoAuxiliar= (NodoBinario<V,K>) nodoActuals.getHijoDerecho();
                while(!NodoBinario.esNodoVacio(nodoAuxiliar)){
                    pilaDeNodos.push((NodoBinario<K, V>) nodoAuxiliar);
                    nodoAuxiliar = nodoAuxiliar.getHijoIzquierdo();
                }
            }
        }
        return listaClaves;
    }

    @Override
    public List<K> recorridoPostOrden() {
        List<K> listaDeClaves = new LinkedList<>();
        Stack<NodoBinario<K,V>>pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;
        NodoBinario<K,V> nodoUltimo ;
        NodoBinario<K,V> nodoVisitado=null ;
        while(!NodoBinario.esNodoVacio(nodoActual) || !pilaDeNodos.isEmpty()){
            if(!NodoBinario.esNodoVacio(nodoActual)){
                pilaDeNodos.push(nodoActual);
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            else{
                nodoUltimo = pilaDeNodos.peek();
                if(!NodoBinario.esNodoVacio(nodoUltimo.getHijoDerecho()) && nodoVisitado !=nodoUltimo.getHijoDerecho() ){
                    nodoActual = nodoUltimo.getHijoDerecho();
                }else{
                    listaDeClaves.add(nodoUltimo.getClave());
                    nodoVisitado = pilaDeNodos.pop();
                }
            }
        }
        return listaDeClaves;
        
    }
    @Override
    public V eliminar(K clave) throws DatoNoExisteExcepcion  {
        V valor = this.buscar(clave);
        if(valor == null){
             throw new DatoNoExisteExcepcion();
        }else{
            this.raiz = this.eliminar(this.raiz,clave);
            return valor;
        }
        
    }
    public List<K> recorridoEnPostOrden(){
        List<K> lista = new LinkedList<>();
        recorridoEnPostOrdenRecursivo(this.raiz,lista);
        return lista;
    }

    private void recorridoEnPostOrdenRecursivo(NodoBinario<K, V> raiz, List<K> lista) {
        if(NodoBinario.esNodoVacio(raiz)){
           return; 
        }
        recorridoEnPostOrdenRecursivo(raiz.getHijoIzquierdo(),lista);
        recorridoEnPostOrdenRecursivo(raiz.getHijoDerecho(),lista);
        lista.add(raiz.getClave());
    }
    //Implemente un método recursivo que retorne la cantidad nodos hojas que existen en un arbol binario
    public int cantidadDeNodosHojas(){
        int contador=0;
        return this.cantidadDeNodosHojasRecursivo(raiz, contador);
    }
    private  int cantidadDeNodosHojasRecursivo(NodoBinario<K,V> nodoActual,int contador){
        if(NodoBinario.esNodoVacio(nodoActual)){
          return 0;
        }
        int cantidadPorIzquierda = this.cantidadDeNodosHojasRecursivo(nodoActual.getHijoIzquierdo(), contador);
        int cantidadPorDerecha = this.cantidadDeNodosHojasRecursivo(nodoActual.getHijoDerecho(), contador);
        if(nodoActual.esHoja()){
            return cantidadPorDerecha+cantidadPorIzquierda+1;
        }
        return cantidadPorDerecha+cantidadPorIzquierda;
    }
    
        //Implemente un método recursivo que retorne la cantidad nodos hojas en un solo nivel
    public int cantidadDeNodosHojasEnUnNivel(int nivel){
        int contador=0;
        return this.cantidadDeNodosHojasEnUnNivelRecursivo(raiz, contador,0,nivel);
    }
    private  int cantidadDeNodosHojasEnUnNivelRecursivo(NodoBinario<K,V> nodoActual,int contador,int nivelActual,int nivel){
        if(NodoBinario.esNodoVacio(nodoActual)){
          return 0;
        }
        int cantidadPorIzquierda = this.cantidadDeNodosHojasEnUnNivelRecursivo(nodoActual.getHijoIzquierdo(), contador,nivelActual+1,nivel);
        int cantidadPorDerecha = this.cantidadDeNodosHojasEnUnNivelRecursivo(nodoActual.getHijoDerecho(), contador,nivelActual+1,nivel);
        if(nodoActual.esHoja() && nivel==nivelActual){
            return cantidadPorDerecha+cantidadPorIzquierda+1;
        }
        return cantidadPorDerecha+cantidadPorIzquierda;
    }
    
    
    //Implemente un método iterativo que retorne la cantidad nodos hojas que existen en un árbol binario 
    public  int cantidadDeNodosHojasIterativo(){
        if(NodoBinario.esNodoVacio(raiz)){
           return 0;
        }
        int contador=0;
        Queue<NodoBinario<K,V>> colaNodos = new LinkedList<>();
        colaNodos.offer(raiz);
        while(!colaNodos.isEmpty()){
           NodoBinario<K,V> nodoActual = colaNodos.poll();
           if(nodoActual.esHoja()){
               contador = contador + 1 ;
           }
           if(!nodoActual.esVacioHijoIzquierdo()){
               colaNodos.offer(nodoActual.getHijoIzquierdo());
           }
           if(!nodoActual.esVacioHijoDerecho()){
               colaNodos.offer(nodoActual.getHijoDerecho());
           }
        }
        return contador;
    }
    //Implemente un método recursivo que retorne la cantidad nodos hojas que existen en un árbol binario,
    //pero solo en el nivel 
    public int cantidadNodosHojaAntesUnNivel(int nivel){
        return cantidadNodosHojaAntesUnNivelRecursivo(this.raiz,0,nivel);
    } 

    private int cantidadNodosHojaAntesUnNivelRecursivo(NodoBinario<K, V> nodoActual,int nivelActual,int nivel) {
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadPorIzquierda = cantidadNodosHojaAntesUnNivelRecursivo(nodoActual.getHijoIzquierdo(),
                nivelActual+1,nivel);
        int cantidadPorDerecha = cantidadNodosHojaAntesUnNivelRecursivo(nodoActual.getHijoDerecho(),
                nivelActual+1,nivel);
        if(nodoActual.esHoja() && nivel >= nivelActual){
            return cantidadPorIzquierda+cantidadPorDerecha+1;
        }
        return cantidadPorIzquierda+cantidadPorDerecha;
    }
        //Implemente un método iterativo que retorne la cantidad nodos hojas que existen en un árbol binario,
    //pero solo en el nivel 
    public int cantidadNodosHojasAntesNivelIterativo(int nivel){
        if(NodoBinario.esNodoVacio(raiz)){
            return 0;
        }
        Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>();
        NodoBinario<K,V> nodoActual ;
        colaDeNodos.offer(this.raiz);
        int contador=0;
        while(!colaDeNodos.isEmpty()){
            nodoActual = colaDeNodos.poll();
            int nivelActual = this.encontraNivel(nodoActual.getClave());
            if(nivelActual<=nivel && nodoActual.esHoja()){
                contador = contador + 1;
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo()) ;
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho()) ;
            }
        }
        return contador;
    }
    
    
    //Cantidad de nodos hoja un un solo nivel
    
    public int cantidadNodosHojasEnUnNivelIterativo(int nivel){
        if(NodoBinario.esNodoVacio(raiz)){
            return 0;
        }
        Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>();
        NodoBinario<K,V> nodoActual ;
        colaDeNodos.offer(this.raiz);
        int contador=0;
        while(!colaDeNodos.isEmpty()){
            nodoActual = colaDeNodos.poll();
            int nivelActual = this.encontraNivel(nodoActual.getClave());
            if(nivelActual==nivel && nodoActual.esHoja()){
                contador = contador + 1;
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo()) ;
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho()) ;
            }
        }
        return contador;
    }
    //Encontrar nivel de nodo
    public int encontraNivel(K clave){
        NodoBinario<K,V> nodoActual = this.raiz;
        K claveActual = nodoActual.getClave();
        Boolean b=false;
        int contador = 0;
        while(b==false){
            if(clave.compareTo(claveActual)>0){
                nodoActual= nodoActual.getHijoDerecho();
                claveActual=nodoActual.getClave();
                contador = contador+1;
            }
            else if(clave.compareTo(claveActual)<0){
                nodoActual= nodoActual.getHijoIzquierdo();
                claveActual=nodoActual.getClave();
                contador = contador+1;
            }else{
                b=true;
                return contador;
            }
        }
        return 0;
    }
    //Altura del algo
    public int altura(){
        return this.altura(this.raiz);
    }
    protected int altura(NodoBinario<K, V> nodoActual) {
        if(nodoActual == null){
            return 0;
        }
        int cantidadPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int cantidadPorDerecha = altura(nodoActual.getHijoDerecho());
        if(cantidadPorIzquierda>cantidadPorDerecha){
            return cantidadPorIzquierda+1;
        }
        return cantidadPorDerecha+1;
    }
    //Contar nodos desde un nivel
    public int contarNodosDesdeUnNivel(int nivel){
        return this.contarNodosDesdeUnNivelRecursivo(this.raiz,0,nivel);
    }
    protected int contarNodosDesdeUnNivelRecursivo(NodoBinario<K, V> nodoActual,int nivelActual,int nivel) {
        if( NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadPorIzquierda = contarNodosDesdeUnNivelRecursivo(nodoActual.getHijoIzquierdo(),nivelActual+1,nivel);
        int cantidadPorDerecha = contarNodosDesdeUnNivelRecursivo(nodoActual.getHijoDerecho(),nivelActual+1,nivel);
         if(nivelActual >= nivel){
            return cantidadPorIzquierda + cantidadPorDerecha + 1;
        }
        return cantidadPorDerecha  + cantidadPorIzquierda ;
    }
    
    
    //Implemente un método recursivo que retorne verdadero, si un árbol binario esta balanceado según las reglas 
    //que establece un árbol AVL, falso en caso contrario.
    public Boolean verificarSiEsBalanceado(){
        return verificarSiEsBalanceadoRecursivo(this.raiz);
    }

    private Boolean verificarSiEsBalanceadoRecursivo(NodoBinario<K, V> nodoActual) {
        if(NodoBinario.esNodoVacio(nodoActual)){
            return true;
        }
        Boolean guardarPorIzquierda = verificarSiEsBalanceadoRecursivo(nodoActual.getHijoIzquierdo());
        Boolean guardarPorDerecha = verificarSiEsBalanceadoRecursivo(nodoActual.getHijoDerecho());
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
        int diferencia = alturaPorIzquierda-alturaPorDerecha;
        if(guardarPorIzquierda==false || guardarPorDerecha==false){
            return false;
        }
        if(diferencia < -1 || diferencia >1){
            return false;
        }
        return true;
    }
    
    
    //RECURSIVO : Implemente un método privado que reciba un nodo binario de un árbol binario 
    //y que retorne cual sería su sucesor inorden de la clave de dicho nodo. 
    public String buscarSucesorPublico(K clave) throws DatoNoExisteExcepcion{
        NodoBinario<K,V> nodoActual = this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            if(clave.compareTo(nodoActual.getClave())>0){
                nodoActual=nodoActual.getHijoDerecho();
            }else if(clave.compareTo(nodoActual.getClave())<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                NodoBinario<K,V> nodoSucesor = buscarSucesorPrivado(nodoActual);
                if(NodoBinario.esNodoVacio(nodoSucesor)){
                    return "No tiene nodo Sucesor";
                }
                return nodoSucesor.getClave().toString();
            }
        }
        throw new DatoNoExisteExcepcion("Error la clave no existe");
    }
    //  Metodo privado para encontrar un nodo sucesor
    private NodoBinario<K,V> buscarSucesorPrivado(NodoBinario<K,V> nodoBuscar) throws DatoNoExisteExcepcion{
        K clave = nodoBuscar.getClave();
        NodoBinario<K,V> nodoActual = nodoBuscar.getHijoDerecho();
        if(NodoBinario.esNodoVacio(nodoActual)){
             return null;
        }
        while(!NodoBinario.esNodoVacio(nodoActual.getHijoIzquierdo())){
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoActual;
    }
    
    
    
    //RECURSIVO : Implemente un metodo privado que reciba un nodo binario de un arbol binario 
    //y que retorne cual sería su predecesor inorden de la clave de dicho nodo. 
    public String buscarPredecesorPublico(K clave) throws DatoNoExisteExcepcion{
        NodoBinario<K,V> nodoActual = this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            if(clave.compareTo(nodoActual.getClave())>0){
                nodoActual=nodoActual.getHijoDerecho();
            }else if(clave.compareTo(nodoActual.getClave())<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                NodoBinario<K,V> nodoPredecesor = buscarPredecesorPrivado(nodoActual);
                if(NodoBinario.esNodoVacio(nodoPredecesor)){
                    return "No tiene nodo Predecesor";
                }
                return nodoPredecesor.getClave().toString();
            }
        }
        throw new DatoNoExisteExcepcion("Error la clave no existe");
    }
    //  Metodo privado para encontrar un nodo predecesor
    private NodoBinario<K,V> buscarPredecesorPrivado(NodoBinario<K,V> nodoBuscar) throws DatoNoExisteExcepcion{
        K clave = nodoBuscar.getClave();
        NodoBinario<K,V> nodoActual = nodoBuscar.getHijoIzquierdo();
        if(NodoBinario.esNodoVacio(nodoActual)){
             return null;
        }
        while(!NodoBinario.esNodoVacio(nodoActual.getHijoDerecho())){
            nodoActual = nodoActual.getHijoDerecho();
        }
        return nodoActual;
    }
    
    
    
    
    // Implemente un metodo privado que reciba un nodo binario de un arbol binario y
    //que retorne cual sera su predecesor inorden de la clave de dicho nodo. 
    public NodoBinario<K,V> buscarPredecesorIterativo(K clave) throws DatoNoExisteExcepcion{
        if(NodoBinario.esNodoVacio(this.raiz)){
            throw new DatoNoExisteExcepcion("Error arbol vacio");
        }
       NodoBinario<K,V> nodoActual = this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            if(clave.compareTo(nodoActual.getClave())>0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }
            else if(clave.compareTo(nodoActual.getClave())<0){
                nodoActual=nodoActual.getHijoDerecho();
            }else{
                if(nodoActual.esVacioHijoDerecho()){
                    throw new DatoNoExisteExcepcion("No tiene sucesor");
                }else{
                    NodoBinario<K,V> nodoDerecho=nodoActual.getHijoDerecho();
                    while(!NodoBinario.esNodoVacio(nodoDerecho.getHijoIzquierdo())){
                        nodoDerecho= nodoDerecho.getHijoIzquierdo();
                    }
                    return nodoDerecho;
                }
            }
        }
        throw new DatoNoExisteExcepcion("No se encontro resultados");
    }
   //7. Implemente un metodo iterativo que la logica de un recorrido en postorden que 
    //retorne verdadero, si un Arbol binario esta balanceado segun las reglas que establece un Arbol AVL, falso en caso contrario.  
    public  boolean verificarSiElArbolEstaBalanceadoIterativo(){
        Stack<NodoBinario<K,V>>pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;
        NodoBinario<K,V> nodoUltimo ;
        NodoBinario<K,V> nodoVisitado=null ;
        while(!NodoBinario.esNodoVacio(nodoActual) || !pilaDeNodos.isEmpty()){
            if(!NodoBinario.esNodoVacio(nodoActual)){
                pilaDeNodos.push(nodoActual);
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            else{
                nodoUltimo = pilaDeNodos.peek();
                if(!NodoBinario.esNodoVacio(nodoUltimo.getHijoDerecho()) && nodoVisitado !=nodoUltimo.getHijoDerecho() ){
                    nodoActual = nodoUltimo.getHijoDerecho();
                }else{
                    int diferenciaAltura = this.altura(nodoUltimo.getHijoIzquierdo())-this.altura(nodoUltimo.getHijoDerecho());
                    if( !(diferenciaAltura >=-1 && diferenciaAltura<=1)){
                        return false;
                    }
                    nodoVisitado = pilaDeNodos.pop();
                }
            }
        }
        return true;
    }
    //Implemente un metodo que reciba en listas de parametros las llaves y valores de los recorridos en preorden e inorden respectivamente
    //y que reconstruya el Arbol binario original. Su metodo no debe usar el metodo insertar.
    public NodoBinario<K,V> reconstruirArbolBinario(List<K> clavePreorden,List<V> valorPreorde, List<K> claveInorden,List<V> valorInorden){
          
        return this.raiz;
    }

    
    

    
    public void printBinaryTree(){
        printBinaryTree(raiz, 0);
    }
    private  void printBinaryTree(NodoBinario<K,V> nodo, int level){
        if(nodo==null) 
            return; 
        printBinaryTree(nodo.getHijoDerecho(), level+1); 
        if(level!=0){ 
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
        System.out.println("|--------"+"("+nodo.getClave()+","+nodo.getValor()+")"); 
        } else System.out.println("("+nodo.getClave()+","+nodo.getValor()+")"); 
        printBinaryTree(nodo.getHijoIzquierdo(), level+1); 
    }

    private NodoBinario<K,V> eliminar(NodoBinario<K, V> nodoActual, K clave)throws DatoNoExisteExcepcion {
        if(NodoBinario.esNodoVacio(raiz)){
           throw new DatoNoExisteExcepcion("Error el dato no existe");
        }
        K claveActual = nodoActual.getClave();
        
        if(clave.compareTo(claveActual)>0){
            NodoBinario<K,V> supuestoNodoDerecho = eliminar(nodoActual.getHijoDerecho(),clave);
            nodoActual.setHijoDerecho(supuestoNodoDerecho);
            return nodoActual;
        }else if(clave.compareTo(claveActual)<0){
            NodoBinario<K,V> supuestoNodoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(),clave);
            nodoActual.setHijoIzquierdo(supuestoNodoIzquierdo);
            return nodoActual;
        }
        //caso 1
        if(nodoActual.esHoja()){
            return this.nodoVacioParaElArbol();
        }
        //caso 2
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoDerecho();
        }
        if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
        
        //caso 3
            NodoBinario<K,V>  nodoReemplazo = buscarPredecedorSucesor(nodoActual.getHijoDerecho());
            NodoBinario<K,V>  nodoDerecho = eliminar(nodoActual.getHijoDerecho(),nodoReemplazo.getClave());
            nodoActual.setHijoDerecho(nodoDerecho);
            nodoReemplazo.setHijoDerecho(nodoActual.getHijoDerecho());
            nodoReemplazo.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
            nodoActual.setHijoDerecho(this.nodoVacioParaElArbol());
            nodoActual.setHijoIzquierdo(this.nodoVacioParaElArbol());
            return nodoReemplazo;
        
    }

    private NodoBinario<K, V> buscarPredecedorSucesor(NodoBinario<K, V> nodoActual) {
        if(NodoBinario.esNodoVacio(nodoActual.getHijoIzquierdo())){
            return nodoActual;
        }
        
        return nodoActual.getHijoIzquierdo();
    }

    
    public void ReconstruirArbolBinario(List<K> listaClaveInorden,List<V> listaValorInorden,List<K> listaClavePreorden,List<V> listaValorPreorden){
        int cantidadDeNodos = listaClaveInorden.size();
        K claveInorden = listaClaveInorden.get(0); listaClaveInorden.remove(0);
        int indiceClaveDeLaRaiz = listaClavePreorden.indexOf(claveInorden);
        
        List<K> subListaIzquierda = new LinkedList<>();  
        subListaIzquierda.addAll( listaClavePreorden.subList(0,indiceClaveDeLaRaiz)); 
        boolean eliminar=listaClavePreorden.removeAll(subListaIzquierda);
        int cantidadIzquierda =subListaIzquierda.size();
        
        int cantidadDeNodosDerecha = listaClavePreorden.size()-1;
        List<K> subListaDerecha = new LinkedList<>();  
         subListaDerecha.addAll(listaClavePreorden.subList(1,cantidadDeNodosDerecha)); 
        boolean eliminaer=listaClavePreorden.removeAll(subListaDerecha);

    }
}   



















