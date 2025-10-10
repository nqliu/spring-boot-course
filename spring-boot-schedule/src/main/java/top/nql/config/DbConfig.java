package top.nql.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author nql
 */
@Configuration
@ConfigurationProperties(prefix = "db.backup")
@Data
public class DbConfig {
    private String host;
    private String port;
    private String username;
    private String password;
    private String dbName;
    private String localPath;
}
