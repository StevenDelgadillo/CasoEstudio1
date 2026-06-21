package pilaDinamica;

import java.util.ArrayList;

public class PilaDinamica {

    // Atributs
    private ArrayList<String> elementos;

    // metodos
    // constructor
    public PilaDinamica(){
        elementos = new ArrayList<>();
    }

    // operaciones
    private boolean estaVacia(){
        return elementos.isEmpty();
    }

    public void push(String elemento){
        elementos.addLast(elemento);
    }

    public String pop(){
        if (estaVacia()){
            System.out.println("La pila esta vacia. \n");
            return null;
        }
        return elementos.removeLast();
    }

    public String peek() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia. \n");
            return null;
        }
        return elementos.getLast();
    }
}