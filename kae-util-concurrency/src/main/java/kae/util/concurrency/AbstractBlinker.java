package kae.util.concurrency;

import net.jcip.annotations.GuardedBy;

/**
 * AbstractBlinker
 *
 * @author Kapralov A.
 * 10.12.2014 17:44
 */
public abstract class AbstractBlinker {

  private final long[] pattern;
  private final int cyclesCount;

  @GuardedBy("this")
  private Thread thread;

  /**
   * @param pattern     an array with a pattern where odd indices "eyes open" and on even - "eyes closed".
   * @param cyclesCount count of cycles. If it equals {@code 0} then goes infinitely until {@code stop()} is called.
   */
  public AbstractBlinker(long[] pattern, int cyclesCount) {
    this.pattern = pattern;
    this.cyclesCount = cyclesCount;
  }

  public synchronized boolean isActive() {
    return thread != null && !thread.isInterrupted();
  }

  public synchronized void start() {
    if (isActive()) {
      return;
    }

    thread = new Thread(this::playPattern);
    thread.start();
  }

  public synchronized void stop() {
    if (!isActive()) {
      return;
    }

    thread.interrupt();
    thread = null;
  }

  private void playPattern() {
    int cycleCounter = 0;
    Thread currentThread = Thread.currentThread();
    do {
      for (int i = 0; i < pattern.length; i++) {
        if (currentThread.isInterrupted()) {
          return;
        }

        blink(i % 2 != 0);

        long gap = pattern[i];
        if (gap > 0) {
          try {
            Thread.sleep(gap);
          } catch (InterruptedException e) {
            // restore interrupted status
            currentThread.interrupt();
          }
        }
      }
    } while (!currentThread.isInterrupted() && (cyclesCount == 0 || ++cycleCounter < cyclesCount));
  }

  protected abstract void blink(boolean opened);

}
