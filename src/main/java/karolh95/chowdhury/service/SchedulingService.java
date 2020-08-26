package karolh95.chowdhury.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

	private final static int CORE_POOL_SIZE = 1;
	private final static int INITIAL_DELAY = 0;
	private final static int PERIOD = 1;

	private final Runnable action;

	private ScheduledThreadPoolExecutor executor;
	private ScheduledFuture<?> future;

	SchedulingService(Runnable runnable) {

		this.action = runnable;
		executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(CORE_POOL_SIZE);

	}

	public void runScheduledTask() {

		if (future == null) {
			future = schedule();
		}
	}

	public void cancelScheduledTask() {

		if (future != null) {
			future.cancel(true);
			future = null;
		}
	}

	private ScheduledFuture<?> schedule() {

		return executor.scheduleAtFixedRate(action, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);
	}
}
