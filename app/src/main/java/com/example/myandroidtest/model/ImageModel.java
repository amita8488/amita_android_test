package com.example.myandroidtest.model;

public class ImageModel {

    String id;
    String title;
    Thumbnail thumbnail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public class Thumbnail{
        String id;
        String domain;
        String basePath;
        String key;
        int aspectRatio;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getBasePath() {
            return basePath;
        }

        public void setBasePath(String basePath) {
            this.basePath = basePath;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(int aspectRatio) {
            this.aspectRatio = aspectRatio;
        }
    }

}
