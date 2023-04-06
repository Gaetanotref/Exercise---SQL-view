import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Students> italianStudents = new ArrayList<>();
        ArrayList<Students> germanStudents = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "developer", "developer");
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE VIEW italian_students AS SELECT name, surname FROM test1 WHERE country = 'Italy'");
            statement.executeUpdate("CREATE VIEW german_students AS SELECT name, surname FROM test1 WHERE country = 'Germany'");

            ResultSet resultSet = statement.executeQuery("select name,surname from italian_students");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                italianStudents.add(new Students(name, surname));
            }
            resultSet = statement.executeQuery("Select name, surname from german_students");
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                germanStudents.add(new Students(name, surname));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
