package Client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Client.db.DBConnection;
import Client.models.Article;
import Client.models.Topic;
import Client.models.User;

public class DAOTopic {
    public static Topic add(Topic topic) {
        Connection conn = DBConnection.getConnection();
        String req = "INSERT INTO topic(name,img) VALUES (?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, topic.getName());
            ps.setString(2, topic.getImg());
          
          
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next())
                System.out.println("topic generated successfully key = " + result.getInt(1));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DAOTopic.findOne(topic.getName());
    }
    
    public static ArrayList<Topic> find(){
    	ArrayList<Topic> lists = new ArrayList<>();
    	Connection conn = DBConnection.getConnection();
        String req = "select * from topic";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
                     
            ResultSet result = ps.executeQuery();
             while (result.next())
            	 lists.add(new Topic(result.getString(1),result.getString(2)));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }
 



    public static Topic findOne(String name) {

        Connection conn = DBConnection.getConnection();
        String req = "select topic.name,topic.img "
                +
                "from topic " +
                "where topic.name = ?";
       
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, name);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
            	 return new Topic(result.getString(1), result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

   
}