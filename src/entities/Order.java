package entities;

public class Order {
    private Long id;
    private String buyerId;
    private String itemId;
    private String orderTime;

    public Order(Long id, String buyerId, String itemId, String orderTime) {
        this.id = id;
        this.buyerId = buyerId;
        this.itemId = itemId;
        this.orderTime = orderTime;
    }

    public Order(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}