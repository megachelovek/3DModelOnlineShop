package model;

public class Render {
    private long id_render;
    private int size;
    private String po_render;

    public Render(long id_render, int size, String po_render){
        this.id_render=id_render;
        this.po_render=po_render;
        this.size=size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setIdRender(long id_render) {
        this.id_render = id_render;
    }

    public long getIdRender() {
        return id_render;
    }

    public String getPoRender() {
        return po_render;
    }

    public void setPoRender(String po_render) {
        this.po_render = po_render;
    }
}
