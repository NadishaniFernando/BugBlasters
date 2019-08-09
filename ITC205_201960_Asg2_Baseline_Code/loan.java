import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable {
	
	public static enum LoanState { current, overDue, DISCHARGED };
	//LOAN_STATE should be LoanState and CURRENT should be current and OVER_DUE should be as overDue
	
	private int id;//ID should be as id
	private book b;//B should be as b
	private member m;//M should be as m
	private Date d;//D should be as d
	private LoanState state;//LOAN_STATE should be LoanState

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;//ID should be as id
		this.b = book;//B should be as b
		this.m = member;//M should be as m
		this.d = dueDate;//D should be as d
		this.state = LoanState.current;//LOAN_STATE should be LoanState and CURRENT should be current
	}

	
	public void checkOverDue() {
		if (state == LoanState.current &&//LOAN_STATE should be LoanState and CURRENT should be current
			Calendar.getInstance().Date().after(d)) {//D should be as d and INSTANCE should be as getInstance
			this.state = LoanState.overDue;//LOAN_STATE should be LoanState and OVER_DUE should be as overDue			
		}
	}

	
	public boolean overDue() {//OVer_Due should be overDue
		return state == LoanState.overDue;//LOAN_STATE should be LoanState and OVER_DUE should be as overDue
	}

	
	public Integer id() {//ID should be as id
		return id;//ID should be as id
	}


	public Date getDueDate() {//Get_Due_Date should be as getDueDate
		return d;//D should be as d
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(id).append("\n")//ID should be as id
		  .append("  Borrower ").append(m.getId()).append(" : ")//M should be as m and GeT_ID should be as getId
		  .append(m.getLastName()).append(", ").append(m.getFirstName()).append("\n")
			//M should be as m and Get_LastName should be as getLastName and Get_FirstName should be as getFirstName
		  .append("  Book ").append(b.id()).append(" : " )//ID should be as id and B should be as b
		  .append(b.title()).append("\n")//B should be as b and TITLE should be as title
		  .append("  DueDate: ").append(sdf.format(d)).append("\n")//D should be as d
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member member() {//Member should be as member
		return m;//M should be as m
	}


	public book book() {//Book should be as book
		return b;//B should be as b
	}


	public void discharge() {//DiScHaRgE should be as discharge
		state = LoanState.DISCHARGED;//LOAN_STATE should be LoanState		
	}

}
