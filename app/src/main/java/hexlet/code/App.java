package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "@|fg(green)gendiff|@", mixinStandardHelpOptions = true, version = "gendiff 0.0",
                description = "Compares two configuration files and shows a difference.")

class App implements Callable<Integer> {

    @Option(names = {"-h", "--help"}, description = "Usage: gendiff [-h] ")
    private boolean helpOption = false;

    @Option(names = {"-f", "--format"},
            description = "Output format [default stylish] ",
            defaultValue = "stylish")
    private String formatName;

    @Parameters(index = "0",
            description = "The path of the first file",
            paramLabel = "pathFile1")
    private String pathFile1;

    @Parameters(index = "1",
            description = "The path of the second file",
            paramLabel = "pathFile2")
    private String pathFile2;

    @Override
    public Integer call() throws Exception {
        try {
            formatName = formatName.toUpperCase();
            String result = Differ.generate(pathFile1, pathFile2, formatName.toUpperCase());
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error in generate differ");
            System.err.println(e.getMessage());
            return 1;
        }

        if (helpOption) {
            System.out.println("Usage: gendiff [-hV] [-f=format] filepath1 filepath2\n"
                    + "Compares two configuration files and shows a difference.\n"
                    + "      filepath1         path to first file\n"
                    + "      filepath2         path to second file\n"
                    + "  -f, --format=format   output format [default: stylish]\n"
                    + "  -h, --help      Show this help message and exit.\n"
                    + "  -V, --version   Print version information and exit.\n");
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
