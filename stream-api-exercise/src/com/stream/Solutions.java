package com.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Solutions {
    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        return Arrays.stream(names).filter(s -> s.length() > 5).mapToInt(s -> s.length()).sum();
    }

    public static List<String> transform(List<List<String>> collection) {
        List<String> newCollection = new ArrayList<>();
        //collection.stream().forEach(list->list.forEach(subElement->newCollection.add(subElement)));  //It works but bad
        collection.stream().forEach(list -> newCollection.addAll(list));
        return newCollection;
    }

    public static Person getOldestPerson(List<Person> people) {
        //people.stream().max( (x,y)-> Integer.compare(x.getAge(), y.getAge()));
        return people.stream().max(Comparator.comparingInt(Person::getAge)).get();
    }

    public static Set<String> getKidNames(List<Person> people) {
        return people.stream().filter(person -> person.getAge() < 18).map(person -> person.getName()).collect(Collectors.toSet());
    }

    //Partition these people into adults and kids, you'll need a special collector for this one
    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(person -> person.getAge() >= 18));
    }

    //Group these people by nationality, same kind as the previous exercise
    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(person -> person.getNationality()));
    }

    //Return a comma-separated string of all these people's names
    public static String namesToString(List<Person> people) {
        return people.stream().map(person -> person.getName()).collect(Collectors.joining(", ", "Names: ", "."));
    }

    // Write a method that returns a comma separated string based on a given list of integers. Each element should be
    // preceded by the letter 'e' if the number is even, and preceded by the letter 'o' if the number is odd.
    // For example, if the input list is (3,44), the output should be 'o3,e44'.
    public static String getString(List<Integer> list) {
        return list.stream().map(integer -> (integer % 2 == 0 ? "e" : "o") + integer).collect(Collectors.joining(","));
    }
}
