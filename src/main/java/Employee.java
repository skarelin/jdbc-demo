
class Employee {
    private Long id;
    private String name;
    private Integer salary;


    static Employee of(String name, Integer salary) {
        return new Employee(null, name, salary);
    }

    static Employee of(Long id, String name, Integer salary) {
        return new Employee(id, name, salary);
    }

    private Employee(Long id, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
