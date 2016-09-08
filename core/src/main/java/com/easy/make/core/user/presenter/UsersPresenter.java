package com.easy.make.core.user.presenter;

import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.navigation.Navigator;
import com.easy.make.core.user.data.model.User;
import com.easy.make.core.user.data.model.Users;
import com.easy.make.core.user.displayer.UsersDisplayer;
import com.easy.make.core.user.service.UserService;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class UsersPresenter {
    private final UserService userService;
    private final UsersDisplayer usersDisplayer;
    private final Navigator navigator;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public UsersPresenter(UserService userService,
                          UsersDisplayer usersDisplayer,
                          Navigator navigator) {
        this.userService = userService;
        this.usersDisplayer = usersDisplayer;
        this.navigator = navigator;
    }

    public void startPresenting() {
        usersDisplayer.attach(selectionListener);

        subscriptions.add(
                userService.getAllUsers().subscribe(new Action1<Users>() {
                    @Override
                    public void call(Users users) {
                        usersDisplayer.display(users);
                    }
                })
        );
//        subscriptions.add(
//                channelService.getOwnersOfChannel(channel)
//                        .subscribe(new Action1<DatabaseResult<Users>>() {
//                            @Override
//                            public void call(DatabaseResult<Users> databaseResult) {
//                                if (databaseResult.isSuccess()) {
//                                    usersDisplayer.displaySelectedUsers(databaseResult.getData());
//                                } else {
//                                    errorLogger.reportError(databaseResult.getFailure(), "Cannot fetch channel owners");
//                                    usersDisplayer.showFailure();
//                                }
//                            }
//                        })
//        );
    }

    public void stopPresenting() {
        usersDisplayer.detach(selectionListener);
        subscriptions.clear();
        subscriptions = new CompositeSubscription();
    }

    private UsersDisplayer.SelectionListener selectionListener = new UsersDisplayer.SelectionListener() {
        @Override
        public void onUserSelected(final User user) {
//            analytics.trackAddChannelOwner(channel.getName(), user.getId());
//            channelService.addOwnerToPrivateChannel(channel, user)
//                    .subscribe(updateOnActionResult());
        }

        @Override
        public void onUserDeselected(User user) {
//            analytics.trackRemoveChannelOwner(channel.getName(), user.getId());
//            channelService.removeOwnerFromPrivateChannel(channel, user)
//                    .subscribe(updateOnActionResult());
        }

        @Override
        public void onCompleteClicked() {
//            navigator.toParent();
        }
    };

    private Action1<DatabaseResult<User>> updateOnActionResult() {
        return new Action1<DatabaseResult<User>>() {
            @Override
            public void call(DatabaseResult<User> userDatabaseResult) {
                if (!userDatabaseResult.isSuccess()) {
//                    errorLogger.reportError(userDatabaseResult.getFailure(), "Cannot update channel owners");
                    usersDisplayer.showFailure();
                }
            }
        };
    }

}
