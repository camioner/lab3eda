package lab3;
import java.util.ArrayList;
import java.util.Random;

class GenerateData {
    ArrayList<Game> games = new ArrayList<>((int) Math.pow(10, 6));
    String[] palabras = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior"};
    String[] categorias = {"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Simulación"};


    public void GenerateData() {
        Random rand = new Random();
        for (int i = 0; i < games.size(); i++) {
            String nombre = palabras[rand.nextInt(palabras.length)] + palabras[rand.nextInt(palabras.length)];
            String categoria = categorias[rand.nextInt(categorias.length)];
            int precio = rand.nextInt(70000);
            int quality = rand.nextInt(100);


        }
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}

public class generator {
    ArrayList<Game> games100 = generateGames(100);
    saveToFile(games100, "games_100.txt");

    ArrayList<Game> games10000 = generateGames(10000);
    saveToFile(games10000, "games_10000.txt");

    ArrayList<Game> games1000000 = generateGames(1000000);
    saveToFile(games1000000, "games_1000000.txt");
}