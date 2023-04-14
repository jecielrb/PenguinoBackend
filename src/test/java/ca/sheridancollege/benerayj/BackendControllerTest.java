package ca.sheridancollege.benerayj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.benerayj.controller.BackendController;

@SpringBootTest
public class BackendControllerTest {

	@Autowired
	BackendController backendController;

	/**
	 * 
	 * Tests the health of the BackendController by checking if the healthCheck
	 * method returns the expected string message.
	 * 
	 * @throws AssertionError If the healthCheck method does not return the expected
	 *                        string message.
	 */
	@Test
	void health() throws AssertionError {
		assertEquals("HEALTH CHECK OK!", backendController.healthCheck());
	}

	/**
	 * 
	 * Tests the length of the list of suggested names returned by the suggestNames
	 * method in the BackendController class.
	 * 
	 * @throws AssertionError If the length of the list returned by the suggestNames
	 *                        method does not match the expected length.
	 */
	@Test
	void namesLength() throws AssertionError {
		Integer namesLength = backendController.suggestNames(5).size();
		assertEquals(5, namesLength);
	}

}
