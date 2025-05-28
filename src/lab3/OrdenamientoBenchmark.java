package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdenamientoBenchmark {

    static String[] algoritmos = {
            "bubbleSort", "insertionSort", "selectionSort",
            "mergeSort", "quickSort", "collectionsSort","countingSort"
    };
    static int[] tamaños = {100, 10000, 1000000};
    static String[] atributos = {"price", "quality", "category"};

    public static void main(String[] args) {
        for (String atributo : atributos) {
            System.out.println("\n=== Ordenamiento por atributo: " + atributo.toUpperCase() + " ===");
            System.out.printf("%-15s %-15s %-20s\n", "Algoritmo", "Tamaño", "Promedio (ms)");

            for (String algoritmo : algoritmos) {
                for (int tamaño : tamaños) {
                    if (algoritmo.equals("countingSort") && !atributo.equals("quality")) continue;
                    // Saltar los algoritmos O(n^2) si el tamaño es 1 millón
                    if (tamaño == 1000000 &&
                            (algoritmo.equals("bubbleSort") ||
                                    algoritmo.equals("insertionSort") ||
                                    algoritmo.equals("selectionSort"))) {
                        System.out.printf("%-15s %-15d %-20s\n", algoritmo, tamaño, "> 300.000");
                        continue;
                    }

                    long totalTiempo = 0;

                    for (int intento = 1; intento <= 3; intento++) {
                        ArrayList<Game> original = GenerateData.loadFromFile("data/games_" + tamaño + ".csv");
                        ArrayList<Game> copia = new ArrayList<>(original);
                        Dataset dataset = new Dataset(copia);

                        long inicio = System.nanoTime();

                        if (algoritmo.equals("collectionsSort")) {
                            Comparator<Game> comparador = getComparator(atributo);
                            Collections.sort(copia, comparador);
                            dataset.sortedByAttribute = atributo;
                        } else {
                            dataset.sortbyAlgorithm(algoritmo, atributo);
                        }

                        long fin = System.nanoTime();
                        long tiempoMs = (fin - inicio) / 1_000_000;

                        totalTiempo += tiempoMs;
                    }

                    long promedio = totalTiempo / 3;
                    System.out.printf("%-15s %-15d %-20d\n", algoritmo, tamaño, promedio);
                }
            }
        }
    }

    private static Comparator<Game> getComparator(String atributo) {
        return switch (atributo) {
            case "price" -> Comparator.comparingInt(Game::getPrice);
            case "quality" -> Comparator.comparingInt(Game::getQuality);
            case "category" -> Comparator.comparing(Game::getCategory);
            default -> throw new IllegalArgumentException("Atributo no válido");
        };
    }
}