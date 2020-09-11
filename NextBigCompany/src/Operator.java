public class Operator implements Employee {
    private final double SALARY = 25000 + Math.random() * 5000;

    @Override
    public double getMonthSalary() {

        return (SALARY);

    }
}
