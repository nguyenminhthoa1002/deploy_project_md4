package ra.payload.request;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private String colorHex;
    private String sizeName;
    private int quantity;
    private int productId;
}
