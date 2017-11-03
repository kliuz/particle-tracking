/******************************************************************************
  *  Name:    Kevin Liu
  * 
  *  Description:  This program keeps track of bead motion across multiple
  *  frames. For each bead in the second image (time-wise), distance is 
  *  printed out.
  *
  ******************************************************************************/
public class BeadTracker {
    public static void main(String[] args) {
        Stopwatch help = new Stopwatch();
        // parses numerical command-line arguments
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        
        // starts examining the image files, two frames at a time
        for (int i = 3; i < (args.length-1); i++) {
            // creates picture objects
            Picture image1 = new Picture(args[i]);
            Picture image2 = new Picture(args[i+1]);
            // creates BeadFinder objects
            BeadFinder blobs1 = new BeadFinder(image1, tau);
            BeadFinder blobs2 = new BeadFinder(image2, tau);
            // creates Blob object arrays that distances will be computed from
            Blob[] beads1 = blobs1.getBeads(min);
            Blob[] beads2 = blobs2.getBeads(min);
            
            // for each x in the "second" image, find lowest distance
            for (Blob x : beads2) {
                double lowest = Double.POSITIVE_INFINITY;
                for (Blob y : beads1) {
                    double distance = x.distanceTo(y);
                    if (distance < lowest) {
                        lowest = distance;
                    }
                }
                // print distance if it is less than acceptable particle distance
                if (lowest <= delta)
                    StdOut.printf("%.4f\n", lowest);
            } 
            
            
        }
        StdOut.println(help.elapsedTime());
    }
}