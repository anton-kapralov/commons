package kae.util.concurrency;

import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStopwatch {

  @Test
  void testStartStop() throws Exception {
    final int timeout = 3000;
    Stopwatch stopwatch = new Stopwatch(500);
    stopwatch.addListener(new TestStopwatchListener());

    stopwatch.start();
    Thread.sleep(timeout);

    stopwatch.stop();
    assertTrue(stopwatch.getElapsed() - timeout < 10);

    Thread.sleep(timeout);
    stopwatch.start();
    Thread.sleep(timeout);

    stopwatch.stop();
    assertTrue(stopwatch.getElapsed() - timeout * 2 < 10);

    stopwatch.reset();
    stopwatch.start();
    Thread.sleep(timeout);

    stopwatch.stop();
    Thread.sleep(1000);

    assertTrue(stopwatch.getElapsed() - timeout < 10);
  }

  private static class TestStopwatchListener implements StopwatchListener {
    @Override
    public void started() {
      System.out.println("Started!");
    }

    @Override
    public void stopped() {
      System.out.println("Stopped!");
    }

    @Override
    public void reset() {
      System.out.println("Reset!");
    }

    @Override
    public void ticked(long time) {
      System.out.println(MILLISECONDS.toSeconds(time));
    }
  }
}
