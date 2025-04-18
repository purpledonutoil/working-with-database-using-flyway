package databaseService;

public class DatabaseInitService{

    public static void main(String[] args) {
        Database db = Database.getInstance();
        FlywayMigration flywayMigration = new FlywayMigration();
        flywayMigration.initDb(db.getConnURL());
    }
}

