import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

public class NbaStats {
	public static int getOption(Scanner input) {
		int option = -1;
		try {
			System.out.print("Enter a number: ");
			option = input.nextInt();
			System.out.print("\n");
			while (option < 1 || option > 5) {
				System.out.print("Invalid input, enter a number from 1 to 5: ");
				System.out.print("\n");
				option = input.nextInt();
			}
		} catch (InputMismatchException e) {
			while (!input.hasNextInt()) {
				System.out.print("Invalid input, enter a number from 1 to 5: ");
				System.out.print("\n");
				input.next();
			}
			option = input.nextInt();
		}
		return option;
	}

	public static void mainMenu() {
		System.out.println("Select one of the options below: ");
		System.out.println("1. NBA Contracts");
		System.out.println("2. Yesterday's Score Leaders");
		System.out.println("3. List of Teams");
		System.out.println("4. Player Information");
		System.out.println("5. Quit\n");
	}

	public static void chooser(int option, NbaParser parser, Scanner input) {
		String data = "";
		switch (option) {
			case 1:
				System.out.println("Enter Start Date (yyyy-MM-dd): ");
				String startDate = input.next();
				System.out.println("Enter End Date (yyyy-MM-dd): ");
				String endDate = input.next();
				try {
					data = parser.getJSON("https://stats.nba.com/js/data/playermovement/NBA_Player_Movement.json");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				parser.parseContracts(data, startDate, endDate);
				parser.displayContracts();
				System.out.println("Total Contracts: " + parser.getContracts().size() + "\n");
				System.out.println("(Press Enter to Continue)");
				try {
					System.in.read();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 2:
				break;
			case 3:
				try {
					data = parser.postJSON("https://probasketballapi.com/teams");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				parser.parseTeams(data);
				parser.displayTeams();
				System.out.println("(Press Enter to Continue)");
				try {
					System.in.read();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 4:
				System.out.println("Enter Player's First Name");
				String first = input.next();
				System.out.println("Enter Player's Last Name");
				String last = input.next();
				try {
					data = parser.postJSON("https://probasketballapi.com/players", first, last);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				parser.parsePlayer(data);
				parser.displayPlayer();
				System.out.println("(Press Enter to Continue)");
				try {
					System.in.read();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 5:
				break;
			default:
				break;
		}
	}

	public static void main(String[] args) {
		int option = 0;
		Scanner input = new Scanner(System.in);
		String key = "2lPXDq8xnNyuAJjoh9gGIZeL7ivWQ4Ft";
		NbaParser parser = new NbaParser(key);
		while (option != 5) {
			mainMenu();
			option = getOption(input);
			chooser(option, parser, input);
		}
	}
}
