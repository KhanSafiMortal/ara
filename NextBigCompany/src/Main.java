public class Main {
    public static void main(String[] args) {
        Company Sunshine = new Company();


        for (int i = 0; i < 180; i++) {
            Employee employee = new Operator();
            Sunshine.hire(employee);
        }
        for (int i = 0; i < 80; i++) {
            Manager manager = new Manager();
            Sunshine.setCompanyIncome(manager.getCompanyIncome());
            Sunshine.hire(manager);

        }
        for (int i = 0; i < 10; i++) {
            TopManager topManager = new TopManager();
            topManager.setIncome(Sunshine.getIncome());
            Sunshine.hire(topManager);
        }
        for (int i = 0; i < Sunshine.getSize(); i++) {
            Sunshine.fire(i);
        }
        for (Employee employees : Sunshine.getEmployees()) {
            System.out.println(employees + " " + employees.getMonthSalary());
        }
        System.out.println("В компании работает " + Sunshine.getSize() + " человек");
        System.out.println("Компания заработала " + Sunshine.getIncome() + " рублей.");

    }
}
