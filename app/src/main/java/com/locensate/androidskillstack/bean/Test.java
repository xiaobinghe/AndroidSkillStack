package com.locensate.androidskillstack.bean;

/**
 * -------------------------------------
 * <p>
 * 项目名称： FirstLineCode
 * <p>
 * 版权：locensate.com 版权所有 2016
 * <p>
 * 公司主页：http://www.locensate.com/
 * <p>
 * 描述：
 * <p>
 * 作者： xiaobinghe
 * <p>
 * 时间： 2017/3/22 11:49
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */

public class Test {


    /**
     * flag : true
     * data : {"account":"hangman","name":"张三","sex":1,"icon":"/a/b/zhangsan.png","sign":"我的个性签名","area":"深圳","token":"5904c7ae-3e75-48c8-bbee-ad094533a422"}
     */

    private boolean flag;
    /**
     * account : hangman
     * name : 张三
     * sex : 1
     * icon : /a/b/zhangsan.png
     * sign : 我的个性签名
     * area : 深圳
     * token : 5904c7ae-3e75-48c8-bbee-ad094533a422
     */

    private DataBean data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String account;
        private String name;
        private int sex;
        private String icon;
        private String sign;
        private String area;
        private String token;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
