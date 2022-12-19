package models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "Profile.findAll", query = "select p from Profile p"),
        @NamedQuery(name = "Profile.findByUsername", query = "select p from Profile p where p.username = :username"),
        @NamedQuery(name = "Profile.deleteByUsername", query = "delete from Profile p where p.username = :username"),
        @NamedQuery(name = "findLatestID", query = "select p from Profile p order by p.id DESC"),
        @NamedQuery(name = "Profile.existsByUsername", query = "select count(p)  from Profile p where p.username = :username"),
        @NamedQuery(name = "Profile.updateUser", query = "update Profile p set p.username = :username1, p.name = :name, p.email = :email, p.lastName = :lastName, p.dob = :dob, p.password = :password, p.bio = :bio where p.username = :username8"),
        @NamedQuery(name = "Profile.updateNameByUsername", query = "update Profile p set p.name = :name where p.username = :username")
})
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", allocationSize = 1)
	private Integer id;
	private String name;
	private String lastname;
	private Date dob;
	private String username;
	private String password;
	private String bio;
	private String email;

	public Profile() {
    }

	public Profile(String name, String lastname, Date dob, String username, String password, String bio, String email) {
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

public static boolean userExits(String username) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("persistence-unit1");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNamedQuery("Profile.existsByUsername");
        namedQuery.setParameter("username", username);

        var profile = namedQuery.getSingleResult();

        entitymanager.close();
        emfactory.close();
        if ((Long) profile == 0) {
            return false;

        } else {
            return true;
        }
    }

    public static Profile getUser(String username) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("persistence-unit1");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNamedQuery("Profile.findByUsername");
        namedQuery.setParameter("username", username);

        var profile = (Profile) namedQuery.getSingleResult();

        entitymanager.close();
        emfactory.close();

        return profile;
    }

    public static int createUser(Profile profile) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("persistence-unit1");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        entitymanager.persist(profile);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();

        return 1;
    }

    public static boolean updateUser(Profile profile) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("persistence-unit1");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        try {
            Query namedQuery = entitymanager.createNamedQuery("Profile.updateNameByUsername");
            namedQuery.setParameter("username", profile.getUsername());
            namedQuery.setParameter("name", profile.getName());

            namedQuery.executeUpdate();

            entitymanager.close();
            emfactory.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}







