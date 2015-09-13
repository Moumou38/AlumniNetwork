package imac.alumninetwork.data;

/**
 * Created by tony on 16/12/14.
 */
public class Alumni {

    private String name;
    private String company;
    private String promo;
    private String field;
    private String email;
    private String phoneNumber;


    public Alumni(String name, String company, String field, String promo, String email, String phoneNumber) {
        this.name = name;
        this.field = field;
        this.company = company;
        this.promo = promo;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Alumni() {};

    public String getName() {
        return name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
