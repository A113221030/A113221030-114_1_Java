import java.io.File;

public class RecursiveListDemo {
    public static void main(String[] args) {
        File root = new File(".");
        listAllFiles(root, 0);
    }
    
    /**
     * 遞迴列出目錄中的所有檔案和子目錄
     * @param dir   要列出的目錄
     * @param level 目前的縮排層級
     */
    public static void listAllFiles(File dir, int level) {
        // 根據層級產生縮排字串
        String indent = "  ".repeat(level);
        
        File[] files = dir.listFiles();
        if (files == null) return;
        
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(indent + "📁 " + file.getName() + "/");
                // 遞迴處理子目錄
                listAllFiles(file, level + 1);
            } else {
                System.out.println(indent + "📄 " + file.getName());
            }
        }
    }
}
