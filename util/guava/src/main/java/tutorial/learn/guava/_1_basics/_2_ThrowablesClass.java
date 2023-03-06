package tutorial.learn.guava._1_basics;

import com.google.common.base.Throwables;

import java.nio.file.NoSuchFileException;
import java.util.List;

/**
 * <a href="https://www.baeldung.com/guava-throwables">tutorial</a>
 */
public class _2_ThrowablesClass {
    public static void main(String[] args) {
        _2_ThrowablesClass t = new _2_ThrowablesClass();
        t.m2();
    }

    private void m2() {
        try {
            methodThatMightThrowAnything();
        } catch (NoSuchFileException exception) {

            // USEFUL: gets the root cause of exception
            Throwable t = Throwables.getRootCause(exception);

            // USEFUL: return a list of all the throwables in the hierarchy.
            // This is handy if we want to check if it contains a certain type of exception.
            List<Throwable> ts = Throwables.getCausalChain(exception);

            // USEFUL: return the recursive stack trace of the exception.
            String stackTrace = Throwables.getStackTraceAsString(exception);
        }
    }

    // /**
    //  * Throwables.propagateIfInstanceOf() is used to rethrow checked exceptions as is
    //  * if their types are declared in throws clause of the method.
    //  */
    // private void m1() throws IOException, SQLException {
    //
    //     // https://stackoverflow.com/a/5090697/10582056
    //
    //     try {
    //
    //         methodThatMightThrowAnything();
    //
    //     } catch (IKnowWhatToDoWithThisException e) {
    //         // Handle exception
    //
    //         //  if caused throwable is an instance of Error, RuntimeException or Exception
    //         //  we can invoke the propagateIfPossible to propagate it as-is
    //     } catch (Throwable t) {
    //         Throwables.throwIfInstanceOf(t, IOException.class);
    //         Throwables.throwIfInstanceOf(t, SQLException.class);
    //
    //         throw new RuntimeException(t);
    //     }
    // }


    // A method that causes an exception
    private void methodThatMightThrowAnything() throws NoSuchFileException {
        throw new NoSuchFileException("throwing throwable");
    }

    static class IKnowWhatToDoWithThisException extends RuntimeException {
    }
}
