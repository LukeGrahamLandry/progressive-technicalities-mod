package com.LukeTheDuke9801.progressivetechnicalities.util.books;

public class RocketHelp implements BookContents{
    public String getTitle(){
        return "Rocket Help";
    }

    @Override
    public String getContents() {
        return "To get to the next dimension, craft a Rocket to Luna. Then right click it with a clear path to the sky to " +
                "go to luna. When you get there, your rocket will revert to a basic rocket casing which can be crafted with dirt " +
                "to make a rocket home. Don't forget to wear an astronaut helmet because there's no air in space. You can buy one from " +
                "Spaceman Spiff (found in wanderer villages in the overworld). On Luna you can find dilithium ore which is " +
                "used to create rockets to other planets. ";
    }
}
