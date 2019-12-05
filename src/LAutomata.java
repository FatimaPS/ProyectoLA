import java.io.*;

public class LAutomata {
    public LAutomata() {
        lexico lexico = new lexico();
        if (lexico.errores == 0 ){
            tabla[] tokens = lexico.codigoTokens;
            sintactico sintactico = new sintactico(tokens);
        }
    }
}
