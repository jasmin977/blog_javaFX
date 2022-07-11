package Client.models;

import java.util.Date;

/**
 * Blog
 */
public class Article {

    private int id;
    private String title;
    private String content,img;
    private Date created_at;
    private User author;
    private int likes;

    public Article(int id, String title, String content, Date created_at, User author, String img) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.author = author;
        this.img = img;
    }

    public Article(String title, String content,Date created_at, User author,String img) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.created_at = created_at;
        this.img = img;
    }

    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

}