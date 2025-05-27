package lab3;

import java.util.ArrayList;

public class quicksort {

    public void sort(ArrayList<Game> data, String attribute) {
        if (data == null || data.size() <= 1) return;
        quicksort(data, 0, data.size() - 1, attribute);
    }

    private void quicksort(ArrayList<Game> data, int low, int high, String attr) {
        if (low >= high) return;

        Game pivot = data.get((low + high) / 2);
        int left = low, right = high;

        while (left <= right) {
            while (compare(data.get(left), pivot, attr) < 0) left++;
            while (compare(data.get(right), pivot, attr) > 0) right--;

            if (left <= right) {
                Game tmp = data.get(left);
                data.set(left, data.get(right));
                data.set(right, tmp);
                left++;
                right--;
            }
        }

        quicksort(data, low, right, attr);
        quicksort(data, left, high, attr);
    }

    private int compare(Game g1, Game g2, String attr) {
        switch (attr) {
            case "price":
                return Integer.compare(g1.getPrice(), g2.getPrice());
            case "quality":
                return Integer.compare(g1.getQuality(), g2.getQuality());
            case "category":
                return g1.getCategory().compareTo(g2.getCategory());
            default:
                return Integer.compare(g1.getPrice(), g2.getPrice());
        }
    }
}