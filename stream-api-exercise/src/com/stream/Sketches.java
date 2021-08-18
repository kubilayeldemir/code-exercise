package com.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sketches {
    public static <T> void printItems(List<T> list){
        printListItems(list.toArray());
    }
    private static <T> void printListItems(T... list){
        Arrays.stream(list).forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");
        System.out.println("myList:"+myList.getClass().toString());

        List<String> lines = Arrays.asList("spring", "node", "mkyong");

        List<String> result = lines.stream()                // convert list to stream
                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
                .collect(Collectors.toList());              // collect the output and convert streams to a List

        result.forEach(System.out::println);

        List<String> items = new ArrayList<>();
        items.add("test1");
        items.add("test2");
        items.add("test3");
        items.add("test4");
        items.add("test5");

        int[] array = {1, 2};
        System.out.println("Array:"+array.getClass().toString()+"2");

        Consumer<String> consumer = s -> System.out.println(s);
//        items = items.stream().filter(item ->Integer.parseInt(String.valueOf(item.charAt(item.length()-1))) > 3).collect(Collectors.toList());

        items.stream().map(s -> s.toUpperCase(Locale.ROOT));
        Function<String, Integer> nameMappingFunction = String::length;

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(111);
        intList.add(14);
        intList.add(1111);
        intList.add(-50);
        intList.add(12123);
        intList.add(2);
        intList.add(43);

        printItems(intList);

        intList = intList.stream().sorted().collect(Collectors.toList());

        printItems(items);
        System.out.println("--------------------------------------");
        printItems(intList);
//        Traditional java for-each iterator which is an External Iterator.
        for (String item : items) {
            System.out.println(item);
        }

        // Creating predicate
        Predicate<Integer> lesserthan = i -> (i < 18);

        // Calling Predicate method
        System.out.println(lesserthan.test(10));

        Map<String, String> map = new HashMap<>();
        map.put("1", "test1");
        map.put("2", "test2");
        map.put("3", "test3");
        map.put("4", "test4");
        map.put("5", "test5");
        map.put("6", "test6");

        //Traditional way of iterating map elements using external iterator.
        for(Map.Entry<String, String> entry :map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue()); }

        Dictionary<Integer,String> map2 = new Hashtable<Integer,String>();
        AbstractMap<Integer,String> map3 = new HashMap<Integer,String>();
        Map<Integer,String> newMap = new HashMap<Integer,String>();

        ArrayList<String> arrayList = new ArrayList<String>();
        List<String> ListarrayList = new ArrayList<String>();
        arrayList.clone();
        var iter = ListarrayList.iterator();
    }
}
