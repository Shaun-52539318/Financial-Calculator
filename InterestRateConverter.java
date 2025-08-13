public class InterestRateConverter {
    public static double nominalToEffective(double nominalRate, int compoundingPeriods) {
        if(nominalRate < 0 || compoundingPeriods <= 0) throw new IllegalArgumentException("Inputs must be non-negative and periods > 0.");
        return Math.pow(1 + (nominalRate / compoundingPeriods), compoundingPeriods) - 1;
    }
}