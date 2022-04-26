package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/powergrid", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertBilling(String Acc, String Date, String Unit, String Price, String Total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into billing1(`B_ID`,`bill_Acc`,`bill_Date`,`bill_Unit`,`bill_price`,`bill_Total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Acc);
			preparedStmt.setString(3, Date);
			preparedStmt.setString(4, Unit);
			preparedStmt.setString(5, Price);
			preparedStmt.setString(6, Total);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the billing.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBilling() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Account No</th><th>Date</th><th>Unit</th><th>Unit Price</th><th>Total Price</th></tr>";
			String query = "select * from billing1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String B_ID = Integer.toString(rs.getInt("B_ID"));
				String Acc = rs.getString("bill_Acc");
				String Date = rs.getString("bill_Date");
				String Unit = rs.getString("bill_Unit");
				String Price = rs.getString("bill_price");
				String Total = rs.getString("bill_Total");

				// Add into the HTML table
				output += "<tr><td>" + B_ID + "</td>";
				output += "<td>" + Acc + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + Unit + "</td>";
				output += "<td>" + Price + "</td>";
				output += "<td>" + Total + "</td>";
				
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the billing.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBilling(String ID, String Acc, String Date, String Unit, String Price, String Total) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE billing1 SET bill_Acc=?,bill_Date=?,bill_Unit=?,bill_price=?,bill_Total=?" + "WHERE B_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Acc);
			preparedStmt.setString(2, Date);
			preparedStmt.setString(3, Unit);
			preparedStmt.setString(4, Price);
			preparedStmt.setString(5, Total);
			preparedStmt.setInt(6, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the billing.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBilling(String ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from billing1 where B_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Deleted successfully.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
