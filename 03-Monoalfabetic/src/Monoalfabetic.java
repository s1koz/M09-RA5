import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {

    static String alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    static char[] alfabetArry = alfabet.toCharArray();
    static char[] alfabetPermutat = permutaAlfabet(alfabetArry);

    public static void main(String[] args) {

        String[] msgs = {"àrbritre, coixí, Perímetre", "Taüll, DÍA, año", "Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        // alfabet ---------------------------------
        for (int i = 0; i < alfabetArry.length; i++) {
            System.out.print(alfabetArry[i]);

            if (i < alfabetArry.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        // alfabetPermutat ---------------------------------
        for (int i = 0; i < alfabetPermutat.length; i++) {
            System.out.print(alfabetPermutat[i]);

            if (i < alfabetPermutat.length - 1) {
                System.out.print(" ");
            }
        }

        // Xifratge ---------------------------------
        System.out.printf("\nXifratge:");

        System.out.println();

        int numTest = 1;
        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraMonoAlfa("Test " + String.format("%02d", numTest) + " " + msgs[i]);
            System.out.printf("%-23s => %s%n",msgs[i], msgsXifrats[i]);
        }



    }

    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> lista = new ArrayList<>();

        for (char letra : alfabet) {
            lista.add(letra);
        }

        Collections.shuffle(lista);

        char[] permutat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            permutat[i] = lista.get(i);
        }

        return permutat;
    }

    public static String xifraMonoAlfa(String cadena) {
        String xifrat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char letra = cadena.charAt(i);

            if (Character.isUpperCase(letra)) {

                for (int n = 0; n < alfabetArry.length; n++) {
                    if (alfabetArry[n] == letra) {
                        xifrat += alfabetPermutat[n];
                        break;
                    }
                }

            } else if (Character.isLowerCase(letra)) {

                for (int n = 0; n < alfabetArry.length; n++) {
                    if (alfabetArry[n] == Character.toUpperCase(letra)) {
                        xifrat += Character.toLowerCase(alfabetPermutat[n]);
                        break;
                    }
                }

            } else {
                xifrat += letra;
            }
        }

        return xifrat;
    }

    public static String desxifraMonoAlfa(String cadena) {
        String desxifrat = "";



        return desxifrat;
    }

}
