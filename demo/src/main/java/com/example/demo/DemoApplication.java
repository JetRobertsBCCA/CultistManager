package com.example.demo;

import com.example.demo.model.Cult;
import com.example.demo.model.Cultist;
import com.example.demo.model.CultMembership;
import com.example.demo.repository.CultRepository;
import com.example.demo.repository.CultistRepository;
import com.example.demo.repository.CultMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CultRepository cultRepository;

	@Autowired
	private CultistRepository cultistRepository;

	@Autowired
	private CultMembershipRepository cultMembershipRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. Create Cult");
			System.out.println("2. Create Cultist");
			System.out.println("3. Create Cult Membership");
			System.out.println("4. View all Cults");
			System.out.println("5. View All Cultist");
			System.out.println("6. Super Secret Option");
			System.out.println("7. Exit");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

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
					System.out.print("Enter cult ID: ");
					if (scanner.hasNextLong()) {
						Long membershipCultId = scanner.nextLong();
						System.out.print("Enter cultist ID: ");
						if (scanner.hasNextLong()) {
							Long membershipCultistId = scanner.nextLong();
							scanner.nextLine();
							CultMembership membership = new CultMembership();
							cultRepository.findById(membershipCultId).ifPresent(membership::setCult);
							cultistRepository.findById(membershipCultistId).ifPresent(membership::setCultist);
							cultMembershipRepository.save(membership);
							System.out.println("Cult membership created.");
						} else {
							System.out.println("Invalid cultist ID. Please enter a numeric value.");
							scanner.nextLine();
						}
					} else {
						System.out.println("Invalid cult ID. Please enter a numeric value.");
						scanner.nextLine();
					}
					break;
				case 4:
					viewAllCults();
					break;
				case 5:
					viewAllCultists();
					break;
				case 6:
					System.out.print("Enter admin password: ");
					String password = scanner.nextLine();
					if ("admin123".equals(password)) {
						System.out.print("Are you sure you want to delete all data? [Y/N]: ");
						String confirmation = scanner.nextLine();
						if ("Y".equalsIgnoreCase(confirmation)) {
							cultRepository.deleteAll();
							cultistRepository.deleteAll();
							cultMembershipRepository.deleteAll();
							System.out.println("All data deleted.");
						} else {
							System.out.println("Operation cancelled.");
						}
					} else {
						System.out.println("Incorrect password.");
					}
					break;
				case 7:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}

	@Transactional
	private void viewAllCults() {
		List<Cult> cults = cultRepository.findAll();
		System.out.println("Cults:");
		for (Cult cult : cults) {
			System.out.println("ID: " + cult.getId() + ", Name: " + cult.getName());
			System.out.println("Cultists:");
			for (Cultist cultist : cult.getCultists()) {
				System.out.println("  - ID: " + cultist.getId() + ", Name: " + cultist.getName());
			}
			System.out.println();
		}
	}

	private void viewAllCultists() {
		List<Cultist> cultists = cultistRepository.findAll();
		System.out.println("Cultists:");
		for (Cultist cultist : cultists) {
			System.out.println("ID: " + cultist.getId() + ", Name: " + cultist.getName());
			if (cultist.getCult() != null) {
				System.out.println("Cult: " + cultist.getCult().getName());
			}
			System.out.println();
		}
	}
}