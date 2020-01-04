### 1.创建maven项目
### 2.在pom文件中引入ebean的依赖项
- 引入slf4j是因为ebean需要依赖他作为日志，如果不引入会报错
- 注意mysql驱动的版本，根据数据库版本引入，我的是mysql8，所以引入的是8.x.x的驱动
```
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.29</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.6.6</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.18</version>
        </dependency>
        <!-- ebean -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>persistence-api</artifactId>
            <version>2.2.1</version>
        </dependency>
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>ebean</artifactId>
            <version>12.1.8</version>
        </dependency>
        <!-- Optional: query bean support -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>ebean-querybean</artifactId>
            <version>12.1.8</version>
        </dependency>
        <!-- Optional: query bean generation via APT -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>querybean-generator</artifactId>
            <version>12.1.8</version>
            <scope>provided</scope>
        </dependency>
        <!-- Test dependencies -->
        <!-- includes docker test database container support  -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>ebean-test</artifactId>
            <version>12.1.8</version>
            <scope>test</scope>
        </dependency>
        <!-- /ebean -->
```
### 3.添加ebean的组件配置
```
            <!-- eBean -->
            <plugin>
                <groupId>io.repaint.maven</groupId>
                <artifactId>tiles-maven-plugin</artifactId>
                <version>2.12</version>
                <extensions>true</extensions>
                <configuration>
                    <tiles>
                        <tile>io.ebean.tile:enhancement:12.1.8</tile> <!-- ebean enhancement -->
                        <tile>io.ebean.tile:kotlin-kapt:1.5</tile> <!-- kotlin compile with query bean generation -->
                    </tiles>
                </configuration>
            </plugin>
```
### 4.完整的pom文件
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.demo</groupId>
    <artifactId>java_and_ebean</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.29</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.6.6</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.18</version>
        </dependency>
        <!-- ebean -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>persistence-api</artifactId>
            <version>2.2.1</version>
        </dependency>
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>ebean</artifactId>
            <version>12.1.8</version>
        </dependency>
        <!-- Optional: query bean support -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>ebean-querybean</artifactId>
            <version>12.1.8</version>
        </dependency>
        <!-- Optional: query bean generation via APT -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>querybean-generator</artifactId>
            <version>12.1.8</version>
            <scope>provided</scope>
        </dependency>
        <!-- Test dependencies -->
        <!-- includes docker test database container support  -->
        <dependency>
            <groupId>io.ebean</groupId>
            <artifactId>ebean-test</artifactId>
            <version>12.1.8</version>
            <scope>test</scope>
        </dependency>
        <!-- /ebean -->
    </dependencies>

    <build>
        <plugins>
            <!-- eBean -->
            <plugin>
                <groupId>io.repaint.maven</groupId>
                <artifactId>tiles-maven-plugin</artifactId>
                <version>2.12</version>
                <extensions>true</extensions>
                <configuration>
                    <tiles>
                        <tile>io.ebean.tile:enhancement:12.1.8</tile> <!-- ebean enhancement -->
                        <tile>io.ebean.tile:kotlin-kapt:1.5</tile> <!-- kotlin compile with query bean generation -->
                    </tiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
### 5.idea打开settings，在plugins中查找 ebean11，找到Ebean 11 Enhancer，下载后重启idea，然后在工具栏的build中，可以看到Ebean 11 Enhancement，勾选它
### 6.在resources目录下新建数据库配置文件，文件名为ebean.properties，ebean默认会查找该文件名的配置文件；需要注意的是数据库的驱动，我这里是mysql8，所以用的是com.mysql.cj.jdbc.Driver，如果引入的是mysql5的驱动jar，则需要去掉cj，使用com.mysql.jdbc.Driver
```
ebean.search.packages= com.demo
# the name of the default server
datasource.default=db

## define these in external properties ...

datasource.db.username=root
datasource.db.password=joker8133xx
datasource.db.databaseUrl=jdbc:mysql://localhost:3306/springboot_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
datasource.db.databaseDriver=com.mysql.cj.jdbc.Driver
```
### 7.编写实体类
- @Entity声明这是一个数据库实体类
- @Table中的name指定是哪一张表
- 继承Model类之后，该类的实例会自动拥有 .save() .delete() .updata()等方法，如果不继承也可以正常使用该实体类，但是少了这些方法
```
package com.demo.domain;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_author")
public class Author extends Model {
    @Id
    Long id;
    String realName;
    String nickName;

    ... 这里省略getter、setter
}
```
### 8.测试例子
```
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
```
### 9.说明
- ebean基本上有以下几种使用方式：
##### 一，实体类继承Model类，自带增删改方法；
```
Author author = new Author(null, "Lorin", "Lorin");
author.save();
```
##### 二，使用ebean的DB类进行操作，也可以配合实体类继承Model来使用，执行原生sql也可以用该类来进行；
```
Author author = DB.find(Author.class, 1);

System.out.println("====测试Author类，修改");
author.setNickName("Lorin123");
author.update();

// 原生sql
List<Author> list = DB.findNative(Author.class, "select * from t_author").findList();
System.out.println(list);
```
##### 三，编写完实体类之后，使用maven的compile打包一下（在idea右侧的工具栏中一般会有maven工具栏，Lifecycle下，点击即可执行），会自动生成出”Q实体类“，比如Author就会生成出QAuthor类，相比于普通实体类，QAuthor类的功能更强大，而且相比于普通实体类，QAuthor类的增删改有返回值，可以用来判断操作是否成功，普通实体类的增删改没有返回值
```
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
```
### 10.打包编译，需要在pom中添加”maven-assembly-plugin“组件
- 其中的mainClass需要指定入口函数所在的类
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>2.6</version>
    <executions>
        <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals> <goal>single</goal> </goals>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>com.demo.demo.AuthorDemo</mainClass>
                    </manifest>
                </archive>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
        </execution>
    </executions>
</plugin>
```
### 11.配置完打包组件后，使用maven的package进行打包，会在target目录下生成两个jar，一个是"项目名.jar"，一个是"项目名-jar-with-dependencies.jar"，第二个jar包就是组件将项目代码以及所有第三方依赖进行打包，打包完后使用 “java -jar xxx.jar” 命令执行第二个jar包就可以将项目运行起来了。