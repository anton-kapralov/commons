package kae.util.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCountdownTimer {

  @Test
  void testCountdown() throws Exception {
    CountDownLatch latch = new CountDownLatch(1);

    int ticksCount = 5;
    int timeout = 200;
    CountdownTimer countdownTimer = new CountdownTimer(ticksCount * timeout, timeout);
    TickedListener tickedListener = new TickedListener();
    countdownTimer.addCountdownTickedListener(tickedListener);
    countdownTimer.addCountdownTimeOverListener(timer -> {
      System.out.println("Finished!");
      latch.countDown();
    });

    countdownTimer.start();

    latch.await();

    assertEquals(ticksCount, tickedListener.ticks.get());
  }

  private static class TickedListener implements CountdownTickedListener {

    private final AtomicInteger ticks = new AtomicInteger();

    @Override
    public void ticked(CountdownTimer timer) {
      System.out.println(timer.getTimeRemaining());
      ticks.incrementAndGet();
    }
  }
}
