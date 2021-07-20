import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class PreparedStatementDemo {

    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseProperties.getUrl(), DatabaseProperties.getUsername(),
                DatabaseProperties.getPassword())) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id in (?, ?)");
            preparedStatement.setLong(1, 1L);
            preparedStatement.setLong(2, 2L);

            final ResultSet resultSet = preparedStatement.executeQuery();

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
