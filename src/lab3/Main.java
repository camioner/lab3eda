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

    public ArrayList<Game> getGamesByPrice(int price) {
        if (!"price".equals(sortedByAttribute)) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game g : data) {
                if (g.getPrice() == price) result.add(g);
            }
            return result;
        }

        return new BinarySearch().search(data, "price", "exact", Integer.toString(price), null);
    }

    public ArrayList<Game> getGamesByPriceRange(int lo, int hi) {
        if (!"price".equals(sortedByAttribute)) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game g : data) {
                if (g.getPrice() >= lo && g.getPrice() <= hi) result.add(g);
            }
            return result;
        }

        return new BinarySearch().search(data, "price", "range", Integer.toString(lo), Integer.toString(hi));
    }

    public ArrayList<Game> getGamesByQuality(int quality) {
        if (!"quality".equals(sortedByAttribute)) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game g : data) {
                if (g.getQuality() == quality) result.add(g);
            }
            return result;
        }

        return new BinarySearch().search(data, "quality", "exact", Integer.toString(quality), null);
    }

    public ArrayList<Game> getGamesByCategory(String categoria) {
        if (!"category".equals(sortedByAttribute)) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game g : data) {
                if (g.getCategory().equals(categoria)) result.add(g);
            }
            return result;
        }

        return new BinarySearch().search(data, "category", "exact", categoria, null);
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