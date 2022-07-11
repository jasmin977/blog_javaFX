package Client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Client.db.DBConnection;
import Client.models.User;

public class DAOUser {
    public static User add(User user) {
        Connection conn = DBConnection.getConnection();
        String req = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getPhoneNo());
            ps.setString(7, user.getImage());
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next())
                System.out.println("User generated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void delete(String name) {
        Connection conn = DBConnection.getConnection();
        String req = "DELETE FROM users where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User update(User user) {

        Connection conn = DBConnection.getConnection();
        String req = "UPDATE users SET name = ? fullname = ?, password = ?, email = ?, gender = ?, phoneNo = ?,image = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getPhoneNo());
            ps.setString(7, user.getImage());
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next())
                System.out.println("User generated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void updateImage(String image,String name) {

        Connection conn = DBConnection.getConnection();
        String req = "UPDATE users SET image = ? where name = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
          
            ps.setString(1, image);
            ps.setString(2, name);
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next())
                System.out.println("image updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    public static User findOne(String name) {
        Connection conn = DBConnection.getConnection();
        String req = "SELECT * FROM users WHERE name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, name);
            ResultSet res = ps.executeQuery();
            if(res.next())
                return new User(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<User> find(String name) {
        ArrayList<User> results = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String req = "SELECT * FROM users WHERE name LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, name);
            ResultSet res = ps.executeQuery();
            while (res.next())
                results.add(new User(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static User findOneByEmail(String email) {

        Connection conn = DBConnection.getConnection();
        String req = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, email);
            ResultSet res = ps.executeQuery();
            while (res.next())
                return new User(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User findOne(String name, String password) {

        Connection conn = DBConnection.getConnection();
        String req = "SELECT * FROM users WHERE name = ? and password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet res = ps.executeQuery();
            while (res.next())
                return new User(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<User> find() {
        ArrayList<User> results = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String req = "SELECT * FROM users";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet res = ps.executeQuery();
            while (res.next())
                results.add(new User(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
