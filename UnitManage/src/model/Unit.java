package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "roshini22");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUnit(String user_account_no, String usage_date, String used_units, String price_per_unit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " INSERT INTO `electricity_power_unit`(`unit_id`, `user_account_no`, `usage_date`, `used_units`, `price_per_unit`, `total_price`) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			double sum_total_price = Double.parseDouble(used_units) * Double.parseDouble(price_per_unit);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, user_account_no);
			preparedStmt.setString(3, usage_date);
			preparedStmt.setString(4, used_units);
			preparedStmt.setString(5, price_per_unit);
			preparedStmt.setString(6, String.valueOf(sum_total_price));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUnit() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "";
			String query = "select * from electricity_power_unit";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			int count = 1;
			while (rs.next()) {
				String unit_id = Integer.toString(rs.getInt("unit_id"));
				String user_account_no = rs.getString("user_account_no");
				String usage_date = rs.getString("usage_date");
				String used_units = rs.getString("used_units");
				String price_per_unit = rs.getString("price_per_unit");
				String total_price = rs.getString("total_price");

				// Add into the html table
				output += "<tr id='row_"+unit_id+"'><td>" + count + "</td>";
				output += "<td>" + user_account_no + "</td>";
				output += "<td>" + usage_date + "</td>";
				output += "<td>" + used_units + "</td>";
				output += "<td>" + price_per_unit + "</td>";
				output += "<td>" + total_price + "</td>";
				output += "<td><button onclick='showUpdateForm("+unit_id+");' type=\"button\" class=\"btn btn-primary\">Update</button> <button onclick='removeRow("+unit_id+")' type=\"button\" class=\"btn btn-danger\">Delete</button></td>";
				output += "</tr>";
				count++;
				
			}
			con.close();
			// Complete the html table
			
		} catch (Exception e) {
			output = "Error while reading the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUnit(String unit_id, String user_account_no, String usage_date, String used_units, String price_per_unit) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE electricity_power_unit SET user_account_no=?,usage_date=?,used_units=?,price_per_unit=?,total_price=?" + "WHERE unit_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			double sum_total_price = Double.parseDouble(used_units) * Double.parseDouble(price_per_unit);
			// binding values
			preparedStmt.setString(1, user_account_no);
			preparedStmt.setString(2, usage_date);
			preparedStmt.setString(3, used_units);
			preparedStmt.setString(4, price_per_unit);
			preparedStmt.setString(5, String.valueOf(sum_total_price));
			preparedStmt.setInt(6, Integer.parseInt(unit_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the unit.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteUnit(String unit_id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from electricity_power_unit where unit_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(unit_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the unit.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String viewUnit(String unit_id_update) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "";
			String query = "select * from electricity_power_unit where unit_id="+unit_id_update;
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			int count = 1;
			if(rs.next()) {
				String unit_id = Integer.toString(rs.getInt("unit_id"));
				String user_account_no = rs.getString("user_account_no");
				String usage_date = rs.getString("usage_date");
				String used_units = rs.getString("used_units");
				String price_per_unit = rs.getString("price_per_unit");
				String total_price = rs.getString("total_price");

				// Add into the html table
				output += "<div class=\"form-group\">\n" + 
						"						<label for=\"inputEmail3\" class=\"col-sm-2 control-label\">User\n" + 
						"							Account Number</label>\n" + 
						"						<div class=\"col-sm-10\">\n" + 
						"							<input value='"+user_account_no+"' type=\"text\" class=\"form-control\" name=\"user_account_no\"\n" + 
						"								placeholder=\"User Account Number\">\n" + 
						"						</div>\n" + 
						"					</div>\n" + 
						"\n" + 
						"					<div class=\"form-group\">\n" + 
						"						<label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Usage\n" + 
						"							Date</label>\n" + 
						"						<div class=\"col-sm-10\">\n" + 
						"							<input value='"+usage_date+"' type=\"text\" class=\"form-control\" name=\"usage_date\"\n" + 
						"								placeholder=\"Usage Date\">\n" + 
						"						</div>\n" + 
						"					</div>\n" + 
						"\n" + 
						"					<div class=\"form-group\">\n" + 
						"						<label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Used\n" + 
						"							Units</label>\n" + 
						"						<div class=\"col-sm-10\">\n" + 
						"							<input value='"+used_units+"' type=\"text\" class=\"form-control\" id=\"used_units_u\"\n" + 
						"								name=\"used_units\" placeholder=\"Used Units\">\n" + 
						"						</div>\n" + 
						"					</div>\n" + 
						"\n" + 
						"					<div class=\"form-group\">\n" + 
						"						<label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Price\n" + 
						"							Per Unit</label>\n" + 
						"						<div class=\"col-sm-10\">\n" + 
						"							<input value='"+price_per_unit+"' type=\"text\" class=\"form-control\" id=\"price_per_unit_u\"\n" + 
						"								name=\"price_per_unit\" placeholder=\"Price Per Unit\">\n" + 
						"						</div>\n" + 
						"					</div>\n" + 
						"\n" + 
						"					<div class=\"form-group\">\n" + 
						"						<label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Total\n" + 
						"							Price</label>\n" + 
						"						<div class=\"col-sm-10\">\n" + 
						"							<input value='"+total_price+"' type=\"text\" class=\"form-control\" disabled=\"true\"\n" + 
						"								id=\"total_price_u\" name=\"total_price\" placeholder=\"Total Price\">\n" + 
						"						</div>\n" + 
						"					</div>\n" + 
						"\n" + 
						"					<div class=\"form-group\">\n" + 
						"						<div class=\"col-sm-offset-2 col-sm-10\">\n" + 
						"						<input type=\"hidden\" name='unit_id' value='"+unit_id+"' />	<button type=\"submit\" class=\"btn btn-default\">Update</button>\n" + 
						"							<button onclick=\"backtoList();\" type=\"button\"\n" + 
						"								class=\"btn btn-danger\">Cancel</button>\n" + 
						"\n" + 
						"						</div>\n" + 
						"					</div>";
				count++;
				
			}
			con.close();
			// Complete the html table
			
		} catch (Exception e) {
			output = "Error while reading the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

