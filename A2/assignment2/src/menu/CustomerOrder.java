package menu;

import exceptions.TooManyToppingsException;

import javax.swing.*;

/**
 * Customer Ordering class used for creating multiple types of pizzas, and storing Orders in an
 * order object, to be returned by a calling class.
 */
public class CustomerOrder {
    /**
     * Name of customer
     */
    private String customerName;
    /**
     * The customer order
     */
    private Order order;

    /**
     * Default Constructor to create a pizza.
     */
    public CustomerOrder() {
        customerName = this.requestName();
        this.order = new Order();
    }

    /**
     * Constructor taking customer name as the parameter.
     *
     * @param customerName String donating the name of the customer
     */
    public CustomerOrder(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Creates a test customer order to connect with a future GUI
     *
     * @return Order containing the entire order of the customer, which includes the unique
     * order/customer identifier, data and time.
     * @throws TooManyToppingsException when attempting to add toppings to Pizza or any class
     *                                  extending Pizza
     */
    public Order createOrder() throws TooManyToppingsException {
        if (customerName == null) {
            this.customerName = requestName();
        }

        Order order = new Order();
        order.setName(customerName);
        return order;
    }

    /**
     * Returns human-readable string representation of a Customer order.
     *
     * @return string representation of this Customer Order
     */
    @Override
    public String toString() {
        return "Customer Order {" + order.toString() + "}";
    }

    /**
     * Prompts the user for their name, following an attempt to instantiate the order without one.
     *
     * @return String from the input field donating the customers name.
     */
    protected String requestName() {
        JOptionPane pane = new JOptionPane();
        return pane.getInputValue().toString();
    }
}
