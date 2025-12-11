import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.StreamSupport;

@Command(name="ls", description = "List directory contents")
public class ls implements Runnable{

    @Option(names = {"-d", "--directory"}, defaultValue = ".", description = "Specify the directory to list")
    private String directory;

    @Option(names = {"-s", "--sort"}, description = "Sort the output alphabetically in ascending order")
    private boolean sort;
    @Option(names = {"-r", "--reverse"}, description = "Sort the output alphabetically in descending order")
    private boolean reverse;
    @Option(names = {"-a", "--all"}, description = "Show all files, including hidden files")
    private boolean all;
    @Option(names = {"-f", "--files"}, description = "Show only regular files")
    private boolean files;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message")
    private boolean helpRequested;

    @Override
    public void run() {
        if (helpRequested) {
            CommandLine.usage(this, System.out);
        }
        Path path = Paths.get(directory);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            var fileStream = StreamSupport.stream(stream.spliterator(), false)
                    .filter(p -> {
                        return all || !p.toFile().isHidden();
                    }).sorted();
            if (reverse) {
                fileStream = fileStream.sorted(Comparator.reverseOrder());
            }
            fileStream.forEach(
                    p -> System.out.println(p.getFileName()));
        } catch (IOException e) {
            System.err.println("No such directory: " + path);
        } catch (SecurityException e) {
            System.err.println("Permission denied");
        }
    } 

    public static void main(String[] args) {
        ls cli = new ls();
        new CommandLine(cli).execute(args);
        

    }
}


    
