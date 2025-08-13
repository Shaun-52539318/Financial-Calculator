import java.util.ArrayList;
import java.util.List;

public class AmortizationCalculator {
    public static double futureValueNoIncrease(double X, double r, int np) {
        if (np <= 0) throw new IllegalArgumentException("Number of periods (np) must be positive.");
        if (X < 0) throw new IllegalArgumentException("Payment amount (X) must be non-negative.");
        if (r == 0) return X * np;
        return X * (Math.pow(1 + r, np) - 1) / r;
    }

    public static double futureValueIncreaseEveryPayment(double X, double r, double j, int np) {
        if (np <= 0) throw new IllegalArgumentException("Number of periods (np) must be positive.");
        if (X < 0) throw new IllegalArgumentException("Payment amount (X) must be non-negative.");
        if (r == j) return X * np * Math.pow(1 + r, np - 1);
        return X * (Math.pow(1 + r, np) - Math.pow(1 + j, np)) / (r - j);
    }

    public static List<AmortizationRow> generateLevelAmortizationTable(double principal, double ratePerPeriod, int periods) {
        if (periods <= 0) throw new IllegalArgumentException("Number of periods must be positive.");
        if (principal < 0) throw new IllegalArgumentException("Principal must be non-negative.");
        if (ratePerPeriod < 0) throw new IllegalArgumentException("Interest rate must be non-negative.");

        double payment = LoanAnnuityCalculator.calculateAnnuityPayment(principal, ratePerPeriod, periods);
        double balance = principal;
        List<AmortizationRow> table = new ArrayList<>();

        for (int i = 1; i <= periods; i++) {
            double interest = balance * ratePerPeriod;
            double principalPaid = payment - interest;
            balance -= principalPaid;
            if (i == periods) {
                principalPaid += balance;
                payment += balance;
                balance = 0;
            }
            table.add(new AmortizationRow(i, payment, interest, principalPaid, Math.max(balance, 0)));
        }
        return table;
    }

    public static class AmortizationRow {
        public final int period;
        public final double payment;
        public final double interest;
        public final double principalPaid;
        public final double balance;

        public AmortizationRow(int period, double payment, double interest, double principalPaid, double balance) {
            this.period = period;
            this.payment = payment;
            this.interest = interest;
            this.principalPaid = principalPaid;
            this.balance = balance;
        }
    }
}