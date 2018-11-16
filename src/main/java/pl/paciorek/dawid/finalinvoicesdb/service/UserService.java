package pl.paciorek.dawid.finalinvoicesdb.service;

import pl.paciorek.dawid.finalinvoicesdb.model.User;

public interface UserService {
    void saveUser(User user);
    User getActiveUser(String name);
    boolean isUserAlreadyPresent(User user);
}
