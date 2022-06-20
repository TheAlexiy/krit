import java.sql.*;
import java.util.Arrays;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/database_krit";
    private static final String username = "Alex";
    private static final String password = "admin";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            executeAlgorithm();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
            resultSet.close();
        }
    }
    public static void executeAlgorithm() throws SQLException {
        int index = 0;
        String[][] unmodifiedArray = new String[15][5];
        String query = "SELECT * FROM t_values";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            unmodifiedArray[index][0] = String.valueOf(resultSet.getInt(1));
            unmodifiedArray[index][1] = resultSet.getString(2);
            unmodifiedArray[index][2] = String.valueOf(resultSet.getInt(3));
            unmodifiedArray[index][3] = resultSet.getString(4);
            unmodifiedArray[index][4] = String.valueOf(resultSet.getDouble(5));
            index++;
        }
        String[][] resultArray = new String[15][5];
        for (int i = 0; i < 15; i++) {
            resultArray[i][0] = unmodifiedArray[i][0];
            resultArray[i][1] = unmodifiedArray[i][1];
            resultArray[i][2] = unmodifiedArray[i][2];
            resultArray[i][3] = unmodifiedArray[i][3];
            switch (resultArray[i][3]) {
                case "Mtoe" -> resultArray[i][4] = unmodifiedArray[i][4];
                case "Gwh" -> resultArray[i][4] = String.valueOf(Double.parseDouble(unmodifiedArray[i - 1][4]) * 11630);
                case "Twh" -> resultArray[i][4] = String.valueOf(Double.parseDouble(unmodifiedArray[i - 2][4]) * 11.63);
            }
        }
        System.out.println(Arrays.deepToString(resultArray).replace("], ", "]\n"));
    }
}
