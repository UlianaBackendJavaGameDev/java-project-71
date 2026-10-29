package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

  @Parameters(index = "0", description = "path to first file")
  private String filePath1;

  @Parameters(index = "1", description = "path to second file")
  private String filePath2;

  @Option(
      names = {"-f", "--format"},
      description = "output format [default: stylish]",
      defaultValue = "stylish")
  private String format;

  @Override
  public Integer call() throws Exception {
    String diff = Differ.generate(filePath1, filePath2);
    System.out.println(diff);
    return 0;
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
