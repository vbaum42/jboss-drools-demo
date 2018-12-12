package rs.devoteam.drivinglicense;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class DrivingLicenseApplicationTests {
	
	private static StatelessKieSession kSession;
	private static KieCommands kCommands;
	
	@BeforeClass
	public static void setupKSession(){
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		
		kCommands = kieServices.getCommands();
		kSession = kContainer.newStatelessKieSession();
	}
	
	@Test
	public void applicantTooYoung(){
		Applicant applicant = new Applicant("Vladimir Baumgartner", 16);
		assertTrue(applicant.isValid());
		kSession.execute(applicant);
		assertFalse(applicant.isValid());
	}
	
	@Test
	public void applicationFromPreviousYear(){
		Application application = new Application(new GregorianCalendar(2017, Calendar.FEBRUARY, 11).getTime());
		assertTrue(application.isValid());
		kSession.execute(application);
		assertFalse(application.isValid());
	}
	
	@Test
	public void arrayOfEvents(){
		Applicant applicant = new Applicant("Vladimir Baumgartner", 16); 
		Application application = new Application(new GregorianCalendar(2017, Calendar.FEBRUARY, 11).getTime());
		assertTrue(application.isValid());
		assertTrue(applicant.isValid());
		kSession.execute(Arrays.asList(new Object[]{application, applicant}));
		assertFalse(application.isValid());
		assertFalse(applicant.isValid());
	}
	
	@Test
	public void batchCommands(){
		List<Command> cmds = new ArrayList<Command>();
		cmds.add( kCommands.newInsert(new Applicant("Vladimir Baumgartner", 16), "Mr. Baumgartner")); 
		cmds.add( kCommands.newInsert(new Applicant("Milorad Rankic", 17), "Mr. Rankic")); 
		cmds.add( kCommands.newInsert(new Applicant("Nada Ignjic", 26), "Ms. Ignjic")); 
		cmds.add( kCommands.newInsert(new Applicant("Radan Antic", 27), "Mr. Antic")); 
		ExecutionResults results = kSession.execute( kCommands.newBatchExecution(cmds) );
		assertFalse(((Applicant)results.getValue("Mr. Baumgartner")).isValid());
	}
}
