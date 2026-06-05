package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import config.DatabaseConnection;
import models.User;

//Clase que funciona como intermediario directo entre la aplicacion y la base de datos para la 
//autentificacion del login
public class LoginRepository {

	public User login(String email, String password) {
		
		String sql = "SELECT id_usuario, email, contrasenia, usuario FROM Usuarios WHERE email = ? AND contrasenia = ?";
		
		try (
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			//Si hubo coincidencia de resultados 
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id_usuario"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("usuario"));
				
				return user;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		//Si llega aqui es que las credenciales estuvieron mal
		return null;
	}
	
}