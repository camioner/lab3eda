package lab3;

import java.util.ArrayList;

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

    public ArrayList<Game> sortbyAlgorithm(String algoritmo, String atributo) {
        // ordena en base a uno de esos y un atributo "bubbleSort”, “insertionSort”, “selectionSort”, “mergeSort”, “quickSort”
        switch (algoritmo) {
            case "bubbleSort":
                new bubblesort().sort(data, atributo);
                this.sortedByAttribute = atributo;

                break;
            case "insertionSort":
                new insertionsort().sort(data, atributo);
                this.sortedByAttribute = atributo;

                break;
            case "selectionSort":
                new selectionsort().sort(data, atributo);
                this.sortedByAttribute = atributo;

                break;
            case "mergeSort":
                new merge_sort().sort(data, atributo);
                this.sortedByAttribute = atributo;

                break;
            case "quickSort":
                new quicksort().sort(data, atributo);
                this.sortedByAttribute = atributo;

                break;
            default:
                System.out.println("No se ha seleccionado un algoritmo valido");
        }

        return data;
    }

}