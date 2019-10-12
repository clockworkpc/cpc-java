package io.cucumber.skeleton;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class MathsOpsTest {

    static int repetitions;

    @BeforeAll
    public static void init() {
        repetitions = 0;
    }


    @Nested
    @DisplayName("Nested Test Cases Class A")
    class A {
        @Tag("unit-test")
        @DisplayName("Check for File Not Found Exception")
        @Test
        public void testReadFile() throws FileNotFoundException {
            MathsOps mathsOps = new MathsOps();
            String localDir = System.getProperty("user.dir");
            String filePath = localDir + "/src/test/resources/fixtures/helloWorld.txt";
            mathsOps.readFile(filePath);
        }

        @Tag("integration-test")
        @DisplayName("Assertions with Lambda")
        @Test
        public void testWithLambda() {
            char[] vowels = {'a', 'e', 'i', 'o', 'u'};
            assertAll("vowels",
                    () -> assertEquals(vowels[0], 'a'),
                    () -> assertEquals(vowels[1], 'e'),
                    () -> assertEquals(vowels[2], 'i'),
                    () -> assertEquals(vowels[3], 'o'),
                    () -> assertEquals(vowels[4], 'u')
            );
        }

        @Tag("unit-test")
        @DisplayName("Check for File Not Found Using Assume (Assumption True)")
        @Test
        public void testReadFileIfExistsTrue() throws FileNotFoundException {
            String localDir = System.getProperty("user.dir");
            String filePath = localDir + "/src/test/resources/fixtures/helloWorld.txt";
            File f = new File(filePath);
            assumeTrue(f.exists());
            assumingThat((1 + 1 == 2), () -> assertEquals(1, 1, "Both are not equal"));
            assertEquals(1, 1, "Both are equal");
            MathsOps mathsOps = new MathsOps();
        }
    }

    @Nested
    @DisplayName("Nested Test Cases Class B")
    class B {
        @Tag("unit-test")
        @DisplayName("Check for File Not Found Exception")
        @Test
        public void testReadFile() throws FileNotFoundException {
            MathsOps mathsOps = new MathsOps();
            String localDir = System.getProperty("user.dir");
            String filePath = localDir + "/src/test/resources/fixtures/helloWorld.txt";
            File f = new File(filePath);
            mathsOps.readFile(filePath);
        }

        @Tag("integration-test")
        @DisplayName("Assertions with Lambda")
        @Test
        public void testWithLambda() {
            char[] vowels = {'a', 'e', 'i', 'o', 'u'};
            assertAll("vowels",
                    () -> assertEquals(vowels[0], 'a'),
                    () -> assertEquals(vowels[1], 'e'),
                    () -> assertEquals(vowels[2], 'i'),
                    () -> assertEquals(vowels[3], 'o'),
                    () -> assertEquals(vowels[4], 'u')
            );
        }
    }


    @Tag("unit-test")
    @DisplayName("Check for File Not Found Using Assume (Assumption True)")
    @Test
    public void testReadFileIfExistsTrue() throws FileNotFoundException {
        String localDir = System.getProperty("user.dir");
        String filePath = localDir + "/src/test/resources/fixtures/helloWorld.txt";
        File f = new File(filePath);
        assumeTrue(f.exists());
        assumingThat((1 + 1 == 2), () -> assertEquals(1, 1, "Both are not equal"));
        assertEquals(1, 1, "Both are equal");

        MathsOps mathsOps = new MathsOps();
    }

    @DisplayName("Check for File Not Found Using Assume (Assumption Fails)")
    @Test
    public void testReadFileIfExistsFalse() throws FileNotFoundException {
        File f = new File("demo1.txt");
        assumeTrue(f.exists());
        assertEquals(1, 2, "Elements are not equal");
    }

    @Tag("unit-test")
    @DisplayName("Check for Arithmetic Exception")
    @Test
    public void testDivider() {
        MathsOps mathops = new MathsOps();
        assertThrows(ArithmeticException.class, () -> mathops.divider(10, 0));
    }

    @DisplayName("Check for FileNotFoundException")
    @Test
    public void testReadFileWithException() {
        MathsOps mathsOps = new MathsOps();
        assertThrows(FileNotFoundException.class, () -> {
            mathsOps.readFile("1.txt");
        });
    }

    @DisplayName("Time out an overly long operation")
    @Test
    public void testLongOperation() {
        MathsOps mathsOps = new MathsOps();
        assertTimeout(Duration.ofMillis(4000), () -> mathsOps.longOperation());
    }

    @DisplayName("Check for Arithmetic Exception")
    @RepeatedTest(3)
    public void testDividerRepeated(RepetitionInfo repetitionInfo) {
        MathsOps mathops = new MathsOps();
        assertThrows(ArithmeticException.class, () -> mathops.divider(10, 0));
        repetitions++;
        assertEquals(repetitions, repetitionInfo.getCurrentRepetition());
        assertEquals(3, repetitionInfo.getTotalRepetitions());
    }

//    @DisplayName("Parameterized Test")
//    @ParameterizedTest
//    @ValueSource(strings = {"hello", "world"})
//    void testWithParameters(String string) {
//        assertEquals("hello " + string, "hello world");
//    }

    @DisplayName("Excluded Test Case")
    @Disabled
    @Test
    void disabledTest() {
        assertEquals((2 + 2), (1 + 3));
    }

}

