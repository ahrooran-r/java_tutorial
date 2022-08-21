package tutorial.learn.apache_commons._1_lang;

import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.arch.Processor;

public class _8_ArchUtils {
    public static void main(String[] args) {

        Processor processor = ArchUtils.getProcessor();
        processor.getArch();
        processor.getType();
        processor.is32Bit();
        processor.is64Bit();
        processor.isX86();
    }
}
