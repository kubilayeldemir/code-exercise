package com.stream;

import java.util.*;

import static com.stream.Solutions.getKidNames;
import static com.stream.Solutions.partitionAdults;
import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        //Source: https://www.codingame.com/playgrounds/20782/java-guild-meeting-52018/welcome

        System.out.println("---------Total number of letters in all the names with more than 5 letters----------");
        System.out.println("Testing if [william, jones, aaron, seppe, frank, gilliam] returns 14");
        int x = Solutions.getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam");
        System.out.println(x);

        System.out.println("---------Flatten this multidimensional collection----------");
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        Solutions.transform(collection).stream().forEach(System.out::println);

        System.out.println("---------Get the oldest person from the collection-------------");
//        Person sara = new Person("Sara", 4);
//        Person viktor = new Person("Viktor", 40);
//        Person eva = new Person("Eva", 42);
//        List<Person> collectionMaxAndComp = asList(sara, eva, viktor);
//        System.out.println(Solutions.getOldestPerson(collectionMaxAndComp).getName().equals("Eva"));


        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collectionPartition = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = partitionAdults(collectionPartition);
        System.out.println(result);
    }
}
