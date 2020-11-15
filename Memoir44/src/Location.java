import java.util.ArrayList;

/**
 * Location class defines a single (row, column) tuple from the map.
 * @author Asma
 * @version 1.0
 */
public class Location {
    // represents the number of row
    // from 1 to 9
    private int row;
    // represents the number of column
    // from 1 to 13
    private int column;
    // indicates that the location if full or empty
    // true for full and false for empty
    private boolean status;

    /**
     * Create a new location on the map game.
     * @param row  number of row of the location
     * @param column  number of column of the location
     */
    public Location(int row, int column){
        this.row = row;
        this.column = column;
        // set location initial status to false (empty)
        this.status = false;
    }

    /*************************************** getter methods ************************************/
    public int getRow(){return row;}
    public int getColumn(){return column;}
    protected boolean getStatus(){ return status;}
    /*******************************************************************************************/
    /*************************************** setter methods ************************************/
    public void setRow(int row){this.row = row;}
    public void setColumn(int column){this.column = column;}
    protected void setStatus(boolean status){this.status = status;}
    /*******************************************************************************************/

    /**
     * Check if two locations are the same.
     * @param location  location to be checked
     * @return  true if two locations are the same
     */
    public boolean equals(Location location){
        if (this.row == location.getRow()){
            if (this.column == location.getColumn()) return true;
        }
        return false;
    }

    /**
     * Calculate Manhattan distance between two locations.
     * @param location  location of the target
     * @return  Manhattan distance between two locations
     */
    public int returnDistanceBetweenTwoLocations(Location location){
        System.out.println(this.row); // test
        System.out.println(this.column); // test
        System.out.println(location.row); // test
        System.out.println(this.column); // test
        if (this.row == location.row) return  Math.abs(this.column - location.column);
        if (this.column == location.column) return  Math.abs(this.row - location.row);
        Location newLocation = new Location(0,0);
        if (this.row < location.row) newLocation = this;
        else newLocation = location;
        newLocation.setRow(newLocation.getRow() + ((Math.abs(this.row - location.row) / 2)*2));
        newLocation.setColumn(newLocation.getColumn() + ((Math.abs(this.row - location.row) / 2)*1));
        if (Math.abs(this.row - location.row) % 2 != 0) {
            newLocation.setRow(newLocation.getRow() + 1);
            if (Math.min(this.row, location.row) % 2 != 0) newLocation.setColumn(newLocation.getColumn() + 1);
        }
            if (newLocation.equals(location) || newLocation.equals(this)) return Math.abs(this.row - location.row);
        return Math.abs(this.row - location.row) + Math.abs(this.column - location.column);
    }

    /**
     * print the location info.
     * Number of row and column.
     */
    protected void printInfo(){
        System.out.print("at row: " + row + " column: " + column);
    }

    /**
     * Check if the location is valid.
     * Row must be between 1 and 9.
     * Column must be between 1 and 13.
     * @param location location to be checked
     * @return  true if the location is valid
     */
    public static boolean validLocation(Location location){
        if (location.row >= 1 && location.row <= 9){
            if (location.column >= 1 && location.column <= 13) return true;
        }
        return false;
    }

    /**
     * This method saves valid directions for a specific location.
     * Directions that player can go through them.
     * @return  an array list of valid directions for the location
     */
    protected ArrayList<String> validDirectionsInLocation(){
        ArrayList<String> validDirections = new ArrayList<>();
        if (validLocation(new Location(row, column+1))) validDirections.add("R");
        if (validLocation(new Location(row, column-1))) validDirections.add("L");
        if (row%2 == 0){
            if (validLocation(new Location(row+1,column))) validDirections.add("DR");
            if (validLocation(new Location(row+1, column-1))) validDirections.add("DL");
            if (validLocation(new Location(row-1, column))) validDirections.add("UR");
            if (validLocation(new Location(row-1, column-1))) validDirections.add("UL");
        }
        else {
            if (validLocation(new Location(row+1,column))) validDirections.add("DL");
            if (validLocation(new Location(row+1, column+1))) validDirections.add("DR");
            if (validLocation(new Location(row-1, column))) validDirections.add("UL");
            if (validLocation(new Location(row-1, column+1))) validDirections.add("UR");
        }
        return validDirections;
    }
}
