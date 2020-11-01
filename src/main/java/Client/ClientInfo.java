package Client;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ClientInfo {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String passport;


    public ClientInfo( String firstName, String lastName, Date dateOfBirth, String gender, String passport) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientInfo)) return false;
        ClientInfo that = (ClientInfo) o;
        return id.equals(that.id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                dateOfBirth.equals(that.dateOfBirth) &&
                gender.equals(that.gender) &&
                passport.equals(that.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, passport);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
