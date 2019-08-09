
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class library implements Serializable {
	
	private static final String LIBRARY_FILE = "library.obj";//libraryFile should be as LIBRARY_FILE
	private static final int LOAN_LIMIT = 2;//loanLimit should be as LOAN_LIMIT
	private static final int LOAN_PERIOD = 2;//loanPeriod should be as LOAN_PERIOD
	private static final double FINE_PER_DAY = 1.0;//finePerDay should be as FINE_PER_DAY
	private static final double MAX_FINES_OWED = 1.0;//maxFinesOwed should be as MAX_FINES_OWED
	private static final double DAMAGE_FEE = 2.0;//damageFee should be as DAMAGE_FEE
	
	private static librarySelf;//library SeLf should be as librarySelf
	private int bookId;//BOOK_ID should be as bookId
	private int memberId;//MEMBER_ID should be as memberId
	private int loanId;//LOAN_ID should be as loanId
	private Date loanDate;//LOAN_DATE should be as loanDate
	
	private Map<Integer, book> catalog;//CATALOG should be as catalog
	private Map<Integer, member> members;//MEMBERS should be as members
	private Map<Integer, loan> loans;//LOANS should be as loans
	private Map<Integer, loan> currentLoans;//CURRENT_LOANS should be as currentLoans
	private Map<Integer, book> damagedBooks;//DAMAGED_BOOKS should be as damagedBooks
	

	private library() {
		catalog = new HashMap<>();//CATALOG should be as catalog
		members = new HashMap<>();//MEMBERS should be as members
		loans = new HashMap<>();//LOANS should be as loans
		currentLoans = new HashMap<>();//CURRENT_LOANS should be as currentLoans
		damagedBooks = new HashMap<>();//DAMAGED_BOOKS should be as damagedBooks
		bookId = 1;//BOOK_ID should be as bookId
		memberId = 1;//MEMBER_ID should be as memberId	
		loanId = 1;//LOAN_ID should be as loanId	
	}

	
	public static synchronized library getInstance() {//INSTANCE should be as getInstance		
		if (self == null) {//SeLf should be as self
			Path path = Paths.get(LIBRARY_FILE);//libraryFile should be as LIBRARY_FILE and PATH should be as path		
			if (Files.exists(path)) {//PATH should be as path	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {//libraryFile should be as LIBRARY_FILE
			    
					self = (library) LiF.readObject();//SeLf should be as self
					Calendar.getInstance().setDate(self.loanDate);//Set_dATE should be as setDate
					//LOAN_DATE should be as loanDate and INSTANCE should be as getInstance and SeLf should be as self
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library();//SeLf should be as self
		}
		return self;//SeLf should be as self
	}

	
	public static synchronized void SAVE() {
		if (self != null) {//SeLf should be as self
			self.loanDate = Calendar.getInstance().Date();//LOAN_DATE should be as loanDate and INSTANCE should be as getInstance and SeLf should be as self
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {//libraryFile should be as LIBRARY_FILE
				LoF.writeObject(self);//SeLf should be as self
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookID() {//BookID should be as getBookID
		return bookId;//BOOK_ID should be as bookId
	}
	
	
	public int getMemberId() {//MemberID should be as getMemberId
		return memberId;//MEMBER_ID should be as memberId
	}
	
	
	private int nextBookId() {//NextBID should be as nextBookId
		return bookId++;//BOOK_ID should be as bookId
	}

	
	private int nextMemberId() {//NextMID should be as nextMemberId
		return memberId++;//MEMBER_ID should be as memberId
	}

	
	private int nextLoanId() {//NextLID should be as nextLoanId
		return loanId++;//LOAN_ID should be as loanId
	}

	
	public List<member> Members() {//members should be as Members		
		return new ArrayList<member>(members.values());//MEMBERS should be as members 
	}


	public List<book> Books() {//BOOKS should be as Books		
		return new ArrayList<book>(catalog.values());//CATALOG should be as catalog
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values());//CURRENT_LOANS should be as currentLoans
	}


	public member addMember(String lastName, String firstName, String email, int phoneNo) {//Add_mem should be as addMember		
		member member = new member(lastName, firstName, email, phoneNo, nextMemberId());//NextMID should be as nextMemberId
		members.put(member.getId(), member);//MEMBERS should be as members and GeT_ID should be as getId		
		return member;
	}

	
	public book addBook(String a, String t, String c) {//Add_book should be as addBook		
		book b = new book(a, t, c, nextBookId());//NextBID should be as nextBookId
		catalog.put(b.ID(), b);//CATALOG should be as catalog		
		return b;
	}

	
	public member getMember(int memberId) {//MEMBER should be as getMember
		if (members.containsKey(memberId))//MEMBERS should be as members 
			return members.get(memberId);//MEMBERS should be as members
		return null;
	}

	
	public book Book(int bookId) {
		if (catalog.containsKey(bookId))//CATALOG should be as catalog 
			return catalog.get(bookId);//CATALOG should be as catalog		
		return null;
	}

	
	public int loanLimit() {//LOAN_LIMIT should be as loanLimit
		return LOAN_LIMIT;//loanLimit should be as LOAN_LIMIT
	}

	
	public boolean memberCanBorrow(member member) {//MEMBER_CAN_BORROW should be as memberCanBorrow		
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT )//Number_Of_Current_Loans should be as getNumberOfCurrentLoans and loanLimit should be as LOAN_LIMIT
			return false;
				
		if (member.getFinesOwed() >= MAX_FINES_OWED)//Fines_OwEd should be as getFinesOwed and maxFinesOwed should be as MAX_FINES_OWED 
			return false;
				
		for (loan loan : member.getLoans())//GeT_LoAnS should be as getLoans
			if (loan.isOverDue())//OVer_Due should be as isOverDue
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {//Loans_Remaining_For_Member should be as loansRemainingForMember	
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();//loanLimit should be as LOAN_LIMIT and Number_Of_Current_Loans should be as getNumberOfCurrentLoans
	}

	
	public loan issueLoan(book book, member member) {//ISSUE_LAON should be as issueLoan
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);//Due_Date should be as getDueDate and loanPeriod should be as LOAN_PERIOD and INSTANCE should be as getInstance
		loan loan = new loan(nextLoanId(), book, member, dueDate);//NextLID should be as nextLoanId
		member.takeOutLoan(loan);//Take_Out_Loan should be as takeOutLoan
		book.Borrow();
		loans.put(loan.ID(), loan);//LOANS should be as loans
		currentLoans.put(book.ID(), loan);//CURRENT_LOANS should be as currentLoans
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {//LOAN_BY_BOOK_ID should be as getLoanByBookId
		if (currentLoans.containsKey(bookId)) {//CURRENT_LOANS should be as currentLoans
			return currentLoans.get(bookId);//CURRENT_LOANS should be as currentLoans
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {//CalculateOverDueFine should be as calculateOverDueFine 
		if (loan.isOverDue()) {//OVer_Due should be as isOverDue
			long daysOverDue = Calendar.getInstance().Get_Days_Difference(loan.Get_Due_Date());//INSTANCE should be as getInstance
			double fine = daysOverDue * FINE_PER_DAY;//finePerDay should be as FINE_PER_DAY
			return fine;
		}
		return 0.0;		
	}


	public void Discharge_loan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);//CalculateOverDueFine should be as calculateOverDueFine
		member.addFine(overDueFine);//Add_Fine should be as addFine	
		
		member.dischargeLoan(currentLoan);//dIsChArGeLoAn should be as dischargeLoan 
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);//damageFee should be as DAMAGE_FEE and Add_Fine should be as addFine
			damagedBooks.put(book.ID(), book);//DAMAGED_BOOKS should be as damagedBooks
		}
		currentLoan.discharge();//DiScHaRgE should be as discharge
		currentLoans.remove(book.ID());//CURRENT_LOANS should be as currentLoans
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {//CURRENT_LOANS should be as currentLoans
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {//Repair_BOOK should be repairBook
		if (damagedBooks.containsKey(currentBook.ID())) {//DAMAGED_BOOKS should be as damagedBooks
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());//DAMAGED_BOOKS should be as damagedBooks
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
