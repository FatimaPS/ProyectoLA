public class tabla {
    String nombre;
    int token;
    public tabla() {
    }

    public tabla(String nombre, int token) {
        this.nombre = nombre;
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
