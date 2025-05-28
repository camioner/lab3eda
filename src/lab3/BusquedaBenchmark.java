package lab3;

import java.util.ArrayList;

public class BusquedaBenchmark {

    public static void main(String[] args) {
        System.out.println("== Benchmark de búsqueda sobre dataset de 1.000.000 ==");

        ArrayList<Game> original = GenerateData.loadFromFile("data/games_1000000.csv");
        String categoriaObjetivo = original.get(original.size() / 2).getCategory();
        int precioObjetivo = original.get(original.size() / 2).getPrice();

        int rangoMin = precioObjetivo - 10000;
        int rangoMax = precioObjetivo + 10000;
        int calidadObjetivo = original.get(original.size() / 2).getQuality();

        System.out.printf("%-30s %-20s %-15s %-10s\n", "Método", "Tipo de búsqueda", "Tiempo (ms)", "Resultados");

        // getGamesByPrice
        {
            Dataset dataset = new Dataset(new ArrayList<>(original));

            long start = System.nanoTime();
            var res = dataset.getGamesByPrice(precioObjetivo);
            long end = System.nanoTime();

            imprimir("getGamesByPrice", "Linear", end - start, res.size());

            dataset.sortbyAlgorithm("mergeSort", "price");

            start = System.nanoTime();
            res = dataset.getGamesByPrice(precioObjetivo);
            end = System.nanoTime();

            imprimir("getGamesByPrice", "Binary", end - start, res.size());
        }

        // getGamesByPriceRange
        {
            Dataset dataset = new Dataset(new ArrayList<>(original));

            long start = System.nanoTime();
            var res = dataset.getGamesByPriceRange(rangoMin, rangoMax);
            long end = System.nanoTime();

            imprimir("getGamesByPriceRange", "Linear", end - start, res.size());

            dataset.sortbyAlgorithm("mergeSort", "price");

            start = System.nanoTime();
            res = dataset.getGamesByPriceRange(rangoMin, rangoMax);
            end = System.nanoTime();

            imprimir("getGamesByPriceRange", "Binary", end - start, res.size());
        }

        // getGamesByCategory
        {
            Dataset dataset = new Dataset(new ArrayList<>(original));

            long start = System.nanoTime();
            var res = dataset.getGamesByCategory(categoriaObjetivo);
            long end = System.nanoTime();

            imprimir("getGamesByCategory", "Linear", end - start, res.size());

            dataset.sortbyAlgorithm("mergeSort", "category");

            start = System.nanoTime();
            res = dataset.getGamesByCategory(categoriaObjetivo);
            end = System.nanoTime();

            imprimir("getGamesByCategory", "Binary", end - start, res.size());
        }
    }

    private static void imprimir(String metodo, String tipo, long nanosegundos, int cantidad) {
        System.out.printf("%-30s %-20s %-15.2f %-10d\n",
                metodo, tipo, nanosegundos / 1_000_000.0, cantidad);
    }
}
