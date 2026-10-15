package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador {

    private final String alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] alfabetArry = alfabet.toCharArray();
    private final char[] alfabetPermutat;

    public XifradorMonoalfabetic() {
        alfabetPermutat = permutaAlfabet(alfabetArry);
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

    @Override
    public TextXifrat xifra(String msg, String clau)
            throws ClauNoSuportada {

        if (clau != null) {
            throw new ClauNoSuportada("Clau no suportada");
        }

        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)
            throws ClauNoSuportada {

        if (clau != null) {
            throw new ClauNoSuportada("Clau no suportada");
        }

        return desxifraMonoAlfa(xifrat.toString());
    }
}