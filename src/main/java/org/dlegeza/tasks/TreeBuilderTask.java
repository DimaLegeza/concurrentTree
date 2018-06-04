package org.dlegeza.tasks;

import java.util.Date;

import org.dlegeza.data.TreeNode;

public class TreeBuilderTask implements Runnable {
	private TreeNode root;

	public TreeBuilderTask setRoot(TreeNode root) {
		this.root = root;
		return this;
	}

	@Override
	public void run()  {
		System.out.println(new Date() + " - Calling tree expansion...");
		this.root.getRandomNode().addChild();
	}

}
