package Client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Client.db.DBConnection;
import Client.models.Article;
import Client.models.User;

public class DAOArticle {
    public static Article add(Article article) {
        Connection conn = DBConnection.getConnection();
        String req = "INSERT INTO article(title, content, author,img) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
           
            ps.setString(3, article.getAuthor().getName());
            ps.setString(4, article.getImg());
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next())
                System.out.println("Article generated successfully key = " + result.getInt(1));
            article.setId(result.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DAOArticle.findOne(article.getId());
    }

    public static void delete(int _id) {
        Connection conn = DBConnection.getConnection();
        String req = "DELETE FROM article WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, _id);
            int res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Article update(Article article, String author) {
        Connection conn = DBConnection.getConnection();
        String req = "UPDATE article SET title=?, content=? where author = ? and id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setString(3, author);
            ps.setInt(4, article.getId());
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next())
                System.out.println("Article updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public static Article findOne(int _id) {

        Connection conn = DBConnection.getConnection();
        String req = "select article.id,title,content,created_at, name, fullname, password, email,gender, phoneNo, img "
                +
                "from article , users " +
                "where article.id = ? and article.author = users.name ";// and likes.id = article.id " +
        // "group by likes.id";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, _id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                User user = new User(result.getString(5), result.getString(6), result.getString(7), result.getString(8),
                        result.getString(9), result.getString(10));
                return new Article(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4), user,
                        result.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Article> find() {
        ArrayList<Article> results = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String req = "select article.id,title,content,created_at, name, fullname, password, email,gender, phoneNo, img "
                +
                "from article , users " +
                "where article.author = users.name ";// and likes.id = article.id " +
        // "group by likes.id";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                User user = new User(res.getString(5), res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9), res.getString(10));
                results.add(new Article(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4),user, res.getString(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
