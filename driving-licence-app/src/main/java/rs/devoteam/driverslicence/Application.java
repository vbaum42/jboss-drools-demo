package rs.devoteam.driverslicence;

import java.util.Date;

public class Application {
	
	private Date dateApplied;
	private boolean valid;
	
	public Application(Date dateApplied) {
		super();
		this.dateApplied = dateApplied;
		this.valid = true;
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	

}
