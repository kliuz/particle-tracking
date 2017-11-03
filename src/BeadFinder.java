/******************************************************************************
  *  Name:    Kevin Liu
  * 
  *  Description:  This program locates all blobs that have luminance above the 
  *  given threshold via depth-first searching. This searching occurs after a 
  *  particle with sufficient luminance is encountered. This program then 
  *  searches throuh all the blobs to find the beads (which have size greater 
  *  than the minimum input size).
  *
  ******************************************************************************/
import java.awt.Color;

public class BeadFinder {
    private Picture frame;              // given frame
    private boolean[][] encountered;    // keeps tracks of visited pixels
    private Stack<Blob> storedBlobs;    // temporarily keeps track of blobs
    
    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        // initializes local variables
        double threshold = tau; 
        frame = picture; 
        // initializes instance variables as well as boolean array
        storedBlobs = new Stack<Blob>(); 
        encountered = new boolean[frame.width()][frame.height()];
        
        // goes through each pixel of the image
        for (int i = 0; i < frame.width(); i++) {
            for (int j = 0; j < frame.height(); j++) {
                // retrieves the luminance of the pixel
                Color color = frame.get(i, j); 
                double lum = Luminance.intensity(color);
                // searches and pushes the blob if it hasn't been met before
                if (!encountered[i][j]) {
                    if (lum >= threshold) {
                        Blob blob = new Blob(); 
                        search(blob, i, j, threshold); 
                        storedBlobs.push(blob);
                    }
                }
                // marks pixel as seen
                else 
                    encountered[i][j] = true;
            }
        } 
    }
    
    // helper method for depth-first searching
    private void search(Blob blob, int x, int y, double tau) {      
        // checks for end cases
        if ((x < 0) || (x >= frame.width())) return;
        if ((y < 0) || (y >= frame.height())) return;
        if (encountered[x][y]) return;
        encountered[x][y] = true; 
        
        // adds pixel to blob if it is bright enough
        Color color = frame.get(x, y);
        double lum = Luminance.intensity(color);
        if (lum < tau) return;
        blob.add(x, y);
        
        // depth-first search
        search(blob, x+1, y, tau);        // right
        search(blob, x, y-1, tau);        // down
        search(blob, x-1, y, tau);        // left
        search(blob, x, y+1, tau);        // up
    }
    
    // counts all the beads
    private int beadCounter(int min) {
        int count = 0; 
        for (Blob x : storedBlobs) {
            if (x.mass() >= min) 
                count++;
        }
        return count; 
    }
    
    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {
        int size = beadCounter(min); 
        int count = 0; 
        Blob[] beads = new Blob[size];
        
        // fills in beads array with beads
        for (Blob x : storedBlobs) {
            if (x.mass() >= min) {
                beads[count] = x;
                count++;
            }
        }    
        return beads;   
    }
    
    //  test client, as described below
    public static void main(String[] args) {
        // intializes variables from command line
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture picture = new Picture(args[2]);
        // prints out beads
        BeadFinder test = new BeadFinder(picture, tau);
        Blob[] beads = test.getBeads(min);
        for (int i = 0; i < beads.length; i++) 
            StdOut.println(beads[i].toString());
    }
}