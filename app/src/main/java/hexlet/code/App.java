
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import static java.lang.System.exit;

public class App implements Runnable {
    @Option(names = {"-h", "--help"}, description = "Usage: gendiff [-hV].")
    private boolean helpOption;
// test
    public static void main(String[] args) {
        CommandLine.run(new App(), args);
        System.out.println("Hello World!");
    }
    @Override
    public void run() {
        if (helpOption) {
            System.out.println("Usage: gendiff [-hV] [-f=format] filepath1 filepath2\n"
                  + "Compares two configuration files and shows a difference.\n"
                  + "      filepath1         path to first file\n"
                  + "      filepath2         path to second file\n"
                  + "  -f, --format=format   output format [default: stylish]\n"
                  + "  -h, --help      Show this help message and exit.\n"
                  + "  -V, --version   Print version information and exit.\n");
            exit(0);
        }
    }
}
