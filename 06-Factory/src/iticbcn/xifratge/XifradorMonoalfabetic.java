package iticbcn.xifratge;

public class XifradorMonoalfabetic implements Xifrador {

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau monoalfabètica no pot ser nul·la");
        }
        
        char[] chars = msg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + 1);
        }
        return new TextXifrat(new String(chars).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau monoalfabètica no pot ser nul·la");
        }
        if (xifrat == null) {
            throw new ClauNoSuportada("No es pot desxifrar un TextXifrat nul");
        }

        String msg = new String(xifrat.getBytes());
        char[] chars = msg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - 1);
        }
        return new String(chars);
    }
}