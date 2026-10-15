package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private final String alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] alfabetArry = alfabet.toCharArray();
    private char[] alfabetPermutat;

    private Random random;
    private final long clauSecreta = 86; 
    
    public void initRandom(long clauSecreta) { 
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

    @Override
    public TextXifrat xifra(String msg, String clau)
            throws ClauNoSuportada {

        try {
            initRandom(Long.parseLong(clau));
            return new TextXifrat(xifraPoliAlfa(msg).getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(
                    "La clau per xifrat Polialfabetic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)
            throws ClauNoSuportada {

        try {
            initRandom(Long.parseLong(clau));
            return desxifraPoliAlfa(xifrat.toString());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(
                    "La clau de Polialfabetic ha de ser un String convertible a long");
        }
    }

}