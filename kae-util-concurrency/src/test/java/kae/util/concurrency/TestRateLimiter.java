package kae.util.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class TestRateLimiter {

  @Test
  void testThrottling() throws Exception {
    final int rate = 10;
    final int timeout = 2;
    final int threadCount = 16;
    final int taskCount = 100;

    final RateLimiter rateLimiter = new RateLimiter(rate);

    final ExecutorService executorService = Executors.newFixedThreadPool(threadCount, new ThreadFactory() {

      private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
      private final AtomicInteger counter = new AtomicInteger();

      @Override
      public Thread newThread(Runnable r) {
        final Thread thread = defaultThreadFactory.newThread(r);
        thread.setName("TestRateLimiter-Thread-" + counter.getAndIncrement());
        return thread;
      }
    });

    final Counter counter = new Counter();

    for (int i = 0; i < taskCount; i++) {
      executorService.submit(() -> {
        try {
          rateLimiter.run(counter);
        } catch (InterruptedException e) {
          // restore interrupted status
          Thread.currentThread().interrupt();
        }
      });
    }

    TimeUnit.SECONDS.sleep(timeout);

    executorService.shutdownNow();

    assertEquals(rate * timeout, counter.value.get());
  }

  private static class Counter implements Runnable {
    private final AtomicInteger value = new AtomicInteger();

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName());
      value.incrementAndGet();
    }
  }

}
