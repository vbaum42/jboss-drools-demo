package rs.devoteam.firealarm;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class FireAlarmApplicationTests {
	
	private static KieSession kSession;
	private Map<String, Room> rooms = new HashMap<String, Room>(); 
	
	@BeforeClass
	public static void setupKSession(){
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();

		kSession = kContainer.newKieSession();
	}
	
	@AfterClass
	public static void destroyKSession(){
		kSession.dispose();
	}
	
	@Test
	public void allRegular(){
		String[] roomNames = new String[]{"kitchen", "bedroom", "office", "livingroom"};
		for (String name : roomNames){
			Room room = new Room(name);
			rooms.put(name,	room);
			kSession.insert(room);
			Sprinkler sprinkler = new Sprinkler(room);
			kSession.insert(sprinkler);
		}
		
		kSession.fireAllRules();
		
		Fire kitchenFire = new Fire(rooms.get("kitchen"));
		Fire officeFire = new Fire(rooms.get("office"));
		
		FactHandle kitchenFireHandle = kSession.insert(kitchenFire);
		FactHandle officeFireHandle = kSession.insert(officeFire);
		
		kSession.fireAllRules();
		
		kSession.delete(kitchenFireHandle);
		kSession.delete(officeFireHandle);
		
		kSession.fireAllRules();
	}
}
