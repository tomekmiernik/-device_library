package com.example.company.device_library.util.mappers;

public interface Mapper<F, T> {
    T map(F from);

    F reverse(T to);
}
