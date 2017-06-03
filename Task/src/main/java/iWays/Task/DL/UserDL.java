package iWays.Task.DL;

import iWays.Task.Models.User;

import java.sql.*;

public class UserDL {
    private final Connection connection;

    public UserDL() {
        this.connection = openConnection();
    }

    private static Connection openConnection () {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            System.out.println("Error: failed to load PostgreSQL JDBC driver.");
            System.out.println(exception.getMessage());
            System.exit(-1);
        }

        // uspostavljanje konekcije
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/iWays", "postgres", "postgres");
        } catch (SQLException exception) {
            System.out.println("Error: connection failed.");
            System.out.println(exception.getErrorCode() + " " + exception.getMessage());
            System.exit(-1);
        }
        return conn;
    }

    public void Create(User user) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("insert into users (title, firstName, lastName, email, phoneCode, phoneNumber, password, tos) values(?, ?, ?, ?, ?, ?, ?, ?);");
        pstmt.setString(i++,user.getTitle());
        pstmt.setString(i++,user.getFirstName());
        pstmt.setString(i++,user.getLastName());
        pstmt.setString(i++,user.getEmail());
        pstmt.setString(i++,user.getPhoneCode());
        pstmt.setString(i++,user.getPhoneNumber());
        pstmt.setString(i++, user.getPassword());
        pstmt.setString(i++, user.getTos());

        pstmt.executeUpdate();
        System.out.println("User created.");
    }

    public User Read(int userId) {
        int i=1;
        User result = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("select * from users where userId = ?;");
            pstmt.setInt(i++,userId);

            i=1;
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            result = new User(rs.getInt(i++),
                    rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),
                    rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void Update(User user) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("update users set title = ?, firstName = ?, lastName = ?, email = ?, phoneCode = ?, phoneNumber = ?, password = ?, tos = ? where userId = ? ;");
        pstmt.setString(i++,user.getTitle());
        pstmt.setString(i++,user.getFirstName());
        pstmt.setString(i++,user.getLastName());
        pstmt.setString(i++,user.getEmail());
        pstmt.setString(i++,user.getPhoneCode());
        pstmt.setString(i++,user.getPhoneNumber());
        pstmt.setString(i++,user.getPassword());
        pstmt.setString(i++,user.getTos());
        pstmt.setInt(i++,user.getUserId());

        pstmt.executeUpdate();
        System.out.println("User updated.");
    }

    public void Delete(int userId) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("delete from users where userId = ? ;");
        pstmt.setInt(i++,userId);

        pstmt.executeUpdate();

        System.out.println("User deleted.");
    }
}