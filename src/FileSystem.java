import java.io.File;

public interface FileSystem {
    public void OpenRootFolder();
    public void FilesMerging();
    public void OpenFolder(File folder, String path);
    void SortByName();
    void SortByDependency();

    public void PrintFilesList();

    void CheckCycles();

}
