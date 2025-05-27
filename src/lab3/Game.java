package lab3;
import java.util.ArrayList;


class Game {
    String nombre;
    String categoria;
    int precio;
    int calidad;

    public Game(String nombre, String categoria, int precio, int calidad) {
        this.calidad = calidad;
        this.categoria = categoria;
        this.precio = precio;
        this.nombre = nombre;
    }

    public String getName() {
        return nombre;
    }

    public String getCategory() {
        return categoria;
    }

    public int getPrice() {
        return precio;
    }

    public int getQuality() {
        return calidad;
    }
    public String toCSV() {
        return this.nombre + "," + this.categoria + "," + this.precio + "," + this.calidad;
    }
}