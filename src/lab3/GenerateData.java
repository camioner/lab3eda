package lab3;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class GenerateDataa {
    static Random random = new Random();
    static String[] nombres = {"Galaxy", "Legend", "Warrior", "Empire", "Dragon", "Last"};
    static String[] categorias = {"Accion", "Aventura", "RPG", "Estrategia", "Simulacion"};

    private static String generateRandomName() {
        return nombres[random.nextInt(nombres.length)] + nombres[random.nextInt(nombres.length)];
    }

    public static ArrayList<Game> generate(int numJuegos) {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < numJuegos; i++) {
            String nombre = generateRandomName();
            String categoria = categorias[random.nextInt(categorias.length)];
            int precio = random.nextInt(70001); // [0, 70000]
            int calidad = random.nextInt(101);  // [0, 100]
            games.add(new Game(nombre, categoria, precio, calidad));
        }
        return games;
    }

    public static void saveToFile(ArrayList<Game> games, String nombreArchivo) {
        try {
            File file = new File(nombreArchivo);
            file.getParentFile().mkdirs(); // Crea la carpeta si no existe
            try (PrintWriter writer = new PrintWriter(file)) {
                for (Game g : games) {
                    writer.println(g.toCSV());
                }
            }
            System.out.println("Archivo guardado correctamente: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }

    public static ArrayList<Game> loadFromFile(String rutaArchivo) {
        ArrayList<Game> games = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                if (datos.length != 4) continue;
                String nombre = datos[0];
                String categoria = datos[1];
                int precio = Integer.parseInt(datos[2]);
                int calidad = Integer.parseInt(datos[3]);
                games.add(new Game(nombre, categoria, precio, calidad));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar archivo: " + rutaArchivo);
            e.printStackTrace();
        }
        return games;
    }

    public static void printGames(ArrayList<Game> juegos) {
        for (Game game : juegos) {
            System.out.println("Nombre: " + game.getName() +
                    ", Categoria: " + game.getCategory() +
                    ", Precio: " + game.getPrice() +
                    ", Calidad: " + game.getQuality());
        }
    }

    public static void main(String[] args) {
        int[] tamaños = {100, 10000, 1000000};
        for (int tamaño : tamaños) {
            ArrayList<Game> juegos = generate(tamaño);
            String ruta = "data/games_" + tamaño + ".csv";
            saveToFile(juegos, ruta);
        }
    }
}