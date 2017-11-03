/******************************************************************************
 *  Name:    Kevin Liu
 *
 ******************************************************************************/
public class Blob {
    // declares instance variables for x and y center of mass
    private double xCenter, yCenter;
    // declares total number of pixels
    private int size;
    
    // creates an empty blob
    public Blob() {
        xCenter = 0;
        yCenter = 0;
        size = 0; 
    }
    // adds pixel (x, y) to this blob
    public void add(int x, int y) {
        size++;
        xCenter = (((size-1)*xCenter) + x) / size; 
        yCenter = (((size-1)*yCenter) + y) / size;
    }
    // number of pixels added to this blob
    public int mass() {
        return size;
    }      
    // Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        double squareX = (this.xCenter - that.xCenter)*(this.xCenter - that.xCenter);
        double squareY = (this.yCenter - that.yCenter)*(this.yCenter - that.yCenter);
        double distance = Math.sqrt(squareX + squareY);
        return distance;
    }
    // string representation of this blob 
    // mass (x, y) four digits of precision after the decimal point
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", size, xCenter, yCenter);
    }      
    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        Blob test1 = new Blob();
        test1.add(1, 3);
        StdOut.println("size: " + test1.mass());
        StdOut.println(test1);
        
        Blob test2 = new Blob();
        test2.add(2, 4);
        test2.add(100, 259);
        StdOut.println("distance to: " + test1.distanceTo(test2));
    }
}