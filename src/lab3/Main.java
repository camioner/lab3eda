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
}

class Dataset {
    ArrayList<Game> data;
    //    ArrayList<Game> auxiiar;
    String sortedByAttribute; //quality , categoria o precio

    public Dataset(ArrayList<Game> games) {
        this.data = games;
    }

    public ArrayList<Game> getGamesByPrice(int price) { //retorna los juegos con el precio
        ArrayList<Game> resutados = new ArrayList<Game>();

        for (Game g : data) {
            if (g.getPrice() == price) {
                resutados.add(g);
            }
        }
        return resutados;
    }

    public ArrayList<Game> getGamesByPriceRange(int lo, int hi) { // retorna los juegos dentro del rango de precio
        ArrayList<Game> resutados = new ArrayList<Game>();

        for (Game g : data) {
            if (g.getPrice() >= lo && g.getPrice() <= hi) {
                resutados.add(g);
            }
        }
        return resutados;
    }

    public ArrayList<Game> getGamesByCategory(String categoria) { // retorna todos los juegos con la categoria dada
        ArrayList<Game> resutados = new ArrayList<Game>();

        for (Game g : data) {
            if (g.getCategory() == categoria) {
                resutados.add(g);
            }
        }
        return resutados;
    }

    public ArrayList<Game> getGetGamesByQuality(int Quality) { // retorna los juegos con la calidad dada
        ArrayList<Game> resutados = new ArrayList<Game>();

        for (Game g : data) {
            if (g.getQuality() == Quality) {
                resutados.add(g);
            }
        }
        return resutados;
    }

    public ArrayList<Game> SortbyAlgorithm(String algoritmo, String atributo) {
        // ordena en base a uno de esos y un atributo "bubbleSort”, “insertionSort”, “selectionSort”, “mergeSort”, “quickSort”
        switch (algoritmo) {
            case "bubbleSort":
                new bubblesort().sort(data, atributo);

                break;
            case "insertionSort":
                new insertionsort().sort(data, atributo);
                break;
            case "selectionSort":
                new selectionsort().sort(data, atributo);
                break;
            case "mergeSort":
                new merge_sort().sort(data, atributo);
                break;
            case "quickSort":
                new quicksort().sort(data, atributo);
                break;
            default:
                System.out.println("No se ha seleccionado un algoritmo valido");
        }

        return data;
    }

}

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

    }
}