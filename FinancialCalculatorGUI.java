import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FinancialCalculatorGUI extends JFrame {
    public FinancialCalculatorGUI() {
        setTitle("Financial Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Simple Interest Tab
        tabbedPane.addTab("Simple Interest", new SimpleInterestPanel());

        // 2. Compound Interest Tab
        tabbedPane.addTab("Compound Interest", new CompoundInterestPanel());

        // 3. Interest Rate Converter Tab
        tabbedPane.addTab("Interest Rate Converter", new InterestRateConverterPanel());

        // 4. Force of Interest Tab
        tabbedPane.addTab("Force of Interest", new ForceOfInterestPanel());

        // 5. Loan Annuity Tab
        tabbedPane.addTab("Loan Annuity", new LoanAnnuityPanel());

        // 6. Amortization Table Tab
        tabbedPane.addTab("Amortization Table", new AmortizationTablePanel());

        // 7. FV (No Increase)
        tabbedPane.addTab("FV No Increase", new FVNoIncreasePanel());

        // 8. FV (Growing Annuity)
        tabbedPane.addTab("FV Growing Annuity", new FVGrowingPanel());

        add(tabbedPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // --- Panels for each calculation ---

    class SimpleInterestPanel extends JPanel {
        JTextField principalF = new JTextField(10);
        JTextField rateF = new JTextField(10);
        JTextField periodsF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        SimpleInterestPanel() {
            setLayout(new GridLayout(5,2));
            add(new JLabel("Principal:"));
            add(principalF);
            add(new JLabel("Rate (e.g. 0.05):"));
            add(rateF);
            add(new JLabel("Periods:"));
            add(periodsF);

            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double p = Double.parseDouble(principalF.getText());
                    double r = Double.parseDouble(rateF.getText());
                    int n = Integer.parseInt(periodsF.getText());
                    double res = SimpleInterestCalculator.calculateSimpleInterest(p, r, n);
                    resultL.setText("Result: " + String.format("%,.2f", res));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    class CompoundInterestPanel extends JPanel {
        JTextField principalF = new JTextField(10);
        JTextField rateF = new JTextField(10);
        JTextField periodsF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        CompoundInterestPanel() {
            setLayout(new GridLayout(5,2));
            add(new JLabel("Principal:"));
            add(principalF);
            add(new JLabel("Rate (e.g. 0.05):"));
            add(rateF);
            add(new JLabel("Periods:"));
            add(periodsF);

            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double p = Double.parseDouble(principalF.getText());
                    double r = Double.parseDouble(rateF.getText());
                    int n = Integer.parseInt(periodsF.getText());
                    double res = CompoundInterestCalculator.calculateCompoundInterest(p, r, n);
                    resultL.setText("Result: " + String.format("%,.2f", res));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    class InterestRateConverterPanel extends JPanel {
        JTextField nominalRateF = new JTextField(10);
        JTextField compPeriodsF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        InterestRateConverterPanel() {
            setLayout(new GridLayout(4,2));
            add(new JLabel("Nominal Rate (e.g. 0.07):"));
            add(nominalRateF);
            add(new JLabel("Compounding Periods/Year:"));
            add(compPeriodsF);
            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double r = Double.parseDouble(nominalRateF.getText());
                    int n = Integer.parseInt(compPeriodsF.getText());
                    double res = InterestRateConverter.nominalToEffective(r, n);
                    resultL.setText("Effective Rate: " + String.format("%,.6f", res));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    class ForceOfInterestPanel extends JPanel {
        JTextField rateF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        ForceOfInterestPanel() {
            setLayout(new GridLayout(3,2));
            add(new JLabel("Effective Annual Rate (e.g. 0.07):"));
            add(rateF);
            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double r = Double.parseDouble(rateF.getText());
                    double res = ForceOfInterestCalculator.calculateForceOfInterest(r);
                    resultL.setText("Force of Interest: " + String.format("%,.6f", res));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    class LoanAnnuityPanel extends JPanel {
        JTextField principalF = new JTextField(10);
        JTextField rateF = new JTextField(10);
        JTextField periodsF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        LoanAnnuityPanel() {
            setLayout(new GridLayout(5,2));
            add(new JLabel("Principal:"));
            add(principalF);
            add(new JLabel("Rate (e.g. 0.05):"));
            add(rateF);
            add(new JLabel("Periods:"));
            add(periodsF);

            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double p = Double.parseDouble(principalF.getText());
                    double r = Double.parseDouble(rateF.getText());
                    int n = Integer.parseInt(periodsF.getText());
                    double res = LoanAnnuityCalculator.calculateAnnuityPayment(p, r, n);
                    resultL.setText("Annuity Payment: " + String.format("%,.2f", res));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    class AmortizationTablePanel extends JPanel {
        JTextField principalF = new JTextField(10);
        JTextField rateF = new JTextField(10);
        JTextField periodsF = new JTextField(10);
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"Period", "Payment", "Interest", "Principal", "Balance"}, 0);
        JTable table = new JTable(tableModel);

        AmortizationTablePanel() {
            setLayout(new BorderLayout());

            JPanel inputPanel = new JPanel(new GridLayout(4,2));
            inputPanel.add(new JLabel("Principal:"));
            inputPanel.add(principalF);
            inputPanel.add(new JLabel("Rate (e.g. 0.05):"));
            inputPanel.add(rateF);
            inputPanel.add(new JLabel("Periods:"));
            inputPanel.add(periodsF);
            JButton calcBtn = new JButton("Generate Table");
            inputPanel.add(calcBtn);

            add(inputPanel, BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);

            calcBtn.addActionListener(e -> {
                try {
                    double p = Double.parseDouble(principalF.getText());
                    double r = Double.parseDouble(rateF.getText());
                    int n = Integer.parseInt(periodsF.getText());
                    List<AmortizationCalculator.AmortizationRow> rows = AmortizationCalculator.generateLevelAmortizationTable(p, r, n);
                    tableModel.setRowCount(0);
                    for (AmortizationCalculator.AmortizationRow row : rows) {
                        tableModel.addRow(new Object[]{
                                row.period,
                                String.format("%,.2f", row.payment),
                                String.format("%,.2f", row.interest),
                                String.format("%,.2f", row.principalPaid),
                                String.format("%,.2f", row.balance)
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    class FVNoIncreasePanel extends JPanel {
        JTextField paymentF = new JTextField(10);
        JTextField rateF = new JTextField(10);
        JTextField periodsF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        FVNoIncreasePanel() {
            setLayout(new GridLayout(5,2));
            add(new JLabel("Payment per Period:"));
            add(paymentF);
            add(new JLabel("Rate (e.g. 0.05):"));
            add(rateF);
            add(new JLabel("Periods:"));
            add(periodsF);

            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double x = Double.parseDouble(paymentF.getText());
                    double r = Double.parseDouble(rateF.getText());
                    int n = Integer.parseInt(periodsF.getText());
                    double fv = AmortizationCalculator.futureValueNoIncrease(x, r, n);
                    resultL.setText("FV: " + String.format("%,.2f", fv));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    class FVGrowingPanel extends JPanel {
        JTextField paymentF = new JTextField(10);
        JTextField rateF = new JTextField(10);
        JTextField growthF = new JTextField(10);
        JTextField periodsF = new JTextField(10);
        JLabel resultL = new JLabel("Result: ");

        FVGrowingPanel() {
            setLayout(new GridLayout(6,2));
            add(new JLabel("Initial Payment:"));
            add(paymentF);
            add(new JLabel("Rate (e.g. 0.05):"));
            add(rateF);
            add(new JLabel("Growth/Increase Rate (e.g. 0.03):"));
            add(growthF);
            add(new JLabel("Periods:"));
            add(periodsF);

            JButton calcBtn = new JButton("Calculate");
            add(calcBtn);
            add(resultL);

            calcBtn.addActionListener(e -> {
                try {
                    double x = Double.parseDouble(paymentF.getText());
                    double r = Double.parseDouble(rateF.getText());
                    double j = Double.parseDouble(growthF.getText());
                    int n = Integer.parseInt(periodsF.getText());
                    double fv = AmortizationCalculator.futureValueIncreaseEveryPayment(x, r, j, n);
                    resultL.setText("FV: " + String.format("%,.2f", fv));
                } catch (Exception ex) {
                    resultL.setText("Error: " + ex.getMessage());
                }
            });
        }
    }

    // ---- main method ----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinancialCalculatorGUI::new);
    }
}