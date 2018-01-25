package Tools;

public class ConfSingleton {
    private String dbPath = "jdbc:postgresql://localhost:5432/online";
    private String filePath ="c:/Users/Zamba/Documents/OJ/";
    private static ConfSingleton instance;
  
    private ConfSingleton(){
        
    }
    
    
    public String getDbPath() {
        return dbPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public static ConfSingleton getInstance() {
        if(instance == null)
            instance = new ConfSingleton();
        return instance;
    } 
}
