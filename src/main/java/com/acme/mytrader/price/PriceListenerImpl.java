package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PriceListenerImpl implements PriceListener {

	private String security;
	private static int quantityToPurchase = 100;
	//private ExecutionService executionService;

	@Override
	public void priceUpdate(String security, double price) {

		System.out.print("Stock Bought ==>" + security + " stock-" + price + " per each-" + quantityToPurchase+" lots purchased");
		//TODO Would be provided by thrid party service and implementation is out of scope.
		//executionService.buy(security, price, quantityToPurchase);
	}
}
