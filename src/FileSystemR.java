import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс сортировщика файлов.
 */
public class FileSystemR implements FileSystem {

    ArrayList<String> paths_;
    String root_;

    /**
     * Конструктор сортировщика.
     *
     * @param root корневая папка
     */
    public FileSystemR(String root) {
        root_ = Objects.requireNonNullElse(root, "root");
        paths_ = new ArrayList<>();
    }

    /**
     * Рекурсивный алгоритм добавления пути в
     * несортированный список
     *
     * @param folder папка нахождения
     * @param path   путь к папке нахождения
     */
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

    /**
     * Метод открытия корневой папки для добавления файлов в список.
     */
    @Override
    public void OpenRootFolder() {
        System.out.println(root_);
        File folder = new File(root_);
        OpenFolder(folder, null);
    }

    /**
     * Метод склейки всех файлов по отсортированному списку путей.
     */
    @Override
    public void FilesMerging() {
        try {
            File mergedFile = new File("merged.txt");
            mergedFile.createNewFile();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }

        StringBuilder lines = new StringBuilder();

        for (String path : paths_) {
            try {
                File myObj = new File(root_ + "/" + path);
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

    /**
     * Сортировка по именам.
     */
    @Override
    public void SortByName() {
        Collections.sort(paths_);
        Collections.reverse(paths_);
    }

    /**
     * Заполнение рекурсивным алгоритмом (задача дерево зависимостей),
     * который заменяет сортировку по зависимостям.
     *
     * @param path   Ссылка на файл нахождения.
     * @param answer Список отсортированных путей к файлам.
     * @throws FileNotFoundException При чтении файла.
     */
    private void FillAnswer(String path, ArrayList<String> answer) throws FileNotFoundException {
        try {
            File myObj = new File(root_ + "/" + path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("require")) {
                    data = data.substring(data.indexOf("‘") + 1);
                    data = data.substring(0, data.indexOf("’"));
                } else {
                    continue;
                }
                FillAnswer(data, answer);
                if (answer.contains(path)) {
                    return;
                }
                answer.add(path);
            }
            if (answer.contains(path)) {
                return;
            }
            answer.add(path);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found exception!");
        }
    }


    /**
     * Метод обертка - сортировка по зависимостям.
     *
     * @throws FileNotFoundException При чтении файла.
     */
    @Override
    public void SortByDependency() throws FileNotFoundException {
        CheckAllFilesForCycles();
        ArrayList<String> answer = new ArrayList<>();
        for (String path : paths_) {
            if (answer.contains(path)) {
                continue;
            }
            FillAnswer(path, answer);
        }
        paths_ = answer;
    }

    /**
     * Вывод списка путей к файлам.
     */
    @Override
    public void PrintFilesList() {
        for (String line : paths_) {
            System.out.println(line);
        }
    }

    /**
     * Метод обертка по поиску циклов.
     *
     * @throws FileNotFoundException При чтении файла.
     */
    private void CheckAllFilesForCycles() throws FileNotFoundException {
        for (String path : paths_) {
            CheckCycles(path, new ArrayList<String>());
        }
    }

    /**
     * Рекурсивный алгоритм по поиску циклов.
     *
     * @param path  Путь к папку нахождения.
     * @param paths Пройденные пути до данного файла.
     * @throws FileNotFoundException При чтении файла.
     */
    private void CheckCycles(String path, ArrayList<String> paths) throws FileNotFoundException {
        ArrayList<String> pathsRef = new ArrayList<>(paths);
        if (paths.contains(path)) {
            System.err.println("Error: Cycle in dependencies.");
            System.err.println("Problem with files:");
            for (String p : paths) {
                System.err.println(p);
            }
            throw new IllegalArgumentException("Cycle error!");
        } else {
            try {
                File myObj = new File(root_ + "/" + path);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.startsWith("require")) {
                        data = data.substring(data.indexOf("‘") + 1);
                        data = data.substring(0, data.indexOf("’"));
                    } else {
                        continue;
                    }
                    pathsRef.add(path);
                    CheckCycles(data, pathsRef);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found exception!");
            }
        }
    }
}
