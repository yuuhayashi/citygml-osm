package haya4.tools.files.compless;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

public class UnZip {

	/**
     * *.tar.gz解凍
     * ファイル更新日時をオリジナルと同じにします。
     * @param tazFile 解凍する*.tar.gzファイル
     * @param dest 解凍先フォルダ
     * @throws IOException 
     */
    public static void uncompress(Path tazFile, Path dest) throws IOException {
        Files.createDirectories(dest);
        try (TarArchiveInputStream tarIn = new TarArchiveInputStream(new GzipCompressorInputStream(new BufferedInputStream(Files.newInputStream(tazFile))))) {
            TarArchiveEntry tarEntry = tarIn.getNextTarEntry();
            while (tarEntry != null) {
                Path destPath = Paths.get(dest.toString(), tarEntry.getName());
                if (tarEntry.isDirectory()) {
                	Files.createDirectories(destPath);
                }
                else {
                	Path dir = destPath.getParent();
                    if (!Files.exists(dir)) {
                    	Files.createDirectories(dir);
                    }
                    if (!Files.exists(destPath)) {
                        Files.createFile(destPath);
                    }
                    byte[] btoRead = new byte[1024];
                    try (BufferedOutputStream bout = new BufferedOutputStream(Files.newOutputStream(destPath))) {
                        int len;
                        while ((len = tarIn.read(btoRead)) != -1) {
                            bout.write(btoRead, 0, len);
                        }
                    }
                    Files.setLastModifiedTime(destPath, FileTime.fromMillis(tarEntry.getLastModifiedDate().getTime()));
                }
                tarEntry = tarIn.getNextTarEntry();
            }
        }
    }

    /**
     * Zipファイルを展開します
     * @param aZipFile zipファイル
     * @param aOutDir  出力先ディレクトリ
     * @throws java.io.IOException
     */
    public static void decode(Path aZipFile, String aOutDir) throws IOException {
        Path outDir = Paths.get(aOutDir);
        Files.createDirectories(outDir);
        
    	try (ZipInputStream zipIn = new ZipInputStream(Files.newInputStream(aZipFile))) {
    		
            ZipEntry entry = null;
            while ((entry = zipIn.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    String relativePath = entry.getName();
                    outDir = Paths.get(outDir.toString(), relativePath);
                    Files.createDirectories(outDir);
                }
                else {
                    String relativePath = entry.getName();
                    Path outFile = Paths.get(outDir.toString(), relativePath);
                    
                    Path parentFile = outFile.getParent();
                    Files.createDirectories(parentFile);
                    
                    try (OutputStream fileOut = Files.newOutputStream(outFile)) {
                        byte[] buf = new byte[ 256 ];
                        int size = 0;
                        while ((size = zipIn.read(buf)) > 0){
                            fileOut.write(buf, 0, size);
                        }
                    }
                }
                zipIn.closeEntry();
            }
    	}
    }
    

    /**
     * 空でないディレクトリを削除
     * @param file
     * @throws IOException
     */
    public static boolean delete(Path file) throws IOException {
        if (Files.exists(file)) {
            if (Files.isDirectory(file)) {
            	List<Path> files = Files.list(file).collect(Collectors.toList());
                for (Path file1 : files) {
                    if (!delete(file1))
                    	return false; // 再帰呼び出し
                }
            }
            Files.delete(file);
        }
        return true;
    }
}
