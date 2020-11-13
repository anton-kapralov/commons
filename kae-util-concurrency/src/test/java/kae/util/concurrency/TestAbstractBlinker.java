package kae.util.concurrency;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAbstractBlinker {

  @Test
  public void testStartStopWithTimeout() throws Exception {
    long[] pattern = {500, 500, 500, 1000, 1000, 1000};
    int loops = 2;
    final int tickCount = pattern.length * loops;
    final long sleepTime = Arrays.stream(pattern).sum() * loops;

    final BlinkCounter blinkCounter = new BlinkCounter(pattern, 0);

    blinkCounter.start();
    Thread.sleep(sleepTime);

    blinkCounter.stop();
    Thread.sleep(sleepTime);

    assertEquals(tickCount, blinkCounter.blinks.get());
  }

  private static class BlinkCounter extends AbstractBlinker {
    private final AtomicInteger blinks = new AtomicInteger();

    BlinkCounter(long[] pattern, int timeout) {
      super(pattern, timeout);
    }

    @Override
    protected void blink(boolean opened) {
      System.out.println(opened ? "On" : "Off");
      assertEquals(blinks.incrementAndGet() % 2 == 0, opened);
    }
  }
}
