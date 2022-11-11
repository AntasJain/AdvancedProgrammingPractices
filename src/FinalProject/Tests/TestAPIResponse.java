package FinalProject.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import FinalProject.SetupAPI;

public class TestAPIResponse {
	SetupAPI instance = SetupAPI.getInstance();
	@Test
	public void testAPIResponse() {
		assertFalse(instance.getResponse().isEmpty() || instance.getStatusCode()!=200);
	}

}
