package ra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ra.model.serviceImple.FileStorageService;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {
    @Autowired
    private FileStorageService fileStorageService;

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileStorageService.init();
    }
}
