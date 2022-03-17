package com.acme.mytrader.strategy;

import java.util.ArrayList;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TradingStrategy {
	
	
	public PriceListener listener;
	public static final double PRICE_THRESHOLD = 55.0;
	
	public void buyStock(ArrayList<Stock> list) {
		
		listener = new PriceListenerImpl();
		if(list.isEmpty()) {
			throw new NullPointerException("There is no data available for changes in stock exchange rate");
		}
		
		list.stream()
			.filter(s -> s.getPrice() < PRICE_THRESHOLD && s.getSecurity().equals("IBM"))
			.forEach(r -> listener.priceUpdate(r.getSecurity(), r.getPrice()));
		
	}

	
	public static void main(String[] args) {
		
		TradingStrategy tradingStrategy = new TradingStrategy();
		ArrayList<Stock> stream = new ArrayList<>();
		stream.add(Stock.builder().security("IBM").price(100.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(80.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(55.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(50.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(95.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(65.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(80.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(55.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(50.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(53.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(56.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(65.00).thresholdPrice(PRICE_THRESHOLD).build());
		stream.add(Stock.builder().security("IBM").price(54.50).thresholdPrice(PRICE_THRESHOLD).build());
		
		
		tradingStrategy.buyStock(stream);
		
	}
}

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
class Stock {

  private final String security;
  private final double price;
  private final double thresholdPrice;
}
