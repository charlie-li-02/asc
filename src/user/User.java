package user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class User {

    private String username;
    private String usernamePath = "src/user/username.txt";

    public User() {
        username = loadUsername();
    }

    private String loadUsername() {
        try {
            List<String> sl = Files.readAllLines(Paths.get(usernamePath));
            if (sl.size() > 0) {
                return sl.get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
