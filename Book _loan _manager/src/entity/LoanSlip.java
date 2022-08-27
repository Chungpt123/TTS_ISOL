package entity;

public class LoanSlip {
    private Customer customer;
    private LoanslipDetail[] loanslipDetails;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanslipDetail[] getLoanslipDetails() {
        return loanslipDetails;
    }

    public void setLoanslipDetails(LoanslipDetail[] loanslipDetails) {
        this.loanslipDetails = loanslipDetails;
    }
}
