package com.example.restaurant.web;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import com.example.restaurant.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.example.restaurant.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "meals", "password");
    public static MatcherFactory.Matcher<User> USER_WITH_MEALS_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("registered", "meals.user", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int GUEST_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User guest = new User(GUEST_ID, "Guest", "guest@gmail.com", "guest");


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
