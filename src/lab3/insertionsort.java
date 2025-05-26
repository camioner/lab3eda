package lab3;

import java.util.ArrayList;

public class insertionsort {
    public void sort(ArrayList<Game> data, String attribute) {
        int n = data.size();
        for (int i = 1; i < n; ++i) {
            Game key = data.get(i);
            int j = i - 1;

            switch (attribute) {
                case "price":
                    while (j >= 0 && data.get(j).getPrice() > key.getPrice()) {
                        data.set(j + 1, data.get(j));
                        j--;
                    }
                    break;

                case "quality":
                    while (j >= 0 && data.get(j).getQuality() > key.getQuality()) {
                        data.set(j + 1, data.get(j));
                        j--;
                    }
                    break;

                case "category":
                    while (j >= 0 && data.get(j).getCategory().compareTo(key.getCategory()) > 0) {
                        data.set(j + 1, data.get(j));
                        j--;
                    }
                    break;
            }

            data.set(j + 1, key);
        }
    }
}
