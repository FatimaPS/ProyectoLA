import java.io.*;

public class LAutomata {
    public LAutomata() {
        lexico lexico = new lexico();
        tabla[] tokens = lexico.codigoTokens;
        sintactico sintactico = new sintactico(tokens);
    }
}
