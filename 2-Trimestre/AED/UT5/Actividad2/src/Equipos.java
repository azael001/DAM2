public class Equipos {
    private String nombreEquipo;
    private String categoria;
    private int grupo;
    private String sede;
    private String presidente;
    private int puntos;

    public Equipos(String nombreEquipo, String categoria, int grupo, String sede, String presidente, int puntos) {
        this.nombreEquipo = nombreEquipo;
        this.categoria = categoria;
        this.grupo = grupo;
        this.sede = sede;
        this.presidente = presidente;
        this.puntos = puntos;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
