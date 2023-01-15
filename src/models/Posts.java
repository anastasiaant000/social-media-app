package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "posts")
@NamedQueries({
        @NamedQuery(name = "Posts.findByPoster_Username", query = "select p from Posts p where p.poster.username = :username"),
        @NamedQuery(name = "Posts.findByPostTitle", query = "select p from Posts p where p.postTitle = :postTitle"),
        @NamedQuery(name = "Posts.findAll", query = "select p from Posts p"),

})
public class Posts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
    @SequenceGenerator(name = "post_generator", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Profile poster;
    private String postTitle;
    private String postText;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLikes> usersLiked;

    public Posts() {
    }

    public Posts(Profile poster, String postTitle, String postText) {
        this.poster = poster;
        this.postTitle = postTitle;
        this.postText = postText;
    }



    public Profile getPoster() {
        return poster;
    }

    public void setPoster(Profile poster) {
        this.poster = poster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }


    public static int createPost(Posts post) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        entitymanager.persist(post);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();

        return 1;
    }

    public static List getPostsOfUser(String username) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNamedQuery("Posts.findByPoster_Username");
        namedQuery.setParameter("username", username);

        List<Posts> profiles = namedQuery.getResultList();
        entitymanager.close();
        emfactory.close();

        return profiles;
    }

    public int getPostLikes(String postTitle) {
        return PostLikes.getPostLikes(postTitle);
    }

    public static List<Posts> getAllPosts() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNamedQuery("Posts.findAll");


        List<Posts> posts = namedQuery.getResultList();
        entitymanager.close();
        emfactory.close();

        return posts;
    }


  
}

