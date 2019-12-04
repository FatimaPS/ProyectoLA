import java.util.ArrayList;

public class sintactico {
    tabla[] codigo;
    ArrayList<tabla> ids;
    ArrayList<tabla> metodos;
    int contIds;
    int contMet;
    int apuntador;
    int linea;
    public sintactico() {

    }

    public sintactico(tabla[] codigo) {
        this.codigo = codigo;
        ids = new ArrayList<>();
        metodos = new ArrayList<>();
        apuntador = 0;
        linea = 0;
        contIds = 101;
        contMet = 501;

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
                    if (codigo[apuntador].token == 2){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 30){
                            apuntador++;
                            salto();
                            asignaciones();
                            metodos();
                            if (codigo[apuntador].token == 31){
                                apuntador++;
                                salto();
                                if (codigo[apuntador].token == 3){
                                    apuntador++;
                                    salto();
                                    if (codigo[apuntador].token == 30){
                                        apuntador++;
                                        salto();
                                        procesos();
                                        if (codigo[apuntador].token == 31){
                                            apuntador++;
                                            salto();
                                            if (codigo[apuntador].token == 4){
                                                apuntador++;
                                                salto();
                                            } else error(1); //.end
                                        } else error(4);  //]
                                    } else error(3); //[
                                } else error(1); //.code
                            } else error(4);//]
                        } else error(3); //[
                    } else error(1); //.var
                } else error(2); //;
            } else error(1); //nombre
        } else error(1);  //.title
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

     boolean valido;
    public boolean asignaciones(){
        valido=true;
        if (codigo[apuntador].token != 31)
            if (codigo[apuntador].token == 101){
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
                        if (codigo[apuntador].token !=31){
                            asignaciones();
                        }
                    } else error(2); //;
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
                                        if (codigo[apuntador].token !=31){
                                            asignaciones();
                                        }
                                    } else error(2); //;
                                } else error(6); //tipo erroneo
                            } else if (codigo[apuntador].token == 25){
                                        apuntador++;
                                        salto();
                                        if (codigo[apuntador].token == 22){
                                            apuntador++;
                                            salto();
                                            if (codigo[apuntador].token == 34){
                                                apuntador++;
                                                salto();
                                                if (codigo[apuntador].token !=31){
                                                    asignaciones();
                                                }
                                            } else error(2); //;
                                        } else error(6);
                                    } else if (codigo[apuntador].token == 26){
                                            apuntador++;
                                            salto();
                                            if (codigo[apuntador].token == 22){
                                                apuntador++;
                                                salto();
                                                if (codigo[apuntador].token == 34){
                                                    apuntador++;
                                                    salto();
                                                    if (codigo[apuntador].token !=31){
                                                        asignaciones();
                                                    }
                                                } else error(2); //;
                                            } else error(6);
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
                                                                if (codigo[apuntador].token !=31){
                                                                    asignaciones();
                                                                }
                                                            } else error(2); //;
                                                        } else error(6);
                                                    } else error(7); //comillas

                                            } else error(7);
                        }

            } else error(5);
        else apuntador++;
        return valido;
    }

    public void metodos(){
        if (codigo[apuntador].token == 501){
            tabla dato = new tabla(codigo[apuntador].nombre,contMet);
            metodos.add(dato);
            contMet++;
            apuntador++;
            salto();
            if (codigo[apuntador].token == 32){
                apuntador++;
                salto();
                if (buscarId(codigo[apuntador].token) || codigo[apuntador].token == 33){
                    boolean puntos = true;
                    while (buscarId(codigo[apuntador].token) && puntos){
                        puntos = false;
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 38) {
                            puntos = true;
                            apuntador++;
                            salto();
                        }
                    }
                    //if ()





                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 38){
                        apuntador++;
                        salto();
                        if (buscarId(codigo[apuntador].token)){ //aqui van los id
                            apuntador++;
                            salto();
                            if (codigo[apuntador].token == 33){
                                apuntador++;
                                salto();
                                if (codigo[apuntador].token == 30){
                                    apuntador++;
                                    salto();
                                    procesos();
                                    if (codigo[apuntador].token == 31){
                                        apuntador++;
                                        salto();
                                    } else error(4);//]
                                } else error(3); //[
                            } else error(4);//}
                        } else error(5); //id
                    } else error(8); // :
                } else error(5);
            } else error(4); //{
        } else error(9);


    }

    public void procesos(){
        loop();
        operaciones();
        llamaMetodo();
        decisionIf();
        decisionElse();
        EntradaSalida();
        comentarios();
    }

    public void loop() {
        if (codigo[apuntador].token == 27){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 32){
                apuntador++;
                salto();
                if (buscarId(codigo[apuntador].token) || codigo[apuntador].token == 24){  //verificar los id
                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 33){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 30){
                            apuntador++;
                            salto();
                            procesos();
                            if (codigo[apuntador].token == 31){
                                apuntador++;
                                salto();
                            } else error(4); //]
                        } else error(3); //[
                    } else error(4); //}
                } else error(5); //no exsiste id
            } else error(3); //{
        } else error(1);
    }

    public void operaciones() {
        if (buscarId(codigo[apuntador].token)){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 40){
                apuntador++;
                salto();

            } else if (codigo[apuntador].token == 41 || codigo[apuntador].token == 42 || codigo[apuntador].token == 43 || codigo[apuntador].token == 44){
                        apuntador++;
                        salto();
                    }
        } else error(5);
    }

    public void llamaMetodo() {
        if (codigo[apuntador].token == 501){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 32){
                apuntador++;
                salto();
                if (codigo[apuntador].token == 101){
                    apuntador++;
                    salto();
                    if (codigo[apuntador].token == 38){
                        apuntador++;
                        salto();
                        if (codigo[apuntador].token == 101){
                            apuntador++;
                            salto();
                            if (codigo[apuntador].token == 33){
                                apuntador++;
                                salto();
                                if (codigo[apuntador].token == 34){
                                    apuntador++;
                                    salto();
                                } //;
                            }
                        }
                    }
                }
            }
        }
    }

    public void decisionIf() {

    }

    public void decisionElse() {
        if (codigo[apuntador].token == 11){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 30){
                apuntador++;
                salto();
                procesos();
                if (codigo[apuntador].token == 31){
                    apuntador++;
                    salto();
                } else error(4);
            } else error(3);
        } else error(1);
    }

    public void EntradaSalida() {
        if (codigo[apuntador].token == 15 || codigo[apuntador].token == 16){
            apuntador++;
            salto();
            if (codigo[apuntador].token == 101){
                apuntador++;
                salto();
                if (codigo[apuntador].token == 34){
                    apuntador++;
                    salto();
                } else error(2);
            } else error(5);
        } else error(1);
    }

    public void comentarios() {
        if (codigo[apuntador].token == 36){
            apuntador++;
            salto();
            while (codigo[apuntador].token == 23){
                apuntador++;
                salto();
            }
            if (codigo[apuntador].token == 37){
                        apuntador++;
                        salto();
            }

        }
    }

    boolean idValido;
    public boolean buscarId(int id){
        idValido = false;
        int i = 0;
        while (i < ids.size()){
            if (ids.get(i).getToken() == id)
                idValido = true;
                i++;
        }
        return idValido;
    }

    boolean metValido;
    public boolean buscarMet(int id){
        metValido = false;
        int i = 0;
        while (i < metodos.size()){
            if (metodos.get(i).getToken() == id)
                metValido = true;
            i++;
        }
        return metValido;
    }


    public String tipoError(int no){
        String error="";

        return error;
    }

}
