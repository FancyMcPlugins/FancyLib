package de.oliver.fancylib.serverSoftware.schedulers;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface FancyScheduler {

    /**
     * Run the task.
     *
     * @param task task...
     * @param location required for Folia, in Bukkit can be null
     * @return The created {@link FancyScheduler}.
     */
    @NotNull FancyScheduler runTask(Location location, Runnable task);

    /**
     * Run the task asynchronously.
     *
     * @param task task...
     * @return The created {@link FancyScheduler}
     */
    @NotNull FancyScheduler runTaskAsynchronously(Runnable task);

    /**
     * Run the task after a specified number of ticks.
     *
     * @param location required for Folia, in Bukkit can be null
     * @param task task...
     * @param delay The number of ticks to wait.
     * @return The created {@link FancyScheduler}
     */
    @NotNull FancyScheduler runTaskLater(Location location, long delay, Runnable task);

    /**
     * Run the task asynchronously after a specified number of seconds.
     *
     * @param task task...
     * @param delay The number of seconds to wait.
     * @return The created {@link FancyScheduler}
     */
    @NotNull FancyScheduler runTaskLaterAsynchronously(long delay, Runnable task);

    /**
     * Run the task repeatedly on a timer.
     *
     * @param location required for Folia, in Bukkit can be null
     * @param task task...
     * @param delay  The delay before the task is first run (in ticks).
     * @param period The ticks elapsed before the task is run again.
     * @return The created {@link FancyScheduler}
     */
    @NotNull FancyScheduler runTaskTimer(Location location, long delay, long period, Runnable task);

    /**
     * Run the task repeatedly on a timer asynchronously.
     *
     * @param task task...
     * @param delay  The delay before the task is first run (in seconds).
     * @param period The seconds elapsed before the task is run again.
     * @return The created {@link FancyScheduler}
     */
    @NotNull FancyScheduler runTaskTimerAsynchronously(long delay, long period, Runnable task);

    /**
     * Cancel the task.
     */
    void cancel();
}