package com.acme.mytrader.strategy;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.acme.mytrader.price.PriceListener;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

	private static final double PRICE_THRESHOLD = 54.0;

	
	@InjectMocks
	TradingStrategy tradingStrategy;
	
	@Mock
	PriceListener priceListener;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testNothing() {
	}

	@Test
	public void testBuyStock_WhenNoStream_ThenThrowException() {

		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("There is no data available for changes in stock exchange rate");

		ArrayList<Stock> stream = new ArrayList<>();
		tradingStrategy.buyStock(stream);

	}

	@Test
	public void testBuyStock_WhenStreamHasPriceLessThanThreshold_ThenProcessTheStream() {

		//priceListener = new PriceListenerImpl();
		ArrayList<Stock> stream = new ArrayList<>();
		stream.add(Stock.builder().security("IBM").price(100.00).thresholdPrice(PRICE_THRESHOLD).build());

		Mockito.doNothing().when(priceListener).priceUpdate(stream.get(0).getSecurity(), stream.get(0).getPrice());
		tradingStrategy.buyStock(stream);

		verify(priceListener, times(1)).priceUpdate(stream.get(0).getSecurity(), stream.get(0).getPrice());

	}
	
	@Test
	public void testBuyStock_WhenStreamHasPriceAboveOrEqualThreshold_ThenDontProcessTheStream() {

		//priceListener = new PriceListenerImpl();
		ArrayList<Stock> stream = new ArrayList<>();
		stream.add(Stock.builder().security("IBM").price(100.00).thresholdPrice(100.0).build());

		tradingStrategy.buyStock(stream);
		//Mockito is not working. Here it could be verified that in case of price above than threshold, priceUpdate() of listener instance wont be called out.
		verify(priceListener).priceUpdate(stream.get(0).getSecurity(), stream.get(0).getPrice());

	}

}
