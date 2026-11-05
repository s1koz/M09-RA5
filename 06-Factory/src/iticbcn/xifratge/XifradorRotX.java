package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau RotX no pot ser nul·la");
        }

        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau no vàlida per a RotX (ha de ser un enter): " + clau);
        }

        char[] chars = msg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + desplaçament);
        }
        return new TextXifrat(new String(chars).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau RotX no pot ser nul·la");
        }
        if (xifrat == null) {
            throw new ClauNoSuportada("No es pot desxifrar un TextXifrat nul");
        }

        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau no vàlida per a RotX (ha de ser un enter): " + clau);
        }

        String msg = new String(xifrat.getBytes());
        char[] chars = msg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - desplaçament);
        }
        return new String(chars);
    }
}