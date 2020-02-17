package entities;

import java.util.List;

public class Item {
    private Long id;
    private String label;
    private String description;
    private String orders;
    private String creator;
    private String photoPath;

    public Item(Long id, String label, String description, String orders, String creator, String photoPath) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.orders = orders;
        this.creator = creator;
        this.photoPath = photoPath;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
