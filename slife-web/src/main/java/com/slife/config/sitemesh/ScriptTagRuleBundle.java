package com.slife.config.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * Created by chen on 2017/8/29.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class ScriptTagRuleBundle implements TagRuleBundle {
    @Override
    public void install(State defaultState, ContentProperty contentProperty,
                        SiteMeshContext siteMeshContext) {

          defaultState.addRule("slife_js",
                  new ExportTagToContentRule(siteMeshContext,contentProperty.getChild("slife_js"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty,
                        SiteMeshContext siteMeshContext) {

    }

}
