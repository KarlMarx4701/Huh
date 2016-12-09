package edu.bklawsonbsu.huh;

import edu.bklawsonbsu.huh.groupClasses.Group;

@SuppressWarnings("unused") // Inspection warning for Data Object
public class StaticGroupHolder {
    private static String key;
    private static String groupName;
    private static Group group;
    private static String languageAbbr = "en";

    public StaticGroupHolder() {

    }

    public String getLanguageAbbr() {
        return languageAbbr;
    }

    public void setLanguageAbbr(String languageAbbr) {
        StaticGroupHolder.languageAbbr = languageAbbr;
    }

    public void setGroup(Group group) {
        StaticGroupHolder.group = group;
    }

    public Group getGroup() {
        return group;
    }

}
