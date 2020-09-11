public class Manager implements Employee {
    private final double SALARY = 40000;
    private double managerIncome = (int) (115000 + (Math.random() * 25000));
    private double incomePercent = 0.05;

    public double getCompanyIncome() {
        return managerIncome;
    }

    @Override
    public double getMonthSalary() {
        return (SALARY + managerIncome * incomePercent);
    }
}
