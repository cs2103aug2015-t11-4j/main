import java.util.Scanner;

/* @@author Lim Yong Zhi */

public class Alt4 {
	private static final String MESSAGE_COMMAND = "Command: ";
	private static final String MESSAGE_ERROR = "An unknown error has occured";
	
	public static Parser parser;
	public static Scanner sc;
	public static String command;

	/**
	 * Creates the parser when called and uses it to take input
	 * Program closes when parser stops running.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createParser(args);
			createScanner();
			while (parser.getIsRunning()) {
				displayInputMessage();
				getInput();
				parser.runCommand(command);
			}
		} catch (Exception e) {
			displayError();
		}
	}

	private static void displayInputMessage() {
		System.out.print(MESSAGE_COMMAND);
	}

	private static void displayError() {
		System.out.println(MESSAGE_ERROR);
	}

	private static void getInput() {
		command = sc.nextLine();
	}

	private static void createScanner() {
		sc = new Scanner(System.in);
	}

	private static void createParser(String[] args) {
		parser = new Parser(args);
	}
}