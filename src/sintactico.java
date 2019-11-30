import java.util.ArrayList;

public class sintactico {
    tabla[] codigo;
    int apuntador;
    int linea;
    public sintactico() {

    }

    public sintactico(tabla[] codigo) {
        this.codigo = codigo;
        apuntador = 0;
        linea = 0;
    }
    public void estructuraBasica (){
        salto();
        if (codigo[apuntador].token == 1){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 100){
                apuntador++;
                salto();
                if (codigo[apuntador].token == 34){
                    apuntador++;
                    salto();

                }
            }
        } error(1);
    }
    public void salto(){
        while(codigo[apuntador].token == 39) {
            apuntador++;
            linea++;
        }
    }
    public void error(int no){
        System.out.println("Error en la linea "+linea);
        System.out.println("Tipo de error: "+tipoError(no));
    }

    public String tipoError(int no){
        String error="";

        return error;
    }

}
