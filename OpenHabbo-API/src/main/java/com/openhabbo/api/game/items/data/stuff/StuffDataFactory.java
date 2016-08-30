package com.openhabbo.api.game.items.data.stuff;

import com.openhabbo.api.game.items.data.stuff.types.*;

public class StuffDataFactory {
    public static Class<? extends StuffData> getStuffDataClass(StuffDataType type) {
        switch (type) {
            default:
            case LEGACY:
                return LegacyStuffData.class;

            case STRING_ARRAY:
                return StringArrayStuffData.class;

            case HIGH_SCORE:
                return HighScoreStuffData.class;

            case INT_ARRAY:
                return IntArrayStuffData.class;

            case MAP:
                return MapStuffData.class;

            case CRACKABLE:
                return CrackableStuffData.class;

            case EMPTY:
                return EmptyStuffData.class;

            case VOTE_RESULT:
                return VoteResultStuffData.class;
        }
    }
}
