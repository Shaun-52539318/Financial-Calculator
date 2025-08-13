import java.util.List;
import java.util.Scanner;

public class FinancialCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Financial Calculator!");

        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Simple Interest Calculation");
            System.out.println("2. Compound Interest Calculation");
            System.out.println("3. Interest Rate Conversion");
            System.out.println("4. Force of Interest Calculation");
            System.out.println("5. Loan Annuity Calculations");
            System.out.println("6. Amortization Table (Level Payments)");
            System.out.println("7. Future Value of Ordinary Annuity (No Increase)");
            System.out.println("8. Future Value of Growing Annuity (Increase Every Payment)");
            System.out.println("9. Exit");
            System.out.print("Enter choice (1-9): ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    runSimpleInterest(scanner);
                    break;
                case 2:
                    runCompoundInterest(scanner);
                    break;
                case 3:
                    runInterestRateConversion(scanner);
                    break;
                case 4:
                    runForceOfInterest(scanner);
                    break;
                case 5:
                    runLoanAnnuity(scanner);
                    break;
                case 6:
                    runAmortizationTable(scanner);
                    break;
                case 7:
                    runFutureValueNoIncrease(scanner);
                    break;
                case 8:
                    runFutureValueIncreaseEveryPayment(scanner);
                    break;
                case 9:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1-9.");
            }
        }
        scanner.close();
    }

    private static void runSimpleInterest(Scanner scanner) {
        try {
            System.out.println("\n--- Simple Interest Calculation ---");
            double principal = getDoublePrompt(scanner, "Enter principal amount: ");
            double rate = getDoublePrompt(scanner, "Enter interest rate per period (as decimal, e.g., 0.05): ");
            int periods = getIntPrompt(scanner, "Enter number of periods: ");
            double interest = SimpleInterestCalculator.calculateSimpleInterest(principal, rate, periods);
            System.out.printf("Simple Interest: %,.2f\n", interest);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runCompoundInterest(Scanner scanner) {
        try {
            System.out.println("\n--- Compound Interest Calculation ---");
            double principal = getDoublePrompt(scanner, "Enter principal amount: ");
            double rate = getDoublePrompt(scanner, "Enter interest rate per period (as decimal, e.g., 0.05): ");
            int periods = getIntPrompt(scanner, "Enter number of periods: ");
            double compound = CompoundInterestCalculator.calculateCompoundInterest(principal, rate, periods);
            System.out.printf("Compound Amount: %,.2f\n", compound);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runInterestRateConversion(Scanner scanner) {
        try {
            System.out.println("\n--- Interest Rate Converter ---");
            double nominalRate = getDoublePrompt(scanner, "Enter nominal annual interest rate (as decimal): ");
            int compoundingPeriods = getIntPrompt(scanner, "Enter number of compounding periods per year: ");
            double effectiveRate = InterestRateConverter.nominalToEffective(nominalRate, compoundingPeriods);
            System.out.printf("Effective Annual Rate: %,.6f\n", effectiveRate);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runForceOfInterest(Scanner scanner) {
        try {
            System.out.println("\n--- Force of Interest Calculation ---");
            double interestRate = getDoublePrompt(scanner, "Enter effective annual interest rate (as decimal): ");
            double force = ForceOfInterestCalculator.calculateForceOfInterest(interestRate);
            System.out.printf("Force of Interest: %,.6f\n", force);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runLoanAnnuity(Scanner scanner) {
        try {
            System.out.println("\n--- Loan Annuity Calculator ---");
            double principal = getDoublePrompt(scanner, "Enter loan principal: ");
            double rate = getDoublePrompt(scanner, "Enter interest rate per period (as decimal): ");
            int periods = getIntPrompt(scanner, "Enter total number of payments: ");
            double annuity = LoanAnnuityCalculator.calculateAnnuityPayment(principal, rate, periods);
            System.out.printf("Annuity Payment per Period: %,.2f\n", annuity);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runAmortizationTable(Scanner scanner) {
        try {
            System.out.println("\n--- Amortization Table (Level Payments) ---");
            double principal = getDoublePrompt(scanner, "Enter principal (total loan amount): ");
            double rate = getDoublePrompt(scanner, "Enter interest rate per period (as decimal, e.g., 0.05): ");
            int periods = getIntPrompt(scanner, "Enter total number of periods: ");
            List<AmortizationCalculator.AmortizationRow> table = AmortizationCalculator.generateLevelAmortizationTable(principal, rate, periods);
            AmortizationTable.display(table);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runFutureValueNoIncrease(Scanner scanner) {
        try {
            System.out.println("\n--- Future Value of Ordinary Annuity (No Increase) ---");
            double payment = getDoublePrompt(scanner, "Enter payment per period (X): ");
            double rate = getDoublePrompt(scanner, "Enter interest rate per period (as decimal, e.g., 0.05): ");
            int periods = getIntPrompt(scanner, "Enter total number of payments (np): ");
            double fv = AmortizationCalculator.futureValueNoIncrease(payment, rate, periods);
            System.out.printf("Future Value: %,.2f\n", fv);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runFutureValueIncreaseEveryPayment(Scanner scanner) {
        try {
            System.out.println("\n--- Future Value of Growing Annuity (Increase Every Payment) ---");
            double payment = getDoublePrompt(scanner, "Enter initial payment per period (X): ");
            double rate = getDoublePrompt(scanner, "Enter interest rate per period (as decimal, e.g., 0.05): ");
            double growth = getDoublePrompt(scanner, "Enter growth/increase rate per period (as decimal, e.g., 0.03): ");
            int periods = getIntPrompt(scanner, "Enter total number of payments (np): ");
            double fv = AmortizationCalculator.futureValueIncreaseEveryPayment(payment, rate, growth, periods);
            System.out.printf("Future Value: %,.2f\n", fv);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper methods for input with validation
    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input. Please enter an integer: ");
            }
        }
    }

    private static double getDoublePrompt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static int getIntPrompt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }
}