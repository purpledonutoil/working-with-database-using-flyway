package databaseService;

import client.ClientDao;
import client.ClientService;

import java.sql.SQLException;

public class DatabaseInitService{

    public static void main(String[] args) throws SQLException {
        Database db = Database.getInstance();
        FlywayMigration flywayMigration = new FlywayMigration();
        flywayMigration.initDb(db.getConnURL());
        ClientDao dao = new ClientDao(db.getConnection());
        ClientService cls = new ClientService(dao);

        //for testing purposes
        long id = cls.create("Johny");
        System.out.println(cls.getById(id));
        cls.setName(id, "Hello");
        System.out.println(cls.getById(id));
        System.out.println(cls.listAll());
        cls.deleteById(id);
        System.out.println(cls.listAll());
    }
}

