package asw.springboot.luckyword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class LuckyWordApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckyWordApplication.class, args);
	}
}