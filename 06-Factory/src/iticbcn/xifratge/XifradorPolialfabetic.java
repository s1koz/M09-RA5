package iticbcn.xifratge;

public class XifradorPolialfabetic implements Xifrador {

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau polialfabètica no pot ser nul·la");
        }
        
        try {
            long seed = Long.parseLong(clau);
            java.util.Random r = new java.util.Random(seed);
            char[] chars = msg.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int shift = r.nextInt(256);
                chars[i] = (char) (chars[i] + shift);
            }
            return new TextXifrat(new String(chars).getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per a Polialfabètic ha de ser un número long vàlid: " + clau);
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau polialfabètica no pot ser nul·la");
        }
        if (xifrat == null) {
            throw new ClauNoSuportada("No es pot desxifrar un TextXifrat nul");
        }

        try {
            long seed = Long.parseLong(clau);
            java.util.Random r = new java.util.Random(seed);
            String msg = new String(xifrat.getBytes());
            char[] chars = msg.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int shift = r.nextInt(256);
                chars[i] = (char) (chars[i] - shift);
            }
            return new String(chars);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per a Polialfabètic ha de ser un número long vàlid: " + clau);
        }
    }
}