EMI Calculator

A Java console application that estimates the equated monthly installment (EMI), or monthly payment, for a fixed interest loan using the amount borrowed, annual interest rate, and duration.

## Features

- Accepts a loan amount and annual interest rate.
- Lets the user choose years or months before entering the duration.
- Calculates the monthly payment, total interest, and total amount repaid.
- Displays the principal and interest as percentages of total repayment.
- Handles loans with a zero interest rate.
- Rejects invalid duration options, nonpositive loan amounts or durations, and negative interest rates.
- Formats amounts to two decimal places and percentages to one decimal place.

## Inputs

| Input | Expected value |
| --- | --- |
| Loan amount | A positive number, such as `10000` |
| Annual interest rate | A percentage, such as `8.99` for 8.99%; enter `0` for no interest |
| Duration unit | `1` for years or `2` for months |
| Duration | A positive whole number in the selected unit |

For the same loan amount and interest rate, entering 5 years or 60 months produces the same result.

## How the Calculation Works

The annual percentage is converted to a monthly decimal rate:

```java
double monthlyInterestRate = annualInterestRate / 100 / 12;
```

Years are multiplied by 12 to get the number of monthly payments. A duration entered in months is used directly.

For a zero interest rate, the monthly payment is the loan amount divided by the number of payments. Otherwise, a `while` loop repeatedly multiplies `factor` by `1 + monthlyInterestRate`, once per payment:

```java
factor = factor * (1 + monthlyInterestRate);
```

The resulting factor is used in the monthly payment formula:

```java
emi = (loanAmount * monthlyInterestRate * factor) / (factor - 1);
```

The summary is calculated from the unrounded monthly payment:

```java
double totalRepayment = emi * numberOfPayments;
double totalInterest = totalRepayment - loanAmount;
double principalPercentage = loanAmount / totalRepayment * 100;
double interestPercentage = totalInterest / totalRepayment * 100;
```

The displayed percentages show each amount's share of total repayment. The interest percentage in the summary is different from the annual interest rate entered by the user. Formatting rounds only the displayed values.

## Implementation Details

- Variables, `double`, and `int`.
- Console input with `Scanner`, `nextDouble()`, and `nextInt()`.
- Arithmetic, assignment, comparison, and logical operators.
- `if`, `else if`, `else`, and nested conditions.
- A `while` loop and a counter updated with `month = month + 1`.
- Output with `println()` and `printf()`.
- Format specifiers: `%.2f` for two decimal places, `%.1f` for one decimal place, `%%` for a percent sign, and `%n` for a new line.
- Closing the scanner after collecting input.

## How to Run

Requires a Java Development Kit (JDK), with `java` and `javac` available in your terminal. No external libraries are required.

From the directory containing `EMICalculator.java`, compile and run:

```sh
javac -d out EMICalculator.java
java -cp out day3.EMICalculator
```

The run command uses the package declared in the source file. Enter the loan amount and annual interest rate, then select `1` for years or `2` for months and enter the duration. Press Enter after each value to proceed to the loan summary.

## Example

With a loan amount of `10000`, annual interest rate of `8.99`, duration unit of `1` (years), and duration of `5`, the expected summary is:

```text
Loan Summary
Monthly payment: 207.54
Original loan amount: 10000.00 (80.3%)
Total interest paid: 2452.10 (19.7%)
Total amount repaid: 12452.10
```

This example assumes a locale that uses a period as the decimal separator.

## Suggested Manual Checks

These are expected results to check when running the program, not a record of executed Java tests.

| Loan amount | Annual rate | Duration | Expected result |
| --- | --- | --- | --- |
| 10000 | 8.99 | 5 years | Monthly payment: `207.54` |
| 10000 | 8.99 | 60 months | Same summary as 5 years |
| 12000 | 0 | 12 months | Monthly payment: `1000.00`; total interest: `0.00` |
| 10000 | 8.99 | Unit option 3 | `Error: Select 1 for years or 2 for months.` |
| 0 | 8.99 | 5 years | Validation error; no loan summary |
| 10000 | -1 | 5 years | Validation error; no loan summary |
| 10000 | 8.99 | 0 months | Validation error; no loan summary |

## Current Limitations

- Assumes a fixed interest rate and equal monthly payments, with no fees or extra payments.
- Accepts only whole years or whole months.
- Performs one calculation per run. Validation errors end the run without asking for corrected input.
- Expects numeric input. Text input or decimal input where an integer is required causes an input mismatch exception.
- Uses `double` arithmetic and unrounded payments for totals. Multiplying the displayed monthly payment by the number of payments may differ slightly from the displayed total repayment.
- Does not impose upper input limits; extremely large durations or rates can exceed the calculation's numeric range.
- The current validation message says "Loan amount and years must be positive." This also applies when the selected duration is in months.

