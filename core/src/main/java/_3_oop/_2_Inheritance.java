package _3_oop;

class User {

    String userType;

    public void saveWebLink() {
    }

    public void saveMovie() {
    }

    public void saveBook() {
    }

    public void saveBookmark() {
    }

    public void postAReview() {
    }
}

class Staff extends User {

    int id;

    // constructor overloading

    public Staff() {
        super.userType = "Staff";
        System.out.println("Staff constructor 1\n");
    }

    public Staff(int id) {

        // calls default constructor
        this();

        this.id = id;
        System.out.println("Staff constructor 2\n");
    }

    // method overriding
    @Override
    public void postAReview() {
    }
}

class EmailAdmin extends Staff {
    public void handleEmailCampaign() {

    }
}

class Editor extends Staff {
    public void approveReview() {
    }

    public void rejectReview() {
    }
}

// cannot extend this class
// but can create instance
final class ChiefEditor extends Editor {

    public void updateHomepage() {
    }
}

class StaffUtility extends Staff {

    // cannot extend nor instantiate this class
    private StaffUtility() {
    }

    public static void giveSalary(Staff staff) {
        System.out.println("Staff " + staff.toString() + " is given salary\n");
    }
}

public final class _2_Inheritance {
    public static void main(String[] args) {

        Staff staff = new Staff(123456);

        // polymorphism
        Staff chiefEditor = new ChiefEditor();
        // since ChiefEditor() extends Staff, super() is called indirectly on constructor of ChiefEditor()
        // that invokes default constructor of Staff()
        // hence "Staff constructor 1" is printed

        StaffUtility.giveSalary(chiefEditor);
    }
}
