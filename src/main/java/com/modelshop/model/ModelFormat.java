package com.modelshop.model;

public class ModelFormat {
    private long id_format;
    private String formatName;

    public ModelFormat(long id_format, String formatName){
        this.formatName=formatName;
        this.id_format=id_format;
    }
    public long getIdFormat() {
        return id_format;
    }
    public void setIdFormat(long id){
        this.id_format=id;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }
}
