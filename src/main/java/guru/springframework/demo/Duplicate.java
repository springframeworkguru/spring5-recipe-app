package guru.springframework.demo;

public class Duplicate {
    public static void main(String[] args){
        System.out.print("non static call  - >");

        car p1  = new car();
        System.out.print("non static call  - >");
        p1.doSomething();
        System.out.print("static call  - >");
        p1.doSomethingStatic();

        car p2  = new bmw();
        System.out.print("non static call  - >");
        p2.doSomething();
        System.out.print("static call  - >");
        p2.doSomethingStatic();

        bmw c1  = new bmw();
        System.out.print("non static call  - >");
        c1.doSomething();
        System.out.print("static call  - >");
        c1.doSomethingStatic();
    }
}

class bmw extends car{
    public void doSomething(){
        System.out.println("non static child of car bmw");
    }

    public static void doSomethingStatic(){
        System.out.println("static child of car bmw");
    }
}

class car {
    public void doSomething(){
        System.out.println("non static parent of car bmw");
    }

    public static void doSomethingStatic(){
        System.out.println("static parent of car bmw");
    }
}


