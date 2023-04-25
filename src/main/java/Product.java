public class Product {
    private String ID;
    private String name;
    private String desc;
    private double cost;
    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.desc = description;
        this.ID = ID;
        this.cost = cost;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", cost=" + cost +
                '}';
    }

    public String toCSVDataRecord() {
        return name + ", " + desc + ", " + ID + ", " + cost;
    }
}