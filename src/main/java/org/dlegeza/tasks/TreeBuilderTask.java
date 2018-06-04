package org.dlegeza.tasks;

import org.dlegeza.data.TreeNode;

public class TreeBuilderTask implements Runnable {
	private TreeNode root;

	public TreeBuilderTask setRoot(TreeNode root) {
		this.root = root;
		return this;
	}

	@Override
	public void run()  {
		this.root.getRandomNode().addChild();
	}

}
