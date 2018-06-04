package org.dlegeza.tasks;

import java.util.Date;

import org.dlegeza.data.TreeNode;


public class ReaderTask implements Runnable {
	private int randomSec;
	private TreeNode root;

	public ReaderTask setTimeout(int sec) {
		this.randomSec = sec;
		System.out.println(String.format("%s - Will be sleeping for %d sec", new Date(), this.randomSec));
		return this;
	}

	public ReaderTask setRoot(TreeNode node) {
		this.root = node;
		return this;
	}

	@Override
	public void run() {
		int treeSize = this.root.treeSize();
		System.out.println(String.format("%s - sleeping for %d sec resumed - Tree size: %d", new Date(), this.randomSec, treeSize));
	}

}
