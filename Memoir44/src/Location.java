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

    /**
     * Create a new location on the map game.
     * @param row  number of row of the location
     * @param column  number of column of the location
     */
    public Location(int row, int column){
        this.row = row;
        this.column = column;
    }

    /*************************************** getter methods ************************************/
    public int getRow(){return row;}
    public int getColumn(){return column;}
    /*******************************************************************************************/
    /*************************************** setter methods ************************************/
    public void setRow(int row){this.row = row;}
    public void setColumn(int column){this.column = column;}
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
     * @param location1  location number one
     * @param location2  location number two
     * @return  Manhattan distance between two locations
     */
    public int returnDistanceBetweenTwoLocations(Location location1, Location location2){
        return Math.abs(location1.column - location2.column) + Math.abs(location1.row - location2.row);
    }
}
