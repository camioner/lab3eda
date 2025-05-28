package lab3;

import java.util.ArrayList;

public class countingsort {

    public void sort(ArrayList<Game> data, String attribute) {
        if (!attribute.equals("quality")) {
            System.out.println(" Counting Sort solo está implementado para 'quality'.");
            return;
        }

        int max = 100;
        int min = 0;
        int range = max - min + 1;

        ArrayList<ArrayList<Game>> buckets = new ArrayList<>(range);
        for (int i = 0; i < range; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribuir los juegos en buckets según calidad
        for (Game g : data) {
            int calidad = g.getQuality();
            buckets.get(calidad - min).add(g);
        }

        // Combinar los buckets en la lista original
        data.clear();
        for (ArrayList<Game> bucket : buckets) {
            data.addAll(bucket);
        }
    }
}