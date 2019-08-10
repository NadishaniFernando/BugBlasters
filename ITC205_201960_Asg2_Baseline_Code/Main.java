import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner INPUT;//IN should be as INPUT
	private static library LIBRARY;//LIB should be as LIBRARY
	private static String MENU;
	private static Calendar CALENDAR;//CAL should be as CALENDAR
	private static SimpleDateFormat SIMPLE_DATE_FORMAT;//SDF should be as SIMPLE_DATE_FORMAT
	
	
	private static String getMenu() {//Get_menu should be as getMenu
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			INPUT = new Scanner(System.in);//IN should be as INPUT
			LIBRARY = library.INSTANCE();//LIB should be as LIBRARY
			CALENDAR = Calendar.INSTANCE();//CAL should be as CALENDAR
			SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");//SDF should be as SIMPLE_DATE_FORMAT
	
			for (member m : LIBRARY.members()) {//LIB should be as LIBRARY and MEMBERS should be as members
				output(m);
			}
			output(" ");
			for (book b : LIBRARY.books()) {//LIB should be as LIBRARY and BOOKS should be as books
				output(b);
			}
						
			MENU = getMenu();//Get_menu should be as getMenu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SIMPLE_DATE_FORMAT.format(CALENDAR.Date()));//CAL should be as CALENDAR and SDF should be as SIMPLE_DATE_FORMAT
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();//ADD_MEMBER should be as addMember
					break;
					
				case "LM": 
					members();//MEMBERS should be as members
					break;
					
				case "B": 
					addBook();//ADD_BOOK should be as addBook
					break;
					
				case "LB": 
					books();//BOOKS should be as books
					break;
					
				case "FB": 
					fixBooks();//FIX_BOOKS should be as fixBooks
					break;
					
				case "L": 
					borrowBook();//BORROW_BOOK should be as borrowBook
					break;
					
				case "R": 
					returnBook();//RETURN_BOOK should be as returnBook
					break;
					
				case "LL": 
					currentLoans();//CURRENT_LOANS should be as currentLoans
					break;
					
				case "P": 
					fines();//FINES should be as fines
					break;
					
				case "T": 
					incrementDate();//INCREMENT_DATE should be as incrementDate
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void fines() {//FINES should be as fines
		new PayFineUI(new PayFineControl()).run();//RuN should be as run		
	}


	private static void currentLoans() {//CURRENT_LOANS should be as currentLoans
		output("");
		for (loan loan : LIBRARY.currentLoans()) {//LIB should be as LIBRARY and CurrentLoans should be as currentLoans
			output(loan + "\n");
		}		
	}



	private static void books() {//BOOKS should be as books
		output("");
		for (book book : LIBRARY.books()) {//LIB should be as LIBRARY and BOOKS should be as books
			output(book + "\n");
		}		
	}



	private static void members() {//MEMBERS should be as members
		output("");
		for (member member : LIBRARY.members()) {//LIB should be as LIBRARY and MEMBERS should be as members
			output(member + "\n");
		}		
	}



	private static void borrowBook() {//BORROW_BOOK should be as borrowBook
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {//RETURN_BOOK should be as returnBook
		new ReturnBookUI(new ReturnBookControl()).run();//RuN should be as run		
	}


	private static void fixBooks() {//FIX_BOOKS should be as fixBooks
		new FixBookUI(new FixBookControl()).run();//RuN should be as run		
	}


	private static void incrementDate() {//INCREMENT_DATE should be as incrementDate
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CALENDAR.incrementDate(days);//CAL should be as CALENDAR
			LIBRARY.checkCurrentLoans();//LIB should be as LIBRARY
			output(SIMPLE_DATE_FORMAT.format(CALENDAR.Date()));//CAL should be as CALENDAR and SDF should be as SIMPLE_DATE_FORMAT
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {//ADD_BOOK should be as addBook
		
		String author = input("Enter author: ");//A should be as author
		String title  = input("Enter title: ");//T should be as title
		String callNumber = input("Enter call number: ");//C should be as callNumber
		book B = LIBRARY.addBook(author, title, callNumber);//LIB should be as LIBRARY and Add_book should be as addBook
		//A should be as author and T should be as title and C should be as callNumber
		output("\n" + B + "\n");
		
	}

	
	private static void addMember() {//ADD_MEMBER should be as addMember
		try {
			String lastName = input("Enter last name: ");//LN should be as lastName
			String firstName  = input("Enter first name: ");//FN should be as firstName
			String email = input("Enter email: ");//EM should be as email
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();//PN should be as phoneNo
			member M = LIBRARY.addMember(lastName, firstName, email, phoneNo);//LIB should be as LIBRARY and Add_mem should be as addMember
			//LN should be as lastName and FN should be as firstName and EM should be as email and PN should be as phoneNo
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return INPUT.nextLine();//IN should be as INPUT
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
