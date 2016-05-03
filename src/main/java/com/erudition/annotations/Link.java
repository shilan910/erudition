package com.erudition.annotations;

/**
 * Created by jeff on 16/2/28.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Link {

    public String label();
    public String family();
    public String parent();

}