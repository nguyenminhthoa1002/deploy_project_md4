package ra.payload.request;

import java.util.ArrayList;

public class SearchByPriceColorSize {
    private ArrayList<Integer> listsize = new ArrayList<>();
    private ArrayList<Integer> listColor = new ArrayList<>();
    private float min;
    private float max;

    public ArrayList<Integer> getListsize() {
        return listsize;
    }

    public void setListsize(ArrayList<Integer> listsize) {
        this.listsize = listsize;
    }

    public ArrayList<Integer> getListColor() {
        return listColor;
    }

    public void setListColor(ArrayList<Integer> listColor) {
        this.listColor = listColor;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
}
