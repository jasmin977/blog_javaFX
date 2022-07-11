package Client.models;

import java.util.Date;

/**
 * Blog
 */
public class Topic {

  
    private String name;
    private String img;
  

    public Topic(String name, String img) {
        this.name = name;
        this.img = img;    
    }
    public Topic(String name) {
        
        this.name = name;
       

    }

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}

 

   

}