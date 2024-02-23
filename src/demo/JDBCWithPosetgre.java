package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCWithPosetgre {
	public static void main(String[] args) throws Exception {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "Postgre@123");
			System.out.println("Database connected");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Scanner scanner = new Scanner(System.in);

			System.out.println("enter choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				String insertQuery = "INSERT INTO Customer VALUES (3, 'riya','riya@gmail.com')";
				pstmt = con.prepareStatement(insertQuery);
				pstmt.executeUpdate();
				System.out.println("Record inserted");

				break;

			case 2:
				System.out.println("Enter Customer ID to delete:");
				int id = scanner.nextInt();
				// con.setAutoCommit(false);
				String deleteQuery = "delete from Customer where id='" + id + "' ";
				pstmt = con.prepareStatement(deleteQuery);
				pstmt.executeUpdate();
				System.out.println("Record deleted");

				break;

			case 3:
				System.out.println("Enter Customer name:");
				String newname = scanner.next();
				System.out.println("Enter Customer email:");
				String newemail = scanner.next();

				String updateQuery = "UPDATE Customer SET name ='" + newname + "' , email = '" + newemail
						+ "' WHERE id = 5";
				pstmt = con.prepareStatement(updateQuery);
				pstmt.executeUpdate();
				System.out.println("Record updated");

				break;

			default:
				System.out.println("Invalid choice");
			}

			String Printquery = "select * from Customer";
			pstmt = con.prepareStatement(Printquery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				System.out.println("ID: " + Id + ", Name: " + name + ", Email: " + email);
			}

			con.close();
			pstmt.close();
			rs.close();
			scanner.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
