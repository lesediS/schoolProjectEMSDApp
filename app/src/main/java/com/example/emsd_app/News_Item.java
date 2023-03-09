package com.example.emsd_app;

public class News_Item {

    int newsImage;
    String newsDescription;

    //Constructor
    public News_Item(int newsImage, String newsDescription)
    {
        this.newsImage = newsImage;
        this.newsDescription = newsDescription;
    }

    //Getter and Setter methods
    public int getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(int newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }
}
