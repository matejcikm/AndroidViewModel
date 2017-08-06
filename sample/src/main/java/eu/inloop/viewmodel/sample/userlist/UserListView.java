package eu.inloop.viewmodel.sample.userlist;

import java.util.List;

import eu.inloop.viewmodel.IView;

public interface UserListView extends IView {

    void showUsers(List<String> users);

    void navigateToActivity();
}
