package _5_enums;

public enum EnumBookGenre {
    BIOGRAPHY(12),
    HORROR(15),

    NEWLY_ADDED_GENRE(18) {
        public boolean isKidNotFriendly(int age) {
            return age < this.getMinAgeToRead();
        }
    };

    private int minAgeToRead;

    // if enums have state, then constructor is necessary
    // constructors are `private` by default
    EnumBookGenre(int minAgeToRead) {
        this.minAgeToRead = minAgeToRead;
    }

    public int getMinAgeToRead() {
        return this.minAgeToRead;
    }

    public boolean isKidFriendly(int age) {

        // this -> represents the Enum constants such as BIOGRAPHY, HORROR etc.
        if (this == EnumBookGenre.BIOGRAPHY) {
            return age >= this.minAgeToRead;
        }
        return false;
    }
    // above method has a limitation as new Enum constants will not be represented in this block
    // hence programmer should remember to update this method

    // instead of this, programmer can also write constant-specific class body
    // to enforce writing methods he can have abstract methods

    // abstract boolean isKidNotFriendly(int age);

    public static void main(String[] args) {

        // value in an enum is called an enumerator

        // EnumClass.values() -> returns all the constants of the enum as array of Enum Class
        EnumBookGenre bookGenre = EnumBookGenre.values()[0];

        // get a single enumerator
        bookGenre.toString(); // -> BIOGRAPHY

        // get enumerator name
        bookGenre.name(); // -> BIOGRAPHY

        // get enumerator ordinal (the positional number)
        bookGenre.ordinal(); // -> 0

        // get enumerator class
        bookGenre.getDeclaringClass(); // -> class Enums.EnumBookGenre

        // check equality
        bookGenre.equals(EnumBookGenre.HORROR); // -> false
        EnumBookGenre.BIOGRAPHY.equals(EnumBookGenre.HORROR);

        // comparing two enumerators
        bookGenre.compareTo(EnumBookGenre.HORROR); // -> -1
        EnumBookGenre.BIOGRAPHY.compareTo(EnumBookGenre.HORROR);

        // calling user defined method
        bookGenre.getMinAgeToRead();

        // can loop over all values and perform above actions
        for (EnumBookGenre loopBookGenre : EnumBookGenre.values()) {
            System.out.println(loopBookGenre.toString());
            System.out.println(loopBookGenre.name());
            System.out.println(loopBookGenre.ordinal());
            System.out.println(loopBookGenre.getDeclaringClass());
            System.out.println(loopBookGenre.equals(EnumBookGenre.HORROR));
            System.out.println(loopBookGenre.compareTo(EnumBookGenre.HORROR));
            System.out.println(loopBookGenre.getMinAgeToRead());
        }
    }
}
