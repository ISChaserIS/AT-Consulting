import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Olcha on 13.11.2015.
 */
@Entity
@Table(name = "A")
public class MyTable {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="last_name")
    private String last_name;

    @Column(name="first_name")
    private int first_name;

    public MyTable() {}

    // getters

    public String getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public int getFirst_name()
    {
        return first_name;
    }

    // setters

    public void setId(String uuid)
    {
        this.id = id;
    }

    public void setLogin(String lastname)
    {
        this.login = login;
    }

    public void setPassword(String firstname)
    {
        this.password = password;
    }

    public void setLast_name(String middlename)
    {
        this.last_name = last_name;
    }

    public void setFirst_name(String age)
    {
        this.first_name = first_name;
    }
}
