import java.util.ArrayList;

/**
 *  - Numeros literales: secuencias de digitos, con punto decimal opcional. Ej: 3, 12.5
 *  - Variables numericas: identificadores (letras, digitos y guion bajo,
 *    sin iniciar con digito). Ej: x, y, base1
 *  - Operadores: + - * /
 *  - Parentesis: ( y )
 */
public class Tokenizador {

    public static ArrayList<String> tokenizar(String expresion) {
        ArrayList<String> tokens = new ArrayList<>();
        int i = 0;
        int n = expresion.length();

        while (i < n) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }


            if (Character.isDigit(c)) {
                int inicio = i;
                while (i < n && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    i++;
                }
                tokens.add(expresion.substring(inicio, i));
                continue;
            }


            if (Character.isLetter(c) || c == '_') {
                int inicio = i;
                while (i < n && (Character.isLetterOrDigit(expresion.charAt(i)) || expresion.charAt(i) == '_')) {
                    i++;
                }

                tokens.add(expresion.substring(inicio, i));
                continue;
            }


            throw new IllegalArgumentException(
                    "Caracter no reconocido '" + c + "' en la posicion " + i);
        }


        return tokens;
    }

    public static boolean esNumero(String token) {
        return Character.isDigit(token.charAt(0));
    }

    public static boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

}