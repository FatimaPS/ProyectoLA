import java.io.*;

public class Archivo {
    public String leerCodigo(String ruta){
        int lines = 0;
        //Representa el texto dentro del archivo, por ahora, vacío.
        String texto = "";
        //El archivo de donde sacamos el texto.
        File archivo = new File(ruta);
        try {
            //Le ponemos al lector de archivo 'fr' el atributo 'archivo' en su constructor para leerlo después.
            FileReader fr = new FileReader(archivo);
            //Esta parte ayuda a la eficiencia de la lectura.
            BufferedReader buffer = new BufferedReader(fr);
            //Nos indica cuando es el fin del archivo.
            boolean fin = false;
            //Búfer para guardar las líneas de texto.
            StringBuffer sbf = new StringBuffer();
            //Salto de línea para que el texto salga en líneas separadas (opcional)
            String nl = System.getProperty("line.separator");
            //Mientras no sea el fin del archivo:
            while(fin == false){
                //Leemos una línea del archivo.
                String linea = buffer.readLine();
                lines++;
                //Si la línea no está vacía...
                if(linea != null){
                    //Agregamos al búfer la línea y el salto de línea 'nl' (opcional)
                    sbf.append(linea + nl);
                }
                else{
                    //De lo contrario damos fin al ciclo while.
                    fin = true;
                }
            }
            //Asignamos a 'texto' lo que se agregó al búfer:
            texto = sbf.toString();
        }
        catch (FileNotFoundException e) {
            //Esto se ejecuta si el archivo no fue encontrado.
            e.printStackTrace();
        }
        catch (IOException e) {
            //Esto se ejecuta si hay un error en la lectura del archivo.
            e.printStackTrace();
        }
        //Devolvemos el texto.
        return texto;
    }
}
