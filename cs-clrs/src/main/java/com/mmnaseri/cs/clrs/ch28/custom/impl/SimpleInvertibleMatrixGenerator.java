package com.mmnaseri.cs.clrs.ch28.custom.impl;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.InvertibleMatrixGenerator;
import com.mmnaseri.cs.clrs.ch28.custom.impl.column.ColumnAdditionElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.column.ColumnMultiplicationElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.column.ColumnSwitchElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.operations.AdditionElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.impl.operations.MultiplicationElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.impl.operations.SwitchElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.impl.row.RowAdditionElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.row.RowMultiplicationElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.row.RowSwitchElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.clrs.common.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 12:11 PM)
 */
public class SimpleInvertibleMatrixGenerator<E extends Number> implements InvertibleMatrixGenerator<E> {

    private final Class<E> type;
    private static final List<ElementaryOperationPerformer<?, ?>> performers = new ArrayList<>();
    static {
        performers.add(new RowAdditionElementaryOperationPerformer<>());
        performers.add(new RowMultiplicationElementaryOperationPerformer<>());
        performers.add(new RowSwitchElementaryOperationPerformer<>());
        performers.add(new ColumnAdditionElementaryOperationPerformer<>());
        performers.add(new ColumnMultiplicationElementaryOperationPerformer<>());
        performers.add(new ColumnSwitchElementaryOperationPerformer<>());

    }

    public SimpleInvertibleMatrixGenerator(Class<E> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Matrix<E> generate(int size) {
        final Random random = new Random();
        final int bound = 1 + random.nextInt(10);
        final List<Matrix<E>> elementaryMatrices = new ArrayList<>(bound);
        for (int i = 0; i < bound; i++) {
            final Matrix<E> seed = MatrixUtils.copyOf(MatrixUtils.identity(type, size));
            final ElementaryOperationPerformer<E, ElementaryOperation<E>> performer = (ElementaryOperationPerformer<E, ElementaryOperation<E>>) performers.get(random.nextInt(performers.size()));
            performer.perform(seed, getOperation(performer, size, random));
            elementaryMatrices.add(seed);
        }
        while (elementaryMatrices.size() >= 2) {
            elementaryMatrices.add(0, MatrixUtils.multiply(elementaryMatrices.remove(0), elementaryMatrices.remove(0)));
        }
        return elementaryMatrices.get(0);
    }

    private ElementaryOperation<E> getOperation(ElementaryOperationPerformer<E, ElementaryOperation<E>> performer, int size, Random random) {
        int bound = size * size;
        final Class<? extends ElementaryOperation> operationType = performer.getOperationType();
        if (SwitchElementaryOperation.class.equals(operationType)) {
            int source = random.nextInt(size);
            int target;
            do {
                target = random.nextInt(size);
            } while (source == target);
            return new SwitchElementaryOperation<>(source, target);
        } else if (MultiplicationElementaryOperation.class.equals(operationType)) {
            int target = random.nextInt(size);
            E constant;
            do {
                constant = NumberUtils.random(type, random, bound);
            } while (constant.equals(NumberUtils.zero(type)));
            return new MultiplicationElementaryOperation<>(constant, target);
        } else if (AdditionElementaryOperation.class.equals(operationType)) {
            int source = random.nextInt(size);
            int target;
            do {
                target = random.nextInt(size);
            } while (source == target);
            E constant;
            do {
                constant = NumberUtils.random(type, random, bound);
            } while (constant.equals(NumberUtils.zero(type)));
            return new AdditionElementaryOperation<>(source, target, constant);
        }
        return null;
    }

}
