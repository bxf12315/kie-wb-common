package org.kie.workbench.common.screens.social.hp.client.homepage;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.NavLink;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.security.shared.api.identity.User;
import org.kie.uberfire.social.activities.client.widgets.item.model.LinkCommandParams;
import org.kie.uberfire.social.activities.client.widgets.timeline.simple.model.SimpleSocialTimelineWidgetModel;
import org.kie.uberfire.social.activities.model.SocialPaged;
import org.kie.uberfire.social.activities.model.SocialUser;
import org.kie.uberfire.social.activities.service.SocialUserRepositoryAPI;
import org.kie.workbench.common.screens.social.hp.client.resources.i18n.Constants;
import org.kie.workbench.common.screens.social.hp.client.util.IconLocator;
import org.kie.workbench.common.screens.social.hp.predicate.UserTimeLineFileChangesPredicate;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.UberView;
import org.uberfire.lifecycle.OnOpen;
import org.uberfire.mvp.ParameterizedCommand;

@ApplicationScoped
@WorkbenchScreen(identifier = "SocialHomePageSidePresenter")
public class SocialHomePageSidePresenter {

    public interface View extends UberView<SocialHomePageSidePresenter> {

        void setupWidget( SimpleSocialTimelineWidgetModel model );
    }

    @Inject
    private View view;

    @Inject
    private PlaceManager placeManager;

    @Inject
    Caller<SocialUserRepositoryAPI> socialUserRepositoryAPI;

    @Inject
    private User loggedUser;

    @Inject
    private IconLocator iconLocator;

    @Inject
    private DefaultSocialLinkCommandGenerator linkCommandGenerator;

    @PostConstruct
    public void init() {
    }

    @AfterInitialization
    public void loadContent() {

    }

    @OnOpen
    public void onOpen() {
        final SocialPaged socialPaged = new SocialPaged( 5 );
        socialUserRepositoryAPI.call( new RemoteCallback<SocialUser>() {
            public void callback( SocialUser socialUser ) {
                SimpleSocialTimelineWidgetModel model = new SimpleSocialTimelineWidgetModel( socialUser, new UserTimeLineFileChangesPredicate(), placeManager, socialPaged )
                        .withOnlyMorePagination( new NavLink( "(more...)" ))
                        .withIcons( iconLocator.getResourceTypes() )
                        .withLinkCommand( generateLinkCommand() );
                view.setupWidget( model );
            }
        } ).findSocialUser( loggedUser.getIdentifier() );
    }

    private ParameterizedCommand<LinkCommandParams> generateLinkCommand() {
       return linkCommandGenerator.generateLinkCommand();
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return Constants.INSTANCE.RecentAssets();
    }

    @WorkbenchPartView
    public UberView<SocialHomePageSidePresenter> getView() {
        return view;
    }

}
