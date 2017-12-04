package neu.edu.cs5010;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayYahtzeeTest {
  @Test(expected = IllegalArgumentException.class)
  public void main1() throws Exception {
    PlayYahtzee.main(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void main2() {
    PlayYahtzee.main(new String[]{"abc"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void main3() {
    PlayYahtzee.main(new String[]{"abc","900"});
  }

  @Test
  public void mian4(){
    PlayYahtzee.main(new String[]{"localhost","1200"});
  }
}