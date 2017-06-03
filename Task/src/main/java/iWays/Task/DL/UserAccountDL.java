package iWays.Task.DL;

import iWays.Task.Models.Link;
import iWays.Task.Models.UserAccount;

import java.sql.*;
import java.util.List;

public class UserAccountDL {
    private final Connection connection;

    public UserAccountDL() {
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

    public void Create(UserAccount userAccount) throws SQLException {
        AvatarDL avatarDL = new AvatarDL();
        CompanyDL companyDL = new CompanyDL();
        int i=1;
        PreparedStatement pstmt = connection.prepareStatement("insert into userAccounts (title, firstName, lastName, email, phoneCode, phoneNumber, role, avatar, company) values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
        pstmt.setString(i++,userAccount.getTitle());
        pstmt.setString(i++,userAccount.getFirstName());
        pstmt.setString(i++,userAccount.getLastName());
        pstmt.setString(i++,userAccount.getEmail());
        pstmt.setString(i++,userAccount.getPhoneCode());
        pstmt.setString(i++,userAccount.getPhoneNumber());
        pstmt.setInt(i++, userAccount.getRole());
        pstmt.setInt(i++, avatarDL.Create(userAccount.getAvatar()));
        if(companyDL.Read(userAccount.getCompany().getCompanyId())!=null){
            pstmt.setInt(i++,userAccount.getCompany().getCompanyId());
        }
        else pstmt.setInt(i++, companyDL.Create(userAccount.getCompany()));

        pstmt.executeUpdate();
        System.out.println("User account created.");
    }

    public UserAccount Read(int userId) {
        int i=1;
        UserAccount result = null;
        AvatarDL avatarDL = new AvatarDL();
        CompanyDL companyDL = new CompanyDL();
        BillingAddressDL billingAddressDL = new BillingAddressDL();
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("select * from userAccounts where userId = ?;");
            pstmt.setInt(i++,userId);

            i=1;
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            result = new UserAccount(rs.getInt(i++),
                    rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),
                    rs.getInt(i++),
                    avatarDL.Read(rs.getInt(i++)),
                    companyDL.Read(rs.getInt(i++)),
                    billingAddressDL.ReadBillingAddresses(userId),
                    linkDL.ReadUserLinks(userId));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void Update(UserAccount userAccount) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("update userAccounts set title = ?, firstName = ?, lastName = ?, email = ?, phoneCode = ?, phoneNumber = ?, role = ?, avatar = ?, company = ? where userId = ? ;");
        pstmt.setString(i++,userAccount.getTitle());
        pstmt.setString(i++,userAccount.getFirstName());
        pstmt.setString(i++,userAccount.getLastName());
        pstmt.setString(i++,userAccount.getEmail());
        pstmt.setString(i++,userAccount.getPhoneCode());
        pstmt.setString(i++,userAccount.getPhoneNumber());
        pstmt.setInt(i++,userAccount.getRole());
        pstmt.setInt(i++,userAccount.getAvatar().getAvatarId());
        pstmt.setInt(i++,userAccount.getCompany().getCompanyId());

        pstmt.executeUpdate();
        System.out.println("User account updated.");
    }

    public void Delete(int userId) throws SQLException {
        int i=1;
        LinkDL linkDL = new LinkDL();
        PreparedStatement pstmt = connection.prepareStatement("delete from userAccounts where userId = ? ;");
        pstmt.setInt(i++,userId);

        pstmt.executeUpdate();

        List<Link> links = linkDL.ReadUserLinks(userId);

        i=1;
        pstmt = connection.prepareStatement("delete from userLinks where userId = ? ;");
        pstmt.setInt(i++,userId);

        for(Link l : links) linkDL.Delete(l.getLinkId());

        pstmt.executeUpdate();
        System.out.println("User account deleted.");
    }
}