package com.k1.trakttv.dependency;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * To manage instances scoped in @ApplicationScope live as long as Application object.
 * <p/>
 * Created by K1 on 7/17/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
