package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PowerConsumpation {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electrogrid",
					"root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPowerCon(String name, String type, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into powercon(`consumtiopn_id`,`name`,`type`,`description`)" 
			+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, description);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the power consumpation .";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPowerCon() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Name</th><th>Type</th><th>Description</th></tr>";
			String query = "select * from powercon";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String consumtiopn_id = Integer.toString(rs.getInt("consumtiopn_id"));
				String name = rs.getString("name");
				String type = rs.getString("type");
				String description = rs.getString("description");

				output += "<tr><td>" + consumtiopn_id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + description + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the power consumpation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePowerCon(String consumtiopn_id, String name, String type, String description) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE powercon SET name=?,type=?,description=? WHERE consumtiopn_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(consumtiopn_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the power consumpation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePowerCon(String consumtiopn_id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from powercon where consumtiopn_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(consumtiopn_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the power consumpation.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
