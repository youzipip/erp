package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * json对象转化器
 * 简单json--->标准json
 *
 */
public class TreeNodeBuilder {

	/**
	 * 
	 * @param nodes
	 * @param topId  最顶层的节点pid
	 * @return
	 */
	public static List<TreeNode> build(List<TreeNode> nodes, Integer topId) {
		List<TreeNode> treeNodes = new ArrayList<>();
		for (TreeNode n1 : nodes) {
			//找到顶层节点
			if(n1.getPid()==topId){
				treeNodes.add(n1);
			}
			for (TreeNode n2 : nodes) {
				//如果外层节点id=内层节点pid，内层节点为外节点的子节点
				if(n2.getPid()==n1.getId()){
					n1.getChildren().add(n2);
				}
			}
		}
		return treeNodes;
	}

	
}
