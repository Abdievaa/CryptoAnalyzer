import java.nio.file.Path;

public class PathHelper {

    public static Path buildFullName(String path, String suffix) {
        Path pathPrimary = Path.of(path);
        Path parent = pathPrimary.getParent();
        String fileName = pathPrimary.getFileName().toString();
        String newFileName;
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0, index) + suffix + fileName.substring(index);
        } else {
            newFileName = fileName + suffix;
        }
        return parent.resolve(newFileName);
    }
}
