package es.drodriguez.com.listviewapi.models;

public class Producto {
    private int id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String category;

    public Producto() {
        this.name = "Zapatillas";
        this.description = "Deporte";
        this.price = 200.00;
        this.category = "Deportes";
    }
    public Producto(int id, String name, String description, Double price, String image, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.category + ": " + this.name;
    }
}
