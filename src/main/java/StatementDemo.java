import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class StatementDemo {

    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseProperties.getUrl(),
                DatabaseProperties.getUsername(), DatabaseProperties.getPassword())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer salary = resultSet.getInt("salary");

                Employee employee = Employee.of(id, name, salary);
                employeeList.add(employee);
            }

            employeeList.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}