package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserRepository {

    private final String FILE_PATH = "users.csv"; 

    public void save(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH, true), StandardCharsets.UTF_8))) {
            writer.write(user.toCsv());
            writer.newLine();
        }
    }

    public List<User> getUsers() throws IOException {
        List<User> userList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromCsv(line);
                if (user != null) {
                    userList.add(user);
                }
            }
        }
        return userList;
    }
}
