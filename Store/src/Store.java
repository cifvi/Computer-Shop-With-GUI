import javax.swing.*;
import java.util.Vector;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
public class Store {
    public final int branchID;

    public Vector<Computer> inStock;

    public Vector<Supplier> suppliers;

    public Vector<Purchase> purchases;

    public Vector<Component> all_components;

    public Vector<Customer> customers;

    public Vector<Repair> repairs;

    public Vector<CustomerReview> reviews;

    public Store(int branchID){
        this.branchID = branchID;
        try {
            File store = new File("Store.txt");
            store.createNewFile();
            File suppliers = new File("Suppliers.txt");
            suppliers.createNewFile();
            Scanner suppliersScanner = new Scanner(suppliers);
            File computers = new File("Computers.txt");
            computers.createNewFile();
            Scanner computersScanner = new Scanner(computers);
            File customers = new File("Customers.txt");
            customers.createNewFile();
            Scanner customersScanner = new Scanner(customers);
            File components = new File("Components.txt");
            components.createNewFile();
            Scanner componentsScanner = new Scanner(components);
            File repairs = new File("Repairs.txt");
            repairs.createNewFile();
            Scanner repairsScanner = new Scanner(repairs);
            File purchases = new File("Purchases.txt");
            purchases.createNewFile();
            Scanner purchasesScanner = new Scanner(purchases);
            File reviews = new File("Reviews.txt");
            reviews.createNewFile();
            Scanner reviewsScanner = new Scanner(reviews);


            this.suppliers = getSuppliersFromFile(suppliersScanner);
            this.all_components = getComponentsFromFile(componentsScanner);
            this.inStock = getComputersFromFile(computersScanner);
            this.customers = getCustomersFromFile(customersScanner);
            this.purchases = getPurchasesFromFile(purchasesScanner);
            this.repairs = getRepairsFromFile(repairsScanner);
            this.reviews = getReviewsFromFile(reviewsScanner);


            suppliersScanner.close();
            computersScanner.close();
            customersScanner.close();
            componentsScanner.close();
            repairsScanner.close();
            purchasesScanner.close();
        }
        catch (Exception e){
            System.out.println("Error opening .txt files");
            System.exit(1);
        }
    }

    public Computer findComputer(int comp_id){
        for (Computer computer : inStock) {
            if (computer.getComp_id() == comp_id) {
                return computer;
            }
        }
        return null;
    }
    public Supplier findSupplier(int sup_id){
        for (Supplier supplier : suppliers) {
            if (supplier.getSid() == sup_id) {
                return supplier;
            }
        }
        return null;
    }

    public Customer findCustomer(int customer_id){
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customer_id) {
                return customer;
            }
        }
        return null;
    }

    public Component findComponent(int component_id){
        for (Component component : all_components) {
            if (component.getCid() == component_id) {
                return component;
            }
        }
        return null;
    }

    public Vector<Computer> findPurchases(int customer_id){
        Vector<Computer> computerList = new Vector<>();
        for (Purchase pur : purchases) {
            if (pur.getCustomer_id() == customer_id)
                computerList.add(pur.getComputer());
        }
        return computerList;
    }

    public static void WriteToFile(String filename, Object obj) throws RuntimeException{
        try {
            FileWriter Writer = new FileWriter(filename, true);
            Writer.write(obj.toString());
            Writer.write(System.lineSeparator());
            Writer.close();
        }
        catch (Exception e){
            throw new RuntimeException("Error writing to " + filename);
        }
    }

    public void newComponent(Component new_component){
        all_components.add(new_component);
        WriteToFile("Components.txt", new_component);
    }

    public void newComponent(Component new_component, JFrame frame){
        all_components.add(new_component);
        String userMsg = "New component added successfully";
        FileThread fileThread = new FileThread("Components.txt", userMsg, new_component, frame, true, this);
        fileThread.start();
    }

    public void newComputer(Computer new_computer){
        inStock.add(new_computer);
        WriteToFile("Computers.txt", new_computer);
    }

    public void newComputer(Computer new_computer, JFrame frame){
        inStock.add(new_computer);
        String userMessage = "Computer added successfully";
        FileThread fileThread = new FileThread("Computers.txt", userMessage, new_computer, frame, true, this);
        fileThread.start();
    }

    public void newSupplier(Supplier new_supplier){
        suppliers.add(new_supplier);
        WriteToFile("Suppliers.txt", new_supplier);
    }

    public void newSupplier(Supplier new_supplier, JFrame frame){
        suppliers.add(new_supplier);
        String userMsg = "Supplier added Successfully";
        FileThread fileThread = new FileThread("Suppliers.txt", userMsg, new_supplier, frame, true, this);
        fileThread.start();
    }

    public void newCustomer(Customer new_customer){
        customers.add(new_customer);
        WriteToFile("Customers.txt", new_customer);
    }

    public void newPurchase(Purchase purchase, JFrame frame){
        purchases.add(purchase);
        String userMessage = "Computer purchased successfully, receipt number is: " + purchase.getReceipt_id();
        synchronized (purchase.getComputer()){
            if (purchase.getComputer().getStock() > 0)
                purchase.getComputer().sell();
            else
                userMessage = "Computer not in stock";
        }
        FileThread fileThread = new FileThread("Purchases.txt", userMessage, purchase, frame, false, this);
        fileThread.start();
    }

    public void newRepair(Customer customer, Computer computer, JFrame frame){
        float repairPrice = (float) (computer.getPrice() * 0.1);
        Repair repair = new Repair(customer, computer);
        repair.setRepair_price(repairPrice);
        repairs.add(repair);

        String userMessage = "Computer sent for repair";
        FileThread fileThread = new FileThread("Repairs.txt", userMessage, repair, frame, false, this);
        fileThread.start();
    }

    public void newReview(Customer customer, Computer computer, String reviewDetails, int rating, JFrame frame){
        CustomerReview review= new CustomerReview(customer, computer, rating);
        review.setReview_details(reviewDetails);
        reviews.add(review);
        String userMessage = "Review posted";
        FileThread fileThread = new FileThread("Reviews.txt", userMessage, review, frame, false, this);
        fileThread.start();
    }

    public Vector<Supplier> getSuppliersFromFile(Scanner fileScanner){
        Vector<Supplier> supList = new Vector<>();
        int warranty;
        String sup_name, phone_num;
        String line;
        String[] fields;
        while (fileScanner.hasNext()){
            line = fileScanner.nextLine();
            fields = line.split(",");
            sup_name = fields[1].split(":")[1];
            warranty = Integer.parseInt(fields[2].split(":")[1]);
            phone_num = fields[3].split(":")[1];

            Supplier supplier = new Supplier(sup_name, warranty, phone_num);
            supList.add(supplier);
        }
        fileScanner.close();
        return supList;
    }

    public Vector<Component> getComponentsFromFile(Scanner fileScanner){
        Vector<Component> compList = new Vector<>();
        String line, description;
        String[] fields;
        float price;
        int warranty, sup_id;
        Supplier manufacturer;

        while (fileScanner.hasNext()){
            line = fileScanner.nextLine();
            fields = line.split(",");
            description = fields[1].split(":")[1];
            price = Float.parseFloat(fields[2].split(":")[1]);
            warranty = Integer.parseInt(fields[3].split(":")[1]);
            sup_id = Integer.parseInt(fields[4].split(":")[1]);
            manufacturer = findSupplier(sup_id);
            Component component = new Component(description, price, warranty, manufacturer);
            compList.add(component);
        }
        fileScanner.close();
        return compList;
    }

    public Vector<Computer> getComputersFromFile(Scanner fileScanner){
        Vector<Computer> computersList = new Vector<>();
        String line, name;
        String[] fields;
        float price, discount;
        int stock, component_id;

        while (fileScanner.hasNext()){
            Vector<Component> componentsList = new Vector<>();
            Component component;
            line = fileScanner.nextLine();
            fields = line.split(",");
            name = fields[1].split(":")[1];
            price = Float.parseFloat(fields[2].split(":")[1]);
            discount = Float.parseFloat(fields[3].split(":")[1]);
            stock = Integer.parseInt(fields[4].split(":")[1]);

            for (int i = 5; i < fields.length; i++) {
                component_id = Integer.parseInt(fields[i].split(":")[0].strip());
                component = findComponent(component_id);
                componentsList.add(component);
            }

            Computer computer = new Computer(name, price, discount, stock, componentsList);
            computersList.add(computer);
        }
        fileScanner.close();
        return computersList;
    }

    public Vector<Customer> getCustomersFromFile(Scanner fileScanner){
        Vector<Customer> customerList = new Vector<>();
        int id;
        String line, name, phone_num, email;
        String[] fields;

        while (fileScanner.hasNext()){
            line = fileScanner.nextLine();
            fields = line.split(",");
            id = Integer.parseInt(fields[0].split(":")[1]);
            name = fields[1].split(":")[1];
            phone_num = fields[2].split(":")[1];
            email = fields[3].split(":")[1];

            Customer customer = new Customer(id, name, phone_num, email);
            customerList.add(customer);
        }
        fileScanner.close();
        return customerList;
    }

    public Vector<Purchase> getPurchasesFromFile(Scanner fileScanner){
        Vector<Purchase> purchasesList = new Vector<>();
        Customer customer;
        Computer computer;
        int receipt_id, customer_id, computer_id;
        float final_price;
        String line, date;
        String[] fields;

        while (fileScanner.hasNext()){
            line = fileScanner.nextLine();
            fields = line.split(",");
            receipt_id = Integer.parseInt(fields[0].split(":")[1]);
            customer_id = Integer.parseInt(fields[1].split(":")[1]);
            computer_id = Integer.parseInt(fields[2].split(":")[1]);
            date = fields[3].split(":")[1];
            final_price = Float.parseFloat(fields[4].split(":")[1]);

            customer = findCustomer(customer_id);
            computer = findComputer(computer_id);

            Purchase purchase = new Purchase(receipt_id, customer, computer, date, final_price);
            purchasesList.add(purchase);
        }
        fileScanner.close();
        return purchasesList;
    }

    public Vector<Repair> getRepairsFromFile(Scanner fileScanner){
        Vector<Repair> repairsList = new Vector<>();
        Customer customer;
        Computer computer;
        int customer_id, computer_id;
        float price;
        String line, date;
        String[] fields;

        while (fileScanner.hasNext()){
            line = fileScanner.nextLine();
            fields = line.split(",");
            customer_id = Integer.parseInt(fields[0].split(":")[1]);
            computer_id = Integer.parseInt(fields[1].split(":")[1]);
            date = fields[2].split(":")[1];
            price = Float.parseFloat(fields[3].split(":")[1]);

            customer = findCustomer(customer_id);
            computer = findComputer(computer_id);

            Repair repair = new Repair(customer, computer, date, price);
            repairsList.add(repair);
        }
        fileScanner.close();
        return repairsList;
    }

    public Vector<CustomerReview> getReviewsFromFile(Scanner fileScanner){
        Vector<CustomerReview> reviewsList = new Vector<>();
        Customer customer;
        Computer computer;
        int customer_id, computer_id, rating;
        String line, date;
        StringBuilder details = new StringBuilder();
        String[] fields;

        while (fileScanner.hasNext()){
            line = fileScanner.nextLine();
            fields = line.split(",");
            customer_id = Integer.parseInt(fields[0].split(":")[1]);
            computer_id = Integer.parseInt(fields[1].split(":")[1]);
            date = fields[2].split(":")[1];
            rating = Integer.parseInt(fields[3].split(":")[1]);
            details.append(fields[4].split(":")[1]);
            for (int i = 5; i < fields.length; i++){
                details.append(",").append(fields[i]);
            }


            customer = findCustomer(customer_id);
            computer = findComputer(computer_id);

            CustomerReview review = new CustomerReview(customer, computer, rating, date, details.toString());
            reviewsList.add(review);
        }
        fileScanner.close();
        return reviewsList;
    }
}