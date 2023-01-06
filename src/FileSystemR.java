import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class FileSystemR implements FileSystem{

    ArrayList<String> paths_;
    String root_;

    public FileSystemR(String root) {
        root_ = Objects.requireNonNullElse(root, "root");
        paths_ = new ArrayList<>();
    }

    @Override
    public void OpenFolder(File folder, String path) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                if (path == null) {
                    OpenFolder(fileEntry, fileEntry.getName());
                } else {
                    OpenFolder(fileEntry, path + "/" + fileEntry.getName());
                }
            } else {
                if (path == null) {
                    paths_.add(fileEntry.getName());
                } else {
                    paths_.add(path + "/" + fileEntry.getName());
                }
            }
        }
    }

    @Override
    public void OpenRootFolder() {
        System.out.println(root_);
        File folder = new File(root_);
        OpenFolder(folder, null);
    }

    @Override
    public void FilesMerging() {
        try {
            File mergedFile = new File("merged.txt");
            mergedFile.createNewFile();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }

        StringBuilder lines = new StringBuilder();

        for (String path: paths_) {
            try {
                File myObj = new File("root/" + path);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    lines.append(data).append("\n");
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        try {
            FileWriter myWriter = new FileWriter("merged.txt");
            myWriter.write(lines.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override
    public void SortByName() {
        Collections.sort(paths_);
        Collections.reverse(paths_);
    }



    @Override
    public void SortByDependency() {

    }

    @Override
    public void PrintFilesList() {
        for (String line: paths_) {
            System.out.println(line);
        }
    }

    @Override
    public void CheckCycles() {

    }
}
