package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import models.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import config.DatabaseConnection;

//Se encarga de las cosas d crear,leer,actualizar y borrar de la tabla de usuarios en la base de datos
public class UserRepository {

	//Guarda un nuevo usuario en la base de datos
    public void save(User user) throws IOException {
    		String sql = "INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, usuario, fecha_nacimiento, email, contrasenia, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    		
    		try (Connection conn = DatabaseConnection.getConnection();
    	             PreparedStatement stmt = conn.prepareStatement(sql)) {
    	            
    	            stmt.setString(1, user.getName());
    	            stmt.setString(2, user.getLastNameP());
    	            stmt.setString(3, user.getLastNameM());
    	            stmt.setString(4, user.getUsername());
    	            stmt.setString(5, user.getBirthDate());
    	            stmt.setString(6, user.getEmail());
    	            stmt.setString(7, user.getPassword());
    	            stmt.setString(8, user.getGender());
    	            
    	            stmt.executeUpdate();
    	            System.out.println("Usuario guardado en la Base de Datos exitosamente.");
    	            
    	        } catch (SQLException ex) {
    	            System.out.println("Error al guardar en Base de Datos:");
    	            ex.printStackTrace();
    	        }
    
    }

    //Trae toda una lista de los usaios en la base d datos
    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_usuario"));
                user.setName(rs.getString("nombre"));
                user.setLastNameP(rs.getString("apellido_paterno"));
                user.setLastNameM(rs.getString("apellido_materno"));
                user.setUsername(rs.getString("usuario"));
                user.setBirthDate(rs.getString("fecha_nacimiento"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("contrasenia"));
                user.setGender(rs.getString("genero"));
                
                usersList.add(user);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return usersList;
    }
    
    //Elimina a un usuario de la base de datos usando su ID.
    public void delete(int idUsuario) {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //Actualiza los datos de un usuario que ya existe usando el ID
    public void update(User user) {
        String sql = "UPDATE Usuarios SET nombre=?, apellido_paterno=?, apellido_materno=?, usuario=?, fecha_nacimiento=?, email=?, contrasenia=?, genero=? WHERE id_usuario=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastNameP());
            stmt.setString(3, user.getLastNameM());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getBirthDate());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getGender());
            stmt.setInt(9, user.getId()); 
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //Verifica si ya existe alguien usando el username para no repetir nombres
    public boolean userExists(String user) {
        boolean exists = false;
        String sql = "SELECT id_usuario FROM Usuarios WHERE usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            
            // Si rs.next() es verdadero, es porque ese nombre de usuario ya se esta usando
            if (rs.next()) {
                exists = true; 
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return exists;
    }

    
    //Verifica si hay un correo en a base de datos
    public boolean emailExists(String email) {
        boolean exists = false;
        String sql = "SELECT id_usuario FROM Usuarios WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            // Si rs.next() es verdadero, es que si ese correo ya s esta usando
            if (rs.next()) {
                exists = true; 
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return exists;
    }
    
}
