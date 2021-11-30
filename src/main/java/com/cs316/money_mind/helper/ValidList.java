package com.cs316.money_mind.helper;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * ValidList
 *
 * @author Sainjargal Ishdorj
 **/
@Data
@ToString
public class ValidList<E> implements List<E> {
    @Valid
    @Delegate
    private List<E> list = new ArrayList<>();
}