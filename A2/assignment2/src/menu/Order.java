package menu;

import pizza.CustomPizza;
import pizza.Pizza;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Object model to manage an individual order.
 */
public class Order {
    /**
     * A constant lambda functional interface to apply a 10% discount
     */
    public static final MenuItem.Discount DISCOUNT_10 = (double price) -> price * 0.9;
    /**
     * A constant lambda functional interface to apply a 25% discount
     */
    public static final MenuItem.Discount DISCOUNT_25 = (double price) -> price * 0.75;
    /**
     * customer name
     */
    private String name;
    /**
     * universally unique identifier
     */
    private UUID uuid;
    /**
     * immutable date-time object that represents a date
     */
    private LocalDate date;
    /**
     * immutable date-time object that represents a time
     */
    private LocalTime time;
    /**
     * list of pizza in this order
     */
    private List<Pizza> orders;
    /**
     * total price of this order
     */
    private double totalPrice;

    /**
     * Creates an Order.
     */
    public Order() {
        this.name = "Not Given";
        this.uuid = UUID.randomUUID();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.orders = new ArrayList<>();
    }

    /**
     * Mutator method to modify the customer name
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to modify the orders UUID
     *
     * @param uuid UUID
     */
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Mutator method to modify the order's creation date
     *
     * @param date LocalDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Mutator method to modify the order's creation time
     *
     * @param time LocalTime
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Adds a completed pizza to the order list.
     *
     * @param pizza a pizza to add to this order
     */
    public void add(Pizza pizza) {
        orders.add(pizza);
        totalPrice += pizza.getTotalPrice();
    }

    /**
     * generate order detail information
     *
     * @return detail order information
     */
    private String generateOrderInfo() {
        if (orders.size() == 0) {
            return "";
        }

        StringBuilder orderInfo = new StringBuilder("1 - " + orders.get(0).toString());
        for (int i = 1; i < orders.size(); i++) {
            orderInfo.append(System.lineSeparator());
            orderInfo.append((i + 1) + " - " + orders.get(i).toString());
        }
        return orderInfo.toString();
    }

    /**
     * Object.toString() providing a complete synopsis of the order class including the current
     * assigned date and time
     *
     * @return String representing the instantiated class
     */
    @Override
    public String toString() {
        String infoWithoutTotal = String.format("Date: %s\n" + "Time: %s\n" + "Customer: %s\n"
                        + "Order number: %s\n" + "Order:\n" + "%s\n", date.toString(),
                time.toString().substring(0, 5), name, uuid.toString(), this.generateOrderInfo());

        if (orders.size() == 0) {
            String infoWithTotal = String.format("%sTotal: $%.2f\n", infoWithoutTotal, totalPrice);
            return infoWithTotal;
        }

        if (orders.size() >= 3) {
            double priceBeforeDiscount = totalPrice;
            if (orders.size() >= 6) {
                totalPrice = DISCOUNT_25.applyDiscount(totalPrice);
            } else {
                totalPrice = DISCOUNT_10.applyDiscount(totalPrice);
            }
            String infoWithTotal = String.format("%s\nMulti item discount applied of $%.2f "
                    + "applied, new Total: $%.2f\n", infoWithoutTotal,
                    priceBeforeDiscount, totalPrice);
            return infoWithTotal;
        } else {
            String infoWithTotal = String.format("%s\nTotal: $%.2f\n",
                    infoWithoutTotal, totalPrice);
            return infoWithTotal;
        }
    }
}
