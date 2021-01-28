package com.demo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.demo.constant.CaptchaTypeEnum;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.RoleApiTree;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.RoleApiRefVo;
import com.demo.entity.vo.RoleApiTreeVo;
import com.demo.entity.vo.RoleApiVo;
import com.demo.entity.vo.UserVo;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * <h1>测试类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Test {

    public static void main(String[] args) {

        User u = new User();
        u.setId(0L);
        u.setAccount("root");
        u.setName("root");
        u.setGender(0);
        u.setYear(0);
        u.setCreateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(u);

        UserVo uv = new UserVo();
        uv.setId(1L);
        uv.setAccount("admin");
        uv.setName("ck");
        uv.setGender(1);
        uv.setYear(1998);
        uv.setYearNot(0);
        uv.setYearEnd(2000);
        System.out.println(uv);

        Result r1 = Result.o(ResultCodeEnum.OK);
        System.out.println(r1.isOk());
        Result r2 = Result.e(ResultCodeEnum.ERROR);
        System.out.println(r2.isOk());
        Result r3 = Result.e(ResultCodeEnum.USER_ACCOUNT_FORBIDDEN, "哈哈哈哈", u);
        System.out.println(r3);
        User ru = (User) r3.getData();
        System.out.println(ru);

        ResultBatch<User> batchResult = new ResultBatch<>();
        batchResult.add(true, u, "ok");
        batchResult.add(false, u, "fff");
        batchResult.add(true, u, "true");
        batchResult.add(false, u, "aaa");
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());
        ResultBatch<User> batchResult2 = new ResultBatch<>();
        batchResult2.add(false, u, "false");
        batchResult2.add(true, u, "qwqw");
        batchResult2.add(false, u, "fet");
        ResultBatch<User> batchResultMerge = ResultBatch.merge(batchResult, batchResult2);
        System.out.println(batchResultMerge);

        // google chrome
        // String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)
        // AppleWebKit/537.36 (KHTML, like
        // Gecko) Chrome/72.0.3626.119 Safari/537.36";
        // ie9
        String userAgentString = "User-Agent:Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;";
        // safari
        // String userAgentString = "User-Agent:Mozilla/5.0 (Macintosh; U; Intel Mac OS
        // X 10_6_8; en-us)
        // AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50";
        // iphone
        // String userAgentString = "User-Agent:Mozilla/5.0 (iPhone; U; CPU iPhone OS
        // 4_3_3 like Mac OS X;
        // en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2
        // Safari/6533.18.5";
        // ipad
        // String userAgentString = "User-Agent:Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like
        // Mac OS X; en-us)
        // AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2
        // Safari/6533.18.5";
        // android
        // String userAgentString = "User-Agent: MQQBrowser/26 Mozilla/5.0 (Linux; U;
        // Android 2.3.7; zh-cn;
        // MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko)
        // Version/4.0 Mobile
        // Safari/533.1";
        // windows phone
        // String userAgentString = "User-Agent: Mozilla/5.0 (compatible; MSIE 9.0;
        // Windows Phone OS 7.5;
        // Trident/5.0; IEMobile/9.0; HTC; Titan)";
        UserAgent userAgent = new UserAgent(userAgentString);
        System.out.println(userAgent.getBrowser().getName());
        System.out.println(userAgent.getBrowserVersion());
        System.out.println(userAgent.getOperatingSystem().getName());
        System.out.println(userAgent.getOperatingSystem().getDeviceType().getName());

        System.out.println(CaptchaTypeEnum.findByType(1));

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

        System.out.println(roleApiTreeList);
        RoleApiTreeVo tree = list2Tree(roleApiTreeList);
        System.out.println(tree);
        List<RoleApiTreeVo> expandedList = tree2ExpandedList(tree);
        System.out.println(expandedList);

        List<RoleApiVo> roleApiList = new ArrayList<>();
        roleApiList.add(new RoleApiVo(0L, ""));
        roleApiList.add(new RoleApiVo(1L, "all"));
        roleApiList.add(new RoleApiVo(2L, "a"));
        roleApiList.add(new RoleApiVo(3L, "b+ca"));
        List<RoleApiRefVo> roleApiRefList = new ArrayList<>();
        roleApiRefList.add(new RoleApiRefVo(0L, 0L, 0L));
        roleApiRefList.add(new RoleApiRefVo(1L, 1L, 1L));
        roleApiRefList.add(new RoleApiRefVo(2L, 2L, 11L));
        roleApiRefList.add(new RoleApiRefVo(3L, 3L, 12L));
        roleApiRefList.add(new RoleApiRefVo(4L, 3L, 131L));

    }

    /**
     * 列表转树
     *
     * @param list 列表
     */
    public static RoleApiTreeVo list2Tree(List<RoleApiTreeVo> list) {
        // 按parentId分组
        Map<Long, List<RoleApiTreeVo>> map = list.stream().collect(Collectors.groupingBy(RoleApiTreeVo::getParentId));
        // 找到根节点，id为1，parentId为0
        RoleApiTreeVo tree = map.get(0L).stream().filter(s -> s.getId() == 1L).findFirst().get();
        // 把根节点从map中去除，防止循环嵌套
        map.get(0L).remove(tree);
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

}
