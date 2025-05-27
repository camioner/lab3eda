package lab3;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int[] tamaños = {100, 10000, 1000000};
        String[] algoritmos = {"bubbleSort", "insertionSort", "selectionSort", "mergeSort", "quickSort"};

        for (int tamaño : tamaños) {
            System.out.println("== Tamaño del dataset: " + tamaño + " ==");

            // Cargar datos originales desde archivo
            ArrayList<Game> juegosOriginal = GenerateData.loadFromFile("data/games_" + tamaño + ".csv");

            for (String algoritmo : algoritmos) {
                // Copiar los datos para no alterar los originales
                ArrayList<Game> copia = new ArrayList<>();
                for (Game g : juegosOriginal) copia.add(g);

                Dataset dataset = new Dataset(copia);

                // Medir tiempo de ordenamiento
                long startSort = System.nanoTime();
                dataset.sortbyAlgorithm(algoritmo, "price");
                long endSort = System.nanoTime();
                long tiempoOrden = (endSort - startSort) / 1_000_000;

                // Escoger un valor de precio que sabemos que existe
                int precioObjetivo = copia.get(copia.size() / 2).getPrice();

                // Medir tiempo de búsqueda binaria
                long startSearch = System.nanoTime();
                ArrayList<Game> encontrados = dataset.getGamesByPrice(precioObjetivo);
                long endSearch = System.nanoTime();
                long tiempoBusqueda = (endSearch - startSearch) / 1_000_000;

                System.out.printf("Algoritmo: %-12s | Ordenar: %5d ms | Buscar: %5d ms | Resultados: %d\n",
                        algoritmo, tiempoOrden, tiempoBusqueda, encontrados.size());
            }

            System.out.println();
        }
    }
}
