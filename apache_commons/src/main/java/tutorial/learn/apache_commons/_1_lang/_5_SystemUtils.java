package tutorial.learn.apache_commons._1_lang;

import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;

public class _5_SystemUtils {
    public static void main(String[] args) {

        SystemUtils.getHostName(); // AHROO

        SystemUtils.getJavaHome(); // E:\Programs\Java\graalvm-ee-java17-22.0.0.2

        SystemUtils.getUserDir(); // E:\Code\Tutorials\java_tutorial

        SystemUtils.getUserName(); // msi

        SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8);
        SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_1_8);

    }
}
