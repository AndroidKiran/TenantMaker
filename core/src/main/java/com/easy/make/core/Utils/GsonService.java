package com.easy.make.core.Utils;

import com.easy.make.core.user.data.model.User;

/**
 * Created by ravi on 12/09/16.
 */
public interface GsonService {

    String toString(User user);

    User toUser(String string);
}
