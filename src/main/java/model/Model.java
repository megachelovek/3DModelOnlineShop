package model;

public class Model {
    private User author;
    private long id_model;
    private String name;
    private int poly;
    private int vertex;
    private ModelFormat format;
    private Render render;

    public Model(User author, long id_model, String name, int poly, int vertex, ModelFormat format, Render render){
        this.author=author;
        this.id_model=id_model;
        this.name=name;
        this.poly=poly;
        this.vertex=vertex;
        this.format=format;
        this.render=render;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoly() {
        return poly;
    }

    public void setPoly(int poly) {
        this.poly = poly;
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public long getIdModel() {
        return id_model;
    }

    public void setIdModel(long id_model) {
        this.id_model = id_model;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    public ModelFormat getFormat() {
        return format;
    }

    public void setFormat(ModelFormat format) {
        this.format = format;
    }

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }
}
