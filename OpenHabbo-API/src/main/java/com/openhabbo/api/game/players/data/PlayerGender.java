package com.openhabbo.api.game.players.data;

public enum PlayerGender {
    MALE,
    FEMALE;


    public static PlayerGender getGender(String gender) {
        switch (gender) {
            case "m":
                return MALE;

            case "f":
                return FEMALE;
        }

        return MALE;
    }
}
