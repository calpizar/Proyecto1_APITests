package model;

public class Article {
    private String title;
    private String Content;
    public Article(String title, String content) {
        this.title = title;
        Content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return Content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        Content = content;
    }

}
