import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        StringListImpl list = new StringListImpl(5);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list);
        System.out.println("size = " + list.size());
        System.out.println("capacity = " + list.getCapacity());
//        list.add("6");
//        list.add(0, "q");
//        list.set(5,"q");
//        list.remove("5");
//        list.remove(5);
//        System.out.println(list.contains("3"));
//        System.out.println(list.indexOf("3"));
//        System.out.println(list.lastIndexOf("u"));
//        System.out.println(list.get(5));
        StringListImpl otherList = new StringListImpl(5);
        otherList.add("1");
        otherList.add("2");
        otherList.add("3");
        otherList.add("4");
        System.out.println(list.equals(otherList));
//        System.out.println(list.size());
//        System.out.println(list.isEmpty());
//        list.clear();
//        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list);
        System.out.println("size = " + list.size());
        System.out.println("capacity = " + list.getCapacity());
//        System.out.println(max(list));
    }

    public static int max(StringList list) {
        int max = parseInt(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int a = parseInt(list.get(i));
            if(max < a) {
                max = a;
            }
        }
        return max;
    }
}
