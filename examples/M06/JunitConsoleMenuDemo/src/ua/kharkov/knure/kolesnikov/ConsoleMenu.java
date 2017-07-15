package ua.kharkov.knure.kolesnikov;

import java.util.Scanner;

/**
 * Demonstrates console menu.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class ConsoleMenu {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			System.out.print("> ");
			String line = scanner.nextLine();
			if ("AAA".equals(line)) {
				// do work if AAA option
				System.out.println("You print \"AAA\"");
				continue;
			}
			if ("555".equals(line)) {
				// do work for 555 option
				System.out.println("You print \"555\"");
				continue;
			}
			System.out.println("Available option: AAA, 555; Ctrl+Z to exit.");
		}
		System.out.println("Finished..");
	}

}
