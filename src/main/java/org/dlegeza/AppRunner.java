package org.dlegeza;

import org.dlegeza.data.TreeNode;
import org.dlegeza.executors.ExecutorProvider;
import org.dlegeza.tasks.ReaderTask;
import org.dlegeza.tasks.TreeBuilderTask;

import java.util.Random;

public class AppRunner {
	private ExecutorProvider provider = new ExecutorProvider();
	private static AppRunner runner = new AppRunner();

	public static void main(String[] args) {
		TreeNode root = new TreeNode();
		int treeSizeLimit = 20000;
		int maxTimeout = treeSizeLimit / 1000;
		int currentSize = root.treeSize();
		while (currentSize < treeSizeLimit) {
			int timeout = new Random().nextInt(maxTimeout) + 1;
			int builderTimeout = new Random().nextInt(maxTimeout) + 1;
			runner.provider.submit(new TreeBuilderTask().setRoot(root), builderTimeout);
			runner.provider.submit(new ReaderTask().setRoot(root).setTimeout(timeout), timeout);
			currentSize++;
		}


		runner.provider.shutdown();
		root.print();
	}

}
