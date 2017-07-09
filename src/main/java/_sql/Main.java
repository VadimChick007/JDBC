package _sql;


public class Main {
    public static void main(String[] args) {

//        Person person1 = new Person(19,"Vadym");
//        Person person2 = new Person(20,"Dima");
//        Person person3 = new Person(35,"Petro");
//        person1.save();
//        person2.save();
//        person3.save();
//
        Person person = Person.findOne("40ef7536-8f1c-47d0-abe0-2ba25d68dbe5");
        System.out.println(person);


    }
}
