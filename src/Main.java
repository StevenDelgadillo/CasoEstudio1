import pilaDinamica.PilaDinamica;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Estudio de Caso #1 - Estructuras de Datos - Universidad CENFOTEC.
 * Analisis de expresiones aritmeticas: validacion de parentesis balanceados
 * usando una pila dinamica.
 */



public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static String expresionActual = null;

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println(" ANALIZADOR DE EXPRESIONES ARITMETICAS");
        System.out.println(" Validacion de parentesis balanceados (pila dinamica)");
        System.out.println("=========================================================");
        menu();
    }



    private static void menu() {

        int opcion;

        do {

            System.out.println("\n========= MENU =========");
            System.out.println("1. Ingresar expresion");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> ingresarExpresion();
                case 2 -> System.out.println("Saliendo del programa. Hasta pronto!");
                default -> System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 2);
    }

    // Opciones del menu



    private static void ingresarExpresion() {
        System.out.println("Ingrese la expresion:");
        expresionActual = sc.nextLine();
        validarParentesis();

    }


    private static void validarParentesis() {
        if (expresionActual == null) {
            System.out.println("Primero debe ingresar una expresion (opcion 1).");
            return;
        }



        ArrayList<String> tokens;
        try {
            tokens = Tokenizador.tokenizar(expresionActual);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al analizar la expresion: " + e.getMessage());
            return;
        }




        System.out.println("Tokens encontrados:");
        for (String token : tokens) {
            if (Tokenizador.esNumero(token)) {
                System.out.println(token + "  ->  numero literal");
            } else if (Tokenizador.esOperador(token)) {
                System.out.println(token + "  ->  operador");
            } else if (token.equals("(") || token.equals(")")) {
                System.out.println(token + "  ->  parentesis");
            } else {
                // Variable numerica: se pide su valor al usuario en el momento
                System.out.print("Ingrese el valor de la variable \"" + token + "\": ");
                String valor = sc.nextLine();
                System.out.println(token + "  ->  variable numerica (valor = " + valor + ")");
            }
        }



        if (!contieneParentesis(tokens)) {
            System.out.println();
            System.out.println("Resultado: no se encontraron parentesis.");
            return;
        }


        boolean balanceada = validarConPila(tokens);

        System.out.println();
        if (balanceada) {
            System.out.println("Resultado: los parentesis estan BALANCEADOS.");
        } else {
            System.out.println("Resultado: los parentesis estan DESBALANCEADOS.");
        }
    }

    // validacion con la pila

    // esto confirma si hay al meno un parentesis
    private static boolean contieneParentesis(ArrayList<String> tokens) {
        for (String token : tokens) {
            if (token.equals("(") || token.equals(")")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida los parentesis usando la pila dinamica. Por cada '(' se hace
     * push, y por cada ')' se hace pop. Se lleva un contador propio porque
     * estaVacia() es privado en la clase dada (tal como se definio en clase)
     */

    private static boolean validarConPila(ArrayList<String> tokens) {
        PilaDinamica pila = new PilaDinamica();
        int contador = 0;


        for (String token : tokens) {
            if (token.equals("(")) {
                pila.push("(");
                contador++;
                System.out.println("push(\"(\")");

            } else if (token.equals(")")) {
                if (contador == 0) {
                    System.out.println("Se encontro un ')' sin un '(' que le corresponda.");
                    return false;
                }

                pila.pop();
                contador--;
                System.out.println("pop()  ->  \")\" cierra correctamente");
            }
        }

        if (contador != 0) {
            System.out.println("Quedaron " + contador + " parentesis '(' sin cerrar.");
        }
        return contador == 0;
    }



    // adecuaciones al codigo


    private static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpia el salto de linea
        return valor;
    }
}