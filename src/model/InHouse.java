package model;

/***
 * Creates InHouse class
 */
public class InHouse extends Part {
    private int machineId;

    /***
     * InHouse constructor
     * @param id variable for the ID number for the part
     * @param name variable associated with the name of the part
     * @param price variable for the price of the part
     * @param stock variable for the inventory level of the part
     * @param min variable for the minimum amount of parts allowed in inventory
     * @param max variable for the maximum amount of parts allowed in inventory
     * @param machineId assigns the Machine ID to the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /***
     * gets Machine Id
     * @return Machine Id
     */
    public int getMachineId() {
        return machineId;
    }

    /***
     * sets the Machine ID
     * @param machineId sets the Machine ID
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
