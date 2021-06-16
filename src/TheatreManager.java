import java.util.Scanner;

public class TheatreManager {
	
	static Scanner sc = new Scanner(System.in);
	static int rows ;
	static int seats;
	public static String[][] arr; 
	static int currentIncome = 0;
	static int purchasedTicket = 0;
	
	public static void main(String[] args) {
		
		System.out.println("Enter the number of rows:");
		rows = sc.nextInt();
		System.out.println("Enter the number of seats in each row:");
		seats = sc.nextInt();
		arr = new String[rows + 1][seats + 1];
		
		for(int i = 0; i <= rows; i++) {
			for(int j = 0; j <= seats; j++) {
				if(i == 0 && j == 0) {
					arr[i][j] = "  ";
				} else if (i == 0) {
					arr[i][j] = j + " ";
				} else if(j == 0) {
					arr[i][j] = i + " ";
				} else {
					arr[i][j] = "S ";
				}
			}
		}
		showOptions();
	}
	
	public static void showOptions() {
		System.out.println();
		System.out.println("1. Show the seats \n2. Buy a ticket \n3. Statistics\n0. Exit");
		int userChoice = sc.nextInt();
		
		switch (userChoice) {
		case 1:
			showTheSeats(rows, seats);
			break;
		case 2:
			buyTicket(rows, seats);
			break;
		case 3:
			showStatistics(rows, seats);
			break;
		case 0:
			return;
		}
	}
	
	public static void showTheSeats(int rows, int seats) {
		System.out.println("Cinema:");
		for(int i = 0; i <= rows; i++) {
			for(int j = 0; j <= seats; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		showOptions();
		
	}
	
	static int buyRow;
	static int buySeat;
	
	public static void buyTicket(int rows, int seats) {
		System.out.println("Enter a row number:");
		buyRow = sc.nextInt();
		
		System.out.println("Enter a seat number in that row:");
		buySeat = sc.nextInt();
		System.out.println();
		if (buyRow > rows || buySeat > seats) {
			System.out.println("Wrong input!");
			System.out.println();
			buyTicket(rows, seats);
		}
		if (arr[buyRow][buySeat] == "B ") {
			System.out.println("That ticket has already been purchased!");
			System.out.println();
			buyTicket(rows, seats);
		} else {
			arr[buyRow][buySeat] = "B ";
		}
		if(rows*seats < 60) {
			System.out.println("Ticket price: $10");
			currentIncome += 10;
			purchasedTicket ++;
		} else if(rows*seats > 60) {
			if(buyRow <= rows/2) {
				System.out.println("Ticket price $10");
				currentIncome += 10;
				purchasedTicket ++;
			} else {
				System.out.println("Ticket price $8");
				currentIncome += 8;
				purchasedTicket ++;
			}
		}
		showOptions();
	}
	
	public static void showStatistics(int rows, int seats) {
		/* this stats will show the current income, total income, the number of available seats, 
		 * and the percentage of occupancy*/
		double totalIncome = 0;
		if (rows * seats < 60) {
			totalIncome = rows * seats * 10;
		} else {
			totalIncome = ((rows/2)*seats*10) + ((rows - (rows/2))*8*seats);
		}
		
		double percent = ((double)purchasedTicket*100) / (rows * seats);
		System.out.printf("Number of purchased tickets: %d", purchasedTicket);
		System.out.printf("\nPercentage: %.2f%%", percent);
		System.out.printf("\nCurrent income: $%d", currentIncome);
		System.out.printf("\nTotal income: $%d", (int)totalIncome);
		System.out.println();
		showOptions();
	}
}
