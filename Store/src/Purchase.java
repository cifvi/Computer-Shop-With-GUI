import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Purchase {
    private static int nextReceipt = 0;
    private final int receipt_id;
    private final Customer customer;
    private final Computer computer;
    public final LocalDate date;
    public float final_price;

    public Purchase(Customer costumer, Computer comp){
        this.receipt_id = nextReceipt++;
        this.customer = costumer;
        this.computer = comp;
        this.date = LocalDate.now();
        this.final_price = calcDiscount();
    }

    public Purchase(int receipt_id, Customer customer, Computer computer, String date, float final_price){
        nextReceipt++;
        this.receipt_id = receipt_id;
        this.customer = customer;
        this.computer = computer;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.final_price = final_price;
    }

    public int getReceipt_id() {
        return receipt_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Computer getComputer() {
        return computer;
    }

    public int getCustomer_id() {
        return customer.getCustomerID();
    }

    public int getComp_id() {
        return computer.getComp_id();
    }

    public LocalDate getDate() {
        return date;
    }

    public float getFinal_price() {
        return final_price;
    }

    @Override
    public String toString() {
        return "Receipt Number:" + getReceipt_id() +
                ", Customer ID:" + getCustomer_id() +
                ", Computer ID:" + getComp_id() +
                ", Purchase Date:" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Final price:" + final_price;
    }

    public float calcDiscount(){
        return computer.getPrice() - computer.getDiscount();
    }
}