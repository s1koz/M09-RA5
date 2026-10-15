public class RotX {

    static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static char[] alfabetMinuscula = alfabet.toCharArray();
    static char[] alfabetMayuscula = alfabet.toUpperCase().toCharArray();

    public static void main(String[] args) {

        String msgs[] = {"ABC", "XYZ", "Hola, Mr.calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        String msgsXifrat = "Úiüht, úiü wx ùxì ív?";

        int posicioXifra = 0;
        int posicioDescifra = 0;

        System.out.println("\nXifrat\n---------");

        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRotX(msgs[i], posicioXifra);
            System.out.printf("(%d)-%-23s => %s%n", posicioXifra,msgs[i], msgsXifrats[i]);

            posicioXifra+=2;
        }

        System.out.println("\nDesxifrat\n---------");

        for (String msg : msgsXifrats) {
            System.out.printf("(%d)-%-23s => %s%n", posicioDescifra,msg, desxifraRotX(msg, posicioDescifra));

            posicioDescifra+=2;
        }

        System.out.println("\nMissatge Xifrat: " + msgsXifrat);
        System.out.println("----------------");

        forcaBrutaRotX(msgsXifrat);
    }

    public static String xifraRotX(String entrada, int posicio) {

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
                        xifrat += alfabetMayuscula[(n + posicio) % alfabetMayuscula.length];
                        break;
                    }
                }

            } else if (esMinuscula) {

                for (int n = 0; n < alfabetMinuscula.length; n++) {
                    if (alfabetMinuscula[n] == letra) {
                        xifrat += alfabetMinuscula[(n + posicio) % alfabetMinuscula.length];
                        break;
                    }
                }

            } else {
                xifrat += letra;
            }
        }

        return xifrat;
    }

    public static String desxifraRotX(String entrada, int posicio) {

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
                        desxifrat += alfabetMayuscula[(n - posicio + alfabetMayuscula.length) % alfabetMayuscula.length];
                        break;
                    }
                }

            } else if (esMinuscula) {

                for (int n = 0; n < alfabetMinuscula.length; n++) {
                    if (alfabetMinuscula[n] == letra) {
                        desxifrat += alfabetMinuscula[(n - posicio + alfabetMinuscula.length) % alfabetMinuscula.length];
                        break;
                    }
                }

            } else {
                desxifrat += letra;
            }
        }

        return desxifrat;
    }

    public static void forcaBrutaRotX(String entrada) {

        String desxifrat = "";

        for (int posicio = 0; posicio < alfabet.length();posicio++) {

            desxifrat = "";

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
                            desxifrat += alfabetMayuscula[(n - posicio + alfabetMayuscula.length) % alfabetMayuscula.length];
                            break;
                        }
                    }

                } else if (esMinuscula) {

                    for (int n = 0; n < alfabetMinuscula.length; n++) {
                        if (alfabetMinuscula[n] == letra) {
                            desxifrat += alfabetMinuscula[(n - posicio + alfabetMinuscula.length) % alfabetMinuscula.length];
                            break;
                        }
                    }

                } else {
                    desxifrat += letra;
                }
            }
            System.out.printf("(%d)->%s%n", posicio, desxifrat);
        }

    }
}