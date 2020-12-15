package model;

/***
 * Creates the Part class
 */
public abstract class Part {
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    protected int min;
    protected int max;

    /***
     * Constructs the Part class
     * @param id variable for the ID number for the part
     * @param name variable associated with the name of the part
     * @param price variable for the price of the part
     * @param stock variable for the inventory level of the part
     * @param min variable for the minimum amount of parts allowed in inventory
     * @param max variable for the maximum amount of parts allowed in inventory
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /***
     * Gets Part ID
     * @return Part ID
     */
    public int getId() {
        return id;
    }

    /***
     * Sets part ID
     * @param id references the input ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Gets name of Part
     * @return Part name
     */
    public String getName() {
        return name;
    }

    /***
     * Sets name of Part
     * @param name references input string
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Gets price of Part
     * @return price of Part
     */
    public double getPrice() {
        return price;
    }

    /***
     * Sets the price of the Part
     * @param price references input price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /***
     * Gets the inventory level of a Parts
     * @return inventory level
     */
    public int getStock() {
        return stock;
    }

    /***
     * Sets inventory level of a Part
     * @param stock references the input for the inventory level
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /***
     * Gets the minimum amount of Parts allowed in inventory
     * @return the minimum amount
     */
    public int getMin() {
        return min;
    }

    /***
     * Sets the minimum amount of Parts allowed in inventory
     * @param min references the input for the minimum amount
     */
    public void setMin(int min) {
        this.min = min;
    }

    /***
     * Gets the maximum amount of Parts allowed in inventory
     * @return the maximum amount
     */
    public int getMax() {
        return max;
    }

    /***
     * sets the maximum amount of parts allowed in inventory
     * @param max references the input for the maximum amount
     */
    public void setMax(int max) {
        this.max = max;
    }
}
