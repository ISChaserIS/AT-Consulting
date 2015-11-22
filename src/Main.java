import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Olcha on 13.11.2015.
 */
public class Main {
    private static SessionFactory dbSessions;

    public static void main(String[] args) {
        Main caller = new Main();

        dbSessions = new AnnotationConfiguration()
                .configure("/resources/hibernate.cfg.xml")
                .addAnnotatedClass(MyTable.class)
                .buildSessionFactory();

//				caller.getAllMyTable(); // SELECT * FROM table_name;
//				caller.getOneProperty(); // SELECT column_name FROM table_name;
//				caller.getSeveralProperties(); // SELECT column_name1, column_name2 FROM table_name;
//				caller.setNewLine(); // INSERT INTO table_name VALUES (...);
//				caller.updateLine(); // UPDATE table_name SET column_name = '...' WHERE column_name = '...';
//				caller.deleteLine(); // DELETE FROM table_name WHERE column_name = '...';

        dbSessions.close();
    }
    private void deleteLine () // DELETE FROM table_name WHERE column_name = '...';
    {
        Session session = dbSessions.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            MyTable fm = (MyTable) session.get(MyTable.class, "093d6b97-b0f1-424e-9779-1505c1565f0a");
            fm.setId("12");
            session.delete(fm);
            tx.commit();
            System.out.println("Deleting has been done!");
        }
        catch (HibernateException e) {if (tx!=null) tx.rollback(); e.printStackTrace();} finally {session.close();}
    }

    private void updateLine () // UPDATE table_name SET column_name = '...' WHERE column_name = '...';
    {
        Session session = dbSessions.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            MyTable fm = (MyTable) session.get(MyTable.class, "686ec34b-63b6-4776-a274-e7388533cc5a");
            fm.setId("12");
            session.update(fm);
            tx.commit();
            System.out.println("Updating  has been done!");
        }
        catch (HibernateException e) {if (tx!=null) tx.rollback(); e.printStackTrace();} finally {session.close();}
    }

    private void setNewLine () // INSERT INTO table_name VALUES (...);
    {
        Session session = dbSessions.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            MyTable newFm = new MyTable();
            newFm.setId(UUID.randomUUID().toString());
            newFm.setFirst_name("testLastName");
            newFm.setLast_name("testFirstName");
            newFm.setLogin("testMiddleName");
            newFm.setPassword("9");
            session.save(newFm);
            tx.commit();
            System.out.println("Inserting has been done!");
        }
        catch (HibernateException e) {if (tx!=null) tx.rollback(); e.printStackTrace();} finally {session.close();}
    }

    private void getSeveralProperties () // SELECT column_name1, column_name2 FROM table_name;
    {
        Session session = dbSessions.openSession();
        try
        {
            List<Object[]> propertiesList = session.createQuery("SELECT login, password FROM MyTable").list();

            for (Object[] row : propertiesList)
            {
                System.out.println(row[0] + ", " + row[1] + " years old.");
            }
        }
        catch (HibernateException e) {e.printStackTrace();} finally {session.close();}
    }

    private void getOneProperty () // SELECT column_name FROM table_name;
    {
        Session session = dbSessions.openSession();
        try
        {
            List properties = session.createQuery("SELECT first_name FROM MyTable").list();

            for (Object str : properties)
            {
                System.out.println("First name: " + str);
            }
        }
        catch (HibernateException e) {e.printStackTrace();} finally {session.close();}
    }

    private void getAllMyTable () // SELECT * FROM table_name;
    {
        Session session = dbSessions.openSession();
        try
        {
            List MyTableList = session.createQuery("FROM MyTable").list();

            for (Iterator iterator = MyTableList.iterator(); iterator.hasNext();)
            {
                MyTable fm = (MyTable) iterator.next();
                System.out.print("Last name: " + fm.getId() + "\nFirst name: " + fm.getLast_name() + "\nMiddle name: " + fm.getLogin() + "\nAge: " + fm.getPassword() + "\n\n");
            }
        }
        catch (HibernateException e) {e.printStackTrace();} finally {session.close();}
    }
}
