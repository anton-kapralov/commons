package kae.util.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunnableCycle {

  @Test
  public void testStartStopWithTimeout() throws Exception {
    final int timeout = 500;
    final int tickCount = 2;
    final int sleepTime = timeout * tickCount;

    final LoopCounter loopCounter = new LoopCounter(timeout);

    loopCounter.start();
    Thread.sleep(sleepTime);

    loopCounter.stop();
    Thread.sleep(sleepTime);

    assertEquals(tickCount, loopCounter.loops.get() - 1);
  }

  private static class LoopCounter extends RunnableCycle {
    private final AtomicInteger loops = new AtomicInteger();

    LoopCounter(int timeout) {
      super(timeout);
    }

    @Override
    protected void loop() {
      System.out.println("Tick!");
      loops.incrementAndGet();
    }
  }
}
