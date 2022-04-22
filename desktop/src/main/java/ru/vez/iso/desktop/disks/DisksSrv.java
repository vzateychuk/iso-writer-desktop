package ru.vez.iso.desktop.disks;

import java.util.List;

public interface DisksSrv {

    /**
     * Загрузить ISO файл в локальный файловый кэш
     * */
    void readIsoFileNamesAsync(String dir);

    /**
     * iterates over a directory and outputs all of the fi les that end with a *.iso extension
     * */
    List<String> readIsoFileNames(String dir);
}
