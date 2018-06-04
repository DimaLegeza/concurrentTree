package org.dlegeza.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeNode {
	private final Logger logger = LoggerFactory.getLogger(TreeNode.class);
	private List<TreeNode> children = new ArrayList<>();

	public void addChild() {
		synchronized (TreeNode.class) {
			logger.warn("Tree expanded...");
			children.add(new TreeNode());
		}
	}

	public TreeNode getRandomNode() {
		int childrenSize = this.children.size();
		int getDeeper = new Random().nextInt(1);
		if (childrenSize != 0 && getDeeper == 0) {
			int randomChildNode = new Random().nextInt(childrenSize);
			return this.children.get(randomChildNode).getRandomNode();
		} else {
			return this;
		}
	}

	public int treeSize() {
		synchronized (TreeNode.class) {
			int accum = 1;
			for (TreeNode child : this.children) {
				accum += child.treeSize();
			}
			return accum;
		}
	}
}
