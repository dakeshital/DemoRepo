package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRegistration {

	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres",
					"Postgre@123");
			System.out.println("Database connected");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Scanner scanner = new Scanner(System.in);

			System.out.println("enter choice");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter User name:");
				String userName = scanner.next();
				System.out.println("Enter password:");
				String password = scanner.next();
				System.out.println("Confirm password:");
				String confirmPassword = scanner.next();

				if (password.equals(confirmPassword)) {
					String insertQuery = "INSERT INTO Users (userName, password) VALUES ('" + userName + "', '"
							+ password + "')";
					pstmt = con.prepareStatement(insertQuery);
					pstmt.executeUpdate();
					System.out.println("User Registered Successfully");
				} else {
					System.out.println("Password and Confirm Password do not match");
				}
				break;

			case 2:
				System.out.println("Enter User name:");
				String username = scanner.next();
				System.out.println("Enter password:");
				String pass = scanner.next();

				String loginQuery = "SELECT * FROM Users WHERE userName = '" + username + "' AND password = '" + pass
						+ "'";
				pstmt = con.prepareStatement(loginQuery);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("Login Successful");
				} else {
					System.out.println("Invalid Username or Password");
				}
				break;

			case 3:
				System.out.println("Enter User name:");
				String uname = scanner.next();

				String query = "SELECT * FROM Users WHERE userName = '" + uname + "'";
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("User already exists");
				} else {
					System.out.println("User does not exist");
				}
				break;

			case 4:
				System.out.println("Forgot Password ");

				System.out.println("Enter User name:");
				String Username = scanner.next();
				System.out.println("Enter new password:");
				String newPassword = scanner.next();

				String checkquery = "SELECT * FROM Users WHERE userName = '" + Username + "' ";
				pstmt = con.prepareStatement(checkquery);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					String updateQuery = "UPDATE Users SET password ='" + newPassword + "' WHERE userName = '"
							+ Username + "'";
					pstmt = con.prepareStatement(updateQuery);
					pstmt.executeUpdate();
					System.out.println("Password updated");

				} else {
					System.out.println("Invalid Username");
				}
				
				break;

			default:
				System.out.println("Invalid choice");
			}

			con.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			scanner.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
