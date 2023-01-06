public class Main {
    public static void main(String[] args) {
        FileSystemR fileSystemR = new FileSystemR(null);
        fileSystemR.OpenRootFolder();
        fileSystemR.SortByName();
        fileSystemR.PrintFilesList();
        fileSystemR.FilesMerging();
    }
}