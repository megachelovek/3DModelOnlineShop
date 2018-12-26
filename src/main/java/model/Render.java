package model;

public class Render {
    private long idRender;
    private int size;
    private String poRender;

    public Render(long id, int size, String po){
        this.idRender=id;
        this.poRender=po;
        this.size=size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setIdRender(long id) {
        this.idRender = id;
    }

    public long getIdRender() {
        return idRender;
    }

    public String getPoRender() {
        return poRender;
    }

    public void setPoRender(String po) {
        this.poRender = po;
    }
}
