import java.util.Date;
public class Profile {
	private int id;
	private String name;
	private String lastname;
	private Date dob;
	private String username;
	private String password;
	private String bio;
	private String email;

	public Profile(int id, String name, String lastname, Date dob, String username, String password, String bio, String email) {
		this.name=name;
		this.lastname=lastname;
		this.dob=dob;
		this.username=username;
		this.password=password;
		this.bio=bio;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname=lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dobe) {
		this.dob=dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String Username) {
		this.username=username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password=password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio=bio;
	}
	public String getEmail() {
	        return email;
	    }

	 public void setEmail(String email) {
	        this.email = email;
    }

 @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", username='" + username + '\'' + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", dob=" + dob + ", password='" + password + '\'' + ", bio='" + bio + '\'' + '}';
    }





}
