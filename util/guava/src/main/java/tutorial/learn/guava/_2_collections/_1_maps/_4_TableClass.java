package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.collect.*;

import java.util.List;

/**
 * Guava's Table is a collection that represents a table like structure containing
 * rows, columns and the associated cell values. The row and the column act as an ordered pair of keys.
 * <p>
 * <a href="https://www.baeldung.com/guava-table">tutorial</a>
 * <p>
 * Table represents a special map where two keys can be specified in combined fashion to refer to a single value.
 */
public class _4_TableClass {
    public static void main(String[] args) {

        /*
          It's similar to creating a map of maps, for example,
          Map<UniversityName, Map<CoursesOffered, SeatAvailable>>.
         */
        Table<String, String, Integer> universityCourseSeatTable = HashBasedTable.create();

        // Implementation of Table whose row keys and column keys are ordered
        // by their natural ordering or by supplied comparators.
        Table<String, String, Integer> universityCourseSeatTable2 = TreeBasedTable.create();

        List<String> universityRowTable
                = Lists.newArrayList("Mumbai", "Harvard");
        List<String> courseColumnTables
                = Lists.newArrayList("Chemical", "IT", "Electrical");

        // create an immutable instance of Table whose internal data are never going to change
        Table<String, String, Integer> universityCourseSeatTable4
                = ImmutableTable.<String, String, Integer>builder()
                .put("Mumbai", "Chemical", 120).build();

        // DON'T use this unless you know items beforehand and do not plan to add or remove anything
        Table<String, String, Integer> universityCourseSeatTable3
                = ArrayTable.create(universityRowTable, courseColumnTables);


        // CRUD operations

        // add
        universityCourseSeatTable.put("Mumbai", "Chemical", 120);

        // get
        universityCourseSeatTable.get("Mumbai", "Chemical");

        // retrieve row, column maps
        universityCourseSeatTable.rowMap();
        universityCourseSeatTable.columnMap();

        // retrieve respective counterpart of table
        universityCourseSeatTable.row("Mumbai");
        universityCourseSeatTable.column("Chemical");

        // retrieve rows or colums only
        universityCourseSeatTable.rowKeySet();
        universityCourseSeatTable.columnKeySet();

        // check ?
        universityCourseSeatTable.contains("Mumbai", "Chemical");
        universityCourseSeatTable.containsRow("Mumbai");
        universityCourseSeatTable.containsRow("Chemical");
        universityCourseSeatTable.containsValue(120);
        universityRowTable.isEmpty();

        // delete
        universityCourseSeatTable.remove("Mumbai", "Chemical");
    }

}
