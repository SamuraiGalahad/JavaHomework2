public class Main {
    public static void main(String[] args) {
        try {
            Visual.FileSystemRun();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}