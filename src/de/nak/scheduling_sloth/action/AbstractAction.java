package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Abstract class for all application action and global fields.
 *
 * @author      Kevin Scholz <kevin.scholz@nordakademie.de>
 * @version     1.0
 * @since       2014-11-15
 */
public abstract class AbstractAction extends ActionSupport {

    /**
     * Get user language from Context
     * @return Active user language
     */
    public final String getLocalLang() {
        return getLocale().getLanguage();
    }
}
