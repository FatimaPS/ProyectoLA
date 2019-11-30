import javax.swing.*;
import java.util.ArrayList;

public class lexico {
    private String code;
    Archivo archivo;
    ArrayList<tabla> codigoTokens;
    ArrayList<String> codigoString;
    ArrayList<tabla> palabrasReservadas;
    ArrayList<tabla> delimitadores;
    ArrayList<tabla> operadores;
    ArrayList<tabla> letras;
    ArrayList<tabla> digitos;
    ArrayList<tabla> nombre;
    int contadorIdMetodo;
    public lexico() {
        contadorIdMetodo=100;
        archivo = new Archivo();
        palabrasReservadas = new ArrayList<>();
        delimitadores = new ArrayList<>();
        operadores = new ArrayList<>();
        codigoString = new ArrayList<>();
        letras = new ArrayList<>();
        digitos = new ArrayList<>();
        nombre = new ArrayList<>();
        codigoTokens = new ArrayList<>();
        cargarLenguaje();
        code = archivo.leerCodigo("code.txt");
        int errores = analizar();
        System.out.println();
        for (int i = 0; i < codigoTokens.size(); i++) {
            System.out.print(codigoTokens.get(i)+" ");
        }
        System.out.println();
        if (errores != 0) System.out.println(codigoString.get(errores)+" No pertenece al lenguaje");
    }


    int analizar(){
        int error = 0;
        int i = 0;
        boolean valido = true;
        while (i < codigoTokens.size() && valido){
            String palabra = codigoString.get(i);
            //System.out.print(palabra);
            int token = buscar(palabra);
            if (token != 0) codigoTokens.get(i).setToken(i); // = token;
            else valido = false;
            i++;
        }
        if (!valido) error = i-1;
        return error;
    }

    boolean coco = false;
    int buscar(String dato) {
        System.out.print(dato);
        int existe = 0;
        int i ;
        if (!dato.equals("\n")) {
            if ((dato.equals("\"") || dato.equals("<!")) && !coco) coco = true;
            else if ((dato.equals("\"") || dato.equals(">!")) && coco) coco = false;
            if (!coco) {
                i = 0;
                while (i < palabrasReservadas.size() && existe == 0) {
                    if (palabrasReservadas.get(i).getNombre().equals(dato))
                        existe = palabrasReservadas.get(i).getToken();
                    i++;
                }
                i = 0;
                while (i < operadores.size() && existe == 0) {
                    if (operadores.get(i).getNombre().equals(dato)) existe = operadores.get(i).getToken();
                    i++;
                }
                i = 0;
                while (i < delimitadores.size() && existe == 0) {
                    if (delimitadores.get(i).getNombre().equals(dato)){

                        existe = delimitadores.get(i).getToken();
                    }
                    i++;
                }
                if (dato.length() > 1 && existe == 0) { // nombres de id y metodos mayor a una letra o digito
                    String c0 = "" + dato.charAt(0);
                    if (c0.equals("$") || c0.equals("#")) existe = verificarNombre(dato);
                }
            } else {
                existe = 800;
            }
        }else existe = 39;
        return existe;
    }
    int verificarNombre(String dato){
        int no = 0;
        String l = ""+dato.charAt(1);
        boolean letra = false;
        for (tabla d: letras) {
            if (l.equals(d.getNombre())) letra = true;
        }
        if (letra){
            boolean b = true;
            boolean aux;
            int i = 1, ok = 0;
            while (i < dato.length() && b){
                int j = 0;
                ok = 0;
                aux = true;
                l = ""+dato.charAt(i);
                while(j < nombre.size() && aux){
                    if (l.equals(nombre.get(j).getNombre())){
                        ok = 1;
                        aux = false;
                    }
                    j++;
                }
                if (ok == 0) b = false;
                i++;
            }
            if (b){
                l = ""+dato.charAt(0);
                if (l.equals("$")) no =  id_metodo; // codigo para ids
                else no = id_metodo;
            }
        }
        return no;
    }
    int id_metodo = 101;
    void cargarLenguaje(){
        String[] prs = archivo.leerCodigo("palabrasReservadas.txt").split("\n");
        String[] ds = archivo.leerCodigo("delimitadores.txt").split("\n");
        String[] ops = archivo.leerCodigo("operadores.txt").split("\n");
        String[] nms = archivo.leerCodigo("numeros.txt").split("\n");
        String[] lt = archivo.leerCodigo("letras.txt").split("\n");
        String[] nomb = archivo.leerCodigo("nombre.txt").split("\n");
        tabla dato;
        for (int i = 0; i < prs.length; i++) {
            String[] separador = prs[i].split(" ");
            String nombre = separador[0];
            String token = separador[1];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            palabrasReservadas.add(dato);
        }
        for (int i = 0; i < ds.length; i++) {
            String[] separador = ds[i].split(" ");
            String nombre = separador[0];
            String token = separador[1];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            delimitadores.add(dato);
        }
        for (int i = 0; i < ops.length; i++) {
            String[] separador = ops[i].split(" ");
            String nombre = separador[0];
            String token = separador[1];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            operadores.add(dato);
        }
        for (int i = 0; i < nms.length; i++) {
            String[] separador = nms[i].split(" ");
            String nombre = separador[0];
            String token = separador[1];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            digitos.add(dato);
        }
        for (int i = 0; i < lt.length; i++) {
            String[] separador = lt[i].split(" ");
            String nombre = separador[0];
            String token = separador[1];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            letras.add(dato);
        }
        for (int i = 0; i < nomb.length; i++) {
            String[] separador = nomb[i].split(" ");
            String nombre = separador[0];
            String token = separador[1];
            dato = new tabla(nombre, Integer.parseInt(token.trim()));
            this.nombre.add(dato);
        }
    }
    int getTamano(){
        int tamano = 0;
        String[] lineas = code.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            String[] palabras = lineas[i].split(" ");
            for (int j = 0; j < palabras.length; j++) {
                codigoString.add(palabras[j].trim());
            }
            codigoString.add("\n");
            tamano+= palabras.length;
            tamano++;
        }
        return tamano;
    }
    void mostrarLenguaje(){
        System.out.println("Palabras reservadas");
        for (tabla dato: palabrasReservadas) {
            System.out.println("-> "+dato.getNombre()+"  "+dato.getToken());
        }
        System.out.println("Delimitadores");
        for (tabla dato: delimitadores) {
            System.out.println("-> "+dato.getNombre()+"  "+dato.getToken());
        }
        System.out.println("operadores");
        for (tabla dato: operadores) {
            System.out.println("-> "+dato.getNombre()+"  "+dato.getToken());
        }
    }
}
