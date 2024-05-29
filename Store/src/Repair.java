import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Repair {
    private final Customer customer;
    private final Computer computer;
    private final LocalDate arrivalTime;
    public float repair_price;

    public Repair(Customer customer, Computer computer){
        this.customer = customer;
        this.computer = computer;
        this.arrivalTime = LocalDate.now();
    }

    public Repair(Customer customer, Computer computer, String date, float price){
        this.customer = customer;
        this.computer = computer;
        this.arrivalTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.repair_price = price;
    }

    public Computer getComputer() {
        return computer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public float getRepair_price() {
        return repair_price;
    }

    public void setRepair_price(float repair_price) {
        this.repair_price = repair_price;
    }

    @Override
    public String toString() {
        return "Customer ID:" + customer.getCustomerID() +
                ", Computer ID:" + computer.getComp_id() +
                ", Arrival time:" + arrivalTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Repair price:" + repair_price;
    }
}