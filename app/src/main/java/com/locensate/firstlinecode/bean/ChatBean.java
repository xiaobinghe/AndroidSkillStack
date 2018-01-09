package com.locensate.firstlinecode.bean;

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
 * 时间： 2017/3/1 11:34
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */
public class ChatBean {
    public String content;
    public int type;
    public static int RECEVIED = 1;
    public static int SENDED = 0;

    public ChatBean(String content, int type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatBean chatBean = (ChatBean) o;

        if (type != chatBean.type) return false;
        return content.equals(chatBean.content);

    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + type;
        return result;
    }

    @Override
    public String toString() {
        return "ChatBean{" +
                "content='" + content + '\'' +
                ", type=" + type +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
