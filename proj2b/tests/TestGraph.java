import org.junit.jupiter.api.Test;
import main.MyGraph;
import java.util.ArrayList;
import java.util.Arrays;
import static com.google.common.truth.Truth.assertThat;

public class TestGraph {
    @Test
    public void testGraphAdd() {
        MyGraph graph = new MyGraph();
        graph.add(0, new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9)));
        graph.add(1, new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10)));
        graph.printAll();
    }
}
