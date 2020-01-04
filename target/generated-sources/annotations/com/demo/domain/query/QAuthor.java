package com.demo.domain.query;

import com.demo.domain.Author;
import io.ebean.Database;
import io.ebean.FetchGroup;
import io.ebean.Query;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import javax.annotation.Generated;

/**
 * Query bean for Author.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@Generated("io.ebean.querybean.generator")
@TypeQueryBean("v1")
public class QAuthor extends TQRootBean<Author,QAuthor> {

  private static final QAuthor _alias = new QAuthor(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QAuthor alias() {
    return _alias;
  }

  public PLong<QAuthor> id;
  public PString<QAuthor> realName;
  public PString<QAuthor> nickName;


  /**
   * Return a query bean used to build a FetchGroup.
   */
  public static QAuthor forFetchGroup() {
    return new QAuthor(FetchGroup.queryFor(Author.class));
  }


  /**
   * Construct with a given Database.
   */
  public QAuthor(Database server) {
    super(Author.class, server);
  }

  /**
   * Construct using the default Database.
   */
  public QAuthor() {
    super(Author.class);
  }

  /**
   * Construct for Alias.
   */
  private QAuthor(boolean dummy) {
    super(dummy);
  }

  /**
   * Private constructor for FetchGroup building.
   */
  private QAuthor(Query<Author> fetchGroupQuery) {
    super(fetchGroupQuery);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PLong<QAuthor> id = _alias.id;
    public static PString<QAuthor> realName = _alias.realName;
    public static PString<QAuthor> nickName = _alias.nickName;
  }
}
