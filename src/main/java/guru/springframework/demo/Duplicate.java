package guru.springframework.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Duplicate {
    public static void main(String[] args) {
        List<String> a=new ArrayList();
        a.add("22");
        a.add("1");
        a.add("34");
        a.add("22");
        a.add("16");
 ArrayList<String> newList = new ArrayList<>();
        HashSet<String> s = new HashSet<String>(a);
        int a2=a.size();
        int a1=s.size();
       System.out.println("a2-a1 = " + (a2 - a1));
        for(String s1:s) {
           if (Collections.frequency(a, s1)>1){
               System.out.println("s1 = " + s1+">>>>>"+Collections.frequency(a, s1));
               s.remove(s1);
               System.out.println("s = " + s);
               for (int i=1;i<=Integer.valueOf(Collections.frequency(a, s1));i++){
                   newList.add("1");               }
               newList.addAll(s);
               System.out.println("newList = " + newList);
               //System.out.println("Collections.frequency(a, s1) = " + Collections.frequency(a, s1));
           }
        }
    }
}

