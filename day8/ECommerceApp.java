package day8;

//import java.util.LinkedList;
//import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
// Shared resource
class OrderProcessor {
    private final BlockingQueue<String> orderQueue = new LinkedBlockingQueue<>();
 
    public void placeOrder(String order) {
        orderQueue.add(order);
        System.out.println(Thread.currentThread().getName() + " placed order: " + order);
    }
 
    public String processOrder() {
        try {
        	return orderQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}

class PaymentProcessor {
	public void processPayment(String order) {
		System.out.println("Processing payment for: " + order);
		try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        }
	}
}
 
// Producer: Customer placing orders
class CustomerThread implements Runnable {
    private final OrderProcessor processor;
    private final String customerName;
 
    public CustomerThread(OrderProcessor processor, String customerName) {
        this.processor = processor;
        this.customerName = customerName;
    }
 
    @Override
    public void run() {
        String order = "Item_" + (int)(Math.random() * 1000);
        processor.placeOrder(customerName + "-" + order);
    }
}
 
// Consumer: Inventory checking and processing orders
class InventoryThread extends Thread {
    private final OrderProcessor processor;
    private final PaymentProcessor paymentProcessor;
    
    public InventoryThread(OrderProcessor processor, PaymentProcessor paymentProcessor) {
        this.processor = processor;
        this.paymentProcessor = paymentProcessor;
    }
 
    @Override
    public void run() {
        while (true) {
        	// process order
            String order = processor.processOrder();
            if (order == null) break;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            	Thread.currentThread().interrupt();
            }
            System.out.println("Inventory processed: " + order);
            
            // processing payment
            paymentProcessor.processPayment(order);
            System.out.println("Payment completed for: " + order);
        }
    }
}
 
// Main class
public class ECommerceApp {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
 
        // Start inventory thread
        InventoryThread inventory = new InventoryThread(processor, paymentProcessor);
        inventory.start();
 
        // Thread pool to simulate multiple customers
        ExecutorService executor = Executors.newFixedThreadPool(5);
 
        // Simulate 10 customers placing orders
        for (int i = 1; i <= 10; i++) {
            executor.execute(new CustomerThread(processor, "Customer" + i));
        }
 
        executor.shutdown();
    }
}