package edu.ncsu.csc326.coffeemaker.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ncsu.csc326.coffeemaker.Recipe;

public class InventoryDB {
	
	public InventoryDB(){
	}

	// empty existing inventory data
	public static boolean clearInventory() {
		DBConnection db = new DBConnection();
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean inventoryCleared = false;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement("TRUNCATE TABLE inventory");
			stmt.executeUpdate();
			inventoryCleared = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn, stmt);
		}
		return inventoryCleared;
	}


	// set initial inventory
	public static boolean setInventory(int coffee, int milk, int sugar, int chocolate) {
		DBConnection db = new DBConnection();
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean inventorySet = false;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement("INSERT INTO inventory VALUES(?,?,?,?)");
			stmt.setInt(1, coffee);
			stmt.setInt(2, milk);
			stmt.setInt(3, sugar);
			stmt.setInt(4, chocolate);
			stmt.executeUpdate();
			inventorySet = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn, stmt);
		}
		return inventorySet;
	}




	// add inventory to the coffee maker
	public static boolean addInventory(String field, int amount) {
		DBConnection db = new DBConnection();
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean inventoryAdded = false;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement("UPDATE inventory SET " + field + " = ?");
			stmt.setInt(1, checkInventory(field) + amount);
			stmt.executeUpdate();
			inventoryAdded = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn, stmt);
		}
		return inventoryAdded;
	}

	// expend/subtract the inventory after making coffee
	public static boolean useInventory(String field, int amount) {
		DBConnection db = new DBConnection();
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean inventoryUsed = false;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement("UPDATE inventory SET " + field + " = ?");
			stmt.setInt(1, checkInventory(field) - amount);
			stmt.executeUpdate();
			inventoryUsed = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn, stmt);
		}
		return inventoryUsed;
	}

	// display the amounts of each item that the coffee maker has, in a format that can be used by Inventory.java
	public static int checkInventory(String field) {
		DBConnection db = new DBConnection();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM inventory");
			ResultSet rs = stmt.executeQuery();
			//Only one result b/c name is primary key
			rs.next();
			result = rs.getInt(field);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn, stmt);
		}
		return result;
	}




}
