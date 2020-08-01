package rssreader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RssReaderTest {
    @Test
    public void test() {
        RssReader rssReader = new RssReader();
        assertThat(rssReader.greet(), is("Hello world."));
    }
}
