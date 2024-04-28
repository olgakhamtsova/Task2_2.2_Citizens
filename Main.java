import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.*;
import java.util.stream.IntStream;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.Comparator;
import java.util.Random;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> stream = persons.stream();
        Stream<Person> stream_1 = stream.filter(p -> p.getAge() < 18);
        long count = stream_1.count();
        System.out.println(count);

        Stream<Person> stream_2 = persons.stream();
        Stream<Person> stream_3 = stream_2.filter(p -> p.getAge() >= 18 && p.getAge() < 27 && p.getSex() == Sex.MAN);
        Stream<String> names_2 = stream_3.map(p -> p.getFamily());
        List<String> names_3 = names_2.collect(Collectors.toList());
        System.out.println(names_3);

        Stream<Person> stream_4 = persons.stream();
        Stream<Person> stream_5 = stream_4.filter(p -> (p.getAge() >= 18 && p.getAge() < 65 && p.getSex() == Sex.MAN) || (p.getAge() >= 18 && p.getAge() < 60 && p.getSex() == Sex.WOMAN));
        Stream<Person> stream_6 = stream_5.sorted(Comparator.comparing(Person::getFamily));
        List<String> names_4 = stream_6.map(p -> p.getFamily()).collect(Collectors.toList());
        System.out.println(names_4);
    }

}