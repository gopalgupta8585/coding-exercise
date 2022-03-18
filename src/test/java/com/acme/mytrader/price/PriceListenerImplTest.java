package com.acme.mytrader.price;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceListenerImplTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	 
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	public void testPriceUpdate_whenExecutes() {
		
		String security = "IBM";
		double price = 53.0;
		int quantityToPurchase = 100;
		PriceListenerImpl priceListener = new PriceListenerImpl();
		priceListener.priceUpdate(security, price);
		assertEquals("Stock Bought ==>" + security + " stock-" + price + " per each-" + quantityToPurchase+" lots purchased", outContent.toString());
	}
}
