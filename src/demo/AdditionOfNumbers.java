package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdditionOfNumbers {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres",
					"Postgre@123");
			System.out.println("Database connected");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from AddNumbers");

			int number1 = 0;
			int number2 = 0;
			while (rs.next()) {
				number1 = rs.getInt("num1");
				number2 = rs.getInt("num2");
				System.out.println("NUM1: " + number1 + ", NUM2: " + number2);
			}
			int result = number1 + number2;

			String insertQuery = "insert into Result values ('" + result + "')";
			stmt.executeUpdate(insertQuery);

			System.out.println("RESULT: " + result);

			con.close();
			stmt.close();
			rs.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
