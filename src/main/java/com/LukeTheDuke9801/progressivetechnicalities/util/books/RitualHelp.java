package com.LukeTheDuke9801.progressivetechnicalities.util.books;

public class RitualHelp implements BookContents {
    public String getTitle(){
        return "Ritual Help";
    }

    @Override
    public String getContents() {
        return "Ritual structures can be found in the oil dimension. " +
                "However, they are incomplete. Perhaps replacing the ores under the rune stones with full " +
                "blocks would complete one. Then activate the ritual by right clicking the central stones with " +
                "a ritual catalyst. Activating the ritual requires a mob near by for it to sacrifice. There are " +
                "several catalysts one of which is the Feywild Portal Key which will send you to the feywild " +
                "dimension. Bring an extra portal key to get home.";
    }
}
