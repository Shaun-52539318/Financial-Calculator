public class ForceOfInterestCalculator {
    public static double calculateForceOfInterest(double interestRate) {
        if(interestRate < 0) throw new IllegalArgumentException("Interest rate must be non-negative.");
        return Math.log(1 + interestRate);
    }
}