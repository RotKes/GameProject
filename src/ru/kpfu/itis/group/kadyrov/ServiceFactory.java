package ru.kpfu.itis.group.kadyrov;

import ru.kpfu.itis.group.kadyrov.services.UserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Амир on 04.11.2016.
 */
public class ServiceFactory {
    private static ServiceFactory instance;
    private UserService userService;

    static {
        instance = new ServiceFactory();
    }

    private ServiceFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Амир\\IdeaProjects\\GameProjectV3.1\\src\\resources\\service.properties"));

            String userService = properties.getProperty("userservice.class");
            this.userService = (UserService) Class.forName(userService).newInstance();

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
