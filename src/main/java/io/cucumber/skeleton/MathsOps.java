package io.cucumber.skeleton;

import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileReader;

public class MathsOps {
    int divider(int a, int b) {
        return a / b;
    }

    void readFile(String path) throws FileNotFoundException {
        File f = new File(path);
        try {
            FileReader reader = new FileReader(f);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    boolean isOdd(int n) {
        return n % 2 != 0;
    }

    void longOperation() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Slept for 3 seconds");
    }
}

