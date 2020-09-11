public class TopManager implements Employee{
    private final double SALARY = 60000;
    private double premium = 1.5;
    private double Income = 0;


    public void setIncome(double companyIncome) {
        this.Income = companyIncome;
    }

    @Override
    public double getMonthSalary() {
        if (Income > 10000000) {
            return (SALARY + (SALARY * premium));
        } else {
            return (SALARY);
        }
    }
}
