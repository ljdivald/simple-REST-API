package iWays.Task.DL;

import iWays.Task.Models.Country;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

public class CountryDL {
    private final Connection connection;

    public CountryDL() {
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

    public int Create(Country country) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("insert into countries (name, code, phoneCode) values(?, ?, ?);");
        pstmt.setString(i++,country.getName());
        pstmt.setString(i++,country.getCode());
        pstmt.setString(i++,country.getPhoneCode());

        pstmt.executeUpdate();
        System.out.println("Country created.");

        pstmt = connection.prepareStatement("select * from countries;");

        ResultSet rs = pstmt.executeQuery();

        rs.last();
        return rs.getInt(1);
    }

    public Country Read(int countryId) throws SQLException {
        int i=1;
        Country result;
        PreparedStatement pstmt = connection.prepareStatement("select * from countries where countryId = ?;");
        pstmt.setInt(i++,countryId);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if(rs.next()) result = new Country(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
        else result = null;
        return result;
    }

    public void Update(Country country) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("update countries set name = ?, code = ? , phoneCode = ? where countryId = ? ;");
        pstmt.setString(i++,country.getName());
        pstmt.setString(i++,country.getCode());
        pstmt.setString(i++,country.getPhoneCode());
        pstmt.setInt(i++,country.getCountryId());

        pstmt.executeUpdate();
        System.out.println("Country updated.");
    }

    public void Delete(int countryId) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("delete from countries where countryId = ? ;");
        pstmt.setInt(i++,countryId);

        pstmt.executeUpdate();
        System.out.println("Country deleted.");
    }
}