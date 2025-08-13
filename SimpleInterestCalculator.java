public class SimpleInterestCalculator {
    public static double calculateSimpleInterest(double principal, double rate, int periods) {
        if(principal < 0 || rate < 0 || periods < 0) throw new IllegalArgumentException("Inputs must be non-negative.");
        return principal *(1 + rate * periods);
    }
}