package com.fererlab.story;

import java.util.Collections;
import java.util.List;

public class Login extends BaseJUnitStory {

    @Override
    public List<?> getSteps() {
        return Collections.singletonList(new LoginSteps());
    }

}