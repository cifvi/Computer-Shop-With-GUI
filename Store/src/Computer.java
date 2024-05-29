import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

public class Computer {
    private static int nextCompID = 1;
    private final int comp_id;
    public String comp_name;
    public float price;
    public float discount;
    public int stock;
    public Vector<Component> components;

    public Computer(){
        comp_id = nextCompID++;
        stock = 0;
        discount = 0;
        components = new Vector<>();
    }

    public Computer(String comp_name, float price, float discount, int stock, Vector<Component> components){
        this.comp_id = nextCompID++;
        this.comp_name = comp_name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.components = components;
    }

    public int getComp_id() {
        return comp_id;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Vector<Component> getComponents(){
        return components;
    }

    public void setComponents(Vector<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public synchronized void sell(){
        try {
            RandomAccessFile raf = new RandomAccessFile("Computers.txt", "rw");
            Path path = Path.of("Computers.txt");
            String str = Files.readString(path);
            int pos = str.indexOf(comp_name);
            pos = str.indexOf("Stock", pos);
            raf.seek(pos);
            raf.writeBytes("Stock:" + --stock + getComponentsListAsString());
            raf.close();
        }
        catch (Exception e){
            System.out.println("Error in changing stock");
            System.exit(1);
        }
    }

    public StringBuilder getComponentsListAsString(){
        StringBuilder componentsIDs = new StringBuilder();
        for (Component component: components)
            componentsIDs.append(", ").append(component.toStringOnlyID());
        return componentsIDs;
    }

    @Override
    public String toString() {
        StringBuilder componentsIDs = getComponentsListAsString();

        return "Computer ID:" + comp_id +
                ", Computer name:" + comp_name +
                ", Price:" + price +
                ", Discount:" + discount +
                ", Stock:" + stock + componentsIDs;
    }
}