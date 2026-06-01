package utils;

import models.User;

public class Session {
	//Esta clase es para recordar quien inicio sesion en el uso de las notas
    private static User userLogged;

    public static void setUserLogged(User user) {
        userLogged = user;
    }

    public static User getUserLogged() {
        return userLogged;
    }
}
