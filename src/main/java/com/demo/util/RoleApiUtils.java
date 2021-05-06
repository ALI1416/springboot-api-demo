package com.demo.util;

import com.demo.entity.po.RoleApiTree;
import com.demo.entity.vo.RoleApiTreeVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>api权限工具</h1>
 *
 * <p>
 * createDate 2021/01/28 16:16:04
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RoleApiUtils {

    /**
     * api权限路径map
     */
    private static Map<Long, List<String>> roleApiPath = new HashMap<>();

    public static void main(String[] args) {
        List<RoleApiTreeVo> roleApiTreeList = new ArrayList<>();
        roleApiTreeList.add(new RoleApiTreeVo(0L, 0L, "*"));
        roleApiTreeList.add(new RoleApiTreeVo(1L, 0L, "/api"));
        roleApiTreeList.add(new RoleApiTreeVo(11L, 1L, "a"));
        roleApiTreeList.add(new RoleApiTreeVo(12L, 1L, "b"));
        roleApiTreeList.add(new RoleApiTreeVo(13L, 1L, "c"));
        roleApiTreeList.add(new RoleApiTreeVo(111L, 11L, "aa"));
        roleApiTreeList.add(new RoleApiTreeVo(112L, 11L, "ab"));
        roleApiTreeList.add(new RoleApiTreeVo(113L, 11L, "ac"));
        roleApiTreeList.add(new RoleApiTreeVo(121L, 12L, "ba"));
        roleApiTreeList.add(new RoleApiTreeVo(122L, 12L, "bb"));
        roleApiTreeList.add(new RoleApiTreeVo(131L, 13L, "ca"));
        roleApiTreeList.add(new RoleApiTreeVo(1111L, 111L, "aaa"));
        roleApiTreeList.add(new RoleApiTreeVo(1112L, 111L, "aab"));
        roleApiTreeList.add(new RoleApiTreeVo(1131L, 113L, "aca"));
        roleApiTreeList.add(new RoleApiTreeVo(1311L, 131L, "caa"));

        // 按parentId分组
        Map<Long, List<RoleApiTreeVo>> map = roleApiTreeList.stream()
                .collect(Collectors.groupingBy(RoleApiTreeVo::getParentId));

        System.out.println(roleApiTreeList);
        RoleApiTreeVo tree = list2Tree(map, roleApiTreeList, 1);
        System.out.println(tree);
        System.out.println(roleApiTreeList);
//        List<RoleApiTreeVo> expandedList = tree2ExpandedList(tree);
//        System.out.println(expandedList);
//
//        RoleApiTreeVo tree2 = list2Tree(roleApiTreeList, 122);
//        System.out.println(tree2);
//
//        List<RoleApiTreeVo> expandedList1 = tree2List(tree2);
//        System.out.println(expandedList1);
//
//        List<RoleApiVo> roleApiList = new ArrayList<>();
//        roleApiList.add(new RoleApiVo(0L, ""));
//        roleApiList.add(new RoleApiVo(1L, "all"));
//        roleApiList.add(new RoleApiVo(2L, "a"));
//        roleApiList.add(new RoleApiVo(3L, "b+ca"));
//        List<RoleApiRefVo> roleApiRefList = new ArrayList<>();
//        roleApiRefList.add(new RoleApiRefVo(0L, 0L, 0L));
//        roleApiRefList.add(new RoleApiRefVo(1L, 1L, 1L));
//        roleApiRefList.add(new RoleApiRefVo(2L, 2L, 11L));
//        roleApiRefList.add(new RoleApiRefVo(3L, 3L, 12L));
//        roleApiRefList.add(new RoleApiRefVo(4L, 3L, 131L));

    }

    /**
     * 列表转树
     *
     * @param list 列表
     * @param id   从哪个id开始转换
     */
    public static RoleApiTreeVo list2Tree(Map<Long, List<RoleApiTreeVo>> map, List<RoleApiTreeVo> list, long id) {
        // 找到对应id的节点
        RoleApiTreeVo tree = list.stream().filter(s -> s.getId() == id).findFirst().get();
        // 生成树
        makeTree(tree, map);
        return tree;
    }

    /**
     * 生成树
     *
     * @param tree 接收的树
     * @param map  按parentId分组并且去除根节点的map
     */
    public static void makeTree(RoleApiTreeVo tree, Map<Long, List<RoleApiTreeVo>> map) {
        // 找到子节点
        List<RoleApiTreeVo> childs = map.get(tree.getId());
        if (childs != null) {
            // 对子节点进行排序
            if (childs.size() != 1) {
                List<RoleApiTreeVo> childsOrder = childs.stream().sorted(Comparator.comparing(RoleApiTree::getPath))
                        .collect(Collectors.toList());
                childs.clear();
                childs.addAll(childsOrder);
            }
            // 子节点生成树
            for (RoleApiTreeVo child : childs) {
                makeTree(child, map);
            }
            // 插入子节点
            tree.setChilds(childs);
        }
    }

    /**
     * 树转展开列表
     *
     * @param tree 树
     */
    public static List<RoleApiTreeVo> tree2ExpandedList(RoleApiTreeVo tree) {
        List<RoleApiTreeVo> list = new ArrayList<>();
        // 找到子节点
        List<RoleApiTreeVo> childs = tree.getChilds();
        // 子节点去展开
        if (childs != null) {
            for (RoleApiTreeVo child : childs) {
                makeExpandedList(list, child, tree.getPath());
            }
        }
        // 设置根节点的path
        tree.setPath(tree.getPath() + "/");
        // 子节点清空
        tree.setChilds(null);
        // 插入根节点
        list.add(tree);
        // 所有的节点展开完成后根据path排序
        return list.stream().sorted(Comparator.comparing(RoleApiTree::getPath)).collect(Collectors.toList());
    }

    /**
     * 生成展开列表
     *
     * @param list   接收的列表
     * @param tree   树
     * @param prefix 前缀
     */
    public static void makeExpandedList(List<RoleApiTreeVo> list, RoleApiTreeVo tree, String prefix) {
        // 节点设置新的前缀
        prefix = prefix + "/" + tree.getPath();
        tree.setPath(prefix);
        // 找到子节点
        List<RoleApiTreeVo> childs = tree.getChilds();
        // 子节点去展开
        if (childs != null) {
            for (RoleApiTreeVo child : childs) {
                makeExpandedList(list, child, prefix);
            }
        }
        // 子节点清空
        tree.setChilds(null);
        // 插入节点
        list.add(tree);
    }

    /**
     * 树转列表
     *
     * @param tree 树
     */
    public static List<RoleApiTreeVo> tree2List(RoleApiTreeVo tree) {
        List<RoleApiTreeVo> list = new ArrayList<>();
        makeList(list, tree);
        return list;
    }

    /**
     * 生成列表
     *
     * @param list 接收的列表
     * @param tree 树
     */
    public static void makeList(List<RoleApiTreeVo> list, RoleApiTreeVo tree) {
        // 找到子节点
        List<RoleApiTreeVo> childs = tree.getChilds();
        // 子节点去展开
        if (childs != null) {
            for (RoleApiTreeVo child : childs) {
                makeList(list, child);
            }
        }
        // 子节点清空
        tree.setChilds(null);
        // 插入节点
        list.add(tree);
    }
}
