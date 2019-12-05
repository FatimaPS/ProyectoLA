import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;

public class sintactico {
    tabla[] codigo;
    ArrayList<tabla> ids;
    ArrayList<tabla> metodos;
    int contIds;
    int contMet;
    int apuntador;
    int linea;
    public sintactico(tabla[] codigo) {
        this.codigo = codigo;
        ids = new ArrayList<>();
        metodos = new ArrayList<>();
        apuntador = 0;
        linea = 1;
        contIds = 101;
        contMet = 501;
        if (estructuraBasica()){
            System.out.println("Todo ok");
        }
        System.out.println("-----------------------------------------------");
        System.out.println("\n\n");
    }
    public boolean estructuraBasica (){
        boolean correcto = false;
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
                    if (codigo[apuntador].token == 2){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 30){
                            apuntador++;
                            salto();
                            boolean v = true;
                            while (codigo[apuntador].token == 101 && v)
                                v = asignaciones();
                            if (v)
                                while (codigo[apuntador].token == 501 && v)
                                    v = metodos();
                                if (v) {
                                    if (codigo[apuntador].token == 31) {
                                        apuntador++;
                                        salto();
                                        if (codigo[apuntador].token == 3) {
                                            apuntador++;
                                            salto();
                                            if (codigo[apuntador].token == 30) {
                                                apuntador++;
                                                salto();
                                                v = true;
                                                while (codigo[apuntador].token != 31 && v){
                                                    v = procesos();
                                                        apuntador++;
                                                        salto();
                                                }
                                                if (v){
                                                    if (codigo[apuntador].token == 31){
                                                        apuntador++;
                                                        salto();
                                                        if (codigo[apuntador].token == 4) {
                                                            correcto = true;
                                                            apuntador++;
                                                            while(apuntador < codigo.length && correcto){
                                                                if (codigo[apuntador].token != 39)
                                                                    correcto = false;
                                                                else linea++;
                                                                    apuntador++;
                                                            }
                                                            if (!correcto) error(10);

                                                        }
                                                        else {
                                                            error(0); //]
                                                            valido = false;
                                                        }
                                                    } else {
                                                        error(4); //]
                                                        valido = false;
                                                    }
                                                } else valido = false;
                                            } else error(3); //[
                                        } else error(1); //.code
                                    } else error(4);//]
                                }
                        } else error(3); //[
                    } else error(1); //.var
                } else error(2); //;
            } else error(1); //nombre
        } else error(1);  //.title
        return correcto;
    }
    public void salto(){
        while(codigo[apuntador].token == 39) {
                apuntador++;
                linea++;
        }
    }
    public void saltoAtras(){
        apuntador--;
        while(codigo[apuntador].token == 39) {
            apuntador--;
            linea--;
        }
    }
    public void error(int no){
        System.out.println("Error en la linea "+linea);
        System.out.println("Tipo de error: "+tipoError(no));
    }

    boolean valido;
    public boolean asignaciones(){
        valido=true;
        tabla dato = new tabla(codigo[apuntador].nombre,contIds);
        ids.add(dato);
        contIds++;
        apuntador++;
        salto();
        if (codigo[apuntador].token == 20 || codigo[apuntador].token == 21 || codigo[apuntador].token == 22 ){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 34){
                apuntador++;
                salto();
            } else {
                valido=false;
                error(2); //;
            }
        } else if (codigo[apuntador].token == 40){
                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 24){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 21){
                            apuntador++;
                            salto();
                            if (codigo[apuntador].token == 34){
                                apuntador++;
                                salto();
                            } else {
                                valido=false;
                                error(2); //;
                            }
                        } else {
                            valido=false;
                            error(6); //tipo erroneo
                        }
                    } else if (codigo[apuntador].token == 25 || codigo[apuntador].token == 26){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 22){
                            apuntador++;
                            salto();
                            if (codigo[apuntador].token == 34){
                                apuntador++;
                                salto();
                            } else {
                                valido=false;
                                error(2); //;
                            }
                        } else {
                            valido=false;
                            error(6);
                        }
                    } else if (codigo[apuntador].token == 35){
                            apuntador++;
                            salto();
                            while (codigo[apuntador].token == 23){
                                apuntador++;
                                salto();
                            }
                            if (codigo[apuntador].token == 35){
                                apuntador++;
                                salto();
                                if (codigo[apuntador].token == 20){
                                    apuntador++;
                                    salto();
                                    if (codigo[apuntador].token == 34){
                                        apuntador++;
                                        salto();
                                    } else {
                                        valido=false;
                                        error(2); //;
                                    }
                                } else {
                                    valido=false;
                                    error(6);
                                }
                            } else {
                                valido=false;
                                error(7); //comillas
                            }
                    } else {
                        valido=false;
                        error(7);
                    }
                }

        return valido;
    }

    public boolean metodos() {
        valido = true;
        tabla dato = new tabla(codigo[apuntador].nombre, contMet);
        metodos.add(dato);
        contMet++;
        apuntador++;
        salto();
        if (codigo[apuntador].token == 32) {
            apuntador++;
            salto();
            if (buscarId(codigo[apuntador].nombre) || codigo[apuntador].token == 33) {
                boolean puntos = true;
                while (buscarId(codigo[apuntador].nombre) && puntos) {
                    puntos = false;
                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 38) {
                        puntos = true;
                        apuntador++;
                        salto();
                    }
                }
                if (!puntos) {
                    if (codigo[apuntador].token == 33) {
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 30) {
                            apuntador++;
                            salto();
                            boolean v = true;
                            while (codigo[apuntador].token != 31 && v){
                                v = procesos();
                                apuntador++;
                                salto();
                            }
                            if (v){
                                if (codigo[apuntador].token == 31){
                                    apuntador++;
                                    salto();
                                } else {
                                    error(4); //]
                                    valido = false;
                                }
                            } else valido = false;
                        } else{
                            error(4);
                            valido = false;
                        }
                    } else {
                        error(3);
                        valido = false;
                    }
                } else {
                    error(10);
                    valido = false;
                }
            } else {
                error(4);
                valido = false;
            }
        }else {
            error(3);
            valido = false;
        }
        return valido;
    }

    public boolean procesos(){
        valido = true;
        int opc = codigo[apuntador].token;
        switch (opc){
            case 27: valido = loop();
            break;
            case 10: valido = decisionIfElse();
            break;
            case 15: valido = EntradaSalida();
            break;
            case 16: valido = EntradaSalida();
            break;
            case 36: valido = comentarios();
            break;
            default:
                if (buscarMet(codigo[apuntador].nombre)){
                    valido = llamaMetodo();
                } else if (buscarId(codigo[apuntador].nombre))
                    valido = operaciones();
                else {
                    valido = false;
                    error(10);
                }
        }


        return valido;
    }

    public boolean loop() {
        valido = true;

        apuntador++;
        salto();
        if (codigo[apuntador].token == 32){
            apuntador++;
            salto();
            if (buscarId(codigo[apuntador].nombre) || codigo[apuntador].token == 24){  //verificar los id
                apuntador++;
                salto();
                if (codigo[apuntador].token == 33){
                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 30){
                        apuntador++;
                        salto();
                        boolean v = true;
                        while (codigo[apuntador].token != 31 && v){
                            v = procesos();
                            apuntador++;
                            salto();
                        }
                        if (v){
                            if (codigo[apuntador].token == 31){
                            } else {
                                error(4); //]
                                valido = false;
                            }
                        } else valido = false;
                    } else {
                        error(3); //]
                        valido = false;
                    }
                } else {
                    error(4); //]
                    valido = false;
                }
            } else {
                error(5); //]
                valido = false;
            }
        } else {
            error(3); //]
            valido = false;
        }
        return valido;
    }

    public boolean operaciones() {
        valido = true;
        if (buscarId(codigo[apuntador].nombre)){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 40){
                apuntador++;
                salto();
                if (codigo[apuntador].token == 24 || buscarId(codigo[apuntador].nombre)){
                    apuntador++;
                    salto();
                } else if (codigo[apuntador].token == 35){
                    apuntador++;
                    salto();
                    while (codigo[apuntador].token == 23 || codigo[apuntador].token == 39 ){
                        apuntador++;
                    }
                    if (codigo[apuntador].token == 35) {
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 34){
                        } else {
                            error(2);
                            valido = false;
                        }
                    } else {
                        error(7);
                        valido = false;
                    }
                } else {
                    error(14);
                    valido = false;
                }
            } else if (codigo[apuntador].token >= 41 && codigo[apuntador].token <= 44){
                apuntador++;
                salto();
                if (codigo[apuntador].token == 24 || buscarId(codigo[apuntador].nombre)) {
                    apuntador++;
                    salto();
                    boolean puntos = true;
                    while (codigo[apuntador].token == 38 && puntos) {
                        puntos = false;
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 24 || buscarId(codigo[apuntador].nombre)) {
                            puntos = true;
                            apuntador++;
                            salto();
                        }
                    }
                    if (puntos){
                        if (codigo[apuntador].token != 34){

                        }
                    }else {
                        error(11);
                        valido = false;
                    }
                }  else {
                    error(11);
                    valido = false;
                }
            }else {
                error(15);
                valido = false;
            }
        } else {
            valido = false;
            error(5);
        }
        return valido;
    }

    public boolean llamaMetodo() {
        valido = true;
        apuntador++;
        salto();
        if (codigo[apuntador].token == 32){
            apuntador++;
            salto();
            if (buscarId(codigo[apuntador].nombre) || codigo[apuntador].token == 33) {
                boolean puntos = true;
                while (buscarId(codigo[apuntador].nombre) && puntos) {
                    puntos = false;
                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 38) {
                        puntos = true;
                        apuntador++;
                        salto();
                    }
                }
                if (!puntos){
                    if (codigo[apuntador].token == 33){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 34){

                        }  else {
                            error(10);
                            valido = false;
                        }
                    } else {
                        error(4);
                        valido = false;
                    }
                }else {
                    error(11);
                    valido = false;
                }
            }  else {
                error(11);
                valido = false;
            }
        }  else {
            error(3);
            valido = false;
        }

        return valido;
    }

    public boolean decisionIfElse() {
        valido = true;
        apuntador++;
        salto();
        if (codigo[apuntador].token == 32){
            apuntador++;
            salto();
            if (buscarId(codigo[apuntador].nombre) || codigo[apuntador].token == 24) {
                apuntador++;
                salto();
                if (codigo[apuntador].token >= 45 && codigo[apuntador].token <= 48) {
                    apuntador++;
                    salto();
                    if (buscarId(codigo[apuntador].nombre) || codigo[apuntador].token == 24) {
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 33) {
                            apuntador++;
                            salto();
                            if (codigo[apuntador].token == 30){
                                apuntador++;
                                salto();
                                boolean v = true;
                                while (codigo[apuntador].token != 31 && v){
                                    v = procesos();
                                    if (v){
                                        apuntador++;
                                        salto();
                                    }
                                }
                                if (v){
                                    if (codigo[apuntador].token == 31){
                                        apuntador++;
                                        salto();
                                        if (codigo[apuntador].token == 11) {
                                            apuntador++;
                                            salto();
                                            valido = decisionElse();
                                        } else saltoAtras();
                                    } else {
                                        error(4);
                                        valido = false;
                                    }
                                } else valido = false;
                            } else {
                                error(3);
                                valido = false;
                            }
                        } else {
                            error(4);
                            valido = false;
                        }
                    } else {
                        error(11);
                        valido = false;
                    }
                } else {
                    error(12);
                    valido = false;
                }
            } else {
                error(11);
                valido = false;
            }
        }  else {
            error(3);
            valido = false;
        }

        return valido;
    }

    public boolean decisionElse() {
        valido = true;
        if (codigo[apuntador].token == 30){
            apuntador++;
            salto();
            boolean v = true;
            while (codigo[apuntador].token != 31 && v){
                v = procesos();
                apuntador++;
                salto();
            }
            if (v){
                if (codigo[apuntador].token == 31){
                } else {
                    error(4);
                    valido = false;
                }
            }else valido = false;
        } else {
            error(3);
            valido = false;
        }
       return valido;
    }

    public boolean EntradaSalida() {
        valido = true;
        apuntador++;
        salto();
        if (buscarId(codigo[apuntador].nombre)){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 34){
            } else {
                error(2);
                valido = false;
            }
        } else {
            error(5);
            valido = false;
        }
        return valido;
    }

    public boolean comentarios() {
        valido = true;
            apuntador++;
            boolean com = true;
            while (com){
                if (codigo[apuntador].token == 23 || codigo[apuntador].token == 39 && apuntador+1 != codigo.length)
                    apuntador++;
                else com = false;
            }
            if (codigo[apuntador].token == 37){

            }else {
                error(13);
                valido = false;
                apuntador--;
            }
        return valido;
    }

    boolean idValido;
    public boolean buscarId(String nombre){
        idValido = false;
        int i = 0;
        while (i < ids.size()){
            if (ids.get(i).nombre.equals(nombre)) {
                idValido = true;
            }
            i++;
        }
        return idValido;
    }

    boolean metValido;
    public boolean buscarMet(String nombre){
        metValido = false;
        int i = 0;
        while (i < metodos.size()){
            if (metodos.get(i).nombre.equals(nombre))
                metValido = true;
            i++;
        }
        return metValido;
    }


    public String tipoError(int no){
        String error="";
        Archivo archivo = new Archivo();
        ArrayList<tabla> erro = new ArrayList<>();
        String[] errores = archivo.leerCodigo("errores.txt").split("\n");
        tabla dato;
        for (int i = 0; i < errores.length; i++) {
            String[] separador = errores[i].split("_");
            String nombre = separador[1];
            String token = separador[0];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            erro.add(dato);
        }
        for (tabla d: erro){
            if (d.token == no) error = d.nombre;
        }
        return error;
    }

}
