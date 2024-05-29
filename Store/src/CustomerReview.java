import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class CustomerReview {
    private final Customer customer;
    private final Computer computer;
    public int rating;
    public String review_details;
    public LocalDate post_date;

    public CustomerReview(Customer customer, Computer computer, int rating){
        this.customer = customer;
        this.computer = computer;
        this.rating = rating;
        this.review_details = "";
        this.post_date = LocalDate.now();
    }

    public CustomerReview(Customer customer, Computer computer, int rating, String date, String review_details){
        this.customer = customer;
        this.computer = computer;
        this.rating = rating;
        this.review_details = review_details;
        this.post_date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Customer getCustomer() {
        return customer;
    }

    public Computer getComputer() {
        return computer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview_details() {
        return review_details;
    }

    public void setReview_details(String review_details) {
        this.review_details = review_details;
    }

    public LocalDate getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDate post_date) {
        this.post_date = post_date;
    }

    public String reviewFormat(){
        return "By: " + customer.getCustomer_name() + ", Rating: " + rating + "⭐" + ", Date: " + post_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + System.lineSeparator()
                + review_details + System.lineSeparator() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "Customer ID:" + customer.getCustomerID() +
                ", Computer ID:" + computer.getComp_id() +
                ", Date of posting:" + post_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Rating:" + rating +
                ", Review details:" + review_details;
    }
}