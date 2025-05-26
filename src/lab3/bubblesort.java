package lab3;

import java.util.ArrayList;

public class bubblesort {

    public void sort(ArrayList<Game> data, String attribute) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Game g1 = data.get(j);
                Game g2 = data.get(j + 1);

                boolean shouldSwap = false;

                switch (attribute) {
                    case "price":
                        shouldSwap = g1.getPrice() > g2.getPrice();
                        break;
                    case "quality":
                        shouldSwap = g1.getQuality() > g2.getQuality();
                        break;
                    case "category":
                        shouldSwap = g1.getCategory().compareTo(g2.getCategory()) > 0;
                        break;
                    default:
                        shouldSwap = g1.getPrice() > g2.getPrice(); // Por defecto ordena por precio
                }

                if (shouldSwap) {
                    // Intercambiar los elementos
                    data.set(j, g2);
                    data.set(j + 1, g1);
                }
            }
        }
    }

}
