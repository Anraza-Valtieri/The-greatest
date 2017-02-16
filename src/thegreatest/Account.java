package thegreatest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account{
	private String name;
	private String password;
	private String uniqID;
	private int type;
	private String email;
	private int aID;

	public Account(){
		this.name = "";
		this.password = "";
		this.email = "";
		this.uniqID = "";
		this.type = 0;
		this.aID = 0;
	}

	public Account(String name, String password, String uniqID, int type, int aID, String email) {
		this.name = name;
		this.password = password;
		this.uniqID = uniqID;
		this.type = type;
		this.aID = aID;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUniqID() {
		return uniqID;
	}

	public void setUniqID(String uniqID) {
		this.uniqID = uniqID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getaID() {
		return aID;
	}

	public void setaID(int aID) {
		this.aID = aID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	public void createAccount(String user, String uniqID, String email, String pass, int type)  {
		Connection connection = null;
		PreparedStatement statement = null;

		String sql = "INSERT INTO accounts (name, uniqueID, email, password, type) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = dnConnector.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, user);
			statement.setString(2, uniqID);
			statement.setString(3, email);
			statement.setString(4, pass);
			statement.setInt(5, type);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new account was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return;
	}

	public boolean getLogin(String uniqueID, String PassWord){

		String dbId = "";
		String dbPw = "";

		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = dnConnector.getConnection();
			String SQL = "SELECT * FROM accounts WHERE uniqueID=?";
			if(connection == null) {
				System.out.println("Error: No connection to DB");
				return false;
			}
			statement = connection.prepareStatement(SQL);
			statement.setString(1, uniqueID);
			rs = statement.executeQuery();
	    /*rs = statement.executeQuery(SQL);*/

			//check uniqueId and password
			if (rs.next())
			{
				dbId = rs.getString("uniqueID");
				dbPw = rs.getString("password");
			}

			if(dbId == null || dbId == ""){
				System.out.println("ID not found!\n----");
				return false;
			}

			if (uniqueID.equals(dbId) && password.equals(dbPw))
			{
				System.out.println("Successful Login!\n----");
				String dbName = rs.getString("name");
				String dbEmail = rs.getString("email");
				int dbType = rs.getInt("type");
				int dbAid = rs.getInt("id");

				setName(dbName);
				setUniqID(dbId);
				setPassword(dbPw);
				setEmail(dbEmail);
				setType(dbType);
				setaID(dbAid);
				return true;
			}



		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Unsuccessful Login\n----");
		return false;
	}


	public Account getAccount(int aId)  {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;

		Account acc = null;
		String query = "SELECT * FROM accounts WHERE id=" + aId;
		try {
			connection = dnConnector.getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery(query);

			if (rs.next()) {
				acc = new Account();
				acc.setName(rs.getString("name"));
				acc.setUniqID(rs.getString("uniqueID"));
				acc.setEmail(rs.getString("email"));
				acc.setPassword(rs.getString("password"));
				acc.setType(rs.getInt("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return acc;
	}

	public void updateAccount(String name, String uniqID, String email, String password)  {
		Connection connection = null;
		PreparedStatement statement = null;

		String sql = "UPDATE accounts SET name=?, email=?, password=?, WHERE uniqueID=?";

		try {
			connection = dnConnector.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, uniqID);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("An account was updated successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return;
	}

	public void deleteAccount(String uniqID)  {
		Connection connection = null;
		PreparedStatement statement = null;

		String sql = "DELETE FROM accounts WHERE uniqueID=?";

		try {
			connection = dnConnector.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, uniqID);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("An account was deleted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return;
	}

}

