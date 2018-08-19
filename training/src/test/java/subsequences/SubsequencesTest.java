package subsequences;

import org.junit.*;
import java.util.*;

public class SubsequencesTest {

    private Subsequence subsequence = new Subsequence();

    @Test
    public void emptyXList(){
        List x = Collections.emptyList();
        List y = Arrays.asList(1, 2, 3);
        Assert.assertTrue(subsequence.find(x, y));
    }

    @Test
    public void emptyYList(){
        List x = Arrays.asList(1, 3, 5);
        List y = Collections.emptyList();
        Assert.assertFalse(subsequence.find(x, y));
    }

    @Test
    public void emptyBothLists(){
        List x = Collections.emptyList();
        List y = Collections.emptyList();
        Assert.assertTrue(subsequence.find(x, y));
    }

    @Test
    public void yListContainsXListWithNulls(){
        List x = Arrays.asList(1, 3, 5, null);
        List y = Arrays.asList(8, 1, 2, 3, 8, 5, null);
        Assert.assertTrue(subsequence.find(x, y));
    }

    @Test
    public void xListLargerThanYList(){
        List x = Arrays.asList(1, 3, 5, null, 8, 9, 10);
        List y = Arrays.asList(8, 1, 2, 3, 8, 5, null, 7);
        Assert.assertFalse(subsequence.find(x, y));
    }

    @Test
    public void yListContainsXList(){
        List x = Arrays.asList("S", "L");
        List y = Arrays.asList(null, "c", "S", "n", "L", "b");
        Assert.assertTrue(subsequence.find(x, y));
    }

    @Test
    public void yListNotContainsXList(){
        List x = Arrays.asList(1, 3, 5, "L");
        List y = Arrays.asList(8, 1, 2, 3, 8, 5, null, 7);
        Assert.assertFalse(subsequence.find(x, y));
    }
}
