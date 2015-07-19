package com.mmnaseri.cs.clrs.ch12.s2;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 7:43 PM)
 */
public interface SearchTree<E, N extends TreeNode<E>> {

    N getRoot();

    void setRoot(N root);

    N find(E value);

    N find(N root, E value);

    N minimum();

    N minimum(N root);

    N maximum();

    N maximum(N root);

    N predecessor(N node);

    N successor(N node);

    N insert(E value);

    @SuppressWarnings("unchecked")
    List<N> insert(E... value);

    N delete(E value);

}
