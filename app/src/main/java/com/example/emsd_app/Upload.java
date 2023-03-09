package com.example.emsd_app;

public class Upload {
    private String id;
    private String imgDescription;
    private String mImageUrl;

    public Upload()
    {
       //Empty constructor required
    }

    public Upload(String id,String imgDescription, String mImageUrl)
    {
        this.id = id;
        if(imgDescription.trim().equals("")){
            imgDescription = "No description";
        }
        this.imgDescription = imgDescription;
        this.mImageUrl = mImageUrl;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgDescription() {
        return imgDescription;
    }

    public void setImgDescription(String imgDescription) {
        this.imgDescription = imgDescription;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
