package dummy;
import java.sql.Array;
import java.util.ArrayList;

class Parent {
    public Parent() {}
}

class Child extends Parent {
    public Child() {
        super();
    }
}

class Child2 extends Parent {
    public Child2() {
        super();
    }
}

public class Main {
    public static void main(String[] args) {
    	Parent child = new Child();
        if(child instanceof Child2) {
        	System.out.println("yes");
        }
    }
}
