package com.yczuoxin.myservice.lambda;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

class IsHiddenTest {

    void lambda1() {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        File[] files = new File(".").listFiles(File::isHidden);
    }

}
