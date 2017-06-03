package iWays.Task.DL;

import iWays.Task.Models.Company;

import java.sql.*;

public class CompanyDL {
    private final Connection connection;

    public CompanyDL() {
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

    public int Create(Company company) throws SQLException {
        LinkDL linkDL = new LinkDL();
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("insert into companies (name, fullCompanyName, vatNumber, links) values(?, ?, ?, ?);");
        pstmt.setString(i++,company.getName());
        pstmt.setString(i++,company.getFullCompanyName());
        pstmt.setString(i++, company.getVatNumber());

        pstmt.setInt(i++,linkDL.Create(company.getLinks()));

        pstmt.executeUpdate();
        System.out.println("Company created.");

        pstmt = connection.prepareStatement("select * from companies;");

        ResultSet rs = pstmt.executeQuery();

        rs.last();
        return rs.getInt(1);
    }

    public Company Read(int companyId) throws SQLException {
        int i=1;
        Company result;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("select * from companies where companyId = ?;");
        pstmt.setInt(i++,companyId);

        i=1;
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        result = new Company(rs.getInt(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++), linkDL.Read(rs.getInt(i++)));

        return result;
    }

    public void Update(Company company) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("update companies set name = ?, fullCompanyName = ? , vatNumber = ? where companyId = ? ;");
        pstmt.setString(i++,company.getName());
        pstmt.setString(i++,company.getFullCompanyName());
        pstmt.setString(i++,company.getVatNumber());
        linkDL.Update(company.getLinks());

        pstmt.executeUpdate();
        System.out.println("Company updated.");
    }

    public void Delete(int companyId) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        Company company = Read(companyId);
        PreparedStatement pstmt = connection.prepareStatement("delete from companies where companyId = ? ;");
        pstmt.setInt(i++,companyId);

        pstmt.executeUpdate();
        linkDL.Delete(company.getLinks().getLinkId());
        System.out.println("Company deleted.");
    }
}