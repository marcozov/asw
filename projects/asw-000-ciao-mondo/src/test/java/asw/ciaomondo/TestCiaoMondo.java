package test.java.asw.ciaomondo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.asw.ciaomondo.CiaoMondo;

public class TestCiaoMondo {

	@Test
	public void testAppHasAGreeting() {
		CiaoMondo ciaoMondo = new CiaoMondo();
		assertNotNull("It must return a greeting.", ciaoMondo.getSaluto());
//		assertNull("failing test", ciaoMondo.getSaluto());
	}

}
