
class Estadio implements Comparable<Estadio> {
    String nombre, ciudad, estado, pais;
    int capacidad, anioInauguracion;

    public Estadio(String nombre, String ciudad, String estado, String pais, int capacidad, int anioInauguracion) {
        this.nombre = nombre.toUpperCase();
        this.ciudad = ciudad.toUpperCase();
        this.estado = estado.toUpperCase();
        this.pais = pais.toUpperCase();
        this.capacidad = capacidad;
        this.anioInauguracion = anioInauguracion;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s, %s | Capacidad: %d | Inauguración: %d",
                nombre, ciudad, estado, pais, capacidad, anioInauguracion);
    }

    @Override
    public int compareTo(Estadio otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Estadio)) return false;
        Estadio otro = (Estadio) obj;
        return this.nombre.equalsIgnoreCase(otro.nombre);
    }


}