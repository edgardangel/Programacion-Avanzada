using System;
using Microsoft.Maui.Controls;


namespace LoanAssistant
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void ComputeLoan(object sender, EventArgs e)
        {
            if (double.TryParse(BalanceEntry.Text, out double balance) &&
                double.TryParse(InterestEntry.Text, out double interest) &&
                int.TryParse(MonthsEntry.Text, out int months))
            {
                double monthlyInterest = interest / 1200;
                double multiplier = Math.Pow(1 + monthlyInterest, months);
                double payment = balance * monthlyInterest * multiplier / (multiplier - 1);

                PaymentEntry.Text = payment.ToString("0.00");
                AnalysisLabel.Text = $"Loan Analysis:\nLoan Balance: ${balance:0.00}\nInterest Rate: {interest}%\nMonthly Payment: ${payment:0.00}";
            }
        }

        private void NewLoanAnalysis(object sender, EventArgs e)
        {
            BalanceEntry.Text = InterestEntry.Text = MonthsEntry.Text = PaymentEntry.Text = "";
            AnalysisLabel.Text = "Loan Analysis:";
            ComputeButton.IsEnabled = true;
            NewLoanButton.IsEnabled = false;
        }

        private void ExitButton_Clicked(object sender, EventArgs e)
        {
            System.Diagnostics.Process.GetCurrentProcess().Kill();
        }
    }
}

