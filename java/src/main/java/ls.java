import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="ls", description = "List directory contents")
public class ls implements Runnable{

    @Option(names = {"-d", "--directory"}, description = "Specify the directory to list")
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
        Path path = Paths.get(directory);
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(path);
            
        } catch (IOException e) {
            System.err.println("No such file or directory: " + directory);
        }
    } 

    public static void main(String[] args) {


    }
}


    
