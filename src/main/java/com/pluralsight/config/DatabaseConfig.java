package com.pluralsight.config;
import org.apache.commons.dbcp2.BasicDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DatabaseConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cardealershipdatabase";
    private static final String PROPERTIES_FILE = "application.properties";

    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();

            try {
                Properties props = loadProperties();

                dataSource.setUrl(DB_URL);
                dataSource.setUsername(props.getProperty("db.username"));
                dataSource.setPassword(props.getProperty("db.password"));

            } catch (IOException e) {
                System.err.println("Error loading database properties: " + e.getMessage());
                System.err.println("Please ensure application.properties exists with db.username and db.password");
                System.exit(1);
            }
        }

        return dataSource;
    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            props.load(fis);
        }
        return props;
    }

}