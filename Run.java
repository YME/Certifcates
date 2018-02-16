import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Run {


    public static void main(String[] args) throws IOException {

        //caminho para o ficheiro latex
        String path = System.getProperty("user.dir") + "/main.tex";


        //caminho para o ficheiro de nomes
        String pathNames = System.getProperty("user.dir") + "/nomes";

        ArrayList<String> names = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try{
            Stream<String> stream = Files.lines(Paths.get(pathNames));
            stream.forEach(s -> names.add(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            Stream<String> stream = Files.lines(Paths.get(path));
            stream.forEach(s -> lines.add(s));
        } catch (IOException e) {
            e.printStackTrace();
        }


        int i=1;
        for (String name : names) {
            List<String> putLines = new ArrayList<>();
            for (String line : lines) {
                putLines.add(line.replaceAll("VAR_NAME", name));
            }
            Path p = Paths.get(System.getProperty("user.dir")+"/CERTIFICADOS_TEX/ficheiro"+i+".tex");
            Files.write(p, putLines, StandardCharsets.UTF_8);
            i++;
        }

    }
}
