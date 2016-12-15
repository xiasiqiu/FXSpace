package com.fx.fxspace.di.component;

import com.fx.fxspace.di.modules.WelcomeModule;
import com.fx.fxspace.di.scopes.UserScope;
import com.fx.fxspace.view.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

@UserScope
@Component(modules = WelcomeModule.class,dependencies = NetComponent.class)
public interface WelcomeComponent {
    void inject(WelcomeActivity welcomeActivity);
}
