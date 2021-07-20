import java.sql.*;

class CallableStatementDemo {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.getUrl(), DatabaseProperties.getUsername(),
                DatabaseProperties.getPassword())) {
            CallableStatement statement = connection.prepareCall("CALL add_employee(?, ?)");
            statement.setString(1, "Donald");
            statement.setInt(2, 1000);
            statement.execute();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE name=?");
            preparedStatement.setString(1, "Donald");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer salary = resultSet.getInt("salary");

                Employee employee = Employee.of(id, name, salary);

                System.out.println(employee);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}