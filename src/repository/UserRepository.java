package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import models.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UserRepository {

	private final String FILE_PATH = "users.json";

	private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void save(User user) throws IOException {
        List<User> users = getUsers();
        users.add(user);
        updateAll(users);
    }

    public List<User> getUsers() throws IOException {
        File file = new File(FILE_PATH);
        
        // Si el archivo no existe o esta vacio, regresa una lista nueva
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        
        // Jackson lee el archivo y lo trae como lista de usuarios
        return mapper.readValue(file, new TypeReference<List<User>>() {});
    }
    
    public void updateAll(List<User> users) throws IOException {
        //Sobreescrisbre el archivo json con la lista actualizada
        mapper.writeValue(new File(FILE_PATH), users);
    }
    
    public void delete(int index) throws IOException {
        List<User> users = getUsers();
        // Mantiene la validacion para evitar errores
        if (index >= 0 && index < users.size()) {
            users.remove(index);
            // Guarda la lista ya sin el usuario
            updateAll(users); 
        }
    }
    
    public void update(int index, User updatedUser) throws IOException {
        List<User> users = getUsers();
        // Mantenemos tu validación original
        if (index >= 0 && index < users.size()) {
            users.set(index, updatedUser);
            // Guarda la lista con el usuario modificado
            updateAll(users); 
        }
    }
    
    
}
