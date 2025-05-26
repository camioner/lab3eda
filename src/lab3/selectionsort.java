package lab3;

import java.util.ArrayList;

public class selectionsort {
    public void sort(ArrayList<Game> data, String attribute) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                switch (attribute) {
                    case "price":
                        if (data.get(j).getPrice() < data.get(min).getPrice()) {
                            min = j;
                        }
                        break;
                    case "quality":
                        if (data.get(j).getQuality() < data.get(min).getQuality()) {
                            min = j;
                        }
                        break;
                    case "category":
                        if (data.get(j).getCategory().compareTo(data.get(min).getCategory()) < 0) {
                            min = j;
                        }
                        break;

                }
            }
            Game temp = data.get(i);
            data.set(i, data.get(min));
            data.set(min, temp);
        }
    }
}
