package org.dlegeza.tasks;

import org.dlegeza.data.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReaderTask implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(TreeNode.class);
	private int randomSec;
	private TreeNode root;

	public ReaderTask setTimeout(int sec) {
		this.randomSec = sec;
		logger.info("Will be sleeping for {} sec", this.randomSec);
		return this;
	}

	public ReaderTask setRoot(TreeNode node) {
		this.root = node;
		return this;
	}

	@Override
	public void run() {
		int treeSize = this.root.treeSize();
		logger.info("sleeping for {} sec resumed - Tree size: {}", this.randomSec, treeSize);
	}

}
