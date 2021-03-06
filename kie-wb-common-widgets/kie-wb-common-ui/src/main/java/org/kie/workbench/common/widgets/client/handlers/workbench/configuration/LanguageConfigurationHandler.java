package org.kie.workbench.common.widgets.client.handlers.workbench.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.workbench.common.widgets.client.resources.i18n.CommonConstants;
import org.uberfire.commons.data.Pair;
import org.uberfire.ext.services.shared.preferences.UserWorkbenchPreferences;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;

@ApplicationScoped
public class LanguageConfigurationHandler extends WorkbenchConfigurationHandler {

    private final static String LANGUAGE_SECLECTOR_HADNLER_ID = "languageSeclector";

    private Map<String, String> languageMap = new HashMap<String, String>();

    private CommonConstants constants = GWT.create( CommonConstants.class );

    @Inject
    ConfigurationComboBoxItemWidget languageItem;

    public LanguageConfigurationHandler() {
        languageMap.put( "default", constants.English() );
        languageMap.put( "zh_CN", constants.Chinese() );
        languageMap.put( "de", constants.German() );
        languageMap.put( "es", constants.Spanish() );
        languageMap.put( "fr", constants.French() );
        languageMap.put( "ja", constants.Japanese() );
        languageMap.put( "pt_BR", constants.Portuguese() );
    }

    @Override
    public String getDescription() {
        return constants.Language_Selector();
    }

    @Override
    public void configurationSetting( boolean isInit ) {
        String languageName = languageItem.getSelectedItem().getK1();
        String isRefresh = Window.Location.getParameter( "isRefresh" );
        if ( (isRefresh == null || isRefresh.equals( "" )) && isInit ) {
            Window.Location.assign( Window.Location.createUrlBuilder()
                    .removeParameter( LocaleInfo.getLocaleQueryParam() )
                    .setParameter( LocaleInfo.getCurrentLocale().getLocaleQueryParam(), languageName )
                    .setParameter( "isRefresh", "false" )
                    .buildString() );
        } else if ( !isInit ) {
            Window.Location.assign( Window.Location.createUrlBuilder()
                    .removeParameter( LocaleInfo.getLocaleQueryParam() )
                    .setParameter( LocaleInfo.getCurrentLocale().getLocaleQueryParam(), languageName )
                    .buildString() );
        }
    }

    @Override
    protected void setDefaultConfigurationValues( UserWorkbenchPreferences response ) {
        languageItem.setSelectedItem( response.getLanguage() );
    }

    @Override
    protected void initHandler() {
        languageItem.extensionItemLabel.setText( constants.Language() );
        languageItem.getExtensionItem().clear();
        String[] languages = LocaleInfo.getAvailableLocaleNames();
        for ( String language : languages ) {
            languageItem.getExtensionItem().addItem( Pair.newPair( languageMap.get( language ), language ) );
        }
        super.getExtensions().add( Pair.newPair( LANGUAGE_SECLECTOR_HADNLER_ID, languageItem ) );
    }

    @Override
    protected UserWorkbenchPreferences getSelectedUserWorkbenchPreferences() {
        UserWorkbenchPreferences preference = super.getPreference();
        if ( preference != null ) {
            String selectedlanguage = languageItem.getSelectedItem().getK1();
            preference.setLanguage( selectedlanguage );
            return preference;
        }
        preference = new UserWorkbenchPreferences( languageItem.getSelectedItem().getK1() );
        return preference;
    }
}
