package develop.site.model.service;

public abstract class BaseServiceModel {

    private String Id;

    public BaseServiceModel() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
