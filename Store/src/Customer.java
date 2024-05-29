public class Customer {
    private final int customerID;
    public String customer_name;
    public String phone_num;
    public String email;

    public Customer(int id, String customer_name, String phone_num, String email) {
        customerID = id;
        this.customer_name = customer_name;
        this.phone_num = phone_num;
        this.email = email;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String costumer_name) {
        this.customer_name = costumer_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer ID:" + customerID +
                ", Customer name:" + customer_name +
                ", Phone number:" + phone_num +
                ", Email:" + email;
    }
}