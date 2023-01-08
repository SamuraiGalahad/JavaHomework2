import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Интерфейс сортировщика файла. За описаниями методов иди в FileSystemR.
 */
public interface FileSystem {
    public void OpenRootFolder() throws FileNotFoundException;

    public void FilesMerging() throws FileNotFoundException;

    public void OpenFolder(File folder, String path) throws FileNotFoundException;

    void SortByName();

    void SortByDependency() throws FileNotFoundException;

    public void PrintFilesList();

    private void CheckCycles(String path, ArrayList<String> paths) throws FileNotFoundException {
        System.err.println("Not implemented!");
    }

    private void FillAnswer(String path, ArrayList<String> answer) {
        System.err.println("Not implemented!");
    }

}
