import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Olcha on 22.11.2015.
 */
public class DatabaseHelper {
    private static SessionFactory dbSessions;

    //Создаём новую сессию для работы с БД
    private void createSessionFactory(){
        dbSessions = new AnnotationConfiguration()
                .configure("/resources/hibernate.cfg.xml")
                .addAnnotatedClass(MyTable.class)
                .buildSessionFactory();
    }

    //Проверяем наличие пользователя в БД
    public String checkUser(String login, String password){
        Session session = dbSessions.openSession();
        try {
            List userList = session.createQuery("FROM MyTable").list();
            for (Iterator iterator = userList.iterator(); iterator.hasNext();){
                MyTable element = (MyTable)iterator.next();
                if (element.getLogin() == login && element.getPassword() == password)
                    return "Здравствуйте " + element.getFirst_name() + " " + element.getLast_name();
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return "Простите, такой пользователь отсутствует в базе данных.";
    }

    //Добавление нового пользователя в БД, с заданными параметрами
    public String insertUser(String id, String login, String password, String firstName,
                             String lastName, String gender, String birthDate, String phoneNumber){
        Session session = dbSessions.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            MyTable table = new MyTable();
            table.setId(id);
            table.setLogin(login);
            table.setPassword(password);
            table.setFirst_name(firstName);
            table.setLast_name(lastName);
            table.setGender(gender);
            table.setBirthDate(birthDate);
            table.setPhoneNumber(phoneNumber);
            session.save(table);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            return "Ошибка добавления данных";
        }finally {
            session.close();
        }
        return "Данные успешно добавлены";
    }
}
