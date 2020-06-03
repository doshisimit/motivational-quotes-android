package app.simit.com.motivationalquotes;

public class Quote {
private String id;
private String imageURL;
private String hashTags;
private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getHashTags() {
        return hashTags;
    }

    public void setHashTags(String hashTags) {
        this.hashTags = hashTags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", hashTags='" + hashTags + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Quote(String id, String imageURL, String hashTags, String title) {
        this.id = id;
        this.imageURL = imageURL;
        this.hashTags = hashTags;
        this.title = title;
    }
}
