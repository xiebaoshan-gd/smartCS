package com.example.demo.vo;

import java.util.List;

import lombok.Data;

@Data
public class TreeNode {
	private Integer id; // 节点ID
	private String text; // 节点内容
	private String url; // 节点的url地址
	private String remark; // 节点的描述信息
	private Integer pid; // 父节点ID
	private String state; // open代表展开，closed代表关闭
	private Boolean checked; // true代表选中，false代表未选中
	private List<TreeNode> children; // 子节点
}
