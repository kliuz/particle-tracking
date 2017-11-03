public class Avogadro {
    public static void main(String[] args) {
        double T = 297;                    // temperature (K)
        double N = 9.135e-4;               // viscosity of water (Ns/m^2)
        double P = 0.5e-6;                 // radius of bead (m) 
        double R = 8.31446;                // universal gas constant
        double CONVERSION = 0.175e-6;      // pixel to meter conversion
        double sum = 0;
        double entries = 0;
        while (!StdIn.isEmpty()) {
            double temp = CONVERSION*StdIn.readDouble();
            sum += (temp*temp);
            entries++;
        }
        
        double D = sum / (2*entries);
        double K = (6*Math.PI*N*P*D) / T;
        double avogadro = R / K; 
            
        StdOut.printf("%s %.4e\n", "Boltzmann = ", K); 
        StdOut.printf("%s %.4e\n", "Avogadro = ", avogadro); 
    }
}