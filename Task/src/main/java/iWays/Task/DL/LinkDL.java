package iWays.Task.DL;

import iWays.Task.Models.Link;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinkDL {

    private final Connection connection;

    public LinkDL() {
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

    public int Create(Link link) throws SQLException {
        int i=1;

        PreparedStatement pstmt = connection.prepareStatement("insert into links (rel, href) values(?, ?);");
        pstmt.setString(i++,link.getRel());
        pstmt.setString(i++,link.getHref());

        pstmt.executeQuery();
        System.out.println("Link created.");

        pstmt = connection.prepareStatement("select * from links;");

        ResultSet rs = pstmt.executeQuery();

        rs.last();
        return rs.getInt(1);
    }

    public Link Read(int linkId) throws SQLException {
        int i=1;
        Link result;
        PreparedStatement pstmt = connection.prepareStatement("select * from links where linkId = ?;");
        pstmt.setInt(i++,linkId);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        result = new Link(rs.getInt(1),rs.getString(2),rs.getString(3));

        return result;
    }

    public List<Link> ReadUserLinks(int userId) throws SQLException {
        int i=1;
        List<Link> result = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement("select * from userLinks where userId = ?;");
        pstmt.setInt(i++,userId);

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            result.add(new Link(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return result;
    }

    public List<Link> ReadBillingLinks(int billingId) throws SQLException {
        int i=1;
        List<Link> result = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement("select * from billingLinks where billingId = ?;");
        pstmt.setInt(i++,billingId);

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            result.add(new Link(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return result;
    }

    public void Update(Link link) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("update links set rel = ?, href = ? where linkId = ? ;");
        pstmt.setString(i++,link.getRel());
        pstmt.setString(i++,link.getHref());
        pstmt.setInt(i++,link.getLinkId());

        pstmt.executeUpdate();
        System.out.println("Link updated.");
    }

    public void Delete(int linkId) throws SQLException {
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("delete from links where linkId = ? ;");
        pstmt.setInt(i++,linkId);

        pstmt.executeUpdate();
        System.out.println("Link deleted.");
    }
}
