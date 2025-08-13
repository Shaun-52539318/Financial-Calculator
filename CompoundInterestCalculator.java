public class CompoundInterestCalculator {
    public static double calculateCompoundInterest(double principal, double rate, int periods) {
        if(principal < 0 || rate < 0 || periods < 0) throw new IllegalArgumentException("Inputs must be non-negative.");
        return principal * Math.pow(1 + rate, periods);
    }
}