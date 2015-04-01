package strategy;

/**
 * Created by camro.
 */
public interface IGhost {

    /**
     * Start the timer
     */
    public void start();

    /**
     * Stop the timer
     */
    public void stop();

    /**
     *The action to do
     */
    public void doTick();
}
