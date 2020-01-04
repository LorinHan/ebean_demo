package com.demo.demo;

import com.demo.domain.Author;
import com.demo.domain.query.QAuthor;
import io.ebean.DB;

import java.util.List;

public class AuthorDemo {
    public static void testQAuthor() {
        System.out.println("QAuthor===查询单个");
        Author one = new QAuthor().id.eq(1).findOne();
        System.out.println(one);

        System.out.println("QAuthor===查询多个");
        List<Author> test = new QAuthor().nickName.eq("Lorin").findList();
        System.out.println(test);

        System.out.println("QAuthor===where查询");
        // raw可以看做where，raw("id in (select ... from ...)") 等价于sql => where id in (select ... from ...)
        List<Author> list = new QAuthor().raw("id > 2").findList();
        System.out.println(list);

        // QAuthor的删除，修改，插入，相比于Author类，有返回值可以判断操作是否成功
        // System.out.println("=====删除=====");
        // int delete = new QAuthor().id.eq(1).delete();
        // System.out.println(delete);
    }
    public static void testAuthor() {
        // System.out.println("====测试Author类，新增");
        // Author author = new Author(null, "Lorin", "Lorin");
        // author.save();

        // 使用DB，查询单个
        Author author = DB.find(Author.class, 1);
        System.out.println(author);

        System.out.println("====测试Author类，修改");
        author.setNickName("Lorin123");
        author.update();

        System.out.println("====使用DB，原生sql查询");
        List<Author> list = DB.findNative(Author.class, "select * from t_author").findList();
        System.out.println(list);
    }

    public static void main(String[] args) {
        System.out.println("====测试QAuthor类");
        testQAuthor();
        testAuthor();
    }
}
