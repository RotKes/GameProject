package ru.kpfu.itis.group.kadyrov.models;

/**
 * Created by Амир on 04.11.2016.
 */
public class File {
    private int id;
    private int type_id;
    private int creator_id;
    private String name;
    private String description;
    private String file_link;

    public File(int id, int type_id, int creator_id, String name, String description, String file_link) {
        this.id = id;
        this.type_id = type_id;
        this.creator_id = creator_id;
        this.name = name;
        this.description = description;
        this.file_link = file_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }
}
