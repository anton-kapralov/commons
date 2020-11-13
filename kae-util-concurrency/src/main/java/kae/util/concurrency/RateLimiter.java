package kae.util.concurrency;

import java.util.concurrent.atomic.AtomicLong;

/** */
public class RateLimiter {

  private final long minimalInterval;

  private final AtomicLong lastRunTime = new AtomicLong();

  public RateLimiter(float rate) {
    this.minimalInterval = (long) (1000 / rate);
  }

  public void run(Runnable r) throws InterruptedException {
    while (true) {
      final long lastRunTimeLocal = lastRunTime.get();
      final long currentTimeMillis = System.currentTimeMillis();

      final long interval = currentTimeMillis - lastRunTimeLocal;

      if (interval > minimalInterval) {
        if (lastRunTime.compareAndSet(lastRunTimeLocal, currentTimeMillis)) {
          r.run();
          return;
        }
      } else {
        Thread.sleep(minimalInterval - interval);
      }
    }
  }
}
