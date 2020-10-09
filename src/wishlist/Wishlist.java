package wishlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Wishlist {
    private HashMap<String, WishlistItem> wishlistItems;


    public Wishlist() {
        wishlistItems = new HashMap<>();
    }

    public void load() throws IOException {
        List<String> save = Files.readAllLines(Paths.get("src/wishlist/data/wishlist.txt"));
        for (String line : save) {
            ArrayList<String> parts = split(line);
            WishlistItem i = new WishlistItem(parts.get(0), parts.get(1), parts.get(2), parts.get(3), Double.parseDouble(parts.get(4)), new URL(parts.get(5)));
            wishlistItems.put(i.getName(), i);
        }
    }

    public void save() throws IOException {
        PrintWriter fileClearer = new PrintWriter("src/wishlist/data/wishlist.txt", "UTF-8");
        fileClearer.close();
        PrintWriter writer = new PrintWriter("src/wishlist/data/wishlist.txt", "UTF-8");
        for (String s: wishlistItems.keySet()) {
            String line = merge(wishlistItems.get(s));
            writer.println(line);
        }
        writer.close();
    }

    public HashMap<String, WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public static ArrayList<String> split(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static String merge(WishlistItem c) {
        String entry = c.getName() + ";" + c.getCategory()+ ";" + c.getBrand() + ";" + c.getSize() + ";" + c.getPrice() + ";" + c.getUrl();
        return entry;
    }
}
