package org.dlegeza.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorProvider {
	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

	public void submit(Runnable task, int delay) {
		executor.schedule(task, delay, TimeUnit.SECONDS);
	}

	public void shutdown() {
		this.executor.shutdown();
		try {
			if (!this.executor.awaitTermination(60, TimeUnit.SECONDS)) {
				this.executor.shutdownNow();
			}
		} catch (InterruptedException ex) {
			this.executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
