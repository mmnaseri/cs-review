package com.mmnaseri.cs.clrs.ch16.s3;

import com.mmnaseri.cs.clrs.ch12.sp.Bit;
import com.mmnaseri.cs.clrs.ch12.sp.BitUtils;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/25/15, 1:26 AM)
 */
public class CharacterCode {

    private final Character character;
    private final Bit[] code;

    public CharacterCode(Character character, Bit[] code) {
        this.character = character;
        this.code = code;
    }

    public Character getCharacter() {
        return character;
    }

    public Bit[] getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getCharacter() + ":" + BitUtils.toString(getCode());
    }

}
