package models;

public class User {
	private String name;
    private String lastNameP;
    private String lastNameM;
    private String username;
    private String birthDate;
    private String email;
    private String password;
    private String gender;
    
    public User() {
    	
    }
    
    public User(String name, String lastNameP, String lastNameM, String username, String birthDate, String email,String gender) {
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.username = username;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
    }
    
    public User(String name, String lastNameP, String lastNameM, String username, String birthDate, String email, String password,String gender) {
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.username = username;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.gender = gender;
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
               gender;
    }

    // Crea un objeto user de una linea csv
    public static User fromCsv(String userData) {
        String[] data = userData.split(",");
        if (data.length >= 8) {
            return new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
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
    
    
}
