package com.naosim.labelanno.load.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * クラスファイルを読み込むユーティリティ
 */
public class ClassLoaderService extends ClassLoader {
    private static final int BUF_SIZE = 1024;

    public static List<Class> findAllClasses(String rootDir) {
        ClassLoaderService service = new ClassLoaderService();
        return getAllFilePathInDirectory(rootDir)
                .stream()
                .map(Path::toFile)
                .map(service::getClassFromFilePath)
                .collect(Collectors.toList());
    }

    public static List<Path> getAllFilePathInDirectory(String rootDir) {
        try (Stream<Path> stream = Files.walk(Paths.get(rootDir))) {
            return stream
                    .filter(v -> v.toFile().getName().contains(".class"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Class getClassFromFilePath(File targetFile) {
        try {
            FileInputStream in = new FileInputStream(targetFile);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[BUF_SIZE];
            int len = in.read(buf);
            while (len != -1) {
                out.write(buf, 0, len);
                len = in.read(buf);
            }
            byte[] loadedData = out.toByteArray();
            Class loadedCLass = defineClass(null, loadedData, 0, loadedData.length);
            return loadedCLass;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
