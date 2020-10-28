import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVService {
    private String path = "D://data.csv";
    private ArrayList<User> list = new ArrayList<>();

    private void getData() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        try(
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ){
            for (CSVRecord csvRecord : csvParser) {
                String email = csvRecord.get("email");
                String name = csvRecord.get("fullName");
                String password = csvRecord.get("password");

                User user = new User(email, name, password);
                list.add(user);
            }
            reader.close();
        } catch (Exception e) {
            throw new Exception();
        }
        this.list = list;
    }

    public void writeUser(User user) throws Exception {
        getData();
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
                CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("email", "fullName", "password"));
        ) {
            for(User userData : this.list) {
                printer.printRecord(userData.toRecord());
            }
            if(allowRegistration(user)){
                printer.printRecord(user.toRecord());
                printer.close();
            } else {
                printer.close();
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception();
        }
    }

    private boolean allowRegistration(User user){
        EmailValidator validator = new EmailValidator();
        String email = user.getEmail();
        String password = user.getPassword();
        String passwordConf = user.getPasswordConf();
        String checkbox = user.getCheckbox();

        if(!validator.validate(email)){
            return false;
        }

        if(!password.equals(passwordConf)){
            return false;
        }

        if(checkbox == null){
            return false;
        }

        if(isDataContainsUser(user)){
            return false;
        }

        return true;
    }

    private boolean isDataContainsUser(User newUser){
        for(User user: this.list){
            if(user.getEmail().equals(newUser.getEmail())){
                return true;
            }
        }
        return false;
    }
}
