package entity;

public class LoanslipDetail {
    private LoanSlip  loanSlip;
    private Book book;
    private int quantity;

    public LoanSlip getLoanSlip() {
        return loanSlip;
    }

    public void setLoanSlip(LoanSlip loanSlip) {
        this.loanSlip = loanSlip;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LoanslipDetail{" +
                "loanSlip=" + loanSlip +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
