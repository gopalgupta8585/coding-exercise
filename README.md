# Developer Programming Exercise

## User Story

As a trader I want to be able to monitor stock prices such that when they breach a trigger level orders can be executed automatically.

Note:

The implementation of PriceSource and EecutionService is out of scope, assuming that it will be provided by third party.
You need to listen to price updates from PriceSource and act accordingly.

## Exercise

Given the following interface definitions (provided)

```
public interface ExecutionService {
    void buy(String security, double price, int volume);
    void sell(String security, double price, int volume);
}
```

```
public interface PriceListener {
    void priceUpdate(String security, double price);
}
```

```
public interface PriceSource {
    void addPriceListener(PriceListener listener);
    void removePriceListener(PriceListener listener);
}
```

Develop a basic implementation of the PriceListener interface that provides the following behaviour:

1. Monitors price movements on a specified single stock (e.g. "IBM")
1. Executes a single "buy" instruction for a specified number of lots (e.g. 100) as soon as the price of that stock is seen to be below
a specified price (e.g. 55.0). Don’t worry what units that is in.

### Considerations

* Please "work out loud" and ask questions
* This is not a test of your API knowledge so feel free to check the web as reference
* There is no specific solution we are looking for

### Some libraries already available:

* Java 8
* JUnit 4
* Mockito
* EasyMock
* JMock

### Considerations for Solutions

There could be lots of possible solutions for this story as described below, but due to lack of clarity, I took this problem in the simplest manner and describing other approaches here only.

1. Observer Pattern: We could resolve this problem with the help of Observer design pattern where we can take PriceSource as Observable and PriceListener interface as Observer to be notified. This pattern used to establish a communication between Obsevable and Observer classes in case of any change happen at the end of Observable side.In the problem statement, it was asked to keep PriceSource and Execution class to out of scope to implement. So I could not consider this solution.

2. Publisher-Subscriber Pattern: We could resolve this problem with the help of Publisher-Subscriber pattern where we could use any message queue based technology ActiveMQ or Kafka or RabbitMQ where we could create topic and create Producer class to send messages of events due to change in price and at the other end as consumer PriceListener interface could listen to those messages and could execute buy instructions. Again due to lack of understanding of problem statement and because of constraints on creation of any new classes, I could not consider this solution.

3. Java stream API: I kept this problem statement very simple as Java program where I create a list of stock object and put a filter as monitoring for change in prices for the stock and as soon as it gets down the threshold price, its calling the PriceListener priceUpdate method to call executioner service to buy the stock.


