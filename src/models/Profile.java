package models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "Profile.findAll", query = "select p from Profile p"),
        @NamedQuery(name = "Profile.findByUsername", query = "select p from Profile p where p.username = :username"),
        @NamedQuery(name = "Profile.deleteByUsername", query = "delete from Profile p where p.username = :username"),
        @NamedQuery(name = "findLatestID", query = "select p from Profile p order by p.id DESC"),
        @NamedQuery(name = "Profile.existsByUsername", query = "select count(p)  from Profile p where p.username = :username"),
        @NamedQuery(name = "Profile.updateUser", query = "update Profile p set p.username = :username1, p.name = :name, p.email = :email, p.lastName = :lastName, p.dob = :dob, p.password = :password, p.bio = :bio where p.username = :username8"),
        @NamedQuery(name = "Profile.findByUsernameNotContains", query = "select p from Profile p where p.username not like concat('%', :username, '%')")
})
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", allocationSize = 1)
    private Integer id;
    private String username;
    private String name;
    private String email;
    private String lastName;
    private Date dob;
    private String password;
    private String bio;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
    private List<Posts> posts;

    public Profile() {
    }

    public Profile(String username, String name, String lastName, Date dob, String password, String bio, String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
        this.password = password;
        this.bio = bio;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", username='" + username + '\'' + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", dob=" + dob + ", password='" + password + '\'' + ", bio='" + bio + '\'' + '}';
    }

    public static boolean userExits(String username) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
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
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNamedQuery("Profile.findByUsername");
        namedQuery.setParameter("username", username);

        var profile = (Profile) namedQuery.getSingleResult();

        entitymanager.close();
        emfactory.close();

        return profile;
    }

    public static List getUsersUsernames(String username) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNamedQuery("Profile.findByUsernameNotContains");
        namedQuery.setParameter("username", username);

        List<Profile> profiles =  namedQuery.getResultList();
        var usernames = profiles.stream().map((Profile user)->user.getUsername()).collect(Collectors.toList());
        entitymanager.close();
        emfactory.close();

        return usernames;
    }
    public static int createUser(Profile profile) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        entitymanager.persist(profile);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();

        return 1;
    }

    public static boolean updateUser(Profile profile) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        try {
            Query namedQuery = entitymanager.createNamedQuery("Profile.updateUser");
            namedQuery.setParameter("username1", profile.getUsername());
            namedQuery.setParameter("name", profile.getName());
            namedQuery.setParameter("email", profile.getEmail());
            namedQuery.setParameter("lastName", profile.getLastName());
            namedQuery.setParameter("dob", profile.getDob());
            namedQuery.setParameter("password", profile.getPassword());
            namedQuery.setParameter("username8", profile.getUsername());
            namedQuery.setParameter("bio", profile.getBio());


            namedQuery.executeUpdate();
            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}








