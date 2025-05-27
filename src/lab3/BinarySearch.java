package lab3;

import java.util.ArrayList;

public class BinarySearch {

    public ArrayList<Game> search(ArrayList<Game> lista, String atributo, String tipoBusqueda, String valor1, String valor2) {
        ArrayList<Game> resultado = new ArrayList<>();

        if (lista.isEmpty()) return resultado;

        // Calcular valor medio si es rango numérico
        int val1 = 0, val2 = 0, midVal = 0;
        if (!atributo.equals("category")) {
            val1 = Integer.parseInt(valor1);
            val2 = (tipoBusqueda.equals("range")) ? Integer.parseInt(valor2) : val1;
            midVal = (val1 + val2) / 2;
        }

        String claveBusqueda = atributo.equals("category") ? valor1 : Integer.toString(midVal);
        int found = buscarValor(lista, atributo, claveBusqueda);
        if (found == -1) return resultado;

        // Expandir a la izquierda
        int i = found;
        while (i >= 0 && estaEnRango(lista.get(i), atributo, valor1, valor2, tipoBusqueda)) {
            resultado.add(0, lista.get(i));
            i--;
        }

        // Expandir a la derecha
        i = found + 1;
        while (i < lista.size() && estaEnRango(lista.get(i), atributo, valor1, valor2, tipoBusqueda)) {
            resultado.add(lista.get(i));
            i++;
        }

        return resultado;
    }

    private int buscarValor(ArrayList<Game> lista, String atributo, String valor) {
        int lo = 0, hi = lista.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = comparar(lista.get(mid), atributo, valor);

            if (cmp == 0) return mid;
            if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }

        return (lo < lista.size()) ? lo : -1;
    }

    private int comparar(Game g, String atributo, String valor) {
        return switch (atributo) {
            case "price" -> Integer.compare(g.getPrice(), Integer.parseInt(valor));
            case "quality" -> Integer.compare(g.getQuality(), Integer.parseInt(valor));
            case "category" -> g.getCategory().compareTo(valor);
            default -> 0;
        };
    }

    private boolean estaEnRango(Game g, String atributo, String min, String max, String tipo) {
        if (tipo.equals("exact")) {
            return comparar(g, atributo, min) == 0;
        } else { // "range"
            return comparar(g, atributo, min) >= 0 && comparar(g, atributo, max) <= 0;
        }
    }
}