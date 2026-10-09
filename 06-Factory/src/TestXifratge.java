public class TestXifratge {
    public static void main(String[] args) {
        AlgorismeFactory[] aFactory = {
            new AlgorismeAES(), new AlgorismeMonoalfabetic(),
            new AlgorismePolialfabetic(), new AlgorismeRotX()
        };

        String[] aNames = {"AES", "Monoalfabetic", "Polialfabetic", "RotX"};

        String[] msgs = {"Test 01: Àlgid, Ülrich, Vàlid",
                        "Test 02: Caràcters especials ¡!¿?*-123[]{}@#"};

        String[][] claus = {{"Claus Secreta 01"}, {"ErrorClau", null},
                        {"ErrorClaus2","123456"},{"-1","13","1000","Errorclau"}};

        for (int i = 0; i<aFactory.length;i++) {
            AlgorismeFactory alg = aFactory[i];
            String nom = aNames[i];

            Xifrador xifrador = alg.creaXifrador();

            System.out.println("=============================");

            for (String msg : msgs) {
                for (String clau : claus[i]) {
                    System.out.println("msg: " + msg);
                    System.out.println("Algorisme: " + nom);
                    System.out.println("Clau: " + clau);
                    TextXifrat tx = null;
                    try {
                        tx = xifrador.xifra(msg, clau);
                    } catch (ClauNoSuportada e) {
                            System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("TextXifrat: " + txt);

                    String desxifrat = null;

                    try {
                            desxifrat = xifrador.desxifrat(tx, clau);
                    } catch (ClauNoSuportada e) {
                            System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("Desxifrat: " + desxifrat);
                    System.out.println("------------");
                }
            }
        }
    }
}
