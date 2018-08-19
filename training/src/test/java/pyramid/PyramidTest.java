package pyramid;

import org.junit.*;
import java.util.*;

public class PyramidTest {

    private Pyramid pyramid = new Pyramid();

    @Test(expected = CannotBuildPyramidException.class)
    public void nullList() throws CannotBuildPyramidException {
        pyramid.buildPyramid(null);
    }

    @Test(expected = CannotBuildPyramidException.class)
    public void wrongArgumentsNumberList() throws CannotBuildPyramidException {
        pyramid.buildPyramid(new ArrayList<>(Arrays.asList(5, 4, 7, 1, 9)));
    }

    @Test(expected = CannotBuildPyramidException.class)
    public void containingNullsList() throws CannotBuildPyramidException {
        pyramid.buildPyramid(new ArrayList<>(Arrays.asList(5, 4, null, 7, 1, 9)));
    }

    @Test
    public void correctList() throws CannotBuildPyramidException {
        int actuals[][] = pyramid.buildPyramid(new ArrayList<>(Arrays.asList(3,6,8,0,-1, 9)));
        int expecteds[][] = {{0, 0, -1, 0, 0}, {0, 0, 0, 3, 0}, {6, 0, 8, 0, 9}};

        for (int i = 0; i < actuals.length; i++) {
            Assert.assertArrayEquals(expecteds[i], actuals[i]);
        }
    }
}
