package model;

public class ModelFormat {
    private long idFormat;
    private String formatName;

    public ModelFormat(long id, String formatName){
        this.formatName=formatName;
        this.idFormat=id;
    }
    public long getIdFormat() {
        return idFormat;
    }
    public void setIdFormat(long id){
        this.idFormat=id;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }
}
