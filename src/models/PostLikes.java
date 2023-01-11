package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "post_Likes")
public class PostLikes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postlike_generator")
    @SequenceGenerator(name = "postlike_generator", allocationSize = 1)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts post;
    private String usernameOfPoster;

    private String usernameOfLiker;

    public PostLikes() {
    }

    public PostLikes(Posts post, String usernameOfLiker, String usernameOfPoster) {
        this.post = post;
        this.usernameOfLiker = usernameOfLiker;
        this.usernameOfPoster = usernameOfPoster;
    }

    public static int getPostLikes(String postTitle) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query namedQuery = entitymanager.createNativeQuery("select count(pl.id) from post_likes pl, posts p  where pl.posts_id=p.id and p.posttitle=:postTitle");
        namedQuery.setParameter("postTitle", postTitle);

        var likes = (long) namedQuery.getResultList().get(0);
        entitymanager.close();
        emfactory.close();

        return (int) likes;
    }

    public static int addLike(PostLikes postlike) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("user-unit");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        entitymanager.persist(postlike);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();

        return 1;
    }

    public static void main(String[] args) {
        System.out.println(PostLikes.getPostLikes("5435345"));
    }

}
