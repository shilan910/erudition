package com.erudition.entity;

import com.erudition.bean.FilesEntity;

import java.util.List;

/**
 * Created by sl on 16-5-16.
 */
public class File {

    private FilesEntity file;

    private List<FilesEntity> relationfiles;

    public FilesEntity getFile() {
        return file;
    }

    public void setFile(FilesEntity file) {
        this.file = file;
    }

    public List<FilesEntity> getRelationfiles() {
        return relationfiles;
    }

    public void setRelationfiles(List<FilesEntity> relationfiles) {
        this.relationfiles = relationfiles;
    }
}
