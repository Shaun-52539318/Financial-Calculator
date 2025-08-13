import java.util.List;

public class AmortizationTable {
    public static void display(List<AmortizationCalculator.AmortizationRow> table) {
        System.out.println("Period |   Payment  |  Interest | Principal |  Balance");
        System.out.println("--------------------------------------------------------");
        for (AmortizationCalculator.AmortizationRow row : table) {
            System.out.printf("%4d   |%10.2f |%9.2f |%9.2f |%9.2f\n",
                    row.period, row.payment, row.interest, row.principalPaid, row.balance);
        }
    }
}