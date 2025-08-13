public class LoanAnnuityCalculator {
    public static double calculateAnnuityPayment(double principal, double rate, int periods) {
        if(principal < 0 || rate < 0 || periods <= 0) throw new IllegalArgumentException("Inputs must be non-negative and periods > 0.");
        if(rate == 0) return principal / periods;
        double numerator = principal * rate * Math.pow(1 + rate, periods);
        double denominator = Math.pow(1 + rate, periods) - 1;
        return numerator / denominator;
    }
}