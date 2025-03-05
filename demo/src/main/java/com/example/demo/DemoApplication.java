package com.example.demo;

import com.example.demo.model.Cult;
import com.example.demo.model.Cultist;
import com.example.demo.repository.CultRepository;
import com.example.demo.repository.CultistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CultRepository cultRepository;

	@Autowired
	private CultistRepository cultistRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. Create Cult");
			System.out.println("2. Create Cultist");
			System.out.println("3. Exit");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
				case 1:
					System.out.print("Enter cult name: ");
					String cultName = scanner.nextLine();
					Cult cult = new Cult();
					cult.setName(cultName);
					cultRepository.save(cult);
					System.out.println("Cult created.");
					break;
				case 2:
					System.out.print("Enter cultist name: ");
					String cultistName = scanner.nextLine();
					System.out.print("Enter cult ID: ");
					Long cultId = scanner.nextLong();
					scanner.nextLine(); // consume newline
					Cultist cultist = new Cultist();
					cultist.setName(cultistName);
					cultRepository.findById(cultId).ifPresent(cultist::setCult);
					cultistRepository.save(cultist);
					System.out.println("Cultist created.");
					break;
				case 3:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}
}
