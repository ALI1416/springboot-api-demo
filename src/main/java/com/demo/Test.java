package com.demo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.demo.constant.CaptchaTypeEnum;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.RoleApiTreeVo;
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
        batchResult.add(u);
        batchResult.add(false, u, "fff");
        batchResult.add(u);
        batchResult.add(false, u, "aaa");
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());

        // google chrome
//        String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";
        // ie9
        String userAgentString = "User-Agent:Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;";
        // safari
//        String userAgentString = "User-Agent:Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50";
        // iphone
//        String userAgentString = "User-Agent:Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
        // ipad
//        String userAgentString = "User-Agent:Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
        // android
//        String userAgentString = "User-Agent: MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        // windows phone
//        String userAgentString = "User-Agent: Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)";
        UserAgent userAgent = new UserAgent(userAgentString);
        System.out.println(userAgent.getBrowser().getName());
        System.out.println(userAgent.getBrowserVersion());
        System.out.println(userAgent.getOperatingSystem().getName());
        System.out.println(userAgent.getOperatingSystem().getDeviceType().getName());

        System.out.println(CaptchaTypeEnum.findByType(1));

        String a1 = "Ab";
        String a2 = null;
        System.out.println(a1.equalsIgnoreCase(a2));

        Object o = "男";
        System.out.println(o);
        System.out.println(o.toString());
        System.out.println((String) o);
//        System.out.println((Integer) o);
//        System.out.println(Integer.valueOf((String) o));
//        System.out.println(Integer.valueOf(o.toString()));
        System.out.println("男".equals(o));

        String s = "1234";
        Long l = 1234L;
        System.out.println(s.equals(l.toString()));

        List<RoleApiTreeVo> list = new ArrayList<>();
        list.add(new RoleApiTreeVo(0L, 0L, ""));
        list.add(new RoleApiTreeVo(1L, 0L, "1"));
        list.add(new RoleApiTreeVo(2L, 0L, "2"));
        list.add(new RoleApiTreeVo(3L, 0L, "3"));
        list.add(new RoleApiTreeVo(11L, 1L, "11"));
        list.add(new RoleApiTreeVo(12L, 1L, "12"));
        list.add(new RoleApiTreeVo(21L, 2L, "21"));
        list.add(new RoleApiTreeVo(22L, 2L, "22"));
        list.add(new RoleApiTreeVo(23L, 2L, "23"));
        list.add(new RoleApiTreeVo(111L, 11L, "111"));
        list.add(new RoleApiTreeVo(231L, 23L, "232"));
        list.add(new RoleApiTreeVo(232L, 23L, "232"));
        list.add(new RoleApiTreeVo(2321L, 232L, "2321"));
        System.out.println(list);
        RoleApiTreeVo tree = list2Tree(list);
        System.out.println(tree);
        List<RoleApiTreeVo> aa = new ArrayList<>();
        tree2ExpandedList(aa, tree, "/", "");
        System.out.println(aa);
    }

    /**
     * 列表转树
     * 
     * @param list 列表
     */
    public static RoleApiTreeVo list2Tree(List<RoleApiTreeVo> list) {
        // 按parentId分组
        Map<Long, List<RoleApiTreeVo>> map = list.stream().collect(Collectors.groupingBy(RoleApiTreeVo::getParentId));
        // 找到根节点，id和parentId都为0
        RoleApiTreeVo root = map.get(0L).stream().filter(s -> s.getId() == 0L).findFirst().get();
        // 把根节点从map中去除，防止循环嵌套
        map.get(0L).remove(root);
        // 生成树
        makeTree(root, map);
        return root;
    }

    /**
     * 生成树
     * 
     * @param root 树的根节点
     * @param map  按parentId分组并且去除根节点的列表
     */
    public static void makeTree(RoleApiTreeVo root, Map<Long, List<RoleApiTreeVo>> map) {
        // 找到子节点
        List<RoleApiTreeVo> childs = map.get(root.getId());
        if (childs != null) {
            // 子节点生成树
            for (RoleApiTreeVo chile : childs) {
                makeTree(chile, map);
            }
            // 插入子节点
            root.setChilds(childs);
        }
    }

    /**
     * 树转展开的列表
     * 
     * @param tree 树
     */
    public static void tree2ExpandedList(List<RoleApiTreeVo> list, RoleApiTreeVo tree, String prefix,
            String newPrefix) {
        RoleApiTreeVo root = tree;
        newPrefix = newPrefix + prefix + root.getPath();
        root.setPath(newPrefix);
        List<RoleApiTreeVo> childs = root.getChilds();
        if (childs != null) {
            for (RoleApiTreeVo child : childs) {
                tree2ExpandedList(list, child, prefix, newPrefix);
            }
        }
        root.setChilds(null);
        list.add(root);
    }

    /**
     * 建造扁平的树
     * 
     * @param tree 树
     */
    public static List<RoleApiTreeVo> buildFlattenedTree(RoleApiTreeVo tree, String prefix) {

        return null;
    }

}
