package models;

public class User {
	private int id;
	private String name;
    private String lastNameP;
    private String lastNameM;
    private String username;
    private String birthDate;
    private String email;
    private String password;
    private String gender;
    private String imagePath;
    
    public User() {
    	
    }
    
    public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
    
    public User(int id, String name, String lastNameP, String lastNameM, String username, String birthDate, String email,String gender) {
    	this.id = id;
    	this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.username = username;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.imagePath = "";
    }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
    
    public User(String name, String lastNameP, String lastNameM, String username, String birthDate, String email, String password,String gender,String imagePath) {
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.username = username;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.imagePath = imagePath;
    }
    
    public User(String name, String lastNameP, String lastNameM, String username, String birthDate, String email,String gender,String imagePath) {
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.username = username;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.imagePath = imagePath;
    }

    // Formato de data pra el csv
    public String toCsv() {
        return name + "," + 
               lastNameP + "," + 
               lastNameM + "," + 
               username + "," + 
               birthDate + "," + 
               email + "," + 
               password + "," + 
               gender +
               (imagePath != null ? imagePath : "");
    }

    // Crea un objeto user de una linea csv
    public static User fromCsv(String userData) {
        String[] data = userData.split(",",-1);
        if (data.length >= 8) {
        	String imgPath = data.length >= 9 ? data[8] : ""; // Si tiene foto la lee, si no, vacío
            return new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], imgPath);
        }
        return null; 
    }

    @Override
    public String toString() {
        return "Username: " + username + " | Email: " + email + " | Name: " + name + " " + lastNameP;
    }

    public String getName() {
        return name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getImagePath() {
    		return imagePath;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setLastNameP(String lastNameP) {
		this.lastNameP = lastNameP;
	}

	public void setLastNameM(String lastNameM) {
		this.lastNameM = lastNameM;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    
    
}
