package com.demo.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Generated;

import io.ebean.config.ModuleInfo;
import io.ebean.config.ModuleInfoLoader;

@Generated("io.ebean.querybean.generator")
@ModuleInfo(entities={"com.demo.domain.Author"})
public class _ebean$ModuleInfo implements ModuleInfoLoader {

  @Override
  public List<Class<?>> entityClasses() {
    List<Class<?>> entities = new ArrayList<>();
    entities.add(com.demo.domain.Author.class);
    return entities;
  }

  @Override
  public List<Class<?>> entityClassesFor(String dbName) {

    return Collections.emptyList();
  }

}
