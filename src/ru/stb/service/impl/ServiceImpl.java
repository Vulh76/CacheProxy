package ru.stb.service.impl;

import ru.stb.service.Service;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceImpl implements Service, Externalizable {
    @Override
    public List<String> init(String text, String div, int limit, Date date) {
        return Arrays.asList(text.split(div, limit));
    }

    @Override
    public List<String> work(String text, String div, int limit) {
        return Arrays.asList(text.split(div, limit));
    }

    @Override
    public void clear() {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
