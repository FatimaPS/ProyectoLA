import java.io.*;

public class LAutomata {
    public LAutomata() {
        lexico lexico = new lexico();
        tabla[] tokens = lexico.codigoTokens;
        String salto = "";
        for (int i = 0; i < tokens.length; i++) {
            salto+=tokens[i].token+" ";
        }
        salto = salto.replaceAll("   ", "-");
        System.out.print(salto);
        System.out.println();
    }
}
