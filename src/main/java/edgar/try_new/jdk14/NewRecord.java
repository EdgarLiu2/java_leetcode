package edgar.try_new.jdk14;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edgar.Liu on 2022/12/12
 */
public class NewRecord {

    static void testPersonRecord() {
        PersonRecord person = new PersonRecord("user1", new PersonRecord("user2", null));

        // toString()
        System.out.println(person);

        // equals()
        PersonRecord person2 = new PersonRecord("user1", new PersonRecord("user2", null));
        assert person.equals(person2);

        // hashCode()
        Set<PersonRecord> persons = new HashSet<>();
        persons.add(person);
        persons.add(person2);
        assert persons.size() == 1;
    }

    public static void main(String[] args) {
        testPersonRecord();
    }
}

record PersonRecord (String name, PersonRecord partner) {
}
