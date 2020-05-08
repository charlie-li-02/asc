package closet;

import javafx.scene.image.Image;

import java.net.URL;

public abstract class ClosetItem {
    private String name;
    private String category;
    private String brand;
    private String size;
    private double price;
    private URL url;
    private Image image;

    public ClosetItem (String name, String category, String brand, String size, double price) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.size = size;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public URL getUrl() {
        return url;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
