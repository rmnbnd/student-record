import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}
