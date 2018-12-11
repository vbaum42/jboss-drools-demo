package rs.devoteam.cashflow;

import java.util.Date;

public class CashFlow {

	private Date date;
	private double amount;
	private String type;
	private long accountNo;
	
	public CashFlow(Date date, double amount, String type, long accountNo) {
		super();
		this.date = date;
		this.amount = amount;
		this.type = type;
		this.accountNo = accountNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	
	
	
}
