package com.acme.mytrader.strategy;

import java.util.ArrayList;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@AllArgsConstructor
@Getter
public class TradingStrategy {
	
	public static PriceListener listener = new PriceListenerImpl();
	public static final double PRICE_THRESHOLD = 55.0;
	
	public static void buyStock(ArrayList<Stock> list) {
		
		list.stream()
			.filter(s -> s.getPrice() < PRICE_THRESHOLD && s.getSecurity().equals("IBM"))
			.forEach(r -> listener.priceUpdate(r.getSecurity(), r.getPrice()));
		
	}

	
	public static void main(String[] args) {
		
		
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
		
		
		buyStock(stream);
		
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
