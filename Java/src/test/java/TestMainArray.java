import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.java_three.lesson06.MainArray;
import ru.geekbrains.java_two.lesson2.exception.ArrayDataException;
import ru.geekbrains.java_two.lesson2.exception.ArraySizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMainArray {

    private MainArray mainArray;

    @BeforeEach
    public void init() {
        mainArray = new MainArray();
    }

    @Test
    public void testCreateNewArraySizeException() {
        int[] emptyArr = {};
        assertThrows(ArraySizeException.class, () -> {
            mainArray.createNewArray(emptyArr);
        });
    }

    @Test
    public void testCreateNewArrayDataException() {
        int[] testArr = {1, 2, 4, 4, 2, 3, 4 };
        assertThrows(ArrayDataException.class, () -> {
            mainArray.createNewArray(testArr);
        });
    }

    @Test
    public void testCreateNewArrayRuntimeException() {
        int[] testArr = {1, 2, 2, 3};
        assertThrows(RuntimeException.class, () -> {
            mainArray.createNewArray(testArr);
        });
    }

    @Test
    public void testCreateNewArray() throws ArrayDataException, ArraySizeException {
        int[] testArr = {1, 2, 4, 4, 2, 3, 4, 1, 7 };
        int[] expectedArr = {1, 7};
        int[] actualArr = mainArray.createNewArray(testArr);
        assertEquals(2, actualArr.length);
        assertArrayEquals(expectedArr, actualArr);
    }


    @ParameterizedTest
    @MethodSource("dataForTestCheckArray")
    public void testCheckArray(boolean result, int[] arr) {
        assertEquals(result, mainArray.checkArray(arr));
    }

    public static Stream<Arguments> dataForTestCheckArray() {
        List<Arguments> out = new ArrayList<>();
        int[] testArrTrue = {1, 2, 3, 4};
        out.add(Arguments.arguments(true, testArrTrue));
        int[] testArrFalse = {1, 2, 3, 1};
        out.add(Arguments.arguments(false, testArrFalse));
        testArrFalse = new int[] {4, 2, 3, 4};
        out.add(Arguments.arguments(false, testArrFalse));
        testArrFalse = new int[] {1, 1, 1, 1};
        out.add(Arguments.arguments(false, testArrFalse));
        testArrFalse = new int[] {4, 4, 4, 4};
        out.add(Arguments.arguments(false, testArrFalse));
        return out.stream();
    }

}
