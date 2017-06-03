package iWays.Task.DL;

import iWays.Task.Models.Avatar;

import java.sql.*;

public class AvatarDL {
    private final Connection connection;

    public AvatarDL() {
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

    public int Create(Avatar avatar) throws SQLException {
        LinkDL linkDL = new LinkDL();
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("insert into avatars (contentType, url, links) values(?, ?, ?);");
        pstmt.setString(i++,avatar.getContentType());
        pstmt.setString(i++,avatar.getUrl());
        pstmt.setInt(i++, linkDL.Create(avatar.getLinks()));

        pstmt.executeUpdate();
        System.out.println("Avatar created.");

        pstmt = connection.prepareStatement("select * from avatars;");

        ResultSet rs = pstmt.executeQuery();

        rs.last();
        return rs.getInt(1);
    }

    public Avatar Read(int avatarId) throws SQLException {
        int i=1;
        Avatar result;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("select * from avatars where avatarId = ?;");
        pstmt.setInt(i++,avatarId);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        result = new Avatar(rs.getInt(1),rs.getString(2),rs.getString(3), linkDL.Read(rs.getInt(4)));

        return result;
    }

    public void Update(Avatar avatar) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("update avatars set contentType = ?, url = ? where avatarId = ? ;");
        pstmt.setString(i++,avatar.getContentType());
        pstmt.setString(i++,avatar.getUrl());
        pstmt.setInt(i++,avatar.getAvatarId());
        linkDL.Update(avatar.getLinks());

        pstmt.executeUpdate();
        System.out.println("Avatar updated.");
    }

    public void Delete(int avatarId) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        Avatar avatar = Read(avatarId);
        PreparedStatement pstmt = connection.prepareStatement("delete from avatars where avatarId = ? ;");
        pstmt.setInt(i++,avatarId);

        pstmt.executeUpdate();
        linkDL.Delete(avatar.getLinks().getLinkId());
        System.out.println("Avatar deleted.");
    }
}