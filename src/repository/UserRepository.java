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
    
    public void updateAll(List<User> users) throws IOException {
        //Cuando se quita el true del FileOutputStream, sobreescribe todo el archivo
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            for (User user : users) {
                writer.write(user.toCsv());
                writer.newLine();
            }
        }
    }
    
    public void delete(int index) throws IOException {
        List<User> users = getUsers();
        if (index >= 0 && index < users.size()) {
            users.remove(index);
            // Guarda la lista ya sin el usuario
            updateAll(users); 
        }
    }
    
    public void update(int index, User updatedUser) throws IOException {
        List<User> users = getUsers();
        if (index >= 0 && index < users.size()) {
            users.set(index, updatedUser);
            // Guarda la lista con el usuario modificado
            updateAll(users); 
        }
    }
    
    
}
