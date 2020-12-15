package model;

/***
 * Creates Outsourced class
 */
public class Outsourced extends Part {

    private String companyName;


    /***
     * Outsourced constructor
     * @param id variable for the ID number of the Part
     * @param name variable associated with the nme of the part
     * @param price variable for the price of the part
     * @param stock variable for the inventory level of the part
     * @param min variable for the minimum amount of parts allowed in inventory
     * @param max variable for the maximum amount of parts allowed in inventory
     * @param companyName variable for the name of the Company outsourced from
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /***
     * gets name of Company supplying parts
     * @return name of company
     */
    public String getCompanyName() {
        return companyName;
    }

    /***
     * sets name of Company supplying parts
     * @param companyName sets name of company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
