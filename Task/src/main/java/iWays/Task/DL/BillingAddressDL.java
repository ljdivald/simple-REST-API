package iWays.Task.DL;

import iWays.Task.Models.BillingAddress;
import iWays.Task.Models.Link;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingAddressDL {
    private final Connection connection;

    public BillingAddressDL() {
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

    public void Create(BillingAddress billingAddress) throws SQLException {
        int i=1;
        CountryDL countryDL = new CountryDL();
        PreparedStatement pstmt = connection.prepareStatement("insert into billingAddresses (userId, address, streetName, city, zip, country) values(?, ?, ?, ?, ?, ?);");
        pstmt.setInt(i++,billingAddress.getUserId());
        pstmt.setString(i++,billingAddress.getAddress());
        pstmt.setString(i++,billingAddress.getStreetName());
        pstmt.setString(i++,billingAddress.getCity());
        pstmt.setString(i++,billingAddress.getZip());
        if(countryDL.Read(billingAddress.getCountry().getCountryId())!=null)
        {
            pstmt.setInt(i++,billingAddress.getCountry().getCountryId());
        }
        else pstmt.setInt(i++,countryDL.Create(billingAddress.getCountry()));

        pstmt.executeUpdate();
        System.out.println("BillingAddress created.");
    }

    public BillingAddress Read(int billingId) throws SQLException {
        int i=1;
        CountryDL countryDL = new CountryDL();
        LinkDL linkDL = new LinkDL();
        BillingAddress result;
        PreparedStatement pstmt = connection.prepareStatement("select * from billingAddresses where billingId = ?;");
        pstmt.setInt(i++,billingId);

        i=1;
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        result = new BillingAddress(rs.getInt(i++),rs.getInt(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),countryDL.Read(rs.getInt(i++)),linkDL.ReadBillingLinks(billingId));

        return result;
    }

    public List<BillingAddress> ReadBillingAddresses(int userId) throws SQLException {
        int i=1;
        CountryDL countryDL = new CountryDL();
        LinkDL linkDL = new LinkDL();
        List<BillingAddress> result = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement("select * from billingAddresses where userId = ?;");
        pstmt.setInt(i++,userId);

        i=1;
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            result.add(new BillingAddress(rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), countryDL.Read(rs.getInt(i++)), linkDL.ReadBillingLinks(rs.getInt(1))));
        }
        return result;
    }

    public void Update(BillingAddress billingAddress) throws SQLException {
        int i=1;
        CountryDL countryDL = new CountryDL();
        PreparedStatement pstmt = connection.prepareStatement("update billingAddresses set userId = ?, address = ?, streetName = ?, city = ?, zip = ?, country = ? where billingId = ? ;");
        pstmt.setInt(i++,billingAddress.getUserId());
        pstmt.setString(i++,billingAddress.getAddress());
        pstmt.setString(i++,billingAddress.getStreetName());
        pstmt.setString(i++,billingAddress.getCity());
        pstmt.setString(i++,billingAddress.getZip());
        if(countryDL.Read(billingAddress.getCountry().getCountryId())==null)
        {
            pstmt.setInt(i++,billingAddress.getCountry().getCountryId());

        }
        else pstmt.setInt(i++,countryDL.Create(billingAddress.getCountry()));
        pstmt.setInt(i++,billingAddress.getBillingId());

        pstmt.executeUpdate();
        System.out.println("Billing address updated.");
    }

    public void Delete(int billingId) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("delete from billingAddresses where billingId = ? ;");
        pstmt.setInt(i++,billingId);

        List<Link> links = linkDL.ReadBillingLinks(billingId);

        i=1;
        pstmt = connection.prepareStatement("delete from billingLinks where billingId = ? ;");
        pstmt.setInt(i++,billingId);

        for(Link l : links) linkDL.Delete(l.getLinkId());

        pstmt.executeUpdate();
        System.out.println("Billing address deleted.");
    }
}