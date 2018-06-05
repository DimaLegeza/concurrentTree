package org.dlegeza.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeNode {
	private final Logger logger = LoggerFactory.getLogger(TreeNode.class);
	private List<TreeNode> children = new ArrayList<>();
	private volatile TreeNode lockObject = this;

	public void addChild() {
		synchronized (this.lockObject) {
			this.setLock(this);
			children.add(new TreeNode());
			logger.warn("Tree expanded...");
		}
	}

	private void setLock(TreeNode lock) {
		this.lockObject = lock;
		for (TreeNode childNode: this.children) {
			childNode.setLock(this.lockObject);
		}
	}

	public TreeNode getRandomNode() {
		int childrenSize = this.children.size();
		int getDeeper = new Random().nextInt(2);
		if (childrenSize != 0 && getDeeper == 1) {
			int randomChildNode = new Random().nextInt(childrenSize);
			return this.children.get(randomChildNode).getRandomNode();
		} else {
			return this;
		}
	}

	public int treeSize() {
		synchronized (this.lockObject) {
			int acc = 1;
			for (TreeNode child : this.children) {
				acc += child.treeSize();
			}
			return acc;
		}
	}

	@Override
	public String toString() {
		return this.tree(0);
	}

	private String tree(int initialDepth) {
		StringBuilder builder = new StringBuilder();
		builder.append("(").append(this.children.size()).append(")").append(System.lineSeparator());
		String offset = IntStream.range(0, initialDepth).mapToObj(i -> " ").collect(Collectors.joining());
		for (final TreeNode child: this.children) {
			builder.append(offset).append(child.children.size() > 0 ? "+-": "|-");
			builder.append(child.tree(initialDepth + 1));
		}
		return builder.toString();
    }
}
