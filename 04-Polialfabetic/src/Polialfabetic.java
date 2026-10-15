import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {

    private final String alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] alfabetArry = alfabet.toCharArray();
    private char[] alfabetPermutat;

    private Random random;
    private final int clauSecreta = 86;

    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año", 
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        Polialfabetic xifrador = new Polialfabetic();

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            xifrador.initRandom(xifrador.clauSecreta);
            msgsXifrats[i] = xifrador.xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            xifrador.initRandom(xifrador.clauSecreta);
            String msg = xifrador.desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }

    }

    public void initRandom(int clauSecreta) {
        random = new Random(clauSecreta);
        permutaAlfabet(alfabetArry);

    }

    public void permutaAlfabet(char[] alfabet) {

        if (random == null) {
            random = new Random(clauSecreta);
        }

        ArrayList<Character> lista = new ArrayList<>();

        for (char letra : alfabet) {
            lista.add(letra);
        }

        Collections.shuffle(lista, random);

        alfabetPermutat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            alfabetPermutat[i] = lista.get(i);
        }


    }

    public String xifraPoliAlfa(String msg) {
        String xifrat = "";

        for (int i = 0; i < msg.length(); i++) {

            char letra = msg.charAt(i);

            if (Character.isUpperCase(letra)) {

                for (int n = 0; n < alfabetArry.length; n++) {
                    
                    if (alfabetArry[n] == letra) {
                        xifrat += alfabetPermutat[n];
                        permutaAlfabet(alfabetArry);
                        break;
                    }
                }

            } else if (Character.isLowerCase(letra)) {

                for (int n = 0; n < alfabetArry.length; n++) {
                    
                    if (alfabetArry[n] == Character.toUpperCase(letra)) {
                        xifrat += Character.toLowerCase(alfabetPermutat[n]);
                        permutaAlfabet(alfabetArry);
                        break;
                    }
                }

            } else {
                xifrat += letra;
            }
        }

        return xifrat;
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        String desxifrat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {

            char letra = msgXifrat.charAt(i);


            if (Character.isUpperCase(letra)) {

                for (int n = 0; n < alfabetPermutat.length; n++) {
                    
                    if (alfabetPermutat[n] == letra) {
                        desxifrat += alfabetArry[n];
                        permutaAlfabet(alfabetArry);
                        break;
                    }
                }

            } else if (Character.isLowerCase(letra)) {

                for (int n = 0; n < alfabetPermutat.length; n++) {
                    
                    if (alfabetPermutat[n] == Character.toUpperCase(letra)) {
                        desxifrat += Character.toLowerCase(alfabetArry[n]);
                        permutaAlfabet(alfabetArry);
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
