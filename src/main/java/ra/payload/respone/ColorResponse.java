package ra.payload.respone;

public class ColorResponse {
    private int colorId;
    private String colorHex;
    private String colorName;

    public ColorResponse() {
    }

    public ColorResponse(int colorId, String colorHex, String colorName) {
        this.colorId = colorId;
        this.colorHex = colorHex;
        this.colorName = colorName;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
