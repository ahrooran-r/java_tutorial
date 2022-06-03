package tutorial.learn.core._3_oop;

class BookMark {
    private long id;
    private String title;

    public BookMark(long id, String title) {
        setId(id);
        setTitle(title);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = Math.abs(id);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }
}

interface CacheIterator {
    boolean hasNext();

    BookMark next();
}

class Cache {
    private BookMark[] items;
    private int next = 0;

    public Cache(int size) {
        this.items = new BookMark[size];
    }

    public void addBookMark(BookMark item) {
        if (next < this.items.length) {
            this.items[next++] = item;
        }
    }

    public CacheIterator iterator() {
        return new MyCacheIterator();
    }

    public final class MyCacheIterator implements CacheIterator {

        private int index;

        @Override
        public boolean hasNext() {
            return index < items.length;
        }

        @Override
        public BookMark next() {
            return items[index++];
        }
    }
}

public class _3_InnerClass {
    public static void main(String[] args) {

        Cache recommendedBookMarks = new Cache(5);
        recommendedBookMarks.addBookMark(new BookMark(1, "1"));
        recommendedBookMarks.addBookMark(new BookMark(2, "2"));
        recommendedBookMarks.addBookMark(new BookMark(3, "3"));
        recommendedBookMarks.addBookMark(new BookMark(4, "4"));
        recommendedBookMarks.addBookMark(new BookMark(5, "5"));

        CacheIterator iterator = recommendedBookMarks.iterator();
        while (iterator.hasNext()) {
            BookMark bookMarkObj = iterator.next();
            System.out.format("Title: %s, ID: %d\n", bookMarkObj.getTitle(), bookMarkObj.getId());
        }
    }
}
