import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class InitData {

    private static final List<Employee> employees = new ArrayList<>();
    private static final String SQL_CREATE_TABLE = """
                    CREATE TABLE IF NOT EXISTS employee(
                    id serial primary key,
                    name varchar,
                    salary integer
                    )
            """;
    private static final String SQL_CREATE_PROCEDURE = """
            create or replace procedure add_employee(given_name varchar, given_salary integer)
            language plpgsql
            as $$
            begin
            insert into employee(name, salary) values (given_name, given_salary);
            end; $$
            """;

    static {
        employees.add(Employee.of("John", 3000));
        employees.add(Employee.of("Anna", 1250));
        employees.add(Employee.of("Victor", 5000));
        employees.add(Employee.of("Donald", 1000));
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.getUrl(), DatabaseProperties.getUsername(), DatabaseProperties.getPassword())) {

            Statement statement = connection.createStatement();

            statement.execute(SQL_CREATE_TABLE);

            PreparedStatement tablePreparedStatement = connection.prepareStatement("insert into employee(name, salary) values(?, ?)");

            for (Employee employee : employees) {
                tablePreparedStatement.setString(1, employee.getName());
                tablePreparedStatement.setInt(2, employee.getSalary());
                tablePreparedStatement.executeUpdate();
            }

            Statement createProcedureStatement = connection.createStatement();
            createProcedureStatement.execute(SQL_CREATE_PROCEDURE);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
