public class Component {
    private static int nextCid = 1;
    private final int cid;
    public String description;
    public float price;
    public int warranty;
    public Supplier manufacturer;

    public Component(){
        this.cid = nextCid++;
    }

    public Component(String description, float price, int warranty, Supplier manufacturer){
        this.cid = nextCid++;
        this.description = description;
        this.price = price;
        this.warranty = warranty;
        this.manufacturer = manufacturer;
    }

    public int getCid() {
        return cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getSid() {
        return manufacturer.getSid();
    }

    public void setManufacturer(Supplier manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toStringOnlyID(){
        return cid + ":" + description;
    }

    @Override
    public String toString() {
        return "Component ID:" + cid +
                ", Description:" + description +
                ", Price:" + price +
                ", Warranty:"+ warranty +
                ", Supplier ID:" + getSid();
    }
}