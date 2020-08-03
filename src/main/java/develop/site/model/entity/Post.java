package develop.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.common.util.JsonDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String slug;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @OrderBy("date")
    private List<Comment> comments;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat ( pattern="dd/MM/yyyy hh:mm:ss")
    private Date postedOn;

    @Size(min=1, max=2)
    @ElementCollection
    private List<String> keywords;

    private Boolean active;

    @NotNull
    @ManyToOne
    private Author author;

    @Column(columnDefinition = "TEXT")
    private String teaser;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String body;

    public Post(){
        this.postedOn = new Date();
        this.active = true;
    }

    public Post(String title){
        this.setTitle(title);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Post [title=" + title + "]";
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}