package closet;

import exceptions.invalidCategory;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Closet {
    private HashMap<String, ClosetItem> accessories;
    private HashMap<String, ClosetItem> bags;
    private HashMap<String, ClosetItem> dresses;
    private HashMap<String, ClosetItem> outerwears;
    private HashMap<String, ClosetItem> pants;
    private HashMap<String, ClosetItem> shirts;
    private HashMap<String, ClosetItem> shoes;
    private HashMap<String, ClosetItem> shorts;
    private HashMap<String, ClosetItem> skirts;
    private HashMap<String, ClosetItem> sweaters;
    private HashMap<String, ClosetItem> sweatshirts;
    private HashMap<String, ClosetItem> tops;


    public Closet() {
        accessories = new HashMap<>();
        bags = new HashMap<>();
        dresses = new HashMap<>();
        outerwears = new HashMap<>();
        pants = new HashMap<>();
        shirts = new HashMap<>();
        shoes = new HashMap<>();
        shorts = new HashMap<>();
        skirts = new HashMap<>();
        sweaters = new HashMap<>();
        sweatshirts = new HashMap<>();
        tops = new HashMap<>();
    }

    public void initCloset() {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processNewItem(TreeItem<String> parent, String name, String category, String brand, String size, String price) {
        ClosetItem c;
        switch (category) {
            case "ACCESSORIES":
                c = new Accessory(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(accessories, c, name, parent);
                break;
            case "BAGS":
                c = new Bag(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(bags, c, name, parent);
                break;
            case "DRESSES":
                c = new Dress(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(dresses, c, name, parent);
                break;
            case "OUTERWEARS":
                c = new Outerwear(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(outerwears, c, name, parent);
                break;
            case "PANTS":
                c = new Pants(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(pants, c, name, parent);
                break;
            case "SHIRTS":
                c = new Shirt(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(shirts, c, name, parent);
                break;
            case "SHOES":
                c = new Shoes(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(shoes, c, name, parent);
                break;
            case "SHORTS":
                c = new Shorts(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(shorts, c, name, parent);
                break;
            case "SKIRTS":
                c = new Skirt(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(skirts, c, name, parent);
                break;
            case "SWEATERS":
                c = new Sweater(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(sweaters, c, name, parent);
                break;
            case "SWEATSHIRTS":
                c = new Sweatshirt(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(sweatshirts, c, name, parent);
                break;
            case "TOPS":
                c = new Top(name, category, brand, size, Double.parseDouble(price));
                processNewItemHelper(tops, c, name, parent);
                break;
            default:
                System.out.println("invalid category while creating new item");
        }
    }

    private void processNewItemHelper(HashMap<String, ClosetItem> map, ClosetItem item, String name, TreeItem<String> parent) {
        map.put(name, item);
        TreeItem<String> child = new TreeItem<>(name);
        parent.getChildren().add(child);
    }

    public void load() throws IOException {
        List<String> save = Files.readAllLines(Paths.get("src/closet/data/accessories.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Accessory(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            accessories.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/bags.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Bag(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            bags.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/dresses.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Dress(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            dresses.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/outerwears.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Outerwear(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            outerwears.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/pants.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Pants(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            pants.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/shirts.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Shirt(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            shirts.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/shoes.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Shoes(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            shoes.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/shorts.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Shorts(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            shorts.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/skirts.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Skirt(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            skirts.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/sweaters.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Sweater(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            sweaters.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/sweatshirts.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Sweatshirt(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            sweatshirts.put(i.getName(), i);
        }

        save = Files.readAllLines(Paths.get("src/closet/data/tops.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            ClosetItem i = new Top(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)));
            tops.put(i.getName(), i);
        }

    }

    public void display(TreeItem<String> parent) throws invalidCategory {
        switch (parent.getValue()) {
            case "ACCESSORIES":
                displayHelper(parent, accessories);
                break;
            case "BAGS":
                displayHelper(parent, bags);
                break;
            case "DRESSES":
                displayHelper(parent, dresses);
                break;
            case "OUTERWEARS":
                displayHelper(parent, outerwears);
                break;
            case "PANTS":
                displayHelper(parent, pants);
                break;
            case "SHIRTS":
                displayHelper(parent, shirts);
                break;
            case "SHOES":
                displayHelper(parent, shoes);
                break;
            case "SHORTS":
                displayHelper(parent, shorts);
                break;
            case "SKIRTS":
                displayHelper(parent, skirts);
                break;
            case "SWEATERS":
                displayHelper(parent, sweaters);
                break;
            case "SWEATSHIRTS":
                displayHelper(parent, sweatshirts);
                break;
            case "TOPS":
                displayHelper(parent, tops);
                break;
            default:
                throw new invalidCategory();
        }
    }

    private void displayHelper(TreeItem<String> parent, HashMap<String, ClosetItem> map) {
        for (String s : map.keySet()) {
            TreeItem<String> child = new TreeItem<>(s);
            parent.getChildren().add(child);
        }
    }

    public void save() throws IOException {
        PrintWriter fileClearer = new PrintWriter("src/closet/data/accessories.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/bags.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/dresses.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/outerwears.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/pants.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/shirts.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/shoes.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/shorts.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/skirts.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/sweaters.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/sweatshirts.txt", "UTF-8");
        fileClearer.close();

        fileClearer = new PrintWriter("src/closet/data/tops.txt", "UTF-8");
        fileClearer.close();

        saveHelper("src/closet/data/accessories.txt", accessories);
        saveHelper("src/closet/data/bags.txt", bags);
        saveHelper("src/closet/data/dresses.txt", dresses);
        saveHelper("src/closet/data/outerwears.txt", outerwears);
        saveHelper("src/closet/data/pants.txt", pants);
        saveHelper("src/closet/data/shirts.txt", shirts);
        saveHelper("src/closet/data/shoes.txt", shoes);
        saveHelper("src/closet/data/shorts.txt", shorts);
        saveHelper("src/closet/data/skirts.txt", skirts);
        saveHelper("src/closet/data/sweaters.txt", sweaters);
        saveHelper("src/closet/data/sweatshirts.txt", sweatshirts);
        saveHelper("src/closet/data/tops.txt", tops);

    }

    private void saveHelper(String path, HashMap<String, ClosetItem> map) throws IOException {
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (String s: map.keySet()) {
            String line = merge(map.get(s));
            writer.println(line);
        }
        writer.close();
    }

    public HashMap<String, ClosetItem> getAccessories() {
        return accessories;
    }

    public HashMap<String, ClosetItem> getBags() {
        return bags;
    }

    public HashMap<String, ClosetItem> getDresses() {
        return dresses;
    }

    public HashMap<String, ClosetItem> getOuterwears() {
        return outerwears;
    }

    public HashMap<String, ClosetItem> getPants() {
        return pants;
    }

    public HashMap<String, ClosetItem> getShirts() {
        return shirts;
    }

    public HashMap<String, ClosetItem> getShoes() {
        return shoes;
    }

    public HashMap<String, ClosetItem> getShorts() {
        return shorts;
    }

    public HashMap<String, ClosetItem> getSkirts() {
        return skirts;
    }

    public HashMap<String, ClosetItem> getSweaters() {
        return sweaters;
    }

    public HashMap<String, ClosetItem> getSweatshirts() {
        return sweatshirts;
    }

    public HashMap<String, ClosetItem> getTops() {
        return tops;
    }

    public static ArrayList<String> split(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static String merge(ClosetItem c) {
        String entry = c.getName() + ";" + c.getCategory()+ ";" + c.getBrand() + ";" + c.getSize() + ";" + c.getPrice();
        return entry;
    }

}
