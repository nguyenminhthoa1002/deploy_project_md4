package ra.payload.request;


import java.util.ArrayList;

public class SearchProductByColorOrSize {
//    private int[] search = new int[50];
    private ArrayList<Integer> search = new ArrayList<>();

    public ArrayList<Integer> getSearch() {
        return search;
    }

    public void setSearch(ArrayList<Integer> search) {
        this.search = search;
    }
}
