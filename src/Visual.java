import java.io.File;

import java.util.Objects;
import java.util.Scanner;

public class Visual {
    /**
     * Метод работы программы с файловым сортировщиком.
     */
    public static void FileSystemRun() {
        String rootPath = GetPathFromUser();
        String mode = GetModeFromUser();
        try {
            FileSystemR fileSystemR = new FileSystemR(rootPath);
            fileSystemR.OpenRootFolder();
            if (mode.equals("1")) {
                fileSystemR.SortByName();
            } else {
                fileSystemR.SortByDependency();
            }
            fileSystemR.FilesMerging();
            PrintResult(fileSystemR);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Метод запросаа строки пути у пользователя.
     *
     * @return Строка пути.
     */
    public static String GetPathFromUser() {
        while (true) {
            System.out.println("Write path to root folder: ");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();
            File obj = new File(path);
            if (!obj.exists()) {
                System.err.println("Wrong file path!");
                continue;
            }
            return path;
        }
    }

    /**
     * Метод запроса строки типа сортировки от пользователя.
     *
     * @return Строка метода сортировки.
     */
    public static String GetModeFromUser() {
        while (true) {
            System.out.println("Write 1 to sort by names.");
            System.out.println("Write 2 to sort by dependencies.");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String mode = scanner.nextLine();
            if (!Objects.equals(mode, "1") && !Objects.equals(mode, "2")) {
                System.err.println("Wrong mode!");
                continue;
            }
            return mode;
        }
    }

    /**
     * Метод вывода результата работы программы.
     *
     * @param obj Сортировщик файлов, его объект.
     */
    public static void PrintResult(FileSystemR obj) {
        System.out.println("Sorting result: ");
        obj.PrintFilesList();
        System.out.println("---------------------------");
        System.out.println("Merged text in merged.txt file in project folder.");
    }
}
