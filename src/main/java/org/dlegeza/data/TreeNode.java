package org.dlegeza.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeNode {
	private volatile List<TreeNode> children = new ArrayList<>();

	public void addChild() {
		children.add(new TreeNode());
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
		int accum = 1;
		for (TreeNode child : this.children) {
			accum += child.treeSize();
		}
		return accum;
	}
}
