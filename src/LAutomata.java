import java.io.*;

public class LAutomata {
    public LAutomata() {
        lexico lexico = new lexico();
        tabla[] tokens = lexico.codigoTokens;
        for (int i = 0; i < tokens.length; i++) {
            System.out.print(i+"-"+tokens[i].token+" ");
        }
        System.out.println();
        sintactico sintactico = new sintactico(tokens);

    }
}
