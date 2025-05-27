package lab3;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        int[] tamanos = {100, 10000, 1000000};
        String[] algoritmos = {"bubbleSort", "insertionSort", "selectionSort", "mergeSort", "quickSort"};

        for (int tamano : tamanos) {
            System.out.println("== Tamaño: " + tamano + " ==");

            ArrayList<Game> juegosOriginal = GenerateData.loadFromFile("data/games_" + tamano + ".csv");

            for (String algoritmo : algoritmos) {
                // Copiar juegos (evita ordenar los mismos)
                ArrayList<Game> copia = new ArrayList<>();
                for (Game g : juegosOriginal) copia.add(g);

                Dataset dataset = new Dataset(copia);

                long inicioOrden = System.nanoTime();
                dataset.sortbyAlgorithm(algoritmo, "price");
                long finOrden = System.nanoTime();

                long tiempoOrden = (finOrden - inicioOrden) / 1_000_000;

                // Buscar un precio aleatorio dentro del rango
                int precioObjetivo = copia.get(copia.size() / 2).getPrice();

                long inicioBusqueda = System.nanoTime();
                ArrayList<Game> encontrados = dataset.getGamesByPrice(precioObjetivo);
                long finBusqueda = System.nanoTime();

                long tiempoBusqueda = (finBusqueda - inicioBusqueda) / 1_000_000;

                System.out.printf("Algoritmo: %-12s | Ordenar: %5d ms | Buscar: %5d ms | Resultados: %d\n",
                        algoritmo, tiempoOrden, tiempoBusqueda, encontrados.size());
            }

            System.out.println();
        }
    }
}