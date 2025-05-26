package lab3;

import java.util.ArrayList;

public class merge_sort {

    public void sort(ArrayList<Game> data, String attribute) {
        if (data.size() <= 1) return;

        int mid = data.size() / 2;
        ArrayList<Game> left = new ArrayList<>(data.subList(0, mid));
        ArrayList<Game> right = new ArrayList<>(data.subList(mid, data.size()));

        sort(left, attribute);
        sort(right, attribute);
        merge(data, left, right, attribute);  // ← nota: se pasa "data" también
    }

    private void merge(ArrayList<Game> data, ArrayList<Game> left, ArrayList<Game> right, String attribute) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (compare(left.get(i), right.get(j), attribute) <= 0) {
                data.set(k++, left.get(i++));
            } else {
                data.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            data.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            data.set(k++, right.get(j++));
        }
    }

    private int compare(Game g1, Game g2, String attribute) {
        switch (attribute) {
            case "price":
                return Integer.compare(g1.getPrice(), g2.getPrice());
            case "quality":
                return Integer.compare(g1.getQuality(), g2.getQuality());
            case "category":
                return g1.getCategory().compareTo(g2.getCategory());
            default:
                return Integer.compare(g1.getPrice(), g2.getPrice()); // fallback
        }
    }
}