package debug;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RemoveCommentsTest {

    @Test
    public void removeComments1() {
        //Normal
        RemoveComments removeComments = new RemoveComments();
        String[] strings = {"/*//*//*/","llon"};
        String[] ans = {"llon"};
        List<String> out = removeComments.removeComments(strings);
        assertEquals(out, Arrays.asList(ans));
    }

    @Test
    public void removeComments2() {
        //Empty
        RemoveComments removeComments = new RemoveComments();
        String[] strings = {"////abcd","/*//*/","//Integer"};
        String[] ans = {};
        assertEquals(removeComments.removeComments(strings), Arrays.asList(ans));
    }
}