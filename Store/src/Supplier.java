public class Supplier {
    private static int nextSid = 1;
    private final int sid;
    public String sname;
    public int warranty;
    public String phone_num;

    public Supplier(){
        this.sid = nextSid++;
    }

    public Supplier(String sname, int warranty, String phone_num){
        this.sid = nextSid++;
        this.sname = sname;
        this.warranty = warranty;
        this.phone_num = phone_num;
    }

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    @Override
    public String toString() {
        return  "Supplier ID:" + sid +
                ", Supplier name:" + sname +
                ", Warranty:" + warranty +
                ", Contact number:" + phone_num;
    }
}