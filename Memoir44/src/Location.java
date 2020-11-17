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
        // set location initial status to false (empty)
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
     * @param location  location of the target
     * @return  Manhattan distance between two locations
     */
    public int returnDistanceBetweenTwoLocations(Location location){
        if (this.row == location.row) return  Math.abs(this.column - location.column);
        if (this.column == location.column) return  Math.abs(this.row - location.row);
        Location newLocation = new Location(0,0);
        if (this.row < location.row){
            newLocation.setRow(this.row);
            newLocation.setColumn(this.column);
        }
        else{
            newLocation.setRow(location.getRow());
            newLocation.setColumn(location.getColumn());
        }
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
}
