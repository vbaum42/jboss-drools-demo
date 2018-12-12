package rs.devoteam.cashflow;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

public class CashFlowApplicationTests {
	
	private static KieSession kSession;
	
	@BeforeClass
	public static void setupKSession(){
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		
		kSession = kContainer.newKieSession();
	}
	
	@Test
	public void testSessionAgenda(){
		Account account = new Account(1, 0);
		CashFlow cf1 = new CashFlow(new GregorianCalendar(2007, Calendar.JANUARY, 12).getTime(), 100.0, "CREDIT", 1);
		CashFlow cf2 = new CashFlow(new GregorianCalendar(2007, Calendar.FEBRUARY, 2).getTime(), 200.0, "DEBIT", 1);
		CashFlow cf3 = new CashFlow(new GregorianCalendar(2007, Calendar.MAY, 18).getTime(), 50.0, "CREDIT", 1);
		CashFlow cf4 = new CashFlow(new GregorianCalendar(2007, Calendar.MARCH, 9).getTime(), 75.0, "CREDIT", 1);
		AccountPeriod ap1 = new AccountPeriod(new GregorianCalendar(2007, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2007, Calendar.MARCH, 31).getTime());
		AccountPeriod ap2 = new AccountPeriod(new GregorianCalendar(2007, Calendar.APRIL, 1).getTime(), new GregorianCalendar(2007, Calendar.JUNE, 30).getTime());
		
		//kSession.insert(Arrays.asList(new Object[]{account, cf1, cf2, cf3, cf4}));
		kSession.insert(account);
		kSession.insert(cf1);
		kSession.insert(cf2);
		kSession.insert(cf3);
		kSession.insert(cf4);
		kSession.insert(ap1);
		
		Agenda agenda = kSession.getAgenda();
		agenda.getAgendaGroup("report").setFocus();
		agenda.getAgendaGroup("calculation").setFocus();
		
		kSession.fireAllRules();
		
		kSession.insert(ap2);
		
		agenda.getAgendaGroup("report").setFocus();
		agenda.getAgendaGroup("calculation").setFocus();
		
		kSession.fireAllRules();
		
	}
	
}
