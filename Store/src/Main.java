import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Ben Cohen 208464487
        // Ofek Shemesh 313559601
        Store store;
        File storeFile = new File("Store.txt");
        Scanner scanner = new Scanner(System.in);

        if (!storeFile.exists()) { // fill sample inventory
            store = new Store(1);

            Supplier Nvidia = new Supplier("Nvidia", 2, "03-4359309");
            store.newSupplier(Nvidia);
            Supplier AMD = new Supplier("AMD", 3, "03-1234567");
            store.newSupplier(AMD);
            Supplier Intel = new Supplier("Intel", 5, "06-3345678");
            store.newSupplier(Intel);
            Supplier Corsair = new Supplier("Corsair", 10, "03-2234567");
            store.newSupplier(Corsair);
            Supplier Dell = new Supplier("Dell", 7, "03-1111112");
            store.newSupplier(Dell);
            Supplier Asus = new Supplier("Asus", 8, "09-1131112");
            store.newSupplier(Asus);
            Supplier HP = new Supplier("HP", 4, "04-1121112");
            store.newSupplier(HP);
            Supplier Toshiba = new Supplier("Toshiba", 2, "03-1111112");
            store.newSupplier(Toshiba);
            Supplier Samsung = new Supplier("Samsung", 9, "03-1154112");
            store.newSupplier(Samsung);
            Supplier Gigabyte = new Supplier("Gigabyte", 9, "03-1154912");
            store.newSupplier(Gigabyte);

            Vector<Component> omenParts = new Vector<>(); // Part list for first sample computer
            Vector<Component> alienwareParts = new Vector<>(); // Part list for second sample computer
            Vector<Component> OriginParts = new Vector<>(); // Part list for second sample computer
            Component component1 = new Component("GeForce RTX 4070", (float)3500, Nvidia.getWarranty(), Nvidia);
            store.newComponent(component1);
            alienwareParts.add(component1);
            Component component2 = new Component("Core™ i9-13900K Processor", (float) 2175.5, Intel.getWarranty(), Intel);
            store.newComponent(component2);
            alienwareParts.add(component2);
            OriginParts.add(component2);
            Component component3 = new Component("RM750x 750W (Modular PSU)", (float)600.7, Corsair.getWarranty(), Corsair);
            store.newComponent(component3);
            alienwareParts.add(component3);
            OriginParts.add(component3);
            Component component4 = new Component("GeForce RTX 4080", (float)4000, Nvidia.getWarranty(), Nvidia);
            store.newComponent(component4);
            omenParts.add(component4);
            Component component5 = new Component("GeForce RTX 4090", (float)6500, Nvidia.getWarranty(), Nvidia);
            store.newComponent(component5);
            OriginParts.add(component5);
            Component component6 = new Component("Core™ i7-10700KF Processor", (float) 1200, Intel.getWarranty(), Intel);
            store.newComponent(component6);
            omenParts.add(component6);
            Component component7 = new Component("970 EVO NVMe M.2 SSD 500GB", (float) 400, Samsung.getWarranty(), Samsung);
            store.newComponent(component7);
            Component component8 = new Component("Gigabyte Z790 Aorus Xtreme", (float) 1500, Gigabyte.getWarranty(), Gigabyte);
            store.newComponent(component8);
            omenParts.add(component8);
            Component component9 = new Component("Gigabyte Z690I Aorus Ultra Plus", (float) 20000, Gigabyte.getWarranty(), Gigabyte);
            store.newComponent(component9);
            OriginParts.add(component9);

            Computer Alienware = new Computer("Alienware", 6000, 0, 5, alienwareParts);
            store.newComputer(Alienware);
            Computer Omen = new Computer("Omen", 5000, 100, 10, omenParts);
            store.newComputer(Omen);
            Computer OriginPC = new Computer("Origin PC", 8000, 5, 7, OriginParts);
            store.newComputer(OriginPC);

        } else { // if not first entry, sample already exists
            store = new Store(1);
        }

        while (true) {
            System.out.println("Welcome. To continue please select an option:");
            System.out.println("1. Store Interface (Instance)");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> MainGUI(store);
                case 2 -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, Please try again.");
            }
            System.out.println(System.lineSeparator());
        }
    }

    static void MainGUI(Store store) {
        JFrame frame = new JFrame("Computer Store");
        frame.setIconImage(new ImageIcon("mainicon.png").getImage());
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On close end program
        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Welcome the store:", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel();
        JButton customerGUI = new JButton("Customer Portal");
        customerGUI.setPreferredSize(new Dimension(300, 50));
        customerGUI.addActionListener(e -> {
            frame.dispose();
            CustomerGUI(store);
        });
        panel2.add(customerGUI);
        JPanel panel3 = new JPanel();
        JButton managerGUI = new JButton("Manager Portal");
        managerGUI.setPreferredSize(new Dimension(300, 50));
        managerGUI.addActionListener(e -> {
            frame.dispose();
            ManagerGUI(store);
        });
        panel3.add(managerGUI);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);
    }

    static void CustomerGUI(Store store) {
        JFrame frame = new JFrame("Customer Portal");
        frame.setIconImage(new ImageIcon("customericon.png").getImage());
        frame.setSize(1000, 600);
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                MainGUI(store);
            }
        });
        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("To use the customer portal, please select an option:", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel();
        JButton buy = new JButton("Purchase computer");
        buy.setPreferredSize(new Dimension(300, 50));
        buy.addActionListener(e -> {
            frame.dispose();
            BuyGUI(store);
        });
        panel2.add(buy);

        JPanel panel3 = new JPanel();
        JButton repair = new JButton("Repair computer");
        repair.setPreferredSize(new Dimension(300, 50));
        repair.addActionListener(e -> {
            frame.dispose();
            RepairGui(store);
        });
        panel3.add(repair);

        JPanel panel4 = new JPanel();
        JButton review = new JButton("Leave a review");
        review.setPreferredSize(new Dimension(300, 50));
        review.addActionListener(e -> {
            frame.dispose();
            AddReviewGUI(store);
        });
        panel4.add(review);

        JPanel panel5 = new JPanel();
        JButton displayReviews = new JButton("Display Reviews");
        displayReviews.setPreferredSize(new Dimension(300, 50));
        displayReviews.addActionListener(e -> {
            frame.dispose();
            DisplayReviewsGUI(store);
        });
        panel5.add(displayReviews);

        frame.setLayout(new GridLayout(5, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);
    }

    static void BuyGUI(Store store) {
        JFrame frame = new JFrame("Customer Portal");
        frame.setIconImage(new ImageIcon("customericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                CustomerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please select your computer:", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel();
        JComboBox<Computer> computerList = new JComboBox<>(store.inStock);
        JScrollPane scrollableComputerList = new JScrollPane(computerList);
        computerList.setPreferredSize(new Dimension(950, 50));
        panel2.add(scrollableComputerList);

        JPanel panel3 = new JPanel();
        JButton purchase = new JButton("Purchase");
        purchase.setPreferredSize(new Dimension(300, 50));
        purchase.addActionListener(e -> {
            frame.dispose();
            AddCustomer(store, (Computer) computerList.getSelectedItem());
        });
        panel3.add(purchase);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);
    }

    static void AddCustomer(Store store, Computer computer) {
        JFrame frame = new JFrame("Customer Portal");
        frame.setIconImage(new ImageIcon("customericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                CustomerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please insert your personal information: ", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.3;
        gbc.weighty = 0.5;

        JLabel idLabel = new JLabel("Customer ID");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(idLabel, gbc);
        JTextField idText = new JTextField("ID");
        gbc.gridx = 1;
        gbc.gridy = 0;
        idText.setPreferredSize(new Dimension(700, 30));
        panel2.add(idText, gbc);

        JLabel nameLabel = new JLabel("Customer Name");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(nameLabel, gbc);
        JTextField nameText = new JTextField("Name");
        gbc.gridx = 1;
        gbc.gridy = 1;
        nameText.setPreferredSize(new Dimension(700, 30));
        panel2.add(nameText, gbc);

        JLabel phoneLabel = new JLabel("Phone Number");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel2.add(phoneLabel, gbc);
        JTextField phoneText = new JTextField("Phone Number");
        gbc.gridx = 1;
        gbc.gridy = 2;
        phoneText.setPreferredSize(new Dimension(700, 30));
        panel2.add(phoneText, gbc);

        JLabel emailLabel = new JLabel("Email (Optional)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel2.add(emailLabel, gbc);
        JTextField emailText = new JTextField("Email");
        gbc.gridx = 1;
        gbc.gridy = 3;
        emailText.setPreferredSize(new Dimension(700, 30));
        panel2.add(emailText, gbc);

        JPanel panel3 = new JPanel();
        JButton purchaseButton = new JButton("Submit");
        purchaseButton.setPreferredSize(new Dimension(300, 30));
        purchaseButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idText.getText());
                Customer customer = store.findCustomer(id);
                String name = nameText.getText();
                String phone_num = phoneText.getText();
                String email = emailText.getText();
                if (customer == null) {
                    customer = new Customer(id, name, phone_num, email);
                    store.newCustomer(customer);
                } else {
                    customer.setCustomer_name(name);
                    customer.setPhone_num(phone_num);
                    customer.setEmail(email);
                }
                Purchase purchase = new Purchase(customer, computer);
                store.newPurchase(purchase, frame);
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frame, "ID must be numbers only", "Add Customer Message", JOptionPane.PLAIN_MESSAGE);
            }
        });
        panel3.add(purchaseButton);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);
    }

    static void RepairGui(Store store) {
        JFrame frame = new JFrame("Customer Portal");
        frame.setIconImage(new ImageIcon("customericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                CustomerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please enter your personal information, and select a computer from the list", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("Customer ID");
        gbc.gridx = 0;
        gbc.weightx = 0.05;
        panel2.add(idLabel, gbc);
        JTextField idText = new JTextField("ID");
        idText.setPreferredSize(new Dimension(700, 30));
        gbc.gridx = 1;
        gbc.weightx = 0.95;
        panel2.add(idText, gbc);

        final JComboBox<Computer>[] computerListDropdown = new JComboBox[]{null};
        JButton getComputers = new JButton("Find Computers");
        getComputers.setPreferredSize(new Dimension(400, 50));
        getComputers.addActionListener(e -> {
            try {
                int customer_id = Integer.parseInt(idText.getText());
                Vector<Computer> computerList = store.findPurchases(customer_id);
                if (computerList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No receipts where found. Please ensure you entered the correct information and try again.", "Repair Message", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    computerListDropdown[0] = new JComboBox<>(computerList);
                    computerListDropdown[0].setPreferredSize(new Dimension(900, 50));
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 2;
                    gbc.weightx = 1.0;
                    panel2.remove(getComputers);
                    panel2.add(computerListDropdown[0], gbc);
                    panel2.revalidate();
                    panel2.repaint();
                }
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frame, "ID must be numbers only", "Repair Message", JOptionPane.PLAIN_MESSAGE);
            }
        });
        panel2.add(getComputers);

        JPanel panel3 = new JPanel();
        JButton repair = new JButton("Repair Computer");
        repair.setPreferredSize(new Dimension(400, 50));
        repair.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idText.getText());
                if (computerListDropdown[0] == null) {
                    JOptionPane.showMessageDialog(frame, "No computer was selected, please select a computer.", "Repair Message", JOptionPane.PLAIN_MESSAGE);
                } else {
                    Customer customer = store.findCustomer(id);
                    if (customer == null)
                        JOptionPane.showMessageDialog(frame, "Customer not found", "Repair Message", JOptionPane.PLAIN_MESSAGE);
                    else
                        store.newRepair(customer, (Computer) computerListDropdown[0].getSelectedItem(), frame);
                }
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frame, "ID must be numbers only", "Repair Message", JOptionPane.PLAIN_MESSAGE);
            }

        });
        panel3.add(repair);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);
    }

    static void AddReviewGUI(Store store) {
        JFrame frame = new JFrame("Customer Portal");
        frame.setIconImage(new ImageIcon("customericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                CustomerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please insert your review details", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("Customer ID");
        gbc.gridx = 0;
        gbc.weightx = 0.05;
        panel2.add(idLabel, gbc);
        JTextField idText = new JTextField("ID");
        idText.setPreferredSize(new Dimension(700, 30));
        gbc.gridx = 1;
        gbc.weightx = 0.95;
        panel2.add(idText, gbc);

        JPanel panel3 = new JPanel();
        JComboBox<Computer> computerList = new JComboBox<>(store.inStock);
        computerList.setPreferredSize(new Dimension(900, 50));
        panel3.add(computerList);

        JPanel panel4 = new JPanel(new GridLayout(2, 1));
        JLabel reviewDetailsLabel = new JLabel("Review Description");
        reviewDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.add(reviewDetailsLabel);
        JTextArea reviewText = new JTextArea();
        reviewText.setPreferredSize(new Dimension(900, 150));
        panel4.add(reviewText);

        JPanel panel5 = new JPanel(new GridBagLayout());
        GridBagConstraints reviewGbc = new GridBagConstraints();
        JLabel ratingLabel = new JLabel("Rating (1-5)");
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        reviewGbc.gridx = 0;
        reviewGbc.gridy = 0;
        reviewGbc.gridwidth = GridBagConstraints.REMAINDER;
        reviewGbc.fill = SwingConstants.HORIZONTAL;
        panel5.add(ratingLabel, reviewGbc);

        JRadioButton oneStar = new JRadioButton("1");
        oneStar.setActionCommand("1");
        JRadioButton twoStar = new JRadioButton("2");
        twoStar.setActionCommand("2");
        JRadioButton threeStar = new JRadioButton("3");
        threeStar.setActionCommand("3");
        JRadioButton fourStar = new JRadioButton("4");
        fourStar.setActionCommand("4");
        JRadioButton fiveStar = new JRadioButton("5");
        fiveStar.setActionCommand("5");
        fiveStar.setSelected(true);

        ButtonGroup ratingGroup = new ButtonGroup();
        reviewGbc.gridwidth = 1;
        reviewGbc.weightx = 0.2;
        reviewGbc.gridy = 1;
        reviewGbc.gridx = 0;
        ratingGroup.add(oneStar);
        panel5.add(oneStar, reviewGbc);
        reviewGbc.gridx = 1;
        ratingGroup.add(twoStar);
        panel5.add(twoStar, reviewGbc);
        reviewGbc.gridx = 2;
        ratingGroup.add(threeStar);
        panel5.add(threeStar, reviewGbc);
        reviewGbc.gridx = 3;
        ratingGroup.add(fourStar);
        panel5.add(fourStar, reviewGbc);
        reviewGbc.gridx = 4;
        ratingGroup.add(fiveStar);
        panel5.add(fiveStar, reviewGbc);

        JPanel panel6 = new JPanel();
        JButton submit_review = new JButton("Submit Review");
        submit_review.setPreferredSize(new Dimension(300, 50));
        submit_review.addActionListener(e -> {
            try {
                int customer_id = Integer.parseInt(idText.getText());
                ButtonModel ratingModel = ratingGroup.getSelection();
                if (ratingModel == null) {
                    JOptionPane.showMessageDialog(frame, "Please select your rating.", "Review Message", JOptionPane.PLAIN_MESSAGE);
                } else {
                    int rating = Integer.parseInt(ratingModel.getActionCommand());
                    String reviewDetails = "";
                    reviewDetails += reviewText.getText();
                    Customer customer = store.findCustomer(customer_id);
                    if (customer == null)
                        JOptionPane.showMessageDialog(frame, "Customer not found", "Review Message", JOptionPane.PLAIN_MESSAGE);
                    else {
                        for (CustomerReview review : store.reviews) {
                            if (customer_id == review.getCustomer().getCustomerID() && ((Computer) computerList.getSelectedItem()).getComp_id() == review.getComputer().getComp_id()){
                                JOptionPane.showMessageDialog(frame, "You have already reviewed this computer", "Review Message", JOptionPane.PLAIN_MESSAGE);
                                return;
                            }
                        }
                        store.newReview(customer, (Computer) computerList.getSelectedItem(), reviewDetails, rating, frame);
                    }
                }
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frame, "ID must be numbers only", "Review Message", JOptionPane.PLAIN_MESSAGE);
            }
        });
        panel6.add(submit_review);

        frame.setLayout(new GridLayout(6, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);
        frame.setVisible(true);
    }
    static void DisplayReviewsGUI(Store store){
        JFrame frame = new JFrame("Customer Portal");
        frame.setIconImage(new ImageIcon("customericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                CustomerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Display Reviews", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel(new GridBagLayout());
        GridBagConstraints listGbc = new GridBagConstraints();
        Vector<String> nameList = new Vector<>();
        Vector<StringBuilder> reviewsList = new Vector<>();
        for (Computer computer : store.inStock) {
            String compName = computer.getComp_name();
            nameList.add(compName);
            StringBuilder compReviews = new StringBuilder();
            for (CustomerReview review : store.reviews){
                if (review.getComputer().getComp_id() == computer.getComp_id()){
                    compReviews.append(review.reviewFormat());
                }
            }
            reviewsList.add(compReviews);
        }

        JComboBox<String> nameSelect = new JComboBox<>(nameList);
        listGbc.fill = GridBagConstraints.HORIZONTAL;
        listGbc.gridy = 0;
        panel2.add(nameSelect, listGbc);

        JTextArea reviewsArea = new JTextArea(reviewsList.elementAt(0).toString());
        // Set the text area to be read-only and wrap the text
        reviewsArea.setEditable(false);
        reviewsArea.setLineWrap(true);
        reviewsArea.setWrapStyleWord(true);

        reviewsArea.setPreferredSize(new Dimension(800, 400));
        listGbc.gridy = 1;
        JScrollPane scrollPane = new JScrollPane(reviewsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel2.add(scrollPane, listGbc);

        nameSelect.addActionListener(e -> {
            int index = nameSelect.getSelectedIndex();
            reviewsArea.setText(reviewsList.elementAt(index).toString());
        });

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        frame.add(panel1, gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.9;
        frame.add(panel2, gbc);
        frame.setVisible(true);
    }

    static void ManagerGUI(Store store) {
        JFrame frame = new JFrame("Manager Portal");
        frame.setIconImage(new ImageIcon("managericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                MainGUI(store);
            }
        });
        frame.setSize(1000, 600);
        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("To use the manager portal, please select an option:", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        JPanel panel2 = new JPanel();
        JButton newSup = new JButton("New Supplier");
        newSup.setPreferredSize(new Dimension(300, 50));
        newSup.addActionListener(e -> {
            frame.dispose();
            AddSupplierGUI(store);
        });
        panel2.add(newSup);

        JPanel panel3 = new JPanel();
        JButton addComponent = new JButton("New Component");
        addComponent.setPreferredSize(new Dimension(300, 50));
        addComponent.addActionListener(e -> {
            frame.dispose();
            AddComponentGUI(store);
        });
        panel3.add(addComponent);

        JPanel panel4 = new JPanel();
        JButton viewPurchases = new JButton("View Purchases");
        viewPurchases.setPreferredSize(new Dimension(300, 50));
        viewPurchases.addActionListener(e -> {
            frame.dispose();
            viewPurchasesGUI(store);
        });
        panel4.add(viewPurchases);

        JPanel panel5 = new JPanel();
        JButton addComputer = new JButton("New Computer");
        addComputer.setPreferredSize(new Dimension(300, 50));
        addComputer.addActionListener(e -> {
            frame.dispose();
            addComputerGUI(store);
        });
        panel5.add(addComputer);

        frame.setLayout(new GridLayout(5, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);
    }

    static void AddSupplierGUI(Store store) {
        JFrame frame = new JFrame("Manager Portal");
        frame.setIconImage(new ImageIcon("managericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                ManagerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please enter the supplier information", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel2 = new JPanel(new GridBagLayout());
        gbc.weightx = 0.3;
        gbc.weighty = 0.5;

        JLabel snameLabel = new JLabel("Supplier Name");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(snameLabel, gbc);
        JTextField snameText = new JTextField("Supplier Name");
        gbc.gridx = 1;
        gbc.gridy = 0;
        snameText.setPreferredSize(new Dimension(700, 30));
        panel2.add(snameText, gbc);

        JLabel phoneLabel = new JLabel("Phone Number");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(phoneLabel, gbc);
        JTextField phoneText = new JTextField("Phone Number");
        gbc.gridx = 1;
        gbc.gridy = 1;
        phoneText.setPreferredSize(new Dimension(700, 30));
        panel2.add(phoneText, gbc);

        JLabel warrantyLabel = new JLabel("Warranty (Years)");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel2.add(warrantyLabel, gbc);
        JTextField warrantyText = new JTextField("Warranty");
        gbc.gridx = 1;
        gbc.gridy = 2;
        warrantyText.setPreferredSize(new Dimension(700, 30));
        panel2.add(warrantyText, gbc);

        JPanel panel3 = new JPanel();
        JButton purchase = new JButton("Add Supplier");
        purchase.setPreferredSize(new Dimension(300, 50));
        purchase.addActionListener(e -> {
            try {
                int warranty = Integer.parseInt(warrantyText.getText());
                if (warranty < 0) {
                    JOptionPane.showMessageDialog(frame, "Warranty cannot be lower than 0. Please insert a valid option.", "Add Supplier Message", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (snameText.getText().isEmpty() || phoneText.getText().isEmpty() || warrantyText.getText().isEmpty())
                        JOptionPane.showMessageDialog(frame, "No fields can remain empty. Try again.", "Add Supplier Message", JOptionPane.PLAIN_MESSAGE);
                    else {
                        Supplier newSup = new Supplier(snameText.getText(), warranty, phoneText.getText());
                        store.newSupplier(newSup, frame);
                    }
                }
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frame, "Warranty must be an integer", "Add Supplier Message", JOptionPane.PLAIN_MESSAGE);
            }

        });
        panel3.add(purchase);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);
    }

    static void AddComponentGUI(Store store) {
        JFrame frame = new JFrame("Manager Portal");
        frame.setIconImage(new ImageIcon("managericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                ManagerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please enter your component information", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel2 = new JPanel(new GridBagLayout());
        gbc.weighty = 0.5;

        JLabel descriptionLabel = new JLabel("Description(Name,Brand,etc.)");
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(descriptionLabel, gbc);
        JTextField descriptionText = new JTextField("Description");
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridy = 0;
        descriptionText.setPreferredSize(new Dimension(700, 30)); // added this line
        panel2.add(descriptionText, gbc);

        JLabel priceLabel = new JLabel("Price");
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(priceLabel, gbc);
        JTextField priceText = new JTextField("Price");
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridy = 1;
        priceText.setPreferredSize(new Dimension(700, 30)); // added this line
        panel2.add(priceText, gbc);


        JPanel panel3 = new JPanel(new GridBagLayout());
        JLabel manufacturerLabel = new JLabel("Select Manufacturer");
        manufacturerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel3.add(manufacturerLabel, gbc);
        JComboBox<Supplier> manufacturerList = new JComboBox<>(store.suppliers);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        manufacturerList.setPreferredSize(new Dimension(700, 50));
        panel3.add(manufacturerList, gbc);

        JPanel panel4 = new JPanel();
        JButton addComponent = new JButton("Add Component");
        addComponent.setPreferredSize(new Dimension(300, 50));
        addComponent.addActionListener(e -> {
            float price = Float.parseFloat(priceText.getText());
            if (price < 0) {
                JOptionPane.showMessageDialog(frame, "Price cannot be lower than 0, Please try again.", "Add Component Message", JOptionPane.PLAIN_MESSAGE);
            } else {
                if (descriptionText.getText().isEmpty() || priceText.getText().isEmpty())
                    JOptionPane.showMessageDialog(frame, "No fields can remain empty", "Add Component Message", JOptionPane.PLAIN_MESSAGE);
                else {
                    Component newComponent = new Component(descriptionText.getText(), price, ((Supplier) manufacturerList.getSelectedItem()).getWarranty(), (Supplier) manufacturerList.getSelectedItem());
                    store.newComponent(newComponent, frame);
                }
            }
        });
        panel4.add(addComponent);

        frame.setLayout(new GridLayout(4, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.setVisible(true);
    }

    static void viewPurchasesGUI(Store store) {
        JFrame frame = new JFrame("Manager Portal");
        frame.setIconImage(new ImageIcon("managericon.png").getImage());
        frame.setLayout(new GridBagLayout());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                ManagerGUI(store);
            }
        });
        frame.setSize(1000, 600);
        frame.setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Transaction List : " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);
        gbc.weighty = 0.3;
        frame.add(panel1, gbc);

        JPanel panel2 = new JPanel();
        JList<Purchase> purchaseList = new JList<>(store.purchases);
        JScrollPane purchaseListScrollable = new JScrollPane(purchaseList);
        purchaseListScrollable.setPreferredSize(new Dimension(800, 400));
        panel2.add(purchaseListScrollable);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 1;
        gbc.weighty = 0.7;
        frame.add(panel2, gbc);

        frame.setVisible(true);
    }

    static void addComputerGUI(Store store) {
        JFrame frame = new JFrame("Manager Portal");
        frame.setIconImage(new ImageIcon("managericon.png").getImage());
        // On close, go back to main portal page
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                ManagerGUI(store);
            }
        });
        frame.setSize(1000, 600);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Assemble your computer", SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.PLAIN, 30));
        panel1.add(label);

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel2 = new JPanel(new GridBagLayout());
        gbc.weightx = 0.3;
        gbc.weighty = 0.5;

        JLabel comNameLabel = new JLabel("Computer Name");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(comNameLabel, gbc);
        JTextField comNameText = new JTextField("Computer Name");
        gbc.gridx = 1;
        gbc.gridy = 0;
        comNameText.setPreferredSize(new Dimension(700, 30));
        panel2.add(comNameText, gbc);

        JLabel priceLabel = new JLabel("Price");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(priceLabel, gbc);
        JTextField priceText = new JTextField("Price");
        gbc.gridx = 1;
        gbc.gridy = 1;
        priceText.setPreferredSize(new Dimension(700, 30));
        panel2.add(priceText, gbc);

        JLabel discountLabel = new JLabel("Discount in ₪");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel2.add(discountLabel, gbc);
        JTextField discountText = new JTextField("Discount");
        gbc.gridx = 1;
        gbc.gridy = 2;
        discountText.setPreferredSize(new Dimension(700, 30));
        panel2.add(discountText, gbc);

        JLabel stockLabel = new JLabel("Stock");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel2.add(stockLabel, gbc);
        JTextField stockText = new JTextField("Stock");
        gbc.gridx = 1;
        gbc.gridy = 3;
        stockText.setPreferredSize(new Dimension(700, 30));
        panel2.add(stockText, gbc);


        JPanel panel3 = new JPanel(new GridBagLayout());
        JCheckBox[] checkboxList = new JCheckBox[store.all_components.size()];
        for (int i = 0; i < store.all_components.size(); i++){
            checkboxList[i] = new JCheckBox(store.all_components.elementAt(i).toString());
        }
        JPanel componentCheckPanel = new JPanel(new GridLayout(checkboxList.length, 1));
        for (JCheckBox box: checkboxList){
            componentCheckPanel.add(box);
        }
        JScrollPane purchaseListScrollable = new JScrollPane(componentCheckPanel);
        purchaseListScrollable.setPreferredSize(new Dimension(800, 100));
        purchaseListScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        purchaseListScrollable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gbc.gridy = 0;
        panel3.add(purchaseListScrollable, gbc);

        JPanel panel4 = new JPanel();
        JButton addComponent = new JButton("Add Computer");
        addComponent.setPreferredSize(new Dimension(300, 50));
        addComponent.addActionListener(e -> {
            try {
                float price = Float.parseFloat(priceText.getText());
                float discount = Float.parseFloat(discountText.getText());
                int stock = Integer.parseInt(stockText.getText());
                if (price < 0 || discount < 0 || stock < 0){
                    JOptionPane.showMessageDialog(frame, "Price/Stock/Discount cannot be lower than 0, Please try again.", "Add Computer Message", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    String componentToString;
                    String idField;
                    int componentID;
                    Component component;
                    Vector<Component> compList = new Vector<>();
                    for (JCheckBox checkBox : checkboxList) {
                        if (checkBox.isSelected()) {
                            componentToString = checkBox.getText();
                            idField = componentToString.split(",")[0].split(":")[1];
                            componentID = Integer.parseInt(idField);
                            component = store.findComponent(componentID);
                            compList.add(component);
                        }
                    }
                    Computer newComputer = new Computer(comNameText.getText(), price, discount, stock, compList);
                    store.newComputer(newComputer, frame);
                }
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frame, "Price, Discount and Stock must be numbers", "Add Component Message", JOptionPane.PLAIN_MESSAGE);
            }
        });
        panel4.add(addComponent);

        frame.setLayout(new GridLayout(4, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.setVisible(true);
    }
}