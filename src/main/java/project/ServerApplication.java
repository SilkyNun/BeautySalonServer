package project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import project.entity.Account;
import project.entity.Master;
import project.entity.Order;
import project.repository.MasterRepository;
import project.repository.AccountRepository;
import project.repository.OrderRepository;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class ServerApplication {

	@Autowired
	private MasterRepository masterRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
//			Master master = new Master("Денис Никифоров", "8403232", 0.02);
//			Account account = new Account(null, "denis", "denis200601", true, master);
//			Order order = new Order(200d, new Date(), new Date(), master, "Лохмаков Максим", "12345678");
//			masterRepository.save(master);
//			accountRepository.save(account);
//			orderRepository.save(order);
			log.info("{}", masterRepository.findAll());
		};
	}

}
