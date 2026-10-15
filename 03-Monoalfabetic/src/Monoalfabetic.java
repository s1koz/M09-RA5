import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {

    private final String alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] alfabetArry = alfabet.toCharArray();
    private final char[] alfabetPermutat = permutaAlfabet(alfabetArry);

    public static void main(String[] args) {

        String[] msgs = {"àrbritre, coixí, Perímetre", "Taüll, DÍA, año", "Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        Monoalfabetic xifrador = new Monoalfabetic();

        // alfabet ---------------------------------
        for (int i = 0; i < xifrador.alfabetArry.length; i++) {
            System.out.print(xifrador.alfabetArry[i]);

            if (i < xifrador.alfabetArry.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        // alfabetPermutat ---------------------------------
        for (int i = 0; i < xifrador.alfabetPermutat.length; i++) {
            System.out.print(xifrador.alfabetPermutat[i]);

            if (i < xifrador.alfabetPermutat.length - 1) {
                System.out.print(" ");
            }
        }

        // Xifratge ---------------------------------
        System.out.printf("\nXifratge:");

        System.out.println();

        int numTestXifratge = 1;
        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifrador.xifraMonoAlfa("Test " + String.format("%02d", numTestXifratge) + " " + msgs[i]);
            System.out.printf("%-35s -> %s%n",("Test " + String.format("%02d", numTestXifratge) + ' ' + msgs[i]), msgsXifrats[i]);
            numTestXifratge++;
        }

        // Desxifratge -------------------------------
        System.out.printf("\nDesxifratge:");

        System.out.println();

        int numTestDesxifra = 1;
        for (int n = 0; n < msgsXifrats.length; n++) {
            System.out.printf("%-35s -> %s%n",("Test " + String.format("%02d", numTestDesxifra) + ' ' + msgsXifrats[n]), xifrador.desxifraMonoAlfa(msgsXifrats[n]));
            numTestDesxifra++;
        }

    }

    public char[] permutaAlfabet(char[] alfabet) {
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

    public String xifraMonoAlfa(String cadena) {
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

    public String desxifraMonoAlfa(String cadena) {
        String desxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char letra = cadena.charAt(i);

            if (Character.isUpperCase(letra)) {

                for (int n = 0; n < alfabetPermutat.length; n++) {
                    if (alfabetPermutat[n] == letra) {
                        desxifrat += alfabetArry[n];
                        break;
                    }
                }

            } else if (Character.isLowerCase(letra)) {

                for (int n = 0; n < alfabetPermutat.length; n++) {
                    if (alfabetPermutat[n] == Character.toUpperCase(letra)) {
                        desxifrat += Character.toLowerCase(alfabetArry[n]);
                        break;
                    }
                }

            } else {
                desxifrat += letra;
            }
        }

        return desxifrat;
    }

}
