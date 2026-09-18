public class Rot13 {

    static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static char[] alfabetMinuscula = alfabet.toCharArray();
    static char[] alfabetMayuscula = alfabet.toUpperCase().toCharArray();

    public static void main(String[] args) {

        String msgs[] = {"ABC", "XYZ", "Hola, Mr.calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n---------");

        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifrat\n---------");

        for (String msg : msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desxifraRot13(msg));
        }
    }

    public static String xifraRot13(String entrada) {

        String xifrat = "";

        for (int i = 0; i < entrada.length(); i++) {

            char letra = entrada.charAt(i);

            boolean esMayuscula = false;
            boolean esMinuscula = false;

            for (int n = 0; n < alfabetMayuscula.length; n++) {
                if (alfabetMayuscula[n] == letra) {
                    esMayuscula = true;
                    break;
                }
            }

            for (int n = 0; n < alfabetMinuscula.length; n++) {
                if (alfabetMinuscula[n] == letra) {
                    esMinuscula = true;
                    break;
                }
            }

            if (esMayuscula) {

                for (int n = 0; n < alfabetMayuscula.length; n++) {
                    if (alfabetMayuscula[n] == letra) {
                        xifrat += alfabetMayuscula[(n + 13) % alfabetMayuscula.length];
                        break;
                    }
                }

            } else if (esMinuscula) {

                for (int n = 0; n < alfabetMinuscula.length; n++) {
                    if (alfabetMinuscula[n] == letra) {
                        xifrat += alfabetMinuscula[(n + 13) % alfabetMinuscula.length];
                        break;
                    }
                }

            } else {
                xifrat += letra;
            }
        }

        return xifrat;
    }

    public static String desxifraRot13(String entrada) {

        String desxifrat = "";

        for (int i = 0; i < entrada.length(); i++) {

            char letra = entrada.charAt(i);

            boolean esMayuscula = false;
            boolean esMinuscula = false;

            for (int n = 0; n < alfabetMayuscula.length; n++) {
                if (alfabetMayuscula[n] == letra) {
                    esMayuscula = true;
                    break;
                }
            }

            for (int n = 0; n < alfabetMinuscula.length; n++) {
                if (alfabetMinuscula[n] == letra) {
                    esMinuscula = true;
                    break;
                }
            }

            if (esMayuscula) {

                for (int n = 0; n < alfabetMayuscula.length; n++) {
                    if (alfabetMayuscula[n] == letra) {
                        desxifrat += alfabetMayuscula[(n - 13 + alfabetMayuscula.length) % alfabetMayuscula.length];
                        break;
                    }
                }

            } else if (esMinuscula) {

                for (int n = 0; n < alfabetMinuscula.length; n++) {
                    if (alfabetMinuscula[n] == letra) {
                        desxifrat += alfabetMinuscula[(n - 13 + alfabetMinuscula.length) % alfabetMinuscula.length];
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