package com.easy.make.tenantmaker.core.user.displayer;

import com.easy.make.tenantmaker.core.user.data.model.User;
import com.easy.make.tenantmaker.core.user.data.model.Users;

public interface UsersDisplayer {

    void attach(SelectionListener selectionListener);

    void detach(SelectionListener selectionListener);

    void display(Users users);

    void showFailure();

    void displaySelectedUsers(Users selectedUsers);

    interface SelectionListener {

        void onUserSelected(User user);

        void onUserDeselected(User user);

        void onCompleteClicked();
    }
}
