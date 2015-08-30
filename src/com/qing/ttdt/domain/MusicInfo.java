package com.qing.ttdt.domain;

import java.io.Serializable;

public class MusicInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -8398119827569509660L;
    private String id;//id
    private String displayName;//显示名称
    private String title;//标题
    private String artist;//歌手
    private String album;//专辑
    private String albumId;//专辑
    private String year;//发行年份
    private int size;//文件大小
    private int duration;//歌曲时长
    private String path;//(data)保存路径
    private int favourite;
    private int quality;
    private String lrcPath;//歌词路径
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getAlbumId() {
        return albumId;
    }
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getFavourite() {
        return favourite;
    }
    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
    public int getQuality() {
        return quality;
    }
    public void setQuality(int quality) {
        this.quality = quality;
    }
    public String getLrcPath() {
        return lrcPath;
    }
    public void setLrcPath(String lrcPath) {
        this.lrcPath = lrcPath;
    }
    @Override
    public String toString() {
        return "MusicInfo [id=" + id + ", displayName=" + displayName
                + ", title=" + title + ", artist=" + artist + ", album="
                + album + ", year=" + year + ", size=" + size + ", duration="
                + duration + ", path=" + path + ", favourite=" + favourite
                + ", quality=" + quality + ", lrcPath=" + lrcPath + "]";
    }
    
}
