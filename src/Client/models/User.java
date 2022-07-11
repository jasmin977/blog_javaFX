package Client.models;

public class User {
    @Override
	public String toString() {
		return "User [name=" + name + ", fullName=" + fullName + ", password=" + password + ", email=" + email
				+ ", gender=" + gender + ", phoneNo=" + phoneNo + "]";
	}

	private String name;
    private String fullName;
    private String password;
    private String email;
    private String gender;
    private String phoneNo;
    private String image;

    public User(String name, String fullName, String password, String email, String gender, String phoneNo) {
        this.setName(name);
        this.setFullName(fullName);
        this.setPassword(password);
        this.setEmail(email);
        this.setGender(gender);
        this.setPhoneNo(phoneNo);
    }

    public User(String name, String fullName, String password, String email, String gender, String phoneNo ,String image) {
        this.setName(name);
        this.setFullName(fullName);
        this.setPassword(password);
        this.setEmail(email);
        this.setGender(gender);
        this.setPhoneNo(phoneNo);
        this.setImage(image);
    }
   
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
