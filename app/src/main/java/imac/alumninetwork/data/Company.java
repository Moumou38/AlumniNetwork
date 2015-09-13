package imac.alumninetwork.data;

import java.util.Date;
import java.util.Map;

/**
 * Created by tony on 16/12/14.
 */
public class Company {

    private String name;
    private String field;
    private String description;
    private String website;

    public Company(String name, String field, String description, String website) {
        this.name = name;
        this.field = field;
        this.description = description;
        this.website = website;
    }

    public Company() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return name;
    }
}
