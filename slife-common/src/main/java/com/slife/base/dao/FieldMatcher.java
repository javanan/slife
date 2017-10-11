package com.slife.base.dao;



import com.slife.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;


public class FieldMatcher {
    private Pattern actived;
    private Pattern locked;
    private boolean ignoreNull = true;
    private boolean ignoreBlankStr;
    private boolean ignoreZero = true;
    private boolean ignoreDate;
    private boolean ignoreId = true;
    private boolean ignoreName;
    private boolean ignorePk;

    public FieldMatcher() {
    }

    public static FieldMatcher make(String actived, String locked, boolean ignoreNull) {
        FieldMatcher fm = new FieldMatcher();
        fm.ignoreNull = ignoreNull;
        if(!StringUtils.isBlank(actived)) {
            fm.actived = Pattern.compile(actived);
        }

        if(!StringUtils.isBlank(locked)) {
            fm.locked = Pattern.compile(locked);
        }

        return fm;
    }

    public static FieldMatcher create(boolean ignoreId) {
        FieldMatcher fm = new FieldMatcher();
        fm.ignoreId = ignoreId;
        return fm;
    }

    public static FieldMatcher make(String actived, String locked, boolean ignoreNull, boolean ignoreZero, boolean ignoreDate, boolean ignoreId, boolean ignoreName, boolean ignorePk) {
        FieldMatcher fm = make(actived, locked, ignoreNull);
        fm.ignoreZero = ignoreZero;
        fm.ignoreDate = ignoreDate;
        fm.ignoreId = ignoreId;
        fm.ignoreName = ignoreName;
        fm.ignorePk = ignorePk;
        return fm;
    }

    public static FieldMatcher make(String actived, String locked, boolean ignoreNull, boolean ignoreZero, boolean ignoreDate, boolean ignoreId, boolean ignoreName, boolean ignorePk, boolean ignoreBlankStr) {
        FieldMatcher fm = make(actived, locked, ignoreNull);
        fm.ignoreZero = ignoreZero;
        fm.ignoreDate = ignoreDate;
        fm.ignoreId = ignoreId;
        fm.ignoreName = ignoreName;
        fm.ignorePk = ignorePk;
        fm.ignoreBlankStr = ignoreBlankStr;
        return fm;
    }

    public boolean match(String str) {
        return null != this.locked && this.locked.matcher(str).find()?false:null == this.actived || this.actived.matcher(str).find();
    }

    public boolean isIgnoreNull() {
        return this.ignoreNull;
    }

    public FieldMatcher setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
        return this;
    }

    public Pattern getActived() {
        return this.actived;
    }

    public Pattern getLocked() {
        return this.locked;
    }

    public FieldMatcher setActived(String actived) {
        if(actived != null) {
            this.actived = Pattern.compile(actived);
        } else {
            this.actived = null;
        }

        return this;
    }

    public FieldMatcher setLocked(String locked) {
        if(locked != null) {
            this.locked = Pattern.compile(locked);
        } else {
            this.locked = null;
        }

        return this;
    }

    public boolean isIgnoreZero() {
        return this.ignoreZero;
    }

    public FieldMatcher setIgnoreZero(boolean ignoreZero) {
        this.ignoreZero = ignoreZero;
        return this;
    }

    public boolean isIgnoreDate() {
        return this.ignoreDate;
    }

    public FieldMatcher setIgnoreDate(boolean ignoreDate) {
        this.ignoreDate = ignoreDate;
        return this;
    }

    public boolean isIgnoreId() {
        return this.ignoreId;
    }

    public FieldMatcher setIgnoreId(boolean ignoreId) {
        this.ignoreId = ignoreId;
        return this;
    }

    public boolean isIgnoreName() {
        return this.ignoreName;
    }

    public FieldMatcher setIgnoreName(boolean ignoreName) {
        this.ignoreName = ignoreName;
        return this;
    }

    public boolean isIgnorePk() {
        return this.ignorePk;
    }

    public FieldMatcher setIgnorePk(boolean ignorePk) {
        this.ignorePk = ignorePk;
        return this;
    }

    public boolean isIgnoreBlankStr() {
        return this.ignoreBlankStr;
    }

    public void setIgnoreBlankStr(boolean ignoreBlankStr) {
        this.ignoreBlankStr = ignoreBlankStr;
    }

    public static FieldMatcher simple(String... fields) {
        final HashSet m = new HashSet(Arrays.asList(fields));
        return new FieldMatcher() {
            public boolean match(String str) {
                return m.contains(str);
            }
        };
    }
}
