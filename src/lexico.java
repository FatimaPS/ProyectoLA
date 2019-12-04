import javax.swing.*;
import java.util.ArrayList;

public class lexico {
    private String code;
    Archivo archivo;
    tabla[] codigoTokens;
    ArrayList<String> codigoString;
    ArrayList<tabla> palabrasReservadas;
    ArrayList<tabla> delimitadores;
    ArrayList<tabla> operadores;
    ArrayList<tabla> letras;
    ArrayList<tabla> digitos;
    ArrayList<tabla> nombre;
    int idToken = 101;
    int nombreToken = 100;
    int metodoToken = 501;
    public lexico() {
        archivo = new Archivo();
        palabrasReservadas = new ArrayList<>();
        delimitadores = new ArrayList<>();
        operadores = new ArrayList<>();
        codigoString = new ArrayList<>();
        letras = new ArrayList<>();
        digitos = new ArrayList<>();
        nombre = new ArrayList<>();
        code = archivo.leerCodigo("code.txt");
        codigoTokens = new tabla[getTamano()];
        cargarLenguaje();
        int errores = analizar();
        if (errores != 0) System.out.println(codigoString.get(errores)+" No pertenece al lenguaje");
    }
    int analizar(){
        int error = 0;
        int i = 0;
        boolean valido = true;
        while (i < codigoTokens.length && valido){
            String palabra = codigoString.get(i);
            int token = buscar(palabra);
            if (token != 0) {
                codigoTokens[i].setToken(token);
                codigoTokens[i].setNombre(codigoString.get(i));
            }
            else{
                valido = false;
            }
            i++;

        }
        if (!valido) error = i-1;
        return error;
    }

    boolean coco = false;
    int buscar(String dato) {
        int existe = 0;
        int i ;
        if (!dato.equals("\n")) {
            if ((dato.equals("\"") || dato.equals("<!")) && !coco) coco = true;
            else if ((dato.equals("\"") || dato.equals("!>")) && coco) coco = false;
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
                if (dato.length() >= 1 && existe == 0) { // nombres de id y metodos mayor a una letra o digito
                    existe = verificarNombre(dato);
                }
            } else {
                if (dato.equals("\"")) existe = 35;
                else if (dato.equals("<!")) existe = 36;
                else existe = 23;
            }
        }else existe = 39;
        return existe;
    }
    int verificarNombre(String dato){
        int no = 0;
        boolean b = true;
        boolean aux;
        int i = 1;
        String l1 = ""+dato.charAt(0);
        String l2 = ""+dato.charAt(1);
        boolean letra = false;
        if (l1.equals("$") || l1.equals("#")){
            for (tabla d : letras)
                if (l2.equals(d.getNombre())) letra = true;
        }
        else{
            for (tabla p : letras)
                if (l1.equals(p.getNombre())) letra = true;
        }
        if (letra){
            while (i < dato.length() && b){
                int j = 0;
                aux = true;
                l1 = ""+dato.charAt(i);
                while(j < nombre.size() && aux){
                    if (l1.equals(nombre.get(j).getNombre())){
                        aux = false;
                    }
                    j++;
                }
                if (aux) b = false;
                i++;
            }
            if (b){
                l1 = ""+dato.charAt(0);
                if (l1.equals("$")) no =  idToken;
                else if (l1.equals("#")) no =  metodoToken;
                else no = nombreToken;
            }
        }

        return no;
    }


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
        for (int i = 0; i < codigoTokens.length; i++) {
            codigoTokens[i] = new tabla();
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
