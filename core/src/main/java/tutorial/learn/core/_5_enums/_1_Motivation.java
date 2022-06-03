package tutorial.learn.core._5_enums;

// prior to enums, an `int enum pattern` is used in java to export compile time constants
class Genre {

    // movie genre group
    public static final int MOVIE_GENRE_HORROR = 0;
    public static final int MOVIE_GENRE_DRAMA = 1;

    // book genre group
    public static final int BOOK_GENRE_BIOGRAPHY = 10;
    public static final int BOOK_GENRE_HORROR = 11;

    // private constructor prevents instantiation as well as subclassing
    private Genre() {
    }
}

/*
 * Deficiencies
 *   1. Type safety -> client can mistakenly type BOOK_GENRE_HORROR instead of MOVIE_GENRE_HORROR
 *   2. Brittle
 *       -> since the values are hardcoded at compile time,
 *       -> if author changes these values, client will have to recompile
 *       -> otherwise values will not change and client will use outdated values
 *   3. No namespace protection
 *       -> to distinguish HORROR_GENRE between movie and book, we use
 *       -> MOVIE_GENRE and BOOK_GENRE as prefix
 *       -> which is kind of clumsy
 *   4. Not easy to print constant names
 *   5. cannot iterate constants of a group like movie domain
 *   6.
 * */

class MovieGenre {
    public static final int HORROR = 0;
    public static final int DRAMA = 1;

    private MovieGenre() {
    }
}

class BookGenre {
    public static final int HORROR = 11;
    public static final int BIOGRAPHY = 10;

    private BookGenre() {
    }
}

// in above two classes, although namespace protection is gained, type safety is still an issue.

// on a closer look, it can be seen that HORROR belongs to both BookGenre and MovieGenre
// so instead of taking them as constants, they can be taken as instances of Genre classes

final class NewMovieGenre {

    private String name;
    private int number;

    private NewMovieGenre(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static final NewMovieGenre HORROR = new NewMovieGenre("HORROR", 0);
    public static final NewMovieGenre DRAMA = new NewMovieGenre("DRAMA", 1);
}

// above class is a better design
// NewMovieGenre.HORROR != NewBookGenre.HORROR

// -> Type safe and not Brittle
// only problem -> writing such class is cumbersome

enum EnumMovieGenre {HORROR, DRAMA}

// hence this is simply implemented as enum in java
// upon compilation the enum class compiles into above class which automatically extends java.lang.enum
// enum classes are not-extendable, not-instantiable

public class _1_Motivation {
}
