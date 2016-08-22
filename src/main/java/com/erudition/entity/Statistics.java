package com.erudition.entity;

/**
 * Created by sl on 16-8-21.
 */
public class Statistics {

    private String fileName;
    private int fileId;
    private int hitNum;
    private int collectionNum;

    public Statistics(String fileName, int fileId, int hitNum, int collectionNum) {
        this.fileName = fileName;
        this.fileId = fileId;
        this.hitNum = hitNum;
        this.collectionNum = collectionNum;
    }

    public Statistics(){

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getHitNum() {
        return hitNum;
    }

    public void setHitNum(int hitNum) {
        this.hitNum = hitNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }
}
