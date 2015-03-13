package kae.util;

/**
 * MorseCodeAbstractPlayer
 *
 * @author Kapralov A.
 *         10.12.2014 17:44
 */
public abstract class MorseCodeAbstractPlayer {

  // Нечетный индекс - точка
  // Четный индекс - тире
  protected final long[] pattern;
  private final int cyclesCount;

  private final Object gapper = new Object();
  private volatile Thread thread;
  private volatile boolean playOn = false;

  /**
   *
   * @param pattern массив с шаблоном кода Морзе: нечетный индекс - точка, четный - тире.
   * @param cyclesCount количество повторов, если передать 0, то будет повторяться, пока не вызвать метод stop().
   */
  public MorseCodeAbstractPlayer(long[] pattern, int cyclesCount) {
    this.pattern = pattern;
    this.cyclesCount = cyclesCount;
  }

  public synchronized void play() {
    if (thread != null) {
      return;
    }

    playOn = true;

    thread = new Thread(new Runnable() {
      @Override
      public void run() {
        playPattern();
      }
    });

    thread.start();
  }

  public synchronized void stop() {
    if (thread == null) {
      return;
    }

    playOn = false;

    synchronized (gapper) {
      gapper.notify();
    }

    thread = null;
  }

  private void playPattern() {
    int cycleCounter = 0;
    while (playOn) {
      for (int i = 0; i < pattern.length; i++) {
        if (!playOn) {
          return;
        }

        dotOrDash(i % 2 != 0);
        long gap = pattern[i];
        if (gap > 0) {
          synchronized (gapper) {
            try {
              gapper.wait(gap);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }

      if (cyclesCount == 0) {
        continue;
      }

      if (++cycleCounter >= cyclesCount) {
        break;
      }
    }

    playOn = false;
  }

  protected abstract void dotOrDash(boolean dot);

  public boolean isPlaying() {
    return playOn;
  }

}
