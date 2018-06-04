package org.dlegeza.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TreeNode {
	private final Logger logger = LoggerFactory.getLogger(TreeNode.class);
	private List<TreeNode> children = new ArrayList<>();
	private TreeNode lockObject = this;

	public void addChild() {
		synchronized (this.lockObject) {
			this.setLock(this);
			children.add(new TreeNode());
			logger.warn("Tree expanded...");
		}
	}

	public void setLock(TreeNode lock) {
		this.lockObject = lock;
		for (TreeNode childNode: this.children) {
			childNode.setLock(this.lockObject);
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
		synchronized (this.lockObject) {
			int acc = 1;
			for (TreeNode child : this.children) {
				acc += child.treeSize();
			}
			return acc;
		}
	}

	public void print() {
        System.out.print(this.children
                .stream()
                .map(child -> String.format("o(%d)", child.children.size()))
                .collect(Collectors.joining()));
        this.children.forEach(child -> child.print());
        System.out.println();
    }
}
