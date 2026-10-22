package day3;

import java.util.Scanner;

public class EMICalculator{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the loan amount:");
        double loanAmount = scanner.nextDouble();

        System.out.println("Enter the annual interest rate:");
        double annualInterestRate = scanner.nextDouble();

        System.out.println("Choose the loan duration unit:");
        System.out.println("1. Years");
        System.out.println("2. Months");
        int durationUnit = scanner.nextInt();

        int duration = 0;

        if (durationUnit == 1)
        {
            System.out.println("Enter the number of whole years:");
            duration = scanner.nextInt();
        }
        else if (durationUnit == 2)
        {
            System.out.println("Enter the number of whole months:");
            duration = scanner.nextInt();
        }

        scanner.close();

        if (durationUnit != 1 && durationUnit != 2)
        {
            System.out.println("Error: Select 1 for years or 2 for months.");
        }
        else if (loanAmount <= 0 || annualInterestRate < 0 || duration <= 0)
        {
            System.out.println("Error");
            System.out.println("Loan amount and years must be positive.");
            System.out.println("Interest cannot be negative.");
        }
        else
        {
            double monthlyInterestRate = annualInterestRate / 100 / 12;
            int numberOfPayments;

            if (durationUnit == 1)
            {
                numberOfPayments = duration * 12;
            }
            else
            {
                numberOfPayments = duration;
            }

            double emi;

            if (annualInterestRate == 0)
            {
                emi = loanAmount / numberOfPayments;
            }
            else
            {
                double factor = 1;
                int month = 0;

                // Repeat the multiplication once per month
                while (month < numberOfPayments)
                {
                    factor = factor * (1 + monthlyInterestRate);
                    month = month + 1;
                }

                emi = (loanAmount * monthlyInterestRate * factor)
                        / (factor - 1);
            }

            double totalRepayment = emi * numberOfPayments;
            double totalInterest = totalRepayment - loanAmount;
            double principalPercentage = loanAmount / totalRepayment * 100;
            double interestPercentage = totalInterest / totalRepayment * 100;

            System.out.println("Loan Summary");

            System.out.printf("Monthly payment: %.2f%n", emi);

            System.out.printf("Original loan amount: %.2f (%.1f%%)%n", loanAmount, principalPercentage);

            System.out.printf("Total interest paid: %.2f (%.1f%%)%n", totalInterest, interestPercentage);

            System.out.printf("Total amount repaid: %.2f%n", totalRepayment);
            
        }
    }
}
