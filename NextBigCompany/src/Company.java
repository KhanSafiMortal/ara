import java.util.ArrayList;
import java.util.List;

public class Company {
    protected double companyIncome = 0;

    public void setCompanyIncome(double companyIncome) {
        this.companyIncome += companyIncome;
    }

    public double getIncome() {
        return companyIncome;
    }

    private List<Employee> employees = new ArrayList<>(new SalaryComparator());

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getSize() {
        return employees.size();
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }


    public void hireAll(List<Employee> employeeList) {
        employees.addAll(employeeList);
    }

    public void fire(int j) {
        employees.remove(j);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> topSalary = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            topSalary.add(employees.get(i));
        }
        return topSalary;
    }
    public List<Employee> getLowestSalaryStaff(int count){
        List<Employee> lowestSalary = new ArrayList<>();
        for (int i = employees.size(); i < employees.size() - count; i--){
            lowestSalary.add(employees.get(i));
        }
        return lowestSalary;
    }
}
